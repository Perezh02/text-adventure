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

      boolean playerTurn = true;

      while (!player.isDead() && !boss.isDead()) {
        if (playerTurn) {
          System.out.println("You have " + player.getHealth() + " health.");
          System.out.println(boss.getName() + " has " + boss.getHealth() + " health.");
          System.out.println("What would you like to do?");
          System.out.println("1. Attack");
          System.out.println("2. Use an item");
          System.out.println("3. Run away");

          int choice;
          do {
            choice = sc.nextInt();
            System.out.println();
            if (choice < 1 || choice > 3) {
              System.out.println(" Invalid choice. Please enter a number between 1 and 3.");
            }
          } while (choice < 1 || choice > 3);

          if (choice == 1) {
            // Player attacks.
            int damage = player.playerAttack();
            boss.takeDamage(damage);
            System.out.println("You dealt " + damage + " damage to " + boss.getName() + ".");
          } else if (choice == 2) {
            //  Player uses an item.
            itemMenu.displayMenu();
            continue;
          } else if (choice == 3) {
            System.out.println();
              System.out.println("You are unable to escape as " + boss.getName() + " is blocking the escape route");
          }
          playerTurn = false;
        } else {
          // Boss's turn
          int bossAttackDamage = boss.getAttack();
          player.takeDamage(bossAttackDamage);
          System.out.println(boss.getName() + " deals " + bossAttackDamage + " damage to you.");
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


