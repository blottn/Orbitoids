package nblott.org.orbitoids;

import android.view.View;

/**
 * Created by Nick on 05/09/2017.
 */

public class Orbitoid {

    public final long ID = IdGen.getNext();

    public enum Pos {TL, TR, BL, BR}

    public float velX;
    public float velY;
    public float rotation;
    public int width;
    public int height;
    public View root;

    public Orbitoid( View root, float velX, float velY) {
        this.root = root;
        this.velX = velX;
        this.velY = velY;
        System.out.println("Width: " + root.getWidth());
        this.width = root.getWidth();
        this.height = root.getHeight();
    }
}
