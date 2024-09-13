public class Sphere {

    // Fields
    private Point position;
    private double radius;
    private Colour colour;

    // Constructors
    public Sphere() {
        this.position = new Point(0, 0, 0);
        this.radius = 1;
        this.colour = new Colour(0, 255, 0);
    }

    public Sphere(Point position, int radius) {
        this.position = position;
        this.radius = radius;
    }

    // Getters
    public Point getPosition() {
        return this.position;
    }

    public double getRadius() {
        return this.radius;
    }

    public Colour getColour() {
        return this.colour;
    }

    // Setters
    public void setPosition(Point position) {
        this.position = position;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setColour(int colourR, int colourG, int colourB) {
        this.colour.setColour(colourR, colourG, colourB);
    }

    public void setColourR(int colourR) {
        this.colour.setColour(colourR, this.colour.getColourG(), this.colour.getColourB());
    }

    public void setColourG(int colourG) {
        this.colour.setColour(this.colour.getColourR(), colourG, this.colour.getColourB());
    }

    public void setColourB(int colourB) {
        this.colour.setColour(this.colour.getColourR(), this.colour.getColourG(), colourB);
    }

    // Methods
//    public double doesRayCollide(Camera camera, Sphere sphere, int pixelY, int pixelX, int width, int height) {
//        // return 0;
//
//        Point cameraOrigin = camera.getPosition();
//
//        // Calculate the ray direction for the given pixel
//        double tanFOV = Math.tan(Math.toRadians(camera.getFOV() / 2.0));
//        double aspectRatio = (double) width / height;
//        double pixelNDCX = (pixelX + 0.5) / width;
//        double pixelNDCY = (pixelY + 0.5) / height;
//        double pixelScreenX = 1 - 2 * pixelNDCX;
//        double pixelScreenY = 2 * pixelNDCY - 1;
//
//        Vector rayVector = new Vector(
//                pixelScreenX * aspectRatio * tanFOV,
//                pixelScreenY * tanFOV,
//                -1
//        ).normalize();
//
//        // Calculate the intersection distance t
//
//        Point P = cameraOrigin;
//        Vector D = rayVector;
//        Point C = sphere.getPosition();
//        double r = sphere.getRadius();
//        Vector O = new Vector(C.getPointX() - P.getPointX(), C.getPointY() - P.getPointY(), C.getPointZ() - P.getPointZ());
//
//        double t1 = -(O.dot(D) - Math.sqrt( ((O.dot(D)*(O.dot(D)) - ((O.dot(O)) - r*r)))));
//        double t2 = -(O.dot(D) + Math.sqrt( ((O.dot(D)*(O.dot(D)) - ((O.dot(O)) - r*r)))));
//
//        if (t1 < t2) {
//            if (t1 > 0) {
//                return t1;
//            }
//            else {
//                return t2;
//            }
//        }
//        else if (t2 < t1) {
//            if (t2 > 0) {
//                return t2;
//            }
//            else {
//                return t1;
//            }
//        }
//        else {
//            return t1;
//        }
//    }

    public double doesRayCollide(Camera camera, Sphere sphere, int pixelY, int pixelX, int width, int height) {
        // return 0;

        Point cameraOrigin = camera.getPosition();

        // Calculate the ray direction for the given pixel
        double tanFOV = Math.tan(Math.toRadians(camera.getFOV() / 2.0));
        double aspectRatio = (double) width / height;
        double pixelNDCX = (pixelX + 0.5) / width;
        double pixelNDCY = (pixelY + 0.5) / height;
        double pixelScreenX = 1 - 2 * pixelNDCX;
        double pixelScreenY = 2 * pixelNDCY - 1;

        Vector rayVector = new Vector(
                pixelScreenX * aspectRatio * tanFOV,
                pixelScreenY * tanFOV,
                -1
        ).normalize();

        // Calculate the intersection distance t

        Point P = cameraOrigin;
        Vector D = rayVector;
        Point C = sphere.getPosition();
        double r = sphere.getRadius();
        Vector O = new Vector(C.getPointX() - P.getPointX(), C.getPointY() - P.getPointY(), C.getPointZ() - P.getPointZ());

        double t1 = -(O.dot(D) - Math.sqrt( ((O.dot(D)*(O.dot(D)) - ((O.dot(O)) - r*r)))));
        double t2 = -(O.dot(D) + Math.sqrt( ((O.dot(D)*(O.dot(D)) - ((O.dot(O)) - r*r)))));

        if (t1 < t2) {
            if (t1 > 0) {
                return t1;
            }
            else {
                return t2;
            }
        }
        else if (t2 < t1) {
            if (t2 > 0) {
                return t2;
            }
            else {
                return t1;
            }
        }
        else {
            return t1;
        }
    }
}
