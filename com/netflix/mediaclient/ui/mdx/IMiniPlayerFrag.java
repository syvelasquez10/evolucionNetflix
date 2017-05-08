// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View;
import android.view.KeyEvent;
import com.netflix.mediaclient.util.MdxUtils$MdxTargetSelectionDialogInterface;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.ui.details.AbsEpisodeView$EpisodeRowListener;

public interface IMiniPlayerFrag extends AbsEpisodeView$EpisodeRowListener, IHandleBackPress, NetflixRatingBar$RatingBarDataProvider, MdxUtils$MdxTargetSelectionDialogInterface
{
    void attachMenuItem(final MdxMenu p0);
    
    boolean dispatchKeyEvent(final KeyEvent p0);
    
    View getSlidingPanelDragView();
    
    int getVolume();
    
    void initMdxComponents();
    
    boolean isMdxMenuEnabled();
    
    boolean isShowing();
    
    boolean isVisible();
    
    void onManagerReady(final ServiceManager p0, final Status p1);
    
    void onManagerUnavailable(final ServiceManager p0, final Status p1);
    
    void onPanelCollapsed();
    
    void onPanelExpanded();
    
    void onPanelSlide(final float p0);
    
    void onResumeFragments();
    
    void sendDialogResponse(final String p0);
    
    void setVolume(final int p0);
}
