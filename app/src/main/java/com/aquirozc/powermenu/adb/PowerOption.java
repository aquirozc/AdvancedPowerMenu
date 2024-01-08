package com.aquirozc.powermenu.adb;

public enum PowerOption {

    POWER_OFF("shell: reboot -p"),
    REBOOT("shell: reboot"),
    RECOVERY("shell: reboot recovery"),
    FASTBOOT("shell: reboot fastboot");


    public final String command;
    private PowerOption (String command){
        this.command = command;
    }
}
