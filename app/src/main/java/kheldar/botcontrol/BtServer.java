package kheldar.botcontrol;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.SystemClock;
import android.util.Log;
import android.widget.TextView;

import java.io.PrintWriter;
import java.util.UUID;

/**
 * Created by Piotr on 2015-04-13.
 */
public class BtServer
{
    private final BluetoothSocket btSocket;
    private final BluetoothDevice btDevice;
    TextView X;
    TextView Z;

    public BtServer (BluetoothDevice device,TextView U, TextView F)
    {
        BluetoothSocket tmp = null;
        btDevice = device;
        try
        {
            UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
            tmp = device.createRfcommSocketToServiceRecord(uuid);

        } catch(Exception e) {};
        btSocket = tmp;
        X=U;
        Z=F;
        Log.d("BTC", "TworzenieClienta");
    }

    public void run()
    {
        try{

            btSocket.connect();
            Log.d("BTC","Polaczono z serwerem");
            PrintWriter out = new PrintWriter(btSocket.getOutputStream());
           // while(true)
            {
                //out.println(String.valueOf(X.getText()) + " "+ String.valueOf(Z.getText()));
                Double x = Double.valueOf(String.valueOf(X.getText()));
                Double z = Double.valueOf(String.valueOf(Z.getText()));
                x = (x/10)*128 + 128;
                z=  (z/10) *128 + 128;

                out.write(x.intValue());
                out.write(z.intValue());
                Log.d("BTC", "cos poszlo +  "+ x.intValue() + ' '  + z.intValue());
                out.flush();
                //SystemClock.sleep(600);
                btSocket.close();
                Log.d("BTC","zakonczono polaczenie");
            }
        }catch(Exception e)
        {
           try
            {
                btSocket.close();
                Log.d("BTC","polaczenie nieudane");
            } catch (Exception x)
            {
            }
        }
    }
}
