import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private static int width = 800;
    private static int height = 600;
    private JPanel panel;
    private JPanel panelUp;
    private JPanel panelDown;
    private JButton backButton;
    private JButton nextButton;
    private JLabel titleLabel;
    public int curIndex = 0;
    private Timer timer;

    public MainWindow() {
        super("Карусель");

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(MainWindow.width, MainWindow.height);
        this.setLocation(d.width / 2 - MainWindow.width / 2, d.height / 2 - MainWindow.height / 2);
        this.getContentPane().add(panel);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                panelUp.repaint();
                titleLabel.setText(App.titles[curIndex]);
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                back();
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                next();
            }
        });

        timer = new Timer(3000, new UpPanelListener(panelUp));
        timer.start();
    }

    private void createUIComponents() {
        panelUp = new CenterPanel();
        panelUp.setLayout(new BorderLayout());

    }

    public void back() {
        curIndex--;
        if (curIndex < 0) {
            curIndex = App.image.length - 1;
        }
        panelUp.repaint();
        titleLabel.setText(App.titles[curIndex]);
    }

    public void next() {
        curIndex++;
        if (curIndex == App.image.length) {
            curIndex = 0;
        }
        panelUp.repaint();
        titleLabel.setText(App.titles[curIndex]);
    }
}

class UpPanelListener implements ActionListener {

    JPanel panel;

    public UpPanelListener(JPanel panel) {
        this.panel = panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainWindow frame = (MainWindow) SwingUtilities.getWindowAncestor(panel);
        frame.next();
    }
}

class CenterPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        MainWindow frame = (MainWindow) SwingUtilities.getWindowAncestor(this);
        if (App.image[frame.curIndex] != null) {
            g.clearRect(0, 0, getSize().width, getSize().height);
            g.drawImage(App.image[frame.curIndex], (getSize().width - 650) / 2, (getSize().height - 450) / 2, null);
        }
    }
}
