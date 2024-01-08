package com.aquirozc.powermenu.adb;

import com.tananaev.adblib.AdbBase64;
import java.util.Base64;

public class AdbBase64Encoder implements AdbBase64 {

    @Override
    public String encodeToString(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

}
