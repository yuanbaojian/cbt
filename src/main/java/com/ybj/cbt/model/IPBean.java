package com.ybj.cbt.model;

public class IPBean {

    private String IpAddress;
    private Integer IpPort;
    private String serverAddress;
    private String anonymityType;
    private String protocolType;

    public String getIpAddress() {
        return IpAddress;
    }

    public void setIpAddress(String ipAddress) {
        IpAddress = ipAddress;
    }

    public Integer getIpPort() {
        return IpPort;
    }

    public void setIpPort(Integer ipPort) {
        IpPort = ipPort;
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public String getAnonymityType() {
        return anonymityType;
    }

    public void setAnonymityType(String anonymityType) {
        this.anonymityType = anonymityType;
    }

    public String getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(String protocolType) {
        this.protocolType = protocolType;
    }
}
