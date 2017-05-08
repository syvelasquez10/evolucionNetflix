// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery.extended;

import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;

class CWExtendedDiscoveryFrag$CWViewHolder$1 implements CWExtendedDiscoveryFrag$RemotePlaybackListener
{
    final /* synthetic */ CWExtendedDiscoveryFrag$CWViewHolder this$1;
    
    CWExtendedDiscoveryFrag$CWViewHolder$1(final CWExtendedDiscoveryFrag$CWViewHolder this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onRemotePlaybackInitiated() {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.this$1.this$0.getActivity())) {
            this.this$1.this$0.dismiss();
        }
    }
}
