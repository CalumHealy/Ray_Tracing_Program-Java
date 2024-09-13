public class Colour {

    // Fields
    private int ColourR, ColourG, ColourB;

    // Constructors
    public Colour(){
        this.ColourR = 255;
        this.ColourG = 255;
        this.ColourB = 255;
    }

    public Colour(int r, int g, int b) {
        this.ColourR = r;
        this.ColourG = g;
        this.ColourB = b;
    }

    // Getters
    public int getColourR() {
        return this.ColourR;
    }

    public int getColourG() {
        return this.ColourG;
    }

    public int getColourB() {
        return this.ColourB;
    }

    // Setters
    public void setColour(int r, int g, int b) {
        this.ColourR = r;
        this.ColourG = g;
        this.ColourB = b;
    }
}
