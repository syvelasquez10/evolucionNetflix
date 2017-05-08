// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Build$VERSION;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class MediaSessionCompat$Token implements Parcelable
{
    public static final Parcelable$Creator<MediaSessionCompat$Token> CREATOR;
    private final Object mInner;
    
    static {
        CREATOR = (Parcelable$Creator)new MediaSessionCompat$Token$1();
    }
    
    MediaSessionCompat$Token(final Object mInner) {
        this.mInner = mInner;
    }
    
    public static MediaSessionCompat$Token fromToken(final Object o) {
        if (o == null || Build$VERSION.SDK_INT < 21) {
            return null;
        }
        return new MediaSessionCompat$Token(MediaSessionCompatApi21.verifyToken(o));
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof MediaSessionCompat$Token)) {
                return false;
            }
            final MediaSessionCompat$Token mediaSessionCompat$Token = (MediaSessionCompat$Token)o;
            if (this.mInner != null) {
                return mediaSessionCompat$Token.mInner != null && this.mInner.equals(mediaSessionCompat$Token.mInner);
            }
            if (mediaSessionCompat$Token.mInner != null) {
                return false;
            }
        }
        return true;
    }
    
    public Object getToken() {
        return this.mInner;
    }
    
    @Override
    public int hashCode() {
        if (this.mInner == null) {
            return 0;
        }
        return this.mInner.hashCode();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        if (Build$VERSION.SDK_INT >= 21) {
            parcel.writeParcelable((Parcelable)this.mInner, n);
            return;
        }
        parcel.writeStrongBinder((IBinder)this.mInner);
    }
}
