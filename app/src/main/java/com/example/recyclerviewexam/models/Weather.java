
package com.example.recyclerviewexam.models;


public class Weather {

    private String ts;
    private int hu;
    private String ic;
    private int pr;
    private int tp;
    private int wd;
    private double ws;
    private String updatedAt;

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public int getHu() {
        return hu;
    }

    public void setHu(int hu) {
        this.hu = hu;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    public int getPr() {
        return pr;
    }

    public void setPr(int pr) {
        this.pr = pr;
    }

    public int getTp() {
        return tp;
    }

    public void setTp(int tp) {
        this.tp = tp;
    }

    public int getWd() {
        return wd;
    }

    public void setWd(int wd) {
        this.wd = wd;
    }

    public double getWs() {
        return ws;
    }

    public void setWs(double ws) {
        this.ws = ws;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
