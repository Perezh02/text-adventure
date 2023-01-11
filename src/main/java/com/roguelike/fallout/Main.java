package com.roguelike.fallout;

import com.roguelike.fallout.menu.BattleMenu;
import com.roguelike.fallout.menu.BossMenu;
import com.roguelike.fallout.menu.ItemMenu;
import com.roguelike.fallout.menu.NameMenu;
import com.roguelike.fallout.model.Boss;
import com.roguelike.fallout.model.Enemy;
import com.roguelike.fallout.model.NPC;
import com.roguelike.fallout.model.Player;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    boolean playing = true;
    NameMenu nameMenu = new NameMenu();
    String name = nameMenu.getPlayerName(sc);

    while (playing == true) {
      // Name screen

      // Start the game
      Player player = new Player(name.trim());
      int encounter = 0;
      while (encounter != 4) {
        // TODO: 1/9/2023 Add Location Enum that randomizes. 
        System.out.println(name + ", you are currently in a dangerous forest.");
        System.out.println("Your health is at " + player.getHealth() + "%.");
        System.out.println("What would you like to do?");
        System.out.println("1. Explore the wilds");
        System.out.println("2. Use an item");
        System.out.println("3. Quit the game");

        int choice;
        while (true) {
          choice = sc.nextInt();
          sc.nextLine();
          if (choice >= 1 && choice <= 3) {
            break;
          }
          System.out.println("Invalid Input, please enter a number between 1 and 3");
        }
        if (choice == 1) {
          // Explore the wilds
          int randomEncounter = (int) (Math.random() * 100);
          if (randomEncounter <= 35) {
            // Encounter a friendly NPC
            NPC npc = new NPC("NPC");
            npc.npcDialog();
            npc.dropStimPak(player);
            encounter++;
          } else {
            // Encounter a dangerous enemy
            BattleMenu battleMenu = new BattleMenu(player, Enemy.generateRandomEnemy());
            battleMenu.startBattle();
            encounter++;
            // Death of player and option to restart
            if (player.isDead() || player.getHealth() <= 0) {
              encounter = 0;
              System.out.println("Would you like to play again? (yes/no)");
              String restart = sc.next();
              if (restart.equalsIgnoreCase("yes")) {
                player = new Player(name);
                player.reset();
              } else if (restart.equalsIgnoreCase("no")) {
                System.out.println("Thanks for playing!");
                playing = false;
                break;
              } else {
                System.out.println("Sorry Invalid input, Please type yes or no");
              }
            }
          }
        } else if (choice == 2) {
          // Use an item.
          ItemMenu itemMenu = new ItemMenu(player);
          itemMenu.displayMenu();
        } else if (choice == 3) {
          System.out.println("Thanks for playing!");
          playing = false;
          break;
        }
      }

      //start the boss battle if encounter is 4
      Boss boss = new Boss("Deathclaw", 100, 10);
      System.out.println(boss.getName()
          + ", the apex predator of the wasteland, blocks your path and attacks you.");
      BossMenu bossMenu = new BossMenu(player, boss);
      bossMenu.startBattle();
      if (player.isDead()) {
        System.out.println("Would you like to play again? (yes/no)");
        String restart = sc.nextLine();
        if (restart.equalsIgnoreCase("yes")) {
          player = new Player(name);
          player.reset();
        } else if (restart.equalsIgnoreCase("no")) {
          System.out.println("Thanks for playing!");
          break;
        } else {
          System.out.println("Sorry Invalid input, Please type yes or no");
        }
      } else {
        System.out.println("You have defeated " + boss.getName() + "!");
        System.out.println("Congratulations on your victory!");
        System.out.println("Would you like to play again? (yes/no)");
        String restart = sc.next();
        if (restart.equalsIgnoreCase("yes")) {
          player = new Player(name);
          player.reset();
        } else if (restart.equalsIgnoreCase("no")) {
          System.out.println("Thanks for playing!");
          break;
        } else {
          System.out.println("Sorry Invalid input, Please type yes or no");
        }
      }
    }
  }
}

