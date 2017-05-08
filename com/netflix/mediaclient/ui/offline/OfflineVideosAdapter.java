// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.servicemgr.interface_.PlaybackBookmark;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import android.widget.TextView;
import android.text.format.Formatter;
import com.netflix.mediaclient.ui.lomo.CwView;
import com.netflix.mediaclient.media.BookmarkStore;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.View$OnClickListener;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView$ViewHolder;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData$ViewType;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmProfile;
import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup$MarginLayoutParams;
import android.text.TextUtils;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData$VideoAndProfileData;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class OfflineVideosAdapter extends OfflineBaseAdapter
{
    private boolean mSkipAdultContent;
    
    public OfflineVideosAdapter(final NetflixActivity netflixActivity, final OfflineAgentInterface offlineAgentInterface, final OfflineBaseAdapter$RowClickListener offlineBaseAdapter$RowClickListener) {
        super(netflixActivity, offlineAgentInterface, offlineBaseAdapter$RowClickListener);
        final String string = this.mActivity.getResources().getString(2131296634);
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
    
    private void displayHeaderIfNecessary(final OfflineBaseAdapter$OfflineViewHolderData offlineBaseAdapter$OfflineViewHolderData, final OfflineAdapterData$VideoAndProfileData offlineAdapterData$VideoAndProfileData, final OfflineAdapterData$VideoAndProfileData offlineAdapterData$VideoAndProfileData2) {
        if (offlineAdapterData$VideoAndProfileData != null && TextUtils.equals((CharSequence)offlineAdapterData$VideoAndProfileData2.video.getProfileId(), (CharSequence)offlineAdapterData$VideoAndProfileData.video.getProfileId())) {
            ((ViewGroup$MarginLayoutParams)offlineBaseAdapter$OfflineViewHolderData.itemContent.getLayoutParams()).topMargin = 0;
            offlineBaseAdapter$OfflineViewHolderData.itemHeader.setVisibility(8);
            offlineBaseAdapter$OfflineViewHolderData.profileName.setText((CharSequence)null);
            offlineBaseAdapter$OfflineViewHolderData.profileAvatar.setImageDrawable(null);
            return;
        }
        ((ViewGroup$MarginLayoutParams)offlineBaseAdapter$OfflineViewHolderData.itemContent.getLayoutParams()).topMargin = offlineBaseAdapter$OfflineViewHolderData.itemContent.getResources().getDimensionPixelOffset(2131427836) + offlineBaseAdapter$OfflineViewHolderData.itemContent.getResources().getDimensionPixelOffset(2131427700) * 2;
        offlineBaseAdapter$OfflineViewHolderData.itemHeader.setVisibility(0);
        final RealmProfile profile = RealmUtils.getProfile(offlineAdapterData$VideoAndProfileData2.video.getProfileId());
        if (profile != null) {
            offlineBaseAdapter$OfflineViewHolderData.profileName.setText((CharSequence)profile.getName());
            NetflixActivity.getImageLoader((Context)this.mActivity).showImg(offlineBaseAdapter$OfflineViewHolderData.profileAvatar, profile.getRealmProfileIconUrl(offlineBaseAdapter$OfflineViewHolderData.profileAvatar.getContext()), IClientLogging$AssetType.boxArt, "icon", ImageLoader$StaticImgConfig.DARK, true);
            return;
        }
        LogUtils.reportErrorSafely("profile not found for " + offlineAdapterData$VideoAndProfileData2.video.getProfileId());
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
                text = 2131296788;
            }
            else {
                text = 2131296789;
            }
            allProfilesButton.setText(text);
            if (BrowseExperience.showKidsExperience()) {
                offlineBaseAdapter$FooterViewHolderData.allProfilesButton.setTextColor(ContextCompat.getColor((Context)this.mActivity, 2131689595));
            }
            offlineBaseAdapter$FooterViewHolderData.allProfilesButton.setOnClickListener((View$OnClickListener)new OfflineVideosAdapter$1(this));
            return;
        }
        final OfflineBaseAdapter$OfflineViewHolderData offlineBaseAdapter$OfflineViewHolderData = (OfflineBaseAdapter$OfflineViewHolderData)recyclerView$ViewHolder;
        OfflineAdapterData$VideoAndProfileData videoAndProfileData;
        if (text == 0) {
            videoAndProfileData = null;
        }
        else {
            videoAndProfileData = this.mOfflineAgent.getLatestOfflinePlayableList().get(text - 1).getVideoAndProfileData();
        }
        final OfflineAdapterData$VideoAndProfileData videoAndProfileData2 = this.mOfflineAgent.getLatestOfflinePlayableList().get(text).getVideoAndProfileData();
        ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.showIndicator, videoAndProfileData2.type == OfflineAdapterData$ViewType.SHOW);
        ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.downloadButton, videoAndProfileData2.type == OfflineAdapterData$ViewType.MOVIE);
        final Playable playable = videoAndProfileData2.video.getPlayable();
        PlaybackBookmark bookmark;
        if (this.mActivity.getServiceManager() != null && this.mActivity.getServiceManager().getCurrentProfile() != null) {
            bookmark = BookmarkStore.getInstance().getBookmark(this.mActivity.getServiceManager().getCurrentProfile().getProfileGuid(), videoAndProfileData2.video.getId());
        }
        else {
            bookmark = null;
        }
        if (bookmark != null && playable != null) {
            ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.progress, true);
            offlineBaseAdapter$OfflineViewHolderData.progress.setProgress(CwView.calculateProgress(videoAndProfileData2.video.getPlayable().getRuntime(), bookmark.mBookmarkInSecond));
        }
        else {
            ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.progress, false);
        }
        offlineBaseAdapter$OfflineViewHolderData.title.setText((CharSequence)videoAndProfileData2.video.getTitle());
        NetflixActivity.getImageLoader((Context)this.mActivity).showImg(offlineBaseAdapter$OfflineViewHolderData.boxShot, videoAndProfileData2.video.getRealmHorzDispUrl((Context)this.mActivity), IClientLogging$AssetType.boxArt, "icon", ImageLoader$StaticImgConfig.DARK, true);
        this.applyColorScheme(offlineBaseAdapter$OfflineViewHolderData);
        this.setupRowForSelection(offlineBaseAdapter$OfflineViewHolderData, text, videoAndProfileData2.video.getId(), true);
        boolean fullyDownloadedAndWatchable;
        if (videoAndProfileData2.type == OfflineAdapterData$ViewType.SHOW) {
            offlineBaseAdapter$OfflineViewHolderData.info.setText((CharSequence)this.mActivity.getString(2131296723, new Object[] { this.mActivity.getResources().getQuantityString(2131361796, videoAndProfileData2.numEpisodes, new Object[] { videoAndProfileData2.numEpisodes }), this.getSpaceString(this.mOfflineAgent.getLatestOfflinePlayableList().getCurrentSpace(text)) }));
            fullyDownloadedAndWatchable = b;
        }
        else {
            fullyDownloadedAndWatchable = b;
            if (videoAndProfileData2.type == OfflineAdapterData$ViewType.MOVIE) {
                final String certification = videoAndProfileData2.video.getCertification();
                String text2;
                if (TextUtils.isEmpty((CharSequence)certification)) {
                    text2 = Formatter.formatShortFileSize((Context)this.mActivity, this.mOfflineAgent.getLatestOfflinePlayableList().getCurrentSpace(text));
                }
                else {
                    text2 = this.mActivity.getString(2131296723, new Object[] { certification, Formatter.formatShortFileSize((Context)this.mActivity, this.mOfflineAgent.getLatestOfflinePlayableList().getCurrentSpace(text)) });
                }
                offlineBaseAdapter$OfflineViewHolderData.info.setText((CharSequence)text2);
                offlineBaseAdapter$OfflineViewHolderData.downloadButton.setStateFromPlayable(videoAndProfileData2.video.getPlayable(), this.mActivity);
                if (this.mOfflineAgent.getLatestOfflinePlayableList().getPercentage(text) < 100) {
                    offlineBaseAdapter$OfflineViewHolderData.downloadButton.setProgress(this.mOfflineAgent.getLatestOfflinePlayableList().getPercentage(text));
                }
                fullyDownloadedAndWatchable = OfflineUiHelper.isFullyDownloadedAndWatchable(this.mOfflineAgent.getLatestOfflinePlayableList().getOfflinePlayableViewData(videoAndProfileData2.video.getId()));
            }
        }
        String id = s;
        if (this.getVideoType(text) != null) {
            id = videoAndProfileData2.video.getId();
        }
        this.updateDownloadStatusString(offlineBaseAdapter$OfflineViewHolderData, text, id, this.getVideoType(text));
        ViewUtils.setVisibleOrGone(offlineBaseAdapter$OfflineViewHolderData.playIcon, fullyDownloadedAndWatchable);
        this.displayHeaderIfNecessary(offlineBaseAdapter$OfflineViewHolderData, videoAndProfileData, videoAndProfileData2);
    }
}
