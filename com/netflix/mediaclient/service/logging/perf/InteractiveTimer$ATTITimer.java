// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.perf;

import com.netflix.mediaclient.ui.home.HomeActivity;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.widget.ImageView;

public class InteractiveTimer$ATTITimer extends InteractiveTimer
{
    public InteractiveTimer$ATTITimer(final InteractiveTimer$InteractiveListener interactiveTimer$InteractiveListener) {
        super(interactiveTimer$InteractiveListener);
    }
    
    public boolean shouldTrack(final ImageView imageView, final IClientLogging$AssetType clientLogging$AssetType) {
        return super.shouldTrack(imageView) && imageView.getContext() instanceof HomeActivity && clientLogging$AssetType == IClientLogging$AssetType.boxArt;
    }
}
