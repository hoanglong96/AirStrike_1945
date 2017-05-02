package player;

import controller.CollisionManager;
import controller.Controller;
import enemys.EnemyBullet;
import enemys.EnemyController;
import game.Collider;
import models.GameRect;
import view.ImageRenderer;

import java.awt.*;

/**
 * Created by HoangLong on 4/12/2017.
 */
public class PlayerController extends Controller implements Collider {
    private int damage = 1;
    private int hitPoint = 15;
    int dx;
    int dy;

    public PlayerController(int x, int y, Image image) {
        super(new GameRect(x, y, image.getWidth(null), image.getHeight(null)),new ImageRenderer(image));
        CollisionManager.instance.add(this);
    }

    public void getHit(int damage){
        hitPoint -= damage;
        if(hitPoint==0){
            gameRect.setDead(true);
            System.out.println("gameOver");
            return;
        }
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    @Override
    public void onCollide(Collider other) {
        if(other instanceof EnemyBullet){
            ((EnemyBullet) other).getHit(damage);
        }
        if(other instanceof EnemyController){
            ((EnemyController) other).getHit(damage);
        }
        if(getHitPoint()>0){
        System.out.println("Hit point : " + hitPoint);
        return;}
    }
    public void getHeartPoint(){
        hitPoint = 5;
    }
    public void move(boolean isUpPressed, boolean isDownPressed, boolean isLeftPressed, boolean isRightPressed) {
        dx = 0;
        dy = 0;
        if (isUpPressed) {
            dy -= 10;
        }
        if (isDownPressed) {
            dy += 10;
        }
        if (isLeftPressed) {
            dx -= 10;
        }
        if (isRightPressed) {
            dx += 10;
        }
    }

    public void update() {
        gameRect.move(dx, dy);
    }
}
