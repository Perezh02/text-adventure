package com.roguelike.fallout.model;

public class Enemy {

  // Fields
  private String name;
  private int health;
  private int attackPower;
  private double dropChanceStimPak;
  private boolean isDead;


  // Constructors
  public Enemy(String name, int health, int attackPower, double dropChanceStimPak) {
    this.name = name;
    this.health = health;
    this.attackPower = attackPower;
    this.dropChanceStimPak = dropChanceStimPak;
    isDead = false;
  }

  public Enemy(String name) {
    this.name = name;
    if (name.equals("Skeleton")) {
      this.health = 50;
      this.attackPower = (int) (Math.random() * 6);
    } else if (name.equals("Ghoul")) {
      this.health = 55;
      this.attackPower = (int) (Math.random() * 15 + 1);
    } else if (name.equals("Rad Roach")) {
      this.health = 40;
      this.attackPower = (int) (Math.random() * 26);
    }
  }

  // Method
  public static Enemy generateRandomEnemy() {
    String[] enemyNames = {"Skeleton", "Ghoul", "Rad Roach"};
    int randomIndex = (int) (Math.random() * enemyNames.length);
    String randomName = enemyNames[randomIndex];
    double randomDropChance = Math.random();
    int health = 0;
    int attackPower = 0;
    if (randomName.equals("Skeleton")) {
      health = 50;
      attackPower = (int) (Math.random() * 6);
    } else if (randomName.equals("Ghoul")) {
      health = 55;
      attackPower = (int) (Math.random() * 16);
    } else if (randomName.equals("Rad Roach")) {
      health = 40;
      attackPower = (int) (Math.random() * 26);
    }
    return new Enemy(randomName, health, attackPower, randomDropChance);
  }

  public void takeDamage(int enemyDamage) {
    this.health -= enemyDamage;
    if (health <= 0) {
      isDead = true;
    }
  }

  public StimPak dropStimPak(Player player) {
    if (isDead() && Math.random() < dropChanceStimPak) {
      int healingAmount = 20;
      StimPak stimPak = new StimPak("StimPak", "Item that heals for " + healingAmount + " health", healingAmount);
      player.addToInventory(stimPak);
      return stimPak;
    } else {
      return null;
    }
  }

  // Getters and Setters

  public double getDropChanceStimPak() {
    return dropChanceStimPak;
  }

  public void setDropChanceStimPak(double dropChanceStimPak) {
    this.dropChanceStimPak = dropChanceStimPak;
  }

  public int getAttackPower() {
    return this.attackPower;
  }

  public boolean isDead() {
    return this.health <= 0;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public void setAttackPower(int attackPower) {
    this.attackPower = attackPower;
  }

  public void setDead(boolean dead) {
    isDead = dead;
  }

}
