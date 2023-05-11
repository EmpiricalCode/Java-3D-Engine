package Interface.Panels;

import java.awt.*;

import javax.swing.*;

import Core.Environment;
import Core.Utility.*;

public class RenderPanel extends JPanel {

    private int pixelSize;
    private int dimensions;
    private int quality;

    private int[][][] colorMatrix;

    private Environment environment;

    // Main constructor
    public RenderPanel(Environment environment, int quality) {

        this.environment = environment;

        // Calculating dimensions and pixel size
        this.dimensions = (int) Math.pow(2, quality);
        this.pixelSize = 512 / dimensions;
        this.quality = quality;

        // Setting up color matrix
        this.colorMatrix = new int[dimensions][dimensions][3];

        // Setting size
        this.setPreferredSize(new Dimension(pixelSize * dimensions, pixelSize * dimensions));
    }

    // Renders the environment
    // Fills the colorMatrix variable and calls repaint() to display it 
    // There exists a virtual "screen" corresponding to the environment's camera.
    // This method shoots 4 rays through each pixel of this virtual screen, which gathers color.
    // The color of these 4 rays is averaged and displayed as the color of the pixel.
    // 
    // Virtual screen is 10 units x 10 units, and the origin of the camera (where the rays are shot out from)
    // is 10 units away from the virtual screen.
    public void render() { 

        Vector3D camDirection = this.environment.getCamera().getDirection();
        Vector3D camPosition = this.environment.getCamera().getOrigin();

        // Unit vector pointing left relative to the camera direction
        Vector3D camVectorLeft = new Vector3D(-camDirection.getY(), camDirection.getX(), 0);

        

        // Unit vector pointing up relative to the camera direction
        Vector3D camVectorUp = Vector3D.cross(camDirection, camVectorLeft);

        Vector3D topLeft;
        Vector3D currentVector;

        Ray tempRay;
        ColorRGB rayColor;

        // Clamping calculated vectors to unit vectors
        camVectorLeft.clamp(1);
        camVectorUp.clamp(1);

        topLeft = Vector3D.add(camPosition, camDirection);
        topLeft.add(Vector3D.multiply(camVectorLeft, 5));
        topLeft.add(Vector3D.multiply(camVectorUp, 5));

        for (int i = 0; i < this.dimensions; i++) {
            for (int j = 0; j < this.dimensions; j++) {

                currentVector = Vector3D.add(topLeft, Vector3D.multiply(camVectorLeft, -j * 10.0/this.dimensions));
                currentVector.add(Vector3D.multiply(camVectorUp, -i * 10.0/this.dimensions));

                // Temp until antialiasing works
                currentVector.add(Vector3D.multiply(camVectorLeft, -5.0/this.dimensions));
                currentVector.add(Vector3D.multiply(camVectorUp, -5.0/this.dimensions));

                tempRay = new Ray(camPosition, Vector3D.subtract(currentVector, camPosition), environment);
                rayColor = tempRay.getColor(quality);

                // Ray depth = quality 
                // TODO: change depth = quality
                colorMatrix[j][i][0] = rayColor.getR();
                colorMatrix[j][i][1] = rayColor.getG();
                colorMatrix[j][i][2] = rayColor.getB();
            }
        }

        repaint();
    }

    // Draws each pixel in the pixel matrix onto the panel
    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (int r = 0; r < dimensions; r++) {
            for (int c = 0; c < dimensions; c++) {
                g.setColor(new Color(colorMatrix[r][c][0], colorMatrix[r][c][1], colorMatrix[r][c][2]));
                g.fillRect(r * pixelSize, c * pixelSize, pixelSize, pixelSize);
            }
        }
    }
}