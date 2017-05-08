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
import com.netflix.mediaclient.android.app.Status;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.support.v7.widget.RecyclerView$LayoutManager;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationEdgePadding;
import android.view.ViewGroup$LayoutParams;
import android.content.Context;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import com.netflix.model.branches.FalkorVideo;
import java.util.HashMap;
import com.netflix.model.branches.FalkorActorStill;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import java.util.Map;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.app.LoadingStatus;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class RoleDetailsFrag$2 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ RoleDetailsFrag this$0;
    
    RoleDetailsFrag$2(final RoleDetailsFrag this$0) {
        this.this$0 = this$0;
    }
    
    public void onGlobalLayout() {
        ViewUtils.removeGlobalLayoutListener((View)this.this$0.actorDetailsView, (ViewTreeObserver$OnGlobalLayoutListener)this);
    }
}
