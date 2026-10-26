package org.example;

import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HistoryWindow {
    public static void display(Taker taker, Controller controller) {
        Stage window = new Stage();
        window.setTitle("History Window");

        ListView<IMemento> listView = new ListView<>(taker.getHistoryList());


        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                controller.restoreState(newVal);
            }
        });

        VBox layout = new VBox(10, listView);
        Scene scene = new Scene(layout, 350, 400);
        window.setScene(scene);
        window.show();
    }

}
