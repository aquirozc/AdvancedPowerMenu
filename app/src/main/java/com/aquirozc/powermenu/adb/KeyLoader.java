package com.aquirozc.powermenu.adb;

import android.util.Log;

import com.tananaev.adblib.AdbCrypto;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class KeyLoader {

    private static final String PUBLIC_KEY = "public_key.bin";
    private static final String PRIVATE_KEY = "private_key.bin";

    private static final AdbBase64Encoder encoder = new AdbBase64Encoder();

    public static AdbCrypto loadKeyPair(File target){

        AdbCrypto crypto = null;
        File publicKey = new File(target,PUBLIC_KEY);
        File privateKey = new File(target,PRIVATE_KEY);

        try{

            if(publicKey.exists() && privateKey.exists()){
                crypto = AdbCrypto.loadAdbKeyPair(encoder,privateKey,publicKey);
                Log.e("KEY_LOADER", "KEYS DO EXITS");
                return crypto;
            }

            crypto = AdbCrypto.generateAdbKeyPair(encoder);
            crypto.saveAdbKeyPair(privateKey,publicKey);
            Log.e("KEY_LOADER", "KEYS NOT EXITS");

        }catch (IOException | InvalidKeySpecException exception){
            Log.e("KEY_LOADER", exception.getMessage());
        }catch (NoSuchAlgorithmException exception){
            Log.e("KEY_LOADER", exception.getMessage());
        }

        return  crypto;
    }

}
