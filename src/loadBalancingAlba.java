import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class loadBalancingAlba {
    public static String[] route_requests(int numTargets, int maxConnectionsPerTarget, String[] requests) { 
        //inicializamos el array de servers a su maximo
        int[] servers = new int[numTargets];
        for(int i = 0; i < servers.length; i++) {
            servers[i] = maxConnectionsPerTarget;
        }

        //mapa para linkear las conexiones a qué target están
        Map<String, Integer> connToTarget = new HashMap<>();

        //mapa para linkear las conexiones a objectIds
        Map<String, String> connToObject = new HashMap<>();
        //mapa para linkear según un objectId X a un Target Y --> SOLO PRIMERAS APARICIONES DE LAS CONEXIONES!!!
        Map<String, Integer> objectToTarget = new HashMap<>();
        //keep track of how many connections with that objectId X have been made. If there are no existing connections, then load balancing is applied normaly.
        Map<String, Integer> objectCount = new HashMap<>();
        
        //Lista de logs
        List<String> logs = new ArrayList<>();
        
        //empezamos a parsear las requests
        for(String req : requests) {
            //separamos los parametros de las requests
            String[] parts = req.split(",", -1);
            String action = parts[0];
            String connId = parts[1];
            String userId = parts[2];
            String objectId = parts[3];

            switch (action) {
                case "CONNECT":
                    int bestServer = 0; //cogemos el index del primer server como el mejor para comparar
                    int bestServerLoad = servers[0]; //carga/conexiones disponibles del primer server

                    int target = objectToTarget.getOrDefault(objectId,0);
                    if (target != 0) { //ya existe alguna conexion de ese objeto
                        bestServer = target;
                    } else { //aun no existe conexion de ese objeto
                        for (int i = 0; i < servers.length; i++) {
                            if (servers[i] > bestServerLoad) { //si ese server tieme mayor num de conexiones disponibles
                                //actualizamos index bestServer
                                bestServer = i;
                                //actualizamos carga del mejor server
                                bestServerLoad = servers[i];
                            }
                        }
                    }
                   
                    //Registramos la conexión
                    --servers[bestServer]; //decrementamos numero de conexiones
                    connToTarget.put(connId, bestServer); //la connId está en el index del server bestServer
                    connToObject.put(connId, objectId); //la connId está linkeada a ese objeto

                    //actualizamos object stickiness
                    int count = objectCount.getOrDefault(objectId, 0) + 1;
                    objectCount.put(objectId, count);
                    if (count == 1) {
                        objectToTarget.put(objectId, bestServer); //objX --> TargetIndex ONLY NEW OBJECT CONNECTIONS TO A TARGET, FIRST APPEARANCE!!!! ONLY TRACK X OBJECT TO AN Y TARGET, NOT ALL REGISTERS
                    }

                    /*if (count == 0) {
                        objectCount.put(objectId, 1);
                    } else {
                        objectCount.put(objectId, count+1);
                    }*/                  

                    //Añadir al log
                    logs.add(connId + "," + userId + "," + (bestServer + 1) + "\n");

                    break;
                case "DISCONNECT":                    
                    Integer targetDisconnect = connToTarget.remove(connId); //we get the index of the server where that target is connected to. Returns: the previous value associated with key, or null if there was no mapping for key
                    if (targetDisconnect != null) {
                        ++servers[targetDisconnect];
                        if(connToObject.remove(connId) != null) {
                            int countDisconnect = objectCount.getOrDefault(objectId, 0) - 1; //if 0, object se queda sin conexiones (remove from countObject + objectToTarget association); if -1 there was no object associated, whatever i do will not influentiate
                            if (countDisconnect <= 0) {
                                objectCount.remove(objectId);
                                objectToTarget.remove(objectId);
                            } else {
                                objectCount.put(objectId, countDisconnect); //actualizamos count de ese object
                            }
                        }
                    }
                    break;
                default:
                    break;
            }
        }

        return requests;
    }
}
