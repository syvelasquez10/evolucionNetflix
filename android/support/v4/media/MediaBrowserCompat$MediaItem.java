// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import android.os.Build$VERSION;
import android.text.TextUtils;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public class MediaBrowserCompat$MediaItem implements Parcelable
{
    public static final Parcelable$Creator<MediaBrowserCompat$MediaItem> CREATOR;
    public static final int FLAG_BROWSABLE = 1;
    public static final int FLAG_PLAYABLE = 2;
    private final MediaDescriptionCompat mDescription;
    private final int mFlags;
    
    static {
        CREATOR = (Parcelable$Creator)new MediaBrowserCompat$MediaItem$1();
    }
    
    MediaBrowserCompat$MediaItem(final Parcel parcel) {
        this.mFlags = parcel.readInt();
        this.mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
    }
    
    public MediaBrowserCompat$MediaItem(final MediaDescriptionCompat mDescription, final int mFlags) {
        if (mDescription == null) {
            throw new IllegalArgumentException("description cannot be null");
        }
        if (TextUtils.isEmpty((CharSequence)mDescription.getMediaId())) {
            throw new IllegalArgumentException("description must have a non-empty media id");
        }
        this.mFlags = mFlags;
        this.mDescription = mDescription;
    }
    
    public static MediaBrowserCompat$MediaItem fromMediaItem(final Object o) {
        if (o == null || Build$VERSION.SDK_INT < 21) {
            return null;
        }
        return new MediaBrowserCompat$MediaItem(MediaDescriptionCompat.fromMediaDescription(MediaBrowserCompatApi21$MediaItem.getDescription(o)), MediaBrowserCompatApi21$MediaItem.getFlags(o));
    }
    
    public static List<MediaBrowserCompat$MediaItem> fromMediaItemList(final List<?> list) {
        List<MediaBrowserCompat$MediaItem> list2;
        if (list == null || Build$VERSION.SDK_INT < 21) {
            list2 = null;
        }
        else {
            final ArrayList<MediaBrowserCompat$MediaItem> list3 = new ArrayList<MediaBrowserCompat$MediaItem>(list.size());
            final Iterator<?> iterator = list.iterator();
            while (true) {
                list2 = list3;
                if (!iterator.hasNext()) {
                    break;
                }
                list3.add(fromMediaItem(iterator.next()));
            }
        }
        return list2;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public MediaDescriptionCompat getDescription() {
        return this.mDescription;
    }
    
    public int getFlags() {
        return this.mFlags;
    }
    
    public String getMediaId() {
        return this.mDescription.getMediaId();
    }
    
    public boolean isBrowsable() {
        return (this.mFlags & 0x1) != 0x0;
    }
    
    public boolean isPlayable() {
        return (this.mFlags & 0x2) != 0x0;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MediaItem{");
        sb.append("mFlags=").append(this.mFlags);
        sb.append(", mDescription=").append(this.mDescription);
        sb.append('}');
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeInt(this.mFlags);
        this.mDescription.writeToParcel(parcel, n);
    }
}
