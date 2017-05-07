// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.media.Language;

public interface RemotePlayer$RemoteTargetUiListener
{
    void cancelDialog();
    
    void endOfPlayback();
    
    void error(final int p0, final String p1);
    
    void mdxStateChanged(final boolean p0);
    
    void showDialog(final RemoteDialog p0);
    
    void targetListChanged();
    
    void updateDuration(final int p0);
    
    void updateLanguage(final Language p0);
    
    void updateTargetCapabilities(final MdxTargetCapabilities p0);
    
    void updateUi(final RemotePlayer$RemoteTargetState p0);
    
    void updateVideoMetadata();
}
