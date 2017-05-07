// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.javabridge.ui;

public enum IMedia$MediaEventEnum
{
    generic_background("background"), 
    media_audioTrackChanged("audioTrackChanged"), 
    media_buffering("buffering"), 
    media_bufferingComplete("bufferingComplete"), 
    media_bufferrange("bufferrange"), 
    media_endOfStream("endOfStream"), 
    media_error("error"), 
    media_exception("exception"), 
    media_nccp("Nccp"), 
    media_nccpError("nccperror"), 
    media_newStream("newStream"), 
    media_openComplete("openComplete"), 
    media_removeSubtitle("removeSubtitle"), 
    media_setvideobitraterange("setvideobitraterange"), 
    media_setvideoresolutionrange("setvideoresolutionrange"), 
    media_showSubtitle("showSubtitle"), 
    media_skip("skip"), 
    media_stateChanged("statechanged"), 
    media_streamSelected("streamSelected"), 
    media_subtitleTrackChanged("subtitleTrackChanged"), 
    media_subtitledata("subtitledata"), 
    media_swim("swim"), 
    media_underflow("underflow"), 
    media_updatePts("updatePts"), 
    media_updateVideoBitrate("updateVideoBitrate"), 
    media_videoWindowChanged("videoWindowChanged"), 
    media_videobitraterangechanged("videobitraterangechanged"), 
    media_warning("warning");
    
    protected String eventName;
    
    private IMedia$MediaEventEnum(final String eventName) {
        this.eventName = eventName;
    }
    
    public final String getName() {
        return this.eventName;
    }
}
