// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import android.app.Notification$Action$Builder;
import android.graphics.drawable.Icon;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import android.app.Notification$Builder;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.INetflixService;
import android.content.Context;
import android.os.Handler;
import android.annotation.TargetApi;

@TargetApi(24)
class DownloadNotificationManagerNPlus extends DownloadNotificationManager
{
    public DownloadNotificationManagerNPlus(final Handler handler, final Context context, final INetflixService netflixService, final ImageLoader imageLoader, final OfflineAgentInterface offlineAgentInterface) {
        super(handler, context, netflixService, imageLoader, offlineAgentInterface);
    }
    
    @Override
    protected void addCancelDownloadAction(final Notification$Builder notification$Builder, final OfflinePlayableViewData offlinePlayableViewData) {
        notification$Builder.addAction(new Notification$Action$Builder(Icon.createWithResource(this.mContext, 2130837708), (CharSequence)this.mContext.getString(2131231150), this.getDeletePlayableIntent(offlinePlayableViewData.getPlayableId())).build());
    }
    
    @Override
    protected void addResumeDownloadAction(final Notification$Builder notification$Builder, final OfflinePlayableViewData offlinePlayableViewData) {
        notification$Builder.addAction(new Notification$Action$Builder(Icon.createWithResource(this.mContext, 2130837743), (CharSequence)this.mContext.getString(2131231157), this.getStartDownloadIntent(offlinePlayableViewData.getPlayableId())).build());
    }
    
    @Override
    protected void addStopDownloadAction(final Notification$Builder notification$Builder, final OfflinePlayableViewData offlinePlayableViewData) {
        notification$Builder.addAction(new Notification$Action$Builder(Icon.createWithResource(this.mContext, 2130837778), (CharSequence)this.mContext.getString(2131231158), this.getStopDownloadIntent(offlinePlayableViewData.getPlayableId())).build());
    }
    
    @Override
    protected void addWatchAction(final Notification$Builder notification$Builder, final OfflinePlayableViewData offlinePlayableViewData) {
        notification$Builder.addAction(new Notification$Action$Builder(Icon.createWithResource(this.mContext, 2130837931), (CharSequence)this.mContext.getString(2131231159), this.getWatchPlayableIntent(offlinePlayableViewData.getPlayableId(), this.mVideoType)).build());
    }
    
    @Override
    protected String getBigTextForInProgress(final OfflinePlayableViewData offlinePlayableViewData) {
        return this.getSecondaryTitlePercentageSizeString(offlinePlayableViewData);
    }
    
    @Override
    protected String getBigTextForStoppedByUser(final OfflinePlayableViewData offlinePlayableViewData) {
        return this.getSecondaryTitlePercentageSizeString(offlinePlayableViewData);
    }
    
    @Override
    protected String getBigTitleForInProgress() {
        return this.mTitle;
    }
    
    @Override
    protected String getBigTitleForStoppedByUser() {
        return this.mTitle;
    }
    
    @Override
    protected int getDownloadCompleteSmallIcon() {
        return 2130837770;
    }
    
    @Override
    protected int getErrorNotificationSmallIcon() {
        return 2130837771;
    }
    
    @Override
    protected void setSmallIconForInProgress(final Notification$Builder notification$Builder) {
        notification$Builder.setSmallIcon(17301633);
    }
    
    @Override
    protected void setSmallIconForStoppedByUser(final Notification$Builder notification$Builder) {
        notification$Builder.setSmallIcon(17301634);
    }
}
