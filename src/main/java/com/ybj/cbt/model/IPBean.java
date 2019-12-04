package com.ybj.cbt.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IPBean {

    private String IPAddress;
    private Integer IpPosrt;
    private String serverAddress;
    private String anonymityType;
    private String protocolType;
}
