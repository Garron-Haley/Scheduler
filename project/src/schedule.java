import java.io.Serializable;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class schedule extends Pane implements Serializable {
    int start;
    int next;
    schedule(){
        start = 0;
        next = 0;
    }
    schedule(int newstart, int newnext){
        start = newstart;
        next = newnext + 1;
    }
    public void setstart(int newstart){
        start = newstart;
    }
    public void setnext(int newnext){
        next = newnext + 1;
    }

}
