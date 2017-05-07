// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import android.os.SystemClock;
import android.os.Build$VERSION;
import android.text.TextUtils;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class PlaybackStateCompat implements Parcelable
{
    public static final long ACTION_FAST_FORWARD = 64L;
    public static final long ACTION_PAUSE = 2L;
    public static final long ACTION_PLAY = 4L;
    public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024L;
    public static final long ACTION_PLAY_FROM_SEARCH = 2048L;
    public static final long ACTION_PLAY_PAUSE = 512L;
    public static final long ACTION_REWIND = 8L;
    public static final long ACTION_SEEK_TO = 256L;
    public static final long ACTION_SET_RATING = 128L;
    public static final long ACTION_SKIP_TO_NEXT = 32L;
    public static final long ACTION_SKIP_TO_PREVIOUS = 16L;
    public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096L;
    public static final long ACTION_STOP = 1L;
    public static final Parcelable$Creator<PlaybackStateCompat> CREATOR;
    public static final long PLAYBACK_POSITION_UNKNOWN = -1L;
    public static final int STATE_BUFFERING = 6;
    public static final int STATE_CONNECTING = 8;
    public static final int STATE_ERROR = 7;
    public static final int STATE_FAST_FORWARDING = 4;
    public static final int STATE_NONE = 0;
    public static final int STATE_PAUSED = 2;
    public static final int STATE_PLAYING = 3;
    public static final int STATE_REWINDING = 5;
    public static final int STATE_SKIPPING_TO_NEXT = 10;
    public static final int STATE_SKIPPING_TO_PREVIOUS = 9;
    public static final int STATE_STOPPED = 1;
    private final long mActions;
    private final long mBufferedPosition;
    private final CharSequence mErrorMessage;
    private final long mPosition;
    private final float mSpeed;
    private final int mState;
    private Object mStateObj;
    private final long mUpdateTime;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<PlaybackStateCompat>() {
            public PlaybackStateCompat createFromParcel(final Parcel parcel) {
                return new PlaybackStateCompat(parcel, null);
            }
            
            public PlaybackStateCompat[] newArray(final int n) {
                return new PlaybackStateCompat[n];
            }
        };
    }
    
    private PlaybackStateCompat(final int mState, final long mPosition, final long mBufferedPosition, final float mSpeed, final long mActions, final CharSequence mErrorMessage, final long mUpdateTime) {
        this.mState = mState;
        this.mPosition = mPosition;
        this.mBufferedPosition = mBufferedPosition;
        this.mSpeed = mSpeed;
        this.mActions = mActions;
        this.mErrorMessage = mErrorMessage;
        this.mUpdateTime = mUpdateTime;
    }
    
    private PlaybackStateCompat(final Parcel parcel) {
        this.mState = parcel.readInt();
        this.mPosition = parcel.readLong();
        this.mSpeed = parcel.readFloat();
        this.mUpdateTime = parcel.readLong();
        this.mBufferedPosition = parcel.readLong();
        this.mActions = parcel.readLong();
        this.mErrorMessage = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
    }
    
    public static PlaybackStateCompat fromPlaybackState(final Object mStateObj) {
        if (mStateObj == null || Build$VERSION.SDK_INT < 21) {
            return null;
        }
        final PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(PlaybackStateCompatApi21.getState(mStateObj), PlaybackStateCompatApi21.getPosition(mStateObj), PlaybackStateCompatApi21.getBufferedPosition(mStateObj), PlaybackStateCompatApi21.getPlaybackSpeed(mStateObj), PlaybackStateCompatApi21.getActions(mStateObj), PlaybackStateCompatApi21.getErrorMessage(mStateObj), PlaybackStateCompatApi21.getLastPositionUpdateTime(mStateObj));
        playbackStateCompat.mStateObj = mStateObj;
        return playbackStateCompat;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public long getActions() {
        return this.mActions;
    }
    
    public long getBufferedPosition() {
        return this.mBufferedPosition;
    }
    
    public CharSequence getErrorMessage() {
        return this.mErrorMessage;
    }
    
    public long getLastPositionUpdateTime() {
        return this.mUpdateTime;
    }
    
    public float getPlaybackSpeed() {
        return this.mSpeed;
    }
    
    public Object getPlaybackState() {
        if (this.mStateObj != null || Build$VERSION.SDK_INT < 21) {
            return this.mStateObj;
        }
        return this.mStateObj = PlaybackStateCompatApi21.newInstance(this.mState, this.mPosition, this.mBufferedPosition, this.mSpeed, this.mActions, this.mErrorMessage, this.mUpdateTime);
    }
    
    public long getPosition() {
        return this.mPosition;
    }
    
    public int getState() {
        return this.mState;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlaybackState {");
        sb.append("state=").append(this.mState);
        sb.append(", position=").append(this.mPosition);
        sb.append(", buffered position=").append(this.mBufferedPosition);
        sb.append(", speed=").append(this.mSpeed);
        sb.append(", updated=").append(this.mUpdateTime);
        sb.append(", actions=").append(this.mActions);
        sb.append(", error=").append(this.mErrorMessage);
        sb.append("}");
        return sb.toString();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeInt(this.mState);
        parcel.writeLong(this.mPosition);
        parcel.writeFloat(this.mSpeed);
        parcel.writeLong(this.mUpdateTime);
        parcel.writeLong(this.mBufferedPosition);
        parcel.writeLong(this.mActions);
        TextUtils.writeToParcel(this.mErrorMessage, parcel, n);
    }
    
    public static final class Builder
    {
        private long mActions;
        private long mBufferedPosition;
        private CharSequence mErrorMessage;
        private long mPosition;
        private float mRate;
        private int mState;
        private long mUpdateTime;
        
        public Builder() {
        }
        
        public Builder(final PlaybackStateCompat playbackStateCompat) {
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
}
