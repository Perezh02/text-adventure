package com.roguelike.fallout.model;

public class Item {

  // Fields
  private String itemName;
  private String itemDescription;

  // Constructor
  public Item(String itemName, String itemDescription) {
    this.itemName = itemName;
  }

  // Methods


  // Getters and setters

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public String getItemDescription() {
    return itemDescription;
  }

  public void setItemDescription(String itemDescription) {
    this.itemDescription = itemDescription;
  }

  // To String

  @Override
  public String toString() {
    return itemName + ": " + itemDescription;
  }

}







