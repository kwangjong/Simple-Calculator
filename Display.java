import java.lang.Math;
/**
 * This class displays entered number and the resulting numbers
 * @author kj
 */
public class Display implements CalcGUI {
  private int WIDTH; // Width of the Button
  private int HEIGHT; // Height of the Button
  protected Calculator processing; // PApplet object where the button will be displayed
  private float[] position; // array storing x and y positions of the Button with respect to 
                            // the display window 
  protected String display;   // string that will be displayed

  /**
   * Creates a new Button object positioned at a given position of the display window
   * 
   * @param positionX x-coordinate of the button in the display window
   * @param positionY y-coordinate of the button in the display window
   * @param processing PApplet object that represents the display window
   */
  public Display(Calculator processing) {
    this.processing = processing;
    this.position = new float[2];
    this.position[0] = 1;
    this.position[1] = 1;
    this.WIDTH = 235;
    this.HEIGHT = 58;
    this.display = "";
  }
  
  /**
   * Draws the button to the display window.
   * It sets filling color dark gray when mouse is not over and light gray when mouse is over
   */
  @Override
  public void draw() {
    this.processing.noStroke();
    //fill rect transparent
    processing.fill(255,0);
    // draw the button (rectangle with a centered text)
    processing.rect(position[0], position[1],
        position[0] + WIDTH, position[1] + HEIGHT);
    processing.fill(255); // set the fill color to white
    // default font size is 40
    int fontSize = 40;
    processing.textSize(fontSize);
    // reduce if text width is larger
    while((WIDTH-10)-processing.textWidth(display)<0) {
      fontSize--;
      processing.textSize(fontSize);
    }
    processing.textAlign(processing.RIGHT, processing.BOTTOM);
    processing.text(display, position[0]+(WIDTH-5), position[1]+(HEIGHT-3)); 
    // display the text aligned to the right
  }
}
