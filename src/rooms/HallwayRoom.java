package rooms;

import theitems.Item;
import theobstacles.Obstacle;

import java.util.ArrayList;

public class HallwayRoom extends Room{

    private int[] escapeCoordinate;
    Obstacle crate;
    private int[] pressPlate = {3, 3};
    private boolean barsRetracted = false;
    private boolean eventTrigger;

    public HallwayRoom(int[][] exitCoordinates, ArrayList<Item> items, String[][] map, Obstacle crate) {
        super(exitCoordinates, items, map);
        this.escapeCoordinate = new int[]{0, 3};
        this.crate = crate;
    }

    @Override
    protected void createLayout() {
        for (int index = 1; index < 6; index++) {
            for (int innerIndex = 2; innerIndex < 5; innerIndex++) {
                this.getMap()[index][innerIndex] = " ";
            }
        }
    }

    @Override
    protected void populateRoom() {
        for (int[] door : this.getExitCoordinate()) {
            this.getMap()[door[0]][door[1]] = "D";
        }
        this.getMap()[this.escapeCoordinate[0]][this.escapeCoordinate[1]] = "E";
        this.getMap()[this.crate.getObstacleCoordinate()[0]][this.crate.getObstacleCoordinate()[1]] = "O";
        this.getMap()[this.pressPlate[0]][this.pressPlate[1]] = "*";
    }

    public boolean onPressPlate() {
        if (this.getMap()[pressPlate[0]][pressPlate[1]].equals("P")
                || this.getMap()[pressPlate[0]][pressPlate[1]].equals("O")) {
            if (!this.barsRetracted) {
                System.out.println("The bars blocking the doors retract.");
                this.barsRetracted = true;
            }
            return true;
        } else {
            if (this.barsRetracted) {
                System.out.println("Bars extend over the doors, blocking them off.");
                this.barsRetracted = false;
            }
            return false;
        }
    }

    @Override
    public void checkEventTriggers() {
        this.eventTrigger = this.onPressPlate();
    }


}
