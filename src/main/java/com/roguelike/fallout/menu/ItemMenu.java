package com.roguelike.fallout.menu;

import com.roguelike.fallout.model.Item;
import com.roguelike.fallout.model.StimPak;
import java.util.Scanner;
import com.roguelike.fallout.model.Player;

public class ItemMenu {

  private Player player;
  private Scanner sc;

  public ItemMenu(Player player) {
    this.player = player;
    sc = new Scanner(System.in);
  }

  public void displayMenu() {
    System.out.println("Here are the items in your inventory: ");
    player.printInventory();

    System.out.println("What would you like to do?");
    System.out.println("1. Use an item");
    System.out.println("2. Return to battle menu");

    int choice = sc.nextInt();

    switch (choice) {
      case 1:
        useItem();
        break;
      case 2:
        return;
      default:
        System.out.println("Invalid choice. Please enter a number between 1 and 2.");
        displayMenu();
        break;
    }
  }

  public void useItem() {
    System.out.println("Which item would you like to use?");
    String itemChoice = sc.nextLine();
    int itemIndex = -1;
    try {
      itemIndex = Integer.parseInt(itemChoice);
    } catch (NumberFormatException e) {
      System.out.println("Invalid choice. Please enter a number.");
      useItem();
      return;
    }
    if (itemIndex < 1 || itemIndex > player.getInventory().size()) {
      System.out.println("Invalid choice. Please enter a number between 1 and " + player.getInventory().size());
      useItem();
      return;
    }
    Item item = player.getInventory().get(itemIndex - 1);
    if (!player.getInventory().contains(item)) {
      System.out.println("This item is not in your inventory.");
      useItem();
      return;
    }
    if (item instanceof StimPak) {
      ((StimPak) item).useStimPak(player);
    } else {
      System.out.println("This type of item cannot be used.");
    }
    return;
  }
}