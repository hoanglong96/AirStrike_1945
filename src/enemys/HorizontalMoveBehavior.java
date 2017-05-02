package enemys;

import models.GameRect;

/**
 * Created by HoangLong on 4/15/2017.
 */
public class HorizontalMoveBehavior extends MoveBehavior {
    @Override
    public void doMove(GameRect gameRect){
        gameRect.move(0,1);
    }
}
