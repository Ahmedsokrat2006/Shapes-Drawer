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

        Font font = new Font("SansSerif", 1, 20);
        btnPrev.setFont(font);
        btnNext.setFont(font);
        addAreas.setFont(font);

        ActionHandler handler = new ActionHandler();
        btnPrev.addActionListener(handler);
        btnNext.addActionListener(handler);
        addAreas.addActionListener(handler);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnPrev);
        buttonPanel.add(btnNext);
        buttonPanel.add(addAreas);

        JPanel colorButtonPanel = new JPanel();
        colorButtonPanel.setBackground(Color.BLACK);
        colorButtonPanel.setLayout(new GridLayout(9, 1, 0, 10));

        Color[] colors = {Color.WHITE, Color.RED, Color.YELLOW, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.GRAY};
        String[] colorNames = {"White", "Red", "Yellow", "Blue", "Cyan", "Green", "Orange", "Magenta", "Gray"};
        for (int i = 0; i < colors.length; i++) {
            JButton btn = new JButton(colorNames[i]);
            btn.addActionListener(handler);
            setButtonColor(btn, colors[i]);
            colorButtonPanel.add(btn);
        }

        drawPanel.setBackground(Color.BLACK);
        buttonPanel.setBackground(Color.BLACK);


        add(drawPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(colorButtonPanel, BorderLayout.EAST);
        setVisible(true);
    }

    public void setButtonColor(JButton button, Color color) {
        button.setForeground(color);
        button.setBackground(Color.BLACK);
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
            } else if (e.getSource() instanceof JButton) {
                drawPanel.setColor(((JButton) e.getSource()).getForeground());
            }
        }
    }
}