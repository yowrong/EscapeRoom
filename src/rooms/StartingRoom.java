package rooms;

import theitems.Item;
import theplayer.Player;

import java.util.ArrayList;

public class StartingRoom extends Room {
    private int[][] exitCoordinate;
    private ArrayList<Item> itemsInRoom;
    private String[][] StartingRoom;
    private int[] torchPlace = {1, 5};


    public StartingRoom(int[][] exitCoordinates, ArrayList<Item> items, String[][] map) {
        super(exitCoordinates, items, map);
        this.exitCoordinate = exitCoordinates;
        this.itemsInRoom = items;
        this.StartingRoom = map;
    }

    @Override
    protected void createLayout() {
        for (int index = 1; index < 6; index++) {
            for (int innerIndex = 1; innerIndex < 6; innerIndex++) {
                if(innerIndex > 3 || index > 3){
                    this.StartingRoom[index][innerIndex] = " ";
                }
            }
        }
    }


    public void addItemToRoom(Item itemToDrop, int[] currentPosition) {
        this.itemsInRoom.add(itemToDrop);
        itemToDrop.setItemCoordinate(currentPosition);
    }

    protected void populateRoom() {
        for (Item torch : itemsInRoom) {
            this.StartingRoom[torch.getItemCoordinate()[0]][torch.getItemCoordinate()[1]] = "T";
        }
        this.getExitCoordinate();

        //Real door
        this.StartingRoom[this.getExitCoordinate()[0][0]][this.getExitCoordinate()[0][1]] = "D";

//        this.StartingRoom[(this.getExitCoordinate()[0][0]) + 1][this.getExitCoordinate()[0][1]] = "P";

        //EXPERIMENTAL -- This is where player spawns, Door behind starting point should be dysfunctional.
        //User should be prompted something along the lines "door is shut with a magical force"

        //Locked door
        this.StartingRoom[(this.getExitCoordinate()[0][0]) + 4][this.getExitCoordinate()[0][1] - 4] = "D";
        this.StartingRoom[(this.getExitCoordinate()[0][0]) + 4][this.getExitCoordinate()[0][1] - 3] = "P";
        this.StartingRoom[this.getExitCoordinate()[0][0] + 1][this.getExitCoordinate()[0][1] + 1] = "H";
    }

    public void torchLever(Player player) {
        for (Item itemInInventory : player.getInventory()) {

            if (player.getPlayerCoordinates() == torchPlace && itemInInventory.getItemName() == "torch") {
                //Player places item.
            }
        }
    }


    public static void main(final String[] args) {
        int[][] testExitCoord = {{0, 4}};
        int[] testItemCoord = {5, 3};
        String testItemName = "A torch";
        String testItemDesc = "It's a torch... Seems to light the way";

        Item torch = new Item(testItemCoord, testItemName, testItemDesc);
        ArrayList<Item> testItemsInRoom = new ArrayList<Item>();
        String[][] testMap = new String[7][7];
        for (int index = 0; index < 7; index++) {
            for (int innerIndex = 0; innerIndex < 7; innerIndex++) {
                testMap[index][innerIndex] = "U";
            }
        }
//        Room testRoom = new Room(testExitCoord, testItemsInRoom, testMap);
//        testRoom.deleteLayout();
//        testRoom.createLayout();
//        testRoom.displayLayout();

        StartingRoom testStartingRoom = new StartingRoom(testExitCoord, testItemsInRoom, testMap);
        testStartingRoom.getItemsInRoom().add(torch);
        testStartingRoom.deleteLayout();
        testStartingRoom.createLayout();
//        testStartingRoom.displayLayout();
        testStartingRoom.populateRoom();

        //Room desc
        System.out.println();

        testStartingRoom.displayLayout();

    }
}
