import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller {
    private static View v;
    private static Model m;

    public static void main(String[] args) {
        ImageHolder.makeImages();
        v = new View(new Mouse(), new AL());
        // here is temp
        v.setMap("ozette");
        m = new Model();
        v.draw(m.getX(), m.getY(), m.getScale(), m.getLocation());
    }

    public static class AL implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object src = e.getSource();
            if (src.equals(v.getSettingsButton())) {
                v.openSettings();
                v.drawSettings();
                m.openSettings();
            } else if (src.equals(v.getReturnToMapButton())) {
                v.closeSettings();
                m.closeSettings();
                v.draw(m.getX(), m.getY(), m.getScale(), m.getLocation());
            } else {
                if (m.settingsOpen()) {
                    if (src.equals(v.getOzetteButton())) {
                        m.openNewMap();
                        v.setMap("ozette");
                        m.closeSettings();
                        v.closeSettings();
                        v.draw(m.getX(), m.getY(), m.getScale(), m.getLocation());
                    } else if (src.equals(v.getOlympicButton())) {
                        m.openNewMap();
                        v.setMap("olympic");
                        m.closeSettings();
                        v.closeSettings();
                        v.draw(m.getX(), m.getY(), m.getScale(), m.getLocation());
                    }
                } else {
                    if (src.equals(v.getLocationButton())) {
                        m.findLocation();
                        m.moveToLocation();
                    } else if (src.equals(v.getZoomInButton())) {
                        m.zoomIn();
                    } else if (src.equals(v.getZoomOutButton())) {
                        m.zoomOut();
                    }
                    v.draw(m.getX(), m.getY(), m.getScale(), m.getLocation());
                }
            }
        }

    }

    public static class Mouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            m.moveScreen(e.getX(), e.getY());
            v.draw(m.getX(), m.getY(), m.getScale(), m.getLocation());
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

    }
}
