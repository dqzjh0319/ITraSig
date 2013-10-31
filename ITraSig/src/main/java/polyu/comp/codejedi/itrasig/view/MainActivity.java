package polyu.comp.codejedi.itrasig.view;

import android.app.Activity;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import polyu.comp.codejedi.itrasig.R;
import polyu.comp.codejedi.itrasig.util.AccelManagementActivity;

public class MainActivity extends Activity {

    private GLSurfaceView mainView;
    private Button btn_accel;
    private static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mainView = new MainGLSurfaceView(this);
        setContentView(R.layout.fragment_main);
        Log.i(TAG, "OnCreate processed.");
        btn_accel = (Button)findViewById(R.id.btn_accel_management);
        btn_accel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i(TAG, "On click processed!!!");
                Intent main2AccelIntent = new Intent(MainActivity.this, AccelManagementActivity.class);
                startActivity(main2AccelIntent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //mainView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mainView.onPause();
    }
}
