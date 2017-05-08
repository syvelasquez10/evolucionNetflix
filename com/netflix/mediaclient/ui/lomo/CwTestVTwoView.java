// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import android.content.Context;
import android.widget.TextView;

public class CwTestVTwoView extends CwView
{
    TextView episodeInfo;
    boolean showEpisodeInfo;
    
    public CwTestVTwoView(final Context context, final boolean showEpisodeInfo) {
        super(context);
        this.showEpisodeInfo = showEpisodeInfo;
    }
    
    @Override
    public String getImageUrl(final CWVideo cwVideo, final boolean b) {
        return BrowseExperience.getLomoVideoViewImageUrl(this.getContext(), cwVideo, CwTestVTwoView.class, 0);
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903086;
    }
    
    @Override
    public void setInfoViewId(final int id) {
        this.info.setId(id);
    }
    
    @Override
    public void setTitle(final CWVideo cwVideo) {
    }
    
    @Override
    public void update(final CWVideo cwVideo, final Trackable trackable, final int n, final boolean b, final boolean b2) {
        super.update(cwVideo, trackable, n, b, b2);
        this.episodeInfo = (TextView)this.findViewById(2131689739);
        if (VideoType.SHOW == cwVideo.getType()) {
            this.episodeInfo.setText((CharSequence)this.getContext().getString(2131231180, new Object[] { cwVideo.getSeasonAbbrSeqLabel(), cwVideo.getEpisodeNumber() }));
            ViewUtils.setVisibleOrGone((View)this.episodeInfo, this.showEpisodeInfo);
            return;
        }
        this.episodeInfo.setVisibility(8);
    }
}
