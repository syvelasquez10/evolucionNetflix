// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.Log;
import java.util.concurrent.atomic.AtomicBoolean;

public class PlayerWorkflowState
{
    PlayerActivity.PlayerActivityState activityState;
    boolean audioSeekToInProgress;
    boolean draggingAudioInProgress;
    boolean draggingInProgress;
    private long lastActionTime;
    boolean lowBandwidth;
    AtomicBoolean playStartInProgress;
    boolean playStarted;
    private boolean playbackStopped;
    boolean seekToInProgress;
    boolean stalled;
    private int timelineCurrentSeekPosition;
    boolean timelineExitOnSnap;
    public boolean timelineInSnapZone;
    private int timelinePreviousSeekPosition;
    private int timelineSeekDelta;
    int timelineStartSeekPosition;
    private boolean userInteraction;
    boolean videoLoaded;
    boolean videoPrepared;
    long volumeChangeInProgress;
    
    public PlayerWorkflowState() {
        this.playStartInProgress = new AtomicBoolean();
        this.activityState = PlayerActivity.PlayerActivityState.ACTIVITY_NOTREADY;
        this.videoLoaded = false;
        this.videoPrepared = false;
        this.playbackStopped = false;
        this.playStarted = false;
        this.volumeChangeInProgress = -1L;
        this.timelineStartSeekPosition = 0;
        this.timelineCurrentSeekPosition = 0;
        this.timelineSeekDelta = 0;
        this.timelineExitOnSnap = false;
        this.timelinePreviousSeekPosition = 0;
        this.timelineInSnapZone = true;
    }
    
    public PlayerActivity.PlayerActivityState getActivityState() {
        return this.activityState;
    }
    
    public long getLastActionTime() {
        return this.lastActionTime;
    }
    
    public AtomicBoolean getPlayStartInProgress() {
        return this.playStartInProgress;
    }
    
    public int getTimelineCurrentSeekPosition() {
        return this.timelineCurrentSeekPosition;
    }
    
    public boolean getTimelineExitOnSnap() {
        return this.timelineExitOnSnap;
    }
    
    public int getTimelinePreviousSeekPosition() {
        return this.timelinePreviousSeekPosition;
    }
    
    public int getTimelineSeekDelta() {
        return this.timelineSeekDelta;
    }
    
    public int getTimelineStartSeekPosition() {
        return this.timelineStartSeekPosition;
    }
    
    public long getVolumeChangeInProgress() {
        return this.volumeChangeInProgress;
    }
    
    public boolean isAudioSeekToInProgress() {
        return this.audioSeekToInProgress;
    }
    
    public boolean isDraggingAudioInProgress() {
        return this.draggingAudioInProgress;
    }
    
    public boolean isDraggingInProgress() {
        return this.draggingInProgress;
    }
    
    public boolean isLowBandwidth() {
        return this.lowBandwidth;
    }
    
    public boolean isPlayStarted() {
        return this.playStarted;
    }
    
    public boolean isPlaybackStopped() {
        return this.playbackStopped;
    }
    
    public boolean isSeekForward() {
        return this.timelineCurrentSeekPosition < this.timelinePreviousSeekPosition;
    }
    
    public boolean isSeekToInProgress() {
        return this.seekToInProgress;
    }
    
    public boolean isStalled() {
        return this.stalled;
    }
    
    public boolean isTimelineInSnapZone() {
        return this.timelineInSnapZone;
    }
    
    public boolean isVideoLoaded() {
        return this.videoLoaded;
    }
    
    public boolean isVideoPrepared() {
        return this.videoPrepared;
    }
    
    public boolean noUserInteraction() {
        return !this.userInteraction;
    }
    
    public void reset() {
        this.lastActionTime = 0L;
        this.videoLoaded = false;
        this.videoPrepared = false;
        this.draggingInProgress = false;
        this.draggingAudioInProgress = false;
        this.seekToInProgress = false;
        this.audioSeekToInProgress = false;
        this.playbackStopped = false;
        this.playStarted = false;
        this.stalled = false;
        this.volumeChangeInProgress = -1L;
        this.lowBandwidth = false;
        this.resetTimeline();
    }
    
    public void resetTimeline() {
        this.timelineCurrentSeekPosition = 0;
        this.timelineInSnapZone = true;
        this.timelinePreviousSeekPosition = 0;
        this.timelineStartSeekPosition = 0;
        this.timelineSeekDelta = 0;
    }
    
    public void setDraggingInProgress(final boolean draggingInProgress) {
        this.draggingInProgress = draggingInProgress;
    }
    
    public void setLastActionTime(final long lastActionTime) {
        this.lastActionTime = lastActionTime;
    }
    
    public void setTimelineExitOnSnap(final boolean timelineExitOnSnap) {
        this.timelineExitOnSnap = timelineExitOnSnap;
    }
    
    public void updatePosition(final int timelinePreviousSeekPosition) {
        this.timelineCurrentSeekPosition = this.timelinePreviousSeekPosition;
        this.timelinePreviousSeekPosition = timelinePreviousSeekPosition;
        this.timelineSeekDelta = Math.abs(this.timelineCurrentSeekPosition - this.timelinePreviousSeekPosition);
    }
    
    public void userInteraction() {
        Log.d("SRDJAN", "userInteraction called!");
        this.userInteraction = true;
    }
}
