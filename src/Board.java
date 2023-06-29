import javafx.scene.layout.Pane;


public abstract class Board {
    private static final int WIDTH = 15;
    private static final int LENGTH = 21;
    private static Hexagonal[][] board = new Hexagonal[WIDTH][LENGTH];
    private static int[][] map = new int[WIDTH][LENGTH];

    static {
        int i, j;
        for (i = 0; i < WIDTH; i++) {
            for (j = 0; j < LENGTH; j++) {
                if (i % 2 == 0) {
                    board[i][j] = new Hexagonal(3 * Hexagonal.getA() / 2 * i + Hexagonal.getA() / 2,
                            Math.sqrt(3) * Hexagonal.getA() * j + Math.sqrt(3) / 2 * Hexagonal.getA(),
                            ((i == 0 || i == WIDTH - 1 || j == LENGTH - 1) ? MyColor.BOARD1 : MyColor.BOARD2));

                } else {
                    board[i][j] = new Hexagonal(3 * Hexagonal.getA() / 2 * i + Hexagonal.getA() / 2,
                            Math.sqrt(3) * Hexagonal.getA() * j,
                            ((i == 0 || i == WIDTH - 1 || j == LENGTH - 1) ? MyColor.BOARD1 : MyColor.BOARD2));
                }
            }
        }
        for (i = 0; i < WIDTH; i++) {
            for (j = 0; j < LENGTH; j++) {
                if (i == 0 || i == WIDTH - 1 || j == LENGTH - 1)
                    map[i][j] = -1;
                else
                    map[i][j] = 0;
            }
        }
    }

    public static int getWidth() {
        return WIDTH;
    }

    public static int getLength() {
        return LENGTH;
    }

    public static Hexagonal[][] getBoard() {
        return board;
    }

    public static int[][] getMap() {
        return map;
    }

    public static void setMap(int i, int j, int value) {
        map[i][j] = value;
    }

    public static void initialize() {
        int i, j;
        for (i = 0; i < WIDTH; i++) {
            for (j = 0; j < LENGTH; j++) {
                if (i == 0 || i == WIDTH - 1 || j == LENGTH - 1)
                    map[i][j] = -1;
                else
                    map[i][j] = 0;
            }
        }
    }

    public static void draw(Pane pane) {
        int i, j;
        for (i = 0; i < Board.getWidth(); i++) {
            for (j = 0; j < Board.getLength(); j++) {
                pane.getChildren().add(Board.getBoard()[i][j].getPolygon());
            }
        }
        for (i = 0; i < Board.getWidth(); i++) {
            for (j = 0; j < Board.getLength(); j++) {
                if (map[i][j] != -1 && map[i][j] != 0) {
                    double x = (3 * Hexagonal.getA() * i + Hexagonal.getA()) / (2);
                    double y = (i % 2 == 0
                            ? ((2 * Math.sqrt(3) * Hexagonal.getA() * j + Math.sqrt(3) * Hexagonal.getA()) / 2)
                            : (Math.sqrt(3) * Hexagonal.getA() * j));
                    switch (map[i][j]) {
                        case 1: {
                            Hexagonal hexagonal = new Hexagonal(x, y, MyColor.PURPLE);
                            pane.getChildren().add(hexagonal.getPolygon());
                            break;
                        }
                        case 2: {
                            Hexagonal hexagonal = new Hexagonal(x, y, MyColor.RED);
                            pane.getChildren().add(hexagonal.getPolygon());
                            break;
                        }
                        case 3: {
                            Hexagonal hexagonal = new Hexagonal(x, y, MyColor.PINK);
                            pane.getChildren().add(hexagonal.getPolygon());
                            break;
                        }
                        case 4: {
                            Hexagonal hexagonal = new Hexagonal(x, y, MyColor.ORANGE);
                            pane.getChildren().add(hexagonal.getPolygon());
                            break;
                        }
                        case 5: {
                            Hexagonal hexagonal = new Hexagonal(x, y, MyColor.GREEN);
                            pane.getChildren().add(hexagonal.getPolygon());
                            break;
                        }
                        case 6: {
                            Hexagonal hexagonal = new Hexagonal(x, y, MyColor.BLUE);
                            pane.getChildren().add(hexagonal.getPolygon());
                            break;
                        }
                        case 7: {
                            Hexagonal hexagonal = new Hexagonal(x, y, MyColor.YELLOW);
                            pane.getChildren().add(hexagonal.getPolygon());
                            break;
                        }
                    }
                }
            }
        }
    }

    public static void add(Element element) {
        for (int k = 0; k < 4; k++) {
            double x = (element.getPolygon()[k].getBoundsInParent().getMinX() + element.getPolygon()[k].getBoundsInParent().getMaxX()) / 2;
            double y = (element.getPolygon()[k].getBoundsInParent().getMinY() + element.getPolygon()[k].getBoundsInParent().getMaxY()) / 2;
            int i = (int) Math.round(((2 * x - 2 * Hexagonal.getA()) / (3 * Hexagonal.getA())));
            int j = (int) Math
                    .round((i % 2 == 0 ? ((y - Math.sqrt(3) * Hexagonal.getA()) / (Math.sqrt(3) * Hexagonal.getA()))
                            : ((2 * y - Math.sqrt(3) * Hexagonal.getA()) / (2 * Math.sqrt(3) * Hexagonal.getA()))));
            Board.getMap()[i][j] = element.getType();
        }
    }
}