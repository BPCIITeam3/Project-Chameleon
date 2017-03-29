package ChameleonFiles;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Game extends Application {
    Pane testRoomAlpha;
    Pane bckgrnd;

    ImageView backgroundImageView;
    
    Image bckgrndImage;
    Image playerImage;
    Image saveImage;
    Image loadImage;

    List<Player> players = new ArrayList<>();
    List<Saver> savers = new ArrayList<>();
    List<Loader> loaders = new ArrayList<>();
    
    boolean collision = false;
    Scene scene;
   
    
    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Project Chameleon");
        Group root = new Group();
        
        final Popup popup = new Popup();
        
       
        bckgrnd = new Pane();
        testRoomAlpha = new Pane();
        
        root.getChildren().add(bckgrnd);
        root.getChildren().add(testRoomAlpha);
 
        scene = new Scene( root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();

        beginGame();
        createPlayers();

        AnimationTimer gameLoop = new AnimationTimer() {
            
            @Override
            public void handle(long now) {
                
                players.forEach(sprite -> sprite.processInput());
                savePoint(true);
                loadPoint(true);
                checkCollisions();
                players.forEach(sprite -> sprite.move());
                players.forEach(sprite -> sprite.updateUI());
            }
        };
        gameLoop.start();   
    }
    private void beginGame() {
        backgroundImageView = new ImageView("Resources/bckgrndtest.png");
        bckgrnd.getChildren().add(backgroundImageView);
        playerImage = new Image("Resources/grory.png");
        saveImage = new Image("Resources/savepoint.png");
        loadImage = new Image("Resources/loadpoint.png");
    }
    private void checkCollisions() {

        collision = false;

        for( Player player: players) {
                for( Saver saver: savers) {
                if( player.collidesWith(saver)) {
                    collision = true;
                    player.setX(150);
                }
            }
                for( Loader loader: loaders) {
                if( player.collidesWith(loader)) {
                    collision = true;
                    System.out.println("load");
                }
            }
            
        }
    }
    private void savePoint(Boolean trfa) {

        // image
        Image image = saveImage;

        double x = 100;
        double y = 200;

        // create a sprite
        Saver saver = new Saver( testRoomAlpha, image, x, y, 0, 0);

        savers.add( saver);

    }
    private void loadPoint(Boolean trfa) {

        Image image = loadImage;

        double x = 300;
        double y = 200;
        Loader loader = new Loader( testRoomAlpha, image, x, y, 0, 0);

        loaders.add( loader);

    }

    private void createPlayers() {
        
        
        Input input = new Input( scene);
        input.addListeners();

        Image image = playerImage;
        double x = (Settings.SCENE_WIDTH - image.getWidth()) / 2.0;
        double y = Settings.SCENE_HEIGHT * 0.7;
        Player player = new Player(testRoomAlpha, image, x, y, 0, 0,Settings.PlayerSpeed, input);
        players.add(player);
    }

    public static void main(String[] args) {
        launch(args);
    }

}