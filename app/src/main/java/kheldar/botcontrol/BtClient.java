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
public class BtClient extends Thread
{
    private final BluetoothSocket btSocket;
    private final BluetoothDevice btDevice;
    TextView X;
    TextView Y;

    public BtClient (BluetoothDevice device,TextView U, TextView F)
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
        Y=F;
        Log.d("BTC", "TworzenieClienta");
    }

    public void run()
    {
        try{

            btSocket.connect();
            Log.d("BTC","Polaczono z serwerem");
            PrintWriter out = new PrintWriter(btSocket.getOutputStream());
            while(true)
            {
              //out.println(String.valueOf(X.getText()) + " "+ String.valueOf(Z.getText()));
                Double x = Double.valueOf(String.valueOf(X.getText()));
                Double y = Double.valueOf(String.valueOf(Y.getText()));
                x = x* 12.8 + 128;
                y=  y * 12.8 + 128;

                if(x>255) x=255.0;
                if(y>255) y= 255.0;
                if(x<0)x=0.0;
                if(y<0) y =0.0;

                //char p;
                //p = (char) ((char)(x.intValue() << 8) + (char)z.intValue());
                //Log.d("BTS","p = " + p);

                //x=230.0;
                //y=254.0;
                //int PT = x.intValue() << 8;
                //int send = PT + y.intValue() ;



           //     out.write(x.byteValue());
             //   out.flush();
               // out.write(y.byteValue());
                //out.flush();
                if(x<10)
                    out.print("00" + x.intValue());
                else if(x<100)
                    out.print("0" + x.intValue());
                else
                    out.print(x.intValue());

                out.flush();
                if(y<10)
                    out.print("00" + y.intValue());
                else if(y<100)
                    out.print("0" + y.intValue());
                else
                    out.print(y.intValue());
                out.flush();
               // Log.d("BTC", "wysylamyyy daaane +  " + send + ' ' +Integer.toHexString(send));
                //out.write(x.intValue());
                //out.flush();
                //out.write(z.intValue());
                Log.d("BTC", "wysylamyyy daaane +  "+ x.intValue() + ' '  + y.intValue());
                //out.flush();
                SystemClock.sleep(100);
            }
            }catch(Exception e)
        {
            try
            {
              btSocket.close();
                Log.d("BTC","nie polaczono z serwerem");
            } catch (Exception x)
            {
            }
        }
    }

    @Override
    public void interrupt()
    {
        try
        {
            btSocket.close();
            Log.d("BTC","Zakonczono polaczenie");
            super.interrupt();
        } catch (Exception x)
        {
        }
    }
}
