package ChameleonFiles;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

public class Player extends SpriteBase {

    double BorderMinX;
    double BorderMaxX;
    double BorderMinY;
    double BorderMaxY;
    double wall1y;
    double wall1x;

    Input input;

    double speed;

    public Player(Pane layer, Image image, double x, double y, double dx, double dy, double speed, Input input) {

        super(layer, image, x, y, dx, dy);

        this.speed = speed;
        this.input = input;

        init();
    }

    private void init() {
        BorderMinX = 64 - image.getWidth();
        BorderMaxX = Settings.SCENE_WIDTH - image.getWidth() * 2;
        BorderMinY = 64 - image.getHeight();
        BorderMaxY = Settings.SCENE_HEIGHT - image.getHeight() * 2;
    }

    public void processInput() {
        if (input.isMoveUp()) {
            dy = -speed;
        } else if (input.isMoveDown()) {
            dy = speed;
        } else {
            dy = 0d;
        }

        if (input.isMoveLeft()) {
            dx = -speed;
        } else if (input.isMoveRight()) {
            dx = speed;
        } else {
            dx = 0d;
        }

    }

    @Override
    public void move() {
        super.move();
        checkBounds();
    }

    private void checkBounds() {
        if (Double.compare(y, BorderMinY) < 0) {
            y = BorderMinY;
        } else if (Double.compare(y, BorderMaxY) > 0) {
            y = BorderMaxY;
        } else if (Double.compare(y, wall1y) > 0) {
        }
        if (Double.compare(x, BorderMinX) < 0) {
            x = BorderMinX;
        } else if (Double.compare(x, BorderMaxX) > 0) {
            x = BorderMaxX;
        }

    }

}
