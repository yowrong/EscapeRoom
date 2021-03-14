package theplayer;

import rooms.Room;
import theitems.Item;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    private String playerName;
    private Room currentRoom;
    private ArrayList<Item> inventory;
    private int[] playerCoordinates;

    public Player(String playerName, Room currentRoom, int[] playerCoordinates) {
        this.playerName = playerName;
        this.currentRoom = currentRoom;
        this.playerCoordinates = playerCoordinates;
        this.inventory = new ArrayList<>();
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public String getPlayerName() {
        return playerName;
    }

    public ArrayList<Item> getInventory() {
        return this.inventory;
    }

    public int[] getPlayerCoordinates() {
        return playerCoordinates;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void playerMove(String direction) {
        int[] playerOldPos = this.playerCoordinates;

        if (direction.equals("north") && !currentRoom.getMap()[playerOldPos[0] - 1][playerOldPos[1]].equals("X")) {
            this.playerCoordinates[0] -= 1;
            currentRoom.updatePlayerPos(playerOldPos, this.playerCoordinates);
        }

        else if (direction.equals("south") && !currentRoom.getMap()[playerOldPos[0] + 1][playerOldPos[1]].equals("X")) {
            this.playerCoordinates[0] += 1;
            currentRoom.updatePlayerPos(playerOldPos, this.playerCoordinates);
        }

        else if (direction.equals("west") && !currentRoom.getMap()[playerOldPos[0]][playerOldPos[1] - 1].equals("X")) {
            this.playerCoordinates[1] -= 1;
            currentRoom.updatePlayerPos(playerOldPos, this.playerCoordinates);
        }

        else if (direction.equals("east") && !currentRoom.getMap()[playerOldPos[0]][playerOldPos[1] + 1].equals("X")) {
            this.playerCoordinates[1] += 1;
            currentRoom.updatePlayerPos(playerOldPos, this.playerCoordinates);
        }

        else {
            System.out.println("There's a wall there");
        }
    }

    public void placeItem(String itemName) {
        for (Item itemInInventory : inventory) {
            if (itemName.equals(itemInInventory.getItemName())) {
                itemInInventory.setItemCoordinate(this.getPlayerCoordinates());
                currentRoom.addItemToRoom(itemInInventory, itemInInventory.getItemCoordinate());
                inventory.remove(itemInInventory);
            }
        }
    }

    public void pickUpItem() {
        Item itemToRemove = null;
        for (Item itemInRoom : currentRoom.getItemsInRoom()) {
            if (Arrays.equals(this.getPlayerCoordinates(), itemInRoom.getItemCoordinate())) {
                this.getInventory().add(itemInRoom);
                itemToRemove = itemInRoom;
                System.out.println("Ye");
            }
        }

        currentRoom.getItemsInRoom().remove(itemToRemove);
    }
}
