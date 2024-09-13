public class Camera {

    // Fields
    private Point position;
    private Vector direction;
    private float AspectRatio;
    private int FOV;

    // Constructor
    public Camera() {
        this.position = new Point(0, 5, 0);
        this.direction = new Vector(0, 0, 1);
        this.AspectRatio = (float) 16/9;
        this.FOV = 35;
    }

    public Camera(Point position, Vector direction, float AspectRatio, int FOV) {
        this.position = position;
        this.direction = direction;
        this.AspectRatio = AspectRatio;
        this.FOV = FOV;
    }

    // Getters
    public Point getPosition() {
        return this.position;
    }

    public Vector getDirection() {
        return this.direction;
    }

    public float getAspectRation() {
        return this.AspectRatio;
    }

    public int getFOV() {
        return this.FOV;
    }

    // Setters
    public void setPosition(Point position) {
        this.position = position;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    public void setAspectRation(float AspectRatio) {
        this.AspectRatio = AspectRatio;
    }

    public void setFOV(int FOV) {
        this.FOV = FOV;
    }

}
