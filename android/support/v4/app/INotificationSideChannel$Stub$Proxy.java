// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.Notification;
import android.os.Parcel;
import android.os.IBinder;

class INotificationSideChannel$Stub$Proxy implements INotificationSideChannel
{
    private IBinder mRemote;
    
    INotificationSideChannel$Stub$Proxy(final IBinder mRemote) {
        this.mRemote = mRemote;
    }
    
    public IBinder asBinder() {
        return this.mRemote;
    }
    
    @Override
    public void cancel(final String s, final int n, final String s2) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
            obtain.writeString(s);
            obtain.writeInt(n);
            obtain.writeString(s2);
            this.mRemote.transact(2, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
    
    @Override
    public void cancelAll(final String s) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
            obtain.writeString(s);
            this.mRemote.transact(3, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
    
    public String getInterfaceDescriptor() {
        return "android.support.v4.app.INotificationSideChannel";
    }
    
    @Override
    public void notify(final String s, final int n, final String s2, final Notification notification) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.app.INotificationSideChannel");
            obtain.writeString(s);
            obtain.writeInt(n);
            obtain.writeString(s2);
            if (notification != null) {
                obtain.writeInt(1);
                notification.writeToParcel(obtain, 0);
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
