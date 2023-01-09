package com.roguelike.fallout.model;

public class Enemy {

  // Fields
  private String name;
  private int health;
  private int attackPower;
  private double dropChance;
  private int radiation;

  // Constructors
  public Enemy(String name, int health, int attackPower, double dropChance, int radiation) {
    this.name = name;
    this.health = health;
    this.attackPower = attackPower;
    this.dropChance = dropChance;
    this.radiation = radiation;
  }

  // Methods
  public Enemy(String name) {
    this.name = name;
    if (name.equals("Skeleton")) {
      this.health = 50;
      this.attackPower = (int) (Math.random() * 6);
    } else if (name.equals("Ghoul")) {
      this.health = 55;
      this.attackPower = (int) (Math.random() * 15);
    } else if (name.equals("Rad Roach")) {
      this.health = 40;
      this.attackPower = (int) (Math.random() * 25);
    }
  }

  public void setRadiation(int radiation) {
    this.radiation = radiation;
  }

  public int getRadiation() {
    return this.radiation;
  }

  public void increasedRadiation(int amount) {
    this.radiation += amount;
  }

  public void increaseRadiation(int amount) {
    this.radiation -= amount;
  }

  public void takeDamage(int damage) {
    this.health -= damage;
  }

  public void setDropChance(double dropChance) {
    this.dropChance = dropChance;
  }

  public int getAttackPower() {
    return this.attackPower;
  }

  public boolean isDead() {
    return this.health <= 0;
  }

}