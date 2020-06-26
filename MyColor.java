import java.awt.*;
import java.awt.color.ColorSpace;

public class MyColor {
    private String name;
    private Color color;

    public MyColor(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }

    public String toString() {
        return this.name;
    }
}
