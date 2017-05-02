package game;

import models.GameRect;

/**
 * Created by HoangLong on 4/29/2017.
 */
public interface Collider {
    GameRect getGameRect();
    void onCollide(Collider other);
}
