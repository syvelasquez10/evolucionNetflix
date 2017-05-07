// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;

public class KubrickVideoDetailsClickListener extends VideoDetailsClickListener
{
    public KubrickVideoDetailsClickListener(final NetflixActivity netflixActivity, final PlayContextProvider playContextProvider) {
        super(netflixActivity, playContextProvider);
    }
    
    @Override
    protected void launchDetailsActivity(final NetflixActivity netflixActivity, final Video video, final PlayContext playContext) {
        DetailsActivityLauncher.show(netflixActivity, video, playContext, "KubrickDeetsClickListener", 65536);
    }
}
