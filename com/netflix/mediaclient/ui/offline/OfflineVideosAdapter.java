// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData$VideoAndProfileData;
import android.widget.TextView;
import android.text.format.Formatter;
import android.text.TextUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View$OnClickListener;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView$ViewHolder;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData$ViewType;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class OfflineVideosAdapter extends OfflineBaseAdapter
{
    private boolean mSkipAdultContent;
    
    public OfflineVideosAdapter(final NetflixActivity netflixActivity, final OfflineAgentInterface offlineAgentInterface, final OfflineBaseAdapter$RowClickListener offlineBaseAdapter$RowClickListener) {
        super(netflixActivity, offlineAgentInterface, offlineBaseAdapter$RowClickListener);
        final String string = this.mActivity.getResources().getString(2131231078);
        int n;
        if (BrowseExperience.showKidsExperience()) {
            n = -16777216;
        }
        else {
            n = -1;
        }
        this.setToolbarTitle(string, n);
        this.mSkipAdultContent = this.showAllProfilesButton();
        this.refreshUIData();
    }
    
    private void refreshUIData() {
        if (this.mOfflineAgent.setSkipAdultContent(this.mSkipAdultContent)) {
            this.mOfflineAgent.refreshUIData();
            this.notifyDataSetChanged();
        }
    }
    
    private boolean showAllProfilesButton() {
        return this.mActivity.getServiceManager().getCurrentProfile() != null && this.mActivity.getServiceManager().getCurrentProfile().isKidsProfile();
    }
    
    @Override
    public boolean containsPlayableId(final int n, final String s) {
        return this.mOfflineAgent.getLatestOfflinePlayableList().size() > n && this.mOfflineAgent.getLatestOfflinePlayableList().get(n).containsPlayable(s);
    }
    
    @Override
    public int getItemCount() {
        if (this.mOfflineAgent.getLatestOfflinePlayableList() == null) {
            return 0;
        }
        if (this.showAllProfilesButton()) {
            return this.mOfflineAgent.getLatestOfflinePlayableList().size() + 1;
        }
        return this.mOfflineAgent.getLatestOfflinePlayableList().size();
    }
    
    @Override
    public int getItemViewType(final int n) {
        if (this.showAllProfilesButton() && (this.mOfflineAgent.getLatestOfflinePlayableList() == null || n == this.mOfflineAgent.getLatestOfflinePlayableList().size())) {
            return 1;
        }
        return 0;
    }
    
    @Override
    public String getPlayableId(final int n) {
        if (n < this.mOfflineAgent.getLatestOfflinePlayableList().size()) {
            final OfflineAdapterData offlineAdapterData = this.mOfflineAgent.getLatestOfflinePlayableList().get(n);
            if (offlineAdapterData.getVideoAndProfileData().type == OfflineAdapterData$ViewType.MOVIE || offlineAdapterData.getVideoAndProfileData().type == OfflineAdapterData$ViewType.SHOW) {
                return offlineAdapterData.getVideoAndProfileData().video.getId();
            }
        }
        return null;
    }
    
    @Override
    public VideoType getVideoType(final int n) {
        switch (OfflineVideosAdapter$2.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$offline$OfflineAdapterData$ViewType[this.mOfflineAgent.getLatestOfflinePlayableList().get(n).getVideoAndProfileData().type.ordinal()]) {
            default: {
                return null;
            }
            case 1: {
                return VideoType.MOVIE;
            }
            case 2: {
                return VideoType.SHOW;
            }
        }
    }
    
    public boolean isShowingAdultContent() {
        return !this.mSkipAdultContent;
    }
    
    @Override
    public void onBindViewHolder(final RecyclerView$ViewHolder recyclerView$ViewHolder, int text) {
        final String s = null;
        final boolean b = false;
        if (recyclerView$ViewHolder instanceof OfflineBaseAdapter$FooterViewHolderData) {
            final OfflineBaseAdapter$FooterViewHolderData offlineBaseAdapter$FooterViewHolderData = (OfflineBaseAdapter$FooterViewHolderData)recyclerView$ViewHolder;
            if (this.mSkipAdultContent) {
                text = 2130837730;
            }
            else {
                text = 2130837732;
            }
            offlineBaseAdapter$FooterViewHolderData.allProfilesButton.setCompoundDrawablesWithIntrinsicBounds(0, 0, text, 0);
            final TextView allProfilesButton = offlineBaseAdapter$FooterViewHolderData.allProfilesButton;
            if (this.mSkipAdultContent) {
                text = 2131231235;
            }
            else {
                text = 2131231236;
            }
            allProfilesButton.setText(text);
            if (BrowseExperience.showKidsExperience()) {
                offlineBaseAdapter$FooterViewHolderData.allProfilesButton.setTextColor(ContextCompat.getColor((Context)this.mActivity, 2131624058));
            }
            offlineBaseAdapter$FooterViewHolderData.allProfilesButton.setOnClickListener((View$OnClickListener)new OfflineVideosAdapter$1(this));
            return;
        }
        final OfflineBaseAdapter$OfflineViewHolderData offlineBaseAdapter$OfflineViewHolderData = (OfflineBaseAdapter$OfflineViewHolderData)recyclerView$ViewHolder;
        final OfflineAdapterData$VideoAndProfileData videoAndProfileData = this.mOfflineAgent.getLatestOfflinePlayableList().get(text).getVideoAndProfileData();
        ViewUtils.setVisibleOrInvisible((View)offlineBaseAdapter$OfflineViewHolderData.info, videoAndProfileData.type != OfflineAdapterData$ViewType.PROFILE);
        ViewUtils.setVisibleOrInvisible((View)offlineBaseAdapter$OfflineViewHolderData.profileName, videoAndProfileData.type == OfflineAdapterData$ViewType.PROFILE);
        ViewUtils.setVisibleOrInvisible((View)offlineBaseAdapter$OfflineViewHolderData.title, videoAndProfileData.type != OfflineAdapterData$ViewType.PROFILE);
        ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.showIndicator, videoAndProfileData.type == OfflineAdapterData$ViewType.SHOW);
        ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.downloadButton, videoAndProfileData.type == OfflineAdapterData$ViewType.MOVIE);
        ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.progress, false);
        if (videoAndProfileData.type != OfflineAdapterData$ViewType.PROFILE) {
            offlineBaseAdapter$OfflineViewHolderData.itemView.getLayoutParams().height = (int)this.mActivity.getResources().getDimension(2131362275);
            offlineBaseAdapter$OfflineViewHolderData.boxShot.getLayoutParams().width = (int)this.mActivity.getResources().getDimension(2131362274);
            offlineBaseAdapter$OfflineViewHolderData.title.setText((CharSequence)videoAndProfileData.video.getTitle());
            NetflixActivity.getImageLoader((Context)this.mActivity).showImg(offlineBaseAdapter$OfflineViewHolderData.boxShot, videoAndProfileData.video.getRealmHorzDispUrl((Context)this.mActivity), IClientLogging$AssetType.boxArt, "icon", ImageLoader$StaticImgConfig.DARK, true);
        }
        else {
            offlineBaseAdapter$OfflineViewHolderData.itemView.getLayoutParams().height = (int)this.mActivity.getResources().getDimension(2131362271);
            offlineBaseAdapter$OfflineViewHolderData.boxShot.getLayoutParams().width = (int)this.mActivity.getResources().getDimension(2131362270);
            offlineBaseAdapter$OfflineViewHolderData.profileName.setText((CharSequence)videoAndProfileData.profile.getName());
            NetflixActivity.getImageLoader((Context)this.mActivity).showImg(offlineBaseAdapter$OfflineViewHolderData.boxShot, videoAndProfileData.profile.getRealmProfileIconUrl((Context)this.mActivity), IClientLogging$AssetType.boxArt, "icon", ImageLoader$StaticImgConfig.DARK, true);
        }
        this.applyColorScheme(offlineBaseAdapter$OfflineViewHolderData);
        String id;
        if (videoAndProfileData.video == null) {
            id = null;
        }
        else {
            id = videoAndProfileData.video.getId();
        }
        this.setupRowForSelection(offlineBaseAdapter$OfflineViewHolderData, text, id, videoAndProfileData.type != OfflineAdapterData$ViewType.PROFILE);
        boolean fullyDownloadedAndWatchable;
        if (videoAndProfileData.type == OfflineAdapterData$ViewType.SHOW) {
            offlineBaseAdapter$OfflineViewHolderData.info.setText((CharSequence)this.mActivity.getString(2131231166, new Object[] { this.mActivity.getResources().getQuantityString(2131296260, videoAndProfileData.numEpisodes, new Object[] { videoAndProfileData.numEpisodes }), this.getSpaceString(this.mOfflineAgent.getLatestOfflinePlayableList().getCurrentSpace(text)) }));
            fullyDownloadedAndWatchable = b;
        }
        else {
            fullyDownloadedAndWatchable = b;
            if (videoAndProfileData.type == OfflineAdapterData$ViewType.MOVIE) {
                final String certification = videoAndProfileData.video.getCertification();
                String text2;
                if (TextUtils.isEmpty((CharSequence)certification)) {
                    text2 = Formatter.formatShortFileSize((Context)this.mActivity, this.mOfflineAgent.getLatestOfflinePlayableList().getCurrentSpace(text));
                }
                else {
                    text2 = this.mActivity.getString(2131231166, new Object[] { certification, Formatter.formatShortFileSize((Context)this.mActivity, this.mOfflineAgent.getLatestOfflinePlayableList().getCurrentSpace(text)) });
                }
                offlineBaseAdapter$OfflineViewHolderData.info.setText((CharSequence)text2);
                offlineBaseAdapter$OfflineViewHolderData.downloadButton.setStateFromPlayable(videoAndProfileData.video.getPlayable(), this.mActivity);
                if (this.mOfflineAgent.getLatestOfflinePlayableList().getPercentage(text) < 100) {
                    offlineBaseAdapter$OfflineViewHolderData.downloadButton.setProgress(this.mOfflineAgent.getLatestOfflinePlayableList().getPercentage(text));
                }
                fullyDownloadedAndWatchable = OfflineUiHelper.isFullyDownloadedAndWatchable(this.mOfflineAgent.getLatestOfflinePlayableList().getOfflinePlayableViewData(videoAndProfileData.video.getId()));
            }
        }
        String id2 = s;
        if (this.getVideoType(text) != null) {
            id2 = videoAndProfileData.video.getId();
        }
        this.updateDownloadStatusString(offlineBaseAdapter$OfflineViewHolderData, text, id2, this.getVideoType(text));
        ViewUtils.setVisibleOrGone(offlineBaseAdapter$OfflineViewHolderData.playIcon, fullyDownloadedAndWatchable);
    }
}
