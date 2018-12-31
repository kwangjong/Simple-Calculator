/**
 * number button
 * @author kj
 */
public class NumberButton extends Button {
  public static boolean newEntry =  false; // used to identify when to reset number

  /**
   * Constructor 
   * creates a button with number
   * @param x x-coordiantes of the Button object
   * @param y y-coordinates of the Button object
   * @param label operator symbol
   * @param processing PApplet object that represents the display window
   */
  public NumberButton(float x, float y, String label, Calculator processing) {
    super(x, y, processing); //pass x, y, and park to superclass constructor
    this.label =  label; //set button label
    this.WIDTH = 58;
    this.HEIGHT = 47;
    this.colorNorm[0] = 128;
    this.colorNorm[1] = 134;
    this.colorNorm[2] = 139;
    this.colorClicked[0] = colorNorm[0] - 33;
    this.colorClicked[1] = colorNorm[1] - 35;
    this.colorClicked[2] = colorNorm[2] - 35;
  }

  /**
   * Constructor 
   * creates a button with specified color 
   * @param x x-coordiantes of the Button object
   * @param y y-coordinates of the Button object
   * @param label operator symbol
   * @param width width
   * @param height height
   * @param processing PApplet object that represents the display window
   */
  public NumberButton(float x, float y, String label, int width, int height, Calculator processing) {
    super(x, y, processing); //pass x, y, and park to superclass constructor
    this.label =  label; //set button label
    this.WIDTH = width;
    this.HEIGHT = height;
    this.colorNorm[0] = 128;
    this.colorNorm[1] = 134;
    this.colorNorm[2] = 139;
    this.colorClicked[0] = colorNorm[0] - 33;
    this.colorClicked[1] = colorNorm[1] - 35;
    this.colorClicked[2] = colorNorm[2] - 35;
  }

  /**
   * concate number to the display
   */
  @Override
  public void action() {
    // donot concate zero if display is zero
    if(newEntry) {
      processing.display = label;
      newEntry = false;
    } else if(processing.display.equals("0")&&!label.equals("."))
      processing.display = label;
    else if(label.equals("0")&&processing.display.equals("0"))
      return;
    else if(label.equals(".")&&processing.display.contains("."))
      return;
    else
      processing.display += label;

  }
  
}
