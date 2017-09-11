package nblott.org.orbitoids;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Nick on 07/09/2017.
 */

public class Controller {

    private ConstraintLayout space;
    private List<Orbitoid> orbitoids;
    private Runnable ticker;
    private Handler mHandler;
    private AppCompatActivity activity;

    private int width;
    private int height;

    public Controller(ConstraintLayout space, AppCompatActivity activity) {
        this.activity = activity;
        this.space = space;
        this.orbitoids = new ArrayList<>();
        this.mHandler = new Handler();

        //get screen width
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        this.width = size.x;
        this.height = size.y;
    }

    public void start() {
        ticker = new Runnable() {
            @Override
            public void run() {
                try {
                    tick();
                }
                finally {
                    mHandler.post(ticker);
                }
            }
        };
        mHandler.post(ticker);
    }

    private Orbitoid getOrbitoid(Orbitoid.Pos pos, float dX, float dY) {
        View root = activity.getLayoutInflater().inflate(R.layout.orbitoid, null);
        Orbitoid orbitoid = new Orbitoid(root,dX,dY);
        if (pos.equals(Orbitoid.Pos.BL)) {
            root.setX(space.getWidth());
            root.setY(0);
        } else if (pos.equals(Orbitoid.Pos.BR)){

        } else if (pos.equals(Orbitoid.Pos.TR)) {

        } else {
            root.setX(0);
            root.setY(0);
        }
        return orbitoid;
    }

    private void tick() {
        for (Orbitoid orbitoid : orbitoids) {
            bounce(orbitoid);
            move(orbitoid);
        }
    }


    private void bounce(Orbitoid orbitoid) {
        //check for walls

        int dp = 100;

        Resources r = activity.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());


        if (orbitoid.root.getX() + orbitoid.velX + TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100, r.getDisplayMetrics()) > 1080) { // check right
            orbitoid.velX = -orbitoid.velX;
        }
        if (orbitoid.root.getX() + orbitoid.velX < 0) { // check left
            orbitoid.velX = -orbitoid.velX;
        }
        if (orbitoid.root.getY() + orbitoid.velY < 0) { // check up
            orbitoid.velY = -orbitoid.velY;
        }
        if (orbitoid.root.getY() + orbitoid.velY + 262.5 > 1920 - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, r.getDisplayMetrics())) { // check down
            orbitoid.velY = -orbitoid.velY;
        }
    }

    private void move(Orbitoid orbitoid) {
        orbitoid.root.setX(orbitoid.root.getX() + orbitoid.velX);
        orbitoid.root.setY(orbitoid.root.getY() + orbitoid.velY);
    }



    public void addOrbitoid(Orbitoid orbitoid) {
        this.orbitoids.add(orbitoid);
        space.addView(orbitoid.root, 0, new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
    }

    public Orbitoid createOrbitoid(Orbitoid.Pos pos, float dX, float dY) {
        View root = activity.getLayoutInflater().inflate(R.layout.orbitoid, null);
        Orbitoid orbitoid = new Orbitoid(root,dX,dY);
        if (pos.equals(Orbitoid.Pos.BL)) {
            root.setX(space.getWidth());
            root.setY(0);
        } else if (pos.equals(Orbitoid.Pos.BR)){

        } else if (pos.equals(Orbitoid.Pos.TR)) {

        } else {
            root.setX(0);
            root.setY(0);
        }
        return orbitoid;
    }

    public void clearOrbitoids() {
        for (Orbitoid orbitoid : orbitoids) {
            space.removeView(orbitoid.root);
        }
        orbitoids.clear();
    }
}
