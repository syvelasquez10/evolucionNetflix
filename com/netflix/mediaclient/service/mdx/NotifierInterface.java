// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

public interface NotifierInterface
{
    void audiosub(final String p0, final String p1);
    
    void capability(final String p0, final String p1);
    
    void dialogCancel(final String p0, final String p1);
    
    void dialogShow(final String p0, final String p1);
    
    void error(final String p0, final int p1, final String p2);
    
    void metaData(final String p0, final String p1);
    
    void movieMetaData(final String p0, final String p1, final String p2, final int p3);
    
    void movieMetaDataAvailable(final String p0);
    
    void notready();
    
    void playbackEnd(final String p0);
    
    void playbackStart(final String p0);
    
    void ready();
    
    void simplePlaybackState(final String p0, final boolean p1, final boolean p2);
    
    void state(final String p0, final String p1, final int p2, final int p3);
    
    void targetList();
}
