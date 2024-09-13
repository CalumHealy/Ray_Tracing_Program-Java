public class Vector {

    // Fields
    private double x, y, z;

    // Constructors

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Getters
    public Vector getVector() {
        return this;
    }

    public double getVectorX() {
        return this.x;
    }

    public double getVectorY() {
        return this.y;
    }

    public double getVectorZ() {
        return this.z;
    }

    // Setters
    public void setVectorXYZ(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    // Methods

    public Vector subtract(Vector v) {
        return new Vector(this.x - v.x, this.y - v.y, this.z - v.z);
    }

    public double dot(Vector v) {
        return this.x * v.x + this.y * v.y + this.z * v.z;
    }

    public Vector normalize() {
        double length = Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        return new Vector(this.x / length, this.y / length, this.z / length);
    }

    public String toString() {
        return String.format("Vector(%.2f, %.2f, %.2f)", x, y, z);
    }
}
