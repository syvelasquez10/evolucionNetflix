// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.text.TextUtils;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.details.KubrickShowDetails;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class KubrickShowDetailsFrag$HeroSlideshow$1 implements Runnable
{
    final /* synthetic */ KubrickShowDetailsFrag$HeroSlideshow this$1;
    
    KubrickShowDetailsFrag$HeroSlideshow$1(final KubrickShowDetailsFrag$HeroSlideshow this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.animateSlideshow();
        this.this$1.handler.postDelayed(this.this$1.slideshowTask, 3000L);
    }
}
