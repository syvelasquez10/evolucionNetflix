// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class MediaSessionCompat$Token implements Parcelable
{
    public static final Parcelable$Creator<MediaSessionCompat$Token> CREATOR;
    private final Parcelable mInner;
    
    static {
        CREATOR = (Parcelable$Creator)new MediaSessionCompat$Token$1();
    }
    
    MediaSessionCompat$Token(final Parcelable mInner) {
        this.mInner = mInner;
    }
    
    public int describeContents() {
        return this.mInner.describeContents();
    }
    
    public Object getToken() {
        return this.mInner;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeParcelable(this.mInner, n);
    }
}
