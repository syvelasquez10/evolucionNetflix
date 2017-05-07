// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx.notification;

import android.widget.RemoteViews;
import android.content.Context;

public class MdxPostPlayerRemoteViews extends MdxRemoteViews
{
    private String headerTitle;
    
    public MdxPostPlayerRemoteViews(final String s, final boolean b, final MdxNotificationManager.MdxNotificationIntentRetriever mdxNotificationIntentRetriever, final Context headerTitle) {
        super(s, b, mdxNotificationIntentRetriever, headerTitle);
        this.setHeaderTitle(headerTitle);
    }
    
    private void setHeaderTitle(final Context context) {
        if (context == null) {
            return;
        }
        this.headerTitle = context.getResources().getString(2131493243);
    }
    
    private void setPlayNextButton(final RemoteViews remoteViews) {
        if (this.mIntentRetriever != null && remoteViews != null) {
            remoteViews.setImageViewResource(2131165483, 2130837800);
            if (this.mIntentRetriever.getPlayNextIntent() != null) {
                remoteViews.setOnClickPendingIntent(2131165483, this.mIntentRetriever.getPlayNextIntent());
            }
        }
    }
    
    private void setPostplayButtons(final RemoteViews remoteViews) {
        if (remoteViews == null) {
            return;
        }
        this.setPlayNextButton(remoteViews);
        this.setStopButton(remoteViews);
    }
    
    private void setStopButton(final RemoteViews remoteViews) {
        if (this.mIntentRetriever == null || remoteViews == null) {
            return;
        }
        remoteViews.setImageViewResource(2131165484, 2130837802);
        remoteViews.setOnClickPendingIntent(2131165484, this.mIntentRetriever.getStopIntent());
    }
    
    @Override
    protected RemoteViews createViewForEpisodes(final boolean b) {
        if (b) {
            return new RemoteViews(this.mPackageName, 2130903127);
        }
        return new RemoteViews(this.mPackageName, 2130903128);
    }
    
    @Override
    protected String getHeader() {
        return this.headerTitle;
    }
    
    @Override
    public void setState(final boolean b, final boolean b2) {
    }
    
    @Override
    protected void updateControlAsActive(final RemoteViews postplayButtons) {
        this.setPostplayButtons(postplayButtons);
    }
    
    @Override
    protected void updateControlsAsInactive(final RemoteViews postplayButtons) {
        this.setPostplayButtons(postplayButtons);
    }
}
