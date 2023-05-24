package Interface.Panels;

import java.awt.*;

import javax.swing.*;

import Core.Environment;
import Core.Utility.*;

public class RenderPanel extends JPanel {

    private int pixelSize;
    private int dimensions;

    private int[][][] colorMatrix;
    private int[][][] renderMatrix;

    private Environment environment;

    // Main constructor
    public RenderPanel(Environment environment, int quality) {

        this.environment = environment;

        // Calculating dimensions and pixel size
        this.dimensions = (int) Math.pow(2, quality);
        this.pixelSize = 512 / dimensions;

        // Setting up color matrix
        this.colorMatrix = new int[dimensions][dimensions][3];
        this.renderMatrix = new int[dimensions*2][dimensions*2][3];

        // Setting size
        this.setPreferredSize(new Dimension(pixelSize * dimensions, pixelSize * dimensions));
    }

    // Renders the environment 
    // Fills the colorMatrix variable and calls repaint() to display it 
    // There exists a virtual "screen" corresponding to the environment's camera.
    // This method shoots 4 rays through each pixel of this virtual screen, which gathers color.
    // The color of these 4 rays is averaged and displayed as the color of the pixel.

    // Virtual screen is 10 units x 10 units, and the origin of the camera (where the rays are shot out from)
    // is 10 units away from the virtual screen.
    public void render() { 

        Vector3D camDirection = this.environment.getCamera().getDirection();
        Vector3D camPosition = this.environment.getCamera().getOrigin();

        // Unit vector pointing left relative to the camera direction
        Vector3D camVectorLeft = new Vector3D(-camDirection.getY(), camDirection.getX(), 0);

        // Unit vector pointing up relative to the camera direction
        Vector3D camVectorUp = Vector3D.cross(camDirection, camVectorLeft);
        Vector3D currentVector;
        Vector3D topLeft;

        Ray sampleRay;
        ColorRGB rayColor;
        ColorRGB finalColor;

        // Clamping calculated vectors to unit vectors
        camVectorLeft.clamp(1);
        camVectorUp.clamp(1);

        topLeft = Vector3D.add(camPosition, camDirection);
        topLeft.add(Vector3D.multiply(camVectorLeft, 5));
        topLeft.add(Vector3D.multiply(camVectorUp, 5));

        for (int i = 0; i < this.dimensions*2; i++) {
            for (int j = 0; j < this.dimensions*2; j++) {

                currentVector = Vector3D.add(topLeft, Vector3D.multiply(camVectorLeft, -j * 10.0/(this.dimensions*2)));
                currentVector.add(Vector3D.multiply(camVectorUp, -i * 10.0/(this.dimensions*2)));

                // Temp until antialiasing works
                currentVector.add(Vector3D.multiply(camVectorLeft, -5.0/(this.dimensions*2)));
                currentVector.add(Vector3D.multiply(camVectorUp, -5.0/(this.dimensions*2)));

                finalColor = new ColorRGB(0, 0, 0);

                for (int p = 0; p < 100; p++) {
                    sampleRay = new Ray(camPosition, Vector3D.subtract(currentVector, camPosition), environment);
                    rayColor = sampleRay.getColor(20);
                    finalColor = ColorRGB.add(finalColor, rayColor);    
                }

                finalColor = ColorRGB.multiply(finalColor, 0.01);
                
                renderMatrix[j][i][0] = finalColor.getR();
                renderMatrix[j][i][1] = finalColor.getG();
                renderMatrix[j][i][2] = finalColor.getB();
            }

            System.out.println((i + 1) /  ((double) this.dimensions * 2));
        }

        // Anti-aliasing and gamma correction
        for (int i = 0; i < this.dimensions*2; i++) {
            for (int j = 0; j < this.dimensions*2; j++) {
                if (j % 2 == 1 && i % 2 == 1) {
                    colorMatrix[j/2][i/2][0] = (renderMatrix[j][i][0] + renderMatrix[j][i-1][0] + renderMatrix[j-1][i][0] + renderMatrix[j-1][i-1][0]) / 4;
                    colorMatrix[j/2][i/2][1] = (renderMatrix[j][i][1] + renderMatrix[j][i-1][1] + renderMatrix[j-1][i][1] + renderMatrix[j-1][i-1][1]) / 4;
                    colorMatrix[j/2][i/2][2] = (renderMatrix[j][i][2] + renderMatrix[j][i-1][2] + renderMatrix[j-1][i][2] + renderMatrix[j-1][i-1][2]) / 4;

                    // Gamma correcting the colors to achieve a more comfortable brightness
                    colorMatrix[j/2][i/2][0] = (int) (Math.pow((colorMatrix[j/2][i/2][0] / 255.0), 0.5) * 255);
                    colorMatrix[j/2][i/2][1] = (int) (Math.pow((colorMatrix[j/2][i/2][1] / 255.0), 0.5) * 255);
                    colorMatrix[j/2][i/2][2] = (int) (Math.pow((colorMatrix[j/2][i/2][2] / 255.0), 0.5) * 255);
                }
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