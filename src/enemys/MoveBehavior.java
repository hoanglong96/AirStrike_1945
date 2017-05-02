package enemys;

import models.GameRect;

/**
 * Created by HoangLong on 4/15/2017.
 */
public class MoveBehavior {
    public void doMove(GameRect gameRect){
        gameRect.move(1,1);
        if(gameRect.getX() >= 550){
            gameRect.move(-3,3);
        }
    }
}
