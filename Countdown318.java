import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/* This program is incomplete. It only checks for solutions in which the
numbers stay in order, which is not how Countdown is played. I'm not
currently planning on finishing this program. */

/** r/DailyProgrammer Easy Challenge #318.
 * @author Eric Cao
 */
public class Countdown318 {

  /** Inner Node class.
   */
  public static class Node {
    int val; // result of equation
    List<Character> lst; // list of operations

    // constructor
    Node() {
      this.lst = new LinkedList<Character>();
    }

    // alternate constructor
    Node(List<Character> lst) {
      this.lst = lst;
    }

    /** Sets value */
    public int setVal(int val) {
      this.val = val;
      return this.val;
    }
  }

  /** Holds integers from user input */
  public static List<Integer> lst;

  /** Main method.
   * @param args 7 integers, the first 6 of which target the 7th.
   */
  public static void main(String[] args) {
    lst = new LinkedList<Integer>();
    Queue<Node> que = new LinkedList<Node>();

    // parse through user input
    for (int i = 0; i < args.length - 1; i++) {
      int temp = Integer.parseInt(args[i]);
      lst.add(temp);
    }
    int target = Integer.parseInt(args[args.length - 1]);

    // generate all combinations of operations
    Node n = new Node();
    que.add(n);
    while (true) { // exits when all combinations of operations generated
      n = que.remove();
      if (n.lst.size() == args.length - 2) {
        break;
      }

      List<Node> tempLst = ops(n);
      for (int i = 0; i < tempLst.size(); i++) {
        que.add((Node) tempLst.get(i));
      }
    }

    // calculate values and print if equal to target
    while (!que.isEmpty()) {
      //System.out.println(n.lst);
      //System.out.println(solve(n));
      if (n.setVal(solve(n)) == target) {
        System.out.println(n.lst);
      }
      n = que.remove();
    }
    //System.out.println(n.lst);
    //System.out.println(solve(n));
    if (n.setVal(solve(n)) == target) { // one more time for the last node
      System.out.println(n.lst);
    }
  }

  private static int solve(Node n) {
    int curr = lst.get(0);
    for (int i = 1; i < lst.size(); i++) {
      char c = n.lst.get(i - 1);
      switch (c) {
        case '+':
          curr += lst.get(i);
          break;
        case '-':
          curr -= lst.get(i);
          break;
        case '*':
          curr *= lst.get(i);
          break;
        case '/':
          if (lst.get(i) != 0) {
            if (curr % lst.get(i) == 0) {
              curr /= lst.get(i);
            }
          }
          break;
        default:
          System.err.println("solve broken");
      }
    }
    return curr;
  }

  /** Applies the 4 operations on the current value and the next value.
   * @param n Node to be changed.
   * @return List of nodes.
   */
  private static List<Node> ops(Node n) {
    List<Node> opsLst = new LinkedList<Node>();
    List<Character> temp = new LinkedList<Character>(n.lst);
    temp.add('+');
    Node no = new Node(temp);
    opsLst.add(no);
    temp = new LinkedList<Character>(n.lst);
    temp.add('-');
    no = new Node(temp);
    opsLst.add(no);
    temp = new LinkedList<Character>(n.lst);
    temp.add('*');
    no = new Node(temp);
    opsLst.add(no);
    temp = new LinkedList<Character>(n.lst);
    temp.add('/');
    no = new Node(temp);
    opsLst.add(no);
    return opsLst;
  }
}
