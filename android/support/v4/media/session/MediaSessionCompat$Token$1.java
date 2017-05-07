// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable$Creator;

final class MediaSessionCompat$Token$1 implements Parcelable$Creator<MediaSessionCompat$Token>
{
    public MediaSessionCompat$Token createFromParcel(final Parcel parcel) {
        return new MediaSessionCompat$Token(parcel.readParcelable((ClassLoader)null));
    }
    
    public MediaSessionCompat$Token[] newArray(final int n) {
        return new MediaSessionCompat$Token[n];
    }
}
