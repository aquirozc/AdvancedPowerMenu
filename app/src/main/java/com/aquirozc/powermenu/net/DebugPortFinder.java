package com.aquirozc.powermenu.net;

import android.util.Log;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceEvent;
import javax.jmdns.ServiceInfo;
import javax.jmdns.ServiceListener;

public class DebugPortFinder {

    static class SampleListener implements ServiceListener {
        @Override
        public void serviceAdded(ServiceEvent event) {
            System.out.println("Service added   : " + event.getName() + "." + event.getType());
        }

        @Override
        public void serviceRemoved(ServiceEvent event) {
            System.out.println("Service removed : " + event.getName() + "." + event.getType());
        }

        @Override
        public void serviceResolved(ServiceEvent event) {
            System.out.println("Service resolved: " + event.getInfo());
        }
    }

    public static int findPort(){

            int port = 5555;

            try{
                JmDNS jmDNS = JmDNS.create();

                for (ServiceInfo s: jmDNS.list("_adb-tls-connect._tcp.local.")){
                   port = s.getPort();
                }

            }catch (Exception e){
                Log.e("PORT_FINDER", e.getMessage());
            }


            return  port;

    }

}
