package com.aquirozc.powermenu.main;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.aquirozc.powermenu.R;
import com.aquirozc.powermenu.adb.AdbHandler;
import com.aquirozc.powermenu.adb.KeyLoader;
import com.aquirozc.powermenu.adb.PowerOption;

public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AdbHandler handler = new AdbHandler(KeyLoader.loadKeyPair(getApplicationContext().getFilesDir()),this);

        findViewById(R.id.shutdown_btn).setOnClickListener(e -> handler.sendRequest(PowerOption.POWER_OFF));
        findViewById(R.id.reboot_btn).setOnClickListener(e -> handler.sendRequest(PowerOption.REBOOT));
        findViewById(R.id.recovery_btn).setOnClickListener(e -> handler.sendRequest(PowerOption.RECOVERY));
        findViewById(R.id.fastboot_btn).setOnClickListener(e -> handler.sendRequest(PowerOption.FASTBOOT));

        findViewById(R.id.top_container).setOnClickListener(e -> System.exit(0));
    }

}
