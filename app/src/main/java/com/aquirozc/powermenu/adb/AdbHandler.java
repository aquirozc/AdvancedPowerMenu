package com.aquirozc.powermenu.adb;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.tananaev.adblib.AdbConnection;
import com.tananaev.adblib.AdbCrypto;

import java.io.IOException;
import java.net.Socket;

public class AdbHandler {

   AdbCrypto crypto;
   Context context;

    public AdbHandler(AdbCrypto crypto, Context context){


        this.context = context;
        this.crypto = crypto;

    }

    public void sendRequest(PowerOption c) {

        Toast.makeText(context,c.command,Toast.LENGTH_SHORT).show();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        HandlerThread thread = new HandlerThread("adbthread");
        thread.start();

        Handler handler = new Handler(thread.getLooper());

       handler.post(() -> {
           AdbConnection connection = null;
           try(Socket socket = new Socket("localhost", 5555)){
               connection = AdbConnection.create(socket,crypto);
               try{
                   connection.connect();
                   connection.open(c.command);
                   connection.close();
               }catch (Exception e){
                   Log.e("ADB_HANDLER", e.getMessage());
               }
           } catch (IOException e) {
               Log.e("ADB_HANDLER", e.getMessage());
           }
       });
    }

}
