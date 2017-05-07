// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.WebApiUtils;

public final class SwitchTarget
{
    private static final String TAG = "nf_mdxSwitchTarget";
    private boolean mBeginSwitchTarget;
    SwitchTargetInterface mCallback;
    private String mNewUuid;
    private int mStartTime;
    private TargetManager mTargeManger;
    private int mTrackId;
    private WebApiUtils.VideoIds mVideoIds;
    
    public SwitchTarget(final TargetManager mTargeManger, final SwitchTargetInterface mCallback) {
        this.mTargeManger = mTargeManger;
        this.mCallback = mCallback;
    }
    
    public void startSwitch(final String s, final String mNewUuid, final WebApiUtils.VideoIds mVideoIds, final int mStartTime, final int mTrackId) {
        if (this.mBeginSwitchTarget) {
            Log.e("nf_mdxSwitchTarget", "switching already undergoing");
        }
        if (StringUtils.isEmpty(s)) {
            Log.e("nf_mdxSwitchTarget", "current target is not a mdx target");
            return;
        }
        this.mBeginSwitchTarget = true;
        this.mNewUuid = mNewUuid;
        this.mVideoIds = mVideoIds;
        this.mStartTime = mStartTime;
        this.mTrackId = mTrackId;
        this.mTargeManger.playerStop(s);
    }
    
    public void targetPlaybackStopped(final String s) {
        if (!this.mBeginSwitchTarget) {
            return;
        }
        this.mCallback.onSetToNewTarget(this.mNewUuid);
        if (StringUtils.isNotEmpty(this.mNewUuid)) {
            if (Log.isLoggable("nf_mdxSwitchTarget", 3)) {
                Log.d("nf_mdxSwitchTarget", "start playback on " + this.mNewUuid + ", " + this.mVideoIds.catalogIdUrl + ", " + this.mVideoIds.episodeIdUrl + ", " + this.mStartTime);
            }
            this.mTargeManger.playerPlay(this.mNewUuid, this.mVideoIds.catalogIdUrl, this.mTrackId, this.mVideoIds.episodeIdUrl, this.mStartTime);
        }
        this.mBeginSwitchTarget = false;
    }
    
    public interface SwitchTargetInterface
    {
        void onSetToNewTarget(final String p0);
    }
}
