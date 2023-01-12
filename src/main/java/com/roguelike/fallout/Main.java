package com.roguelike.fallout;

import com.roguelike.fallout.menu.BattleMenu;
import com.roguelike.fallout.menu.BossMenu;
import com.roguelike.fallout.menu.ItemMenu;
import com.roguelike.fallout.menu.NameMenu;
import com.roguelike.fallout.model.Boss;
import com.roguelike.fallout.model.Enemy;
import com.roguelike.fallout.model.Locations;
import com.roguelike.fallout.model.NPC;
import com.roguelike.fallout.model.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    Scanner sc = new Scanner(System.in);
    boolean playing = true;
    NameMenu nameMenu = new NameMenu();
    String name = nameMenu.getPlayerName(sc);

    while (playing) {
      if (playing) {
        // Name screen

        // Start the game
        Player player = new Player(name.trim());
        int encounter = 0;
        List<Locations> allLocations = new ArrayList<>(Arrays.asList(Locations.values()));
        List<Locations> visitedLocations = new ArrayList<>();

        if (!playing) {
          break;
        }

        while (encounter != 4 && !player.isDead() && playing) {
          if (visitedLocations.size() == allLocations.size()) {
            visitedLocations.clear();
          }
          Locations randomLocation = allLocations.get((int) (Math.random() * allLocations.size()));
          while (visitedLocations.contains(randomLocation)) {
            randomLocation = allLocations.get((int) (Math.random() * allLocations.size()));
          }
          visitedLocations.add(randomLocation);
          System.out.println(name + randomLocation.getDescription());
          System.out.println("Your health is at " + player.getHealth() + "%.");
          System.out.println("What would you like to do?");
          System.out.println("1. Explore the wilds");
          System.out.println("2. Use an item");
          System.out.println("3. Quit the game");

          int choice;
          while (true) {
            choice = sc.nextInt();
            System.out.println();
            sc.nextLine();
            System.out.println();

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
              NPC npc = new NPC("traveler");
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
                do {
                  System.out.println("Would you like to play again? (yes/no)");
                  String restart = sc.next();
                  System.out.println();
                  if (restart.equalsIgnoreCase("yes")) {
                    player = new Player(name);
                    player.reset();
                    encounter = 0;
                    visitedLocations.clear();
                    break;
                  } else if (restart.equalsIgnoreCase("no")) {
                    System.out.println("Thanks for playing!");
                    playing = false;
                    break;
                  } else {
                    System.out.println("Sorry Invalid input, Please type yes or no");
                  }
                } while (true);
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

        while (playing) {
          if (encounter == 4) {
            //start the boss battle if encounter is 4
            Boss boss = new Boss("Deathclaw", 100, 10);
            System.out.println(boss.getName()
                + ", the apex predator of the wasteland, blocks your path and attacks you.");
            BossMenu bossMenu = new BossMenu(player, boss);
            bossMenu.startBattle();
            if (player.isDead()) {
              do {
                System.out.println("Would you like to play again? (yes/no)");
                String restart = sc.nextLine();
                System.out.println();
                if (!(restart.equalsIgnoreCase("yes") || restart.equalsIgnoreCase("no"))) {
                  System.out.println("Invalid input, please enter 'yes' or 'no'.");
                } else if (restart.equalsIgnoreCase("yes")) {
                  player = new Player(name);
                  player.reset();
                  encounter = 0;
                  visitedLocations.clear();
                  break;
                } else if (restart.equalsIgnoreCase("no")) {
                  System.out.println("Thanks for playing!");
                  playing = false;
                  break;
                }
              } while (true);

            } else {
              do {
                System.out.println("You have defeated " + boss.getName() + "!");
                System.out.println("Congratulations on your victory!");
                System.out.println("Would you like to play again? (yes/no)");
                String restart = sc.next();
                System.out.println();
                if (!(restart.equalsIgnoreCase("yes") || restart.equalsIgnoreCase("no"))) {
                  System.out.println("Invalid input, please enter 'yes' or 'no'.");
                } else if (restart.equalsIgnoreCase("yes")) {
                  player = new Player(name);
                  player.reset();
                  playing = true;
                  encounter = 0;
                  visitedLocations.clear();
                  break;
                } else if (restart.equalsIgnoreCase("no")) {
                  System.out.println("Thanks for playing!");
                  playing = false;
                  break;
                }
              } while (true);
            }
          } else {
            break;
          }
        }
      }
    }
  }
}

