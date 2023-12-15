package com.example.hellofx3;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;

public class Controller {
   GameLoop gameLoop;

   private double xOffset, yOffset;

   private Stage stage;
   private Scene scene;
   private Parent root;
   private  int seconds = 0;
   private Timeline timer;




   @FXML
   private ImageView character;

   @FXML
   private Pane pane;

   @FXML
   private AnchorPane anchorPane;

   @FXML
   private Circle enemyCircle;

   @FXML
   private Label enemyHealthlabel;

   @FXML
   private Label healthLabel;
   @FXML
   private Label energyLabel;

   @FXML
   private Button nextTurnButton;

   @FXML
   private Button strikeButton;

   @FXML
   private Button  defendButton;

   @FXML
   private Button bashButton;

   @FXML
   private Label timerLabel;

   @FXML
   private Label shieldLabel;

   private List<Cards> deck;

   @FXML
   public void initialize() {
      //Set Timer
      timer = new Timeline(new KeyFrame(Duration.seconds(1), e -> updateTimer()));
      timer.setCycleCount(Timeline.INDEFINITE);
      timer.play();

      gameLoop = new GameLoop();

      // Empty initialize method, without any parameters



   }

   //GUI Real time updates, timers, health defense etc
   private void updateTimer() {
      seconds++;
      int minutes = seconds / 60;
      int secs = seconds % 60;
      timerLabel.setText(String.format("%02d:%02d", minutes, secs));
   }
   private void updateHealthLabel(int health) {
      healthLabel.setText(String.valueOf(health));
   }


   /*
   public void displayHand (List<Cards> hand) {
      hboxContainer.getChildren().clear();
      for(Cards card : hand) {
         ImageView cardView = new ImageView(card.getCardImage());
         System.out.println(String.valueOf(card.getCardImage()));
         cardView.setFitHeight(100);
         cardView.setPreserveRatio(true);
         hboxContainer.getChildren().add(cardView);
      }
   }

    */

   public void updateHealth(int health) {

      healthLabel.setText(String.valueOf(health));
   }
   public void updateEnergy(int energy) {
      energyLabel.setText(energy + "/4");
   }
   public void updateEnemyHealth(int health) {
      enemyHealthlabel.setText(String.valueOf(health));
   }

   public void updateShield(int defend) {
      shieldLabel.setText(String.valueOf(defend));
   }


   @FXML
   private void nextTurn(ActionEvent event) throws IOException {
      // Enemy attacks the character
      gameLoop.getCharacther().takeDamage(gameLoop.getEnemy().attack());

      // Update the health label after taking damage
      updateHealth(gameLoop.getCharacther().getHealth());

      // Reset the character's defense to 0 for the next turn and update the shield label
      gameLoop.getCharacther().resetDefense();
      updateShield(gameLoop.getCharacther().getDefense());

      // Reset energy and update the energy label
      gameLoop.getCharacther().resetEnergy();
      updateEnergy(gameLoop.getCharacther().getEnergy());


   }

   @FXML
   private void StrikeButton(ActionEvent event) throws IOException {
      Cards.Strike strikeCard = new Cards.Strike();
      strikeCard.useCard(gameLoop.getCharacther(), gameLoop.getEnemy());

      updateHealth(gameLoop.getCharacther().getHealth());
      updateEnemyHealth(gameLoop.getEnemy().getHealth());
      updateEnergy(gameLoop.getCharacther().getEnergy());
   }

   @FXML
   private void defendButton(ActionEvent event) throws IOException {
      Cards.Defend defendCard = new Cards.Defend();
      defendCard.useCard(gameLoop.getCharacther());

      // Update the UI to reflect the new state
      updateHealth(gameLoop.getCharacther().getHealth());
      updateEnergy(gameLoop.getCharacther().getEnergy());
      updateShield(gameLoop.getCharacther().getDefense());
   }

   @FXML
   private void shieldBashButton(ActionEvent event) throws IOException {
      Cards.ShieldBash shieldBashCard = new Cards.ShieldBash();
      shieldBashCard.useCard(gameLoop.getCharacther(), gameLoop.getEnemy());

      // Update the UI to reflect the new state
      updateHealth(gameLoop.getCharacther().getHealth());
      updateEnemyHealth(gameLoop.getEnemy().getHealth());
      updateEnergy(gameLoop.getCharacther().getEnergy());
      updateShield(gameLoop.getCharacther().getDefense());
   }


}


   //Mouse actions, related to cards and characters.

