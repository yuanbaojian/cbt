package com.ybj.cbt.model;

/**
 * @Author Website
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public abstract class Website {

    private String URL;
    private String selector;
    private String attr;
    private String descFolder;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getSelector() {
        return selector;
    }

    public void setSelector(String selector) {
        this.selector = selector;
    }

    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        this.attr = attr;
    }

    public String getDescFolder() {
        return descFolder;
    }

    public void setDescFolder(String descFolder) {
        this.descFolder = descFolder;
    }

    public  abstract String getPureSrc(String rawSrc);


}
