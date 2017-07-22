package com.gemastik.toilet.object;

/**
 * Created by anjas on 30/03/17.
 */

public class DataRiwayat {
    private int urin;
    private String time;
    private String location;

    public DataRiwayat(int urin, String time, String location) {
        this.urin = urin;
        this.time = time;
        this.location = location;
    }

    public int getUrin() {
        return urin;
    }

    public void setUrin(int urin) {
        this.urin = urin;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
