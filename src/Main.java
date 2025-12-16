import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main extends JPanel {
    private static final ArrayList<Drawable> shapesList = new ArrayList<>();
    private int i = 0;
    private static final File inputFile = new File("input.txt");
    private static final File outputFile = new File("output.txt");
    private static double totalArea = 0;
    private static double totalCubesArea = 0;
    private static double totalPerimeter = 0;
    private static double totalCubesPerimeter = 0;
    private static double totalVolume = 0;
    private static int cubesCount = 0;
    private Color color = new Color(255, 255, 255);

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (shapesList.isEmpty()) {
            g.setColor(Color.RED);
            g.setFont(new Font(Font.SERIF,Font.BOLD,32));
            g.drawString("No shapes found in input.txt", 50, 50);
            return;
        }

        Drawable currentShape = shapesList.get(i);

        if (currentShape instanceof Circle) {
            drawCircle(g,currentShape);
        } else if (currentShape instanceof Cube) {
            drawCube(g,currentShape);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SERIF,Font.BOLD,24));
        g.drawString("Shape " + (i + 1) + " of " + shapesList.size(), 20, 20);
    }
    private void drawCircle(Graphics g,Drawable currentShape){
        int x = 150;
        int y = 120;

        Circle c = (Circle) currentShape;
        int size = (int) c.getRadius();
        size *= 3;
        g.setColor(color);
        g.drawOval(x, y, size, size);

        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.MONOSPACED,Font.BOLD,16));
        g.drawString("Circle Radius = " + c.getRadius(), x-130, y - 50);
        g.drawString("Circle Area = " + String.format("%.2f", c.getArea()),x-130,y-30);
        g.drawString("Circle Perimeter = " + String.format("%.2f", c.getPerimeter()),x-130,y-10);

        g.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
        g.drawString("How to Draw:",x - 130, y + size + 20);
        g.setFont(new Font(Font.SERIF,0,16));
        g.drawString(c.howToDraw(),x - 130, y + size + 40);
    }

    private void drawCube(Graphics g, Drawable currentShape){
        int x = 150;
        int y = 120;

        Cube c = (Cube) currentShape;
        int size = (int) c.getSide();
        size = 3 * size / 2;
        g.setColor(color);

        g.drawRect(x, y, size, size);
        g.drawRect(x + size / 2, y + size / 2, size, size);
        g.drawLine(x, y, x + size / 2, y + size / 2);
        g.drawLine(x + size, y, x + 3 * size / 2 , y + size / 2);
        g.drawLine(x, y + size, x + size / 2, y + 3 * size / 2);
        g.drawLine(x + size, y + size, x + 3 * size / 2, y + 3 * size / 2);

        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.MONOSPACED,Font.BOLD,16));
        g.drawString("Cube Side = " + c.getSide(), x-130, y - 70);
        g.drawString("Cube Area = " + String.format("%.2f", c.getArea()), x-130, y - 50);
        g.drawString("Cube Perimeter = " + String.format("%.2f", c.getPerimeter()), x-130, y - 30);
        g.drawString("Cube Volume = " + String.format("%.2f", c.getVolume()) , x-130, y - 10);

        g.setFont(new Font(Font.MONOSPACED,Font.BOLD,20));
        g.drawString("How to Draw:",x - 130, y + 3 * size / 2 + 20);
        g.setFont(new Font(Font.SERIF,0,16));
        g.drawString(c.howToDraw(),x - 130, y + 3 * size / 2 + 40);
    }

    public void setColor(Color color){
        this.color = color;
        repaint();
    }

    public void nextShape() {
        if (i < shapesList.size() - 1) {
            i++;
        }else {
            i = 0;
        }
        repaint();
    }

    public void prevShape() {
        if (i > 0) {
            i--;
        } else{
            i = shapesList.size() - 1;
        }
        repaint();
    }

    public void addAreas(){
        try {
            if(totalArea > 0) {
                PrintWriter writer = new PrintWriter(outputFile);
                writer.println("Cubes count: " + cubesCount);
                writer.println("Circles count: " + (shapesList.size() - cubesCount));
                writer.printf("\nSum of all Areas: %.2f", totalArea);
                writer.printf("\nSum of all Perimeters: %.2f", totalPerimeter);
                if(totalCubesArea > 0){
                    writer.printf("\n\nSum of cubes Area: %.2f", totalCubesArea);
                    writer.printf("\nSum of cubes Perimeter: %.2f", totalCubesPerimeter);
                    writer.printf("\n\nSum of cubes Volume: %.2f" , totalVolume);
                }
                if(totalArea - totalCubesArea > 0){
                    writer.printf("\n\nSum of circles Area: %.2f", totalArea - totalCubesArea);
                    writer.printf("\nSum of circles Perimeter: %.2f", totalPerimeter - totalCubesPerimeter);
                }
                JOptionPane.showMessageDialog(null, "Sum of areas added to the File successfully :)");
                writer.close();
            }
            else {
                JOptionPane.showMessageDialog(null,"No Shapes Found!!!!");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        try {
            Scanner input = new Scanner(inputFile);
            if (input.hasNextInt()) {
                int size = input.nextInt();
                for (int i = 0; i < size; i++) {
                    if (input.hasNext()) {
                        String type = input.next();
                        double val = input.nextDouble();
                        if (type.equalsIgnoreCase("circle")) {
                            Circle c = new Circle(val);
                            shapesList.add(c);
                            totalArea += c.getArea();
                            totalPerimeter += c.getPerimeter();
                        } else if (type.equalsIgnoreCase("cube")) {
                            Cube c = new Cube(val);
                            shapesList.add(c);
                            cubesCount++;
                            totalArea += c.getArea();
                            totalCubesArea += c.getArea();
                            totalPerimeter += c.getPerimeter();
                            totalCubesPerimeter += c.getPerimeter();
                            totalVolume += c.getVolume();
                        }
                    }
                }
            }

        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null,"No Input File Found");
            return;
        }
        new DrawFrame();
    }
}