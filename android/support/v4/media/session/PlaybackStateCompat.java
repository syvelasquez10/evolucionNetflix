// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.media.session;

import java.util.Iterator;
import android.os.Build$VERSION;
import android.text.TextUtils;
import android.os.Parcel;
import java.util.Collection;
import java.util.ArrayList;
import android.os.Bundle;
import java.util.List;
import android.os.Parcelable$Creator;
import android.os.Parcelable;

public final class PlaybackStateCompat implements Parcelable
{
    public static final long ACTION_FAST_FORWARD = 64L;
    public static final long ACTION_PAUSE = 2L;
    public static final long ACTION_PLAY = 4L;
    public static final long ACTION_PLAY_FROM_MEDIA_ID = 1024L;
    public static final long ACTION_PLAY_FROM_SEARCH = 2048L;
    public static final long ACTION_PLAY_FROM_URI = 8192L;
    public static final long ACTION_PLAY_PAUSE = 512L;
    public static final long ACTION_PREPARE = 16384L;
    public static final long ACTION_PREPARE_FROM_MEDIA_ID = 32768L;
    public static final long ACTION_PREPARE_FROM_SEARCH = 65536L;
    public static final long ACTION_PREPARE_FROM_URI = 131072L;
    public static final long ACTION_REWIND = 8L;
    public static final long ACTION_SEEK_TO = 256L;
    public static final long ACTION_SET_RATING = 128L;
    public static final long ACTION_SKIP_TO_NEXT = 32L;
    public static final long ACTION_SKIP_TO_PREVIOUS = 16L;
    public static final long ACTION_SKIP_TO_QUEUE_ITEM = 4096L;
    public static final long ACTION_STOP = 1L;
    public static final Parcelable$Creator<PlaybackStateCompat> CREATOR;
    private static final int KEYCODE_MEDIA_PAUSE = 127;
    private static final int KEYCODE_MEDIA_PLAY = 126;
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
    public static final int STATE_SKIPPING_TO_QUEUE_ITEM = 11;
    public static final int STATE_STOPPED = 1;
    final long mActions;
    final long mActiveItemId;
    final long mBufferedPosition;
    List<PlaybackStateCompat$CustomAction> mCustomActions;
    final CharSequence mErrorMessage;
    final Bundle mExtras;
    final long mPosition;
    final float mSpeed;
    final int mState;
    private Object mStateObj;
    final long mUpdateTime;
    
    static {
        CREATOR = (Parcelable$Creator)new PlaybackStateCompat$1();
    }
    
    PlaybackStateCompat(final int mState, final long mPosition, final long mBufferedPosition, final float mSpeed, final long mActions, final CharSequence mErrorMessage, final long mUpdateTime, final List<PlaybackStateCompat$CustomAction> list, final long mActiveItemId, final Bundle mExtras) {
        this.mState = mState;
        this.mPosition = mPosition;
        this.mBufferedPosition = mBufferedPosition;
        this.mSpeed = mSpeed;
        this.mActions = mActions;
        this.mErrorMessage = mErrorMessage;
        this.mUpdateTime = mUpdateTime;
        this.mCustomActions = new ArrayList<PlaybackStateCompat$CustomAction>(list);
        this.mActiveItemId = mActiveItemId;
        this.mExtras = mExtras;
    }
    
    PlaybackStateCompat(final Parcel parcel) {
        this.mState = parcel.readInt();
        this.mPosition = parcel.readLong();
        this.mSpeed = parcel.readFloat();
        this.mUpdateTime = parcel.readLong();
        this.mBufferedPosition = parcel.readLong();
        this.mActions = parcel.readLong();
        this.mErrorMessage = (CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel);
        this.mCustomActions = (List<PlaybackStateCompat$CustomAction>)parcel.createTypedArrayList((Parcelable$Creator)PlaybackStateCompat$CustomAction.CREATOR);
        this.mActiveItemId = parcel.readLong();
        this.mExtras = parcel.readBundle();
    }
    
    public static PlaybackStateCompat fromPlaybackState(final Object mStateObj) {
        if (mStateObj == null || Build$VERSION.SDK_INT < 21) {
            return null;
        }
        final List<Object> customActions = PlaybackStateCompatApi21.getCustomActions(mStateObj);
        List<PlaybackStateCompat$CustomAction> list = null;
        if (customActions != null) {
            final ArrayList list2 = new ArrayList<PlaybackStateCompat$CustomAction>(customActions.size());
            final Iterator<Object> iterator = customActions.iterator();
            while (true) {
                list = (List<PlaybackStateCompat$CustomAction>)list2;
                if (!iterator.hasNext()) {
                    break;
                }
                list2.add(PlaybackStateCompat$CustomAction.fromCustomAction(iterator.next()));
            }
        }
        Bundle extras;
        if (Build$VERSION.SDK_INT >= 22) {
            extras = PlaybackStateCompatApi22.getExtras(mStateObj);
        }
        else {
            extras = null;
        }
        final PlaybackStateCompat playbackStateCompat = new PlaybackStateCompat(PlaybackStateCompatApi21.getState(mStateObj), PlaybackStateCompatApi21.getPosition(mStateObj), PlaybackStateCompatApi21.getBufferedPosition(mStateObj), PlaybackStateCompatApi21.getPlaybackSpeed(mStateObj), PlaybackStateCompatApi21.getActions(mStateObj), PlaybackStateCompatApi21.getErrorMessage(mStateObj), PlaybackStateCompatApi21.getLastPositionUpdateTime(mStateObj), list, PlaybackStateCompatApi21.getActiveQueueItemId(mStateObj), extras);
        playbackStateCompat.mStateObj = mStateObj;
        return playbackStateCompat;
    }
    
    public static int toKeyCode(final long n) {
        if (n == 4L) {
            return 126;
        }
        if (n == 2L) {
            return 127;
        }
        if (n == 32L) {
            return 87;
        }
        if (n == 16L) {
            return 88;
        }
        if (n == 1L) {
            return 86;
        }
        if (n == 64L) {
            return 90;
        }
        if (n == 8L) {
            return 89;
        }
        if (n == 512L) {
            return 85;
        }
        return 0;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public long getActions() {
        return this.mActions;
    }
    
    public long getActiveQueueItemId() {
        return this.mActiveItemId;
    }
    
    public long getBufferedPosition() {
        return this.mBufferedPosition;
    }
    
    public List<PlaybackStateCompat$CustomAction> getCustomActions() {
        return this.mCustomActions;
    }
    
    public CharSequence getErrorMessage() {
        return this.mErrorMessage;
    }
    
    public Bundle getExtras() {
        return this.mExtras;
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
        List<Object> list = null;
        if (this.mCustomActions != null) {
            final ArrayList<Object> list2 = new ArrayList<Object>(this.mCustomActions.size());
            final Iterator<PlaybackStateCompat$CustomAction> iterator = this.mCustomActions.iterator();
            while (true) {
                list = list2;
                if (!iterator.hasNext()) {
                    break;
                }
                list2.add(iterator.next().getCustomAction());
            }
        }
        if (Build$VERSION.SDK_INT >= 22) {
            this.mStateObj = PlaybackStateCompatApi22.newInstance(this.mState, this.mPosition, this.mBufferedPosition, this.mSpeed, this.mActions, this.mErrorMessage, this.mUpdateTime, list, this.mActiveItemId, this.mExtras);
        }
        else {
            this.mStateObj = PlaybackStateCompatApi21.newInstance(this.mState, this.mPosition, this.mBufferedPosition, this.mSpeed, this.mActions, this.mErrorMessage, this.mUpdateTime, list, this.mActiveItemId);
        }
        return this.mStateObj;
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
        sb.append(", custom actions=").append(this.mCustomActions);
        sb.append(", active item id=").append(this.mActiveItemId);
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
        parcel.writeTypedList((List)this.mCustomActions);
        parcel.writeLong(this.mActiveItemId);
        parcel.writeBundle(this.mExtras);
    }
}
