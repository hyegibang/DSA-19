import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class Permutations {

    private static void permutationsHelper(List<Integer> current, List<Integer> unused, List<List<Integer>> permutations){
        //base case
        if (unused.isEmpty()){
            permutations.add(new LinkedList<>(current));
        }

        //iterate every choice of character
        for (Integer c : new LinkedList<>(unused)){
            current.add(c);
            unused.remove(c); // remove the first
            permutationsHelper(current, unused, permutations);
            unused.add(c); // going to next branch
            current.remove(c); //remove the last
        }

    }
    public static List<List<Integer>> permutations(List<Integer> A) {
        List<List<Integer>> permutations = new LinkedList<>();
        List<Integer> current = new LinkedList<>();
        List<Integer> unused = new LinkedList<>();

        for(int i:A){
            unused.add(i);
        }
        permutationsHelper(current,unused,permutations);
        return permutations;
    }

}
