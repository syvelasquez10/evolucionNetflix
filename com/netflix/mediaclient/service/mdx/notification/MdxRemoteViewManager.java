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
    private boolean mIsInPostPlay;
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
                remoteViews = new RemoteViews(this.mPackageName, 2130903127);
            }
            else {
                remoteViews = new RemoteViews(this.mPackageName, 2130903130);
            }
        }
        else if (this.mIsEpisode) {
            if (b) {
                remoteViews = new RemoteViews(this.mPackageName, 2130903126);
            }
            else {
                remoteViews = new RemoteViews(this.mPackageName, 2130903125);
            }
        }
        else if (b) {
            remoteViews = new RemoteViews(this.mPackageName, 2130903129);
        }
        else {
            remoteViews = new RemoteViews(this.mPackageName, 2130903128);
        }
        this.updateBoxart(remoteViews);
        this.updateTitles(remoteViews);
        this.updateControl(remoteViews);
        return remoteViews;
    }
    
    private void setPauseActive(final RemoteViews remoteViews) {
        remoteViews.setImageViewResource(2131165483, 2130837798);
        remoteViews.setOnClickPendingIntent(2131165483, this.mIntentRetriever.getPauseIntent());
    }
    
    private void setPauseInactive(final RemoteViews remoteViews) {
        remoteViews.setImageViewResource(2131165483, 2130837799);
        remoteViews.setOnClickPendingIntent(2131165483, this.mIntentRetriever.getNoActionIntent());
    }
    
    private void setPlayActiveWithGetNext(final RemoteViews remoteViews) {
        remoteViews.setImageViewResource(2131165483, 2130837800);
        remoteViews.setOnClickPendingIntent(2131165483, this.mIntentRetriever.getPlayNextIntent());
    }
    
    private void setPlayActiveWithResume(final RemoteViews remoteViews) {
        remoteViews.setImageViewResource(2131165483, 2130837800);
        remoteViews.setOnClickPendingIntent(2131165483, this.mIntentRetriever.getResumeIntent());
    }
    
    private void setPlayInactive(final RemoteViews remoteViews) {
        remoteViews.setImageViewResource(2131165483, 2130837801);
        remoteViews.setOnClickPendingIntent(2131165483, this.mIntentRetriever.getNoActionIntent());
    }
    
    private void setSkipActive(final RemoteViews remoteViews) {
        remoteViews.setImageViewResource(2131165482, 2130837796);
        remoteViews.setOnClickPendingIntent(2131165482, this.mIntentRetriever.getSkipbackIntent(-30));
    }
    
    private void setSkipInactive(final RemoteViews remoteViews) {
        remoteViews.setImageViewResource(2131165482, 2130837797);
        remoteViews.setOnClickPendingIntent(2131165482, this.mIntentRetriever.getNoActionIntent());
    }
    
    private void setStopActive(final RemoteViews remoteViews) {
        remoteViews.setImageViewResource(2131165484, 2130837802);
        remoteViews.setOnClickPendingIntent(2131165484, this.mIntentRetriever.getStopIntent());
    }
    
    private void setStopInactive(final RemoteViews remoteViews) {
        remoteViews.setImageViewResource(2131165484, 2130837803);
        remoteViews.setOnClickPendingIntent(2131165484, this.mIntentRetriever.getNoActionIntent());
    }
    
    private void updateBoxart(final RemoteViews remoteViews) {
        if (this.mBoxart != null) {
            remoteViews.setImageViewBitmap(2131165479, this.mBoxart);
        }
    }
    
    private void updateControl(final RemoteViews remoteViews) {
        if (this.mIsLegacy) {
            return;
        }
        if (!this.mInTransition) {
            this.setSkipActive(remoteViews);
            this.setStopActive(remoteViews);
            if (this.mIsInPostPlay) {
                this.setPlayActiveWithGetNext(remoteViews);
                this.showSkipBack(false);
                return;
            }
            if (this.mPaused) {
                this.setPlayActiveWithResume(remoteViews);
                this.showSkipBack(true);
                return;
            }
            this.setPauseActive(remoteViews);
            this.showSkipBack(true);
        }
        else {
            this.setSkipInactive(remoteViews);
            this.setStopInactive(remoteViews);
            if (this.mIsInPostPlay) {
                this.setPlayInactive(remoteViews);
                this.showSkipBack(false);
            }
            if (this.mPaused) {
                this.setPlayInactive(remoteViews);
                this.showSkipBack(true);
                return;
            }
            this.setPauseInactive(remoteViews);
            this.showSkipBack(true);
        }
    }
    
    private void updateTitles(final RemoteViews remoteViews) {
        if (StringUtils.isNotEmpty(this.mMainTitle)) {
            remoteViews.setTextViewText(2131165480, (CharSequence)this.mMainTitle);
        }
        else {
            remoteViews.setTextViewText(2131165480, (CharSequence)"");
        }
        if (StringUtils.isNotEmpty(this.mHeaderTitle)) {
            remoteViews.setTextViewText(2131165486, (CharSequence)this.mHeaderTitle);
        }
        else {
            remoteViews.setTextViewText(2131165486, (CharSequence)"");
        }
        if (this.mIsEpisode) {
            if (!StringUtils.isNotEmpty(this.mSubTitle)) {
                remoteViews.setTextViewText(2131165481, (CharSequence)"");
                return;
            }
            remoteViews.setTextViewText(2131165481, (CharSequence)this.mSubTitle);
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
    
    public void setPauseState(final boolean mPaused, final boolean mInTransition, final boolean mIsInPostPlay) {
        this.mPaused = mPaused;
        this.mInTransition = mInTransition;
        this.mIsInPostPlay = mIsInPostPlay;
        if (this.mNormalRemoteView != null) {
            this.updateControl(this.mNormalRemoteView);
        }
        if (this.mExpandedRemoteView != null) {
            this.updateControl(this.mExpandedRemoteView);
        }
    }
    
    public void setPlayNextState() {
        if (this.mExpandedRemoteView != null) {
            this.mExpandedRemoteView.setImageViewResource(2131165483, 2130837800);
            if (this.mIntentRetriever.getPlayNextIntent() != null) {
                this.mExpandedRemoteView.setOnClickPendingIntent(2131165483, this.mIntentRetriever.getPlayNextIntent());
            }
            this.mExpandedRemoteView.setImageViewResource(2131165484, 2130837802);
            this.mExpandedRemoteView.setOnClickPendingIntent(2131165484, this.mIntentRetriever.getStopIntent());
        }
        if (this.mNormalRemoteView != null) {
            this.mNormalRemoteView.setImageViewResource(2131165483, 2130837800);
            if (this.mIntentRetriever.getPlayNextIntent() != null) {
                this.mNormalRemoteView.setOnClickPendingIntent(2131165483, this.mIntentRetriever.getPlayNextIntent());
            }
            this.mNormalRemoteView.setImageViewResource(2131165484, 2130837802);
            this.mNormalRemoteView.setOnClickPendingIntent(2131165484, this.mIntentRetriever.getStopIntent());
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
        if (b) {
            if (this.mExpandedRemoteView != null) {
                this.mExpandedRemoteView.setViewVisibility(2131165482, 0);
            }
            if (this.mNormalRemoteView != null) {
                this.mNormalRemoteView.setViewVisibility(2131165482, 0);
            }
        }
        else {
            if (this.mExpandedRemoteView != null) {
                this.mExpandedRemoteView.setViewVisibility(2131165482, 8);
            }
            if (this.mNormalRemoteView != null) {
                this.mNormalRemoteView.setViewVisibility(2131165482, 8);
            }
        }
    }
}
