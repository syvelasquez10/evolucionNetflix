// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications.types;

import android.text.Html;
import com.netflix.mediaclient.ui.social.notifications.SocialNotificationViewHolder;
import android.view.View;
import com.netflix.model.leafs.social.SocialNotificationSummary$NotificationTypes;
import android.content.Context;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import android.support.v4.app.NotificationCompat$BigPictureStyle;
import android.support.v4.app.NotificationCompat$Builder;

public class SocialVideoImplicitFeedback extends SocialNotification
{
    @Override
    protected void addNotificationText(final NotificationCompat$Builder notificationCompat$Builder, final NotificationCompat$BigPictureStyle notificationCompat$BigPictureStyle, final SocialNotificationSummary socialNotificationSummary, final Context context) {
        final String string = context.getResources().getString(2131493358);
        notificationCompat$BigPictureStyle.setSummaryText(string);
        notificationCompat$Builder.setContentText(string);
    }
    
    @Override
    public SocialNotificationSummary$NotificationTypes getNotificationType() {
        return SocialNotificationSummary$NotificationTypes.IMPLICIT_FEEDBACK;
    }
    
    @Override
    public void initView(final View view, final SocialNotificationViewHolder socialNotificationViewHolder, final SocialNotificationSummary socialNotificationSummary, final Context context) {
        super.initView(view, socialNotificationViewHolder, socialNotificationSummary, context);
        socialNotificationViewHolder.getMiddleTextView().setText((CharSequence)Html.fromHtml(context.getResources().getString(2131493362, new Object[] { socialNotificationSummary.getVideo().getTitle() })));
    }
}
