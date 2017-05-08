// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iris.notifications.type;

import android.text.format.DateUtils;
import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.text.Html;
import com.netflix.mediaclient.util.StringUtils;
import android.view.View;
import com.netflix.model.leafs.social.IrisNotificationSummary$NotificationTypes;
import android.widget.TextView;
import com.netflix.mediaclient.ui.iris.notifications.NotificationViewHolder;
import android.content.Context;
import com.netflix.model.leafs.social.IrisNotificationSummary;
import android.support.v4.app.NotificationCompat$BigPictureStyle;
import android.support.v4.app.NotificationCompat$Builder;

public class NewSeasonAlert extends BaseNotification
{
    @Override
    protected void addNotificationText(final NotificationCompat$Builder notificationCompat$Builder, final NotificationCompat$BigPictureStyle notificationCompat$BigPictureStyle, final IrisNotificationSummary irisNotificationSummary, final Context context) {
    }
    
    @Override
    public TextView getAddToMyListButton(final NotificationViewHolder notificationViewHolder) {
        return (TextView)notificationViewHolder.getRightButton();
    }
    
    @Override
    public IrisNotificationSummary$NotificationTypes getNotificationType() {
        return IrisNotificationSummary$NotificationTypes.NEW_SEASON_ALERT;
    }
    
    @Override
    public View getPlayMovieButton(final NotificationViewHolder notificationViewHolder) {
        return (View)notificationViewHolder.getNSAArtImage();
    }
    
    @Override
    public void initView(final NotificationViewHolder notificationViewHolder, final IrisNotificationSummary irisNotificationSummary, final Context context) {
        notificationViewHolder.getNSAArtImage().setPressedStateHandlerEnabled(false);
        notificationViewHolder.getMovieArtImage().setPressedStateHandlerEnabled(false);
        if (StringUtils.isNotEmpty(irisNotificationSummary.getHeaderText())) {
            notificationViewHolder.getTopTextView().setVisibility(0);
            notificationViewHolder.getTopTextView().setText((CharSequence)Html.fromHtml(irisNotificationSummary.getHeaderText()));
        }
        if (notificationViewHolder.getFriendImage() != null) {
            notificationViewHolder.getFriendImage().setVisibility(8);
        }
        if (notificationViewHolder.getUnreadIndicator() != null) {
            final View unreadIndicator = notificationViewHolder.getUnreadIndicator();
            int visibility;
            if (irisNotificationSummary.getWasRead()) {
                visibility = 4;
            }
            else {
                visibility = 0;
            }
            unreadIndicator.setVisibility(visibility);
        }
        notificationViewHolder.getMovieArtImage().setVisibility(8);
        notificationViewHolder.getNSAArtImage().setVisibility(0);
        NetflixActivity.getImageLoader(context).showImg(notificationViewHolder.getNSAArtImage(), irisNotificationSummary.getTVCardUrl(), IClientLogging$AssetType.boxArt, irisNotificationSummary.getImageAltText(), ImageLoader$StaticImgConfig.DARK, true);
        if (notificationViewHolder.getBottomTextView() != null) {
            notificationViewHolder.getBottomTextView().setVisibility(8);
        }
        if (notificationViewHolder.getTimeStampView() != null && irisNotificationSummary.getShowTimestamp()) {
            notificationViewHolder.getTimeStampView().setVisibility(0);
            notificationViewHolder.getTimeStampView().setText(DateUtils.getRelativeTimeSpanString(context, irisNotificationSummary.getTimestamp()));
        }
        if (notificationViewHolder.getPlayButton() != null) {
            notificationViewHolder.getPlayButton().setVisibility(8);
        }
        if (notificationViewHolder.getNSAPlayButton() != null) {
            notificationViewHolder.getNSAPlayButton().setVisibility(0);
        }
        if (StringUtils.isNotEmpty(irisNotificationSummary.getBodyText())) {
            notificationViewHolder.getMiddleTextView().setGravity(8388611);
            notificationViewHolder.getMiddleTextView().setText((CharSequence)Html.fromHtml(irisNotificationSummary.getBodyText()));
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
