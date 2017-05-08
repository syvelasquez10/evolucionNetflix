// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.servicemgr.interface_.Playable;
import com.netflix.mediaclient.servicemgr.interface_.PlaybackBookmark;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.ui.lomo.CwView;
import com.netflix.mediaclient.media.BookmarkStore;
import android.content.Context;
import android.text.format.Formatter;
import com.netflix.mediaclient.util.TimeUtils;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.support.v7.widget.RecyclerView$ViewHolder;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;

public class OfflineEpisodesAdapter extends OfflineBaseAdapter
{
    private static final String TAG = "OfflineEpisodesAdapter";
    private RealmVideoDetails[] episodesArray;
    private final String selectedProfileId;
    private final String showPlayableId;
    
    public OfflineEpisodesAdapter(final NetflixActivity netflixActivity, final OfflineAgentInterface offlineAgentInterface, final OfflineBaseAdapter$RowClickListener offlineBaseAdapter$RowClickListener, final String showPlayableId, final String selectedProfileId) {
        super(netflixActivity, offlineAgentInterface, offlineBaseAdapter$RowClickListener);
        this.showPlayableId = showPlayableId;
        this.selectedProfileId = selectedProfileId;
        this.refreshEpisodesAndUIData();
    }
    
    private void refreshEpisodesAndUIData() {
        final OfflineAdapterData showUIData = OfflineFragment.getShowUIData(this.mOfflineAgent.getLatestOfflinePlayableList(), this.showPlayableId, this.selectedProfileId);
        if (showUIData == null) {
            Log.i("OfflineEpisodesAdapter", "All the episodes were removed - closing the activity");
            this.episodesArray = null;
            this.mActivity.finish();
            return;
        }
        final String title = showUIData.getVideoAndProfileData().video.getTitle();
        int n;
        if (BrowseExperience.showKidsExperience()) {
            n = -16777216;
        }
        else {
            n = -1;
        }
        this.setToolbarTitle(title, n);
        this.episodesArray = showUIData.getEpisodes();
    }
    
    @Override
    public boolean containsPlayableId(final int n, final String s) {
        return this.episodesArray.length > n && s.equalsIgnoreCase(this.episodesArray[n].getId());
    }
    
    @Override
    public int getItemCount() {
        if (this.episodesArray == null) {
            return 0;
        }
        return this.episodesArray.length;
    }
    
    @Override
    public String getPlayableId(int n) {
        final RealmVideoDetails realmVideoDetails = this.episodesArray[n];
        if (realmVideoDetails.getType() == VideoType.EPISODE) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            return realmVideoDetails.getId();
        }
        return null;
    }
    
    @Override
    public VideoType getVideoType(final int n) {
        return this.episodesArray[n].getType();
    }
    
    @Override
    public void onBindViewHolder(final RecyclerView$ViewHolder recyclerView$ViewHolder, final int n) {
        final OfflineBaseAdapter$OfflineViewHolderData offlineBaseAdapter$OfflineViewHolderData = (OfflineBaseAdapter$OfflineViewHolderData)recyclerView$ViewHolder;
        final RealmVideoDetails realmVideoDetails = this.episodesArray[n];
        final boolean longClickable = realmVideoDetails.getType() == VideoType.EPISODE;
        ViewUtils.setVisibleOrInvisible((View)offlineBaseAdapter$OfflineViewHolderData.info, longClickable);
        ViewUtils.setVisibleOrInvisible((View)offlineBaseAdapter$OfflineViewHolderData.profileName, !longClickable);
        ViewUtils.setVisibleOrInvisible((View)offlineBaseAdapter$OfflineViewHolderData.title, longClickable);
        ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.showIndicator, false);
        ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.downloadButton, longClickable);
        ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.boxShot, longClickable);
        boolean fullyDownloadedAndWatchable;
        if (longClickable) {
            final OfflinePlayableViewData offlinePlayableViewData = this.mOfflineAgent.getLatestOfflinePlayableList().getOfflinePlayableViewData(realmVideoDetails.getId());
            if (offlinePlayableViewData != null) {
                offlineBaseAdapter$OfflineViewHolderData.info.setText((CharSequence)this.mActivity.getString(2131231166, new Object[] { this.mActivity.getResources().getString(2131231163, new Object[] { TimeUtils.convertSecondsToMinutes(realmVideoDetails.getPlayable().getRuntime()) }), Formatter.formatShortFileSize((Context)this.mActivity, offlinePlayableViewData.getTotalEstimatedSpace()) }));
                if (offlinePlayableViewData.getPercentageDownloaded() < 100) {
                    offlineBaseAdapter$OfflineViewHolderData.downloadButton.setProgress(offlinePlayableViewData.getPercentageDownloaded());
                }
                fullyDownloadedAndWatchable = OfflineUiHelper.isFullyDownloadedAndWatchable(offlinePlayableViewData);
            }
            else {
                if (Log.isLoggable()) {
                    Log.w("OfflineEpisodesAdapter", "Episode view got null data for playableID: " + realmVideoDetails.getId());
                }
                fullyDownloadedAndWatchable = false;
            }
            offlineBaseAdapter$OfflineViewHolderData.itemView.getLayoutParams().height = (int)this.mActivity.getResources().getDimension(2131362275);
            offlineBaseAdapter$OfflineViewHolderData.boxShot.getLayoutParams().width = (int)this.mActivity.getResources().getDimension(2131362274);
            offlineBaseAdapter$OfflineViewHolderData.title.setText((CharSequence)String.format("%d. %s", realmVideoDetails.getPlayable().getEpisodeNumber(), realmVideoDetails.getTitle()));
            offlineBaseAdapter$OfflineViewHolderData.downloadButton.setStateFromPlayable(realmVideoDetails.getPlayable(), this.mActivity);
            PlaybackBookmark bookmark;
            final PlaybackBookmark playbackBookmark = bookmark = null;
            if (this.mActivity.getServiceManager() != null) {
                bookmark = playbackBookmark;
                if (this.mActivity.getServiceManager().getCurrentProfile() != null) {
                    bookmark = BookmarkStore.getInstance().getBookmark(this.mActivity.getServiceManager().getCurrentProfile().getProfileGuid(), realmVideoDetails.getId());
                }
            }
            ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.progress, bookmark != null);
            final Playable playable = realmVideoDetails.getPlayable();
            if (bookmark != null && playable != null) {
                offlineBaseAdapter$OfflineViewHolderData.progress.setProgress(CwView.calculateProgress(realmVideoDetails.getPlayable().getRuntime(), bookmark.mBookmarkInSecond));
            }
            NetflixActivity.getImageLoader((Context)this.mActivity).showImg(offlineBaseAdapter$OfflineViewHolderData.boxShot, realmVideoDetails.getRealmHorzDispUrl((Context)this.mActivity), IClientLogging$AssetType.boxArt, "boxart", ImageLoader$StaticImgConfig.DARK, true);
        }
        else {
            offlineBaseAdapter$OfflineViewHolderData.itemView.getLayoutParams().height = (int)this.mActivity.getResources().getDimension(2131362271);
            offlineBaseAdapter$OfflineViewHolderData.profileName.setText((CharSequence)realmVideoDetails.getTitle());
            ViewUtils.setVisibleOrGone((View)offlineBaseAdapter$OfflineViewHolderData.progress, false);
            fullyDownloadedAndWatchable = false;
        }
        this.applyColorScheme(offlineBaseAdapter$OfflineViewHolderData);
        this.setupRowForSelection(offlineBaseAdapter$OfflineViewHolderData, n, realmVideoDetails.getId(), longClickable);
        String id;
        if (longClickable) {
            id = realmVideoDetails.getId();
        }
        else {
            id = null;
        }
        VideoType episode;
        if (longClickable) {
            episode = VideoType.EPISODE;
        }
        else {
            episode = null;
        }
        this.updateDownloadStatusString(offlineBaseAdapter$OfflineViewHolderData, n, id, episode);
        ViewUtils.setVisibleOrGone(offlineBaseAdapter$OfflineViewHolderData.playIcon, fullyDownloadedAndWatchable);
        offlineBaseAdapter$OfflineViewHolderData.itemView.setLongClickable(longClickable);
    }
    
    @Override
    public void updatePlayableList() {
        this.refreshEpisodesAndUIData();
        super.updatePlayableList();
    }
}
