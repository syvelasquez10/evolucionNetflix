// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.location;

import android.os.Looper;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Handler;
import com.facebook.react.bridge.Callback;

class LocationModule$SingleUpdateRequest
{
    private final Callback mError;
    private final Handler mHandler;
    private final LocationListener mLocationListener;
    private final LocationManager mLocationManager;
    private final String mProvider;
    private final Callback mSuccess;
    private final long mTimeout;
    private final Runnable mTimeoutRunnable;
    private boolean mTriggered;
    
    private LocationModule$SingleUpdateRequest(final LocationManager mLocationManager, final String mProvider, final long mTimeout, final Callback mSuccess, final Callback mError) {
        this.mHandler = new Handler();
        this.mTimeoutRunnable = new LocationModule$SingleUpdateRequest$1(this);
        this.mLocationListener = (LocationListener)new LocationModule$SingleUpdateRequest$2(this);
        this.mLocationManager = mLocationManager;
        this.mProvider = mProvider;
        this.mTimeout = mTimeout;
        this.mSuccess = mSuccess;
        this.mError = mError;
    }
    
    public void invoke() {
        this.mLocationManager.requestSingleUpdate(this.mProvider, this.mLocationListener, (Looper)null);
        this.mHandler.postDelayed(this.mTimeoutRunnable, this.mTimeout);
    }
}
