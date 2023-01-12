package com.roguelike.fallout.menu;

import java.util.Scanner;

/**
 * NameMenu class handles the player naming functionalities.
 */
public class NameMenu {

  // Constructor
  public String getPlayerName(Scanner sc) {
    String name = "";
    System.out.println("Welcome to Fallout: Text Adventure game!");
    System.out.print("Could you tell me your name? (8 characters or less): ");
    // Check to see if input is greater than 8 character or input is empty or null.
    while (name.isEmpty() || name.length() > 8) {
      name = sc.nextLine().trim();
      System.out.println();
      if (name.isEmpty()) {
        System.out.println("Sorry, I couldn't see your name!");
      }
      if (name.length() > 8) {
        System.out.println(
            "I'm pretty sure that is more than 8 characters. Do you maybe have a shorter name that I can call you?");
      }
    }
    return name;
  }
}
