package com.roguelike.fallout.model;

import java.util.Random;

public class Enemy {

  // Fields
  private String name;
  private int health;
  private double dropChanceStimPak;
  private boolean isDead;
  private int minAttackPower;
  private int maxAttackPower;
  private Random rand;


  // Constructors
  public Enemy(String name, int health, int attackPower, double dropChanceStimPak) {
    this.name = name;
    this.health = health;
    this.dropChanceStimPak = dropChanceStimPak;
    isDead = false;
  }

  public Enemy(String name, int health, double randomDropChance) {
    this.name = name;
    if (name.equals("Skeleton")) {
      this.health = 50;
      this.minAttackPower = 0;
      this.maxAttackPower = 5;
    } else if (name.equals("Ghoul")) {
      this.health = 55;
      this.minAttackPower = 0;
      this.maxAttackPower = 15;
    } else if (name.equals("Rad Roach")) {
      this.health = 40;
      this.minAttackPower = 0;
      this.maxAttackPower = 25;
    }
    this.dropChanceStimPak = randomDropChance;
  }

  // Method
  public static Enemy generateRandomEnemy() {
    String[] enemyNames = {"Skeleton", "Ghoul", "Rad Roach"};
    int randomIndex = (int) (Math.random() * enemyNames.length);
    String randomName = enemyNames[randomIndex];
    double randomDropChance = Math.random();
    int health = 0;
    if (randomName.equals("Skeleton")) {
      health = 50;
      randomDropChance = 0.5;
    } else if (randomName.equals("Ghoul")) {
      health = 55;
      randomDropChance = 0.4;
    } else if (randomName.equals("Rad Roach")) {
      health = 40;
      randomDropChance = 0.3;
    }
    return new Enemy(randomName, health, randomDropChance);
  }

  public int enemyAttack() {
    Random rand = new Random();
    return rand.nextInt((maxAttackPower - minAttackPower) + 1) + minAttackPower;
  }

  public void takeDamage(int enemyDamage) {
    this.health -= enemyDamage;
    if (health <= 0) {
      isDead = true;
    }
  }

  public Object dropStimPak(Player player) {
    if (isDead() && Math.random() < getDropChanceStimPak()) {
      int healingAmount = 20;
      int id = new Random().nextInt();
      StimPak stimPak = new StimPak("StimPak", healingAmount, id);
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

  public void setDead(boolean dead) {
    isDead = dead;
  }

  public int getMinAttackPower() {
    return minAttackPower;
  }

  public void setMinAttackPower(int minAttackPower) {
    this.minAttackPower = minAttackPower;
  }

  public int getMaxAttackPower() {
    return maxAttackPower;
  }

  public void setMaxAttackPower(int maxAttackPower) {
    this.maxAttackPower = maxAttackPower;
  }

  public Random getRand() {
    return rand;
  }

  public void setRand(Random rand) {
    this.rand = rand;
  }
}
