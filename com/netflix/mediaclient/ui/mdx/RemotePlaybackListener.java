// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.media.Language;

public interface RemotePlaybackListener
{
    void cancelDialog();
    
    void error(final int p0, final String p1);
    
    void mdxStateChanged(final boolean p0);
    
    void showDialog(final RemoteDialog p0);
    
    void targetListChanged();
    
    void updateDuration(final int p0);
    
    void updateLanguage(final Language p0);
    
    void updateState(final String p0, final int p1, final int p2);
    
    void updateTargetCapabilities(final MdxTargetCapabilities p0);
    
    void updateVideoMetadata();
}
