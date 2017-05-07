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
    private String mHeaderTitle;
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
                remoteViews = new RemoteViews(this.mPackageName, 2130903128);
            }
            else {
                remoteViews = new RemoteViews(this.mPackageName, 2130903131);
            }
        }
        else if (this.mIsEpisode) {
            if (b) {
                remoteViews = new RemoteViews(this.mPackageName, 2130903127);
            }
            else {
                remoteViews = new RemoteViews(this.mPackageName, 2130903126);
            }
        }
        else if (b) {
            remoteViews = new RemoteViews(this.mPackageName, 2130903130);
        }
        else {
            remoteViews = new RemoteViews(this.mPackageName, 2130903129);
        }
        this.updateBoxart(remoteViews);
        this.updateTitles(remoteViews);
        this.updateControl(remoteViews);
        return remoteViews;
    }
    
    private void updateBoxart(final RemoteViews remoteViews) {
        if (this.mBoxart != null) {
            remoteViews.setImageViewBitmap(2131165473, this.mBoxart);
        }
    }
    
    private void updateControl(final RemoteViews remoteViews) {
        if (this.mIsLegacy) {
            return;
        }
        if (!this.mInTransition) {
            remoteViews.setImageViewResource(2131165476, 2130837792);
            remoteViews.setOnClickPendingIntent(2131165476, this.mIntentRetriever.getSkipbackIntent(-30));
            remoteViews.setImageViewResource(2131165478, 2130837798);
            remoteViews.setOnClickPendingIntent(2131165478, this.mIntentRetriever.getStopIntent());
            if (this.mPaused) {
                remoteViews.setImageViewResource(2131165477, 2130837796);
                remoteViews.setOnClickPendingIntent(2131165477, this.mIntentRetriever.getResumeIntent());
                return;
            }
            remoteViews.setImageViewResource(2131165477, 2130837794);
            remoteViews.setOnClickPendingIntent(2131165477, this.mIntentRetriever.getPauseIntent());
        }
        else {
            remoteViews.setImageViewResource(2131165476, 2130837793);
            remoteViews.setOnClickPendingIntent(2131165476, this.mIntentRetriever.getNoActionIntent());
            remoteViews.setImageViewResource(2131165478, 2130837799);
            remoteViews.setOnClickPendingIntent(2131165478, this.mIntentRetriever.getNoActionIntent());
            if (this.mPaused) {
                remoteViews.setImageViewResource(2131165477, 2130837797);
                remoteViews.setOnClickPendingIntent(2131165477, this.mIntentRetriever.getNoActionIntent());
                return;
            }
            remoteViews.setImageViewResource(2131165477, 2130837795);
            remoteViews.setOnClickPendingIntent(2131165477, this.mIntentRetriever.getNoActionIntent());
        }
    }
    
    private void updateTitles(final RemoteViews remoteViews) {
        if (StringUtils.isNotEmpty(this.mMainTitle)) {
            remoteViews.setTextViewText(2131165474, (CharSequence)this.mMainTitle);
        }
        else {
            remoteViews.setTextViewText(2131165474, (CharSequence)"");
        }
        if (StringUtils.isNotEmpty(this.mHeaderTitle)) {
            remoteViews.setTextViewText(2131165480, (CharSequence)this.mHeaderTitle);
        }
        else {
            remoteViews.setTextViewText(2131165480, (CharSequence)"");
        }
        if (this.mIsEpisode) {
            if (!StringUtils.isNotEmpty(this.mSubTitle)) {
                remoteViews.setTextViewText(2131165475, (CharSequence)"");
                return;
            }
            remoteViews.setTextViewText(2131165475, (CharSequence)this.mSubTitle);
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
    
    public void setTitles(final boolean mIsEpisode, final String mMainTitle, final String mSubTitle, final String mHeaderTitle) {
        if (this.mIsEpisode != mIsEpisode) {
            this.mIsEpisode = mIsEpisode;
            this.mNormalRemoteView = this.createRemoteView(false);
            if (this.mExpandedRemoteView != null && !this.mIsLegacy) {
                this.mExpandedRemoteView = this.createRemoteView(true);
            }
        }
        this.mHeaderTitle = mHeaderTitle;
        this.mMainTitle = mMainTitle;
        this.mSubTitle = mSubTitle;
        if (this.mNormalRemoteView != null) {
            this.updateTitles(this.mNormalRemoteView);
        }
        if (this.mExpandedRemoteView != null) {
            this.updateTitles(this.mExpandedRemoteView);
        }
    }
    
    public void showSkipBack(final boolean b) {
        RemoteViews remoteViews;
        if (this.mExpandedRemoteView != null) {
            remoteViews = this.mExpandedRemoteView;
        }
        else {
            remoteViews = this.mNormalRemoteView;
        }
        if (remoteViews == null) {
            return;
        }
        if (b) {
            remoteViews.setViewVisibility(2131165476, 0);
            return;
        }
        remoteViews.setViewVisibility(2131165476, 8);
    }
}
