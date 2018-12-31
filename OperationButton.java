import java.util.ArrayList;

/**
 * Operation Button: C, +/−, %, ÷, ×, −, +, =
 * @author kj
 */
public class OperationButton extends Button {
  /**
   * Constructor 
   * creates a button with operation
   * @param x x-coordiantes of the Button object
   * @param y y-coordinates of the Button object
   * @param label operator symbol
   * @param processing PApplet object that represents the display window
   */
  public OperationButton(float x, float y, String label, Calculator processing) {
    super(x, y, processing); //pass x, y, and park to superclass constructor
    this.label =  label; //set button label
    this.WIDTH = 58;
    this.HEIGHT = 47;
    this.colorNorm[0] = 66;
    this.colorNorm[1] = 133;
    this.colorNorm[2] = 244;
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
   * @param r red
   * @param g green
   * @param b blue
   * @param processing PApplet object that represents the display window
   */
  public OperationButton(float x, float y, String label, float r, float g, float b, Calculator processing) {
    super(x, y, processing); //pass x, y, and park to superclass constructor
    this.label =  label; //set button label
    this.WIDTH = 58;
    this.HEIGHT = 47;
    this.colorNorm[0] = r;
    this.colorNorm[1] = g;
    this.colorNorm[2] = b;
    this.colorClicked[0] = colorNorm[0] - 33;
    this.colorClicked[1] = colorNorm[1] - 35;
    this.colorClicked[2] = colorNorm[2] - 35;
  }

  /**
   * do operation
   */
  @Override
  public void action() {
    double num = Double.parseDouble(processing.display);
    switch(label) {
      case "AC":
        processing.entry = new ArrayList<String>();
        processing.display = "0";
        break;
      case "%":
        processing.display = "" + num/100;
        NumberButton.newEntry = true;
        break;
      case "+/−":
        if(num>0)
          processing.display = "-" + num;
        else if(num<0)
          processing.display = "" + (-num);
        NumberButton.newEntry = true;
        break;
      case "+":
        // . is used to identify number double always contains decimal point
        if(processing.entry.size() > 0 && NumberButton.newEntry) { 
          processing.entry.set(processing.entry.size()-1, "+");
        } else {
          processing.entry.add(""+num);
          processing.entry.add("+");
        }
        NumberButton.newEntry = true;
        break;
      case "-":
        if(processing.entry.size() > 0 && NumberButton.newEntry) {
          processing.entry.set(processing.entry.size()-1, "-");
        } else {
          processing.entry.add(""+num);
          processing.entry.add("-");
        }
        NumberButton.newEntry = true;
        break;
      case "×":
        if(processing.entry.size() > 0 && NumberButton.newEntry) {
          processing.entry.set(processing.entry.size()-1, "×");
        } else {
          processing.entry.add(""+num);
          processing.entry.add("×");
        }
        NumberButton.newEntry = true;
        break;
      case "÷":
        if(processing.entry.size() > 0 && NumberButton.newEntry) {
          processing.entry.set(processing.entry.size()-1, "÷");
        } else {
          processing.entry.add(""+num);
          processing.entry.add("÷");
        }
        NumberButton.newEntry = true;
        break;
      case "=":
        processing.entry.add(""+num);
        calculate();
        NumberButton.newEntry = true;
        break;
    }

    // if double is integer remove decimals
    if(processing.display.contains(".") 
      && processing.display.charAt(processing.display.length()-1) == '0')
      processing.display = "" + processing.display.substring(0,processing.display.indexOf("."));
  }

  private void calculate() {
    
    // calculate multiplication and division first
    int multIndex = processing.entry.indexOf("×");
    int divIndex = processing.entry.indexOf("÷");

    while(multIndex > 0 || divIndex > 0) {
      // do multiplication first if divIndex is  -1 or multIndex is lower than divIndex
      if(multIndex > 0 && multIndex < divIndex || divIndex < 0) {
        double left = Double.parseDouble(processing.entry.get(multIndex-1));
        double right = Double.parseDouble(processing.entry.get(multIndex+1));
        // replace left with the multiplication and remove operator and right
        processing.entry.set(multIndex-1, ""+(left*right));
        processing.entry.remove(multIndex);
        processing.entry.remove(multIndex);
      } else {
        double left = Double.parseDouble(processing.entry.get(divIndex-1));
        double right = Double.parseDouble(processing.entry.get(divIndex+1));
        // replace left with the division and remove operator and right
        processing.entry.set(divIndex-1, ""+(left/right));
        processing.entry.remove(divIndex);
        processing.entry.remove(divIndex);
      }
      multIndex = processing.entry.indexOf("×");
      divIndex = processing.entry.indexOf("÷");
    }

    //calculate addition and subtraction
    int addIndex = processing.entry.indexOf("+");
    int subIndex = processing.entry.indexOf("-");

    while(addIndex > 0 || subIndex > 0) {
      // do addition first if subIndex is  -1 or addIndex is lower than subIndex
      if(addIndex > 0 && addIndex < subIndex || subIndex < 0) {
        double left = Double.parseDouble(processing.entry.get(addIndex-1));
        double right = Double.parseDouble(processing.entry.get(addIndex+1));
        // replace left with the sum and remove operator and right
        processing.entry.set(addIndex-1, ""+(left+right));
        processing.entry.remove(addIndex);
        processing.entry.remove(addIndex);
      } else {
        double left = Double.parseDouble(processing.entry.get(subIndex-1));
        double right = Double.parseDouble(processing.entry.get(subIndex+1));
        // replace left with the difference and remove operator and right
        processing.entry.set(subIndex-1, ""+(left-right));
        processing.entry.remove(subIndex);
        processing.entry.remove(subIndex);
      }
      addIndex = processing.entry.indexOf("+");
      subIndex = processing.entry.indexOf("-");
    }

    processing.display = ""+processing.entry.get(0);
    // reset entry
    processing.entry = new ArrayList<String>();
  }
  
}
