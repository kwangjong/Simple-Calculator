import java.util.ArrayList;

/**
 * Calculator
 * main class of the program
 * this class extends PApplet
 * @author kj
 *
 */
public class Calculator extends PApplet {
  protected ArrayList<CalcGUI> listGUI; // ArrayList storing the current graphical objects
  protected ArrayList<String> entry; // ArrayList storing numbers and operators
  protected String display; // string that will be displayed
  /**
   * CallBack method Defines initial environment properties such as screen size and to load
   * background images and fonts as the program starts Initializes the backgroundImage and listGUI
   * instance fields.
   */
  @Override
  public void setup() {
    this.textAlign(PApplet.CENTER, PApplet.CENTER); // Sets the current alignment for drawing text
                                                    // to CENTER
    this.rectMode(PApplet.CORNERS); // Sets the location from which rectangles are drawn.
    // rectMode(CORNERS) interprets the first two parameters of rect() method as the location of one
    // corner, and the third and fourth parameters as the location of the opposite corner.
    // rect() method draws a rectangle to the display window
    this.focused = true; // Confirms that our Processing program is "focused," meaning that
    // it is active and will accept mouse or keyboard input.

    listGUI = new ArrayList<CalcGUI>(); // create the listGUI ArrayList that would store all the
    // graphic objects that would be drawn on the display window
    
    entry = new ArrayList<String>(); // create the entry ArrayList that would store
    // all the numbers and operator entered
   
    
    // create display
    listGUI.add(new Display(this));
    this.display = "";
    
    // C, +/−, %, ÷, ×, −, +, =
    listGUI.add(new OperationButton(1, 60, "AC", 128, 134, 139, this));
    listGUI.add(new OperationButton(60, 60, "+/−", 128, 134, 139, this));
    listGUI.add(new OperationButton(119, 60, "%", 128, 134, 139, this));
    listGUI.add(new OperationButton(178, 60, "÷", this));
    listGUI.add(new OperationButton(178, 108, "×", this));
    listGUI.add(new OperationButton(178, 156, "-", this));
    listGUI.add(new OperationButton(178, 204, "+", this));
    listGUI.add(new OperationButton(178, 252, "=", this));

    // 789 456 123 0.
    listGUI.add(new NumberButton(1, 108, "7", this));
    listGUI.add(new NumberButton(60, 108, "8", this));
    listGUI.add(new NumberButton(119, 108, "9", this));
    listGUI.add(new NumberButton(1, 156, "4", this));
    listGUI.add(new NumberButton(60, 156, "5", this));
    listGUI.add(new NumberButton(119, 156, "6", this));
    listGUI.add(new NumberButton(1, 204, "1", this));
    listGUI.add(new NumberButton(60, 204, "2", this));
    listGUI.add(new NumberButton(119, 204, "3", this));
    listGUI.add(new NumberButton(1, 252, "0", 117, 47, this));
    listGUI.add(new NumberButton(119, 252, ".", this));

    // initial value is zero
    this.display+=0;
  }
  /**
   * Sets the size of the application display window
   */
  @Override
  public void settings() {
    size(237, 300); // sets the size of the display window to 237 x 322 pixels
  }


  /**
   * Callback method called in an infinite loop. It draws the Jungle Park's window display
   */
  @Override
  public void draw() {
    //update display
    ((Display) listGUI.get(0)).display = this.display;
    // Set the color used for the background of the Processing window
    this.background(60, 64, 67);
    // traverse the listGUI array and draw each stored CalcGUI
    for (int i = 0; i < listGUI.size(); i++)
      listGUI.get(i).draw();
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  @Override
  public void mousePressed() {
    for(CalcGUI gui: listGUI) {
      if(gui instanceof Button) {
        ((Button) gui).mousePressed();
      }
    }
  }

  /**
   * Callback method called each time the mouse is released
   */
  @Override
  public void mouseReleased() {
    for(CalcGUI gui: listGUI) {
      if(gui instanceof Button) {
        ((Button) gui).mouseReleased();
      }
    }
  }

  /**
   * Callback method called each time the user presses a key
   */
  @Override
  public void keyPressed() {
    // traverse through arraylist and call keyPressed
    for(CalcGUI gui : listGUI) {
      if(gui instanceof Button) {
        ((Button) gui).keyPressed(Character.toUpperCase(this.key));
      }
    }
    // if backspace or delete is pressed remove the last character in display
    if (key == BACKSPACE) {
      if(this.display.length() == 1 || this.display.length() == 0)
        this.display = "0";
      else
        this.display = display.substring(0,this.display.length()-1);
    }

  }

  /**
   * Callback method called each time the user presses a key
   */
  @Override
  public void keyReleased() {
    // traverse through arraylist and call keyReleased
    for(CalcGUI gui : listGUI) {
      if(gui instanceof Button) {
        ((Button) gui).keyReleased(Character.toUpperCase(this.key));
      }
    }
  }

  /**
   * This main method starts the application
   * 
   * @param args
   */
  public static void main(String[] args) {
    // starts the application (calls PApplet main() method with the name
    // of the PApplet class to run as parameter)
    PApplet.main("Calculator");
  }
}
