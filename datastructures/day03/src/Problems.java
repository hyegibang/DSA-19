import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }

    public static List<Integer> removeKDigits(int[] A, int k) {
        // TODO: your code here
        // For now, return a List that's correct size, but contains only 0s
        // Important to keep the most left small
        Stack<Integer> l = new Stack<>();

        for (int i = 0; i < A.length; i++) {
            //looking for smallest left value with counter k
            while (!l.empty() && k != 0 && l.peek() > A[i]) {
                l.pop();
                k--;
                }

            if (l.size() < A.length -k ) {
                l.push(A[i]);
            }

        }
        return l;
    }


    public static boolean isPalindrome(Node n) {
        // TODO: your code here
        Node nn = n;
        Node node1 = n;

        int count = 0;
        int midindex;

        while (nn != null) { //length of ll
            nn = nn.next;
            count++;
        }

        if (count % 2 == 0)
            midindex = count / 2; //even
        else
            midindex = (count / 2) + 1; //odd

        for (int i = 0; i < midindex; i++) { // find midindex node
            node1 = node1.next;
        }

        Node curr = node1; // reverse end part
        Node temp = null;
        Node prev = null;

        while (curr != null) {
            temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        curr = prev;

        while (curr != null) {
            System.out.print(curr.val);
            if (n.val != curr.val)
                return false;
            else {
                n = n.next;
                curr = curr.next;

            }

        }
        return true;
    }


    public static String infixToPostfix(String s) {
        // TODO
        String result = "";
        Stack<String> operations = new Stack<>();

        for (int i =0; i < s.length(); i++){
            if (s.charAt(i) == '+'|| s.charAt(i) == '-'|| s.charAt(i) == '*'|| s.charAt(i) == '/') {
                operations.push(s.charAt(i) + "");
            } else if (s.charAt(i) == ')'){
                while(!operations.isEmpty()){
                    result = result + operations.pop() + " " ;
                }
            }
            else if (s.charAt(i)!= '(' && s.charAt(i) != ' '){
                result = result + s.charAt(i) + " ";
            }

            }
        return result.trim();
    }

}

