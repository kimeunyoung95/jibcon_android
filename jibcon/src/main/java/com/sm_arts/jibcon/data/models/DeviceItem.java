package com.sm_arts.jibcon.data.models;

/**
 * Created by admin on 2017-04-06.
 */

public class DeviceItem {
    String deviceType;//디바이스 메뉴의 int 값. int 값으로 판별
    String deviceName;//디바이스 메뉴 아이템 이름 ex) 전등 알람 등등..
    String deviceWifiAddr;
    String id;
    String deviceCom;
    boolean deviceOnOffState;
    String user;
    String mqttTopic;
    String aeName;
    String cntName;

    public DeviceItem(int deviceType, String deviceName) {
        this.deviceType = new Integer(deviceType).toString();
        this.deviceName = deviceName;
    }

    public String getAeName() {
        return "ae-firstled";
//        return aeName;
    }

    public void setAeName(String aeName) {
        this.aeName = aeName;
    }

    public String getCntName() {
//        return cntName;
        return "cnt-led";
    }

    public void setCntName(String cntName) {
        this.cntName = cntName;
    }

    public String getMqttTopic() {
        return mqttTopic;
    }

    public void setMqttTopic(String mqttTopic) {
        this.mqttTopic = mqttTopic;
    }

    public void setDeviceWifiAddr(String deviceWifiAddr) {
        this.deviceWifiAddr = deviceWifiAddr;
    }



    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public boolean isDeviceOnOffState() {
        return deviceOnOffState;
    }

    public void setDeviceOnOffState(boolean deviceOnOffState) {
        this.deviceOnOffState = deviceOnOffState;
    }

    public String getDeviceCom() {
        return deviceCom;
    }

    public void setDeviceCom(String deviceCom) {
        this.deviceCom = deviceCom;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
