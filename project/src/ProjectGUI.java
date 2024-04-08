import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * create schedule
 * @author Garron Haley
 */
public class ProjectGUI extends Application {
    schedule shape = new schedule();
    TextField day = new TextField();
    TextField START = new TextField();
    TextField END = new TextField();

    @Override
    public void start(Stage primary) {

        Pane pane = new Pane();
        schedule shape = new schedule();
        pane.getChildren().add(shape);

        Scene scene = new Scene(pane, 500, 500);
        pane.setMaxWidth(scene.getWidth());
        pane.setMaxHeight(scene.getHeight());
        primary.setTitle("schedule");
        GridPane Gpane = new GridPane();
        Gpane.setLayoutX(25);
        Gpane.setLayoutY(25);

        Gpane.add(new Label("Enter number of Days"), 0, 0);
        Gpane.add(day, 1, 0);
        pane.getChildren().add(Gpane);

        Gpane.setOnKeyPressed(e -> {
            if (e.getCode() == (KeyCode.ENTER)) {
                int days = (Integer.parseInt(String.valueOf(day.getText())));
                Gpane.add(new Label("Enter start time (1 to 24) (time is 24 hour clock)"), 0, 1);
                Gpane.add(START, 1, 1);
                Gpane.setOnKeyPressed(f -> {
                    int start = (Integer.parseInt(String.valueOf(START.getText())));
                    Gpane.add(new Label("Enter end time (1 to 24)"), 0, 2);
                    Gpane.add(END, 1, 2);
                    Gpane.setOnKeyPressed(g -> {
                        if (e.getCode() == (KeyCode.ENTER)) {
                            int end = (Integer.parseInt(String.valueOf(END.getText())));
                            pane.getChildren().remove(Gpane);
                            int hours = end - start;
                            if (end < start) {
                                System.exit(1);
                            }
                            if (end == start) {
                                hours = 24;
                            }
                            TextField[][] tf = new TextField[days][hours];

                            schedule(tf, start, end, hours, days, pane, primary);

                        }
                    });

                });
            }
        });


        primary.setScene(scene);
        primary.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void schedule(TextField[][] tf, int start, int end, int hours, int days, Pane pane, Stage stage) {

       schedule time = new schedule(start, start);


        final int[] currHour = {time.start};
        final int[] NextHour = {time.next};
        System.out.println(time.next);
        String[][] Str = new String[days][hours];


        GridPane[] arraypane = new GridPane[days];

        TextField[][] text = new TextField[days][hours];
              text = new TextField[days][hours];
        for (int t = 0; t < days; t++) {
            for (int y = 0; y < hours; y++) {
                text[t][y] = new TextField();
            }

        }
        for (int t = 0; t < days; t++) {
            for (int y = 0; y < hours; y++) {
                Str[t][y] = new String();
            }

        }


        for (int i = 0; i < days; i++) {
            arraypane[i] = new GridPane();

            for (int j = 0; j < hours; j++) {
                arraypane[i].add(new Label("Enter Schedule from " + currHour[0] + " to " + NextHour[0])
                        , 0, j);
                TextField[][] finalText = text;
                arraypane[i].add(finalText[i][j], 1, j);


                currHour[0]++;
                NextHour[0]++;
            }
            currHour[0] = start;
            NextHour[0] = start + 1;


        }
        System.out.println(arraypane);
        final int[] i = {0};
        i[0] = 0;
        Button btfr = new Button("NEXT>>");
        Button btbk = new Button("<<Back");
        Button btfl = new Button("FINALIZE");
        schedule shape = new schedule();
        stage.setTitle("day " + (i[0] + 1));
        pane.getChildren().add(arraypane[i[0]]);
        btfr.setLayoutX(pane.getMaxWidth() - 100);
        btfr.setLayoutY(pane.getMaxHeight() - 30);

        btbk.setLayoutX(pane.getMinWidth() + 50);
        btbk.setLayoutY(pane.getMaxHeight() - 30);
        btfl.setLayoutX((pane.getMaxWidth() / 2) - 30);
        btfl.setLayoutY(pane.getMaxHeight() - 30);
        pane.getChildren().add(btfr);
        pane.getChildren().add(btbk);
        pane.getChildren().add(btfl);
        btfr.setOnAction(e -> {
            if (i[0] < days -1) {
                pane.getChildren().remove(arraypane[i[0]]);
                i[0]++;
                pane.getChildren().add(arraypane[i[0]]);
                stage.setTitle("day " + (i[0] + 1));
                System.out.println(Str[0][0]);
            }
        });
        btbk.setOnAction(e -> {
            if (i[0] > 0 ) {
                pane.getChildren().remove(arraypane[i[0]]);
                i[0]--;
                pane.getChildren().add(arraypane[i[0]]);
                stage.setTitle("day " + (i[0] + 1));
                System.out.println(Str[0][0]);
            }
        });
        TextField[][] finalText1 = text;
        btfl.setOnAction(e -> {
            pane.getChildren().remove(arraypane[i[0]]);
            pane.getChildren().remove(btbk);
            pane.getChildren().remove(btfr);
            pane.getChildren().remove(btfl);
            i[0] = 0;

            for (int t = 0; t < days ; t++) {
                for (int y = 0; y < hours; y++){
                    Str[t][y] = finalText1[t][y].getText();
                }
            }
            GridPane[] arrpane = new GridPane[days];
            for (int t = 0; t< days; t++){
                arrpane[t] = new GridPane();
                for (int j = 0; j < hours; j++) {
                    arrpane[t].add(new Label("Schedule from " + currHour[0] + " to " + NextHour[0] + " is ")
                            , 0, j);
                    arrpane[t].add(new Label(Str[t][j])
                            , 1, j);



                    currHour[0]++;
                    NextHour[0]++;
                }
                currHour[0] = start;
                NextHour[0] = start + 1;


            }
            stage.setTitle("day " + (i[0] + 1));
            Button btback = new Button("<<BACK");
            Button btnext = new Button("NEXT>>");
            btnext.setLayoutX(pane.getMaxWidth() - 100);
            btnext.setLayoutY(pane.getMaxHeight() - 30);
            btback.setLayoutX(pane.getMinWidth() + 50);
            btback.setLayoutY(pane.getMaxHeight() - 30);
            pane.getChildren().add(arrpane[i[0]]);
            pane.getChildren().add(btback);
            pane.getChildren().add(btnext);

            btnext.setOnAction(l -> {
                if (i[0] < days -1) {
                    pane.getChildren().remove(arrpane[i[0]]);
                    i[0]++;
                    pane.getChildren().add(arrpane[i[0]]);
                    stage.setTitle("day " + (i[0] + 1));
                    System.out.println(Str[0][0]);
                }
            });

            btbk.setOnAction(p -> {
                if (i[0] > 0 ) {
                    pane.getChildren().remove(arrpane[i[0]]);
                    i[0]--;
                    pane.getChildren().add(arrpane[i[0]]);
                    stage.setTitle("day " + (i[0] + 1));
                    System.out.println(Str[0][0]);
                }
            });

        });


    }
}
