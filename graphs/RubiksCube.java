import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


// this is our implementation of a rubiks cube. It is your job to use A* or some other search algorithm to write a
// solve() function
public class RubiksCube {

    private BitSet cube;

    private int moves;
    public double cost;

    private RubiksCube prev;
    public char last_move;


    // initialize a solved rubiks cube
    public RubiksCube() {
        // 24 colors to store, each takes 3 bits
        cube = new BitSet(24 * 5);
        for (int side = 0; side < 6; side++) {
            for (int i = 0; i < 4; i++) {
                setColor(side * 4 + i, side);
            }
        }
    }

    // initialize a rubiks cube with the input bitset
    private RubiksCube(BitSet s) {
        cube = (BitSet) s.clone();
    }

    // creates a copy of the rubics cube
    public RubiksCube(RubiksCube r) {
        cube = (BitSet) r.cube.clone();
    }


    private RubiksCube(BitSet cube, char last_move) {
        this.cube = (BitSet) cube.clone();
        this.last_move = last_move;
    }



    // return true if this rubik's cube is equal to the other rubik's cube
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RubiksCube))
            return false;
        RubiksCube other = (RubiksCube) obj;
        return other.cube.equals(cube);
    }

    /**
     * return a hashCode for this rubik's cube.
     *
     * Your hashCode must follow this specification:
     *   if A.equals(B), then A.hashCode() == B.hashCode()
     *
     * Note that this does NOT mean:
     *   if A.hashCode() == B.hashCode(), then A.equals(B)
     */
    @Override
    public int hashCode() {
        return cube.hashCode();
    }

    public boolean isSolved() {
        return this.equals(new RubiksCube());
    }


    // takes in 3 bits where bitset.get(0) is the MSB, returns the corresponding int
    private static int bitsetToInt(BitSet s) {
        int i = 0;
        if (s.get(0)) i |= 16;
        if (s.get(1)) i |= 8;
        if (s.get(2)) i |= 4;
        if (s.get(3)) i |= 2;
        if (s.get(4)) i |= 1;
        return i;
    }

    // takes in a number 0-5, returns a length-3 bitset, where bitset.get(0) is the MSB
    private static BitSet intToBitset(int i) {
        BitSet s = new BitSet(5);
        if (i % 2 == 1) s.set(4, true);
        i /= 2;
        if (i % 2 == 1) s.set(3, true);
        i /= 2;
        if (i % 2 == 1) s.set(2, true);
        i /= 2;
        if (i % 2 == 1) s.set(1, true);
        i /= 2;
        if (i % 2 == 1) s.set(0, true);
        return s;
    }

    // index from 0-23, color from 0-5
    private void setColor(int index, int number) {
        BitSet colorBitset = intToBitset(number);
        for (int i = 0; i < 5; i++)
            cube.set(index * 5 + i, colorBitset.get(i));
    }


    // index from 0-23, returns a number from 0-5
    private int getColor(int index) {
        return bitsetToInt(cube.get(index * 5, (index + 1) * 5));
    }

    // given a list of rotations, return a rubik's cube with the rotations applied
    public RubiksCube rotate(List<Character> c) {
        RubiksCube rub = this;
        for (char r : c) {
            rub = rub.rotate(r);
        }
        return rub;
    }


    // Given a character in ['u', 'U', 'r', 'R', 'f', 'F'], return a new rubik's cube with the rotation applied
    // Do not modify this rubik's cube.
    public RubiksCube rotate(char c) {
        int[] faceFrom = null;
        int[] faceTo = null;
        int[] sidesFrom = null;
        int[] sidesTo = null;
        // colors move from the 'from' variable to the 'to' variable
        switch (c) {
            case 'u': // clockwise
            case 'U': // counterclockwise
                faceFrom = new int[]{0, 1, 2, 3};
                faceTo = new int[]{1, 2, 3, 0};
                sidesFrom = new int[]{4, 5, 8, 9, 17, 16, 21, 20};
                sidesTo = new int[]{21, 20, 4, 5, 8, 9, 17, 16};
                break;
            case 'r':
            case 'R':
                faceFrom = new int[]{8, 9, 10, 11};
                faceTo = new int[]{9, 10, 11, 8};
                sidesFrom = new int[]{6, 5, 2, 1, 17, 18, 13, 14};
                sidesTo = new int[]{2, 1, 17, 18, 13, 14, 6, 5};
                break;
            case 'f':
            case 'F':
                faceFrom = new int[]{4, 5, 6, 7};
                faceTo = new int[]{5, 6, 7, 4};
                sidesFrom = new int[]{3, 2, 8, 11, 14, 15, 23, 20};
                sidesTo = new int[]{8, 11, 14, 15, 23, 20, 3, 2};
                break;
            default:
                System.out.println(c);
                assert false;
        }

        // if performing a counter-clockwise rotation, swap from and to
        if (Character.isUpperCase(c)) {
            int[] temp;
            temp = faceFrom;
            faceFrom = faceTo;
            faceTo = temp;
            temp = sidesFrom;
            sidesFrom = sidesTo;
            sidesTo = temp;
        }

        RubiksCube res = new RubiksCube(cube);
        for (int i = 0; i < faceFrom.length; i++) res.setColor(faceTo[i], this.getColor(faceFrom[i]));
        for (int i = 0; i < sidesFrom.length; i++) res.setColor(sidesTo[i], this.getColor(sidesFrom[i]));
        return res;
    }

    public Iterable<RubiksCube> neighbors(){
        List<RubiksCube> neighbors = new ArrayList<>();
        char[] moves = {'u', 'U', 'r', 'R', 'f', 'F'};
        for (char rotation: moves){
            RubiksCube tempcube = new RubiksCube(rotate(rotation).cube,rotation);
            neighbors.add(tempcube);
        }
        return neighbors;
    }

    // returns a random scrambled rubik's cube by applying random rotations
    public static RubiksCube scrambledCube(int numTurns) {
        RubiksCube r = new RubiksCube();
        char[] listTurns = getScramble(numTurns);
        for (int i = 0; i < numTurns; i++) {
            r= r.rotate(listTurns[i]);
        }
        return r;
    }

    public static char[] getScramble(int size){
        char[] listTurns = new char[size];
        for (int i = 0; i < size; i++) {
            switch (ThreadLocalRandom.current().nextInt(0, 6)) {
                case 0:
                    listTurns[i] = 'u';
                    break;
                case 1:
                    listTurns[i] = 'U';
                    break;
                case 2:
                    listTurns[i] = 'r';
                    break;
                case 3:
                    listTurns[i] = 'R';
                    break;
                case 4:
                    listTurns[i] = 'f';
                    break;
                case 5:
                    listTurns[i] = 'F';
                    break;
            }
        }
        return listTurns;
    }

    public double findCost() {
        int g_cost = this.moves;
        double f_cost = this.heuristic(this.cube)/3.0;
        double cost = g_cost+f_cost;
        return cost;
    }

    public int heuristic(BitSet s) {
        RubiksCube goal = new RubiksCube();
        int count = 0;
        for(int i=0; i< 24*3;i=i+3){
            if(!s.get(i,i+3).equals(goal.cube.get(i,i+3))) {
                count++;
            }
        }

        return count;
    }


    public static Comparator<RubiksCube> comp = new Comparator<RubiksCube>(){

        @Override
        public int compare(RubiksCube a, RubiksCube b) {
            if (a.cost<b.cost){
                return -1;
            } else if (b.cost<a.cost){
                return 1;
            } else {
                return 0;
            }
        }

    };

    public void updateCube(RubiksCube prev) {

        this.moves = prev.moves +1;
        this.prev = prev;

        cost = findCost();
    }


    // return the list of rotations needed to solve a rubik's cube
    public List<Character> solve() {

        RubiksCube solutionState = new RubiksCube();
        PriorityQueue<RubiksCube> openQueue = new PriorityQueue<>(comp);
        Map<RubiksCube, Double> minCostVisited = new HashMap<>();
        openQueue.add(this);
        RubiksCube thisState = this;
        while (!openQueue.isEmpty()) {

            thisState = openQueue.poll();
            Iterable<RubiksCube> neighbors = thisState.neighbors();
            for (RubiksCube neighState : neighbors) {
                neighState.updateCube(thisState);

                if (neighState != null && neighState.heuristic(neighState.cube) == 0){
                    solutionState.moves = thisState.moves+1;
                    solutionState.last_move = neighState.last_move;
                    solutionState.prev=thisState;
                    return solutions(solutionState);
                }

                boolean ignore = false;

                for (RubiksCube openState: openQueue){
                    if (openState.cube.equals(neighState.cube)){

                        ignore = true;
                        if (neighState.cost<openState.cost) {
                            openState.cost = neighState.cost;
                            openState.moves = neighState.moves;
                            openState.prev = thisState;
                        }
                    }
                }

                if (minCostVisited.containsKey(neighState)) {
                    ignore = true;
                    if (minCostVisited.get(neighState) > neighState.cost) {
                        minCostVisited.put(neighState, neighState.cost);
                    }
                }

                if (ignore == false){
                    neighState.prev = thisState;
                    openQueue.add(neighState);
                }
            }

            minCostVisited.put(thisState, thisState.cost);
        }
        return null;
    }

    private List<Character> solutions(RubiksCube solution){

        LinkedList<Character> totalRotations = new LinkedList<>();

        RubiksCube current = solution;

        while (current.moves != 0){
            totalRotations.addFirst(current.last_move);
            current = current.prev;
        }

        return totalRotations;

    }

}