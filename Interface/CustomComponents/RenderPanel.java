///////////////////////
//
// Filename: RenderPanel.java
// Author: Daniel Long
// Course: ICS4U1
// Description: This class corresponds to the panel where the scene is actually rendered. It handles the actual rendering of the scene.
//
///////////////////////

// TODO: Make closing render window stop render

package Interface.CustomComponents;

import java.awt.*;

import javax.swing.*;

import Core.Environment;
import Core.Utility.*;
import Interface.Windows.MainWindow;

public class RenderPanel extends JPanel implements Runnable {

    private boolean isRendering;
    private int pixelSize;
    private int dimensions;

    private int[][][] colorMatrix;
    private int[][][] renderMatrix;

    private MainWindow mainWindow;
    private Environment environment;

    private boolean antiAliasing;
    private double gammaCorrection;
    private int pixelSamples;
    private int rayDepth;

    // Creates a new render panel
    public RenderPanel(MainWindow mainWindow, Environment environment, int quality, boolean antiAliasing, double gammaCorrection, int pixelSamples, int rayDepth) {

        this.isRendering = false;
        this.mainWindow = mainWindow;
        this.environment = environment;
        this.antiAliasing = antiAliasing;
        this.gammaCorrection = gammaCorrection;
        this.pixelSamples = pixelSamples;
        this.rayDepth = rayDepth;

        // Calculating dimensions and pixel size
        this.dimensions = (int) Math.pow(2, quality);
        this.pixelSize = 512 / dimensions;

        // Setting up color matrix
        this.colorMatrix = new int[dimensions][dimensions][3];
        this.renderMatrix = new int[dimensions*2][dimensions*2][3];

        // Setting size
        this.setPreferredSize(new Dimension(pixelSize * dimensions, pixelSize * dimensions));
    }

    // Returns if the render panel is rendering
    public boolean isRendering() {
        return this.isRendering;
    }

    // Stops the render
    public void stopRendering() {
        this.isRendering = false;

        // This is to let the render settings panel know to hide the render progress label
        this.mainWindow.updateRenderProgress(0);
    }

    // Renders the environment 
    // Fills the colorMatrix variable and calls repaint() to display it 
    // There exists a virtual "screen" corresponding to the environment's camera.
    // This method shoots 4 rays through each pixel of this virtual screen, which gathers color.
    // The color of these 4 rays is averaged and displayed as the color of the pixel.

    // Virtual screen is 10 units x 10 units, and the origin of the camera (where the rays are shot out from)
    // is 10 units away from the virtual screen.
    @Override
    public void run() { 

        int renderDimensions = this.dimensions;

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

        this.isRendering = true;

        // Doubling the render dimensions if anti-aliasing is set to true
        if (this.antiAliasing) {
            renderDimensions *= 2;
        }

        // Clamping calculated vectors to unit vectors
        camVectorLeft.clamp(1);
        camVectorUp.clamp(1);

        //
        topLeft = Vector3D.add(camPosition, camDirection);
        topLeft.add(Vector3D.multiply(camVectorLeft, 5));
        topLeft.add(Vector3D.multiply(camVectorUp, 5));

        // Calculating the color for each pixel
        for (int i = 0; i < renderDimensions; i++) {
            for (int j = 0; j < renderDimensions; j++) {

                // Returning if rendering has stopped
                if (!this.isRendering) {
                    return;
                }

                // Moving the current vector to the right
                // This is the vector where the ray will point to
                currentVector = Vector3D.add(topLeft, Vector3D.multiply(camVectorLeft, -j * Camera.CAMERA_SCREEN_HORIZONTAL_SIZE/(renderDimensions)));
                currentVector.add(Vector3D.multiply(camVectorUp, -i * Camera.CAMERA_SCREEN_VERTICAL_SIZE/(renderDimensions)));

                currentVector.add(Vector3D.multiply(camVectorLeft, -Camera.CAMERA_SCREEN_HORIZONTAL_SIZE/(2 * renderDimensions)));
                currentVector.add(Vector3D.multiply(camVectorUp, -Camera.CAMERA_SCREEN_VERTICAL_SIZE/(2 * renderDimensions)));

                finalColor = new ColorRGB(0, 0, 0);

                // Shooting many rays out of a single pixel and taking the average
                for (int p = 0; p < this.pixelSamples; p++) {
                    sampleRay = new Ray(camPosition, Vector3D.subtract(currentVector, camPosition), environment);
                    rayColor = sampleRay.getColor(this.rayDepth);
                    finalColor = ColorRGB.add(finalColor, rayColor);    
                }

                finalColor = ColorRGB.multiply(finalColor, 1 / (double) this.pixelSamples);
                
                // Store calculated color for the current pixel
                renderMatrix[j][i][0] = finalColor.getR();
                renderMatrix[j][i][1] = finalColor.getG();
                renderMatrix[j][i][2] = finalColor.getB();
            }

            mainWindow.updateRenderProgress(Math.round(10000 * (i + 1) /  ((double) renderDimensions)) / 100.0);
        }

        // Anti-aliasing
        // Both the x and y axis is doubled, meaning that there are actually 4 pixels rendered for every actual pixel on the display screen
        // an average of these 4 pixels is taken in order to smooth out the render
        // An equivalent would be to keep the x and y dimensions constant, and instead shoot 4 rays out of each pixel and taking a similar average.
        // Anti-aliasing is different from the 100 ray samples already taken for each pixel, because the 100 sample rays are shot in the exact same direction
        for (int i = 0; i < renderDimensions; i++) {
            for (int j = 0; j < renderDimensions; j++) {

                // Returning if rendering has stopped
                if (!this.isRendering) {
                    return;
                }

                if (this.antiAliasing) {
                    if (j % 2 == 1 && i % 2 == 1) {
                        colorMatrix[j/2][i/2][0] = (renderMatrix[j][i][0] + renderMatrix[j][i-1][0] + renderMatrix[j-1][i][0] + renderMatrix[j-1][i-1][0]) / 4;
                        colorMatrix[j/2][i/2][1] = (renderMatrix[j][i][1] + renderMatrix[j][i-1][1] + renderMatrix[j-1][i][1] + renderMatrix[j-1][i-1][1]) / 4;
                        colorMatrix[j/2][i/2][2] = (renderMatrix[j][i][2] + renderMatrix[j][i-1][2] + renderMatrix[j-1][i][2] + renderMatrix[j-1][i-1][2]) / 4;
                    }
                } else {
                    colorMatrix[j][i][0] = renderMatrix[j][i][0];
                    colorMatrix[j][i][1] = renderMatrix[j][i][1];
                    colorMatrix[j][i][2] = renderMatrix[j][i][2];
                }
            }
        }

        // Gamma correcting the colors to achieve a more comfortable brightness
        // Essentially converting colors to between 0-1 and takign their square root to brighten them
        for (int i = 0; i < this.dimensions; i++) {
            for (int j = 0; j < this.dimensions; j++) {

                // Returning if rendering has stopped
                if (!this.isRendering) {
                    return;
                }

                colorMatrix[j][i][0] = (int) (Math.pow((colorMatrix[j][i][0] / 255.0), 1/gammaCorrection) * 255);
                colorMatrix[j][i][1] = (int) (Math.pow((colorMatrix[j][i][1] / 255.0), 1/gammaCorrection) * 255);
                colorMatrix[j][i][2] = (int) (Math.pow((colorMatrix[j][i][2] / 255.0), 1/gammaCorrection) * 255);
            }
        }
            
        this.isRendering = false;
        repaint();
    }

    // Draws each pixel in the pixel matrix onto the panel
    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        for (int r = 0; r < dimensions; r++) {
            for (int c = 0; c < dimensions; c++) {
                g.setColor(new Color(colorMatrix[r][c][0], colorMatrix[r][c][1], colorMatrix[r][c][2]));
                g.fillRect(r * pixelSize, c * pixelSize, pixelSize, pixelSize);
            }
        }
    }
}