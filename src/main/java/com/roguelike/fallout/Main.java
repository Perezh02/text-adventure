package com.roguelike.fallout;

import com.roguelike.fallout.menu.GameManager;

/**
 * Main class that starts the game
 */
public class Main {

  public static void main(String[] args) {
    // Create an instance of the GameManager class
    GameManager gameManager = new GameManager();
    // Starts the game.
    gameManager.startGame();
  }
}