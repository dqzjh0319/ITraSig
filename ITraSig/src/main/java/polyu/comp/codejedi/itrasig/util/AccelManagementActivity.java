package polyu.comp.codejedi.itrasig.util;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import polyu.comp.codejedi.itrasig.R;

public class AccelManagementActivity extends Activity {

    private Button btn_accel_switch;
    private TextView tv_accelx;
    private TextView tv_accely;
    private TextView tv_accelz;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private SensorEventListener mAccelEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accel_management_view);
        initView();
        initSensor();
    }

    public class AccelEventListener implements SensorEventListener {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() != Sensor.TYPE_ACCELEROMETER){
                return;
            }
            tv_accelx.setText(""+event.values[0]);
            tv_accely.setText(""+event.values[1]);
            tv_accelz.setText(""+event.values[2]);
        }
    }

    public void startAccelSensing(){
        mAccelEventListener = new AccelEventListener();
        mSensorManager.registerListener(mAccelEventListener,mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    public void stopAccelSensing(){
        mSensorManager.unregisterListener(mAccelEventListener);
    }

    private void initView(){
        btn_accel_switch = (Button)findViewById(R.id.btn_accel_switch);
        btn_accel_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_accel_switch.getText().toString().equals(getString(R.string.accel_start))){
                    startAccelSensing();
                    btn_accel_switch.setText(R.string.accel_stop);
                }
                else{
                    stopAccelSensing();
                    btn_accel_switch.setText(R.string.accel_start);
                }
            }
        });

        tv_accelx = (TextView)findViewById(R.id.tv_accel_x);
        tv_accely = (TextView)findViewById(R.id.tv_accel_y);
        tv_accelz = (TextView)findViewById(R.id.tv_accel_z);
    }

    private void initSensor(){
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor((Sensor.TYPE_ACCELEROMETER));
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAccelSensing();
        btn_accel_switch.setText(R.string.accel_stop);
    }
}
