// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import com.netflix.mediaclient.Log;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class PlayContextImp implements PlayContext
{
    private static final String BUNDLE_BROWSE_PLAY = "play_context_bundle_browse_play";
    private static final String BUNDLE_LIST_POS = "play_context_bundle_list_pos";
    private static final String BUNDLE_PLAY_LOCATION = "play_context_bundle_play_location";
    private static final String BUNDLE_REQUEST_ID = "play_context_bundle_request_id";
    private static final String BUNDLE_TRACK_ID = "play_context_bundle_track_id";
    private static final String BUNDLE_VIDEO_POS = "play_context_bundle_video_pos";
    public static final Parcelable$Creator<PlayContextImp> CREATOR;
    private static final String TAG = "nf_play_context_imp";
    private boolean browsePlay;
    private final int listPos;
    private String playLocation;
    private final String requestId;
    private final int trackId;
    private final int videoPos;
    
    static {
        CREATOR = (Parcelable$Creator)new PlayContextImp$1();
    }
    
    PlayContextImp() {
        this("", -1, -1, -1);
    }
    
    public PlayContextImp(final Parcel parcel) {
        this(parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt());
        this.playLocation = parcel.readString();
    }
    
    public PlayContextImp(final Trackable trackable, final int n) {
        final String requestId = trackable.getRequestId();
        int n2;
        if (trackable.isHero()) {
            n2 = trackable.getHeroTrackId();
        }
        else {
            n2 = trackable.getTrackId();
        }
        this(requestId, n2, trackable.getListPos(), n);
    }
    
    public PlayContextImp(final String requestId, final int trackId, final int listPos, final int videoPos) {
        this.playLocation = PlayLocationType.DIRECT_PLAY.getValue();
        this.requestId = requestId;
        this.trackId = trackId;
        this.listPos = listPos;
        this.videoPos = videoPos;
    }
    
    public static PlayContext createPlayContextFromBundle(final Bundle bundle) {
        if (Log.isLoggable()) {
            Log.i("nf_play_context_imp", "createPlayContextFromBundle");
        }
        PlayContext empty_CONTEXT;
        if (bundle == null) {
            if (Log.isLoggable()) {
                Log.e("nf_play_context_imp", "createPlayContextFromBundle bundle is null, using empty playContext");
            }
            empty_CONTEXT = PlayContext.EMPTY_CONTEXT;
        }
        else {
            final PlayContextImp playContextImp = new PlayContextImp(bundle.getString("play_context_bundle_request_id", (String)null), bundle.getInt("play_context_bundle_track_id", -1), bundle.getInt("play_context_bundle_list_pos", -1), bundle.getInt("play_context_bundle_video_pos", -1));
            playContextImp.setBrowsePlay(bundle.getBoolean("play_context_bundle_browse_play", false));
            playContextImp.setPlayLocation(PlayLocationType.create(bundle.getString("play_context_bundle_play_location", PlayLocationType.DIRECT_PLAY.getValue())));
            empty_CONTEXT = playContextImp;
            if (Log.isLoggable()) {
                Log.i("nf_play_context_imp", "createPlayContextFromBundle\n" + playContextImp.toString());
                return playContextImp;
            }
        }
        return empty_CONTEXT;
    }
    
    public static Bundle playContextToBundle(final PlayContext playContext) {
        if (Log.isLoggable()) {
            Log.i("nf_play_context_imp", "playContextToBundle\n" + playContext.toString());
        }
        final Bundle bundle = new Bundle();
        bundle.putString("play_context_bundle_request_id", playContext.getRequestId());
        bundle.putInt("play_context_bundle_track_id", playContext.getTrackId());
        bundle.putInt("play_context_bundle_list_pos", playContext.getListPos());
        bundle.putInt("play_context_bundle_video_pos", playContext.getVideoPos());
        bundle.putBoolean("play_context_bundle_browse_play", playContext.getBrowsePlay());
        bundle.putString("play_context_bundle_play_location", playContext.getPlayLocation().getValue());
        return bundle;
    }
    
    public PlayContextImp cloneWithNewTrackId(final int n) {
        return new PlayContextImp(this.getRequestId(), n, this.getListPos(), this.getVideoPos());
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Override
    public boolean getBrowsePlay() {
        return this.browsePlay;
    }
    
    public int getHeroTrackId() {
        throw new UnsupportedOperationException("Should not be needed");
    }
    
    public int getListPos() {
        return this.listPos;
    }
    
    @Override
    public PlayLocationType getPlayLocation() {
        return PlayLocationType.create(this.playLocation);
    }
    
    public String getRequestId() {
        return this.requestId;
    }
    
    public int getTrackId() {
        return this.trackId;
    }
    
    @Override
    public int getVideoPos() {
        return this.videoPos;
    }
    
    public boolean isHero() {
        return false;
    }
    
    @Override
    public Bundle playContextToBundle() {
        return playContextToBundle(this);
    }
    
    @Override
    public void setBrowsePlay(final boolean browsePlay) {
        this.browsePlay = browsePlay;
    }
    
    @Override
    public void setPlayLocation(final PlayLocationType playLocationType) {
        this.playLocation = playLocationType.getValue();
    }
    
    @Override
    public String toString() {
        return "PlayContextImp [requestId=" + this.requestId + ", trackId=" + this.trackId + ", listPos=" + this.listPos + ", videoPos=" + this.videoPos + ", playLocation=" + this.playLocation + "]";
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.requestId);
        parcel.writeInt(this.trackId);
        parcel.writeInt(this.listPos);
        parcel.writeInt(this.videoPos);
        parcel.writeString(this.playLocation);
    }
}
