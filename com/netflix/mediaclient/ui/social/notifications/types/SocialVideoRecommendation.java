// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications.types;

import android.widget.Button;
import android.text.Html;
import android.view.View;
import com.netflix.model.leafs.social.SocialNotificationSummary$NotificationTypes;
import android.widget.TextView;
import com.netflix.mediaclient.ui.social.notifications.NotificationViewHolder;
import com.netflix.mediaclient.util.StringUtils;
import android.support.v4.app.NotificationCompat$BigPictureStyle;
import android.content.Intent;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.protocol.nflx.SendThanksToSocialNotificationActionHandler;
import android.support.v4.app.NotificationCompat$Action;
import android.app.PendingIntent;
import com.netflix.mediaclient.util.NotificationUtils;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.content.Context;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.model.leafs.social.SocialNotificationsListSummary;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import android.support.v4.app.NotificationCompat$Builder;

public class SocialVideoRecommendation extends SocialNotification
{
    @Override
    protected void addNotificationActions(final NotificationCompat$Builder notificationCompat$Builder, final SocialNotificationSummary socialNotificationSummary, final SocialNotificationsListSummary socialNotificationsListSummary, final MessageData messageData, final Context context) {
        super.addNotificationActions(notificationCompat$Builder, socialNotificationSummary, socialNotificationsListSummary, messageData, context);
        final int n = (int)System.currentTimeMillis();
        final Intent intentForBroadcastReceiver = DetailsActivityLauncher.getIntentForBroadcastReceiver(socialNotificationSummary.getVideoType(), socialNotificationSummary.getId(), socialNotificationSummary.getVideoId(), socialNotificationSummary.getVideoTitle(), new PlayContextImp(socialNotificationsListSummary.getRequestId(), socialNotificationsListSummary.getMDPTrackId(), 0, 0), null, messageData);
        NotificationUtils.addNotificationSourceToIntent(intentForBroadcastReceiver);
        notificationCompat$Builder.addAction(new NotificationCompat$Action(2130837717, context.getString(2131165682), PendingIntent.getBroadcast(context.getApplicationContext(), n, intentForBroadcastReceiver, 134217728)));
        final Intent sayThanksIntent = SendThanksToSocialNotificationActionHandler.getSayThanksIntent(context, socialNotificationSummary.getId(), socialNotificationSummary.getStoryId(), true, messageData);
        NotificationUtils.addNotificationSourceToIntent(sayThanksIntent);
        notificationCompat$Builder.addAction(new NotificationCompat$Action(2130837790, context.getString(2131165684), PendingIntent.getBroadcast(context.getApplicationContext(), n, sayThanksIntent, 134217728)));
        final Intent coldStartIntentForService = PlayerActivity.createColdStartIntentForService(socialNotificationSummary.getVideoId(), socialNotificationSummary.getVideoType(), new PlayContextImp(socialNotificationsListSummary.getRequestId(), socialNotificationsListSummary.getPlayerTrackId(), 0, 0));
        NotificationUtils.addNotificationSourceToIntent(coldStartIntentForService);
        notificationCompat$Builder.addAction(new NotificationCompat$Action(2130837741, context.getString(2131165683), PendingIntent.getBroadcast(context.getApplicationContext(), n, coldStartIntentForService, 134217728)));
    }
    
    @Override
    protected void addNotificationText(final NotificationCompat$Builder notificationCompat$Builder, final NotificationCompat$BigPictureStyle notificationCompat$BigPictureStyle, final SocialNotificationSummary socialNotificationSummary, final Context context) {
        String s;
        if (StringUtils.isEmpty(socialNotificationSummary.getMessageString())) {
            s = context.getResources().getString(2131165692);
        }
        else {
            s = "\"" + socialNotificationSummary.getMessageString() + "\"";
        }
        notificationCompat$BigPictureStyle.setSummaryText(s);
        notificationCompat$Builder.setContentText(s);
    }
    
    @Override
    public TextView getAddToMyListButton(final NotificationViewHolder notificationViewHolder) {
        return (TextView)notificationViewHolder.getRightButton();
    }
    
    @Override
    public SocialNotificationSummary$NotificationTypes getNotificationType() {
        return SocialNotificationSummary$NotificationTypes.VIDEO_RECOMMENDATION;
    }
    
    @Override
    public View getSayThanksButton(final NotificationViewHolder notificationViewHolder) {
        return (View)notificationViewHolder.getLeftButton();
    }
    
    @Override
    public void initView(final NotificationViewHolder notificationViewHolder, final SocialNotificationSummary socialNotificationSummary, final Context context) {
        boolean enabled = true;
        super.initView(notificationViewHolder, socialNotificationSummary, context);
        notificationViewHolder.getMiddleTextView().setText((CharSequence)Html.fromHtml(context.getResources().getString(2131165693, new Object[] { "" })));
        if (notificationViewHolder.getLeftButton() != null) {
            notificationViewHolder.getLeftButton().setVisibility(0);
            final Button leftButton = notificationViewHolder.getLeftButton();
            if (socialNotificationSummary.getWasThanked()) {
                enabled = false;
            }
            leftButton.setEnabled(enabled);
            final Button leftButton2 = notificationViewHolder.getLeftButton();
            int text;
            if (socialNotificationSummary.getWasThanked()) {
                text = 2131165689;
            }
            else {
                text = 2131165688;
            }
            leftButton2.setText(text);
        }
        if (notificationViewHolder.getRightButton() != null) {
            notificationViewHolder.getRightButton().setVisibility(0);
        }
    }
}
