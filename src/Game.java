import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Label;
import javafx.event.EventHandler;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

import java.util.Random;

public abstract class Game {
    private static int Score;
    static Timeline t;

    public static int getScore() {
        return Score;
    }

    public static void setScore(int score) {
        Score = score;
    }

    public static void menu(Pane pane) {
        // pane
        pane.setStyle("-fx-background-image:url(\"file:menu.jpg\");-fx-background-size:345 558");

        // Hextris label
        Label label = new Label("Hextris");
        label.setPrefSize(400, 100);
        label.setLayoutX(35);
        label.setLayoutY(35);
        label.setStyle("-fx-background-color:null;-fx-font-family:Stencil;-fx-font-size:65;-fx-text-fill:black");
        pane.getChildren().add(label);

        // Start button
        Button start = new Button("Start");
        start.setPrefSize(200, 50);
        start.setLayoutX(150);
        start.setLayoutY(150);
        start.setStyle("-fx-background-color:null;-fx-font-family:Stencil;-fx-font-size:40;-fx-text-fill:DIMGRAY");
        start.setOnMouseEntered(e -> {
            start.setStyle(
                    "-fx-background-color:null;-fx-font-family:Stencil;-fx-font-size:40;-fx-text-fill:LIGHTGRAY");
        });
        start.setOnMouseExited(e -> {
            start.setStyle("-fx-background-color:null;-fx-font-family:Stencil;-fx-font-size:40;-fx-text-fill:DIMGRAY");
        });
        start.setOnAction(e -> {
            Stage newStage = new Stage();
            Pane newPane = new Pane();
            Scene newScene = new Scene(newPane, 345, 558);
            newStage.setTitle("Hextris");
            newStage.getIcons().add(new Image("file:icon.png"));
            newStage.setResizable(false);
            newStage.setScene(newScene);
            ((Node) (e.getSource())).getScene().getWindow().hide();
            newStage.show();
            Game.play(newPane, newScene);
        });
        pane.getChildren().add(start);

        // Exit button
        Button exit = new Button("Exit");
        exit.setPrefSize(200, 50);
        exit.setLayoutX(150);
        exit.setLayoutY(210);
        exit.setStyle("-fx-background-color:null;-fx-font-family:Stencil;-fx-font-size:40;-fx-text-fill:DIMGRAY");
        exit.setOnMouseEntered(e -> {
            exit.setStyle("-fx-background-color:null;-fx-font-family:Stencil;-fx-font-size:40;-fx-text-fill:LIGHTGRAY");
        });
        exit.setOnMouseExited(e -> {
            exit.setStyle("-fx-background-color:null;-fx-font-family:Stencil;-fx-font-size:40;-fx-text-fill:DIMGRAY");
        });
        exit.setOnAction(e -> {
            System.exit(0);
        });
        pane.getChildren().add(exit);
    }

    public static void play(Pane pane, Scene scene) {
        Board.initialize();
        Game.setScore(0);
        Board.draw(pane);
        Random random = new Random();
        t = new Timeline(new KeyFrame(Duration.seconds(0.7), new EventHandler<ActionEvent>() {
            boolean x = false;
            Element element;
            int number = 0;

            @Override
            public void handle(ActionEvent event) {
                if (x == false) {
                    if (number != 0) {
                        Board.add(element);
                        Game.deleteLine(pane);
                    }

                    //element = new Element(11 * Hexagonal.getA(), 0, random.nextInt(7) + 1);

                    if (number == 0) element = new Element(11 * Hexagonal.getA(), 0, 1);
                    if (number == 1) element = new Element(11 * Hexagonal.getA(), 0, 2);
                    if (number == 2) element = new Element(11 * Hexagonal.getA(), 0, 1);
                    if (number == 3) element = new Element(11 * Hexagonal.getA(), 0, 7);
                    if (number == 4) element = new Element(11 * Hexagonal.getA(), 0, 4);
                    if (number == 5) element = new Element(11 * Hexagonal.getA(), 0, 7);
                    if (number == 6) element = new Element(11 * Hexagonal.getA(), 0, 4);
                    if (number != 0 && number != 1 && number != 2 && number != 3 && number != 4 && number != 5 && number != 6) element = new Element(11 * Hexagonal.getA(), 0, random.nextInt(7) + 1);

                    boolean gameOver = Game.gameOver(pane, Board.getMap(), element);
                    if (gameOver)
                        t.stop();
                    if (!gameOver) {
                        number++;
                        pane.getChildren().addAll(element.getPolygon());
                        x = true;
                    }

                } else {
                    x = element.move(1, pane);
                }
                scene.setOnKeyPressed(e -> {
                    if (e.getCode() == KeyCode.SPACE) {
                        if (x)
                            element.move(1, pane);
                    }
                    if (e.getCode() == KeyCode.RIGHT) {
                        if (x)
                            element.move(2, pane);
                    }
                    if (e.getCode() == KeyCode.LEFT) {
                        if (x)
                            element.move(3, pane);
                    }
                    if (e.getCode() == KeyCode.DOWN) {
                        if (x)
                            element.move(4, pane);
                    }
                    if (e.getCode() == KeyCode.UP) {
                        if (x)
                            element.move(5, pane);
                    }
                });
            }
        }));
        t.setCycleCount(Timeline.INDEFINITE);
        t.play();
    }

    public static void deleteLine(Pane pane) {
        int i, j, k;
        boolean result;
        for (j = 0; j < Board.getLength() - 1; j++) {
            result = true;
            for (i = 0; i < Board.getWidth(); i++) {
                if (Board.getMap()[i][j] == 0) {
                    result = false;
                    break;
                }
            }
            if (result) {
                Game.setScore(Game.getScore() + 10);
                for (k = j; k >= 0; k--) {
                    for (i = 0; i < Board.getWidth(); i++) {
                        if (k - 1 != -1)
                            Board.setMap(i, k, Board.getMap()[i][k - 1]);
                        else {
                            if (i != 0 && i != 14)
                                Board.setMap(i, k, 0);
                            if (i == 0 || i == 14)
                                Board.setMap(i, k, -1);
                        }
                    }
                }
                pane.getChildren().clear();
                Board.draw(pane);
            }
        }
    }

    public static boolean gameOver(Pane pane, int[][] map, Element element) {
        boolean gameOver = false;
        switch (element.getType()) {
            case 1: {
                if (map[7][0] != 0 || map[6][0] != 0 || map[7][1] != 0 || map[7][2] != 0) {
                    gameOver = true;
                }
                break;
            }
            case 2: {
                if (map[7][0] != 0 || map[7][1] != 0 || map[6][1] != 0 || map[8][1] != 0) {
                    gameOver = true;
                }
                break;
            }
            case 3: {
                if (map[7][0] != 0 || map[7][1] != 0 || map[7][2] != 0 || map[8][0] != 0) {
                    gameOver = true;
                }
                break;
            }
            case 4: {
                if (map[7][0] != 0 || map[6][0] != 0 || map[8][0] != 0 || map[7][1] != 0) {
                    gameOver = true;
                }
                break;
            }
            case 5: {
                if (map[7][0] != 0 || map[7][1] != 0 || map[6][1] != 0 || map[6][2] != 0) {
                    gameOver = true;
                }
                break;
            }
            case 6: {
                if (map[7][0] != 0 || map[7][1] != 0 || map[7][2] != 0 || map[8][2] != 0) {
                    gameOver = true;
                }
                break;
            }
            case 7: {
                if (map[7][0] != 0 || map[7][1] != 0 || map[7][2] != 0 || map[7][3] != 0) {
                    gameOver = true;
                }
                break;
            }
        }
        if (gameOver) {
            // Pane
            pane.getChildren().clear();
            pane.setStyle("-fx-background-image:url(\"file:menu.jpg\");-fx-background-size:345 558");

            // Game over label
            Label label = new Label("Game Over!");
            label.setPrefSize(400, 100);
            label.setLayoutX(35);
            label.setLayoutY(5);
            label.setStyle("-fx-background-color:null;-fx-font-family:Stencil;-fx-font-size:50;-fx-text-fill:black");
            pane.getChildren().add(label);

            // Score label
            Label score = new Label("Your Score : " + Game.getScore());
            score.setPrefSize(400, 100);
            score.setLayoutX(65);
            score.setLayoutY(70);
            score.setStyle("-fx-background-color:null;-fx-font-family:Stencil;-fx-font-size:30;-fx-text-fill:black");
            pane.getChildren().add(score);

            // Play again button
            Button playAgain = new Button("Play Again");
            playAgain.setPrefSize(200, 50);
            playAgain.setLayoutX(150);
            playAgain.setLayoutY(270);
            playAgain.setStyle(
                    "-fx-background-color:null;-fx-font-family:Stencil;-fx-font-size:25;-fx-text-fill:DIMGRAY");
            playAgain.setOnMouseEntered(e -> {
                playAgain.setStyle(
                        "-fx-background-color:null;-fx-font-family:Stencil;-fx-font-size:25;-fx-text-fill:LIGHTGRAY");
            });
            playAgain.setOnMouseExited(e -> {
                playAgain.setStyle(
                        "-fx-background-color:null;-fx-font-family:Stencil;-fx-font-size:25;-fx-text-fill:DIMGRAY");
            });
            playAgain.setOnAction(e -> {
                Stage newStage = new Stage();
                Pane newPane = new Pane();
                Scene newScene = new Scene(newPane, 345, 558);
                newStage.setTitle("Hextris");
                newStage.getIcons().add(new Image("file:icon.png"));
                newStage.setResizable(false);
                newStage.setScene(newScene);
                ((Node) (e.getSource())).getScene().getWindow().hide();
                newStage.show();
                Game.play(newPane, newScene);
            });
            pane.getChildren().add(playAgain);

            // Exit button
            Button exit = new Button("Exit");
            exit.setPrefSize(200, 50);
            exit.setLayoutX(150);
            exit.setLayoutY(300);
            exit.setStyle("-fx-background-color:null;-fx-font-family:Stencil;-fx-font-size:25;-fx-text-fill:DIMGRAY");
            exit.setOnMouseEntered(e -> {
                exit.setStyle(
                        "-fx-background-color:null;-fx-font-family:Stencil;-fx-font-size:25;-fx-text-fill:LIGHTGRAY");
            });
            exit.setOnMouseExited(e -> {
                exit.setStyle(
                        "-fx-background-color:null;-fx-font-family:Stencil;-fx-font-size:25;-fx-text-fill:DIMGRAY");
            });
            exit.setOnAction(e -> {
                System.exit(0);
            });
            pane.getChildren().add(exit);

            // Additional Element
            Element additionalElement = new Element(240, 170, element.getType());
            pane.getChildren().addAll(additionalElement.getPolygon());
        }
        return gameOver;
    }
}