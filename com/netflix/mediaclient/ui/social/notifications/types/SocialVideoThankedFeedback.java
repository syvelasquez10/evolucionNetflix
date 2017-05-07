// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications.types;

import android.text.Html;
import com.netflix.mediaclient.ui.social.notifications.NotificationViewHolder;
import com.netflix.model.leafs.social.SocialNotificationSummary$NotificationTypes;
import android.content.Context;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import android.support.v4.app.NotificationCompat$BigPictureStyle;
import android.support.v4.app.NotificationCompat$Builder;

public class SocialVideoThankedFeedback extends SocialNotification
{
    @Override
    protected void addNotificationText(final NotificationCompat$Builder notificationCompat$Builder, final NotificationCompat$BigPictureStyle notificationCompat$BigPictureStyle, final SocialNotificationSummary socialNotificationSummary, final Context context) {
        final String string = context.getResources().getString(2131165694);
        notificationCompat$BigPictureStyle.setSummaryText(string);
        notificationCompat$Builder.setContentText(string);
    }
    
    @Override
    public SocialNotificationSummary$NotificationTypes getNotificationType() {
        return SocialNotificationSummary$NotificationTypes.THANKS_SENT;
    }
    
    @Override
    public void initView(final NotificationViewHolder notificationViewHolder, final SocialNotificationSummary socialNotificationSummary, final Context context) {
        super.initView(notificationViewHolder, socialNotificationSummary, context);
        notificationViewHolder.getMiddleTextView().setText((CharSequence)Html.fromHtml(context.getResources().getString(2131165695, new Object[] { "" })));
    }
}
