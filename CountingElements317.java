import java.util.List;
import java.util.LinkedList;

/* Numbers not yet implemented. Parentheses not yet implemented.
*/

/** r/DailyProgrammer Intermediate #317.
 * Takes a chemical formula as text input.
 * Prints the quantity of each element.
 * @author u/cornfedinbred
 */
public class CountingElements317 {

  /** Inner element class.
   */
  private static class Element {
    String name;
    int quantity;

    // Constructor.
    Element(String n) {
        this.name = n;
        this.quantity = 1;
        elements.add(this);
    }
  }

  /** Input chemical formula to be analyzed. */
  private static String formula;
  /** Current element being checked. */
  private static String currElement;
  /** list of all elements found in formula so far. */
  private static List<Element> elements;

  /** Main method
   * @param args chemical formula to be analyzed.
   */
  public static void main(String[] args) {
    elements = new LinkedList<Element>();
    formula = args[0];

    int prevPosition = 0;
    int position = 1;
    int len = formula.length();
    while (position < len) {
      // read until next upper case character
      while (!Character.isUpperCase(formula.charAt(position))) {
        position++;
        if (position == len) {
          break;
        }
      }

      elementInterpreter(formula.substring(prevPosition, position));
      prevPosition = position;
      position++;
      if (position == len) {
        elementInterpreter(formula.substring(prevPosition, position));
      }
      System.err.println("foo");
    }
    /*
    while (position < len) {
      char current = formula.charAt(position);
      currElement = Character.toString(current);
      if (position + 1 < len) {
        if (Character.isLowerCase(formula.charAt(position + 1))) {
          position++;
          currElement += formula.charAt(position);
        }
      }
      elementCounter(currElement);
      position++;
    }
    */

    for (Element e : elements) {
      System.out.println(e.name + ": " + e.quantity);
    }
  }

  private static void elementCounter(String current) {
    for (Element e : elements) {
      if (e.name.equals(current)) {
        e.quantity++;
        return;
      }
    }
    Element temp = new Element(current);
  }

  // gets an element and sometimes a number. eg Cl6, C
  private static void elementInterpreter(String s) {
    int len = s.length();
    if (len == 1) {
      elementCounter(s);
      return;
    }
    if (len == 2) {
      try {
        int num = Integer.parseInt(Character.toString(s.charAt(1)));
        for (int i = 0; i < num; i++) {
          elementCounter(Character.toString(s.charAt(0)));
        }
        return;
      } catch (Exception e) {
        elementCounter(s);
        return;
      }
    }
    if (len > 2) {
      try {
        int num = Integer.parseInt(s.substring(1));
        for (int i = 0; i < num; i++) {
          elementCounter(Character.toString(s.charAt(0)));
        }
        return;
      } catch (Exception e) {
        int num = Integer.parseInt(s.substring(2));
        for (int i = 0; i < num; i++) {
          elementCounter(s.substring(0, 2)); // TODO: ensure this is first 2 letters
        }
        return;
      }
    }
    assert false;
  }
}
