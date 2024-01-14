package com.aquirozc.powermenu.net;

import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Inet4AddressFactory {

    public static Inet4Address getIpAdress(int ip){

        Inet4Address local = null;

        byte[] raw = new byte[4];
        raw[3] = (byte) (ip >>> 24);
        raw[2] = (byte) (ip >>> 16);
        raw[1] = (byte) (ip >>> 8);
        raw[0] = (byte) ip;

        try {
            local = (Inet4Address) Inet4Address.getByAddress(raw);
        }catch (UnknownHostException e){
            System.out.println("Oops");
        }

        return local;
    }
}
