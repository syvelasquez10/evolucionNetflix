// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

public interface NotifierInterface
{
    void abortPinVerification(final String p0, final boolean p1);
    
    void audiosub(final String p0, final String p1);
    
    void capability(final String p0, final String p1);
    
    void dialogCancel(final String p0, final String p1);
    
    void dialogShow(final String p0, final String p1);
    
    void error(final String p0, final int p1, final String p2);
    
    void metaData(final String p0, final String p1);
    
    void movieMetaData(final String p0, final String p1, final String p2, final int p3);
    
    void movieMetaDataAvailable(final String p0);
    
    void notready();
    
    void playbackEnd(final String p0, final String p1, final boolean p2);
    
    void playbackStart(final String p0);
    
    void postplayState(final String p0, final String p1);
    
    void ready();
    
    void requestPinVerification(final String p0, final String p1, final int p2, final int p3, final String p4);
    
    void simplePlaybackState(final String p0, final boolean p1, final boolean p2, final String p3);
    
    void state(final String p0, final String p1, final int p2, final int p3);
    
    void targetList();
}
