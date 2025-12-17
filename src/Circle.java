import java.awt.*;

public class Circle extends Shape{
    private double radius;

    public Circle(double radius) {
        super();
        this.radius = radius;
    }

    public Circle(String color, double radius) {
        super(color);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String howToDraw() {
        return "Draw a circle with radius " + radius;
    }

    @Override
    public String toString() {
        return "Circle \nRadius: " + radius + "\nArea: " + String.format("%.2f",getArea()) + "\nPerimeter: " + String.format("%.2f",getPerimeter()) + "\nColor: " + getColor() + "\nCreated: " + getDateCreated();
    }
}
