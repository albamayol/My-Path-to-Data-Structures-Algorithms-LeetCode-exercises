import java.util.*;

public class loadBalancing {
    public static String[] route_requests(int numTargets, int maxConnectionsPerTarget, String[] requests) {
        // For Parts 1–3:
        // load[i] = remaining capacity proxy (starts equal) — we decrement on CONNECT, increment on DISCONNECT.
        // Since Part 4 isn't enforced yet, this can go negative without rejecting.
        int[] load = new int[numTargets];
        Arrays.fill(load, maxConnectionsPerTarget);

        // connectionId -> target (0-based) / user / object (needed for DISCONNECT & stickiness counts)
        Map<String, Integer> connToTarget = new HashMap<>();
        Map<String, String>  connToUser   = new HashMap<>();
        Map<String, String>  connToObject = new HashMap<>();

        // Part 3: object stickiness
        // objectId -> target index (where there is at least one active connection with that object)
        Map<String, Integer> objectToTarget = new HashMap<>();
        // objectId -> active connection count
        Map<String, Integer> objectCount    = new HashMap<>();

        List<String> logs = new ArrayList<>();

        for (String raw : requests) {
            String[] p = raw.split(",", -1);
            for (int i = 0; i < p.length; i++) p[i] = p[i].trim();
            String action = p[0];

            switch (action) {
                case "CONNECT": {
                    String connId   = p[1];
                    String userId   = p[2];
                    String objectId = p[3];

                    // Part 3: if object already active, must route to same target.
                    Integer sticky = objectToTarget.get(objectId);
                    int bestIdx;

                    if (sticky != null) {
                        bestIdx = sticky;
                    } else {
                        // Part 1: choose target with highest remaining capacity (i.e., fewest active conns).
                        // Tie -> smaller index.
                        bestIdx = 0;
                        int bestCap = load[0];
                        for (int i = 1; i < numTargets; i++) {
                            if (load[i] > bestCap) { // higher remaining capacity = less loaded
                                bestCap = load[i];
                                bestIdx = i;
                            }
                        }
                    }

                    // Register connection
                    load[bestIdx]--; // consume capacity (can go negative in parts 1–3)
                    connToTarget.put(connId, bestIdx);
                    connToUser.put(connId, userId);
                    connToObject.put(connId, objectId);

                    // Update object stickiness & count
                    int cnt = objectCount.getOrDefault(objectId, 0) + 1;
                    objectCount.put(objectId, cnt);
                    if (cnt == 1) {
                        objectToTarget.put(objectId, bestIdx);
                    }

                    // Log (1-based target index)
                    logs.add(connId + ", " + userId + ", " + (bestIdx + 1));
                    break;
                }

                case "DISCONNECT": {
                    String connId = p[1];
                    Integer t = connToTarget.remove(connId);
                    if (t != null) {
                        load[t]++; // free capacity
                        String objectId = connToObject.remove(connId);
                        connToUser.remove(connId);

                        if (objectId != null) {
                            int cnt = objectCount.getOrDefault(objectId, 0) - 1;
                            if (cnt <= 0) {
                                objectCount.remove(objectId);
                                objectToTarget.remove(objectId); // no active connections -> clear stickiness
                            } else {
                                objectCount.put(objectId, cnt);
                                // stickiness remains mapped to the (still active) target
                            }
                        }
                    }
                    break;
                }

                default:
                    // Parts 1–3: ignore others (e.g., SHUTDOWN not required yet)
                    break;
            }
        }

        return logs.toArray(new String[0]);
    }

    // Optional quick test
    public static void main(String[] args) {
        String[] reqs = {
            // Part 3 example: force same object to same target
            "CONNECT, conn1, userA, obj1",
            "CONNECT, conn2, userB, obj1", // must go to same target as conn1
            // Part 2 example: after disconnect, can rebalance
            "DISCONNECT, conn1, userA, obj1",
            "CONNECT, conn3, userC, obj2"
        };
        String[] out = route_requests(2, 100, reqs);
        for (String s : out) System.out.println(s);
        // Expected pattern:
        // conn1, userA, 1
        // conn2, userB, 1   (stickiness)
        // conn3, userC, 2   (rebalanced since only obj1 on target 1 remains)
    }
}
