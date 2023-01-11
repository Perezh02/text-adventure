package com.roguelike.fallout.model;

import java.util.Random;

public class NPC {
  private String name;
  private static final double DROP_CHANCE_STIMPAK = 1.0;

  public NPC(String name) {
    this.name = name;
    setDropChanceStimPak();

  }

  public StimPak dropStimPak(Player player) {

      Random rand = new Random();
      int stimPakdropped = rand.nextInt(3) + 1; // rolls number between 1 and 3

      if (stimPakdropped == 1) {
        int healingAmount = 20;
        StimPak stimPak1 = new StimPak("Stimpak", "Item that heals for" + healingAmount + "health", healingAmount);
        player.addToInventory(stimPak1);
        System.out.println("1 Stimpak added to player inventory");

      } else if (stimPakdropped == 2) {
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

  public void npcDialog () {
    System.out.println("You have encountered a friendly NPC. They give you some helpful information.");
  }

  private void setDropChanceStimPak() {
    System.out.println();
  }


  }


