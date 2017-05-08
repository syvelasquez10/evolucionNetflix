// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.text.TextUtils;
import java.util.List;
import android.os.Parcelable$Creator;
import android.support.v4.media.MediaMetadataCompat;
import android.os.Bundle;
import android.os.Parcel;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;

public abstract class IMediaControllerCallback$Stub extends Binder implements IMediaControllerCallback
{
    private static final String DESCRIPTOR = "android.support.v4.media.session.IMediaControllerCallback";
    static final int TRANSACTION_onEvent = 1;
    static final int TRANSACTION_onExtrasChanged = 7;
    static final int TRANSACTION_onMetadataChanged = 4;
    static final int TRANSACTION_onPlaybackStateChanged = 3;
    static final int TRANSACTION_onQueueChanged = 5;
    static final int TRANSACTION_onQueueTitleChanged = 6;
    static final int TRANSACTION_onSessionDestroyed = 2;
    static final int TRANSACTION_onVolumeInfoChanged = 8;
    
    public IMediaControllerCallback$Stub() {
        this.attachInterface((IInterface)this, "android.support.v4.media.session.IMediaControllerCallback");
    }
    
    public static IMediaControllerCallback asInterface(final IBinder binder) {
        if (binder == null) {
            return null;
        }
        final IInterface queryLocalInterface = binder.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
        if (queryLocalInterface != null && queryLocalInterface instanceof IMediaControllerCallback) {
            return (IMediaControllerCallback)queryLocalInterface;
        }
        return new IMediaControllerCallback$Stub$Proxy(binder);
    }
    
    public IBinder asBinder() {
        return (IBinder)this;
    }
    
    public boolean onTransact(final int n, final Parcel parcel, final Parcel parcel2, final int n2) {
        final PlaybackStateCompat playbackStateCompat = null;
        final MediaMetadataCompat mediaMetadataCompat = null;
        final CharSequence charSequence = null;
        final Bundle bundle = null;
        final ParcelableVolumeInfo parcelableVolumeInfo = null;
        final Bundle bundle2 = null;
        switch (n) {
            default: {
                return super.onTransact(n, parcel, parcel2, n2);
            }
            case 1598968902: {
                parcel2.writeString("android.support.v4.media.session.IMediaControllerCallback");
                return true;
            }
            case 1: {
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                final String string = parcel.readString();
                Bundle bundle3 = bundle2;
                if (parcel.readInt() != 0) {
                    bundle3 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.onEvent(string, bundle3);
                return true;
            }
            case 2: {
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                this.onSessionDestroyed();
                return true;
            }
            case 3: {
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                PlaybackStateCompat playbackStateCompat2 = playbackStateCompat;
                if (parcel.readInt() != 0) {
                    playbackStateCompat2 = (PlaybackStateCompat)PlaybackStateCompat.CREATOR.createFromParcel(parcel);
                }
                this.onPlaybackStateChanged(playbackStateCompat2);
                return true;
            }
            case 4: {
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                MediaMetadataCompat mediaMetadataCompat2 = mediaMetadataCompat;
                if (parcel.readInt() != 0) {
                    mediaMetadataCompat2 = (MediaMetadataCompat)MediaMetadataCompat.CREATOR.createFromParcel(parcel);
                }
                this.onMetadataChanged(mediaMetadataCompat2);
                return true;
            }
            case 5: {
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                this.onQueueChanged(parcel.createTypedArrayList((Parcelable$Creator)MediaSessionCompat$QueueItem.CREATOR));
                return true;
            }
            case 6: {
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                CharSequence charSequence2 = charSequence;
                if (parcel.readInt() != 0) {
                    charSequence2 = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
                }
                this.onQueueTitleChanged(charSequence2);
                return true;
            }
            case 7: {
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                Bundle bundle4 = bundle;
                if (parcel.readInt() != 0) {
                    bundle4 = (Bundle)Bundle.CREATOR.createFromParcel(parcel);
                }
                this.onExtrasChanged(bundle4);
                return true;
            }
            case 8: {
                parcel.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
                ParcelableVolumeInfo parcelableVolumeInfo2 = parcelableVolumeInfo;
                if (parcel.readInt() != 0) {
                    parcelableVolumeInfo2 = (ParcelableVolumeInfo)ParcelableVolumeInfo.CREATOR.createFromParcel(parcel);
                }
                this.onVolumeInfoChanged(parcelableVolumeInfo2);
                return true;
            }
        }
    }
}
