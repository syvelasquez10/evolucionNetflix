// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.graphics.Bitmap$Config;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.GridLayoutManager;
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
import com.netflix.mediaclient.servicemgr.interface_.details.Similarable;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import android.widget.AdapterView$OnItemSelectedListener;
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
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.support.v7.widget.RecyclerView;
import java.util.Stack;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.util.ItemDecorationBarkerGrid;
import android.view.View;
import java.util.List;
import com.netflix.mediaclient.ui.details.CopyrightView;
import com.netflix.mediaclient.ui.mdx.CastPlayerHelper$CastPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodesFrag;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class BarkerShowDetailsFrag$BookmarkedVideoDetails$FetchBookmarkCallback extends LoggingManagerCallback
{
    final /* synthetic */ BarkerShowDetailsFrag$BookmarkedVideoDetails this$1;
    
    public BarkerShowDetailsFrag$BookmarkedVideoDetails$FetchBookmarkCallback(final BarkerShowDetailsFrag$BookmarkedVideoDetails this$1, final String s) {
        this.this$1 = this$1;
        super(s);
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails epDetails, final Status status) {
        boolean b = true;
        super.onEpisodeDetailsFetched(epDetails, status);
        if (status.isError()) {
            Log.w("BarkerShowDetailsFrag", "Error status code fetching data - showing errors view");
            this.this$1.this$0.showErrorView();
            return;
        }
        if (epDetails == null) {
            Log.w("BarkerShowDetailsFrag", "No details in response!");
            this.this$1.this$0.showErrorView();
            return;
        }
        this.this$1.epDetails = epDetails;
        this.this$1.updateSynopsis = true;
        final int playableBookmarkPosition = epDetails.getPlayable().getPlayableBookmarkPosition();
        final BarkerShowDetailsFrag this$0 = this.this$1.this$0;
        if (playableBookmarkPosition <= 0) {
            b = false;
        }
        this$0.hasBookmark = b;
        if (this.this$1.this$0.hasBookmark) {
            this.this$1.updateWithBookmark();
        }
        else {
            this.this$1.updateWithNoBookmark();
        }
        this.this$1.this$0.showViews();
    }
}
