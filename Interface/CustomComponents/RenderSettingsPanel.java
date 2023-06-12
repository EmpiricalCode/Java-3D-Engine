///////////////////////
//
// Filename: RenderSettingsPanel.java
// Author: Daniel Long
// Course: ICS4U1
// Description: A class that handles the creation and user interactions of the render settings panel. This panel allows users to modify camera/lighting/etc settings.
//
///////////////////////

package Interface.CustomComponents;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.*;

import Core.Utility.Vector3D;
import Core.Utility.Enum.PropertyType;
import Interface.Structures.PropertyPanel;
import Interface.Utility.FontLoader;
import Interface.Utility.PropertyElementLoader;
import Interface.Utility.PropertyFormatter;
import Interface.Utility.EntityPropertySetEvents.PropertyTextFieldEventHandler;
import Interface.Windows.MainWindow;

public class RenderSettingsPanel extends PropertyPanel {

    public static final Font BUTTON_FONT = FontLoader.loadFont("montserrat_medium", 17);

    // Render settings and their default values
    private int quality = 8;
    private int pixelSamples = 100;
    private int rayDepth = 10;
    private double gammaCorrection = 2;
    private boolean antiAliasing = true;
    private Vector3D cameraPosition = new Vector3D(0, 0, 0);
    private Vector3D cameraLookAt = new Vector3D(0, 5, 0);;

    private MainWindow mainWindow;

    private JPanel buttonArea;
    private RoundedButton previewButton;
    private RoundedButton renderButton;
    private RoundedButton cancelRenderButton;
    private JLabel progressLabel;
    
    public RenderSettingsPanel(MainWindow mainWindow, int width, int height) {
        super("Render Settings", width, 800, 90);

        this.mainWindow = mainWindow;
        this.getPropertiesArea().setPreferredSize(new Dimension(width, 340));

        // Creating the button panel
        this.buttonArea = new JPanel();
        this.buttonArea.setPreferredSize(new Dimension(width, 460));
        this.buttonArea.setBackground(MainWindow.BACKGROUND_COLOR);
        this.buttonArea.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));

        this.previewButton = new RoundedButton(15, "Preview", new Color(170, 160, 50), new Color(200, 190, 90), true);
        this.previewButton.setBorder(new EmptyBorder(8, 24, 8, 24));
        this.previewButton.setFont(RenderSettingsPanel.BUTTON_FONT);

        // Starting a preview when the preview button is pressed unless there is an ongoing render
        this.previewButton.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseReleased(MouseEvent event) {

                if (previewButton.mouseIn() && !mainWindow.isRendering()) {
                    mainWindow.startPreview();
                }
            }
        });

        this.renderButton = new RoundedButton(15, "Render", new Color(100, 150, 100), new Color(130, 180, 130), false);
        this.renderButton.setForeground(Color.WHITE);
        this.renderButton.setBorder(new EmptyBorder(8, 28, 8, 28));
        this.renderButton.setFont(RenderSettingsPanel.BUTTON_FONT);

        // Starting a render when the render button is pressed unless there is an ongoing render
        this.renderButton.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseReleased(MouseEvent event) {

                if (renderButton.mouseIn() && !mainWindow.isRendering()) {
                    mainWindow.startRender();
                }
            }
        });

        this.cancelRenderButton = new RoundedButton(15, "Cancel Render", new Color(200, 100, 100), new Color(230, 130, 130), true);
        this.cancelRenderButton.setBorder(new EmptyBorder(8, 62, 8, 62));
        this.cancelRenderButton.setFont(RenderSettingsPanel.BUTTON_FONT);

        // Cancelling a render when the cancel render button is pressed
        this.cancelRenderButton.addMouseListener(new MouseAdapter() {
            
            @Override
            public void mouseReleased(MouseEvent event) {
                
                if (cancelRenderButton.mouseIn()) {
                    mainWindow.cancelRender();
                }
            }
        });

        this.progressLabel = new JLabel("");
        this.progressLabel.setFont(FontLoader.loadFont("montserrat_medium", 15));
        this.progressLabel.setForeground(Color.GRAY);
        this.progressLabel.setVisible(false);

        this.add(buttonArea);
        this.buttonArea.add(this.previewButton);
        this.buttonArea.add(this.renderButton);
        this.buttonArea.add(this.cancelRenderButton);
        this.buttonArea.add(this.progressLabel);
    }

    // Updates the render progress label
    public void updateRenderProgress(double percent) {

        if (mainWindow.isRendering()) {
            
            // Making the progress label visible
            if (!this.progressLabel.isVisible()) {
                this.progressLabel.setVisible(true);
            }

            // Updating progerss label text
            this.progressLabel.setText("Render Progress: " + percent + " %");

        } else {
            this.progressLabel.setVisible(false);
        }
    }

    // Loads the render settings properties
    public void loadProperties() {

        // Creating properties
        JTextField qualityComponent = (JTextField) PropertyElementLoader.loadListElement(this.getPropertiesArea(), PropertyType.QUALITY);
        JTextField pixelSamplesComponent = (JTextField) PropertyElementLoader.loadListElement(this.getPropertiesArea(), PropertyType.PIXEL_SAMPLES);
        JTextField rayDepthComponent = (JTextField) PropertyElementLoader.loadListElement(this.getPropertiesArea(), PropertyType.RAY_DEPTH);
        JTextField gammaComponent = (JTextField) PropertyElementLoader.loadListElement(this.getPropertiesArea(), PropertyType.GAMMA);
        JTextField cameraPositionComponent = (JTextField) PropertyElementLoader.loadListElement(this.getPropertiesArea(), PropertyType.CAMERA_POSITION);
        JTextField cameraLookAtComponent = (JTextField) PropertyElementLoader.loadListElement(this.getPropertiesArea(), PropertyType.CAMERA_LOOK_AT);
        JComboBox<?> antiAliasingComponent = (JComboBox<?>) PropertyElementLoader.loadListElement(this.getPropertiesArea(), PropertyType.ANTI_ALIASING);

        // Setting initial values
        qualityComponent.setText(String.valueOf(this.quality - 6));
        pixelSamplesComponent.setText(String.valueOf(this.pixelSamples));
        rayDepthComponent.setText(String.valueOf(this.rayDepth));
        gammaComponent.setText(String.valueOf(this.gammaCorrection));
        cameraPositionComponent.setText(this.cameraPosition.toString());
        cameraLookAtComponent.setText(this.cameraLookAt.toString());
        
        if (this.antiAliasing) {
            antiAliasingComponent.setSelectedIndex(0);
        } else {
            antiAliasingComponent.setSelectedIndex(1);
        }

        // Every focus listener is implemented seperately. Even if they were all grouped under the same focus listener to reduce repeated code, it would
        // then be inefficient to then figure out which kind of property is being changed (without a custom class handling focusListener, where property type could be passed in)

        // A custom focus listener class was used for entity text field properties (PropertyTextFieldEventHandler) because different entities have varying properties.
        // However, for render settings, all the properties are immediately known, and so it is simpler to manually set up listeners for them all

        // Listening for property changes and modifying properties accordingly
        // Listening for quality property change
        qualityComponent.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent event) {
                
                String modifiedFieldText = PropertyFormatter.formatQuality(qualityComponent.getText());
                PropertyTextFieldEventHandler.setProperty(String.valueOf(quality - 6), modifiedFieldText, qualityComponent, mainWindow.isRendering());

                if (modifiedFieldText != null && !mainWindow.isRendering()) {
                    setQuality(Integer.valueOf(modifiedFieldText) + 6);
                }
            }
        });

        // Listening for pixel samples property change
        pixelSamplesComponent.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent event) {
                
                String modifiedFieldText = PropertyFormatter.formatPixelSamples(pixelSamplesComponent.getText());
                PropertyTextFieldEventHandler.setProperty(String.valueOf(pixelSamples), modifiedFieldText, pixelSamplesComponent, mainWindow.isRendering());

                if (modifiedFieldText != null && !mainWindow.isRendering()) {
                    setPixelSamples(Integer.valueOf(modifiedFieldText));
                }
            }
        });

        // Listening for ray depth property change
        rayDepthComponent.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent event) {
                
                // Ray depth uses the same property formatting as pixel samples
                String modifiedFieldText = PropertyFormatter.formatPixelSamples(rayDepthComponent.getText());
                PropertyTextFieldEventHandler.setProperty(String.valueOf(rayDepth), modifiedFieldText, rayDepthComponent, mainWindow.isRendering());

                if (modifiedFieldText != null && !mainWindow.isRendering()) {
                    setRayDepth(Integer.valueOf(modifiedFieldText));
                }
            }
        });

        // Listening for gamma correction property change
        gammaComponent.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent event) {
                
                String modifiedFieldText = PropertyFormatter.formatGamma(gammaComponent.getText());
                PropertyTextFieldEventHandler.setProperty(String.valueOf(gammaCorrection), modifiedFieldText, gammaComponent, mainWindow.isRendering());

                if (modifiedFieldText != null && !mainWindow.isRendering()) {
                    setGamma(Double.valueOf(modifiedFieldText));
                }
            }
        });

        // Listening for camera position property change
        cameraPositionComponent.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent event) {
                
                String modifiedFieldText = PropertyFormatter.formatPosition(cameraPositionComponent.getText());
                String[] triple;
                Vector3D newPos;

                if (modifiedFieldText != null) {

                    triple = modifiedFieldText.split(", ");
                    newPos = new Vector3D(Double.valueOf(triple[0]), Double.valueOf(triple[1]), Double.valueOf(triple[2]));

                    // Reset camera position if it equals to the camera look at position, otherwise set the new camera position
                    if (newPos.toString().equals(cameraLookAt.toString())) {
                        modifiedFieldText = null;
                    } else if (!mainWindow.isRendering()) {
                        setCameraPosition(newPos);
                    }
                }

                PropertyTextFieldEventHandler.setProperty(cameraPosition.toString(), modifiedFieldText, cameraPositionComponent, mainWindow.isRendering());
            }
        });

        // Listening for camera look at position property change
        cameraLookAtComponent.addFocusListener(new FocusAdapter() {

            @Override
            public void focusLost(FocusEvent event) {
                
                String modifiedFieldText = PropertyFormatter.formatPosition(cameraLookAtComponent.getText());
                String[] triple;
                Vector3D newPos;

                if (modifiedFieldText != null) {

                    triple = modifiedFieldText.split(", ");
                    newPos = new Vector3D(Double.valueOf(triple[0]), Double.valueOf(triple[1]), Double.valueOf(triple[2]));

                    // Reset camera look at position if it equals to the camera position, otherwise set the new camera look at position
                    if (newPos.toString().equals(cameraPosition.toString())) {
                        modifiedFieldText = null;
                    } else if (!mainWindow.isRendering()) {
                        setCameraLookAt(newPos);
                    }
                }

                PropertyTextFieldEventHandler.setProperty(cameraLookAt.toString(), modifiedFieldText, cameraLookAtComponent, mainWindow.isRendering());
            }
        });

        // Listening for anti-aliasing property change
        antiAliasingComponent.addItemListener(new ItemListener() {
            
            @Override
            public void itemStateChanged(ItemEvent event) {

                if (event.getStateChange() == ItemEvent.SELECTED) {

                    // Preventing the combobox and property from changing if a render is underway
                    if (mainWindow.isRendering()) {
                        if (antiAliasing) {
                            antiAliasingComponent.setSelectedIndex(0);
                        } else {
                            antiAliasingComponent.setSelectedIndex(1);
                        }
                        
                        return;
                    }

                    // Setting antialiasing
                    if (antiAliasingComponent.getSelectedIndex() == 0) {
                        setAntiAliasing(true);
                    } else {
                        setAntiAliasing(false);
                    }
                }
            }
        });

        this.revalidate();
        this.repaint();
    }

    // Gets the quality
    public int getQuality() {
        return this.quality;
    }

    // Gets the pixel samples
    public int getPixelSamples() {
        return this.pixelSamples;
    }

    // Gets the ray depth
    public int getRayDepth() {
        return this.rayDepth;
    }

    // Gets the gamma correction
    public double getGamma() {
        return this.gammaCorrection;
    }

    // Gets the anti aliasing boolean
    public boolean getAntiAliasing() {
        return this.antiAliasing;
    }

    // Gets the camera position
    public Vector3D getCameraPosition() {
        return this.cameraPosition;
    }

    // Gets the camera look at vector
    public Vector3D getCameraLookAt() {
        return this.cameraLookAt;
    }

    // Sets the quality
    public void setQuality(int quality) {
        this.quality = quality;
    }

    // Sets the pixel samples
    public void setPixelSamples(int samples) {
        this.pixelSamples = samples;
    }

    // Sets the ray depth
    public void setRayDepth(int depth) {
        this.rayDepth = depth;
    }

    // Sets the gamma correction
    public void setGamma(double gamma) {
        this.gammaCorrection = gamma;
    }

    // Sets the anti aliasing boolean
    public void setAntiAliasing(boolean antiAliasing) {
        this.antiAliasing = antiAliasing;
    }

    // Sets the camera position
    public void setCameraPosition(Vector3D position) {
        this.cameraPosition = position;
    }

    // Sets the camera look at vector
    public void setCameraLookAt(Vector3D position) {
        this.cameraLookAt = position;
    }
}