// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.perf;

import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.widget.ImageView;

public class InteractiveTimer$ATTDPTimer extends InteractiveTimer
{
    public InteractiveTimer$ATTDPTimer(final InteractiveTimer$InteractiveListener interactiveTimer$InteractiveListener) {
        super(interactiveTimer$InteractiveListener);
    }
    
    public boolean shouldTrack(final ImageView imageView, final IClientLogging$AssetType clientLogging$AssetType) {
        return super.shouldTrack(imageView) && imageView.getContext() instanceof DetailsActivity && clientLogging$AssetType == IClientLogging$AssetType.boxArt && clientLogging$AssetType == IClientLogging$AssetType.heroImage;
    }
}
