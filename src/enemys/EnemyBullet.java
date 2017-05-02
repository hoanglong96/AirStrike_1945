package enemys;

import controller.CollisionManager;
import controller.Controller;
import game.Collider;
import models.GameRect;
import player.PlayerController;
import view.ImageRenderer;

import java.awt.*;

/**
 * Created by HoangLong on 4/15/2017.
 */
public class EnemyBullet extends Controller implements Collider {
    private int damage = 1;
    public EnemyBullet(int x, int y, Image image) {
        super(new GameRect(x,y,image.getWidth(null),image.getHeight(null)), new ImageRenderer(image));
        CollisionManager.instance.add(this);
    }

    public void update(){
        gameRect.move(0,15);
    }

    public int getY(){
        return gameRect.getY();
    }

    public void getHit(int damage){
        gameRect.setDead(true);
        CollisionManager.instance.remove(this);
    }

    @Override
    public void onCollide(Collider other) {
        if(other instanceof PlayerController){
            ((PlayerController) other).getHit(damage);
        }
    }
}
