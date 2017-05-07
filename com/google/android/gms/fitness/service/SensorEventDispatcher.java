// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.fitness.service;

import java.util.List;
import com.google.android.gms.fitness.data.DataPoint;

public interface SensorEventDispatcher
{
    void publish(final DataPoint p0);
    
    void publish(final List<DataPoint> p0);
}
