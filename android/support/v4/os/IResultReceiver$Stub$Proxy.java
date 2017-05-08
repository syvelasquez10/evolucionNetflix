// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.os;

import android.os.Parcel;
import android.os.Bundle;
import android.os.IBinder;

class IResultReceiver$Stub$Proxy implements IResultReceiver
{
    private IBinder mRemote;
    
    IResultReceiver$Stub$Proxy(final IBinder mRemote) {
        this.mRemote = mRemote;
    }
    
    public IBinder asBinder() {
        return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
        return "android.support.v4.os.IResultReceiver";
    }
    
    @Override
    public void send(final int n, final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.os.IResultReceiver");
            obtain.writeInt(n);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.mRemote.transact(1, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
}
