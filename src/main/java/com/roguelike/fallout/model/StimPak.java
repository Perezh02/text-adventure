package com.roguelike.fallout.model;

public class StimPak extends Item {

  // Fields
  private int healingAmount;

  // Constructors
  public StimPak(String name, String description, int healingAmount) {
    super(name, description);
    this.healingAmount = healingAmount;
  }

  // Methods

  public void useStimPak(Player player) {
    if (player.getInventory().contains(this)) {
      player.removeFromInventory(this);
      player.heal(healingAmount);
      System.out.println("StimPak was used to heal "+ healingAmount + " health points.");
    } else {
      System.out.println("You don't have any StimPaks in your inventory.");
    }
  }

  // Setters and Getters
  public int getHealingAmount() {
    return healingAmount;
  }

  public void setHealingAmount(int healingAmount) {
    this.healingAmount = healingAmount;
  }

  // To String
  @Override
  public String toString() {
    return super.toString() + " (Heals for " + healingAmount + ")";
  }

}
