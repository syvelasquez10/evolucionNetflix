// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.network.connectionclass;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class ConnectionClassManager
{
    private AtomicReference<ConnectionQuality> mCurrentBandwidthConnectionQuality;
    private ExponentialGeometricAverage mDownloadBandwidth;
    private volatile boolean mInitiateStateChange;
    private ArrayList<ConnectionClassManager$ConnectionClassStateChangeListener> mListenerList;
    private AtomicReference<ConnectionQuality> mNextBandwidthConnectionQuality;
    private int mSampleCounter;
    
    private ConnectionClassManager() {
        this.mDownloadBandwidth = new ExponentialGeometricAverage(0.05);
        this.mInitiateStateChange = false;
        this.mCurrentBandwidthConnectionQuality = new AtomicReference<ConnectionQuality>(ConnectionQuality.UNKNOWN);
        this.mListenerList = new ArrayList<ConnectionClassManager$ConnectionClassStateChangeListener>();
    }
    
    public static ConnectionClassManager getInstance() {
        return ConnectionClassManager$ConnectionClassManagerHolder.instance;
    }
    
    private ConnectionQuality mapBandwidthQuality(final double n) {
        if (n < 0.0) {
            return ConnectionQuality.UNKNOWN;
        }
        if (n < 150.0) {
            return ConnectionQuality.POOR;
        }
        if (n < 550.0) {
            return ConnectionQuality.MODERATE;
        }
        if (n < 2000.0) {
            return ConnectionQuality.GOOD;
        }
        return ConnectionQuality.EXCELLENT;
    }
    
    private void notifyListeners() {
        for (int size = this.mListenerList.size(), i = 0; i < size; ++i) {
            this.mListenerList.get(i).onBandwidthStateChange(this.mCurrentBandwidthConnectionQuality.get());
        }
    }
    
    private boolean significantlyOutsideCurrentBand() {
        double n = 150.0;
        if (this.mDownloadBandwidth == null) {
            return false;
        }
        double n2 = 0.0;
        switch (ConnectionClassManager$1.$SwitchMap$com$facebook$network$connectionclass$ConnectionQuality[this.mCurrentBandwidthConnectionQuality.get().ordinal()]) {
            default: {
                return true;
            }
            case 1: {
                n2 = 150.0;
                n = 0.0;
                break;
            }
            case 2: {
                n2 = 550.0;
                break;
            }
            case 3: {
                n2 = 2000.0;
                n = 550.0;
                break;
            }
            case 4: {
                n2 = 3.4028234663852886E38;
                n = 2000.0;
                break;
            }
        }
        final double average = this.mDownloadBandwidth.getAverage();
        if (average > n2) {
            if (average > n2 * 1.25) {
                return true;
            }
        }
        else if (average < 0.8 * n) {
            return true;
        }
        return false;
    }
    
    public void addBandwidth(final long n, final long n2) {
        // monitorenter(this)
        Label_0026: {
            if (n2 != 0L && n * 1.0 / n2 * 8.0 >= 10.0) {
                final double n3 = n * 1.0 / n2;
                try {
                    this.mDownloadBandwidth.addMeasurement(n3 * 8.0);
                    if (this.mInitiateStateChange) {
                        ++this.mSampleCounter;
                        if (this.getCurrentBandwidthQuality() != this.mNextBandwidthConnectionQuality.get()) {
                            this.mInitiateStateChange = false;
                            this.mSampleCounter = 1;
                        }
                        if (this.mSampleCounter >= 5.0 && this.significantlyOutsideCurrentBand()) {
                            this.mInitiateStateChange = false;
                            this.mSampleCounter = 1;
                            this.mCurrentBandwidthConnectionQuality.set(this.mNextBandwidthConnectionQuality.get());
                            this.notifyListeners();
                        }
                        break Label_0026;
                    }
                }
                finally {
                }
                // monitorexit(this)
                if (this.mCurrentBandwidthConnectionQuality.get() != this.getCurrentBandwidthQuality()) {
                    this.mInitiateStateChange = true;
                    this.mNextBandwidthConnectionQuality = new AtomicReference<ConnectionQuality>(this.getCurrentBandwidthQuality());
                }
            }
        }
    }
    // monitorexit(this)
    
    public ConnectionQuality getCurrentBandwidthQuality() {
        synchronized (this) {
            ConnectionQuality connectionQuality;
            if (this.mDownloadBandwidth == null) {
                connectionQuality = ConnectionQuality.UNKNOWN;
            }
            else {
                connectionQuality = this.mapBandwidthQuality(this.mDownloadBandwidth.getAverage());
            }
            return connectionQuality;
        }
    }
    
    public double getDownloadKBitsPerSecond() {
        synchronized (this) {
            double average;
            if (this.mDownloadBandwidth == null) {
                average = -1.0;
            }
            else {
                average = this.mDownloadBandwidth.getAverage();
            }
            return average;
        }
    }
    
    public void remove(final ConnectionClassManager$ConnectionClassStateChangeListener connectionClassManager$ConnectionClassStateChangeListener) {
        if (connectionClassManager$ConnectionClassStateChangeListener != null) {
            this.mListenerList.remove(connectionClassManager$ConnectionClassStateChangeListener);
        }
    }
}
