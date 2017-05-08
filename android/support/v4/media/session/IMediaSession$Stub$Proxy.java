// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.view.KeyEvent;
import android.support.v4.media.RatingCompat;
import android.net.Uri;
import android.text.TextUtils;
import android.os.Parcelable$Creator;
import java.util.List;
import android.support.v4.media.MediaMetadataCompat;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.IBinder;

class IMediaSession$Stub$Proxy implements IMediaSession
{
    private IBinder mRemote;
    
    IMediaSession$Stub$Proxy(final IBinder mRemote) {
        this.mRemote = mRemote;
    }
    
    @Override
    public void adjustVolume(final int n, final int n2, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            obtain.writeInt(n);
            obtain.writeInt(n2);
            obtain.writeString(s);
            this.mRemote.transact(11, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    public IBinder asBinder() {
        return this.mRemote;
    }
    
    @Override
    public void fastForward() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(22, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public Bundle getExtras() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(31, obtain, obtain2, 0);
            obtain2.readException();
            Bundle bundle;
            if (obtain2.readInt() != 0) {
                bundle = (Bundle)Bundle.CREATOR.createFromParcel(obtain2);
            }
            else {
                bundle = null;
            }
            return bundle;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public long getFlags() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(9, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readLong();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    public String getInterfaceDescriptor() {
        return "android.support.v4.media.session.IMediaSession";
    }
    
    @Override
    public PendingIntent getLaunchPendingIntent() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(8, obtain, obtain2, 0);
            obtain2.readException();
            PendingIntent pendingIntent;
            if (obtain2.readInt() != 0) {
                pendingIntent = (PendingIntent)PendingIntent.CREATOR.createFromParcel(obtain2);
            }
            else {
                pendingIntent = null;
            }
            return pendingIntent;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public MediaMetadataCompat getMetadata() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(27, obtain, obtain2, 0);
            obtain2.readException();
            MediaMetadataCompat mediaMetadataCompat;
            if (obtain2.readInt() != 0) {
                mediaMetadataCompat = (MediaMetadataCompat)MediaMetadataCompat.CREATOR.createFromParcel(obtain2);
            }
            else {
                mediaMetadataCompat = null;
            }
            return mediaMetadataCompat;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public String getPackageName() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(6, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public PlaybackStateCompat getPlaybackState() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(28, obtain, obtain2, 0);
            obtain2.readException();
            PlaybackStateCompat playbackStateCompat;
            if (obtain2.readInt() != 0) {
                playbackStateCompat = (PlaybackStateCompat)PlaybackStateCompat.CREATOR.createFromParcel(obtain2);
            }
            else {
                playbackStateCompat = null;
            }
            return playbackStateCompat;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public List<MediaSessionCompat$QueueItem> getQueue() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(29, obtain, obtain2, 0);
            obtain2.readException();
            return (List<MediaSessionCompat$QueueItem>)obtain2.createTypedArrayList((Parcelable$Creator)MediaSessionCompat$QueueItem.CREATOR);
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public CharSequence getQueueTitle() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(30, obtain, obtain2, 0);
            obtain2.readException();
            CharSequence charSequence;
            if (obtain2.readInt() != 0) {
                charSequence = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(obtain2);
            }
            else {
                charSequence = null;
            }
            return charSequence;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public int getRatingType() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(32, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readInt();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public String getTag() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(7, obtain, obtain2, 0);
            obtain2.readException();
            return obtain2.readString();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public ParcelableVolumeInfo getVolumeAttributes() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(10, obtain, obtain2, 0);
            obtain2.readException();
            ParcelableVolumeInfo parcelableVolumeInfo;
            if (obtain2.readInt() != 0) {
                parcelableVolumeInfo = (ParcelableVolumeInfo)ParcelableVolumeInfo.CREATOR.createFromParcel(obtain2);
            }
            else {
                parcelableVolumeInfo = null;
            }
            return parcelableVolumeInfo;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public boolean isTransportControlEnabled() {
        boolean b = false;
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(5, obtain, obtain2, 0);
            obtain2.readException();
            if (obtain2.readInt() != 0) {
                b = true;
            }
            return b;
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void next() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(20, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void pause() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(18, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void play() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(13, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void playFromMediaId(final String s, final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            obtain.writeString(s);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.mRemote.transact(14, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void playFromSearch(final String s, final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            obtain.writeString(s);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.mRemote.transact(15, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void playFromUri(final Uri uri, final Bundle bundle) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                        this.mRemote.transact(16, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                obtain.writeInt(0);
                continue;
            }
        }
    }
    
    @Override
    public void prepare() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(33, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void prepareFromMediaId(final String s, final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            obtain.writeString(s);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.mRemote.transact(34, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void prepareFromSearch(final String s, final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            obtain.writeString(s);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.mRemote.transact(35, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void prepareFromUri(final Uri uri, final Bundle bundle) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    if (uri != null) {
                        obtain.writeInt(1);
                        uri.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                        this.mRemote.transact(36, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                obtain.writeInt(0);
                continue;
            }
        }
    }
    
    @Override
    public void previous() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(21, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void rate(final RatingCompat ratingCompat) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            if (ratingCompat != null) {
                obtain.writeInt(1);
                ratingCompat.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.mRemote.transact(25, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void registerCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            IBinder binder;
            if (mediaControllerCallback != null) {
                binder = mediaControllerCallback.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.mRemote.transact(3, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void rewind() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(23, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void seekTo(final long n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            obtain.writeLong(n);
            this.mRemote.transact(24, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void sendCommand(final String s, final Bundle bundle, final MediaSessionCompat$ResultReceiverWrapper mediaSessionCompat$ResultReceiverWrapper) {
        while (true) {
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            while (true) {
                try {
                    obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                    obtain.writeString(s);
                    if (bundle != null) {
                        obtain.writeInt(1);
                        bundle.writeToParcel(obtain, 0);
                    }
                    else {
                        obtain.writeInt(0);
                    }
                    if (mediaSessionCompat$ResultReceiverWrapper != null) {
                        obtain.writeInt(1);
                        mediaSessionCompat$ResultReceiverWrapper.writeToParcel(obtain, 0);
                        this.mRemote.transact(1, obtain, obtain2, 0);
                        obtain2.readException();
                        return;
                    }
                }
                finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
                obtain.writeInt(0);
                continue;
            }
        }
    }
    
    @Override
    public void sendCustomAction(final String s, final Bundle bundle) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            obtain.writeString(s);
            if (bundle != null) {
                obtain.writeInt(1);
                bundle.writeToParcel(obtain, 0);
            }
            else {
                obtain.writeInt(0);
            }
            this.mRemote.transact(26, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public boolean sendMediaButton(final KeyEvent keyEvent) {
        while (true) {
            boolean b = true;
            final Parcel obtain = Parcel.obtain();
            final Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
                if (keyEvent != null) {
                    obtain.writeInt(1);
                    keyEvent.writeToParcel(obtain, 0);
                }
                else {
                    obtain.writeInt(0);
                }
                this.mRemote.transact(2, obtain, obtain2, 0);
                obtain2.readException();
                if (obtain2.readInt() != 0) {
                    return b;
                }
            }
            finally {
                obtain2.recycle();
                obtain.recycle();
            }
            b = false;
            return b;
        }
    }
    
    @Override
    public void setVolumeTo(final int n, final int n2, final String s) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            obtain.writeInt(n);
            obtain.writeInt(n2);
            obtain.writeString(s);
            this.mRemote.transact(12, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void skipToQueueItem(final long n) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            obtain.writeLong(n);
            this.mRemote.transact(17, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void stop() {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            this.mRemote.transact(19, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
    
    @Override
    public void unregisterCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
        final Parcel obtain = Parcel.obtain();
        final Parcel obtain2 = Parcel.obtain();
        try {
            obtain.writeInterfaceToken("android.support.v4.media.session.IMediaSession");
            IBinder binder;
            if (mediaControllerCallback != null) {
                binder = mediaControllerCallback.asBinder();
            }
            else {
                binder = null;
            }
            obtain.writeStrongBinder(binder);
            this.mRemote.transact(4, obtain, obtain2, 0);
            obtain2.readException();
        }
        finally {
            obtain2.recycle();
            obtain.recycle();
        }
    }
}
