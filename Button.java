/**
 * This a super class for any Button that can be added to a PApplet application
 * This class implements the CalcGUI interface
 * @author kj
 */
public class Button implements CalcGUI {
  protected int WIDTH; // Width of the Button
  protected int HEIGHT; // Height of the Button
  protected Calculator processing; // PApplet object where the button will be displayed
  private float[] position; // array storing x and y positions of the Button with respect to 
                            // the display window 
  protected String label;   // text/label that represents the button
  protected float[] colorNorm; // color when not clicked
  protected float[] colorClicked; // color when pressed
  boolean clicked;

  /**
   * Creates a new Button object positioned at a given position of the display window
   * 
   * @param positionX x-coordinate of the button in the display window
   * @param positionY y-coordinate of the button in the display window
   * @param processing PApplet object that represents the display window
   */
  public Button(float x, float y, Calculator processing) {
    this.processing = processing;
    this.position = new float[2];
    this.position[0] = x;
    this.position[1] = y;
    this.colorNorm = new float[3];
    this.colorClicked = new float[3];
    clicked = false;
  }
  
  /**
   * Draws the button to the display window.
   * It sets filling color dark gray when mouse is not over and light gray when mouse is over
   */
  @Override
  public void draw() {
    this.processing.noStroke();
    //set the fill color
    if(clicked)
      processing.fill(colorClicked[0],colorClicked[1],colorClicked[2]);
    else
      processing.fill(colorNorm[0],colorNorm[1],colorNorm[2]);
    // draw the button (rectangle with a centered text)
    processing.rect(position[0], position[1],
        position[0] + WIDTH, position[1] + HEIGHT);
    processing.fill(255); // set the fill color to black
    processing.textSize(18);
    processing.textAlign(processing.CENTER, processing.CENTER);
    processing.text(label, position[0]+WIDTH/2.0f, position[1]+HEIGHT/2.0f); // display the text of the current button
  }
  
  /**
   * change color when pressed
   */
  public void mousePressed() {
    if (isMouseOver()){
      clicked = true;
      draw();
    }  
  }

  /**
   * revert color when released
   */
  public void mouseReleased() {
    if (isMouseOver()) {
      clicked = false;
      draw();
      action();
    }
  }

  /**
   * change color when pressed
   * @param key key being pressed
   */
  public void keyPressed(char key) {
    Boolean matched = false;
    switch(label) {
      case "0":
      case "1":
      case "2":
      case "3":
      case "4":
      case "5":
      case "6":
      case "7":
      case "8":
      case "9":
      case ".":
      case "%":
      case "+":
      case "-":
        matched = this.label.equals(""+key);
        break;
      case "÷":
        matched = key == '/';
        break;
      case "×":
        matched = key == '*';
        break;
      case "=":
        matched = this.label.equals(""+key) || key == processing.ENTER;
        break;
      case "AC":
        matched = key == 'C';
        break;
    }
    if (matched){
      clicked = true;
      draw();
      action();
    }  
  }

  /**
   * revert color when released
   */
  public void keyReleased(char key) {
    for(CalcGUI gui: processing.listGUI) {
      if(gui instanceof Button) {
        ((Button) gui).clicked = false;
      }
    }
  }
  
  /**
   * Prints message if button was pressed
   */
  public void action() {
    System.out.println(this.label+" called");
  }

  /**
   * Check if mouse is over the button object
   * @return true if mouse is inside the button object, otherwise return false
   */
  public boolean isMouseOver() {
    if (this.processing.mouseX > this.position[0]
        && this.processing.mouseX < this.position[0] + WIDTH
        && this.processing.mouseY > this.position[1]
        && this.processing.mouseY < this.position[1] + HEIGHT)
      return true;
    return false;
  }
}
