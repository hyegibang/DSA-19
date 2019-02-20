import java.util.HashMap;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        // TODO
        HashMap <Double, Integer> distance = new HashMap<>(); // double - number of equidistant, Integer - points
        int count =0;

        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                double numDist = Math.pow(points[i][0]-points[j][0], 2)+Math.pow(points[i][1]-points[j][1], 2);

                if (distance.containsKey(numDist)) {
                    distance.put(numDist, distance.get(numDist) + 1);
                }
                else {
                    distance.put(numDist, 1);
                }
            }

            for (Integer k : distance.values()) {
                count = count + k * (k - 1);
            }

            distance.clear();

        }

        return count;
    }


}


