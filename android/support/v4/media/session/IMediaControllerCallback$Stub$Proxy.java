// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.text.TextUtils;
import java.util.List;
import android.support.v4.media.MediaMetadataCompat;
import android.os.Parcel;
import android.os.Bundle;
import android.os.IBinder;

class IMediaControllerCallback$Stub$Proxy implements IMediaControllerCallback
{
    private IBinder mRemote;
    
    IMediaControllerCallback$Stub$Proxy(final IBinder mRemote) {
        this.mRemote = mRemote;
    }
    
    public IBinder asBinder() {
        return this.mRemote;
    }
    
    public String getInterfaceDescriptor() {
        return "android.support.v4.media.session.IMediaControllerCallback";
    }
    
    @Override
    public void onEvent(final String s, final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            obtain.writeString(s);
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
    
    @Override
    public void onExtrasChanged(final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.mRemote.transact(7, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
    
    @Override
    public void onMetadataChanged(final MediaMetadataCompat mediaMetadataCompat) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            if (mediaMetadataCompat != null) {
                obtain.writeInt(1);
                mediaMetadataCompat.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.mRemote.transact(4, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
    
    @Override
    public void onPlaybackStateChanged(final PlaybackStateCompat playbackStateCompat) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            if (playbackStateCompat != null) {
                obtain.writeInt(1);
                playbackStateCompat.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.mRemote.transact(3, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
    
    @Override
    public void onQueueChanged(final List<MediaSessionCompat$QueueItem> list) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            obtain.writeTypedList((List)list);
            this.mRemote.transact(5, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
    
    @Override
    public void onQueueTitleChanged(final CharSequence charSequence) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            if (charSequence != null) {
                obtain.writeInt(1);
                TextUtils.writeToParcel(charSequence, obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.mRemote.transact(6, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
    
    @Override
    public void onSessionDestroyed() {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            this.mRemote.transact(2, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
    
    @Override
    public void onVolumeInfoChanged(final ParcelableVolumeInfo parcelableVolumeInfo) {
        final Parcel obtain = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");
            if (parcelableVolumeInfo != null) {
                obtain.writeInt(1);
                parcelableVolumeInfo.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.mRemote.transact(8, obtain, (Parcel)null, 1);
        }
        finally {
            obtain.recycle();
        }
    }
}
