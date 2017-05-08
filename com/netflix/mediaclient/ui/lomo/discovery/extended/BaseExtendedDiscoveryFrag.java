// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery.extended;

import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.os.Parcelable;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.View$OnClickListener;
import android.view.View;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.widget.TextView;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.List;
import android.widget.ImageView;
import com.netflix.mediaclient.ui.lomo.discovery.PaginatedDiscoveryAdapter$BlurredStoryArtProvider;
import android.support.v7.widget.RecyclerView$ViewHolder;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;

public abstract class BaseExtendedDiscoveryFrag extends NetflixDialogFrag
{
    private static final String ID_KEY = "id";
    private static final String TAG = "BaseExtendedDiscoveryFrag";
    private static final String TITLE_KEY = "title";
    private static final String TRACKABLE_KEY = "trackable";
    protected RecyclerView$Adapter<RecyclerView$ViewHolder> adapter;
    protected PaginatedDiscoveryAdapter$BlurredStoryArtProvider backgroundBoxartProvider;
    protected ImageView bgImageView;
    protected List<Video> collectionData;
    protected final ErrorWrapper$Callback errorCallback;
    private boolean fragmentWasShown;
    protected LoadingAndErrorWrapper leWrapper;
    protected RecyclerView recyclerView;
    protected String title;
    protected TextView titleView;
    protected PlayContext trackable;
    protected long turboId;
    
    public BaseExtendedDiscoveryFrag() {
        this.errorCallback = new BaseExtendedDiscoveryFrag$2(this);
    }
    
    private void updateBackgroundIfReady() {
        if (this.bgImageView != null && this.backgroundBoxartProvider != null) {
            this.bgImageView.setImageDrawable(this.backgroundBoxartProvider.getBlurredStoryArt());
        }
    }
    
    protected void findViews(final View view) {
        this.recyclerView = (RecyclerView)view.findViewById(2131689778);
        view.findViewById(2131689776).setOnClickListener((View$OnClickListener)new BaseExtendedDiscoveryFrag$1(this));
        this.titleView = (TextView)view.findViewById(2131689777);
        this.bgImageView = (ImageView)view.findViewById(2131689775);
        this.leWrapper = new LoadingAndErrorWrapper(view, this.errorCallback);
    }
    
    @Override
    public void onActivityCreated(final Bundle bundle) {
        super.onActivityCreated(bundle);
        this.getDialog().getWindow().setWindowAnimations(2131427635);
        this.updateBackgroundIfReady();
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setHasOptionsMenu(true);
        this.setStyle(1, 2131427727);
        if (bundle != null) {
            this.trackable = (PlayContext)bundle.getParcelable("trackable");
            this.turboId = bundle.getLong("id");
            this.title = bundle.getString("title");
        }
    }
    
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("BaseExtendedDiscoveryFrag", "onCreateView called");
        final View inflate = layoutInflater.inflate(2130903109, (ViewGroup)null, false);
        this.findViews(inflate);
        this.setupRecyclerView();
        return inflate;
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putParcelable("trackable", (Parcelable)this.trackable);
        bundle.putLong("id", this.turboId);
        bundle.putString("title", this.title);
    }
    
    public void onStart() {
        super.onStart();
        if (!this.fragmentWasShown) {
            this.fragmentWasShown = true;
            return;
        }
        this.getDialog().getWindow().setWindowAnimations(2131427636);
    }
    
    public void restorePage(final ServiceManager serviceManager, final PaginatedDiscoveryAdapter$BlurredStoryArtProvider paginatedDiscoveryAdapter$BlurredStoryArtProvider) {
        if (this.title == null || this.turboId == 0L) {
            final String string = "TurboExtendedDiscoveryFrag::update() was called with title: " + this.title + "; and turboId: " + this.turboId;
            Log.w("BaseExtendedDiscoveryFrag", string);
            ErrorLoggingManager.logHandledException(string);
            return;
        }
        this.updatePage(serviceManager, this.turboId, this.title, this.trackable, paginatedDiscoveryAdapter$BlurredStoryArtProvider);
    }
    
    protected abstract void setupLayoutManagerAndAdapter();
    
    protected void setupRecyclerView() {
        this.recyclerView.setFocusable(false);
        this.recyclerView.setVerticalScrollBarEnabled(false);
        this.setupLayoutManagerAndAdapter();
        this.recyclerView.setAdapter(this.adapter);
    }
    
    public void updatePage(final ServiceManager serviceManager, final long turboId, final String title, final PlayContext trackable, final PaginatedDiscoveryAdapter$BlurredStoryArtProvider backgroundBoxartProvider) {
        this.backgroundBoxartProvider = backgroundBoxartProvider;
        this.trackable = trackable;
        this.turboId = turboId;
        this.title = title;
        this.updateBackgroundIfReady();
    }
}
