// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import java.util.Collection;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableObject;
import com.netflix.mediaclient.ui.offline.TutorialHelper;
import com.netflix.android.tooltips.Tooltip;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.transition.Transition$TransitionListener;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import com.netflix.mediaclient.android.app.Status;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.ui.mdx.MementoMovieDetailsActivity;
import android.support.v7.widget.RecyclerView$LayoutManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.ui.offline.DownloadButton;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DataUtil;
import android.os.Bundle;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.ui.offline.TutorialHelper$Tutorialable;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import android.transition.Transition;
import com.netflix.mediaclient.util.TransitionListenerAdapter;

class MovieDetailsFrag$3 extends TransitionListenerAdapter
{
    final /* synthetic */ MovieDetailsFrag this$0;
    
    MovieDetailsFrag$3(final MovieDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onTransitionEnd(final Transition transition) {
        this.this$0.setBackgroundResource(2131624173);
    }
    
    @Override
    public void onTransitionStart(final Transition transition) {
        this.this$0.setBackgroundResource(2131624189);
    }
}
