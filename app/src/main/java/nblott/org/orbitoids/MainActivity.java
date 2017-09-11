package nblott.org.orbitoids;

import android.content.Context;
import android.content.res.Resources;
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

    public Resources r;

    private ConstraintLayout space;
    private Controller mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        r = getResources();
        
        space = (ConstraintLayout) findViewById(R.id.space);

        findViewById(R.id.spawner).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random = new Random();
                mController.addOrbitoid(mController.createOrbitoid(Orbitoid.Pos.TL, random.nextFloat() * 10, random.nextFloat() * 10));
            }
        });

        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mController.clearOrbitoids();
            }
        });

        mController = new Controller(space, this);
        mController.start();
    }
}
