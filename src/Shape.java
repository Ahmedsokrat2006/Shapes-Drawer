import java.awt.*;
import java.util.Date;

public abstract class Shape implements Drawable{
    private java.util.Date dateCreated;
    private String color;

    public Shape(){
        this.dateCreated = new Date();
        this.color = "White";
    }

    public Shape(String color) {
        this.dateCreated = new Date();
        this.color = color;
    }
    public Date getDateCreated() {
        return dateCreated;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
    public abstract double getArea();
    public abstract double getPerimeter();
}
