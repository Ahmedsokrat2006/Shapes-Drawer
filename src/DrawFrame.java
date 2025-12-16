import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawFrame extends JFrame {

    private final Main drawPanel;
    private final JButton btnPrev;
    private final JButton btnNext;
    private final JButton addAreas;

    public DrawFrame() {
        setTitle("Shape Drawer");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        drawPanel = new Main();
        btnPrev = new JButton("<< Previous");
        btnNext = new JButton("Next >>");
        addAreas = new JButton("Add Sum Of Areas To File");

        ActionHandler handler = new ActionHandler();
        btnPrev.addActionListener(handler);
        btnNext.addActionListener(handler);
        addAreas.addActionListener(handler);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnPrev);
        buttonPanel.add(btnNext);
        buttonPanel.add(addAreas);

        add(drawPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnNext) {
                drawPanel.nextShape();
            } else if (e.getSource() == btnPrev) {
                drawPanel.prevShape();
            } else if (e.getSource() == addAreas) {
                drawPanel.addAreas();
            }
        }
    }
 }