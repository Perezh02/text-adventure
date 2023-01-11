package com.roguelike.fallout.model;


import java.util.ArrayList;


public class Boss {

  // Fields
  private final String name;
  private int health;
  private ArrayList<Attack> attacks;

  // Constructors
  public Boss(String name, int health, int attackPower) {
    this.name = name;
    this.health = 100;
    this.attacks = new ArrayList<Attack>();
    if (name.equals("Deathclaw")) {
      int maxRoll = 5;
      int clawSwipeAttackPower = 20 + (int)(Math.random() * maxRoll);
      int tailWhipAttackPower = 15 + (int)(Math.random() * maxRoll);
      this.attacks.add(new Attack("Claw swipe", clawSwipeAttackPower));
      this.attacks.add(new Attack("Tail whip", tailWhipAttackPower));
    }
  }

  public void useAttack(int attackIndex, Player target) {
    if (attackIndex < this.attacks.size()) {
      this.attacks.get(attackIndex).attack(target);
    }
  }

  static class Player {

    private int health;

    public Player(int health) {
      this.health = health;
    }

    public void takeDamage(int damage) {
      this.health -= damage;
    }

    public int getHealth() {
      return this.health;
    }

    public boolean isDead() {
      return this.health <= 0;
    }
  }

  class Attack {

    private String name;
    private int attackPower;

    public Attack(String name, int attackPower) {
      this.name = name;
      this.attackPower = attackPower;
    }

    public void attack(Player target) {
      target.takeDamage(this.attackPower);
    }
  }

  public void takeDamage(int damage) {
    this.health -= damage;
  }

  public int getAttack() {
    return this.attacks.get(0).attackPower;
  }

  public int getHealth() {
    return health;
  }

  public String getName() {
    return name;
  }

  public ArrayList<Attack> getAttacks() {
    return attacks;
  }

  public boolean isDead() {
    return this.health <= 0;
  }
}
