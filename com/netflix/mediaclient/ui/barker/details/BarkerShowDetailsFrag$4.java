// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.view.View$OnClickListener;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.details.Similarable;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.barker.BarkerUtils;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import android.os.Parcelable;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.Status;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.support.v7.widget.RecyclerView;
import java.util.Stack;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.util.ItemDecorationBarkerGrid;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.ui.details.CopyrightView;
import com.netflix.mediaclient.ui.mdx.CastPlayerHelper$CastPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemSelectedListener;

class BarkerShowDetailsFrag$4 implements AdapterView$OnItemSelectedListener
{
    final /* synthetic */ BarkerShowDetailsFrag this$0;
    
    BarkerShowDetailsFrag$4(final BarkerShowDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onItemSelected(final AdapterView<?> adapterView, final View view, int firstVisibleItemPosition, final long n) {
        final GridLayoutManager gridLayoutManager = (GridLayoutManager)this.this$0.recyclerView.getLayoutManager();
        firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition();
        this.this$0.recyclerView.post((Runnable)new BarkerShowDetailsFrag$4$1(this, firstVisibleItemPosition, gridLayoutManager));
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
    }
}
