// 
// Decompiled by Procyon v0.5.30
// 

package com.vailsys.whistleengine;

import android.hardware.Sensor;
import android.content.Context;
import android.hardware.SensorManager;
import android.hardware.SensorEventListener;

public class ProximitySensor
{
    static final float NEAR_THRESHOLD_CM = 2.0f;
    boolean covered;
    boolean enabled;
    SensorEventListener eventListener;
    ProximityListener listener;
    SensorManager manager;
    
    public ProximitySensor(final Context context, final ProximityListener listener) {
        this.eventListener = (SensorEventListener)new ProximitySensor$1(this);
        this.covered = false;
        this.enabled = false;
        this.listener = listener;
        this.manager = (SensorManager)context.getSystemService("sensor");
    }
    
    public void setEnabled(final boolean enabled) {
        if (this.enabled != enabled) {
            this.enabled = enabled;
            try {
                if (!this.enabled) {
                    this.manager.unregisterListener(this.eventListener);
                    return;
                }
                final Sensor defaultSensor = this.manager.getDefaultSensor(8);
                if (defaultSensor != null) {
                    this.manager.registerListener(this.eventListener, defaultSensor, 3);
                }
            }
            catch (Exception ex) {}
        }
    }
}
