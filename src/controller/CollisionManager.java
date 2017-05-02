package controller;

import game.Collider;
import models.GameRect;

import java.util.ArrayList;

/**
 * Created by HoangLong on 4/29/2017.
 */
public class CollisionManager {
    private ArrayList<Collider> colliders;

    //Singleton
    public static final CollisionManager instance = new CollisionManager();

    private CollisionManager() {
        colliders = new ArrayList<>();
    }

    public void update() {
        for (int i = 0; i < colliders.size() - 1; i++) {
            for (int j = i + 1; j < colliders.size(); j++) {
                Collider ci = colliders.get(i);
                Collider cj = colliders.get(j);

                GameRect recti = ci.getGameRect();
                GameRect rectj = cj.getGameRect();
                if (recti.intersects(rectj)) {
                    ci.onCollide(cj);
                    cj.onCollide(ci);
                }
            }
        }
    }
    public void remove(Collider collider){
        colliders.remove(collider);
    }
    public void add(Collider collider){
        colliders.add(collider);
    }
}
