// 
// Decompiled by Procyon v0.5.30
// 

package com.vailsys.whistleengine;

import android.hardware.SensorEvent;
import android.hardware.Sensor;
import android.hardware.SensorEventListener;

class ProximitySensor$1 implements SensorEventListener
{
    final /* synthetic */ ProximitySensor this$0;
    
    ProximitySensor$1(final ProximitySensor this$0) {
        this.this$0 = this$0;
    }
    
    public void onAccuracyChanged(final Sensor sensor, final int n) {
    }
    
    public void onSensorChanged(final SensorEvent sensorEvent) {
        final boolean b = false;
        if (sensorEvent.sensor.getType() == 8) {
            final float n = sensorEvent.values[0];
            boolean covered = b;
            if (n < sensorEvent.sensor.getMaximumRange()) {
                covered = b;
                if (n <= 2.0f) {
                    covered = true;
                }
            }
            if (covered != this.this$0.covered) {
                this.this$0.covered = covered;
                this.this$0.listener.sensorUpdate(covered);
            }
        }
    }
}
