package com.roguelike.fallout.model;

public class Boss {

  // Fields
  private String name;
  private int health;
  private int attackPower;
  private int radiation;


  // Constructors
  public Boss(String name, int health, int attackPower, int radiation) {
    this.name = name;
    this.health = health;
    this.attackPower = attackPower;
    this.radiation = radiation;

  }

  // Methods
  public Boss(String name) {
    this.name = name;
    if (name.equals("Deathclaw")) {
      this.health = 100;
      this.attackPower = (int) (Math.random() * 25);
    }

  }

  public void takeDamage(int damage) {
    this.health -= damage;
  }

  public int getAttackPower() {
    return this.attackPower;
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

  public boolean isDead() {
    return this.health <= 0;
  }
}
