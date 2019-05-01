class node{
    int vertex;
    int key;
}

public class MCCR {
    public static int MCCR(EdgeWeightedGraph G) {
        // TODO
        // Whether a vertex is in mst solution or not
        Boolean[] exists = new Boolean[G.numberOfV()];
        node[] e = new node[G.numberOfV()];

        IndexPQ<Integer> queue = new IndexPQ<>(G.numberOfV());

        for (int i = 0; i < G.numberOfV(); i++){
            e[i] = new node();
            e[i].key = Integer.MAX_VALUE;
            e[i].vertex = i;
            exists[i] = false;
        }

        // Include the initiating vertex
        exists[0] = true;
        e[0].key = 0;

        for(int i = 0; i < G.numberOfV(); i++){
            queue.insert(e[i].vertex,e[i].key);
        }

        while(!queue.isEmpty()){
            int start = queue.delMin();
            exists[start] = true;
            for(Edge neighbour: G.edges(start)){
                int vertex = neighbour.other(start);
                if(!exists[vertex]){
                    if(e[vertex].key > neighbour.weight()){
                        queue.decreaseKey(vertex,neighbour.weight());
                        e[vertex].key = neighbour.weight();
                    }
                }


            }
        }
        int sum = 0;
        for(int i = 0;i < G.numberOfV();i++){
            sum += e[i].key;
        }
        return sum;
    }

}

