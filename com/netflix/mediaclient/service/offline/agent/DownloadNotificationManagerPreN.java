// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.agent;

import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.offline.OfflinePlayableViewData;
import android.app.Notification$Builder;
import com.netflix.mediaclient.util.gfx.ImageLoader;
import com.netflix.mediaclient.servicemgr.INetflixService;
import android.content.Context;
import android.os.Handler;

class DownloadNotificationManagerPreN extends DownloadNotificationManager
{
    public DownloadNotificationManagerPreN(final Handler handler, final Context context, final INetflixService netflixService, final ImageLoader imageLoader, final boolean b, final OfflineAgentInterface offlineAgentInterface) {
        super(handler, context, netflixService, imageLoader, b, offlineAgentInterface);
    }
    
    @Override
    protected void addCancelDownloadAction(final Notification$Builder notification$Builder, final OfflinePlayableViewData offlinePlayableViewData) {
        notification$Builder.addAction(2130837738, (CharSequence)this.mContext.getString(2131296708), this.getDeletePlayableIntent(offlinePlayableViewData.getPlayableId()));
    }
    
    @Override
    protected void addResumeDownloadAction(final Notification$Builder notification$Builder, final OfflinePlayableViewData offlinePlayableViewData) {
        notification$Builder.addAction(2130837757, (CharSequence)this.mContext.getString(2131296715), this.getStartDownloadIntent(offlinePlayableViewData.getPlayableId()));
    }
    
    @Override
    protected void addStopDownloadAction(final Notification$Builder notification$Builder, final OfflinePlayableViewData offlinePlayableViewData) {
        notification$Builder.addAction(2130837916, (CharSequence)this.mContext.getString(2131296716), this.getStopDownloadIntent(offlinePlayableViewData.getPlayableId()));
    }
    
    @Override
    protected void addWatchAction(final Notification$Builder notification$Builder, final OfflinePlayableViewData offlinePlayableViewData, final VideoType videoType) {
        notification$Builder.addAction(2130838071, (CharSequence)this.mContext.getString(2131296717), this.getWatchPlayableIntent(offlinePlayableViewData.getPlayableId(), videoType));
    }
    
    @Override
    protected String getBigTextForInProgress(final OfflinePlayableViewData offlinePlayableViewData, final DownloadNotificationManager$NotificationData downloadNotificationManager$NotificationData) {
        return this.getSecondaryTitlePercentageSizeString(offlinePlayableViewData, downloadNotificationManager$NotificationData);
    }
    
    @Override
    protected String getBigTextForStoppedByUser(final OfflinePlayableViewData offlinePlayableViewData, final DownloadNotificationManager$NotificationData downloadNotificationManager$NotificationData) {
        return this.getSecondaryTitlePercentageSizeString(offlinePlayableViewData, downloadNotificationManager$NotificationData);
    }
    
    @Override
    protected int getDownloadCompleteSmallIcon() {
        return 2130837945;
    }
    
    @Override
    protected int getErrorNotificationSmallIcon() {
        return 2130837945;
    }
    
    @Override
    protected void setSmallIconForInProgress(final Notification$Builder notification$Builder) {
        notification$Builder.setSmallIcon(2130837945);
    }
    
    @Override
    protected void setSmallIconForStoppedByUser(final Notification$Builder notification$Builder) {
        notification$Builder.setSmallIcon(2130837945);
    }
}
