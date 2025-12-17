import java.awt.*;

public class Cube extends ThreeDShape{
    private double side;

    public Cube(double side) {
        super();
        this.side = side;
    }

    public Cube(String color, double side) {
        super(color);
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public void setSide(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return 6 * side * side;
    }

    @Override
    public double getPerimeter() {
        return 12 * side;
    }

    @Override
    public double getVolume() {
        return Math.pow(side, 3);
    }

    @Override
    public String howToDraw() {
        return "Draw 2 squares with side " + side + " and connect their sides with 4 lines";
    }

    @Override
    public String toString() {
        return "Cube \nSide: " + side + "\nArea: " + String.format("%.2f",getArea()) + "\nPerimeter: " + String.format("%.2f",getPerimeter()) + "\nVolume: " + String.format("%.2f",getVolume()) + "\nColor: " + getColor() + "\nCreated: " + getDateCreated();
    }
}
