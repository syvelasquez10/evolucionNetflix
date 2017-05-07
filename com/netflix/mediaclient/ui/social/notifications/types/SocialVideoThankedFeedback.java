// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications.types;

import android.text.Html;
import com.netflix.mediaclient.ui.social.notifications.SocialNotificationViewHolder;
import android.view.View;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialNotificationSummary;
import android.support.v4.app.NotificationCompat;

public class SocialVideoThankedFeedback extends SocialNotification
{
    @Override
    protected void addNotificationText(final NotificationCompat.Builder builder, final NotificationCompat.BigPictureStyle bigPictureStyle, final SocialNotificationSummary socialNotificationSummary, final Context context) {
        final String string = context.getResources().getString(2131493398);
        bigPictureStyle.setSummaryText(string);
        builder.setContentText(string);
    }
    
    @Override
    public SocialNotificationSummary.NotificationTypes getNotificationType() {
        return SocialNotificationSummary.NotificationTypes.THANKS_SENT;
    }
    
    @Override
    public void initView(final View view, final SocialNotificationViewHolder socialNotificationViewHolder, final SocialNotificationSummary socialNotificationSummary, final Context context) {
        super.initView(view, socialNotificationViewHolder, socialNotificationSummary, context);
        socialNotificationViewHolder.getMiddleTextView().setText((CharSequence)Html.fromHtml(context.getResources().getString(2131493402, new Object[] { socialNotificationSummary.getVideoSummary().getTitle() })));
    }
}
