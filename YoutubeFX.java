import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.sql.*;

public class YoutubeFX extends Application {
    private static Connection con;
    private YoutubeBackend backend;

    @Override
    public void start(Stage primaryStage) {
        try {
            // Initialize database connection
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:/SQLite/youtube.db");
            backend = new YoutubeBackend(con);

            // Create main layout
            VBox mainLayout = new VBox(10);
            mainLayout.setPadding(new Insets(20));
            mainLayout.setAlignment(Pos.CENTER);

            // Create title
            Label titleLabel = new Label("YouTube Backend");
            titleLabel.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");

            // Create buttons
            Button registerButton = new Button("Register");
            Button watchVideoButton = new Button("Watch Video");
            Button exitButton = new Button("Exit");

            // Style buttons
            String buttonStyle = "-fx-background-color: #FF0000; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 10px 20px;";
            registerButton.setStyle(buttonStyle);
            watchVideoButton.setStyle(buttonStyle);
            exitButton.setStyle(buttonStyle);

            // Add event handlers
            registerButton.setOnAction(e -> showRegisterScreen());
            watchVideoButton.setOnAction(e -> showVideoScreen());
            exitButton.setOnAction(e -> System.exit(0));

            // Add components to layout
            mainLayout.getChildren().addAll(titleLabel, registerButton, watchVideoButton, exitButton);

            // Create scene
            Scene scene = new Scene(mainLayout, 600, 400);
            primaryStage.setTitle("YouTube Backend");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            showError("Error", "Failed to start application: " + e.getMessage());
        }
    }

    private void showRegisterScreen() {
        Stage registerStage = new Stage();
        VBox registerLayout = new VBox(10);
        registerLayout.setPadding(new Insets(20));
        registerLayout.setAlignment(Pos.CENTER);

        Label titleLabel = new Label("Register");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");
        nameField.setMaxWidth(300);

        Button registerButton = new Button("Register");
        registerButton.setStyle("-fx-background-color: #FF0000; -fx-text-fill: white;");

        registerButton.setOnAction(e -> {
            try {
                String name = nameField.getText();
                if (name.isEmpty()) {
                    showError("Error", "Please enter your name");
                    return;
                }
                backend.register(name);
                showSuccess("Success", "Registration successful!");
                registerStage.close();
            } catch (Exception ex) {
                showError("Error", "Registration failed: " + ex.getMessage());
            }
        });

        registerLayout.getChildren().addAll(titleLabel, nameField, registerButton);
        Scene scene = new Scene(registerLayout, 400, 300);
        registerStage.setTitle("Register");
        registerStage.setScene(scene);
        registerStage.show();
    }

    private void showVideoScreen() {
        Stage videoStage = new Stage();
        VBox videoLayout = new VBox(10);
        videoLayout.setPadding(new Insets(20));
        videoLayout.setAlignment(Pos.CENTER);

        // Video player frame
        BorderPane videoFrame = new BorderPane();
        videoFrame.setStyle("-fx-background-color: black; -fx-min-width: 400px; -fx-min-height: 300px;");
        Label videoLabel = new Label("THE VIDEO\nDirected by yobi!!");
        videoLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        videoFrame.setCenter(videoLabel);

        // Stats panel
        HBox statsPanel = new HBox(20);
        statsPanel.setAlignment(Pos.CENTER);
        
        try {
            int[] stats = backend.getVideoStats();
            Label subscribersLabel = new Label("Subscribers: " + stats[0]);
            Label likesLabel = new Label("Likes: " + stats[1]);
            Label sharesLabel = new Label("Shares: " + stats[2]);
            
            statsPanel.getChildren().addAll(subscribersLabel, likesLabel, sharesLabel);
        } catch (Exception e) {
            showError("Error", "Failed to load video stats: " + e.getMessage());
        }

        // Action buttons
        HBox actionButtons = new HBox(10);
        actionButtons.setAlignment(Pos.CENTER);
        
        Button likeButton = new Button("Like");
        Button shareButton = new Button("Share");
        Button subscribeButton = new Button("Subscribe");

        String buttonStyle = "-fx-background-color: #FF0000; -fx-text-fill: white;";
        likeButton.setStyle(buttonStyle);
        shareButton.setStyle(buttonStyle);
        subscribeButton.setStyle(buttonStyle);

        likeButton.setOnAction(e -> {
            try {
                backend.like();
                showSuccess("Success", "Video liked!");
                videoStage.close();
                showVideoScreen();
            } catch (Exception ex) {
                showError("Error", "Failed to like video: " + ex.getMessage());
            }
        });

        shareButton.setOnAction(e -> {
            try {
                backend.share();
                showSuccess("Success", "Video shared!");
                videoStage.close();
                showVideoScreen();
            } catch (Exception ex) {
                showError("Error", "Failed to share video: " + ex.getMessage());
            }
        });

        subscribeButton.setOnAction(e -> {
            try {
                backend.subscribe();
                showSuccess("Success", "Channel subscribed!");
                videoStage.close();
                showVideoScreen();
            } catch (Exception ex) {
                showError("Error", "Failed to subscribe: " + ex.getMessage());
            }
        });

        actionButtons.getChildren().addAll(likeButton, shareButton, subscribeButton);

        videoLayout.getChildren().addAll(videoFrame, statsPanel, actionButtons);
        Scene scene = new Scene(videoLayout, 600, 500);
        videoStage.setTitle("Watch Video");
        videoStage.setScene(scene);
        videoStage.show();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showSuccess(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
} 