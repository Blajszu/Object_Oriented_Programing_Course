package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.GrassField;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class SimulationStartPresenter {

    @FXML
    private Label infoLabel;
    @FXML
    private TextField moveList;

    public void onSimulationStartClicked() {

        try {
            Stage simulationStage = new Stage();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("simulationRun.fxml"));
            BorderPane viewRoot = loader.load();
            SimulationRunPresenter simulationRunPresenter = loader.getController();

            var scene = new Scene(viewRoot);
            simulationStage.setScene(scene);
            simulationStage.setTitle("Simulation app");
            simulationStage.minWidthProperty().bind(viewRoot.minWidthProperty());
            simulationStage.minHeightProperty().bind(viewRoot.minHeightProperty());

            simulationStage.show();

            GrassField map = new GrassField(10);
            simulationRunPresenter.setWorldMap(map);
            map.addObserver(simulationRunPresenter);

            List<MoveDirection> directions = OptionsParser.parse(moveList.getText().split(" "));
            List<Vector2d> positions = List.of(new Vector2d(0, 0), new Vector2d(2, 2));

            Simulation simulation = new Simulation(positions, directions, map);
            SimulationEngine engine = new SimulationEngine(List.of(simulation));
            engine.runAsync();
        }
        catch (IllegalArgumentException | IOException e) {
            infoLabel.setText(e.getMessage());
        }
    }
}