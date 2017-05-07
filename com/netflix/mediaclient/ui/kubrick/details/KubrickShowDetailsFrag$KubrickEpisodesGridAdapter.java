// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.model.trackable.TrackableObject;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.Log;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.AbsListView;
import android.os.Bundle;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.widget.GridView;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodeListFrag;
import android.widget.AdapterView;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.ui.details.EpisodeRowView;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersBaseAdapter;
import com.netflix.mediaclient.ui.details.EpisodeListAdapter;

class KubrickShowDetailsFrag$KubrickEpisodesGridAdapter extends EpisodeListAdapter implements StickyGridHeadersBaseAdapter
{
    final /* synthetic */ KubrickShowDetailsFrag this$0;
    
    public KubrickShowDetailsFrag$KubrickEpisodesGridAdapter(final KubrickShowDetailsFrag this$0, final NetflixActivity netflixActivity, final KubrickShowDetailsFrag kubrickShowDetailsFrag) {
        this.this$0 = this$0;
        super(netflixActivity, kubrickShowDetailsFrag);
    }
    
    @Override
    protected EpisodeRowView createView() {
        return new KubrickShowDetailsFrag$KubrickEpisodeView(this.this$0, (Context)this.this$0.getActivity(), 2130903117);
    }
    
    @Override
    public int getCountForHeader(final int n) {
        return this.getCount();
    }
    
    @Override
    public View getHeaderView(final int n, final View view, final ViewGroup viewGroup) {
        if (n == 0) {
            return (View)this.this$0.detailsViewGroup;
        }
        return null;
    }
    
    @Override
    public int getNumHeaders() {
        return 1;
    }
    
    @Override
    public View getView(final int n, View view, final ViewGroup viewGroup) {
        view = super.getView(n, view, viewGroup);
        if (view != null) {
            ViewUtils.applyUniformPaddingToGridItem(view, this.this$0.getActivity().getResources().getDimensionPixelOffset(2131361990), this.this$0.numColumns, n);
        }
        return view;
    }
    
    @Override
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (!view.isInTouchMode()) {
            ((EpisodeRowView)view).handleItemClick();
        }
    }
}
