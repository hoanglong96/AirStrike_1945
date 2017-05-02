package utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Created by HoangLong on 4/15/2017.
 */
public class Utils
{
    public static Image loadImage(String path){
        try {
            return ImageIO.read(new java.io.File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
