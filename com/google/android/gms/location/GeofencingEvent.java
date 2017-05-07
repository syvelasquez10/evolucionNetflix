// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import java.util.Iterator;
import com.google.android.gms.internal.mb;
import java.util.ArrayList;
import android.content.Intent;
import android.location.Location;
import java.util.List;

public class GeofencingEvent
{
    private final int aee;
    private final List<Geofence> aef;
    private final Location aeg;
    private final int tc;
    
    private GeofencingEvent(final int tc, final int aee, final List<Geofence> aef, final Location aeg) {
        this.tc = tc;
        this.aee = aee;
        this.aef = aef;
        this.aeg = aeg;
    }
    
    public static GeofencingEvent fromIntent(final Intent intent) {
        if (intent == null) {
            return null;
        }
        return new GeofencingEvent(intent.getIntExtra("gms_error_code", -1), getGeofenceTransition(intent), getTriggeringGeofences(intent), (Location)intent.getParcelableExtra("com.google.android.location.intent.extra.triggering_location"));
    }
    
    private static int getGeofenceTransition(final Intent intent) {
        final int intExtra = intent.getIntExtra("com.google.android.location.intent.extra.transition", -1);
        if (intExtra != -1 && (intExtra == 1 || intExtra == 2 || intExtra == 4)) {
            return intExtra;
        }
        return -1;
    }
    
    private static List<Geofence> getTriggeringGeofences(final Intent intent) {
        final ArrayList list = (ArrayList)intent.getSerializableExtra("com.google.android.location.intent.extra.geofence_list");
        if (list == null) {
            return null;
        }
        final ArrayList list2 = new ArrayList<mb>(list.size());
        final Iterator<byte[]> iterator = list.iterator();
        while (iterator.hasNext()) {
            list2.add(mb.h(iterator.next()));
        }
        return (List<Geofence>)list2;
    }
    
    public int getErrorCode() {
        return this.tc;
    }
    
    public int getGeofenceTransition() {
        return this.aee;
    }
    
    public List<Geofence> getTriggeringGeofences() {
        return this.aef;
    }
    
    public Location getTriggeringLocation() {
        return this.aeg;
    }
    
    public boolean hasError() {
        return this.tc != -1;
    }
}
