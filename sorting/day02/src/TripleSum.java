import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;


public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        // TODO
            HashMap<Integer, List<Integer[]>> sums = new HashMap<>();

            int count = 0;

            for (int i = 0; i < arr.length; i++) {
                for (int j = i; j < arr.length; j++) {
                    if (i == j) {
                        continue;
                    }

                    List<Integer[]> pairs = new ArrayList<>();

                    pairs.clear();

                    if (sums.containsKey(arr[i] + arr[j])) {
                        pairs.addAll(sums.get(arr[i] + arr[j]));
                        pairs.add(new Integer[]{i, j});
                        sums.put(arr[i] + arr[j], pairs);

                    } else {
                        pairs.add(new Integer[]{i, j});
                        sums.put(arr[i] + arr[j], pairs);
                    }
                }
            }

            for (int i = 0; i < arr.length; i++) {
                if (sums.containsKey(sum - arr[i])) {
                    for (Integer[] val : sums.get(sum - arr[i])) {
                        if (val[0] == i || val[1] == i) {
                            continue;
                        }
                        count++;
                    }
                }
            }
            return count / 3;
        }
    }

