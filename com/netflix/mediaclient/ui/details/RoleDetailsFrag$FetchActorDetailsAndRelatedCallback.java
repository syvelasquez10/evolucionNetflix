// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.android.app.LoadingStatus$LoadingStatusCallback;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView$LayoutManager;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationEdgePadding;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.os.Bundle;
import com.netflix.model.branches.FalkorVideo;
import java.util.HashMap;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import java.util.Map;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import java.util.Iterator;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.model.branches.FalkorActorStill;
import com.netflix.model.branches.FalkorPerson;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class RoleDetailsFrag$FetchActorDetailsAndRelatedCallback extends LoggingManagerCallback
{
    private final long requestId;
    final /* synthetic */ RoleDetailsFrag this$0;
    
    public RoleDetailsFrag$FetchActorDetailsAndRelatedCallback(final RoleDetailsFrag this$0, final long requestId) {
        this.this$0 = this$0;
        super("RoleDetailsFrag");
        this.requestId = requestId;
    }
    
    @Override
    public void onPersonDetailFetched(final FalkorPerson falkorPerson, final FalkorActorStill falkorActorStill, final Status status) {
        super.onPersonDetailFetched(falkorPerson, falkorActorStill, status);
        if (this.requestId != this.this$0.detailsRequestId || this.this$0.isDestroyed()) {
            Log.v("RoleDetailsFrag", "Ignoring stale callback");
        }
        else {
            this.this$0.isLoading = false;
            if (status.isError()) {
                Log.w("RoleDetailsFrag", "Invalid status code");
                this.this$0.leWrapper.showErrorView(false);
                return;
            }
            this.this$0.still = falkorActorStill;
            if (falkorPerson != null && this.this$0.actorDetailsView != null) {
                this.this$0.actorDetailsView.updateDetails(falkorPerson, falkorActorStill != null);
                this.this$0.recyclerView.setVisibility(0);
            }
        }
    }
    
    @Override
    public void onPersonRelatedFetched(final FalkorPerson falkorPerson, final List<Video> list, final Status status) {
        super.onPersonRelatedFetched(falkorPerson, list, status);
        this.this$0.leWrapper.hide(false);
        if (this.requestId != this.this$0.relatedRequestId || this.this$0.isDestroyed()) {
            Log.v("RoleDetailsFrag", "Ignoring stale callback");
        }
        else {
            this.this$0.isLoading = false;
            if (status.isError()) {
                Log.w("RoleDetailsFrag", "Invalid status code");
                if (this.this$0.actorDetailsView != null) {
                    this.this$0.actorDetailsView.onNetFlixLabel.setVisibility(4);
                }
            }
            else {
                if (this.this$0.actorDetailsView != null) {
                    this.this$0.actorDetailsView.onNetFlixLabel.setVisibility(0);
                }
                if (list != null) {
                    final ArrayList<Video> items = new ArrayList<Video>();
                    for (final Video video : list) {
                        if (video.getType() != null && video.getType() != VideoType.UNKNOWN) {
                            items.add(video);
                        }
                    }
                    this.this$0.adapter.setItems(items);
                }
            }
        }
    }
}
