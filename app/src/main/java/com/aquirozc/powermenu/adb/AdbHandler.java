package com.aquirozc.powermenu.adb;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import android.widget.Toast;

import com.tananaev.adblib.AdbConnection;
import com.tananaev.adblib.AdbCrypto;

import java.net.Socket;

public class AdbHandler {

    Context context;
    Handler handler;
    AdbCrypto crypto;

    public AdbHandler(Context context){

        HandlerThread thread = new HandlerThread("ADB_THREAD");
        thread.start();

        handler = new Handler(thread.getLooper());
        crypto = KeyLoader.loadKeyPair(context.getFilesDir());

        this.context = context;
    }

    public void sendRequest(PowerOption c) {

        Toast.makeText(context, c.command, Toast.LENGTH_SHORT).show();

        handler.post(() -> {
          try(Socket socket = new Socket("localhost",5555);
              AdbConnection connection = AdbConnection.create(socket,crypto)){

            connection.connect();
            connection.open(c.command).read();

          } catch (Exception e){
              Log.e("ADB_HANDLER", e.getMessage());
          }
       });

    }

}
