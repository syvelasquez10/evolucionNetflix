// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData;
import android.util.SparseArray;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import android.widget.ProgressBar;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import android.view.View;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.view.ViewGroup;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.text.format.Formatter;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.ViewUtils;
import android.support.v4.content.ContextCompat;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.support.v7.widget.RecyclerView$ViewHolder;
import android.support.v7.widget.RecyclerView$Adapter;

public abstract class OfflineBaseAdapter extends RecyclerView$Adapter<RecyclerView$ViewHolder>
{
    protected static final int FOOTER_ROW = 1;
    protected static final int STANDARD_ROW = 0;
    private static final String TAG = "OfflineBaseAdapter";
    protected final NetflixActivity mActivity;
    protected OfflineAgentInterface mOfflineAgent;
    protected final OfflineBaseAdapter$RowClickListener mRowClickListener;
    protected OfflineBaseAdapter$SelectionController mSelectionController;
    
    public OfflineBaseAdapter(final NetflixActivity mActivity, final OfflineAgentInterface mOfflineAgent, final OfflineBaseAdapter$RowClickListener mRowClickListener) {
        this.mSelectionController = new OfflineBaseAdapter$SelectionController(this);
        this.mActivity = mActivity;
        this.mRowClickListener = mRowClickListener;
        this.mOfflineAgent = mOfflineAgent;
    }
    
    private void refreshActivityOptionsMenu() {
        if (!AndroidUtils.isActivityFinishedOrDestroyed((Context)this.mActivity)) {
            this.mActivity.invalidateOptionsMenu();
        }
    }
    
    protected void applyColorScheme(final OfflineBaseAdapter$OfflineViewHolderData offlineBaseAdapter$OfflineViewHolderData) {
        int n;
        if (BrowseExperience.showKidsExperience()) {
            n = ContextCompat.getColor((Context)this.mActivity, 2131624058);
        }
        else {
            n = ContextCompat.getColor((Context)this.mActivity, 2131624188);
        }
        int textColor;
        if (BrowseExperience.showKidsExperience()) {
            textColor = ContextCompat.getColor((Context)this.mActivity, 2131624058);
        }
        else {
            textColor = ContextCompat.getColor((Context)this.mActivity, 2131624132);
        }
        offlineBaseAdapter$OfflineViewHolderData.info.setTextColor(textColor);
        offlineBaseAdapter$OfflineViewHolderData.profileName.setTextColor(n);
        offlineBaseAdapter$OfflineViewHolderData.title.setTextColor(n);
        offlineBaseAdapter$OfflineViewHolderData.showIndicator.setImageDrawable(ViewUtils.tintAndGet(offlineBaseAdapter$OfflineViewHolderData.showIndicator.getDrawable(), n));
    }
    
    public abstract boolean containsPlayableId(final int p0, final String p1);
    
    public void deleteSelected() {
        this.mSelectionController.deleteSelected();
        if (this.mSelectionController.getItemsCheckedCount() == this.getItemCount()) {
            Log.i("OfflineBaseAdapter", "All the titles on this screen were removed - finishing the activity");
            this.mActivity.finish();
        }
    }
    
    public String generateDeleteDlgString() {
        return this.mSelectionController.generateDeleteDlgString();
    }
    
    @Override
    public abstract int getItemCount();
    
    @Override
    public int getItemViewType(final int n) {
        return 0;
    }
    
    public int getItemsCheckedCount() {
        return this.mSelectionController.getItemsCheckedCount();
    }
    
    public abstract String getPlayableId(final int p0);
    
    public String getSelectedItemsDiskSpace() {
        return this.getSpaceString(this.mSelectionController.estimateSpaceToFree);
    }
    
    protected String getSpaceString(final long n) {
        return Formatter.formatShortFileSize((Context)this.mActivity, n);
    }
    
    public abstract VideoType getVideoType(final int p0);
    
    public boolean isSelectionMode() {
        return this.mSelectionController.isSelectable();
    }
    
    @Override
    public RecyclerView$ViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        if (n == 1) {
            return new OfflineBaseAdapter$FooterViewHolderData(this, this.mActivity.getLayoutInflater().inflate(2130903227, viewGroup, false));
        }
        return new OfflineBaseAdapter$OfflineViewHolderData(this, this.mActivity.getLayoutInflater().inflate(2130903228, viewGroup, false));
    }
    
    public void setSelectionMode(final boolean selectable) {
        this.mSelectionController.setSelectable(selectable);
        this.notifyDataSetChanged();
    }
    
    protected void setToolbarCancelIcon(final boolean b) {
        final NetflixActionBar netflixActionBar = this.mActivity.getNetflixActionBar();
        if (netflixActionBar != null) {
            if (!b) {
                netflixActionBar.replaceUpButtonWithCancelIcon(false);
                int backgroundResource;
                if (BrowseExperience.showKidsExperience()) {
                    backgroundResource = 2131624188;
                }
                else {
                    backgroundResource = 2131623961;
                }
                netflixActionBar.setBackgroundResource(backgroundResource);
                return;
            }
            netflixActionBar.replaceUpButtonWithCancelIcon(true);
            netflixActionBar.setBackgroundResource(2131624033);
        }
    }
    
    protected void setToolbarSmalltitle(final String subtitle, final int subtitleColor) {
        final NetflixActionBar netflixActionBar = this.mActivity.getNetflixActionBar();
        if (netflixActionBar != null) {
            netflixActionBar.setLogoType(NetflixActionBar$LogoType.GONE);
            netflixActionBar.setTitle(null);
            netflixActionBar.setSubtitleColor(subtitleColor);
            netflixActionBar.setSubtitle(subtitle);
        }
    }
    
    protected void setToolbarTitle(final String title, final int titleColor) {
        final NetflixActionBar netflixActionBar = this.mActivity.getNetflixActionBar();
        if (netflixActionBar != null) {
            netflixActionBar.setLogoType(NetflixActionBar$LogoType.GONE);
            netflixActionBar.setTitle(title);
            netflixActionBar.setSubtitle(null);
            netflixActionBar.setTitleColor(titleColor);
        }
    }
    
    protected void setupRowForSelection(final OfflineBaseAdapter$OfflineViewHolderData offlineBaseAdapter$OfflineViewHolderData, final int n, final String s, final boolean b) {
        final float n2 = 0.8f;
        final boolean b2 = true;
        final boolean b3 = true;
        if (b) {
            final AdvancedImageView boxShot = offlineBaseAdapter$OfflineViewHolderData.boxShot;
            float scaleX;
            if (this.mSelectionController.isItemChecked(n)) {
                scaleX = 0.8f;
            }
            else {
                scaleX = 1.0f;
            }
            boxShot.setScaleX(scaleX);
            final AdvancedImageView boxShot2 = offlineBaseAdapter$OfflineViewHolderData.boxShot;
            float scaleY;
            if (this.mSelectionController.isItemChecked(n)) {
                scaleY = 0.8f;
            }
            else {
                scaleY = 1.0f;
            }
            boxShot2.setScaleY(scaleY);
            final ProgressBar progress = offlineBaseAdapter$OfflineViewHolderData.progress;
            float scaleX2;
            if (this.mSelectionController.isItemChecked(n)) {
                scaleX2 = 0.8f;
            }
            else {
                scaleX2 = 1.0f;
            }
            progress.setScaleX(scaleX2);
            final ProgressBar progress2 = offlineBaseAdapter$OfflineViewHolderData.progress;
            float scaleY2;
            if (this.mSelectionController.isItemChecked(n)) {
                scaleY2 = n2;
            }
            else {
                scaleY2 = 1.0f;
            }
            progress2.setScaleY(scaleY2);
            offlineBaseAdapter$OfflineViewHolderData.checkmark.setChecked(this.mSelectionController.isItemChecked(n));
            offlineBaseAdapter$OfflineViewHolderData.itemView.setLongClickable(true);
            offlineBaseAdapter$OfflineViewHolderData.itemView.setClickable(true);
        }
        else {
            offlineBaseAdapter$OfflineViewHolderData.boxShot.setScaleX(1.0f);
            offlineBaseAdapter$OfflineViewHolderData.boxShot.setScaleY(1.0f);
            offlineBaseAdapter$OfflineViewHolderData.itemView.setLongClickable(false);
            offlineBaseAdapter$OfflineViewHolderData.itemView.setClickable(false);
        }
        ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.checkmark, b && this.mSelectionController.isSelectable());
        if (s != null) {
            final RealmVideoDetails offlineVideoDetails = RealmUtils.getOfflineVideoDetails(s);
            if (offlineVideoDetails != null) {
                if (offlineVideoDetails.getType() == VideoType.SHOW) {
                    ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.showIndicator, !this.mSelectionController.isSelectable() && b3);
                }
                else if (offlineVideoDetails.getType() == VideoType.MOVIE || offlineVideoDetails.getType() == VideoType.EPISODE) {
                    ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.downloadButton, !this.mSelectionController.isSelectable() && b2);
                }
            }
        }
    }
    
    public void toggleChecked(final int n, final String s) {
        this.mSelectionController.toggleChecked(n, s);
    }
    
    protected void updateDownloadStatusString(final OfflineBaseAdapter$OfflineViewHolderData offlineBaseAdapter$OfflineViewHolderData, final int n, final String s, final VideoType videoType) {
        CharSequence coloredStatusString;
        if (videoType == null) {
            coloredStatusString = null;
        }
        else {
            coloredStatusString = this.mOfflineAgent.getLatestOfflinePlayableList().getColoredStatusString((Context)this.mActivity, n, s, videoType);
        }
        final boolean notEmpty = StringUtils.isNotEmpty(coloredStatusString);
        if (notEmpty) {
            offlineBaseAdapter$OfflineViewHolderData.downloadStatus.setText(coloredStatusString);
        }
        ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.downloadStatus, notEmpty);
    }
    
    public void updatePlayableList() {
        this.notifyDataSetChanged();
    }
}
