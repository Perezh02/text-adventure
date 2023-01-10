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


  // Constructors
  // TODO: 1/9/23 Pass the players name from the main class to the player constructor. Add the following to main: Player player = new Player(name);
  public Player(String name) {
    setName(name);
    setHealth(100);
    inventory = new ArrayList<>();
    inventoryCount = new HashMap<>();
  }

  // Attack Methods
  // Randomly generate a number between 0 and 25 as damage to Enemy.
  // TODO: 1/9/23 Utilize enemyDamage to inflict damage to enemy health during battle sequence.
  public int playerAttack() {
    Random rand = new Random();
    int enemyDamage = (int) (Math.random() * 26);
    return enemyDamage;
  }

  // TODO: 1/9/23 Utilize this method to take enemyAttack method from Enemy class to inflict damage to player health.
  public void takeDamage(int enemyAttack) {
    health -= enemyAttack;
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
      System.out.println("You don't have ny more " + itemName + " in your inventory.");
    }
  }

  public void printInventory() {
    for (Map.Entry<String, Integer> entry : inventoryCount.entrySet()) {
      String itemName = entry.getKey();
      int count = entry.getValue();
      System.out.println(count + "x " + itemName);
    }
  }

  // Heal Method
  public void heal(int healingAmount) {
    health += healingAmount;
    if (health > maxHealth) {
      health = maxHealth;
    }
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
