// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.model.details.VideoDetails;
import com.netflix.mediaclient.android.widget.SnappableSeekBar$OnSnappableSeekBarChangeListener;

interface MdxMiniPlayerViews$MdxMiniPlayerViewCallbacks extends SnappableSeekBar$OnSnappableSeekBarChangeListener
{
    float getCurrentRating();
    
    VideoDetails getCurrentVideo();
    
    ServiceManager getManager();
    
    IMdx getMdx();
    
    boolean isEpisodeReady();
    
    boolean isLanguageReady();
    
    boolean isPanelExpanded();
    
    boolean isPlayingRemotely();
    
    boolean isRemotePlayerReady();
    
    boolean isVideoUnshared();
    
    void notifyControlsEnabled(final boolean p0);
    
    void onPauseClicked();
    
    void onResumeClicked();
    
    void onShowLanguageSelectorDialog();
    
    void onSkipBackClicked();
    
    void onStopClicked();
}
