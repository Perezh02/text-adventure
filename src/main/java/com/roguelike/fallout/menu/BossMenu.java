package com.roguelike.fallout.menu;
import com.roguelike.fallout.model.Boss;
import com.roguelike.fallout.model.Player;
import java.util.Scanner;

public class BossMenu {

    private Player player;
    private Boss boss;
    private Scanner sc;

    public BossMenu(Player player, Boss boss) {
      this.player = player;
      this.boss = boss;
      sc = new Scanner(System.in);
    }

    public void startBattle() {
      ItemMenu itemMenu = new ItemMenu(player);
      System.out.println(player.getName() + " encountered a " + boss.getName() + "!");
      System.out.println(boss.getName() + " has " + boss.getHealth() + " health.");
      System.out.println(player.getName() + " has " + player.getHealth() + " health.");

      boolean playerTurn = true;

      while (!player.isDead() && !boss.isDead()) {
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
            boss.takeDamage(damage);
            System.out.println(player.getName() + " has dealt " + damage + " damage to the boss.");
          } else if (choice == 2) {
            //  Player uses an item.
            itemMenu.displayMenu();
          } else if (choice == 3) {
            System.out.println();
              System.out.println(player.getName() + " successfully escaped from the boss.");
              return;
          }
          playerTurn = false;
        } else {
          // Boss's turn
          int bossAttackDamage = boss.getAttack();
          player.takeDamage(bossAttackDamage);
          System.out.println(
              "The boss deals " + bossAttackDamage + " damage to " + player.getName() + ".");
          playerTurn = true;
        }
        if (player.isDead()) {
          System.out.println("You have been defeated.");
          return;
        } else if (boss.isDead()) {
          System.out.println("The boss has been defeated");
        }
      }
    }
  }


