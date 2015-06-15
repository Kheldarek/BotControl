package kheldar.botcontrol;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

//TODO: implement BT functions: client,server,finding devices
// TODO: paired list and pairing (different activity probably)


public class GetAccelerometer extends ActionBarActivity implements SensorEventListener {
    SensorManager manager;
    TextView X,Y,Z; // to display accelerometer axis X,Y and Z
    Button BTS,BTC,VIS; //bt server, bt client, visible
    Sensor accelerometer;
    EditText et1; //btserver mac
    BluetoothAdapter ba ;
    BluetoothDevice server;
    BtClient current;





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
        et1= (EditText) findViewById(R.id.editText);
        X.setText("");
        Y.setText("");
        Z.setText("");
        et1.setText("20:14:11:26:03:80");

        manager= (SensorManager)getSystemService(SENSOR_SERVICE);
        manager.registerListener(this,manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),0,null);
        accelerometer=  manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        ba = BluetoothAdapter.getDefaultAdapter();
        server = ba.getRemoteDevice(et1.getText().toString());

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

    public void BTServerActivate(View v)
    {





        // Toast.makeText(getApplicationContext(),"Server BT not implemented... yet",Toast.LENGTH_SHORT).show();
    }
    public void BTVisible(View v)
    {
        Intent Visible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        Visible.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,300);
        startActivity(Visible);
        //Toast.makeText(getApplicationContext(),"BT Visibility not implemented... yet",Toast.LENGTH_SHORT).show();
    }
    public void BTClientActivate(View v)
    {
        Log.d("btA", "wlaczanie clienta");
        BluetoothAdapter ba = BluetoothAdapter.getDefaultAdapter();
        BluetoothDevice server= ba.getRemoteDevice(et1.getText().toString());
        current = new BtClient(server,Z,Y);
        current.start();

       // Toast.makeText(getApplicationContext(),"Client BT not implemented... yet",Toast.LENGTH_SHORT).show();
    }

    public void BTPairedList(View v)
    {

        Intent showList = new Intent(this,PairedList.class);
        startActivity(showList);
       // Toast.makeText(getApplicationContext(),"PairedList not implemented... yet",Toast.LENGTH_SHORT).show();
    }
    public void STOP(View v)
    {

        current.interrupt();
        //Toast.makeText(getApplicationContext(),"Finding devices not implemented... yet",Toast.LENGTH_SHORT).show();
    }


}
