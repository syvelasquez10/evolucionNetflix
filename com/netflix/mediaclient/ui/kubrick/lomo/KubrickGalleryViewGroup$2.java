// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.util.DataUtil;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import java.util.List;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.view.View;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import com.netflix.mediaclient.ui.lomo.LoMoUtils$LoMoWidthType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;

class KubrickGalleryViewGroup$2 extends GridLayoutManager$SpanSizeLookup
{
    final /* synthetic */ KubrickGalleryViewGroup this$0;
    
    KubrickGalleryViewGroup$2(final KubrickGalleryViewGroup this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public int getSpanSize(final int n) {
        if (this.this$0.page == 0 && n == 0) {
            return 2;
        }
        return 1;
    }
}
