// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class zzfk$zza extends Binder implements zzfk
{
    public zzfk$zza() {
        this.attachInterface((IInterface)this, "com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
    }
    
    public static zzfk zzL(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
        if (queryLocalInterface != null && queryLocalInterface instanceof zzfk) {
            return (zzfk)queryLocalInterface;
        }
        return new zzfk$zza$zza(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final Bundle bundle = null;
        Bundle bundle2 = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                if (parcel.readInt() != 0) {
                    bundle2 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.onCreate(bundle2);
                parcel2.writeNoException();
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                this.onRestart();
                parcel2.writeNoException();
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                this.onStart();
                parcel2.writeNoException();
                return true;
            }
            case 4: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                this.onResume();
                parcel2.writeNoException();
                return true;
            }
            case 5: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                this.onPause();
                parcel2.writeNoException();
                return true;
            }
            case 6: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                Bundle bundle3 = bundle;
                if (parcel.readInt() != 0) {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.onSaveInstanceState(bundle3);
                parcel2.writeNoException();
                if (bundle3 != null) {
                    parcel2.writeInt(1);
                    bundle3.writeToParcel(parcel2, 1);
                    return true;
                }
                parcel2.writeInt(0);
                return true;
            }
            case 7: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                this.onStop();
                parcel2.writeNoException();
                return true;
            }
            case 8: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                this.onDestroy();
                parcel2.writeNoException();
                return true;
            }
            case 9: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                this.zzaE();
                parcel2.writeNoException();
                return true;
            }
            case 10: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                this.onBackPressed();
                parcel2.writeNoException();
                return true;
            }
            case 11: {
                parcel.enforceInterface("com.google.android.gms.ads.internal.overlay.client.IAdOverlay");
                final boolean zzeF = this.zzeF();
                parcel2.writeNoException();
                if (zzeF) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                parcel2.writeInt(n);
                return true;
            }
        }
    }
}
