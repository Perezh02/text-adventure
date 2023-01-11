package com.roguelike.fallout.menu;

import java.util.Scanner;

public class NameMenu {

  public String getPlayerName(Scanner sc) {
    String name = "";
    System.out.println("Welcome to Fallout: Text Adventure game!");
    System.out.print("Could you tell me your name? (8 characters or shorter): ");
    while (name.isEmpty() || name.length() > 8) {
      name = sc.nextLine().trim();
      if (name.isEmpty()) {
        System.out.println("Sorry, I couldn't see your name!");
      }
      if (name.length() > 8) {
        System.out.println(
            "I'm pretty sure that's more than 8 characters. Do you maybe have a shorter name I can call you?");
      }
    }
    return name;
  }
}
