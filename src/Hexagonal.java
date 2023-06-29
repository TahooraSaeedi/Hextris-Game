import javafx.scene.shape.Polygon;
import javafx.scene.paint.Color;


class Hexagonal {
    private static final double a = 15;
    private Polygon polygon;

    public Hexagonal(double x, double y, MyColor color) {
        this.polygon = new Polygon();
        polygon.getPoints().addAll(new Double[] { x, y, x + a, y, x + 3 * a / 2, (y + a / 2 * Math.sqrt(3)), x + a,
                (y + a * Math.sqrt(3)), x, (y + a * Math.sqrt(3)), x - a / 2, (y + a / 2 * Math.sqrt(3)) });
        polygon.setStroke(Color.BLACK);
        if (color == MyColor.RED)
            polygon.setFill(Color.RED);
        if (color == MyColor.ORANGE)
            polygon.setFill(Color.DARKORANGE);
        if (color == MyColor.YELLOW)
            polygon.setFill(Color.GOLD);
        if (color == MyColor.GREEN)
            polygon.setFill(Color.LIME);
        if (color == MyColor.BLUE)
            polygon.setFill(Color.DEEPSKYBLUE);
        if (color == MyColor.PINK)
            polygon.setFill(Color.DEEPPINK);
        if (color == MyColor.PURPLE)
            polygon.setFill(Color.PURPLE);
        if (color == MyColor.BLACK)
            polygon.setFill(Color.BLACK);
        if (color == MyColor.WHITE)
            polygon.setFill(Color.WHITE);
        if (color == MyColor.BOARD1)
            polygon.setFill(Color.DIMGRAY);
        if (color == MyColor.BOARD2)
            polygon.setFill(Color.GHOSTWHITE);
    }

    public static double getA() {
        return a;
    }

    public Polygon getPolygon() {
        return polygon;
    }
}