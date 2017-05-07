// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications.types;

import com.netflix.mediaclient.Log;
import android.text.Html;
import android.text.format.DateUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.View;
import com.netflix.model.leafs.social.SocialNotificationSummary$NotificationTypes;
import android.widget.TextView;
import com.netflix.mediaclient.ui.social.notifications.NotificationViewHolder;
import android.content.Context;
import com.netflix.model.leafs.social.SocialNotificationSummary;
import android.support.v4.app.NotificationCompat$BigPictureStyle;
import android.support.v4.app.NotificationCompat$Builder;

public class NewSeasonAlert extends SocialNotification
{
    @Override
    protected void addNotificationText(final NotificationCompat$Builder notificationCompat$Builder, final NotificationCompat$BigPictureStyle notificationCompat$BigPictureStyle, final SocialNotificationSummary socialNotificationSummary, final Context context) {
    }
    
    @Override
    public TextView getAddToMyListButton(final NotificationViewHolder notificationViewHolder) {
        return (TextView)notificationViewHolder.getRightButton();
    }
    
    @Override
    public SocialNotificationSummary$NotificationTypes getNotificationType() {
        return SocialNotificationSummary$NotificationTypes.NEW_SEASON_ALERT;
    }
    
    @Override
    public View getPlayMovieButton(final NotificationViewHolder notificationViewHolder) {
        return (View)notificationViewHolder.getNSAArtImage();
    }
    
    @Override
    public void initView(final NotificationViewHolder notificationViewHolder, final SocialNotificationSummary socialNotificationSummary, final Context context) {
        notificationViewHolder.getNSAArtImage().setPressedStateHandlerEnabled(false);
        notificationViewHolder.getMovieArtImage().setPressedStateHandlerEnabled(false);
        notificationViewHolder.getTopTextView().setVisibility(0);
        notificationViewHolder.getTopTextView().setText(2131493373);
        if (notificationViewHolder.getFriendImage() != null) {
            notificationViewHolder.getFriendImage().setVisibility(8);
        }
        if (notificationViewHolder.getUnreadIndicator() != null) {
            final View unreadIndicator = notificationViewHolder.getUnreadIndicator();
            int visibility;
            if (socialNotificationSummary.getWasRead()) {
                visibility = 4;
            }
            else {
                visibility = 0;
            }
            unreadIndicator.setVisibility(visibility);
        }
        notificationViewHolder.getMovieArtImage().setVisibility(8);
        notificationViewHolder.getNSAArtImage().setVisibility(0);
        NetflixActivity.getImageLoader(context).showImg(notificationViewHolder.getNSAArtImage(), socialNotificationSummary.getTVCardUrl(), IClientLogging$AssetType.boxArt, socialNotificationSummary.getVideoTitle(), ImageLoader$StaticImgConfig.DARK, true);
        if (notificationViewHolder.getBottomTextView() != null) {
            notificationViewHolder.getBottomTextView().setVisibility(8);
        }
        if (notificationViewHolder.getTimeStampView() != null) {
            notificationViewHolder.getTimeStampView().setVisibility(0);
            notificationViewHolder.getTimeStampView().setText(DateUtils.getRelativeTimeSpanString(context, socialNotificationSummary.getNSATimestamp()));
        }
        if (notificationViewHolder.getPlayButton() != null) {
            notificationViewHolder.getPlayButton().setVisibility(8);
        }
        if (notificationViewHolder.getNSAPlayButton() != null) {
            notificationViewHolder.getNSAPlayButton().setVisibility(0);
        }
        final int resourceStringId = socialNotificationSummary.getShowType().getResourceStringId();
        notificationViewHolder.getMiddleTextView().setGravity(3);
        String s;
        if (socialNotificationSummary.getNSASeasonsCount() > 1) {
            s = context.getResources().getQuantityString(resourceStringId, socialNotificationSummary.getNSASeasonsCount(), new Object[] { socialNotificationSummary.getNSASeasonsCount() });
        }
        else {
            s = context.getResources().getQuantityString(resourceStringId, 1, new Object[] { socialNotificationSummary.getNSASeasonIndex() });
        }
        notificationViewHolder.getMiddleTextView().setText((CharSequence)Html.fromHtml(s));
        if (Log.isLoggable()) {
            Log.v(NewSeasonAlert.TAG, "Set middle text to: " + (Object)notificationViewHolder.getMiddleTextView().getText());
        }
        if (notificationViewHolder.getRightButton() != null) {
            notificationViewHolder.getRightButton().setVisibility(0);
        }
        if (notificationViewHolder.getLeftButton() != null) {
            notificationViewHolder.getLeftButton().setVisibility(4);
        }
    }
    
    @Override
    public boolean supportsStatusBar() {
        return false;
    }
}
