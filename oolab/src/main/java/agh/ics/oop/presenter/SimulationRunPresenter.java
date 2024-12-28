package agh.ics.oop.presenter;

import agh.ics.oop.model.MapChangeListener;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldElement;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.util.Boundary;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import java.util.List;

public class SimulationRunPresenter implements MapChangeListener {

    private final int CELL_WIDTH = 38;
    private final int CELL_HEIGHT = 38;

    private WorldMap worldMap;
    private Boundary currentBounds;

    @FXML
    private Label moveLabel;
    @FXML
    public GridPane mapGrid;

    private void clearGrid() {
        if(mapGrid.getChildren().isEmpty()) return;

        mapGrid.getChildren().retainAll(mapGrid.getChildren().getFirst()); // hack to retain visible grid lines
        mapGrid.getColumnConstraints().clear();
        mapGrid.getRowConstraints().clear();
    }

    private void addLabels(int mapHeight, int mapWidth) {
        Label label = new Label("x/y");
        GridPane.setHalignment(label, HPos.CENTER);
        mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
        mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
        mapGrid.add(label, 0, 0);

        for(int i = 0; i <= mapHeight; i++) {
            Label labelY = new Label(String.valueOf(currentBounds.upperRight().getY() - i));
            GridPane.setHalignment(labelY, HPos.CENTER);
            mapGrid.getRowConstraints().add(new RowConstraints(CELL_HEIGHT));
            mapGrid.add(labelY, 0, i+1);
        }

        for(int i = 0; i <= mapWidth; i++) {
            Label labelX = new Label(String.valueOf( currentBounds.lowerLeft().getX() + i));
            GridPane.setHalignment(labelX, HPos.CENTER);
            mapGrid.getColumnConstraints().add(new ColumnConstraints(CELL_WIDTH));
            mapGrid.add(labelX, i+1, 0);
        }
    }

    private void addWorldElements() {
        List<WorldElement> worldElements = (List<WorldElement>) worldMap.getElements();

        worldElements.forEach(worldElement -> {
            Label worldElementLabel = new Label(worldElement.toString());
            GridPane.setHalignment(worldElementLabel, HPos.CENTER);
            Vector2d position = worldElement.getPosition();
            mapGrid.add(worldElementLabel,
                    position.getX() + 1 - currentBounds.lowerLeft().getX(),
                    currentBounds.upperRight().getY() - position.getY() + 1);
        });
    }

    public void setWorldMap(WorldMap worldMap) {
        this.worldMap = worldMap;
    }

    private void drawMap() {
        clearGrid();

        currentBounds = worldMap.getCurrentBounds();
        int mapHeight = currentBounds.upperRight().getY() - currentBounds.lowerLeft().getY();
        int mapWidth = currentBounds.upperRight().getX() - currentBounds.lowerLeft().getX();

        addLabels(mapHeight, mapWidth);
        addWorldElements();
    }

    @Override
    public void mapChanged(WorldMap worldMap, String message) {
        setWorldMap(worldMap);
        Platform.runLater(() -> {
            drawMap();
            moveLabel.setText(message);
        });
    }
}
