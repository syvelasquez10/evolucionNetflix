// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

class PlaycardWorkflowState
{
    public boolean audioSeekToInProgress;
    public boolean draggingInProgress;
    public long lastActionTime;
    public boolean paused;
    public boolean seekToInProgress;
    public int timelineCurrentSeekPosition;
    public boolean timelineExitOnSnap;
    public boolean timelineInSnapZone;
    public int timelinePreviousSeekPosition;
    public int timelineSeekDelta;
    public int timelineStartSeekPosition;
    
    public int getTimelineCurrentSeekPosition() {
        return this.timelineCurrentSeekPosition;
    }
    
    public boolean getTimelineExitOnSnap() {
        return this.timelineExitOnSnap;
    }
    
    public int getTimelinePreviousSeekPosition() {
        return this.timelinePreviousSeekPosition;
    }
    
    public int getTimelineStartSeekPosition() {
        return this.timelineStartSeekPosition;
    }
    
    public boolean isDraggingInProgress() {
        return this.draggingInProgress;
    }
    
    public boolean isSeekForward() {
        return this.timelineCurrentSeekPosition < this.timelinePreviousSeekPosition;
    }
    
    public void reset() {
        this.lastActionTime = 0L;
        this.draggingInProgress = false;
        this.seekToInProgress = false;
        this.audioSeekToInProgress = false;
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
    
    public void setTimelineExitOnSnap(final boolean timelineExitOnSnap) {
        this.timelineExitOnSnap = timelineExitOnSnap;
    }
    
    public void updatePosition(final int timelinePreviousSeekPosition) {
        this.timelineCurrentSeekPosition = this.timelinePreviousSeekPosition;
        this.timelinePreviousSeekPosition = timelinePreviousSeekPosition;
        this.timelineSeekDelta = Math.abs(this.timelineCurrentSeekPosition - this.timelinePreviousSeekPosition);
    }
}
