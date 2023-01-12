package com.roguelike.fallout.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Player {

  // Fields
  private String name;
  private int health;
  private ArrayList<Item> inventory;
  private HashMap<String, Integer> inventoryCount;
  private int maxHealth = 100;
  private boolean isDead;


  // Constructors
  public Player(String name) {
    setName(name);
    setHealth(1);
    isDead = false;
    inventory = new ArrayList<>();
    inventoryCount = new HashMap<>();
  }

  // Attack Methods
  // Randomly generate a number between 0 and 25 as damage to Enemy.
  public int playerAttack() {
    Random rand = new Random();
    int enemyDamage = (int) (Math.random() * 26);
    return enemyDamage;
  }

  public void takeDamage(int enemyAttack) {
    health -= enemyAttack;
    if (health <= 0) {
      isDead = true;
    }
  }

  // Inventory Methods
  public void addToInventory (Item item) {
    inventory.add(item);
    String itemName = item.getItemName();
    int count = inventoryCount.getOrDefault(itemName, 0);
    inventoryCount.put(itemName, count + 1);
  }

  public void removeFromInventory(Item item) {
    String itemName = item.getItemName();
    int count = inventoryCount.getOrDefault(itemName, 0);
    if (count > 0) {
      inventory.remove(item);
      inventoryCount.put(itemName, count - 1);
    } else {
      System.out.println("You don't have any more " + itemName + " in your inventory.");
    }
  }

  public void printInventory() {
    for (Map.Entry<String, Integer> entry : inventoryCount.entrySet()) {
      String itemName = entry.getKey();
      int count = entry.getValue();
      System.out.println(itemName + " x" + count);
    }
  }

  public HashMap<String, Integer> getInventoryCount() {
    return inventoryCount;
  }

  // Heal Method
  public void heal(int healingAmount) {
    health += healingAmount;
    if (health > maxHealth) {
      health = maxHealth;
    }
  }

  // isDead Method
  public boolean isDead() {
    return isDead;
  }

  // Reset Method
  public void reset() {
    setHealth(100);
    isDead = false;
    inventory.clear();
    inventoryCount.clear();
  }

  // Helper Methods

  // Getters and Setters
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

  public ArrayList<Item> getInventory() {
    return inventory;
  }
}