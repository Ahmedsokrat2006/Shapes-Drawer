import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DrawFrame extends JFrame {

    private final Main drawPanel;
    private final JButton btnPrev;
    private final JButton btnNext;
    private final JButton addData;
    private final JButton btnGoTo;

    public DrawFrame() {
        setTitle("Shape Drawer");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        drawPanel = new Main();
        btnGoTo = new JButton(new ImageIcon(getClass().getResource("/search.png")));
        btnPrev = new JButton("<< Previous");
        btnNext = new JButton("Next >>");
        addData = new JButton("Add Data To File");

        Font font = new Font("SansSerif", Font.BOLD, 20);
        btnPrev.setFont(font);
        btnNext.setFont(font);
        addData.setFont(font);

        ActionHandler handler = new ActionHandler();
        btnPrev.addActionListener(handler);
        btnNext.addActionListener(handler);
        addData.addActionListener(handler);
        btnGoTo.addActionListener(handler);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnGoTo);
        buttonPanel.add(btnPrev);
        buttonPanel.add(btnNext);
        buttonPanel.add(addData);

        JPanel colorButtonPanel = new JPanel();
        colorButtonPanel.setBackground(Color.BLACK);
        colorButtonPanel.setLayout(new GridLayout(10, 1, 0, 10));

        Color[] colors = {Color.WHITE, Color.RED, Color.YELLOW, Color.BLUE, Color.CYAN, Color.GREEN, Color.ORANGE, Color.MAGENTA, Color.GRAY, Color.WHITE};
        String[] colorNames = {"White", "Red", "Yellow", "Blue", "Cyan", "Green", "Orange", "Magenta", "Gray", "Custom"};
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

        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
        setVisible(true);
    }

    private void setButtonColor(JButton button, Color color) {
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
            } else if (e.getSource() == addData) {
                drawPanel.addData();
            } else if (e.getSource() == btnGoTo) {
                Main.playSound("/Punch.wav");
                new GoToFrame(drawPanel);
            } else if (e.getSource() instanceof JButton  && ((JButton) e.getSource()).getText().equals("Custom")) {
                Color newColor = JColorChooser.showDialog(DrawFrame.this, "Choose a Color", drawPanel.getColor());
                if (newColor != null) {
                    drawPanel.setColor(newColor, "Custom");
                }
            } else if (e.getSource() instanceof JButton) {
                drawPanel.setColor(((JButton) e.getSource()).getForeground(), ((JButton) e.getSource()).getText());
            }
        }
    }
}