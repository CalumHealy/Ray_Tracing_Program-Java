public class Point {

    // Fields
    private double x;
    private double y;
    private double z;

    // Constructors

    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Getters
    public Point getPoint() {
        return this;
    }

    public double getPointX() {
        return this.x;
    }

    public double getPointY() {
        return this.y;
    }

    public double getPointZ() {
        return this.z;
    }

    // Setters
    public void setPoint(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Methods

    public Vector toVector() {
        return new Vector(x, y, z);
    }

    public String toString() {
        return String.format("Point(%.2f, %.2f, %.2f)", x, y, z);
    }
}
