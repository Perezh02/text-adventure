package com.roguelike.fallout.model;

public class StimPak extends Item {

  // Fields
  private int healingAmount = 20;
  private int id;

  // Constructors
  public StimPak(String name, int healingAmount, int id) {
    super(name);
    this.healingAmount = Integer.parseInt(String.valueOf(healingAmount));
    this.id = id;
  }

  // Methods

  public void useStimPak(Player player) {
    player.heal(StimPak.this.healingAmount);
    System.out.println(
        "StimPak was used to heal " + StimPak.this.healingAmount + " health points.");
  }

  // Setters and Getters
  public int getHealingAmount() {
    return healingAmount;
  }

  public void setHealingAmount(int healingAmount) {
    this.healingAmount = healingAmount;
  }

  public int getId() {
    return id;
  }

  // To String
  @Override
  public String toString() {
    return super.toString() + " (Heals for " + healingAmount + ")";
  }

}