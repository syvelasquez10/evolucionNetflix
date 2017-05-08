// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.Build$VERSION;
import android.text.TextUtils;
import android.os.Parcel;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class PlaybackStateCompat$CustomAction implements Parcelable
{
    public static final Parcelable$Creator<PlaybackStateCompat$CustomAction> CREATOR;
    private final String mAction;
    private Object mCustomActionObj;
    private final Bundle mExtras;
    private final int mIcon;
    private final CharSequence mName;
    
    static {
        CREATOR = (Parcelable$Creator)new PlaybackStateCompat$CustomAction$1();
    }
    
    PlaybackStateCompat$CustomAction(final Parcel parcel) {
        this.mAction = parcel.readString();
        this.mName = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mIcon = parcel.readInt();
        this.mExtras = parcel.readBundle();
    }
    
    PlaybackStateCompat$CustomAction(final String mAction, final CharSequence mName, final int mIcon, final Bundle mExtras) {
        this.mAction = mAction;
        this.mName = mName;
        this.mIcon = mIcon;
        this.mExtras = mExtras;
    }
    
    public static PlaybackStateCompat$CustomAction fromCustomAction(final Object mCustomActionObj) {
        if (mCustomActionObj == null || Build$VERSION.SDK_INT < 21) {
            return null;
        }
        final PlaybackStateCompat$CustomAction playbackStateCompat$CustomAction = new PlaybackStateCompat$CustomAction(PlaybackStateCompatApi21$CustomAction.getAction(mCustomActionObj), PlaybackStateCompatApi21$CustomAction.getName(mCustomActionObj), PlaybackStateCompatApi21$CustomAction.getIcon(mCustomActionObj), PlaybackStateCompatApi21$CustomAction.getExtras(mCustomActionObj));
        playbackStateCompat$CustomAction.mCustomActionObj = mCustomActionObj;
        return playbackStateCompat$CustomAction;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getAction() {
        return this.mAction;
    }
    
    public Object getCustomAction() {
        if (this.mCustomActionObj != null || Build$VERSION.SDK_INT < 21) {
            return this.mCustomActionObj;
        }
        return this.mCustomActionObj = PlaybackStateCompatApi21$CustomAction.newInstance(this.mAction, this.mName, this.mIcon, this.mExtras);
    }
    
    public Bundle getExtras() {
        return this.mExtras;
    }
    
    public int getIcon() {
        return this.mIcon;
    }
    
    public CharSequence getName() {
        return this.mName;
    }
    
    @Override
    public String toString() {
        return "Action:mName='" + (Object)this.mName + ", mIcon=" + this.mIcon + ", mExtras=" + this.mExtras;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.mAction);
        TextUtils.writeToParcel(this.mName, parcel, n);
        parcel.writeInt(this.mIcon);
        parcel.writeBundle(this.mExtras);
    }
}
