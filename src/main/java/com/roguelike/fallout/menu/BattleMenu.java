package com.roguelike.fallout.menu;

import com.roguelike.fallout.model.Enemy;
import com.roguelike.fallout.model.Player;
import com.roguelike.fallout.model.StimPak;
import java.util.Scanner;

/**
 * BattleMenu class handles the user interaction for an encounter with an enemy.
 */
public class BattleMenu {

  // Fields
  public static final int ATTACK_OPTION = 1;
  public static final int INVENTORY_OPTION = 2;
  public static final int ESCAPE_CHANCE = 50;

  private Player player;
  private Enemy enemy;
  private Scanner sc;

  // Constructor
  public BattleMenu(Player player, Enemy enemy) {
    this.player = player;
    this.enemy = enemy;
    sc = new Scanner(System.in);
  }

  // Methods
  public void startBattle() {
    ItemMenu itemMenu = new ItemMenu(player);
    System.out.println("You encountered a " + enemy.getName() + "!");
    boolean playerTurn = true;

    // While player is alive and enemy is not dead.
    while (!player.isDead() && !enemy.isDead()) {
      if (playerTurn) {
        System.out.println("You have " + player.getHealth() + " health.");
        System.out.println(enemy.getName() + " has " + enemy.getHealth() + " health.");
        System.out.println("What would you like to do?");
        System.out.println("1. Attack");
        System.out.println("2. Inventory");
        System.out.println("3. Run away");

        int choice;
        do {
          choice = sc.nextInt();
          System.out.println();

          // Check if user input is invalid.
          if (choice < 1 || choice > 3) {
            System.out.println("Invalid choice. Please enter a number between 1 and 3.");
          }

        } while (choice < 1 || choice > 3);
        if (choice == ATTACK_OPTION) {
          // Player attacks.
          int damage = player.playerAttack();
          enemy.takeDamage(damage);
          System.out.println("You dealt " + damage + " damage to the " + enemy.getName() + ".");
        } else if (choice == INVENTORY_OPTION) {
          // Access the ItemMenu which allows the player to access their inventory and use items such as StimPaks to restore health.
          itemMenu.displayMenu();
          continue;
        } else {
          int escapeChance = (int) (Math.random() * 100);
          if (escapeChance < ESCAPE_CHANCE) {
            System.out.println("You successfully escaped from the " + enemy.getName() + ".");
            return;
          } else {
            System.out.println(
                "You try to run away, but the " + enemy.getName() + " catches up to you.");
          }
        }
        playerTurn = false;

      } else {
        // Enemy's turn in which enemy will attack the player.
        int enemyAttackDamage = enemy.enemyAttack();
        player.takeDamage(enemyAttackDamage);
        System.out.println(
            "The " + enemy.getName() + " deals " + enemyAttackDamage + " damage to you.");
        playerTurn = true;
      }
      if (player.isDead()) {
        System.out.println("You have been defeated.\n");
        return;
      } else if (enemy.isDead()) {
        System.out.println("The " + enemy.getName() + " has been defeated");
        //StimPak Drop Chance after the enemy is defeated
        StimPak stimpak = (StimPak) enemy.dropStimPak(player);
        if (stimpak != null) {
          System.out.println(
              "The " + enemy.getName() + " dropped a " + stimpak.getItemName() + ".");
          System.out.println(
              "You pick up the " + stimpak.getItemName() + " and put it into your inventory.\n");
        } else {
          System.out.println("The " + enemy.getName() + " did not drop an item.\n");
        }
        return;
      }
    }
  }
}
