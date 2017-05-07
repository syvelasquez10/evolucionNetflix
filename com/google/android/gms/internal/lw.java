// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.IBinder;
import com.google.android.gms.location.c;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.location.LocationRequest;
import java.util.List;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.location.a;
import android.location.Location;
import android.app.PendingIntent;
import android.os.IInterface;

public interface lw extends IInterface
{
    void a(final long p0, final boolean p1, final PendingIntent p2);
    
    void a(final PendingIntent p0);
    
    void a(final PendingIntent p0, final lv p1, final String p2);
    
    void a(final Location p0, final int p1);
    
    void a(final lv p0, final String p1);
    
    void a(final lz p0, final PendingIntent p1);
    
    void a(final lz p0, final a p1);
    
    void a(final mg p0, final mw p1, final PendingIntent p2);
    
    void a(final mi p0, final mw p1, final mu p2);
    
    void a(final mk p0, final mw p1);
    
    void a(final mm p0, final mw p1, final PendingIntent p2);
    
    void a(final mq p0, final mw p1, final mu p2);
    
    void a(final ms p0, final LatLngBounds p1, final List<String> p2, final mw p3, final mu p4);
    
    void a(final mw p0, final PendingIntent p1);
    
    void a(final LocationRequest p0, final PendingIntent p1);
    
    void a(final LocationRequest p0, final a p1);
    
    void a(final LocationRequest p0, final a p1, final String p2);
    
    void a(final a p0);
    
    void a(final LatLng p0, final mi p1, final mw p2, final mu p3);
    
    void a(final LatLngBounds p0, final int p1, final mi p2, final mw p3, final mu p4);
    
    void a(final LatLngBounds p0, final int p1, final String p2, final mi p3, final mw p4, final mu p5);
    
    void a(final String p0, final mw p1, final mu p2);
    
    void a(final String p0, final LatLngBounds p1, final me p2, final mw p3, final mu p4);
    
    void a(final List<mb> p0, final PendingIntent p1, final lv p2, final String p3);
    
    void a(final String[] p0, final lv p1, final String p2);
    
    void b(final mw p0, final PendingIntent p1);
    
    void b(final String p0, final mw p1, final mu p2);
    
    Location bT(final String p0);
    
    c bU(final String p0);
    
    Location lT();
    
    IBinder lU();
    
    IBinder lV();
    
    void removeActivityUpdates(final PendingIntent p0);
    
    void setMockLocation(final Location p0);
    
    void setMockMode(final boolean p0);
}
