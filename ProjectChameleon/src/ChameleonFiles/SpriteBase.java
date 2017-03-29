package ChameleonFiles;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public abstract class SpriteBase {

    Image image;
    ImageView imageView;

    Pane layer;

    double x;
    double y;

    double dx;
    double dy;
    double w;
    double h;

    boolean canMove = true;

    public SpriteBase(Pane layer, Image image, double x, double y, double dx, double dy) {

        this.layer = layer;
        this.image = image;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;

        this.imageView = new ImageView(image);
        this.imageView.relocate(x, y);
        this.w = image.getWidth(); // imageView.getBoundsInParent().getWidth();
        this.h = image.getHeight(); // imageView.getBoundsInParent().getHeight();

        addToLayer();

    }

    public void addToLayer() {
        this.layer.getChildren().add(this.imageView);
    }

    public void removeFromLayer() {
        this.layer.getChildren().remove(this.imageView);
    }

    public Pane getLayer() {
        return layer;
    }

    public void setLayer(Pane layer) {
        this.layer = layer;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getDx() {
        return dx;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public double getDy() {
        return dy;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public void move() {

        if (!canMove) {
            return;
        }

        x += dx;
        y += dy;
    }

    public void updateUI() {
        imageView.relocate(x, y);
    }

    public double getWidth() {
        return w;
    }

    public double getHeight() {
        return h;
    }

    public double getCenterX() {
        return x + w * 0.5;
    }

    public double getCenterY() {
        return y + h * 0.5;
    }
    public boolean collidesWith( SpriteBase otherSprite) {

        return ( otherSprite.x + otherSprite.w >= x && otherSprite.y + otherSprite.h >= y && otherSprite.x <= x + w && otherSprite.y <= y + h);

    }
}
