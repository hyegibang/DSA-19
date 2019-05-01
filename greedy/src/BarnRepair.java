import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Gap implements Comparable<Gap> {
    int start,end,size;

    Gap(int s, int e){
        start = s;
        end = e;
        size = size();
    }

    public int size(){
        return end - start - 1;
    }

    public int compareTo(Gap g){
        return size - g.size;
    }
}

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        // TODO
        Arrays.sort(occupied);
        ArrayList<Gap> gaps = new ArrayList<>();
        for(int i = 1;i < occupied.length;i++){
            if(occupied[i] - occupied[i-1] > 1){
                gaps.add(new Gap(occupied[i-1], occupied[i]));
            }
        }
        Collections.sort(gaps);

        int boards = gaps.size() + 1;
        int num_stalls = occupied.length;
        while(boards > M){
            Gap g = gaps.remove(0);
            num_stalls += g.size;
            boards --;
        }


        return num_stalls;
    }
}

/*
Sort stall numbers
Find gaps between stalls
Sort gaps
consecutive stall with cows is covered by a board
Cover the gaps one by one (starting from the smallest) by merging boards
Do this until the number of boards is equal to the maximum number of boards that can be purchased
* */