package controller;

import models.GameRect;
import view.ImageRenderer;

import java.awt.*;

/**
 * Created by HoangLong on 4/29/2017.
 */
public class Controller {
    protected GameRect gameRect;
    protected ImageRenderer imageRenderer;

    public Controller(GameRect gameRect, ImageRenderer imageRenderer) {
        this.gameRect = gameRect;
        this.imageRenderer = imageRenderer;
    }

    public void draw(Graphics graphics) {
        if(gameRect.isInVisiable()) return;
        imageRenderer.render(graphics, gameRect);
    }

    public GameRect getGameRect() {
        return gameRect;
    }

    public void update() {

    }
}
