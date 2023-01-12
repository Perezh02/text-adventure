package com.roguelike.fallout.menu;

import com.roguelike.fallout.model.Boss;
import com.roguelike.fallout.model.Enemy;
import com.roguelike.fallout.model.Locations;
import com.roguelike.fallout.model.NPC;
import com.roguelike.fallout.model.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * GameManager class handles the game loop and all the game functionalities.
 */
public class GameManager {


  //Fields
  public static final int EXPLORE_OPTION = 1;
  public static final int INVENTORY_OPTION = 2;
  public static final int ENCOUNTER_TO_BOSS_BATTLE = 4;
  public static final int RANDOM_ENCOUNTER = 35;
  public static final int BOSS_HEALTH = 100;
  public static final int BOSS_ATTACK_POWER = 30;
  public static final String BOSS_NAME = "Deathclaw";

  // Scanner to get user input.
  Scanner sc = new Scanner(System.in);
  // flag to check if the game is still running.
  boolean playing = true;
  // instance of NameMenu class to get player's name.
  NameMenu nameMenu = new NameMenu();
  String name = nameMenu.getPlayerName(sc);

  /**
   * startGame method initiates the game loop and all the other functionalities of the game
   */
  public void startGame() {

    // Game loop
    while (playing) {
      // Check if the game is still running.
      if (playing) {
        // Start the game and create a new player.
        Player player = new Player(name.trim());
        int encounter = 0;
        // Create a list of all possible locations.
        List<Locations> allLocations = new ArrayList<>(Arrays.asList(Locations.values()));
        // Create a list of visited locations.
        List<Locations> visitedLocations = new ArrayList<>();

        if (!playing) {
          break;
        }

        // Game loop
        while (encounter != 4 && !player.isDead() && playing) {
          // If all locations have been visited, clear the list.
          if (visitedLocations.size() == allLocations.size()) {
            visitedLocations.clear();
          }
          // Select a random location from all available locations.
          Locations randomLocation = allLocations.get((int) (Math.random() * allLocations.size()));
          // Make sure the location has not been visited before.
          while (visitedLocations.contains(randomLocation)) {
            randomLocation = allLocations.get((int) (Math.random() * allLocations.size()));
          }
          // Add the location to the list of visited locations.
          visitedLocations.add(randomLocation);
          // Print the options.
          System.out.println(name + randomLocation.getDescription());
          System.out.println("Your health is at " + player.getHealth() + "%.");
          System.out.println("What would you like to do?");
          System.out.println("1. Explore the wilds");
          System.out.println("2. Inventory");
          System.out.println("3. Quit the game");

          int choice;
          while (true) {
            // Get player choice.
            choice = sc.nextInt();
            System.out.println();
            sc.nextLine();
            System.out.println();
            // Validate player choice.
            if (choice >= 1 && choice <= 3) {
              break;
            }
            System.out.println("Invalid Input, please enter a number between 1 and 3");
          }
          if (choice == EXPLORE_OPTION) {
            // Explore the wilds, player will randomly encounter either a friendly NPC or a dangerous enemy.
            int randomEncounter = (int) (Math.random() * 100);
            if (randomEncounter <= RANDOM_ENCOUNTER) {
              // Encounter a friendly NPC
              NPC npc = new NPC("traveler");
              npc.npcDialog();
              npc.dropStimPak(player);
              encounter++;
            } else {
              // Encounter a dangerous enemy.
              // Create new BattleMenu.
              BattleMenu battleMenu = new BattleMenu(player, Enemy.generateRandomEnemy());
              // Invokes startBattle() which initializes the battle sequence.
              battleMenu.startBattle();
              encounter++;
              // Death of player and option to restart.
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
          } else if (choice == INVENTORY_OPTION) {
            // Use an item.
            // Access the ItemMenu which allows the player to access their inventory and use items such as StimPaks to restore health.
            ItemMenu itemMenu = new ItemMenu(player);
            itemMenu.displayMenu();
          } else {
            // Quit the game
            // This option allows the player to end the game and exit the while loop.
            System.out.println("Thanks for playing!");
            playing = false;
            break;
          }
        }

        //Boss Loop.
        while (playing) {
          if (encounter == ENCOUNTER_TO_BOSS_BATTLE) {
            //start the boss battle if encounter is 4
            Boss boss = new Boss(BOSS_NAME, BOSS_ATTACK_POWER);
            System.out.println(boss.getName()
                + ", the apex predator of the wasteland, blocks your path and attacks you.");
            BossMenu bossMenu = new BossMenu(player, boss);
            bossMenu.startBattle();
            // check if player is dead and ask for restart
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
              // If player defeats the boss.
              System.out.println("Congratulations on your victory!");
              do {
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

