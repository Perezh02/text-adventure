package com.roguelike.fallout.menu;

import com.roguelike.fallout.model.Enemy;
import com.roguelike.fallout.model.Player;
import com.roguelike.fallout.model.StimPak;
import java.util.Scanner;

public class BattleMenu {

  private Player player;
  private Enemy enemy;
  private Scanner sc;

  public BattleMenu(Player player, Enemy enemy) {
    this.player = player;
    this.enemy = enemy;
    sc = new Scanner(System.in);
  }

  public void startBattle() {
    Enemy enemy = Enemy.generateRandomEnemy();
    ItemMenu itemMenu = new ItemMenu(player);
    System.out.println(player.getName() + " encountered a " + enemy.getName() + "!");
    System.out.println(enemy.getName() + " has " + enemy.getHealth() + " health.");
    System.out.println(player.getName() + " has " + player.getHealth() + " health.");

    boolean playerTurn = true;

    while (!player.isDead() && !enemy.isDead()) {
      if (playerTurn) {
        System.out.println("What would you like to do?");
        System.out.println("1. Attack");
        System.out.println("2. Use an item");
        System.out.println("3. Run away");

        int choice;
        do {
          System.out.println("Enter your choice (1-3): ");
              choice = sc.nextInt();
          if (choice < 1 || choice > 3) {
            System.out.println(" Invalid choice. Please enter a number between 1 and 3.");
          }
        } while (choice < 1 || choice > 3);

        if (choice == 1) {
          // Player attacks.
          int damage = player.playerAttack();
          enemy.takeDamage(damage);
          System.out.println(player.getName() + " has dealt " + damage + " damage to the enemy.");
        } else if (choice == 2) {
          //  Player uses an item.
          itemMenu.displayMenu();
        } else if (choice == 3) {
          int escapeChance = (int) (Math.random() * 100);
          if (escapeChance < 50) {
            System.out.println(player.getName() + " successfully escaped from the enemy.");
            return;
          } else {
            System.out.println("You try to run away, but the enemy catches up to you.");
            int enemyAttackDamage = enemy.getAttackPower();
            player.takeDamage(enemyAttackDamage);
            System.out.println(
                "The enemy deals " + enemyAttackDamage + " damage to " + player.getName() + ".");
          }
        }
        playerTurn = false;
      } else {
        // Enemy's turn
        int enemyAttackDamage = enemy.getAttackPower();
        player.takeDamage(enemyAttackDamage);
        System.out.println(
            "The enemy deals " + enemyAttackDamage + " damage to " + player.getName() + ".");
        playerTurn = true;
      }
      if (player.isDead()) {
        System.out.println("You have been defeated.");
        return;
      } else if (enemy.isDead()) {
        System.out.println("The enemy has been defeated");
        StimPak stimpak = enemy.dropStimPak(player);
        //StimPak Drop Chance
        if (stimpak != null) {
          System.out.println("The enemy dropped a " + stimpak.getItemName() + ".");
        } else {
          System.out.println("The enemy didn't drop any item");
        }
        return;
      }
    }
  }
}
