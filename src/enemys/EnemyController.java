package enemys;

import controller.CollisionManager;
import controller.Controller;
import game.Collider;
import models.GameRect;
import player.Bullet;
import player.PlayerController;
import view.ImageRenderer;

import java.awt.*;

/**
 * Created by HoangLong on 4/15/2017.
 */
public class EnemyController extends Controller implements Collider {
    private MoveBehavior moveBehavior;
    private int damage =1;
    public EnemyController(int x, int y, Image image) {
        super(new GameRect(x, y, image.getWidth(null), image.getHeight(null))
                , new ImageRenderer(image));
        CollisionManager.instance.add(this);
    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }

    public void update() {
        if (moveBehavior != null) {
            moveBehavior.doMove(gameRect);
        }
    }

    public void getHit(int damage){
        gameRect.setDead(true);
        CollisionManager.instance.remove(this);

    }
    @Override
    public void onCollide(Collider other) {
        if(other instanceof Bullet){
            ((Bullet) other).getHit(damage);
        }
        if(other instanceof PlayerController){
            ((PlayerController) other).getHit(damage);
        }
    }
}
