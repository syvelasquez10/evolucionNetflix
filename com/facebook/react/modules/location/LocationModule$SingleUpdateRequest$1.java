// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.modules.location;

import android.os.Looper;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.Handler;
import com.facebook.react.bridge.Callback;

class LocationModule$SingleUpdateRequest$1 implements Runnable
{
    final /* synthetic */ LocationModule$SingleUpdateRequest this$0;
    
    LocationModule$SingleUpdateRequest$1(final LocationModule$SingleUpdateRequest this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        synchronized (this.this$0) {
            if (!this.this$0.mTriggered) {
                this.this$0.mError.invoke(PositionError.buildError(PositionError.TIMEOUT, "Location request timed out"));
                this.this$0.mLocationManager.removeUpdates(this.this$0.mLocationListener);
                this.this$0.mTriggered = true;
            }
        }
    }
}
