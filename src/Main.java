import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
    private Color color = Color.WHITE;
    private String colorName = "White";
    private final int x = 150;
    private final int y = 190;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Drawable currentShape = shapesList.get(i);

        if (currentShape instanceof Circle) {
            drawCircle(g, currentShape);
        } else if (currentShape instanceof Cube) {
            drawCube(g, currentShape);
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.SERIF, Font.BOLD, 24));
        g.drawString("Shape " + (i + 1) + " of " + shapesList.size(), 20, 20);
    }

    private void drawCircle(Graphics g, Drawable currentShape) {
        Circle c = (Circle) currentShape;
        c.setColor(colorName);
        int size = (int) c.getRadius();
        size *= 3;
        g.setColor(color);
        g.drawOval(x, y, size, size);

        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        printData(g, c);
        printHowToDraw(g, c, size);
    }

    private void drawCube(Graphics g, Drawable currentShape) {
        Cube c = (Cube) currentShape;
        c.setColor(colorName);
        int size = (int) c.getSide();
        size = 2 * size;
        g.setColor(color);

        g.drawRect(x, y, size, size);
        g.drawRect(x + size / 2, y + size / 2, size, size);
        g.drawLine(x, y, x + size / 2, y + size / 2);
        g.drawLine(x + size, y, x + 3 * size / 2, y + size / 2);
        g.drawLine(x, y + size, x + size / 2, y + 3 * size / 2);
        g.drawLine(x + size, y + size, x + 3 * size / 2, y + 3 * size / 2);

        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));

        printData(g, c);
        printHowToDraw(g, c, 3 * size / 2);
    }

    private void printHowToDraw(Graphics g, Drawable c, int size) {
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g.drawString("How to Draw:", x - 130, y + size + 20);
        g.setFont(new Font(Font.SERIF, 0, 16));
        g.drawString(c.howToDraw(), x - 130, y + size + 40);
    }

    private void printData(Graphics g, Drawable c) {
        String[] text = c.toString().split("\n");
        for (int i = 0; i < text.length; i++) {
            g.drawString(text[i], x - 130, y - (140 - i * 20));
        }
    }

    public void setColor(Color color, String colorName) {
        this.color = color;
        this.colorName = colorName;
        playSound("/Success.wav");
        repaint();
    }

    public Color getColor(){
        return color;
    }

    public void nextShape() {
        if (i < shapesList.size() - 1) {
            i++;
        } else {
            i = 0;
        }
        playSound("/Pew.wav");
        repaint();
    }

    public void prevShape() {
        if (i > 0) {
            i--;
        } else {
            i = shapesList.size() - 1;
        }
        playSound("/Quack.wav");
        repaint();
    }

    public void addData() {
        try {
            PrintWriter writer = new PrintWriter(outputFile);
            playSound("/ta-da!.wav");
            writer.println("Cubes count: " + cubesCount);
            writer.println("Circles count: " + (shapesList.size() - cubesCount));
            writer.printf("\nSum of all Areas: %.2f", totalArea);
            writer.printf("\nSum of all Perimeters: %.2f", totalPerimeter);
            if (totalCubesArea > 0) {
                writer.printf("\n\nSum of cubes Area: %.2f", totalCubesArea);
                writer.printf("\nSum of cubes Perimeter: %.2f", totalCubesPerimeter);
                writer.printf("\n\nSum of cubes Volume: %.2f", totalVolume);
            }
            if (totalArea - totalCubesArea > 0) {
                writer.printf("\n\nSum of circles Area: %.2f", totalArea - totalCubesArea);
                writer.printf("\nSum of circles Perimeter: %.2f", totalPerimeter - totalCubesPerimeter);
            }
            JOptionPane.showMessageDialog(null, "Data Added To The File Successfully :)");
            writer.close();
            Desktop.getDesktop().open(outputFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void runInputFile() {
        try {
            Desktop.getDesktop().open(inputFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void playSound(String soundFileName) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Main.class.getResource(soundFileName)));
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean invalidInputErrorMessage(int i){
        playSound("/Error.wav");
        int n = JOptionPane.showOptionDialog(null, "Invalid input in shape number " + (i + 1), "Invalid Input", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Skip", "Edit"}, 0);
        if (n == 1) {
            runInputFile();
            return true;
        }
        return false;
    }

    private static void inputFileError(String message){
        playSound("/Error.wav");
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
        runInputFile();
    }

    public static void main(String[] args) {
        try {
            Scanner input = new Scanner(inputFile);
            if (input.hasNextInt()) {
                int size = input.nextInt();
                if (size < 0) {
                    inputFileError("Are You Serious?????!!!!! (You Can't Enter Negative Number of Shapes)");
                    return;
                } else if (size < 2) {
                    inputFileError("Number of Shapes Must Be More than or Equal 2 !!");
                    return;
                }
                if(size > 99_999_999){
                    inputFileError("Max number of shapes is 99,999,999");
                    return;
                }
                for (int i = 0; i < size; i++) {
                    if (input.hasNext()) {
                        String type = input.next();
                        if (input.hasNextDouble()) {
                            double val = input.nextDouble();
                            if (val <= 0) {
                                if (invalidInputErrorMessage(i)){
                                    return;
                                }else{
                                    continue;
                                }
                            }
                            if (type.equalsIgnoreCase("circle") && val <= 150) {
                                Circle c = new Circle(val);
                                shapesList.add(c);
                                totalArea += c.getArea();
                                totalPerimeter += c.getPerimeter();
                            } else if (type.equalsIgnoreCase("cube") && val <= 150) {
                                Cube c = new Cube(val);
                                shapesList.add(c);
                                cubesCount++;
                                totalArea += c.getArea();
                                totalCubesArea += c.getArea();
                                totalPerimeter += c.getPerimeter();
                                totalCubesPerimeter += c.getPerimeter();
                                totalVolume += c.getVolume();
                            } else {
                                if(val > 150){
                                    playSound("/Error.wav");
                                    int n = JOptionPane.showOptionDialog(null, "Large input in shape number " + (i + 1) + " (Max input is 150)", "Invalid Input", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Skip", "Edit"}, 0);
                                    if (n == 1) {
                                        runInputFile();
                                        return;
                                    }else {
                                        continue;
                                    }
                                }
                                if (invalidInputErrorMessage(i)){
                                    return;
                                }
                            }
                        }else{
                            if (invalidInputErrorMessage(i)){
                                return;
                            }
                            input.nextLine();
                        }

                    }
                }
            }else {
                inputFileError("Invalid number of shapes");
                return;
            }
        } catch (FileNotFoundException e) {
            playSound("/Error.wav");
            JOptionPane.showMessageDialog(null, "No Input File Found", "File Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (shapesList.size() < 2) {
            inputFileError("Number of Shapes Must Be More than or Equal 2 !!");
            return;
        }
        playSound("/Wow.wav");
        new DrawFrame();
    }
}