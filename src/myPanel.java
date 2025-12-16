import javax.swing.*;
import java.awt.*;

public class myPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        /*
        int n = 500;
        int n0o8 = (int)(n*0.8);
        int n1o5 = (int)(n*1.5);
        int n1o2 = (int)(n*1.2);
        int n1o7 = (int)(n*1.7);
        int n2 = 2*n;
        */

        int n = 100;
        int n0o8 = n-20;
        int n1o5 = n+50;
        int n1o2 = n+20;
        int n1o7 = n+70;
        int n2 = n+100;

        int [] x = {n,n1o5,n2, n1o5};
        int [] y = {n,n1o2,n,n0o8};
        int [] x2 = {n1o5, n1o5,n2,n2};
        int [] y2 = {n1o7,n1o2,n, n1o5};
        int [] x3 = {n1o5, n1o5,n,n};
        int [] y3 = {n1o7,n1o2,n, n1o5};

        g.setColor(Color.decode("0X1f1f1f"));
        g.fillPolygon(x,y,x.length);
        g.fillPolygon(x2,y2,x2.length);
        g.fillPolygon(x3,y3,x3.length);

        g.setColor(Color.decode("0XFFFFFF"));
        g.drawPolygon(x,y,x.length);
        g.drawPolygon(x2,y2,x2.length);
        g.drawPolygon(x3,y3,x3.length);
    }
}
