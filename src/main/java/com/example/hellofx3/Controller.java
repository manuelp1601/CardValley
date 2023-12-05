package com.example.hellofx3;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class Controller {

   private double xOffset, yOffset;

   @FXML
   private Rectangle card;

   @FXML
   private Rectangle card1;

   @FXML
   private Polygon enemy;

   @FXML
   private List<Rectangle> cards = new ArrayList<>();

   @FXML
   private List<Polygon> enemies = new ArrayList<>();

   @FXML
   public void initialize() {
      // Empty initialize method, without any parameters
      cards.add(card);
      cards.add(card1);
      enemies.add(enemy);

   }

   @FXML
   public void handlePress(MouseEvent event) {
      for (Rectangle card : cards) {
         if (event.getSource() == card) {
            xOffset = event.getSceneX() - card.getLayoutX();
            yOffset = event.getSceneY() - card.getLayoutY();
            card.toFront(); // Bring the card to the front while dragging

         }
      }
   }

   @FXML
   public void handleDrag(MouseEvent event) {
      for (Rectangle card : cards) {
         if (event.getSource() == card) {
            card.setLayoutX(event.getSceneX() - xOffset);
            card.setLayoutY(event.getSceneY() - yOffset);

         }
      }
      checkCollision();// Check for collisions during dragging
   }

   @FXML
   public void handleRelease(MouseEvent event) {
      // Add any release logic here if needed
   }

   public void checkCollision() {
      List<Rectangle> cardsToRemove = new ArrayList<>();
      for (Rectangle card : cards) {
         boolean collided = false; // Flag to track collision
         System.out.println("not colliding");
         for (Polygon enemy : enemies) {
            Shape intersect = Shape.intersect(card, enemy);
            if (intersect.getBoundsInLocal().getWidth() != -1) {
               collided = true;// Set the flag if collision occurs
               System.out.println("colliding");
               break; // Exit the inner loop since collision is detected
            }
         }

         if (collided) {
            cardsToRemove.add();
            changeTargetColor(enemy); // Change card color if collided with any enemy
         } else {
            resetTargetColor(enemy); // Reset card color if no collision
         }
      }
      for (Rectangle cards: cardsToRemove) {
         cards.remove(card);
         ((Pane)card.getParent()).getChildren().remove(card);
      }
   }


   private void changeTargetColor(Polygon enemy) {
      // Change color logic for the target shape upon collision
      enemy.setFill(Color.GREEN);
   }

   private void resetTargetColor(Polygon enemy) {
      // Reset color logic for the target shape when no collision
      enemy.setFill(Color.BLUE);
   }
}
