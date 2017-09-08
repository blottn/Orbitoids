package nblott.org.orbitoids;

import android.content.Context;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public final String TAG = this.getClass().getSimpleName();
    private Handler mHandler = new Handler();
    private Runnable ticker;
    private static final int delay = 20;
    private View root;
    private ConstraintLayout space;
    private List<Orbitoid> orbitoids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        space = (ConstraintLayout) findViewById(R.id.space);
        orbitoids = new ArrayList<Orbitoid>();

        int x = 100;
        int y = 100;
        int w = 50;
        int h = 50;

        LayoutInflater inflater = getLayoutInflater();



        ticker = new Runnable() {
            @Override
            public void run() {
                try {
                    tick();
                }
                finally {
                    repost();
                }
            }
        };
        mHandler.postDelayed(ticker, delay);


        //spawn 30
        for (int i = 0 ; i < 30 ; i++ ) {
            Random random = new Random();
            attachOrbitoid(getOrbitoid(Orbitoid.Pos.TL,random.nextFloat()*10 + 4,random.nextFloat()*10 + 4));
        }



//        attachOrbitoid(getOrbitoid(Orbitoid.Pos.BL, 6,6));
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    private View createView() {
        return null;
    }

    // x and y are along the edge
    // vel x and vely are
    private Orbitoid getOrbitoid(Orbitoid.Pos pos, float dX, float dY) {
        View root = getLayoutInflater().inflate(R.layout.orbitoid, null);
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

    private void repost() {
        mHandler.post(ticker);
    }

    private void tick() {
        for (Orbitoid orbitoid : orbitoids) {
            if (orbitoid.root.getX() + orbitoid.velX + 255 > 1080) { // check right
                orbitoid.velX = -orbitoid.velX;
            }
            if (orbitoid.root.getX() + orbitoid.velX < 0) { // check left
                orbitoid.velX = -orbitoid.velX;
            }
            if (orbitoid.root.getY() + orbitoid.velY < 0) { // check up
                orbitoid.velY = -orbitoid.velY;
            }
            if (orbitoid.root.getY() + orbitoid.velY + 310 > 1720) { // check down
                orbitoid.velY = -orbitoid.velY;
            }
            orbitoid.root.setX(orbitoid.root.getX() + orbitoid.velX);
            orbitoid.root.setY(orbitoid.root.getY() + orbitoid.velY);
        }
    }

    private void attachOrbitoid(Orbitoid orbitoid) {
        orbitoids.add(orbitoid);
        space.addView(orbitoid.root, 0, new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT));
    }
}
