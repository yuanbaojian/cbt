package com.ybj.cbt.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IPBean {

    private String IpAddress;
    private Integer IpPort;
    private String serverAddress;
    private String anonymityType;
    private String protocolType;

    @Override
    public String toString() {
        return "IPBean{" +
                "IpAddress='" + IpAddress + '\'' +
                ", IpPort=" + IpPort +
                ", serverAddress='" + serverAddress + '\'' +
                ", anonymityType='" + anonymityType + '\'' +
                ", protocolType='" + protocolType + '\'' +
                '}';
    }
}
