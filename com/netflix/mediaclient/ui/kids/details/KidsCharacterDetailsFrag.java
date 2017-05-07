// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;
import com.netflix.mediaclient.android.widget.ErrorWrapper;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.KidsCharacterDetails;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.View;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class KidsCharacterDetailsFrag extends NetflixFrag
{
    private static final String EXTRA_CHARACTER_ID = "extra_character_id";
    private static final Boolean REFRESH_FETCH;
    private static final String TAG = "KidsCharacterDetailsFrag";
    private KidsCharacterDetailsAdapter adapter;
    private String charId;
    private View content;
    private KidsDetailsViewGroup detailsViewGroup;
    private boolean isLoading;
    private LoadingAndErrorWrapper leWrapper;
    private StickyListHeadersListView listView;
    private ServiceManager manager;
    private long refreshRequestId;
    private long requestId;
    
    static {
        REFRESH_FETCH = true;
    }
    
    private void completeInitIfPossible() {
        if (this.getActivity() == null) {
            Log.v("KidsCharacterDetailsFrag", "Can't complete init yet - activity is null");
            return;
        }
        if (this.manager == null) {
            Log.v("KidsCharacterDetailsFrag", "Can't complete init yet - manager is null");
            return;
        }
        if (this.content == null) {
            Log.v("KidsCharacterDetailsFrag", "Can't complete init yet - details view is null");
            return;
        }
        if (this.charId == null) {
            Log.v("KidsCharacterDetailsFrag", "Can't complete init yet - charId is null");
            return;
        }
        Log.v("KidsCharacterDetailsFrag", "All clear - completing init process...");
        this.fetchCharacterDetails();
    }
    
    public static Fragment create(final String s) {
        final KidsCharacterDetailsFrag kidsCharacterDetailsFrag = new KidsCharacterDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("extra_character_id", s);
        kidsCharacterDetailsFrag.setArguments(arguments);
        return kidsCharacterDetailsFrag;
    }
    
    private void fetchCharacterDetails() {
        boolean b = true;
        if (this.manager == null) {
            Log.w("KidsCharacterDetailsFrag", "Manager is null - can't get character details");
            return;
        }
        this.showLoadingView();
        this.isLoading = true;
        this.requestId = System.nanoTime();
        Log.v("KidsCharacterDetailsFrag", "Fetching data for character ID: " + this.charId);
        final ServiceManager manager = this.manager;
        final String charId = this.charId;
        final long requestId = this.requestId;
        if (KidsCharacterDetailsFrag.REFRESH_FETCH) {
            b = false;
        }
        manager.fetchKidsCharacterDetails(charId, new FetchCharacterDetailsCallback(requestId, b));
    }
    
    private void refreshCharacterDetails() {
        if (this.manager == null) {
            Log.w("KidsCharacterDetailsFrag", "Manager is null - can't refresh character details");
            return;
        }
        this.refreshRequestId = System.nanoTime();
        Log.d("TAG", String.format("refresh watchNext for character id: %s, refreshRequestId: %d", this.charId, this.refreshRequestId));
        this.manager.fetchKidsCharacterDetails(this.charId, new FetchCharacterDetailsCallback(this.refreshRequestId, KidsCharacterDetailsFrag.REFRESH_FETCH));
    }
    
    private void showErrorView() {
        this.leWrapper.showErrorView(true);
        AnimationUtils.hideView((View)this.listView, true);
    }
    
    private void showLoadingView() {
        this.leWrapper.showLoadingView(true);
        AnimationUtils.hideView((View)this.listView, true);
    }
    
    private void showStandardViews() {
        this.leWrapper.hide(true);
        AnimationUtils.showView((View)this.listView, true);
    }
    
    private void updateCharacterDetails(final KidsCharacterDetails kidsCharacterDetails) {
        Log.v("KidsCharacterDetailsFrag", "Updating character details: " + kidsCharacterDetails.getTitle());
        this.detailsViewGroup.updateDetails(kidsCharacterDetails);
        this.adapter = new KidsCharacterDetailsAdapter(this, kidsCharacterDetails);
        this.listView.setAdapter(this.adapter);
        this.showStandardViews();
    }
    
    public boolean isLoadingData() {
        return this.isLoading;
    }
    
    public void onActivityCreated(final Bundle bundle) {
        Log.v("KidsCharacterDetailsFrag", "onActivityCreated");
        super.onActivityCreated(bundle);
        this.completeInitIfPossible();
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        this.charId = this.getArguments().getString("extra_character_id");
        super.onCreate(bundle);
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        this.content = layoutInflater.inflate(2130903109, (ViewGroup)null);
        (this.listView = (StickyListHeadersListView)this.content.findViewById(16908298)).setAreHeadersSticky(false);
        this.listView.setDivider(null);
        this.detailsViewGroup = new KidsDetailsViewGroup((Context)this.getActivity());
        this.listView.addHeaderView((View)this.detailsViewGroup, null, false);
        this.leWrapper = new LoadingAndErrorWrapper(this.content, new ErrorWrapper.Callback() {
            @Override
            public void onRetryRequested() {
                KidsCharacterDetailsFrag.this.showLoadingView();
                KidsCharacterDetailsFrag.this.fetchCharacterDetails();
            }
        });
        return this.content;
    }
    
    @Override
    public void onManagerReady(final ServiceManager manager, final int n) {
        Log.v("KidsCharacterDetailsFrag", "onManagerReady");
        super.onManagerReady(manager, n);
        this.manager = manager;
        this.completeInitIfPossible();
    }
    
    @Override
    public void onManagerUnavailable(final ServiceManager serviceManager, final int n) {
        super.onManagerUnavailable(serviceManager, n);
        this.manager = null;
    }
    
    public void onResume() {
        super.onResume();
        this.refreshCharacterDetails();
    }
    
    private class FetchCharacterDetailsCallback extends LoggingManagerCallback
    {
        private final Boolean isRefresh;
        private final long requestId;
        
        public FetchCharacterDetailsCallback(final long requestId, final Boolean isRefresh) {
            super("KidsCharacterDetailsFrag");
            this.requestId = requestId;
            this.isRefresh = isRefresh;
        }
        
        @Override
        public void onKidsCharacterDetailsFetched(final KidsCharacterDetails kidsCharacterDetails, final Boolean b, final int n) {
            super.onKidsCharacterDetailsFetched(kidsCharacterDetails, b, n);
            if (this.isRefresh && !b) {
                Log.v("KidsCharacterDetailsFrag", "refreshCase data not changed - nothing to do");
                return;
            }
            long n2 = KidsCharacterDetailsFrag.this.requestId;
            if (this.isRefresh) {
                n2 = KidsCharacterDetailsFrag.this.refreshRequestId;
            }
            if (this.requestId != n2) {
                Log.v("KidsCharacterDetailsFrag", "Ignoring stale callback");
                return;
            }
            if (n != 0) {
                Log.w("KidsCharacterDetailsFrag", "Invalid status code");
                KidsCharacterDetailsFrag.this.showErrorView();
                return;
            }
            if (kidsCharacterDetails == null) {
                Log.v("KidsCharacterDetailsFrag", "No details in response");
                KidsCharacterDetailsFrag.this.showErrorView();
                return;
            }
            if (this.isRefresh) {
                KidsCharacterDetailsFrag.this.detailsViewGroup.updateDetails(kidsCharacterDetails);
                return;
            }
            KidsCharacterDetailsFrag.this.updateCharacterDetails(kidsCharacterDetails);
        }
    }
}
