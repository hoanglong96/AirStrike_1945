package game;

import controller.CollisionManager;
import enemys.EnemyBullet;
import enemys.EnemyController;
import enemys.HorizontalMoveBehavior;
import enemys.MoveBehavior;
import models.GameRect;
import player.Bullet;
import player.PlayerController;
import utils.Utils;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by HoangLong on 4/8/2017.
 */
public class GameWindow extends Frame {
    Image backGroundImage = null;
    BufferedImage backBufferImage;
    Graphics backBufferGraphics;

    boolean isUpPressed;
    boolean isDownPressed;
    boolean isRightPressed;
    boolean isLeftPressed;
    boolean isSpacePress;
    boolean enableShoot = true;
    boolean isEnableShootEnemy = true;
    boolean isEnemyAppear;

    int coolDownTimeBulletEnemy;
    int coolDownTime;
    int enemyTime = 3000;


    PlayerController playerController;
    EnemyController enemyController;
    ArrayList<EnemyController> enemyControllers = new ArrayList<>();
    ArrayList<EnemyBullet> enemyBullets = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();

    //Check collision
    public void check() {
        ArrayList<GameRect> gameRects = new ArrayList<>();
        for (int i = 0; i < gameRects.size() - 1; i++) {
            for (int j = i + 1; j < gameRects.size(); j++) {
                GameRect rect1 = gameRects.get(i);
                GameRect rect2 = gameRects.get(j);
                if (rect1.intersects(rect2)) {
                    System.out.println("Va cham");
                }
            }
        }
    }


    //Frame
    public GameWindow() {
        setVisible(true);
        playerController = new PlayerController(170, 630, Utils.loadImage("res/plane3.png"));
        setSize(400, 700);
        setTitle("1945");

        backBufferImage = new BufferedImage(400, 700, BufferedImage.TYPE_INT_ARGB);
        backBufferGraphics = backBufferImage.getGraphics();

        //Create Enemy
        EnemyController enemyController = new EnemyController(200, 50, Utils.loadImage("res/enemy-green-3.png"));
        enemyController.setMoveBehavior(new HorizontalMoveBehavior());
        enemyControllers.add(enemyController);

        addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("Window closing");
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("Window closed");
            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
        addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT: {
                        isRightPressed = true;
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        isLeftPressed = true;
                        break;
                    }
                    case KeyEvent.VK_UP: {
                        isUpPressed = true;
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        isDownPressed = true;
                        break;
                    }
                    case KeyEvent.VK_SPACE: {
                        isSpacePress = true;
                        break;
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_RIGHT: {
                        isRightPressed = false;
                        break;
                    }
                    case KeyEvent.VK_LEFT: {
                        isLeftPressed = false;
                        break;
                    }
                    case KeyEvent.VK_UP: {
                        isUpPressed = false;
                        break;
                    }
                    case KeyEvent.VK_DOWN: {
                        isDownPressed = false;
                        break;
                    }
                    case KeyEvent.VK_SPACE: {
                        isSpacePress = false;
                        break;
                    }
                }
            }
        });

        try {
            backGroundImage = ImageIO.read(new File("res/background.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(17);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //PlayerController
                    playerController.move(isUpPressed,
                            isDownPressed,
                            isLeftPressed,
                            isRightPressed);
                    playerController.update();

                    //Playcontroller shootGun coolDown
                    if (isSpacePress && enableShoot) {
                        Bullet bullet = new Bullet(playerController.getGameRect().getX() + 29, playerController.getGameRect().getY() - 10, Utils.loadImage("res/bullet.png"));
                        bullets.add(bullet);
                        enableShoot = false;
                        coolDownTime = 10;
                    }
                    if (!enableShoot) {
                        coolDownTime--;
                        if (coolDownTime <= 0) {
                            enableShoot = true;
                        }
                    }

                    //PlayerController Bullet
                    for (int i = 0; i < bullets.size(); i++) {
                        if (bullets.get(i).getGameRect().getY() < 0) {
                            bullets.remove(bullets.get(i));
                            i--;
                        } else {
                            bullets.get(i).update();
                        }
                    }

                    //Create Enemy
                    enemyTime -=17;
                    if(enemyTime<0){
                        enemyTime = 3000;
                        isEnemyAppear = true;
                    }

                    if(isEnemyAppear){
                        for(int x=0;x<getWidth();x+=200){
                            EnemyController enemyController = null;
                            if(x<300){
                                enemyController = new EnemyController(x,0,Utils.loadImage("res/plane1.png"));
                                enemyController.setMoveBehavior(new MoveBehavior());
                            }
                            else{
                                enemyController = new EnemyController(x,0,Utils.loadImage("res/enemy-green-3.png"));
                                enemyController.setMoveBehavior(new HorizontalMoveBehavior());
                            }
                            enemyControllers.add(enemyController);
                        }
                        isEnemyAppear = false;
                    }

                    //EnemyBullet
                    for (int i = 0; i < enemyBullets.size(); i++) {
                        if (enemyBullets.get(i).getY() > 700) {
                            enemyBullets.remove(enemyBullets.get(i));
                            i--;
                        } else {
                            enemyBullets.get(i).update();
                        }
                    }

                    //Enemy controller.Controller
                    for (EnemyController enemyController : enemyControllers
                            ) {
                        enemyController.update();
                    }

                    autoShoot();
                    CollisionManager.instance.update();
                    //Draw
                    repaint();
                }
            }
        });
        thread.start();
    }


    public void autoShoot() {
        if (isEnableShootEnemy) {
            for (EnemyController enemyController : enemyControllers) {
                EnemyBullet enemyBullet = new EnemyBullet(enemyController.getGameRect().getX()+10, enemyController.getGameRect().getY(), Utils.loadImage("res/enemy_bullet.png"));
                enemyBullets.add(enemyBullet);
                isEnableShootEnemy = false;
                coolDownTimeBulletEnemy = 25;
            }
        }
    }

    @Override
    public void update(Graphics g) {
        backBufferGraphics.drawImage(backGroundImage, 0, 0, 400, 700, null);


        if (!isEnableShootEnemy) {
            coolDownTimeBulletEnemy--;
            if (coolDownTimeBulletEnemy <= 0) {
                isEnableShootEnemy = true;
            }
        }
        for (EnemyController enemyController : enemyControllers
                ) {
            enemyController.draw(backBufferGraphics);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(backBufferGraphics);

        }
        //remove EnemyController
        Iterator<EnemyController> enemyControllerIterator = enemyControllers.iterator();
        while (enemyControllerIterator.hasNext()){
            EnemyController enemyController = enemyControllerIterator.next();
            if(enemyController.getGameRect().isDead()){
                enemyControllerIterator.remove();
                System.out.println("xoa khong ?");
                try {
                    backBufferGraphics.drawImage(ImageIO.read(new File("res/boom.jpg")),enemyController.getGameRect().getX(),enemyController.getGameRect().getY(),null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        //Delete Player if gameOver
        if(playerController.getGameRect().isDead()){
            playerController.getGameRect().setInVisiable(true);
        }
        else{
            playerController.draw(backBufferGraphics);
        }
        //Remove Bullet
        Iterator<Bullet> bulletIterator = bullets.iterator();
        while(bulletIterator.hasNext()){
            Bullet bullet = bulletIterator.next();
            if(bullet.getGameRect().isDead()){
                bulletIterator.remove();
                //System.out.println("xoa dan");
            }
        }
        //Remove BulletEnemy
        Iterator<EnemyBullet> enemyBulletIterator = enemyBullets.iterator();
        while(enemyBulletIterator.hasNext()){
            EnemyBullet enemyBullet = enemyBulletIterator.next();
            if(enemyBullet.getGameRect().isDead()){
                enemyBulletIterator.remove();
            }
        }

        for (EnemyBullet enemyBullet : enemyBullets) {
            enemyBullet.draw(backBufferGraphics);
        }
        //Ve bufferImage len man hinh
        g.drawImage(backBufferImage, 0, 0, null);
    }
}
