// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import java.util.Set;
import android.util.Log;
import java.util.Iterator;
import android.os.Build$VERSION;
import android.os.Parcel;
import android.support.v4.util.ArrayMap;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.graphics.Bitmap;
import android.os.Bundle;

public final class MediaMetadataCompat$Builder
{
    private final Bundle mBundle;
    
    public MediaMetadataCompat$Builder() {
        this.mBundle = new Bundle();
    }
    
    public MediaMetadataCompat$Builder(final MediaMetadataCompat mediaMetadataCompat) {
        this.mBundle = new Bundle(mediaMetadataCompat.mBundle);
    }
    
    public MediaMetadataCompat build() {
        return new MediaMetadataCompat(this.mBundle, null);
    }
    
    public MediaMetadataCompat$Builder putBitmap(final String s, final Bitmap bitmap) {
        if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(s) && (int)MediaMetadataCompat.METADATA_KEYS_TYPE.get(s) != 2) {
            throw new IllegalArgumentException("The " + s + " key cannot be used to put a Bitmap");
        }
        this.mBundle.putParcelable(s, (Parcelable)bitmap);
        return this;
    }
    
    public MediaMetadataCompat$Builder putLong(final String s, final long n) {
        if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(s) && (int)MediaMetadataCompat.METADATA_KEYS_TYPE.get(s) != 0) {
            throw new IllegalArgumentException("The " + s + " key cannot be used to put a long");
        }
        this.mBundle.putLong(s, n);
        return this;
    }
    
    public MediaMetadataCompat$Builder putRating(final String s, final RatingCompat ratingCompat) {
        if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(s) && (int)MediaMetadataCompat.METADATA_KEYS_TYPE.get(s) != 3) {
            throw new IllegalArgumentException("The " + s + " key cannot be used to put a Rating");
        }
        this.mBundle.putParcelable(s, (Parcelable)ratingCompat);
        return this;
    }
    
    public MediaMetadataCompat$Builder putString(final String s, final String s2) {
        if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(s) && (int)MediaMetadataCompat.METADATA_KEYS_TYPE.get(s) != 1) {
            throw new IllegalArgumentException("The " + s + " key cannot be used to put a String");
        }
        this.mBundle.putCharSequence(s, (CharSequence)s2);
        return this;
    }
    
    public MediaMetadataCompat$Builder putText(final String s, final CharSequence charSequence) {
        if (MediaMetadataCompat.METADATA_KEYS_TYPE.containsKey(s) && (int)MediaMetadataCompat.METADATA_KEYS_TYPE.get(s) != 1) {
            throw new IllegalArgumentException("The " + s + " key cannot be used to put a CharSequence");
        }
        this.mBundle.putCharSequence(s, charSequence);
        return this;
    }
}
