import java.util.List;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Math.tan;

public class Main {

    // List of pixel values
    // List<int[]> rgbValues = new ArrayList<>();

    public static void main(String[] args) {
        int width = 1920;
        int height = 1080;

        // Declare Lists
        List<Plane> planesList = new ArrayList<>();
        List<Sphere> spheresList = new ArrayList<>();
        List<int[]> rgbValues = new ArrayList<>();

        // Declare Objects
        Camera camera = new Camera();
        camera.setFOV(35);
        camera.setPosition(new Point(0, 10, 0));
        camera.setDirection(new Vector(0, 0, 1));

        Plane planeOne = new Plane();
        planesList.add(planeOne);
        planeOne.setColour(175, 50, 50);
        planeOne.setPosition(new Point(0, 0, 0));
        planeOne.setNormal(new Vector(0, 1, 0));
        // TODO Sphere starts to disappear as it gets further away. Fix that.

//        Plane planeTwo = new Plane();
//        planesList.add(planeTwo);
//        planeTwo.setColour(0, 175, 20);
//        planeTwo.setNormal(new Vector(0, 1, 0.2));
//        planeTwo.setPosition(new Point(0, 0, 0));

//        Plane planeThree = new Plane();
//        planesList.add(planeThree);
//        planeThree.setColour(0, 0, 175);
//        planeThree.setPosition(new Point(10, 0, 0));
//        planeThree.setNormal(new Vector(-2, 3, 0));


        Sphere sphereOne = new Sphere();
        spheresList.add(sphereOne);
        sphereOne.setPosition(new Point(0, 10, 40));
        sphereOne.setColour(255, 0, 255);

//        for (int i = 0; i < height; i++) {
//            for (int j = 0; j < width; j++) {
////                if (doesCollidePlane(camera, plane, i, j, width, height, FOV) == 1) {
////                    appendRgbValue(rgbValues, plane.getColourR(), plane.getColourG(), plane.getColourB());
////                }
//                double planeCollision = doesCollidePlane(camera, planeOne, i, j, width, height, FOV);
//                double sphereCollision = doesCollideSphere(camera, sphereOne, i, j, width, height, FOV);
//                if (planeCollision > 0 || sphereCollision > 0) {
//                    if (planeCollision > sphereCollision) {
//                        appendRgbValue(rgbValues, planeOne.getColour().getColourR(), planeOne.getColour().getColourG(), planeOne.getColour().getColourB());
//                    }
//                    else {
//                        appendRgbValue(rgbValues, sphereOne.getColour().getColourR(), sphereOne.getColour().getColourG(), sphereOne.getColour().getColourB());
//                    }
//                }
//                else {
//                    appendRgbValue(rgbValues, 0, 0, 0);
//                }
//            }
//        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Plane closestPlane = new Plane();
                double closestPlaneDistance = Double.MAX_VALUE;
                for (int q = 0; q < planesList.size(); q++) {
                    double currentDouble = planesList.get(q).doesRayCollide(camera, planesList.get(q), i, j, width, height);
                    if (currentDouble < closestPlaneDistance && currentDouble > 0) {
                        closestPlaneDistance = currentDouble;
                        closestPlane = planesList.get(q);
                    }
                }
                Sphere closestSphere = new Sphere();
                double closestSphereDistance = Double.MAX_VALUE;
                for (int p = 0; p < spheresList.size(); p++) {
                    double currentDouble = spheresList.get(p).doesRayCollide(camera, spheresList.get(p), i, j, width, height);
                    if (currentDouble < closestSphereDistance && currentDouble > 0) {
                        closestSphereDistance = currentDouble;
                        closestSphere = spheresList.get(p);
                    }
                }
                if (closestPlaneDistance < closestSphereDistance) {
                    appendRgbValue(rgbValues, closestPlane.getColour().getColourR(), closestPlane.getColour().getColourG(), closestPlane.getColour().getColourB());
                }
                else if (closestSphereDistance < closestPlaneDistance) {
                    appendRgbValue(rgbValues, closestSphere.getColour().getColourR(), closestSphere.getColour().getColourG(), closestSphere.getColour().getColourB());
                }
                else {
                    appendRgbValue(rgbValues, 0, 0, 0);
                }
            }
        }

        BufferedImage image = createImageFromRGBValues(rgbValues, width, height);

        File output = new File("output.png");
        try {
            ImageIO.write(image, "png", output);
            System.out.println("Image saved successfully");
        } catch (IOException e) {
            System.out.println("Error saving images: " + e.getMessage());
        }

    }

    private static void appendRgbValue(List<int[]> rgbValues, int red, int green, int blue) {
        rgbValues.add(new int[]{red, green, blue});
    }

    private static double doesCollidePlane(Camera camera, Plane plane, int pixelY, int pixelX, int width, int height) {
        // Point cameraOrigin = new Point(camera.getPosition().getPointX(), camera.getPosition().getPointY(), camera.getPosition().getPointZ());
        Point cameraOrigin = camera.getPosition();

//        Vector rayVector = new Vector(((tan(FOV)/(width/2))*((width/2)-pixelX)*(-1)), (tan(FOV)/(width/2))*((height/2)-pixelY), 1);
        // The X vector for the specific pixel will be ((tanFOV/(width/2))*((width/2)-pixelX)*-1)
        // The Y vector for the specific pixel will be (tanFOV/(width/2))*((height/2)-pixelY)

        // Dot Product Stuff
        // Ray: r(hat) = p + dt   -   r(hat) is a point on the ray, p is the start point, d is the direction vector, t is the distance
        // Plane: ( r(hat) - p ) dot n = 0   -   r(hat) is the intersecting point, p is a random point on the plane, n is the normal vector
        // t = (1/(d dot n)) * ( p(plane) dot n - p(ray) dot n )
//        double t = (1/((rayVector.getVectorX() * plane.getNormal().getVectorX()) + (rayVector.getVectorY() * plane.getNormal().getVectorY()) + (rayVector.getVectorZ() * plane.getNormal().getVectorZ()))) * ((plane.getPosition().getPointX() * plane.getNormal().getVectorX()) + (plane.getPosition().getPointY() * plane.getNormal().getVectorY()) + (plane.getPosition().getPointZ() * plane.getNormal().getVectorZ()) - (camera.getPosition().getPointX() * plane.getNormal().getVectorX()) + (camera.getPosition().getPointY() * plane.getNormal().getVectorY()) + (camera.getPosition().getPointZ() * plane.getNormal().getVectorZ()));

        // System.out.printf("t: %f\n", t);

//        if (t > 0) {
//            return 1;
//        }
//        else {
//            return 0;
//        }

        // Calculate the ray direction for the given pixel
        double tanFOV = Math.tan(Math.toRadians(camera.getFOV() / 2.0));
        double aspectRatio = (double) width / height;
        double pixelNDCX = (pixelX + 0.5) / width;
        double pixelNDCY = (pixelY + 0.5) / height;
        double pixelScreenX = 2 * pixelNDCX - 1;
        double pixelScreenY = 1 - 2 * pixelNDCY;

        Vector rayVector = new Vector(
                pixelScreenX * aspectRatio * tanFOV,
                pixelScreenY * tanFOV,
                -1
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

    public static double doesCollideSphere(Camera camera, Sphere sphere, int pixelY, int pixelX, int width, int height, int FOV) {
        return 10;
    }

    private static BufferedImage createImageFromRGBValues(List<int[]> rgbValues, int width, int height) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        int index = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (index < rgbValues.size()) {
                    int[] rgb = rgbValues.get(index);
                    int red = clamp(rgb[0], 0, 255);
                    int green = clamp(rgb[1], 0, 255);
                    int blue = clamp(rgb[2], 0, 255);
                    int rgbValue = (red << 16) | (green << 8) | blue;
                    image.setRGB(x, y, rgbValue);
                    index++;
                }
            }
        }

        return image;
    }

    private static int clamp(int value, int min, int max) {
        return Math.max(min, Math.min(max, value));
    }


}
