// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.offline;

import com.netflix.mediaclient.util.StringUtils;
import android.widget.ProgressBar;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.view.View;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.view.ViewGroup;
import android.text.format.Formatter;
import com.netflix.mediaclient.util.ViewUtils;
import android.support.v4.content.ContextCompat;
import android.content.Context;
import com.netflix.mediaclient.util.AndroidUtils;
import android.support.v7.widget.RecyclerView$ViewHolder;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflineAdapterData;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmVideoDetails;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import java.util.List;
import com.netflix.mediaclient.Log;
import java.util.ArrayList;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.offline.realm.RealmUtils;
import android.util.SparseArray;

public class OfflineBaseAdapter$SelectionController
{
    private long estimateSpaceToFree;
    private String initialToolbarTitle;
    private boolean isSelectable;
    private SparseArray<OfflineBaseAdapter$SelectionController$VideoAndProfileId> selectedVideoIds;
    final /* synthetic */ OfflineBaseAdapter this$0;
    
    public OfflineBaseAdapter$SelectionController(final OfflineBaseAdapter this$0) {
        this.this$0 = this$0;
        this.selectedVideoIds = (SparseArray<OfflineBaseAdapter$SelectionController$VideoAndProfileId>)new SparseArray();
        this.isSelectable = false;
    }
    
    private void reset() {
        this.selectedVideoIds.clear();
        this.isSelectable = false;
        this.updateToolbarTitle();
        this.initialToolbarTitle = null;
        this.estimateSpaceToFree = 0L;
    }
    
    private void setItemChecked(final int n, final String s, final boolean b) {
        final boolean b2 = true;
        int n2 = 1;
        long n3;
        if (this.this$0 instanceof OfflineEpisodesAdapter) {
            n3 = this.this$0.mOfflineAgent.getLatestOfflinePlayableList().getOfflinePlayableViewData(s).getCurrentEstimatedSpace();
        }
        else {
            n3 = this.this$0.mOfflineAgent.getLatestOfflinePlayableList().getCurrentSpace(n);
        }
        if (b) {
            if (this.selectedVideoIds.size() != 0) {
                n2 = 0;
            }
            String firstEpisodeProfileId = null;
            if (RealmUtils.getOfflineVideoDetails(s).getType() == VideoType.SHOW) {
                firstEpisodeProfileId = OfflineFragment.getFirstEpisodeProfileId(this.this$0.mOfflineAgent.getLatestOfflinePlayableList(), n);
            }
            this.selectedVideoIds.put(n, (Object)new OfflineBaseAdapter$SelectionController$VideoAndProfileId(this, s, firstEpisodeProfileId));
            this.estimateSpaceToFree += n3;
        }
        else {
            n2 = ((this.selectedVideoIds.size() == 1 && b2) ? 1 : 0);
            this.selectedVideoIds.remove(n);
            this.estimateSpaceToFree -= n3;
        }
        if (n2 != 0) {
            this.this$0.refreshActivityOptionsMenu();
        }
        this.updateToolbarTitle();
        this.this$0.notifyItemChanged(n);
    }
    
    private void updateToolbarTitle() {
        int n = -1;
        if (this.isSelectable) {
            if (this.selectedVideoIds.size() <= 0) {
                this.this$0.setToolbarSmalltitle(this.this$0.mActivity.getResources().getString(2131296722), -1);
                return;
            }
            this.this$0.setToolbarTitle(String.format("%d (%s)", this.selectedVideoIds.size(), this.this$0.getSpaceString(this.estimateSpaceToFree)), -1);
        }
        else if (this.initialToolbarTitle != null) {
            final OfflineBaseAdapter this$0 = this.this$0;
            final String initialToolbarTitle = this.initialToolbarTitle;
            if (BrowseExperience.showKidsExperience()) {
                n = -16777216;
            }
            this$0.setToolbarTitle(initialToolbarTitle, n);
        }
    }
    
    public void deleteSelected() {
        final ArrayList<String> list = new ArrayList<String>();
        final OfflineAgentInterface offlineAgent = this.this$0.mActivity.getServiceManager().getOfflineAgent();
        final int size = this.selectedVideoIds.size();
        for (int i = 0; i < size; ++i) {
            final String videoId = ((OfflineBaseAdapter$SelectionController$VideoAndProfileId)this.selectedVideoIds.valueAt(i)).videoId;
            if (RealmUtils.getOfflineVideoDetails(videoId).getType() == VideoType.SHOW) {
                final RealmVideoDetails[] episodes = OfflineFragment.getShowUIData(this.this$0.mOfflineAgent.getLatestOfflinePlayableList(), videoId, ((OfflineBaseAdapter$SelectionController$VideoAndProfileId)this.selectedVideoIds.valueAt(i)).profileId).getEpisodes();
                for (int length = episodes.length, j = 0; j < length; ++j) {
                    final RealmVideoDetails realmVideoDetails = episodes[j];
                    final VideoType type = realmVideoDetails.getType();
                    Log.i("OfflineBaseAdapter", "details id=%s videoType=%s", realmVideoDetails.getId(), type);
                    if (type == VideoType.EPISODE) {
                        list.add(realmVideoDetails.getPlayable().getPlayableId());
                    }
                }
            }
            else {
                list.add(videoId);
            }
        }
        if (list.size() > 0) {
            offlineAgent.deleteOfflinePlayables(list);
            DownloadButton.removePlayablesFromPreQueued(list);
        }
        this.selectedVideoIds.clear();
        this.updateToolbarTitle();
        if (size > 0) {
            this.this$0.refreshActivityOptionsMenu();
        }
    }
    
    public String generateDeleteDlgString() {
        int i = 0;
        int n = 0;
        int n2 = 0;
        while (i < this.selectedVideoIds.size()) {
            final int key = this.selectedVideoIds.keyAt(i);
            switch (OfflineBaseAdapter$1.$SwitchMap$com$netflix$mediaclient$servicemgr$interface_$VideoType[this.this$0.getVideoType(key).ordinal()]) {
                case 1: {
                    ++n;
                    break;
                }
                case 2: {
                    ++n2;
                    break;
                }
                case 3: {
                    n += this.this$0.mOfflineAgent.getLatestOfflinePlayableList().get(key).getVideoAndProfileData().numEpisodes;
                    break;
                }
            }
            ++i;
        }
        String quantityString;
        if (n2 > 0) {
            quantityString = this.this$0.mActivity.getResources().getQuantityString(2131361797, n2, new Object[] { n2 });
        }
        else {
            quantityString = null;
        }
        String quantityString2;
        if (n > 0) {
            quantityString2 = this.this$0.mActivity.getResources().getQuantityString(2131361795, n, new Object[] { n });
        }
        else {
            quantityString2 = null;
        }
        if (quantityString != null && quantityString2 != null) {
            return this.this$0.mActivity.getString(2131296502, new Object[] { quantityString, quantityString2, this.this$0.getSpaceString(this.estimateSpaceToFree) });
        }
        final NetflixActivity mActivity = this.this$0.mActivity;
        if (quantityString == null) {
            quantityString = quantityString2;
        }
        return mActivity.getString(2131296501, new Object[] { quantityString, this.this$0.getSpaceString(this.estimateSpaceToFree) });
    }
    
    public int getItemsCheckedCount() {
        return this.selectedVideoIds.size();
    }
    
    public boolean isItemChecked(final int n) {
        return this.selectedVideoIds.get(n) != null;
    }
    
    public boolean isSelectable() {
        return this.isSelectable;
    }
    
    public void setSelectable(final boolean b) {
        this.isSelectable = b;
        if (b) {
            if (this.initialToolbarTitle == null) {
                this.initialToolbarTitle = this.this$0.mActivity.getNetflixActionBar().getToolbar().getTitle().toString();
            }
            this.updateToolbarTitle();
        }
        else {
            this.reset();
        }
        this.this$0.setToolbarCancelIcon(b);
    }
    
    public void toggleChecked(final int n, final String s) {
        this.setItemChecked(n, s, this.selectedVideoIds.get(n) == null);
    }
}
