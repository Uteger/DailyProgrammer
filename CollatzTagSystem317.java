import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/** r/dailyprogrammer challenge #317.
 *
 * @author u/cornfedinbred
 */
public class CollatzTagSystem317 {

  /** Main method.
   *
   * @param args not used.
   */
  public static void main(String[] args) {
    Queue<Character> que = new LinkedList<Character>();
    Scanner keys = new Scanner(System.in);

    System.out.print("Enter the starting integer: ");
    int start = keys.nextInt();
    for (int i = 0; i < start; i++) {
      que.add('a');
    }

    while (true) {
      char temp = que.remove();
      try {
        que.remove();
      } catch (NoSuchElementException e) {
        System.exit(0);
      }
      switch (temp) {
        case 'a':
          que.add('b');
          que.add('c');
          break;
        case 'b':
          que.add('a');
          break;
        case 'c':
          for (int i = 0; i < 3; i++) {
            que.add('a');
          }
      }
      System.out.println(que.toString());
    }
  }
}
