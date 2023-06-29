import javafx.scene.transform.Rotate;
import javafx.scene.shape.Polygon;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;

public class Element {
    private double x;
    private double y;
    private int type; // an int between 1 to 7
    private Polygon[] polygon = new Polygon[4];

    public Element(double x, double y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        switch (type) {
            case 1: {
                polygon[0] = new Hexagonal(x, y, MyColor.PURPLE).getPolygon();
                polygon[1] = new Hexagonal(x - 3 * Hexagonal.getA() / 2, y + Math.sqrt(3) * Hexagonal.getA() / 2,
                        MyColor.PURPLE).getPolygon();
                polygon[2] = new Hexagonal(x, y + Math.sqrt(3) * Hexagonal.getA(), MyColor.PURPLE).getPolygon();
                polygon[3] = new Hexagonal(x, y + 2 * Math.sqrt(3) * Hexagonal.getA(), MyColor.PURPLE).getPolygon();
                break;
            }
            case 2: {
                polygon[0] = new Hexagonal(x, y, MyColor.RED).getPolygon();
                polygon[1] = new Hexagonal(x, y + Math.sqrt(3) * Hexagonal.getA(), MyColor.RED).getPolygon();
                polygon[2] = new Hexagonal(x - 3 * Hexagonal.getA() / 2, y + 3 * Math.sqrt(3) * Hexagonal.getA() / 2,
                        MyColor.RED).getPolygon();
                polygon[3] = new Hexagonal(x + 3 * Hexagonal.getA() / 2, y + 3 * Math.sqrt(3) * Hexagonal.getA() / 2,
                        MyColor.RED).getPolygon();
                break;
            }
            case 3: {
                polygon[0] = new Hexagonal(x, y, MyColor.PINK).getPolygon();
                polygon[1] = new Hexagonal(x, y + Math.sqrt(3) * Hexagonal.getA(), MyColor.PINK).getPolygon();
                polygon[2] = new Hexagonal(x, y + 2 * Math.sqrt(3) * Hexagonal.getA(), MyColor.PINK).getPolygon();
                polygon[3] = new Hexagonal(x + 3 * Hexagonal.getA() / 2, y + Math.sqrt(3) * Hexagonal.getA() / 2,
                        MyColor.PINK).getPolygon();
                break;
            }
            case 4: {
                polygon[0] = new Hexagonal(x, y, MyColor.ORANGE).getPolygon();
                polygon[1] = new Hexagonal(x - 3 * Hexagonal.getA() / 2, y + Math.sqrt(3) * Hexagonal.getA() / 2,
                        MyColor.ORANGE).getPolygon();
                polygon[2] = new Hexagonal(x + 3 * Hexagonal.getA() / 2, y + Math.sqrt(3) * Hexagonal.getA() / 2,
                        MyColor.ORANGE).getPolygon();
                polygon[3] = new Hexagonal(x, y + Math.sqrt(3) * Hexagonal.getA(), MyColor.ORANGE).getPolygon();
                break;
            }
            case 5: {
                polygon[0] = new Hexagonal(x, y, MyColor.GREEN).getPolygon();
                polygon[1] = new Hexagonal(x, y + Math.sqrt(3) * Hexagonal.getA(), MyColor.GREEN).getPolygon();
                polygon[2] = new Hexagonal(x - 3 * Hexagonal.getA() / 2, y + 3 * Math.sqrt(3) * Hexagonal.getA() / 2,
                        MyColor.GREEN).getPolygon();
                polygon[3] = new Hexagonal(x - 3 * Hexagonal.getA() / 2, y + 5 * Math.sqrt(3) * Hexagonal.getA() / 2,
                        MyColor.GREEN).getPolygon();
                break;
            }
            case 6: {
                polygon[0] = new Hexagonal(x, y, MyColor.BLUE).getPolygon();
                polygon[1] = new Hexagonal(x, y + Math.sqrt(3) * Hexagonal.getA(), MyColor.BLUE).getPolygon();
                polygon[2] = new Hexagonal(x, y + 2 * Math.sqrt(3) * Hexagonal.getA(), MyColor.BLUE).getPolygon();
                polygon[3] = new Hexagonal(x + 3 * Hexagonal.getA() / 2, y + 5 * Math.sqrt(3) * Hexagonal.getA() / 2,
                        MyColor.BLUE).getPolygon();
                break;
            }
            case 7: {
                polygon[0] = new Hexagonal(x, y, MyColor.YELLOW).getPolygon();
                polygon[1] = new Hexagonal(x, y + Math.sqrt(3) * Hexagonal.getA(), MyColor.YELLOW).getPolygon();
                polygon[2] = new Hexagonal(x, y + 2 * Math.sqrt(3) * Hexagonal.getA(), MyColor.YELLOW).getPolygon();
                polygon[3] = new Hexagonal(x, y + 3 * Math.sqrt(3) * Hexagonal.getA(), MyColor.YELLOW).getPolygon();
                break;
            }
        }
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public Polygon[] getPolygon() {
        return polygon;
    }

    public boolean move(int mode, Pane p) {
        boolean result = true;
        switch (mode) {
            // Move down with the Space key
            case 1: {
                for (int k = 0; k < 4; k++) {
                    this.getPolygon()[k]
                            .setLayoutY(this.getPolygon()[k].getLayoutY() + Math.sqrt(3) * Hexagonal.getA());
                }
                if (!check(Board.getMap(), p)) {
                    for (int k = 0; k < 4; k++) {
                        this.getPolygon()[k]
                                .setLayoutY(this.getPolygon()[k].getLayoutY() - Math.sqrt(3) * Hexagonal.getA());
                    }
                    result = false;
                }
                break;
            }
            // Move right with the Right key
            case 2: {
                int x = (int) Math.round((this.getPolygon()[0].getBoundsInParent().getMaxX() + this.getPolygon()[0].getBoundsInParent().getMinX() - Hexagonal.getA())
                        / (3 * Hexagonal.getA()));
                for (int i = 0; i < 4; i++) {
                    if (x % 2 == 0)
                        this.getPolygon()[i]
                                .setLayoutY(this.getPolygon()[i].getLayoutY() - Math.sqrt(3) / 2 * Hexagonal.getA());
                    else
                        this.getPolygon()[i]
                                .setLayoutY(this.getPolygon()[i].getLayoutY() + Math.sqrt(3) / 2 * Hexagonal.getA());
                    this.getPolygon()[i].setLayoutX(this.getPolygon()[i].getLayoutX() + 3 * Hexagonal.getA() / 2);
                }
                if (!check(Board.getMap(), p)) {
                    for (int i = 0; i < 4; i++) {
                        if (x % 2 == 0)
                            this.getPolygon()[i].setLayoutY(
                                    this.getPolygon()[i].getLayoutY() + Math.sqrt(3) / 2 * Hexagonal.getA());
                        else
                            this.getPolygon()[i].setLayoutY(
                                    this.getPolygon()[i].getLayoutY() - Math.sqrt(3) / 2 * Hexagonal.getA());
                        this.getPolygon()[i].setLayoutX(this.getPolygon()[i].getLayoutX() - 3 * Hexagonal.getA() / 2);
                    }
                    result = false;
                }
                break;
            }
            // Move left with the Left key
            case 3: {
                int x = (int) Math.round((this.getPolygon()[0].getBoundsInParent().getMaxX() + this.getPolygon()[0].getBoundsInParent().getMinX() - Hexagonal.getA())
                        / (3 * Hexagonal.getA()));
                for (int i = 0; i < 4; i++) {
                    if (x % 2 == 0)
                        this.getPolygon()[i]
                                .setLayoutY(this.getPolygon()[i].getLayoutY() - Math.sqrt(3) / 2 * Hexagonal.getA());
                    else
                        this.getPolygon()[i]
                                .setLayoutY(this.getPolygon()[i].getLayoutY() + Math.sqrt(3) / 2 * Hexagonal.getA());
                    this.getPolygon()[i].setLayoutX(this.getPolygon()[i].getLayoutX() - 3 * Hexagonal.getA() / 2);
                }
                if (!check(Board.getMap(), p)) {
                    for (int i = 0; i < 4; i++) {
                        if (x % 2 == 0)
                            this.getPolygon()[i].setLayoutY(
                                    this.getPolygon()[i].getLayoutY() + Math.sqrt(3) / 2 * Hexagonal.getA());
                        else
                            this.getPolygon()[i].setLayoutY(
                                    this.getPolygon()[i].getLayoutY() - Math.sqrt(3) / 2 * Hexagonal.getA());
                        this.getPolygon()[i].setLayoutX(this.getPolygon()[i].getLayoutX() + 3 * Hexagonal.getA() / 2);
                    }
                    result = false;
                }
                break;
            }
            // Rotate with the Down key
            case 4: {
                for (int i = 0; i < 4; i++) {
                    this.getPolygon()[i].getTransforms().add(new Rotate(60, this.getX() + Hexagonal.getA() / 2,
                            this.getY() + Math.sqrt(3) * Hexagonal.getA() / 2));
                }
                if (!check(Board.getMap(), p)) {
                    for (int i = 0; i < 4; i++) {
                        this.getPolygon()[i].getTransforms().add(new Rotate(-60, this.getX() + Hexagonal.getA() / 2,
                                this.getY() + Math.sqrt(3) * Hexagonal.getA() / 2));
                    }
                    result = false;
                }
                break;
            }
            // Rotate with the Up key
            case 5: {
                for (int i = 0; i < 4; i++) {
                    this.getPolygon()[i].getTransforms().add(new Rotate(-60, this.getX() + Hexagonal.getA() / 2,
                            this.getY() + Math.sqrt(3) * Hexagonal.getA() / 2));
                }
                if (!check(Board.getMap(), p)) {
                    for (int i = 0; i < 4; i++) {
                        this.getPolygon()[i].getTransforms().add(new Rotate(60, this.getX() + Hexagonal.getA() / 2,
                                this.getY() + Math.sqrt(3) * Hexagonal.getA() / 2));
                    }
                    result = false;

                }
                break;
            }
        }
        return result;
    }

    private boolean check(int[][] map, Pane p) {
        boolean result = true;
        Shape shape;
        int i, j, k;
        for (i = 0; i < Board.getWidth(); i++) {
            for (j = 0; j < Board.getLength(); j++) {
                if (Board.getMap()[i][j] != 0) {
                    double x = 3 * Hexagonal.getA() * i / 2 + Hexagonal.getA() / 2;
                    double y = (i % 2 == 0
                            ? (2 * Math.sqrt(3) * Hexagonal.getA() * j / 2 + Math.sqrt(3) * Hexagonal.getA() / 2)
                            : (2 * Math.sqrt(3) * Hexagonal.getA() * j / 2));
                    Hexagonal hexagonal = new Hexagonal(x, y, MyColor.BLACK);
                    for (k = 0; k < 4; k++) {
                        p.getChildren().add(hexagonal.getPolygon());
                        shape = Shape.intersect(this.getPolygon()[k], hexagonal.getPolygon());
                        p.getChildren().add(shape);
                        //shape = Shape.intersect(this.getPolygon()[k], hexagonal.getPolygon())
                        if (shape.toString()
                                .contains("MoveTo")) {
                            if (shape.computeAreaInScreen() > 122) {
                                result = false;
                            }
                        }
                        p.getChildren().remove(shape);
                        p.getChildren().remove(hexagonal.getPolygon());
                    }
                }
            }
        }
        return result;
    }
}