/**
 * Solver definition for the 8 Puzzle challenge
 * Construct a tree of board states using A* to find a path to the goal
 */

import java.util.*;

public class Solver {

    public int minMoves = -1;
    private State solutionState;
    private boolean solved = false;

    /**
     * State class to make the cost calculations simple
ffa     */
    private class State implements Comparable<State>{
        // Each state needs to keep track of its cost and the previous state
        private Board board;
        private int moves; // equal to g-cost in A*
        public int cost; // equal to f-cost in A*
        private State prev;

        public State(Board board, int moves, State prev) {
            this.board = board;
            this.moves = moves;
            this.prev = prev;
            // TODO
            cost = moves + board.manhattan();
        }

        public int compareTo(State s){
            return cost - s.cost;
        }
        @Override
        public boolean equals(Object s) {
            if (s == this) return true;
            if (s == null) return false;
            if (!(s instanceof State)) return false;
            return ((State) s).board.equals(this.board);
        }
    }

    /*
     * Return the root state of a given state
     */
    private State root(State state) {
        // TODO: Your code here
        while( state.prev != null)
            state = state.prev;
        return state;
    }

    /*
     * A* Solver
     * Find a solution to the initial board using A* to generate the state tree
     * and a identify the shortest path to the the goal state
     */
    public Solver(Board initial) {
        // first insert the initial search node into a priority queue

        solutionState = new State(initial, 0, null);
        solvepuzzle();
    }

    public boolean solvepuzzle(){
        // more number the moves, lesser the priority
        PriorityQueue<State> open = new PriorityQueue<>();
        ArrayList<State> closed = new ArrayList<>();
        State currstate;
        boolean ign;

        open.add(this.solutionState);

        if(!isSolvable()){
            solved = false;
            return false;
        }

        while(!open.isEmpty()){
            // poll retrieves and removes the head of this queue, or returns null if this queue is empty.
            State q = open.poll();
            for (Board b: q.board.neighbors()) {
                currstate = new State(b,q.moves+1, q);
                if(b.isGoal()){
                    this.solutionState = currstate;
                    this.solved = true;
                    this.minMoves = currstate.moves;
                    return true;
                }

                ign = false;

                for (State s: open) {
                    if(s.equals(currstate) && s.cost <= currstate.cost){
                        ign = true;
                        break;
                    }
                }

                for (State s: closed){
                    if(s.equals(currstate) && s.cost <= currstate.cost){
                        ign = true;
                        break;
                    }
                }

                if(!ign){
                    open.add(currstate);

                }
            }
            closed.add(q);

        }
        return false;
    }

    /*
     * Is the input board a solvable state
     * Research how to check this without exploring all states
     */
    public boolean isSolvable() {
        // TODO: Your code here
        return solutionState.board.solvable();
    }

    /*
     * Return the sequence of boards in a shortest solution, null if unsolvable
     */
    public Iterable<Board> solution() {
        State state = this.solutionState;
        ArrayList<Board> boards = new ArrayList<>();
        if(!isSolvable()){
            return null;
        }
        else{
            while(state != null){
                boards.add(state.board);
                state = state.prev;
            }
        }
        return boards;
    }

    public State find(Iterable<State> iter, Board b) {
        for (State s : iter) {
            if (s.board.equals(b)) {
                return s;
            }
        }
        return null;
    }

    /*
     * Debugging space
     */
    public static void main(String[] args) {
        int[][] initState = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
        Board initial = new Board(initState);

        Solver solver = new Solver(initial);
    }


}
