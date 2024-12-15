package agh.ics.oop.presenter;

import agh.ics.oop.OptionsParser;
import agh.ics.oop.Simulation;
import agh.ics.oop.SimulationEngine;
import agh.ics.oop.model.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;

public class SimulationPresenter implements MapChangeListener {
    private WorldMap worldMap;

    @FXML
    private Label infoLabel;
    @FXML
    private Label moveLabel;
    @FXML
    private TextField moveList;

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    public void drawMap() {
        infoLabel.setText(worldMap.toString());
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        setWorldMap(worldMap);
        Platform.runLater(() -> {
            drawMap();
            moveLabel.setText(message);
        });
    }

    public void onSimulationStartClicked() {
        try {
            GrassField map = new GrassField(10);
            this.setWorldMap(map);
            map.addObserver(this);

            List<MoveDirection> directions = OptionsParser.parse(moveList.getText().split(" "));
            List<Vector2d> positions = List.of(new Vector2d(0, 0), new Vector2d(2, 2));

            Simulation simulation = new Simulation(positions, directions, worldMap);
            SimulationEngine engine = new SimulationEngine(List.of(simulation));
            engine.runAsync();
        }
        catch (IllegalArgumentException e) {
            moveLabel.setText(e.getMessage());
        }
    }
}
