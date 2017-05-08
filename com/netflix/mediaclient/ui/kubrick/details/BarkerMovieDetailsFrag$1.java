// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.support.v7.widget.RecyclerView$ItemDecoration;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.servicemgr.interface_.details.Similarable;
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import android.view.View$OnClickListener;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import android.os.Bundle;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import android.support.v7.widget.RecyclerView;
import java.util.Stack;
import com.netflix.mediaclient.util.ItemDecorationBarkerGrid;
import android.view.View;
import com.netflix.mediaclient.ui.details.CopyrightView;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.ui.details.MovieDetailsFrag;

class BarkerMovieDetailsFrag$1 implements Runnable
{
    final /* synthetic */ BarkerMovieDetailsFrag this$0;
    
    BarkerMovieDetailsFrag$1(final BarkerMovieDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.recyclerView.scrollToPosition(0);
        if (this.this$0.parallaxScroller != null) {
            this.this$0.parallaxScroller.setToolbarColor(0);
        }
    }
}
