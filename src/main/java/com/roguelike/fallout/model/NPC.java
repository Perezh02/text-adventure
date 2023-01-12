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
        StimPak stimPak1 = new StimPak("StimPak", healingAmount, 1);
        player.addToInventory(stimPak1);
        System.out.println("1 StimPak added to player inventory.\n");

      } else if (stimPakdropped == 2) {
        int healingAmount = 20;
        StimPak stimPak1 = new StimPak("StimPak", healingAmount, 1);;
        StimPak stimPak2 = new StimPak("StimPak", healingAmount, 1);;
        player.addToInventory(stimPak1);
        player.addToInventory(stimPak2);
        System.out.println("2 StimPak added to player inventory.\n");

      } else {
        int healingAmount = 20;
        StimPak stimPak1 = new StimPak("StimPak", healingAmount, 1);;
        StimPak stimPak2 = new StimPak("StimPak", healingAmount, 1);;
        StimPak stimPak3 = new StimPak("StimPak", healingAmount, 1);;
        player.addToInventory(stimPak1);
        player.addToInventory(stimPak2);
        player.addToInventory(stimPak3);
        System.out.println("3 StimPaks added to your inventory.\n");
      }
    return null;
  }

  public void npcDialog () {
    System.out.println("You have encountered a friendly " + getName() +". They give you some helpful information.");
  }

  private void setDropChanceStimPak() {
    System.out.println();
  }

  public String getName() {
    return name;
  }
}


