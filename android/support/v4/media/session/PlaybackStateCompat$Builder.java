// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.Build$VERSION;
import android.text.TextUtils;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.os.SystemClock;

public final class PlaybackStateCompat$Builder
{
    private long mActions;
    private long mBufferedPosition;
    private CharSequence mErrorMessage;
    private long mPosition;
    private float mRate;
    private int mState;
    private long mUpdateTime;
    
    public PlaybackStateCompat$Builder() {
    }
    
    public PlaybackStateCompat$Builder(final PlaybackStateCompat playbackStateCompat) {
        this.mState = playbackStateCompat.mState;
        this.mPosition = playbackStateCompat.mPosition;
        this.mRate = playbackStateCompat.mSpeed;
        this.mUpdateTime = playbackStateCompat.mUpdateTime;
        this.mBufferedPosition = playbackStateCompat.mBufferedPosition;
        this.mActions = playbackStateCompat.mActions;
        this.mErrorMessage = playbackStateCompat.mErrorMessage;
    }
    
    public PlaybackStateCompat build() {
        return new PlaybackStateCompat(this.mState, this.mPosition, this.mBufferedPosition, this.mRate, this.mActions, this.mErrorMessage, this.mUpdateTime, null);
    }
    
    public void setActions(final long mActions) {
        this.mActions = mActions;
    }
    
    public void setBufferedPosition(final long mBufferedPosition) {
        this.mBufferedPosition = mBufferedPosition;
    }
    
    public void setErrorMessage(final CharSequence mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
    }
    
    public void setState(final int mState, final long mPosition, final float mRate) {
        this.mState = mState;
        this.mPosition = mPosition;
        this.mRate = mRate;
        this.mUpdateTime = SystemClock.elapsedRealtime();
    }
}
