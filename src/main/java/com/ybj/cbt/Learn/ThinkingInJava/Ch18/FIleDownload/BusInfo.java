package com.ybj.cbt.Learn.ThinkingInJava.Ch18.FIleDownload;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author BusInfo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Getter
@Setter
public class BusInfo {

    String attributes;
    String terminal;
    long stopdis;
    String distance;
    String time;

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public long getStopdis() {
        return stopdis;
    }

    public void setStopdis(long stopdis) {
        this.stopdis = stopdis;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
