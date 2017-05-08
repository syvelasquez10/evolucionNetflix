// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.exoplayback;

import com.netflix.mediaclient.servicemgr.IPlayer$PlaybackError;

public class ExoPlaybackError implements IPlayer$PlaybackError
{
    private ExoPlaybackError$ExoPlaybackErrorCode mCode;
    private String mExceptionStack;
    private String mMessage;
    private String mPlayerState;
    
    ExoPlaybackError(final ExoPlaybackError$ExoPlaybackErrorCode mCode, final String mMessage, final String mPlayerState, final String mExceptionStack) {
        this.mCode = mCode;
        this.mMessage = mMessage;
        this.mPlayerState = mPlayerState;
        this.mExceptionStack = mExceptionStack;
    }
    
    @Override
    public int getErrorCode() {
        return this.mCode.getValue();
    }
    
    public String getExceptionStack() {
        return this.mExceptionStack;
    }
    
    @Override
    public String getMessage() {
        return "ExoPlaybackError: [code : " + this.mCode + ", message : " + this.mMessage + " ]";
    }
    
    @Override
    public String getUiDisplayErrorCode() {
        if (this.mPlayerState != null) {
            return this.mPlayerState + "." + this.mCode.getValue();
        }
        return Integer.toString(this.mCode.getValue());
    }
}
