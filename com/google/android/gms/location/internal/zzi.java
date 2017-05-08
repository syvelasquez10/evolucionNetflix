// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.ActivityRecognitionResult;
import java.util.List;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.zzd;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.GeofencingRequest;
import android.location.Location;
import com.google.android.gms.common.api.Status;
import android.app.PendingIntent;
import com.google.android.gms.location.GestureRequest;
import android.os.IInterface;

public interface zzi extends IInterface
{
    Status zza(final GestureRequest p0, final PendingIntent p1);
    
    void zza(final long p0, final boolean p1, final PendingIntent p2);
    
    void zza(final PendingIntent p0);
    
    void zza(final PendingIntent p0, final zzh p1, final String p2);
    
    void zza(final Location p0, final int p1);
    
    void zza(final GeofencingRequest p0, final PendingIntent p1, final zzh p2);
    
    void zza(final LocationRequest p0, final PendingIntent p1);
    
    void zza(final LocationRequest p0, final zzd p1);
    
    void zza(final LocationRequest p0, final zzd p1, final String p2);
    
    void zza(final LocationSettingsRequest p0, final zzj p1, final String p2);
    
    void zza(final LocationRequestInternal p0, final PendingIntent p1);
    
    void zza(final LocationRequestInternal p0, final zzd p1);
    
    void zza(final LocationRequestUpdateData p0);
    
    void zza(final zzh p0, final String p1);
    
    void zza(final zzd p0);
    
    void zza(final List<ParcelableGeofence> p0, final PendingIntent p1, final zzh p2, final String p3);
    
    void zza(final String[] p0, final zzh p1, final String p2);
    
    void zzah(final boolean p0);
    
    Status zzb(final PendingIntent p0);
    
    Status zzc(final PendingIntent p0);
    
    void zzc(final Location p0);
    
    Status zzd(final PendingIntent p0);
    
    ActivityRecognitionResult zzdu(final String p0);
    
    Location zzdv(final String p0);
    
    LocationAvailability zzdw(final String p0);
    
    void zze(final PendingIntent p0);
    
    Location zzwC();
}
