package com.roguelike.fallout.menu;

import com.roguelike.fallout.model.Boss;
import com.roguelike.fallout.model.Player;
import java.util.Scanner;

/**
 * BossMenu class handles the user interaction for an encounter with a boss.
 */
public class BossMenu {

  public static final int ATTACK_OPTION = 1;
  // Fields
  private Player player;
  private Boss boss;
  private Scanner sc;

  // Constructors
  public BossMenu(Player player, Boss boss) {
    this.player = player;
    this.boss = boss;
    sc = new Scanner(System.in);
  }

  // Method to start the battle
  public void startBattle() {

    // Created an object of ItemMenu class
    ItemMenu itemMenu = new ItemMenu(player);

    boolean playerTurn = true;

    // While loop that continues until either the player or the boss is dead
    while (!player.isDead() && !boss.isDead()) {
      if (playerTurn) {
        System.out.println("You have " + player.getHealth() + " health.");
        System.out.println(boss.getName() + " has " + boss.getHealth() + " health.");
        System.out.println("What would you like to do?");
        System.out.println("1. Attack");
        System.out.println("2. Inventory");
        // do-while loop that prompts user for an input between 1 and 2
        int choice;
        do {
          choice = sc.nextInt();
          System.out.println();
          if (choice < 1 || choice > 2) {
            System.out.println(" Invalid choice. Please enter a number between 1 and 2.");
          }
        } while (choice < 1 || choice > 2);

        if (choice == ATTACK_OPTION) {
          // Player attacks.
          int damage = player.playerAttack();
          boss.takeDamage(damage);
          System.out.println("You dealt " + damage + " damage to " + boss.getName() + ".");
        } else {
          //  Player uses an item.
          itemMenu.displayMenu();
          continue;
        }
        // Set playerTurn to false to indicate it is now the boss's turn
        playerTurn = false;
      } else {
          // Boss's turn
          int bossAttackPower = boss.getAttackPower();
          player.takeDamage(bossAttackPower);
          System.out.println(boss.getName() + " deals " + bossAttackPower + " damage to you.");
          playerTurn = true;
        }
      if (player.isDead()) {
        System.out.println("You were defeated. \n");
        return;
      } else if (boss.isDead()) {
        System.out.println(boss.getName() + " has been defeated");
      }
    }
  }
}



