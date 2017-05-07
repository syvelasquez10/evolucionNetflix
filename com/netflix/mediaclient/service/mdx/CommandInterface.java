// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

public interface CommandInterface
{
    void pinCancelled(final String p0);
    
    void pinConfirmed(final String p0);
    
    void playerAutoAdvance(final String p0, final int p1);
    
    void playerChangeMetaData(final String p0, final String p1, final String p2, final String p3);
    
    void playerDialogReponse(final String p0, final String p1, final String p2);
    
    void playerGetAudioSubtitle(final String p0);
    
    void playerGetCapability(final String p0);
    
    void playerGetCurrentState(final String p0);
    
    void playerPause(final String p0);
    
    void playerPlay(final String p0, final String p1, final int p2, final String p3, final int p4);
    
    void playerResume(final String p0);
    
    void playerSeek(final String p0, final int p1);
    
    void playerSetAudioSubtitle(final String p0, final String p1, final String p2);
    
    void playerSetVolume(final String p0, final int p1);
    
    void playerSkip(final String p0, final int p1);
    
    void playerStop(final String p0);
}
