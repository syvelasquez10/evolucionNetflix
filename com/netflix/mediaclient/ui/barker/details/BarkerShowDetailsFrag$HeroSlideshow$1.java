// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

import android.text.TextUtils;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.details.KubrickShowDetails;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.os.Handler;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class BarkerShowDetailsFrag$HeroSlideshow$1 implements Runnable
{
    final /* synthetic */ BarkerShowDetailsFrag$HeroSlideshow this$1;
    
    BarkerShowDetailsFrag$HeroSlideshow$1(final BarkerShowDetailsFrag$HeroSlideshow this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        this.this$1.animateSlideshow();
        this.this$1.handler.postDelayed(this.this$1.slideshowTask, 4000L);
    }
}
