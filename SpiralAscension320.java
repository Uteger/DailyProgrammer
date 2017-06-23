/** r/DailyProgrammer Spiral Ascension #320. User inputs size of spiral and
 * direction of spiral: 0 if CW, 1 if CCW.
 */
public class SpiralAscension320 {
  // TODO: review 2D array x, y orientation syntax

  /** Next number to be filled in */
  private static int curr;
  /** Max size specified by user */
  private static int maxSize;
  /** X position of the last number to be filled in */
  private static int xpos;
  /** Y position of the last number to be filled in */
  private static int ypos;
  /** 2D array that holds the spiral */
  private static int[][] spiral;
  /** True if spiral goes clockwise. False if CCW */
  private static boolean CW;

  /** Main method.
   * @param args size of spiral; direction of spiral
   */
  public static void main(String[] args) {
    // initialize variables to starting values
    maxSize = Integer.parseInt(args[0]); // args[0] is spiral size
    spiral = new int[maxSize][maxSize];
    curr = 2; // first slot is filled manually
    xpos = 0;
    ypos = 0;
    int currDirection;
    if (args[1].equals("0")) { // args[1] is direction of spiral
      CW = true;
      currDirection = 0; // starting direction: 0 is to the right
    } else {
      CW = false;
      currDirection = 1; // 1 is down
    }

    // populate spiral
    spiral[xpos][ypos] = 1; // sets 0,0 to 1
    int maxNum = maxSize * maxSize;
    while (curr <= maxNum) { // end when max number is reached
      populate(currDirection); // populate an entire row / column
      // change direction depending on CW or CCW
      // 0: right   1: down   2: left   3: up
      currDirection = (CW) ? (currDirection + 1) % 4 : (currDirection + 3) % 4;
    }

    // print spiral
    for (int j = 0; j < maxSize; j++) {
      for (int i = 0 ; i < maxSize; i++) {
        // TODO: change printf to match length of longest number
        System.out.printf("%02d ",spiral[i][j]); // prints with 0's
      }
      System.out.println();
    }
  }

  private static void populate(int currDirection) {
    // TODO: learn about higher order functions; determine if usable here
    while (true) { // check for end conditions within the switch
      switch (currDirection) {
        case 0: // right
          if (xpos + 1 == maxSize) { // check for OutOfBoundsException
            return;
          } else if (spiral[xpos + 1][ypos] != 0) { // end if already populated
            return;
          }
          xpos++; // move position to the right
          break;
        case 1: // down
          if (ypos + 1 == maxSize) {
            return;
          } else if (spiral[xpos][ypos + 1] != 0) {
            return;
          }
          ypos++;
          break;
        case 2: // left
          if (xpos == 0) {
            return;
          } else if (spiral[xpos - 1][ypos] != 0) {
            return;
          }
          xpos--;
          break;
        case 3: // up
          if (ypos == 0) {
            return;
          } else if (spiral[xpos][ypos - 1] != 0) {
            return;
          }
          ypos--;
        default:
          break;
      }
      spiral[xpos][ypos] = curr++; // populate current position, update curr
    }
  }
}
