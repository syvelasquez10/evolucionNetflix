// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.notification;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import android.widget.RemoteViews;
import android.graphics.Bitmap;

public abstract class MdxRemoteViews
{
    private static final String TAG = "nf_mdxnotification";
    protected Bitmap mBoxart;
    protected RemoteViews mExpandedRemoteView;
    protected boolean mInTransition;
    protected final MdxNotificationManager$MdxNotificationIntentRetriever mIntentRetriever;
    protected boolean mIsEpisode;
    protected String mMainTitle;
    protected RemoteViews mNormalRemoteView;
    protected final String mPackageName;
    protected boolean mPaused;
    protected String mSubTitle;
    
    public MdxRemoteViews(final String mPackageName, final boolean mIsEpisode, final MdxNotificationManager$MdxNotificationIntentRetriever mIntentRetriever, final Context context) {
        this.mIntentRetriever = mIntentRetriever;
        this.mPackageName = mPackageName;
        this.mIsEpisode = mIsEpisode;
    }
    
    private RemoteViews createRemoteView(final boolean b) {
        RemoteViews remoteViews;
        if (this.mIsEpisode) {
            remoteViews = this.createViewForEpisodes(b);
        }
        else {
            remoteViews = this.createViewsForMovies(b);
        }
        if (remoteViews != null) {
            this.updateBoxart(remoteViews);
            this.updateTitles(remoteViews);
            this.updateControl(remoteViews);
        }
        return remoteViews;
    }
    
    private RemoteViews createViewsForMovies(final boolean b) {
        if (this.mPackageName == null) {
            return null;
        }
        if (b) {
            return new RemoteViews(this.mPackageName, 2130903149);
        }
        return new RemoteViews(this.mPackageName, 2130903148);
    }
    
    private void updateBoxart(final RemoteViews remoteViews) {
        if (this.mBoxart == null || remoteViews == null) {
            return;
        }
        remoteViews.setImageViewBitmap(2131427668, this.mBoxart);
    }
    
    private void updateTitles(final RemoteViews remoteViews) {
        if (remoteViews != null) {
            if (StringUtils.isNotEmpty(this.mMainTitle)) {
                remoteViews.setTextViewText(2131427669, (CharSequence)this.mMainTitle);
            }
            else {
                remoteViews.setTextViewText(2131427669, (CharSequence)"");
            }
            if (StringUtils.isNotEmpty(this.getHeader())) {
                remoteViews.setTextViewText(2131427675, (CharSequence)this.getHeader());
            }
            else {
                remoteViews.setTextViewText(2131427675, (CharSequence)"");
            }
            if (this.mIsEpisode) {
                if (StringUtils.isNotEmpty(this.mSubTitle)) {
                    remoteViews.setTextViewText(2131427670, (CharSequence)this.mSubTitle);
                    return;
                }
                remoteViews.setTextViewText(2131427670, (CharSequence)"");
            }
        }
    }
    
    protected abstract RemoteViews createViewForEpisodes(final boolean p0);
    
    protected abstract String getHeader();
    
    public RemoteViews getRemoteView() {
        if (this.mNormalRemoteView == null) {
            this.mNormalRemoteView = this.createRemoteView(false);
        }
        return this.mNormalRemoteView;
    }
    
    public RemoteViews getRemoteViewBigContetnt() {
        if (this.mExpandedRemoteView == null) {
            this.mExpandedRemoteView = this.createRemoteView(true);
        }
        return this.mExpandedRemoteView;
    }
    
    public void setBoxart(final Bitmap mBoxart) {
        if (mBoxart != null) {
            Log.d("nf_mdxnotification", "MdxRemoteViewManager:setBoxart");
            this.mBoxart = mBoxart;
            if (this.mNormalRemoteView != null) {
                this.updateBoxart(this.mNormalRemoteView);
            }
            if (this.mExpandedRemoteView != null) {
                this.updateBoxart(this.mExpandedRemoteView);
            }
        }
    }
    
    protected void setPauseActive(final RemoteViews remoteViews) {
        if (remoteViews == null || this.mIntentRetriever == null) {
            return;
        }
        remoteViews.setImageViewResource(2131427672, 2130837833);
        remoteViews.setOnClickPendingIntent(2131427672, this.mIntentRetriever.getPauseIntent());
    }
    
    protected void setPauseInactive(final RemoteViews remoteViews) {
        if (remoteViews == null || this.mIntentRetriever == null) {
            return;
        }
        remoteViews.setImageViewResource(2131427672, 2130837834);
        remoteViews.setOnClickPendingIntent(2131427672, this.mIntentRetriever.getNoActionIntent());
    }
    
    protected void setPlayActiveWithGetNext(final RemoteViews remoteViews) {
        if (remoteViews == null || this.mIntentRetriever == null) {
            return;
        }
        remoteViews.setImageViewResource(2131427672, 2130837835);
        remoteViews.setOnClickPendingIntent(2131427672, this.mIntentRetriever.getPlayNextIntent());
    }
    
    protected void setPlayActiveWithResume(final RemoteViews remoteViews) {
        if (remoteViews == null || this.mIntentRetriever == null) {
            return;
        }
        remoteViews.setImageViewResource(2131427672, 2130837835);
        remoteViews.setOnClickPendingIntent(2131427672, this.mIntentRetriever.getResumeIntent());
    }
    
    protected void setPlayInactive(final RemoteViews remoteViews) {
        if (remoteViews == null || this.mIntentRetriever == null) {
            return;
        }
        remoteViews.setImageViewResource(2131427672, 2130837836);
        remoteViews.setOnClickPendingIntent(2131427672, this.mIntentRetriever.getNoActionIntent());
    }
    
    public abstract void setState(final boolean p0, final boolean p1);
    
    protected void setStopActive(final RemoteViews remoteViews) {
        if (remoteViews == null || this.mIntentRetriever == null) {
            return;
        }
        remoteViews.setImageViewResource(2131427673, 2130837837);
        remoteViews.setOnClickPendingIntent(2131427673, this.mIntentRetriever.getStopIntent());
    }
    
    protected void setStopInactive(final RemoteViews remoteViews) {
        if (remoteViews == null || this.mIntentRetriever == null) {
            return;
        }
        remoteViews.setImageViewResource(2131427673, 2130837838);
        remoteViews.setOnClickPendingIntent(2131427673, this.mIntentRetriever.getNoActionIntent());
    }
    
    public void setTitles(final boolean mIsEpisode, final String mMainTitle, final String mSubTitle) {
        if (this.mIsEpisode != mIsEpisode) {
            this.mIsEpisode = mIsEpisode;
            this.mNormalRemoteView = this.createRemoteView(false);
            if (this.mExpandedRemoteView != null) {
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
    
    protected void updateControl(final RemoteViews remoteViews) {
        if (remoteViews == null) {
            return;
        }
        if (!this.mInTransition) {
            this.updateControlAsActive(remoteViews);
            return;
        }
        this.updateControlsAsInactive(remoteViews);
    }
    
    protected void updateControlAsActive(final RemoteViews pauseActive) {
        if (pauseActive == null) {
            return;
        }
        this.setStopActive(pauseActive);
        if (this.mPaused) {
            this.setPlayActiveWithResume(pauseActive);
            return;
        }
        this.setPauseActive(pauseActive);
    }
    
    protected void updateControlsAsInactive(final RemoteViews pauseInactive) {
        if (pauseInactive == null) {
            return;
        }
        this.setStopInactive(pauseInactive);
        if (this.mPaused) {
            this.setPlayInactive(pauseInactive);
            return;
        }
        this.setPauseInactive(pauseInactive);
    }
}
