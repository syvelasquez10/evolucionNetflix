// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import java.util.Collection;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class KidsGenresLoMoFrag$GenresVideoFetchCallback extends LoggingManagerCallback
{
    final /* synthetic */ KidsGenresLoMoFrag this$0;
    
    public KidsGenresLoMoFrag$GenresVideoFetchCallback(final KidsGenresLoMoFrag this$0, final String s) {
        this.this$0 = this$0;
        super(s);
    }
    
    @Override
    public void onVideosFetched(final List<Video> items, final Status status) {
        super.onVideosFetched(items, status);
        if (status.isError()) {
            Log.w("KidsGenresLoMoFrag", "Invalid status code");
            this.this$0.showErrorView();
            return;
        }
        if (items != null && items.size() > 0) {
            this.this$0.adapter.setItems(items);
            this.this$0.hideLoadingAndErrorViews();
            return;
        }
        this.this$0.showErrorView();
    }
}
