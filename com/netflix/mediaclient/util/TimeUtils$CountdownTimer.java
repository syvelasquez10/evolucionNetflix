// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.android.activity.NetflixActivity;

public class TimeUtils$CountdownTimer
{
    private int mTimer;
    private final NetflixActivity netflixActivity;
    private final Runnable onEverySecond;
    private Runnable onFinish;
    private Runnable onTick;
    private boolean running;
    
    public TimeUtils$CountdownTimer(final NetflixActivity netflixActivity) {
        this.onEverySecond = new TimeUtils$CountdownTimer$1(this);
        this.netflixActivity = netflixActivity;
    }
    
    public int getTime() {
        return this.mTimer;
    }
    
    public void setOnFinish(final Runnable onFinish) {
        this.onFinish = onFinish;
    }
    
    public void setOnTick(final Runnable onTick) {
        this.onTick = onTick;
    }
    
    public void setTime(final int mTimer) {
        this.mTimer = mTimer;
    }
    
    public void startTimer() {
        if (!this.running && this.mTimer > 0) {
            this.running = true;
            this.netflixActivity.getHandler().postDelayed(this.onEverySecond, 1000L);
        }
    }
    
    public void stopTimer() {
        this.running = false;
    }
}
