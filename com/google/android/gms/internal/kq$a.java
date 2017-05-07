// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.result.SessionReadResult;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class kq$a extends Binder implements kq
{
    public kq$a() {
        this.attachInterface((IInterface)this, "com.google.android.gms.fitness.internal.ISessionReadCallback");
    }
    
    public static kq au(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.fitness.internal.ISessionReadCallback");
        if (queryLocalInterface != null && queryLocalInterface instanceof kq) {
            return (kq)queryLocalInterface;
        }
        return new kq$a$a(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("com.google.android.gms.fitness.internal.ISessionReadCallback");
                return true;
            }
            case 1: {
                parcel.enforceInterface("com.google.android.gms.fitness.internal.ISessionReadCallback");
                SessionReadResult sessionReadResult;
                if (parcel.readInt() != 0) {
                    sessionReadResult = (SessionReadResult)SessionReadResult.CREATOR.createFromParcel(parcel);
                }
                else {
                    sessionReadResult = null;
                }
                this.a(sessionReadResult);
                parcel2.writeNoException();
                return true;
            }
        }
    }
}
