// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery;

import com.netflix.mediaclient.android.widget.ObjectRecycler;
import android.app.DialogFragment;
import com.netflix.mediaclient.ui.lomo.discovery.extended.BaseExtendedDiscoveryFrag;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter$Memento;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import java.util.List;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import android.content.res.Resources;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.Coppola2Utils;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.Discovery;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter;

public class PaginatedDiscoveryAdapter extends BasePaginatedAdapter<Discovery>
{
    public static final int DISCOVERY_TOTAL_ITEMS_PER_PAGE = 1;
    public static final int DISCOVERY_VIDEOS_TO_FETCH = 6;
    private static final String TAG = "PaginatedDiscoveryAdapter";
    private DiscoveryBackgroundAnimator animator;
    
    public PaginatedDiscoveryAdapter(final Context context, final DiscoveryBackgroundAnimator animator) {
        super(context);
        this.animator = animator;
    }
    
    @Override
    protected int computeNumItemsPerPage() {
        return 1;
    }
    
    @Override
    protected int computeNumVideosToFetchPerBatch(final int n) {
        return 6;
    }
    
    @Override
    public int getRowHeightInPx() {
        final Resources resources = this.getActivity().getResources();
        int n2;
        final int n = n2 = (int)resources.getDimension(2131361792);
        if (Coppola2Utils.shouldHideContinueWatchingLink((Context)this.activity)) {
            n2 = (int)(n - (resources.getDimension(2131362066) + (resources.getDimension(2131361879) + resources.getDimension(2131362125))));
        }
        Log.v("PaginatedDiscoveryAdapter", "Computed view height: " + n2);
        return n2;
    }
    
    @Override
    protected View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final List<Discovery> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        DiscoveryPage discoveryPage = ((ObjectRecycler<DiscoveryPage>)objectRecycler$ViewRecycler).pop(DiscoveryPage.class);
        if (discoveryPage == null) {
            discoveryPage = new DiscoveryPage((Context)this.getActivity());
        }
        if (list == null || list.size() < 1) {
            Log.e("PaginatedDiscoveryAdapter", "SPY-8068 - PaginatedDiscoveryAdapter - data is not ready to render the page");
            ErrorLoggingManager.logHandledException("SPY-8068 - PaginatedDiscoveryAdapter - data is not ready to render the page");
            return (View)discoveryPage;
        }
        final Discovery discovery = list.get(0);
        discoveryPage.updatePage(list.get(0), n2, (View$OnClickListener)new PaginatedDiscoveryAdapter$AllCWClickListener(this, basicLoMo, n2), (View$OnClickListener)new PaginatedDiscoveryAdapter$CollectionClickListener(this, discovery.getPivot1CollectionId(), discovery.getPivot1Title(), basicLoMo, n2 * 2, discovery.getPivot1TrackId()), (View$OnClickListener)new PaginatedDiscoveryAdapter$CollectionClickListener(this, discovery.getPivot2CollectionId(), discovery.getPivot2Title(), basicLoMo, n2 * 2 + 1, discovery.getPivot2TrackId()), basicLoMo);
        return (View)discoveryPage;
    }
    
    @Override
    public void restoreFromMemento(final BasePaginatedAdapter$Memento basePaginatedAdapter$Memento) {
        super.restoreFromMemento(basePaginatedAdapter$Memento);
        if (this.data != null && this.data.size() > 0) {
            this.animator.setData((List<Discovery>)this.data);
        }
    }
    
    protected void updateAnimatorData(final List<Discovery> list) {
        this.animator.updateData(list);
        final DialogFragment dialogFragment = ((NetflixActivity)this.getActivity()).getDialogFragment();
        if (dialogFragment instanceof BaseExtendedDiscoveryFrag) {
            ((BaseExtendedDiscoveryFrag)dialogFragment).restorePage(((NetflixActivity)this.getActivity()).getServiceManager(), this.animator);
        }
        else if (Log.isLoggable()) {
            Log.w("PaginatedDiscoveryAdapter", "PaginatedDiscoveryAdapter received non-supported dialog type: " + dialogFragment + "; ignoring...");
        }
    }
}
