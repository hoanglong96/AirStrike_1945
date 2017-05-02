package player;

import controller.CollisionManager;
import controller.Controller;
import enemys.EnemyController;
import game.Collider;
import models.GameRect;
import view.ImageRenderer;

import java.awt.*;

/**
 * Created by HoangLong on 4/12/2017.
 */
public class Bullet extends Controller implements Collider {
    private int damage = 1;

    public Bullet(int x, int y, Image image) {
        super(new GameRect(x, y, image.getWidth(null), image.getHeight(null)),
                new ImageRenderer(image));
        CollisionManager.instance.add(this);
    }

    public void draw(Graphics g) {
        imageRenderer.render(g, gameRect);
    }

    public void update() {
        gameRect.move(0, -15);
    }
    public void getHit(int damage){
        gameRect.setDead(true);
        CollisionManager.instance.remove(this);

    }
    @Override
    public void onCollide(Collider other) {
        if(other instanceof EnemyController){
            ((EnemyController) other).getHit(damage);
        }
    }
}
