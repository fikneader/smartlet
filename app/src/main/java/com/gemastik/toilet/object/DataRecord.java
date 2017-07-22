package com.gemastik.toilet.object;

/**
 * Created by anjas on 31/03/17.
 */

public class DataRecord {
    public int level;
    public int urin;
    public String location;
    public String created_at;

    public DataRecord() {
    }

    public DataRecord(String created_at, int level, String location, int urin){
        this.created_at = created_at;
        this.level = level;
        this.location = location;
        this.urin = urin;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getUrin() {
        return urin;
    }

    public void setUrin(int urin) {
        this.urin = urin;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
