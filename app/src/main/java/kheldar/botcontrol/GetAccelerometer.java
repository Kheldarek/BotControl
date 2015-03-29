package kheldar.botcontrol;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

//TODO: implement BT functions: visibility,client,server
// TODO: paired list and pairing (different activity probably)


public class GetAccelerometer extends ActionBarActivity implements SensorEventListener {
    SensorManager manager;
    TextView X,Y,Z; // to display accelerometer axis X,Y and Z
    Button BTS,BTC,VIS; //bt server, bt client, visible
    Sensor accelerometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_accelerometer);
        X=(TextView) findViewById(R.id.Xaxis);
        Y=(TextView) findViewById(R.id.Yaxis);
        Z=(TextView) findViewById(R.id.Zaxis);
        BTC = (Button) findViewById(R.id.BTC);
        BTS = (Button) findViewById(R.id.BTS);
        VIS = (Button) findViewById(R.id.VIS);
        X.setText("");
        Y.setText("");
        Z.setText("");

        manager= (SensorManager)getSystemService(SENSOR_SERVICE);
        manager.registerListener(this,manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),0,null);
        accelerometer=  manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_get_accelerometer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event)
    {
        X.setText(String.valueOf(event.values[0]));
        Y.setText(String.valueOf(event.values[1]));
        Z.setText(String.valueOf(event.values[2]));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void BTServer(View v)
    {
        Toast.makeText(getApplicationContext(),"Server BT not implemented... yet",Toast.LENGTH_SHORT).show();
    }
    public void BTVisible(View v)
    {
        Toast.makeText(getApplicationContext(),"BT Visibility not implemented... yet",Toast.LENGTH_SHORT).show();
    }
    public void BTClient(View v)
    {
        Toast.makeText(getApplicationContext(),"Client BT not implemented... yet",Toast.LENGTH_SHORT).show();
    }
    public void BTPairedList(View v)
    {
        Toast.makeText(getApplicationContext(),"PairedList not implemented... yet",Toast.LENGTH_SHORT).show();
    }
}
