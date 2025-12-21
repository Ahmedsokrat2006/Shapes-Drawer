import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GoToFrame extends JFrame {
    private final Main drawPanel;
    private final JTextField shapeNo;
    private final JButton btnGo;
    private final JLabel label;

    public GoToFrame(Main drawPanel){
        this.drawPanel = drawPanel;

        setTitle("Go To");
        setSize(300,150);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new FlowLayout());

        shapeNo = new JTextField(5);
        btnGo = new JButton("Go");
        label = new JLabel("Shape Number:");

        Font font = new Font("SansSerif", Font.BOLD, 20);
        btnGo.setFont(font);
        shapeNo.setFont(font);
        label.setFont(font);

        getContentPane().setBackground(Color.BLACK);
        label.setForeground(Color.WHITE);

        btnGo.addActionListener(new ActionHandler());

        add(label);
        add(shapeNo);
        add(btnGo);

        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(getClass().getResource("/icon.png")).getImage());
        setVisible(true);
    }

    private class ActionHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == btnGo) {
                try {
                    int n = Integer.parseInt(shapeNo.getText());
                    if(drawPanel.goTo(n)){
                        dispose();
                    }
                } catch (NumberFormatException E) {
                    Main.playSound("/Error.wav");
                    JOptionPane.showMessageDialog(null, "Invalid Shape Number", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
