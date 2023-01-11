package com.roguelike.fallout.model;

import java.util.Random;

public class NPC {
  private String name;
  private int health;
  private int minAttackPower;
  private int maxAttackPower;
  private static final double DROP_CHANCE_STIMPAK = 1.0;

  public NPC(String name, int health, int minAttackPower, int maxAttackPower) {
    this.name = name;
    this.health = health;
    this.minAttackPower = minAttackPower;
    this.maxAttackPower = maxAttackPower;
    setDropChanceStimPak(DROP_CHANCE_STIMPAK);

  }

  public StimPak dropStimPak(Player player) {

      Random rand = new Random();
      int stimpakDropped = rand.nextInt(3) + 1; // rolls number between 1 and 3

      if (stimpakDropped == 1) {
        int healingAmount = 20;
        StimPak stimPak1 = new StimPak("Stimpak", "Item that heals for" + healingAmount + "health", healingAmount);
        player.addToInventory(stimPak1);
        System.out.println("1 Stimpak added to player inventory");

      } else if (stimpakDropped == 2) {
        int healingAmount = 20;
        StimPak stimPak1 = new StimPak("Stimpak", "Item that heals for" + healingAmount + "health", healingAmount);
        StimPak stimPak2 = new StimPak("Stimpak", "Item that heals for" + healingAmount + "health", healingAmount);
        player.addToInventory(stimPak1);
        player.addToInventory(stimPak2);
        System.out.println("2 Stimpak added to player inventory");

      } else {
        int healingAmount = 20;
        StimPak stimPak1 = new StimPak("Stimpak", "Item that heals for" + healingAmount + "health", healingAmount);
        StimPak stimPak2 = new StimPak("Stimpak", "Item that heals for" + healingAmount + "health", healingAmount);
        StimPak stimPak3 = new StimPak("Stimpak", "Item that heals for" + healingAmount + "health", healingAmount);
        player.addToInventory(stimPak1);
        player.addToInventory(stimPak2);
        player.addToInventory(stimPak3);
        System.out.println("3 Stimpaks added to player inventory");
      }
    return null;
  }

  private void setDropChanceStimPak(double dropChanceStimpak) {
    System.out.println();
  }

    public void npcDialog () {
      System.out.println("\\nYou have encountered a friendly NPC. They give you some helpful information.\"\n"
          + "                    + \"\\n\"");
    }
  }


