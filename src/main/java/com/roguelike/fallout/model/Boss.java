package com.roguelike.fallout.model;

public class Boss {

  // Fields
  private final String name;
  private int health;
  private int maxAttackPower;

  // Constructors
  public Boss(String name, int maxAttackPower) {
    this.name = name;
    this.health = 100;
    this.maxAttackPower = maxAttackPower;
  }

  public void takeDamage(int damage) {
    this.health -= damage;
  }

  public int getAttackPower() {
    return (int)(Math.random() * maxAttackPower);
  }

  public int getHealth() {
    return health;
  }

  public String getName() {
    return name;
  }

  public boolean isDead() {
    return this.health <= 0;
  }
}