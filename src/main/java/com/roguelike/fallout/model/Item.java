package com.roguelike.fallout.model;

public class Item {

  // Fields
  private String itemName;

  // Constructor
  public Item(String itemName) {
    setItemName(itemName);
  }

  // Getters and setters
  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  // To String
  @Override
  public String toString() {
    return itemName;
  }
}