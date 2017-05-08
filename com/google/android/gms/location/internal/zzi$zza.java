// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location.internal;

import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.ActivityRecognitionResult;
import java.util.ArrayList;
import com.google.android.gms.location.LocationSettingsRequest;
import android.location.Location;
import com.google.android.gms.location.zzd$zza;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.GestureRequest;
import com.google.android.gms.location.GeofencingRequest;
import java.util.List;
import android.app.PendingIntent;
import android.os.Parcelable$Creator;
import android.os.Parcel;
import android.os.IInterface;
import android.os.IBinder;
import android.os.Binder;

public abstract class zzi$zza extends Binder implements zzi
{
    public static zzi zzcb(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzi) {
            return (zzi)queryLocalInterface;
        }
        return new zzi$zza$zza(binder);
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final boolean b = false;
        boolean b2 = false;
        final LocationRequest locationRequest = null;
        final LocationRequestInternal locationRequestInternal = null;
        final LocationRequestUpdateData locationRequestUpdateData = null;
        LocationRequest fromParcel = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                final ArrayList typedArrayList = parcel.createTypedArrayList((Parcelable$Creator)ParcelableGeofence.CREATOR);
                PendingIntent pendingIntent;
                if (parcel.readInt() != 0) {
                    pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                }
                else {
                    pendingIntent = null;
                }
                this.zza(typedArrayList, pendingIntent, zzh$zza.zzca(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 57: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                GeofencingRequest geofencingRequest;
                if (parcel.readInt() != 0) {
                    geofencingRequest = (GeofencingRequest)GeofencingRequest.CREATOR.createFromParcel(parcel);
                }
                else {
                    geofencingRequest = null;
                }
                PendingIntent pendingIntent2;
                if (parcel.readInt() != 0) {
                    pendingIntent2 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                }
                else {
                    pendingIntent2 = null;
                }
                this.zza(geofencingRequest, pendingIntent2, zzh$zza.zzca(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                PendingIntent pendingIntent3;
                if (parcel.readInt() != 0) {
                    pendingIntent3 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                }
                else {
                    pendingIntent3 = null;
                }
                this.zza(pendingIntent3, zzh$zza.zzca(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                this.zza(parcel.createStringArray(), zzh$zza.zzca(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                this.zza(zzh$zza.zzca(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                final long long1 = parcel.readLong();
                if (parcel.readInt() != 0) {
                    b2 = true;
                }
                PendingIntent pendingIntent4;
                if (parcel.readInt() != 0) {
                    pendingIntent4 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                }
                else {
                    pendingIntent4 = null;
                }
                this.zza(long1, b2, pendingIntent4);
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                PendingIntent pendingIntent5;
                if (parcel.readInt() != 0) {
                    pendingIntent5 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                }
                else {
                    pendingIntent5 = null;
                }
                this.zza(pendingIntent5);
                parcel2.writeNoException();
                return true;
            }
            case 64: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                final ActivityRecognitionResult zzdu = this.zzdu(parcel.readString());
                parcel2.writeNoException();
                if (zzdu != null) {
                    parcel2.writeInt(1);
                    zzdu.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 65: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                PendingIntent pendingIntent6;
                if (parcel.readInt() != 0) {
                    pendingIntent6 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                }
                else {
                    pendingIntent6 = null;
                }
                final Status zzb = this.zzb(pendingIntent6);
                parcel2.writeNoException();
                if (zzb != null) {
                    parcel2.writeInt(1);
                    zzb.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 66: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                PendingIntent pendingIntent7;
                if (parcel.readInt() != 0) {
                    pendingIntent7 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                }
                else {
                    pendingIntent7 = null;
                }
                final Status zzc = this.zzc(pendingIntent7);
                parcel2.writeNoException();
                if (zzc != null) {
                    parcel2.writeInt(1);
                    zzc.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 60: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                GestureRequest zzeA;
                if (parcel.readInt() != 0) {
                    zzeA = GestureRequest.CREATOR.zzeA(parcel);
                }
                else {
                    zzeA = null;
                }
                PendingIntent pendingIntent8;
                if (parcel.readInt() != 0) {
                    pendingIntent8 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                }
                else {
                    pendingIntent8 = null;
                }
                final Status zza = this.zza(zzeA, pendingIntent8);
                parcel2.writeNoException();
                if (zza != null) {
                    parcel2.writeInt(1);
                    zza.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 61: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                PendingIntent pendingIntent9;
                if (parcel.readInt() != 0) {
                    pendingIntent9 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                }
                else {
                    pendingIntent9 = null;
                }
                final Status zzd = this.zzd(pendingIntent9);
                parcel2.writeNoException();
                if (zzd != null) {
                    parcel2.writeInt(1);
                    zzd.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                final Location zzwC = this.zzwC();
                parcel2.writeNoException();
                if (zzwC != null) {
                    parcel2.writeInt(1);
                    zzwC.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                if (parcel.readInt() != 0) {
                    fromParcel = LocationRequest.CREATOR.createFromParcel(parcel);
                }
                this.zza(fromParcel, zzd$zza.zzbX(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 20: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                LocationRequest fromParcel2 = locationRequest;
                if (parcel.readInt() != 0) {
                    fromParcel2 = LocationRequest.CREATOR.createFromParcel(parcel);
                }
                this.zza(fromParcel2, zzd$zza.zzbX(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                LocationRequest fromParcel3;
                if (parcel.readInt() != 0) {
                    fromParcel3 = LocationRequest.CREATOR.createFromParcel(parcel);
                }
                else {
                    fromParcel3 = null;
                }
                PendingIntent pendingIntent10;
                if (parcel.readInt() != 0) {
                    pendingIntent10 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                }
                else {
                    pendingIntent10 = null;
                }
                this.zza(fromParcel3, pendingIntent10);
                parcel2.writeNoException();
                return true;
            }
            case 52: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                LocationRequestInternal zzeH = locationRequestInternal;
                if (parcel.readInt() != 0) {
                    zzeH = LocationRequestInternal.CREATOR.zzeH(parcel);
                }
                this.zza(zzeH, zzd$zza.zzbX(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 53: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                LocationRequestInternal zzeH2;
                if (parcel.readInt() != 0) {
                    zzeH2 = LocationRequestInternal.CREATOR.zzeH(parcel);
                }
                else {
                    zzeH2 = null;
                }
                PendingIntent pendingIntent11;
                if (parcel.readInt() != 0) {
                    pendingIntent11 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                }
                else {
                    pendingIntent11 = null;
                }
                this.zza(zzeH2, pendingIntent11);
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                this.zza(zzd$zza.zzbX(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                PendingIntent pendingIntent12;
                if (parcel.readInt() != 0) {
                    pendingIntent12 = (PendingIntent)PendingIntent.CREATOR.createFromParcel(parcel);
                }
                else {
                    pendingIntent12 = null;
                }
                this.zze(pendingIntent12);
                parcel2.writeNoException();
                return true;
            }
            case 59: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                LocationRequestUpdateData zzeI = locationRequestUpdateData;
                if (parcel.readInt() != 0) {
                    zzeI = LocationRequestUpdateData.CREATOR.zzeI(parcel);
                }
                this.zza(zzeI);
                parcel2.writeNoException();
                return true;
            }
            case 12: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                boolean b3 = b;
                if (parcel.readInt() != 0) {
                    b3 = true;
                }
                this.zzah(b3);
                parcel2.writeNoException();
                return true;
            }
            case 13: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                Location location;
                if (parcel.readInt() != 0) {
                    location = (Location)Location.CREATOR.createFromParcel(parcel);
                }
                else {
                    location = null;
                }
                this.zzc(location);
                parcel2.writeNoException();
                return true;
            }
            case 21: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                final Location zzdv = this.zzdv(parcel.readString());
                parcel2.writeNoException();
                if (zzdv != null) {
                    parcel2.writeInt(1);
                    zzdv.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 26: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                Location location2;
                if (parcel.readInt() != 0) {
                    location2 = (Location)Location.CREATOR.createFromParcel(parcel);
                }
                else {
                    location2 = null;
                }
                this.zza(location2, parcel.readInt());
                parcel2.writeNoException();
                return true;
            }
            case 34: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                final LocationAvailability zzdw = this.zzdw(parcel.readString());
                parcel2.writeNoException();
                if (zzdw != null) {
                    parcel2.writeInt(1);
                    zzdw.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 63: {
                parcel.enforceInterface("com.google.android.gms.location.internal.IGoogleLocationManagerService");
                LocationSettingsRequest locationSettingsRequest;
                if (parcel.readInt() != 0) {
                    locationSettingsRequest = (LocationSettingsRequest)LocationSettingsRequest.CREATOR.createFromParcel(parcel);
                }
                else {
                    locationSettingsRequest = null;
                }
                this.zza(locationSettingsRequest, zzj$zza.zzcc(parcel.readStrongBinder()), parcel.readString());
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
