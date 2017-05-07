// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

public interface Geofence
{
    public static final int GEOFENCE_TRANSITION_DWELL = 4;
    public static final int GEOFENCE_TRANSITION_ENTER = 1;
    public static final int GEOFENCE_TRANSITION_EXIT = 2;
    public static final long NEVER_EXPIRE = -1L;
    
    String getRequestId();
}
