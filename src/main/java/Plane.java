public class Plane {

    // Fields
    private Point position;
    private Vector normal;
    private Colour colour;

    // Constructors
    public Plane() {
        this.position = new Point(0, 0, 0);
        this.normal = new Vector(0, 1, 0);
        this.colour = new Colour(255, 0, 0);
    }

    public Plane(Point position, Vector normal) {
        this.position = position;
        this.normal = normal;
    }

    // Getters
    public Point getPosition() {
        return this.position;
    }

    public Vector getNormal() {
        return this.normal;
    }

    public Colour getColour() {
        return this.colour;
    }

    // Setters
    public void setPosition(Point position) {
        this.position = position;
    }

    public void setNormal(Vector normal) {
        this.normal = normal;
    }

    public void setColour(int colourR, int colourG, int colourB) {
        this.colour.setColour(colourR, colourG, colourB);
    }

    // Methods
    public double doesRayCollide(Camera camera, Plane plane, int pixelY, int pixelX, int width, int height) {
        Point cameraOrigin = camera.getPosition();

        // Calculate the ray direction for the given pixel
        double tanFOV = Math.tan(Math.toRadians(camera.getFOV() / 2.0));
        double aspectRatio = (double) width / height;
        double pixelNDCX = (pixelX + 0.5) / width;
        double pixelNDCY = (pixelY + 0.5) / height;
        double pixelScreenX = 2 * pixelNDCX - 1;
        double pixelScreenY = 1 - 2 * pixelNDCY;

        Vector rayVector = new Vector(
                (pixelScreenX * aspectRatio * tanFOV) + camera.getDirection().getVectorX(),
                (pixelScreenY * tanFOV) + camera.getDirection().getVectorY(),
                -1 + camera.getDirection().getVectorZ()
        ).normalize();

        // Calculate the intersection distance t
        Vector planeNormal = plane.getNormal();
        Vector planePoint = plane.getPosition().toVector();

        double denominator = rayVector.dot(planeNormal);
        if (Math.abs(denominator) < 1e-6) {
            // The ray is parallel to the plane
            return 0;
        }

        Vector cameraToPlane = planePoint.subtract(cameraOrigin.toVector());
        double t = cameraToPlane.dot(planeNormal) / denominator;

        // return (t >= 0) ? 1 : 0;
        return t;
    }
}
