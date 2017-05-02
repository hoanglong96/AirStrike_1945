package models;

import java.awt.*;

/**
 * Created by HoangLong on 4/15/2017.
 */
public class GameRect {
    private int x;
    private int y;
    private int with;
    private int height;
    private boolean isInVisiable;
    private boolean isDead;

    public GameRect(int x, int y, int with, int height) {
        this.x = x;
        this.y = y;
        this.with = with;
        this.height = height;
    }

    public boolean isInVisiable() {
        return isInVisiable;
    }

    public void setInVisiable(boolean inVisiable) {
        this.isInVisiable = inVisiable;
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWith() {
        return with;
    }

    public int getHeight() {
        return height;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public boolean intersects(GameRect other) {
        Rectangle rec1 = new Rectangle(x, y, with, height);
        Rectangle rec2 = new Rectangle(other.x, other.y, other.with, other.height);
        return rec1.intersects(rec2);
    }
}
