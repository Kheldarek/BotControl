package kheldar.botcontrol;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;


public class PairedList extends ActionBarActivity {

    public Set<BluetoothDevice> pairedDevices;
    ListView plist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paired_list);
        BluetoothAdapter device = BluetoothAdapter.getDefaultAdapter();
        pairedDevices = device.getBondedDevices();
        ArrayList<String> devices= new ArrayList<String>();
        for(BluetoothDevice pdev: pairedDevices)
        {
            devices.add(pdev.getName() + "-> " + pdev.getAddress());
        }
        plist = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> L = new ArrayAdapter<String>(this,R.layout.row,devices);
        plist.setAdapter(L);
      /*  EditText et1= (EditText) findViewById(R.id.editText);
        et1.setText(devices.get(0));*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_paired_list, menu);
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
}
