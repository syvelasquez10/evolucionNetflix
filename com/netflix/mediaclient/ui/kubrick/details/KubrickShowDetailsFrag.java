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
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.AdapterView$OnItemClickListener;
import android.widget.ListAdapter;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.widget.GridView;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import android.view.View;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersGridView;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodeListFrag;

public class KubrickShowDetailsFrag extends EpisodeListFrag implements ErrorWrapper$Callback, ManagerStatusListener, ServiceManagerProvider, MdxMiniPlayerFrag$MdxMiniPlayerDialog
{
    private static final double EPISODE_IMAGE_HEIGHT_RATIO = 0.5625;
    private static final String RELATED = "EXTRA_SHOW_RELATED_TTLES";
    private static final String TAG = "KubrickShowDetailsFrag";
    private int episodeImageHeight;
    private int fragWidth;
    private StickyGridHeadersGridView gridView;
    private View gridViewGroup;
    private final KubrickShowDetailsFrag$HeroSlideshow heroSlideshow;
    private boolean isSpinnerShown;
    private int numColumns;
    private View rootContainer;
    
    public KubrickShowDetailsFrag() {
        this.heroSlideshow = new KubrickShowDetailsFrag$HeroSlideshow(this, null);
    }
    
    private void calculateEpisodeImageHeight(final GridView gridView) {
        this.episodeImageHeight = (int)(this.fragWidth / this.numColumns * 0.5625);
    }
    
    private int calculateSpinnerLeftPosition() {
        int n = 0;
        final int detailsPageContentWidth = KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity());
        if (detailsPageContentWidth > 0) {
            n = (DeviceUtils.getScreenWidthInPixels((Context)this.getActivity()) - detailsPageContentWidth) / 2;
        }
        return n + (int)this.getResources().getDimension(2131361997);
    }
    
    public static NetflixDialogFrag create(final String s, final String s2) {
        final KubrickShowDetailsFrag kubrickShowDetailsFrag = new KubrickShowDetailsFrag();
        kubrickShowDetailsFrag.setStyle(1, 2131558696);
        return EpisodeListFrag.applyCreateArgs(kubrickShowDetailsFrag, s, s2, true);
    }
    
    private SeasonDetails getCurrentSeasonDetail() {
        if (this.spinner == null) {
            return null;
        }
        return (SeasonDetails)this.spinner.getItemAtPosition(this.spinner.getSelectedItemPosition());
    }
    
    private void restoreScrollPosition(final float n) {
        this.gridView.post((Runnable)new KubrickShowDetailsFrag$2(this, n));
    }
    
    private void setupClicks() {
        if (this.rootContainer != null) {
            this.rootContainer.setOnClickListener((View$OnClickListener)new KubrickShowDetailsFrag$3(this));
        }
    }
    
    private void setupGridView() {
        this.gridView.setChoiceMode(1);
        this.gridView.setFocusable(false);
        this.gridView.setVerticalScrollBarEnabled(false);
        this.episodesAdapter = new KubrickShowDetailsFrag$KubrickEpisodesGridAdapter(this, (NetflixActivity)this.getActivity(), this);
        this.gridView.setAdapter((ListAdapter)this.episodesAdapter);
        this.gridView.setOnItemClickListener((AdapterView$OnItemClickListener)this.episodesAdapter);
        this.gridView.setAreHeadersSticky(false);
        this.numColumns = this.gridView.getContext().getResources().getInteger(2131427338);
        this.gridView.setNumColumns(this.numColumns);
        this.calculateEpisodeImageHeight(this.gridView);
    }
    
    private void setupGridViewlayout(final View view) {
        if (this.gridViewGroup == null) {
            return;
        }
        this.fragWidth = KubrickUtils.getDetailsPageContentWidth((Context)this.getNetflixActivity());
        final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(this.fragWidth, -1);
        layoutParams.gravity = 1;
        this.gridViewGroup.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
    }
    
    private void showRelatedTitlesIfRequired(final Bundle bundle) {
        if (bundle != null && bundle.getBoolean("EXTRA_SHOW_RELATED_TTLES", false)) {
            this.handler.postDelayed((Runnable)new KubrickShowDetailsFrag$1(this), 200L);
        }
    }
    
    private void toggleSpinnerVisibility() {
        if (this.spinnerViewGroup == null) {
            return;
        }
        final ViewGroup viewGroup = (ViewGroup)this.getActivity().findViewById(16908290);
        if (!this.isSpinnerShown) {
            this.reAttachEpisodesSpinner();
            this.isSpinnerShown = true;
            return;
        }
        viewGroup.removeView((View)this.spinnerViewGroup);
        this.isSpinnerShown = false;
    }
    
    @Override
    protected void addSpinnerAsHeader() {
    }
    
    @Override
    protected ViewGroup createSeasonsSpinnerGroup() {
        final ViewGroup seasonsSpinnerGroup = super.createSeasonsSpinnerGroup();
        seasonsSpinnerGroup.setBackgroundResource(2131296356);
        seasonsSpinnerGroup.setPadding(this.calculateSpinnerLeftPosition(), 0, 0, 0);
        return seasonsSpinnerGroup;
    }
    
    protected void findViews(final View view) {
        this.gridView = (StickyGridHeadersGridView)view.findViewById(16908298);
        this.rootContainer = view.findViewById(2131165480);
        this.gridViewGroup = view.findViewById(2131165683);
    }
    
    @Override
    protected int getCheckedItemPosition() {
        return 0;
    }
    
    @Override
    protected int getHeaderViewsCount() {
        return 1;
    }
    
    @Override
    protected AbsListView getMainView() {
        return (AbsListView)this.gridView;
    }
    
    @Override
    public void hideDetailViewHeader() {
    }
    
    @Override
    protected void initDetailsViewGroup() {
        (this.detailsViewGroup = new KubrickVideoDetailsViewGroup((Context)this.getActivity())).removeActionBarDummyView();
    }
    
    @Override
    protected boolean isListVisible() {
        return this.gridView.getVisibility() == 0;
    }
    
    @Override
    protected void makeFetchShowDetailsRequest(final ServiceManager serviceManager, final String s, final String s2, final boolean b, final long n) {
        serviceManager.getBrowse().fetchShowDetailsAndSeasons(s, s2, b, new KubrickShowDetailsFrag$FetchShowDetailsAndSeasonsCallback(this, n));
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        Log.v("KubrickShowDetailsFrag", "onCreateView called");
        final View inflate = layoutInflater.inflate(2130903200, (ViewGroup)null, false);
        this.leWrapper = new LoadingAndErrorWrapper(inflate, this.errorCallback);
        this.findViews(inflate);
        this.setupGridViewlayout(inflate);
        this.setupGridView();
        this.initDetailsViewGroup();
        this.spinnerViewGroup = this.createSeasonsSpinnerGroup();
        this.toggleSpinnerVisibility();
        this.showRelatedTitlesIfRequired(bundle);
        this.setupDetailsPageParallaxScrollListener((View)this.spinnerViewGroup);
        this.setupClicks();
        return inflate;
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("EXTRA_SHOW_RELATED_TTLES", !this.isSpinnerShown);
    }
    
    public void reAttachEpisodesSpinner() {
        if (this.spinnerViewGroup != null) {
            final ViewGroup viewGroup = (ViewGroup)this.getActivity().findViewById(16908290);
            if (viewGroup != null) {
                viewGroup.removeView((View)this.spinnerViewGroup);
                viewGroup.addView((View)this.spinnerViewGroup);
                this.isSpinnerShown = true;
            }
        }
    }
    
    public void showCurrentSeason() {
        if (this.gridView == null || this.isSpinnerShown) {
            return;
        }
        this.episodesAdapter = new KubrickShowDetailsFrag$KubrickEpisodesGridAdapter(this, (NetflixActivity)this.getActivity(), this);
        final float y = this.gridView.getChildAt(0).getY();
        this.gridView.setAdapter((ListAdapter)this.episodesAdapter);
        this.episodesAdapter.updateSeasonDetails(this.getCurrentSeasonDetail());
        this.restoreScrollPosition(y);
        ((KubrickVideoDetailsViewGroup)this.detailsViewGroup).hideRelatedTitle();
        this.toggleSpinnerVisibility();
    }
    
    @Override
    public void showDetailViewHeader() {
    }
    
    public void showRelatedTitles() {
        if (this.gridView == null || !this.isSpinnerShown) {
            return;
        }
        final KubrickSimilarsGridAdapter adapter = new KubrickSimilarsGridAdapter(this.getNetflixActivity(), this.gridView);
        adapter.setDetailsHeaderView((ViewGroup)this.detailsViewGroup);
        final TrackableObject trackableObject = new TrackableObject(this.showDetails.getSimilarsRequestId(), this.showDetails.getSimilarsTrackId(), this.showDetails.getSimilarsListPos());
        final float y = this.gridView.getChildAt(0).getY();
        this.gridView.setAdapter((ListAdapter)adapter);
        ((KubrickVideoDetailsViewGroup)this.detailsViewGroup).showRelatedTitle();
        adapter.setData(this.showDetails.getSimilars(), trackableObject);
        this.restoreScrollPosition(y);
        this.toggleSpinnerVisibility();
    }
    
    @Override
    protected void updateShowDetails(final ShowDetails showDetails, final boolean b) {
        super.updateShowDetails(showDetails, b);
        this.heroSlideshow.startHeroSlideshow();
    }
}
