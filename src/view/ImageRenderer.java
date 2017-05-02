package view;

import models.GameRect;
import utils.Utils;

import java.awt.*;

/**
 * Created by HoangLong on 4/15/2017.
 */
public class ImageRenderer {
    private Image image;

    public Image getImage() {
        return image;
    }

    public ImageRenderer(Image image) {
        this.image = image;
    }

    public ImageRenderer(String path){
        this(Utils.loadImage(path));
    }

    public void render(Graphics g, GameRect gameRect){
        g.drawImage(image,gameRect.getX(),gameRect.getY(),gameRect.getWith(),gameRect.getHeight(),null);
    }
}
