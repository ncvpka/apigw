package vn.supperapp.apigw.beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeKeepingEveryDayInfo {
    private String working;
    private String title;
    float totalWorking;
    long totalMinuteLate;

    public String getWorking() {
        return working;
    }

    public void setWorking(String working) {
        this.working = working;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getTotalWorking() {
        return totalWorking;
    }

    public void setTotalWorking(float totalWorking) {
        this.totalWorking = totalWorking;
    }

    public long getTotalMinuteLate() {
        return totalMinuteLate;
    }

    public void setTotalMinuteLate(long totalMinuteLate) {
        this.totalMinuteLate = totalMinuteLate;
    }
}
