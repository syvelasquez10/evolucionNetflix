// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.service.FitnessSensorServiceRequest;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class lj$a extends Binder implements lj
{
    public lj$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.fitness.internal.service.IFitnessSensorService");
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final FitnessSensorServiceRequest fitnessSensorServiceRequest = null;
        final lh lh = null;
        final lf lf = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.fitness.internal.service.IFitnessSensorService");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.service.IFitnessSensorService");
                lf lf2 = lf;
                if (parcel.readInt() != 0) {
                    lf2 = (lf)com.google.android.gms.internal.lf.CREATOR.createFromParcel(parcel);
                }
                this.a(lf2, km$a.aq(parcel.readStrongBinder()));
                return true;
            }
            case 2: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.service.IFitnessSensorService");
                FitnessSensorServiceRequest fitnessSensorServiceRequest2 = fitnessSensorServiceRequest;
                if (parcel.readInt() != 0) {
                    fitnessSensorServiceRequest2 = (FitnessSensorServiceRequest)FitnessSensorServiceRequest.CREATOR.createFromParcel(parcel);
                }
                this.a(fitnessSensorServiceRequest2, ks$a.aw(parcel.readStrongBinder()));
                return true;
            }
            case 3: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.service.IFitnessSensorService");
                lh lh2 = lh;
                if (parcel.readInt() != 0) {
                    lh2 = (lh)com.google.android.gms.internal.lh.CREATOR.createFromParcel(parcel);
                }
                this.a(lh2, ks$a.aw(parcel.readStrongBinder()));
                return true;
            }
        }
    }
}
