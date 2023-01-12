package com.roguelike.fallout.menu;

import com.roguelike.fallout.model.Item;
import com.roguelike.fallout.model.StimPak;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import com.roguelike.fallout.model.Player;

/**
 * BattleMenu class handles the inventory interaction and functionalities.
 */
public class ItemMenu {

  // Fields
  public static final int USE_ITEM_OPTION = 1;
  public static final int RETURN_PREVIOUS_MENU_OPTION = 2;

  private Player player;
  private Scanner sc;

  // Constructor
  public ItemMenu(Player player) {
    this.player = player;
    sc = new Scanner(System.in);
  }

  // Methods
  public boolean displayMenu() {
    boolean returnToPreviousMenu = false;
    System.out.println("Here are the items in your inventory: ");
    if (player.getInventory().isEmpty()) {
      System.out.println("None.");
    } else {
      player.printInventory();
    }

    System.out.println("What would you like to do?");
    System.out.println("1. Use an item");
    System.out.println("2. Return to previous menu");

    int choice = 0;
    while (choice < 1 || choice > 2) {
      try {
        choice = sc.nextInt();
        sc.nextLine();
        System.out.println();
        switch (choice) {
          case USE_ITEM_OPTION:
            useItem();
            break;
          case RETURN_PREVIOUS_MENU_OPTION:
            returnToPreviousMenu = true;
            break;
          default:
            System.out.println("Invalid choice. Please enter a number between 1 and 2.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid choice. Please enter a number between 1 and 2.");
        sc.nextLine();
        System.out.println();
      }
    }
    return returnToPreviousMenu;
  }

  public void useItem() {
    if (player.getInventory().isEmpty()) {
      System.out.println("There are no items to use, the inventory is empty.");
      return;
    }
    System.out.println("Which item would you like to use?");
    // Keep track of already listed items
    HashMap<String, Integer> alreadyListed = new HashMap<String, Integer>();
    // printing out items and their count
    int counter = 1;
    for (Map.Entry<String, Integer> entry : player.getInventoryCount().entrySet()) {
      String itemName = entry.getKey();
      int count = entry.getValue();
      if (!alreadyListed.containsKey(itemName)) {
        alreadyListed.put(itemName, counter);
        System.out.println(counter + ". " + itemName + " x" + count);
        counter++;
      }
    }
    System.out.println(counter + ". Return to menu.");

    // asking for user input for selecting item
    int itemIndex = -1;
    while (itemIndex < 1 || itemIndex > player.getInventoryCount().size() + 1) {
      try {
        String itemChoice = sc.nextLine();
        System.out.println();
        itemIndex = Integer.parseInt(itemChoice);
        if (itemIndex < 1 || itemIndex > player.getInventoryCount().size() + 1) {
          System.out.println("Invalid choice. Please enter a number between 1 and " + (
              player.getInventoryCount().size() + 1) + ".");
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid choice. Please enter a number.");
      }
    }
    if (itemIndex == player.getInventoryCount().size() + 1) {
      return;
    }

    // Finding the item user has selected
    Item selectedItem = null;
    for (Map.Entry<String, Integer> entry : player.getInventoryCount().entrySet()) {
      if (alreadyListed.get(entry.getKey()) == itemIndex) {
        String itemName = entry.getKey();
        for (Item i : player.getInventory()) {
          if (i.getItemName().equals(itemName)) {
            selectedItem = i;
            break;
          }
        }
        break;
      }
    }

    // If item is StimPak then heal player.
    if (selectedItem instanceof StimPak) {
      ((StimPak) selectedItem).useStimPak(player);
      player.removeFromInventory(selectedItem);
    } else {
      System.out.println("This type of item cannot be used.");
    }
  }
}