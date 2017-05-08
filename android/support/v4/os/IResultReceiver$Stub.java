// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.os;

import android.os.Bundle;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class IResultReceiver$Stub extends Binder implements IResultReceiver
{
    private static final String DESCRIPTOR = "android.support.v4.os.IResultReceiver";
    static final int TRANSACTION_send = 1;
    
    public IResultReceiver$Stub() {
        this.attachInterface((IInterface)this, "android.support.v4.os.IResultReceiver");
    }
    
    public static IResultReceiver asInterface(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("android.support.v4.os.IResultReceiver");
        if (queryLocalInterface != null && queryLocalInterface instanceof IResultReceiver) {
            return (IResultReceiver)queryLocalInterface;
        }
        return new IResultReceiver$Stub$Proxy(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(int int1, final Parcel parcel, final Parcel parcel2, final int n) {
        switch (int1) {
            default: {
                return super.onTransact(int1, parcel, parcel2, n);
            }
            case 1598968902: {
                parcel2.writeString("android.support.v4.os.IResultReceiver");
                return true;
            }
            case 1: {
                parcel.enforceInterface("android.support.v4.os.IResultReceiver");
                int1 = parcel.readInt();
                Bundle bundle;
                if (parcel.readInt() != 0) {
                    bundle = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                else {
                    bundle = null;
                }
                this.send(int1, bundle);
                return true;
            }
        }
    }
}
