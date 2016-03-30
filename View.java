import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View extends JFrame {
    private JPanel panel, settingsPanel;
    private Graphics pen, settingsPen;
    private static BufferedImage img;
    private JButton getLocation, zoomIn, zoomOut;
    // These are possibly temporary
    private JButton settings, returnToMap, ozetteButton, olympicButton;

    public View(MouseListener mouse, ActionListener al) {
        super("Maps V" + Constants.VERSION);
        // set up panels
        panel = new JPanel();
        panel.setLayout(null);
        panel.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        settingsPanel = new JPanel();
        settingsPanel.setLayout(null);
        settingsPanel.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));
        // set up buttons
        olympicButton = new JButton("olympic");
        olympicButton.addActionListener(al);
        olympicButton.setBounds(2, 42, 100, 40);
        ozetteButton = new JButton("ozette");
        ozetteButton.addActionListener(al);
        ozetteButton.setBounds(2, 2, 100, 40);
        settings = new JButton("set");
        settings.addActionListener(al);
        settings.setBounds(Constants.WIDTH - 40, 2, 40, 20);
        returnToMap = new JButton("set");
        returnToMap.addActionListener(al);
        returnToMap.setBounds(Constants.WIDTH - 40, 2, 40, 20);
        getLocation = new JButton("loc");
        getLocation.addActionListener(al);
        getLocation.setBounds(2, 2, 55, 20);
        zoomIn = new JButton("+");
        zoomIn.addActionListener(al);
        zoomIn.setBounds(59, 2, 45, 20);
        zoomOut = new JButton("-");
        zoomOut.addActionListener(al);
        zoomOut.setBounds(106, 2, 40, 20);
        // add buttons to panels
        panel.add(getLocation);
        panel.add(zoomIn);
        panel.add(zoomOut);
        panel.add(settings);
        settingsPanel.add(returnToMap);
        settingsPanel.add(ozetteButton);
        settingsPanel.add(olympicButton);
        // deal with frame
        setLocation(0, -35);
        add(settingsPanel);
        settingsPen = settingsPanel.getGraphics();
        add(panel);
        setVisible(true);
        // settingsPanel.setVisible(true);
        // panel.setVisible(true);
        pack();
        panel.addMouseListener(mouse);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pen = panel.getGraphics();
        openSettings();
        closeSettings();
    }

    public JButton getReturnToMapButton() {
        return returnToMap;
    }

    public void setMap(String s) {
        img = ImageHolder.getNewMap(s);
    }

    public void closeSettings() {
        remove(settingsPanel);
        add(panel);
        pack();
    }

    public void openSettings() {
        remove(panel);
        add(settingsPanel);
        pack();
        settingsPen = settingsPanel.getGraphics();
    }

    public void drawSettings() {
        settingsPen.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);
        returnToMap.update(returnToMap.getGraphics());
        ozetteButton.update(ozetteButton.getGraphics());
        olympicButton.update(olympicButton.getGraphics());
    }

    public JButton getSettingsButton() {
        return settings;
    }

    public void draw(int x, int y, double scale, double[] loc) {
        BufferedImage subImg = null;

        subImg = img.getSubimage(x, y, (int) (Constants.WIDTH / scale), (int) (Constants.HEIGHT / scale));
        Graphics ipen = subImg.getGraphics();
        Rectangle view = new Rectangle(x, y, (int) (Constants.WIDTH), (int) (Constants.HEIGHT));
        Rectangle l = new Rectangle(Constants.findLongX(loc[1]), Constants.findLatY(loc[0]), 10, 10);
        if (view.contains(l)) {
            ipen.setColor(Color.RED);
            ipen.fillRect((int) l.getX() - x, (int) l.getY() - y, 10, 10);
        }
        pen.drawImage(subImg, 0, 0, Constants.WIDTH, Constants.HEIGHT, null);
        getLocation.update(getLocation.getGraphics());
        zoomIn.update(zoomIn.getGraphics());
        zoomOut.update(zoomOut.getGraphics());
        settings.update(settings.getGraphics());
    }

    // Maybe should go in model??
    public JButton getLocationButton() {
        // TODO Auto-generated method stub
        return getLocation;
    }

    public JButton getOlympicButton() {
        return olympicButton;
    }

    public JButton getZoomInButton() {
        return zoomIn;
    }

    public JButton getZoomOutButton() {
        return zoomOut;
    }

    public JButton getOzetteButton() {
        return ozetteButton;
    }
}
