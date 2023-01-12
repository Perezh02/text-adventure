package com.roguelike.fallout.model;

public enum Locations {
  NUKA_WORLD(", you are currently in Nuka-World. \nYou see various rides and attractions."),
  COMMONWEALTH(", you are currently in Commonwealth. \nYou see abandoned buildings, ruined from nuclear blasts."),
  MOUNT_DESERT_ISLAND(", you are currently on Mount Desert Island. \nYou see hilly pine forests, wetlands and swamps, including a harbor town that sits on a mountain."),
  WILDWOOD_CEMETERY(", you are currently in Wildwood Cemetery. \nYou see numerous graves of local dignitaries and mausoleums.");

  private String description;
  private Locations(String description) {
    this.description = description;
  }
  public String getDescription() {
    return description;
  }
}
