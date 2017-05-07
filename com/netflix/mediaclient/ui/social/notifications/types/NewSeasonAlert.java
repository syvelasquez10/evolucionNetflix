// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications.types;

import android.text.format.DateUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.text.Html;
import com.netflix.mediaclient.util.StringUtils;
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
        if (StringUtils.isNotEmpty(socialNotificationSummary.getHeaderText())) {
            notificationViewHolder.getTopTextView().setVisibility(0);
            notificationViewHolder.getTopTextView().setText((CharSequence)Html.fromHtml(socialNotificationSummary.getHeaderText()));
        }
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
        NetflixActivity.getImageLoader(context).showImg(notificationViewHolder.getNSAArtImage(), socialNotificationSummary.getTVCardUrl(), IClientLogging$AssetType.boxArt, socialNotificationSummary.getImageAltText(), ImageLoader$StaticImgConfig.DARK, true);
        if (notificationViewHolder.getBottomTextView() != null) {
            notificationViewHolder.getBottomTextView().setVisibility(8);
        }
        if (notificationViewHolder.getTimeStampView() != null && socialNotificationSummary.getShowTimestamp()) {
            notificationViewHolder.getTimeStampView().setVisibility(0);
            notificationViewHolder.getTimeStampView().setText(DateUtils.getRelativeTimeSpanString(context, socialNotificationSummary.getTimestamp()));
        }
        if (notificationViewHolder.getPlayButton() != null) {
            notificationViewHolder.getPlayButton().setVisibility(8);
        }
        if (notificationViewHolder.getNSAPlayButton() != null) {
            notificationViewHolder.getNSAPlayButton().setVisibility(0);
        }
        if (StringUtils.isNotEmpty(socialNotificationSummary.getBodyText())) {
            notificationViewHolder.getMiddleTextView().setGravity(3);
            notificationViewHolder.getMiddleTextView().setText((CharSequence)Html.fromHtml(socialNotificationSummary.getBodyText()));
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
