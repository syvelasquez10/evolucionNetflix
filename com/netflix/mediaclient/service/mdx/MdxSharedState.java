// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import org.json.JSONException;
import com.netflix.mediaclient.ui.mdx.MdxTargetCapabilities;
import com.netflix.mediaclient.Log;
import java.util.HashMap;
import android.annotation.SuppressLint;
import java.util.Map;
import com.netflix.mediaclient.servicemgr.IMdxSharedState;

public class MdxSharedState implements IMdxSharedState
{
    private static final long INVALID_TIME = -1L;
    private static final int INVALID_VOLUME = -1;
    @SuppressLint({ "UseSparseArrays" })
    private static final Map<MdxPlaybackState, String> MDX_PLAYBACK_STATE_NAME;
    private static final String TAG;
    private boolean mAllowVolume;
    private boolean mHasActivePlayback;
    private MdxPlaybackState mPlaybackState;
    private String mPostplay;
    private long mReportedPlaybackPositionInMs;
    private long mTimePositionReorptedInMs;
    private String mUuid;
    private int mVolume;
    
    static {
        TAG = MdxSharedState.class.getSimpleName();
        MDX_PLAYBACK_STATE_NAME = new HashMap<MdxPlaybackState, String>() {
            {
                this.put(MdxPlaybackState.Playing, "Playing");
                this.put(MdxPlaybackState.Paused, "Paused");
                this.put(MdxPlaybackState.Stopped, "Stopped");
                this.put(MdxPlaybackState.Loading, "Loading");
                this.put(MdxPlaybackState.Transitioning, "Transitioning");
            }
        };
    }
    
    MdxSharedState(final String mUuid) {
        this.mPlaybackState = MdxPlaybackState.Stopped;
        this.mReportedPlaybackPositionInMs = -1L;
        this.mVolume = -1;
        this.mUuid = mUuid;
    }
    
    private void reset() {
        this.mHasActivePlayback = false;
        this.mPlaybackState = MdxPlaybackState.Stopped;
        this.mReportedPlaybackPositionInMs = -1L;
        this.mVolume = -1;
    }
    
    @Override
    public MdxPlaybackState getMdxPlaybackState() {
        synchronized (this) {
            return this.mPlaybackState;
        }
    }
    
    @Override
    public long getPlaybackPositionInMs() {
        final long n = -1L;
        // monitorenter(this)
        long mReportedPlaybackPositionInMs = n;
        try {
            if (this.mReportedPlaybackPositionInMs > -1L) {
                mReportedPlaybackPositionInMs = n;
                if (this.mHasActivePlayback) {
                    if (this.mPlaybackState == MdxPlaybackState.Playing) {
                        mReportedPlaybackPositionInMs = System.currentTimeMillis() - this.mTimePositionReorptedInMs + this.mReportedPlaybackPositionInMs;
                    }
                    else {
                        mReportedPlaybackPositionInMs = this.mReportedPlaybackPositionInMs;
                    }
                }
            }
            return mReportedPlaybackPositionInMs;
        }
        finally {
        }
        // monitorexit(this)
    }
    
    @Override
    public String getPostplayState() {
        return this.mPostplay;
    }
    
    @Override
    public int getRecentVolume() {
        return this.mVolume;
    }
    
    String getTargetUuid() {
        return this.mUuid;
    }
    
    @Override
    public boolean hasActivePlayback() {
        synchronized (this) {
            return this.mHasActivePlayback;
        }
    }
    
    @Override
    public boolean isVolumeEnabled() {
        synchronized (this) {
            return this.mAllowVolume;
        }
    }
    
    void notifyPlayCommandReceived() {
        this.mPlaybackState = MdxPlaybackState.Loading;
        Log.d(MdxSharedState.TAG, "state: " + MdxSharedState.MDX_PLAYBACK_STATE_NAME.get(this.mPlaybackState) + ", pos: " + this.mReportedPlaybackPositionInMs + ", volume: " + this.mVolume);
    }
    
    void notifyPlaybackEnd() {
        this.reset();
        Log.d(MdxSharedState.TAG, "state: " + MdxSharedState.MDX_PLAYBACK_STATE_NAME.get(this.mPlaybackState) + ", pos: " + this.mReportedPlaybackPositionInMs + ", volume: " + this.mVolume);
    }
    
    void notifyPlaybackStart() {
        this.mHasActivePlayback = true;
        this.mPlaybackState = MdxPlaybackState.Playing;
        this.resetPostplayState();
        Log.d(MdxSharedState.TAG, "state: " + MdxSharedState.MDX_PLAYBACK_STATE_NAME.get(this.mPlaybackState) + ", pos: " + this.mReportedPlaybackPositionInMs + ", volume: " + this.mVolume);
    }
    
    void notifyPlaybackState(final String s, final int n, final int mVolume) {
        this.mReportedPlaybackPositionInMs = n;
        this.mTimePositionReorptedInMs = System.currentTimeMillis();
        this.mVolume = mVolume;
        if ("prepause".equals(s) || "preplay".equals(s) || "preseek".equals(s)) {
            this.mPlaybackState = MdxPlaybackState.Transitioning;
            this.resetPostplayState();
        }
        else if ("PROGRESS".equals(s)) {
            this.resetPostplayState();
            this.mPlaybackState = MdxPlaybackState.Transitioning;
        }
        else if ("PLAY".equals(s) || "PLAYING".equals(s)) {
            this.mPlaybackState = MdxPlaybackState.Playing;
            this.resetPostplayState();
        }
        else if ("PAUSE".equals(s)) {
            this.mPlaybackState = MdxPlaybackState.Paused;
        }
        else {
            this.mPlaybackState = MdxPlaybackState.Stopped;
        }
        Log.d(MdxSharedState.TAG, "state: " + MdxSharedState.MDX_PLAYBACK_STATE_NAME.get(this.mPlaybackState) + ", pos: " + this.mReportedPlaybackPositionInMs + ", volume: " + this.mVolume);
    }
    
    void notifyPostplayState(final String mPostplay) {
        this.mPostplay = mPostplay;
    }
    
    void notifyTargetCapability(final String s) {
        try {
            this.mAllowVolume = new MdxTargetCapabilities(s).isVolumeControl();
        }
        catch (JSONException ex) {
            Log.w(MdxSharedState.TAG, "ignore capability data ", (Throwable)ex);
        }
    }
    
    public void resetPostplayState() {
        this.mPostplay = null;
    }
}
