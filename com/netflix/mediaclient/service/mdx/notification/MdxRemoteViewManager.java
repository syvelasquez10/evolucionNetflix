// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.notification;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.widget.RemoteViews;
import android.graphics.Bitmap;

public final class MdxRemoteViewManager
{
    private static final String TAG = "nf_mdxnotification";
    private Bitmap mBoxart;
    RemoteViews mExpandedRemoteView;
    private boolean mInTransition;
    private final MdxNotificationManager.MdxNotificationIntentRetriever mIntentRetriever;
    private boolean mIsEpisode;
    private final boolean mIsLegacy;
    private String mMainTitle;
    RemoteViews mNormalRemoteView;
    private final String mPackageName;
    private boolean mPaused;
    private String mSubTitle;
    
    public MdxRemoteViewManager(final String mPackageName, final boolean mIsEpisode, final boolean mIsLegacy, final MdxNotificationManager.MdxNotificationIntentRetriever mIntentRetriever) {
        this.mPackageName = mPackageName;
        this.mIsLegacy = mIsLegacy;
        this.mIsEpisode = mIsEpisode;
        this.mIntentRetriever = mIntentRetriever;
    }
    
    private RemoteViews createRemoteView(final boolean b) {
        RemoteViews remoteViews;
        if (this.mIsLegacy) {
            if (this.mIsEpisode) {
                remoteViews = new RemoteViews(this.mPackageName, 2130903113);
            }
            else {
                remoteViews = new RemoteViews(this.mPackageName, 2130903116);
            }
        }
        else if (this.mIsEpisode) {
            if (b) {
                remoteViews = new RemoteViews(this.mPackageName, 2130903112);
            }
            else {
                remoteViews = new RemoteViews(this.mPackageName, 2130903111);
            }
        }
        else if (b) {
            remoteViews = new RemoteViews(this.mPackageName, 2130903115);
        }
        else {
            remoteViews = new RemoteViews(this.mPackageName, 2130903114);
        }
        this.updateBoxart(remoteViews);
        this.updateTitles(remoteViews);
        this.updateControl(remoteViews);
        return remoteViews;
    }
    
    private void updateBoxart(final RemoteViews remoteViews) {
        if (this.mBoxart != null) {
            remoteViews.setImageViewBitmap(2131230957, this.mBoxart);
        }
    }
    
    private void updateControl(final RemoteViews remoteViews) {
        if (this.mIsLegacy) {
            return;
        }
        if (!this.mInTransition) {
            remoteViews.setImageViewResource(2131230960, 2130837767);
            remoteViews.setOnClickPendingIntent(2131230960, this.mIntentRetriever.getSkipbackIntent(-30));
            remoteViews.setImageViewResource(2131230962, 2130837773);
            remoteViews.setOnClickPendingIntent(2131230962, this.mIntentRetriever.getStopIntent());
            if (this.mPaused) {
                remoteViews.setImageViewResource(2131230961, 2130837771);
                remoteViews.setOnClickPendingIntent(2131230961, this.mIntentRetriever.getResumeIntent());
                return;
            }
            remoteViews.setImageViewResource(2131230961, 2130837769);
            remoteViews.setOnClickPendingIntent(2131230961, this.mIntentRetriever.getPauseIntent());
        }
        else {
            remoteViews.setImageViewResource(2131230960, 2130837768);
            remoteViews.setOnClickPendingIntent(2131230960, this.mIntentRetriever.getNoActionIntent());
            remoteViews.setImageViewResource(2131230962, 2130837774);
            remoteViews.setOnClickPendingIntent(2131230962, this.mIntentRetriever.getNoActionIntent());
            if (this.mPaused) {
                remoteViews.setImageViewResource(2131230961, 2130837772);
                remoteViews.setOnClickPendingIntent(2131230961, this.mIntentRetriever.getNoActionIntent());
                return;
            }
            remoteViews.setImageViewResource(2131230961, 2130837770);
            remoteViews.setOnClickPendingIntent(2131230961, this.mIntentRetriever.getNoActionIntent());
        }
    }
    
    private void updateTitles(final RemoteViews remoteViews) {
        if (StringUtils.isNotEmpty(this.mMainTitle)) {
            remoteViews.setTextViewText(2131230958, (CharSequence)this.mMainTitle);
        }
        else {
            remoteViews.setTextViewText(2131230958, (CharSequence)"");
        }
        if (this.mIsEpisode) {
            if (!StringUtils.isNotEmpty(this.mSubTitle)) {
                remoteViews.setTextViewText(2131230959, (CharSequence)"");
                return;
            }
            remoteViews.setTextViewText(2131230959, (CharSequence)this.mSubTitle);
        }
    }
    
    public RemoteViews getRemoteView() {
        if (this.mNormalRemoteView == null) {
            this.mNormalRemoteView = this.createRemoteView(false);
        }
        return this.mNormalRemoteView;
    }
    
    public RemoteViews getRemoteViewBigContetnt() {
        if (this.mExpandedRemoteView == null && !this.mIsLegacy) {
            this.mExpandedRemoteView = this.createRemoteView(true);
        }
        return this.mExpandedRemoteView;
    }
    
    public void setBoxart(final Bitmap mBoxart) {
        Log.d("nf_mdxnotification", "MdxRemoteViewManager:setBoxart");
        this.mBoxart = mBoxart;
        if (this.mNormalRemoteView != null) {
            this.updateBoxart(this.mNormalRemoteView);
        }
        if (this.mExpandedRemoteView != null) {
            this.updateBoxart(this.mExpandedRemoteView);
        }
    }
    
    public void setPauseState(final boolean mPaused, final boolean mInTransition) {
        this.mPaused = mPaused;
        this.mInTransition = mInTransition;
        if (this.mNormalRemoteView != null) {
            this.updateControl(this.mNormalRemoteView);
        }
        if (this.mExpandedRemoteView != null) {
            this.updateControl(this.mExpandedRemoteView);
        }
    }
    
    public void setTitles(final boolean mIsEpisode, final String mMainTitle, final String mSubTitle) {
        if (this.mIsEpisode != mIsEpisode) {
            this.mIsEpisode = mIsEpisode;
            this.mNormalRemoteView = this.createRemoteView(false);
            if (this.mExpandedRemoteView != null && !this.mIsLegacy) {
                this.mExpandedRemoteView = this.createRemoteView(true);
            }
        }
        this.mMainTitle = mMainTitle;
        this.mSubTitle = mSubTitle;
        if (this.mNormalRemoteView != null) {
            this.updateTitles(this.mNormalRemoteView);
        }
        if (this.mExpandedRemoteView != null) {
            this.updateTitles(this.mExpandedRemoteView);
        }
    }
}
