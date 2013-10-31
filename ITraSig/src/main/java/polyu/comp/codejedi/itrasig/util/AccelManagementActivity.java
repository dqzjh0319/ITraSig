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
    private Button btn_gyro_switch;
    private TextView tv_accelx;
    private TextView tv_accely;
    private TextView tv_accelz;

    private TextView tv_gyrox;
    private TextView tv_gyroy;
    private TextView tv_gyroz;

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mGyroScope;
    private SensorEventListener mAccelEventListener;
    private SensorEventListener mGyroEventListener;
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

    public class GyroEventListener implements SensorEventListener {
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType() != Sensor.TYPE_GYROSCOPE){
                return;
            }
            tv_gyrox.setText(""+event.values[0]);
            tv_gyroy.setText(""+event.values[1]);
            tv_gyroz.setText(""+event.values[2]);
        }
    }

    public void startAccelSensing(){
        mAccelEventListener = new AccelEventListener();
        mSensorManager.registerListener(mAccelEventListener,mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    public void stopAccelSensing(){
        mSensorManager.unregisterListener(mAccelEventListener);
    }

    public void startGyroSensing(){
        mGyroEventListener = new GyroEventListener();
        mSensorManager.registerListener(mGyroEventListener, mGyroScope, SensorManager.SENSOR_DELAY_UI);
    }

    public void stopGyroSensing(){
        mSensorManager.unregisterListener(mGyroEventListener);
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

        btn_gyro_switch = (Button)findViewById(R.id.btn_gyro_switch);
        btn_gyro_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_gyro_switch.getText().toString().equals(getString(R.string.accel_start))){
                    startGyroSensing();
                    btn_gyro_switch.setText(R.string.accel_stop);
                }
                else{
                    stopGyroSensing();
                    btn_gyro_switch.setText(R.string.accel_start);
                }
            }
        });

        tv_accelx = (TextView)findViewById(R.id.tv_accel_x);
        tv_accely = (TextView)findViewById(R.id.tv_accel_y);
        tv_accelz = (TextView)findViewById(R.id.tv_accel_z);

        tv_gyrox = (TextView)findViewById(R.id.tv_gyro_x);
        tv_gyroy = (TextView)findViewById(R.id.tv_gyro_y);
        tv_gyroz = (TextView)findViewById(R.id.tv_gyro_z);
    }

    private void initSensor(){
        mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mGyroScope = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    @Override
    protected void onPause() {
        super.onPause();
        stopAccelSensing();
        stopGyroSensing();
        btn_accel_switch.setText(R.string.accel_stop);
        btn_gyro_switch.setText(R.string.accel_stop);
    }
}
