package com.roguelike.fallout;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String name = "";
    boolean playing = true;

    while (playing) {
      System.out.println("Welcome to Fallout: Text Adventure game!");
      System.out.print("Enter your character's name: ");
      name = sc.nextLine();

      // Start the game
      int health = 100;
      boolean alive = true;
      while (alive) {
        System.out.println("\n" + name + ", you are currently in a dangerous forest.");
        System.out.println("Your health is at " + health + "%.");
        System.out.println("What would you like to do?");
        System.out.println("1. Explore the forest");
        System.out.println("2. Rest at the campsite");
        System.out.println("3. Quit the game");

        int choice = sc.nextInt();
        if (choice == 1) {
          // Explore the forest
          int randomEncounter = (int) (Math.random() * 100);
          if (randomEncounter < 30) {
            // Encounter a friendly NPC
            System.out.println(
                "\nYou have encountered a friendly NPC. They give you some helpful information.");
          } else if (randomEncounter < 60) {
            // Encounter a dangerous enemy
            System.out.println("\nYou have encountered a dangerous enemy!");
            System.out.println("What would you like to do?");
            System.out.println("1. Fight the enemy");
            System.out.println("2. Try to run away");

            int fightOrRun = sc.nextInt();
            if (fightOrRun == 1) {
              // Fight the enemy
              int attackPower = (int) (Math.random() * 25);
              health -= attackPower;
              System.out.println(
                  "\nYou attack the enemy and deal " + attackPower + " points of damage.");
              System.out.println("The enemy attacks back and deals 5 points of damage.");
              if (health <= 0) {
                alive = false;
                System.out.println("\nOh no! You have been defeated.");
              } else {
                System.out.println("You have " + health + "% health left.");
              }
            } else if (fightOrRun == 2) {
              // Try to run away
              int escapeChance = (int) (Math.random() * 100);
              if (escapeChance < 50) {
                System.out.println("\nYou successfully escape from the enemy.");
              } else {
                System.out.println("\nYou try to run away, but the enemy catches up to you.");
                System.out.println("The enemy attacks and deals 10 points of damage.");
                health -= 10;
                if (health <= 0) {
                  alive = false;
                  System.out.println("\nOh no! You have been defeated.");

                }
              }
            }
          }
        }
      }
    }
  }
}

