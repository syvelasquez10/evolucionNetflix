// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.network.connectionclass;

import android.util.Log;
import android.os.SystemClock;
import android.net.TrafficStats;
import android.os.HandlerThread;
import java.util.concurrent.atomic.AtomicInteger;

public class DeviceBandwidthSampler
{
    private static long sPreviousBytes;
    private final ConnectionClassManager mConnectionClassManager;
    private DeviceBandwidthSampler$SamplingHandler mHandler;
    private long mLastTimeReading;
    private AtomicInteger mSamplingCounter;
    private HandlerThread mThread;
    
    static {
        DeviceBandwidthSampler.sPreviousBytes = -1L;
    }
    
    private DeviceBandwidthSampler(final ConnectionClassManager mConnectionClassManager) {
        this.mConnectionClassManager = mConnectionClassManager;
        this.mSamplingCounter = new AtomicInteger();
        (this.mThread = new HandlerThread("ParseThread")).start();
        this.mHandler = new DeviceBandwidthSampler$SamplingHandler(this, this.mThread.getLooper());
    }
    
    public static DeviceBandwidthSampler getInstance() {
        return DeviceBandwidthSampler$DeviceBandwidthSamplerHolder.instance;
    }
    
    protected void addFinalSample() {
        this.addSample();
        DeviceBandwidthSampler.sPreviousBytes = -1L;
    }
    
    protected void addSample() {
        final long totalRxBytes = TrafficStats.getTotalRxBytes();
        final long n = totalRxBytes - DeviceBandwidthSampler.sPreviousBytes;
        Label_0096: {
            if (DeviceBandwidthSampler.sPreviousBytes < 0L) {
                break Label_0096;
            }
            synchronized (this) {
                final long elapsedRealtime = SystemClock.elapsedRealtime();
                final long n2 = elapsedRealtime - this.mLastTimeReading;
                this.mConnectionClassManager.addBandwidth(n, n2);
                this.mLastTimeReading = elapsedRealtime;
                if (Log.isLoggable("DeviceBandwidthSampler", 2)) {
                    Log.v("DeviceBandwidthSampler", "addSample(), bytes: " + n + ", time delta: " + n2);
                }
                // monitorexit(this)
                DeviceBandwidthSampler.sPreviousBytes = totalRxBytes;
            }
        }
    }
    
    public void startSampling() {
        if (this.mSamplingCounter.getAndIncrement() == 0) {
            this.mHandler.startSamplingThread();
            this.mLastTimeReading = SystemClock.elapsedRealtime();
        }
    }
    
    public void stopSampling() {
        if (this.mSamplingCounter.decrementAndGet() == 0) {
            this.mHandler.stopSamplingThread();
            this.addFinalSample();
        }
    }
}
