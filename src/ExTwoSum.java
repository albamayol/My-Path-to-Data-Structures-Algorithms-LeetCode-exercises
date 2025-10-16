import java.util.HashMap;

public class ExTwoSum {
    public int[] twoSum(int[] nums, int target) {
        int[] indices = new int[2];
        int i, j;
        boolean found = false;
        for (i = 0; i < nums.length; i++) {
            for (j = 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target && i!=j){
                    indices[0] = i;
                    indices[1] = j;
                    found = true;
                    break;
                }
            }
            if(found) {
                break;
            }
        }
        return indices;
    }


    /**
     * En lugar de buscar dos números que sumen el target en cada par de iteraciones, lo que hacemos es lo siguiente:
     * Restamos el número actual del target para saber qué otro número necesitamos encontrar para que la suma sea igual a target.
     * Si el número que falta (llamado la diferencia) ya ha aparecido antes en el array, entonces sabemos que el número actual junto con ese número (la diferencia) suman el target.
     */
    public int[] twoSum2(int[] nums, int target) {
        // Crear un hashmap para almacenar el número y su índice
        HashMap<Integer, Integer> map = new HashMap<>();
        // Iterar sobre el array
        for (int i = 0; i < nums.length; i++) {
            // Calcular la diferencia entre el target y el número actual
            int difference = target - nums[i];
            // Verificar si la diferencia ya está en el hashmap
            if (map.containsKey(difference)) {
                // Si es así, devolver los índices
                return new int[] { map.get(difference), i };
            }
            // Si no, almacenar el número actual y su índice en el hashmap
            map.put(nums[i], i);
        }
        // Si no se encuentra una solución, devolver un array vacío (aunque según el problema siempre hay una solución)
        return new int[] {};
    }

}
