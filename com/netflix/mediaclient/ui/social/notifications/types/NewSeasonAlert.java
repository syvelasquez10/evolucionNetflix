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
    public void initView(final View view, final NotificationViewHolder notificationViewHolder, final SocialNotificationSummary socialNotificationSummary, final Context context) {
        notificationViewHolder.getNSAArtImage().setPressedStateHandlerEnabled(false);
        notificationViewHolder.getMovieArtImage().setPressedStateHandlerEnabled(false);
        notificationViewHolder.getTopTextView().setVisibility(8);
        if (notificationViewHolder.getFriendImage() != null) {
            notificationViewHolder.getFriendImage().setVisibility(8);
        }
        int backgroundResource;
        if (socialNotificationSummary.getWasRead()) {
            backgroundResource = 2130837888;
        }
        else {
            backgroundResource = 2131230883;
        }
        view.setBackgroundResource(backgroundResource);
        notificationViewHolder.getMovieArtImage().setVisibility(8);
        notificationViewHolder.getNSAArtImage().setVisibility(0);
        String s;
        if (StringUtils.isEmpty(socialNotificationSummary.getNSABoxartUrl())) {
            s = socialNotificationSummary.getVideo().getHorzDispUrl();
        }
        else {
            s = socialNotificationSummary.getNSABoxartUrl();
        }
        NetflixActivity.getImageLoader(context).showImg(notificationViewHolder.getNSAArtImage(), s, IClientLogging$AssetType.boxArt, socialNotificationSummary.getVideo().getTitle(), ImageLoader$StaticImgConfig.DARK, true);
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
        String s2;
        if (socialNotificationSummary.getNSASeasonsCount() > 1) {
            s2 = context.getResources().getQuantityString(resourceStringId, socialNotificationSummary.getNSASeasonsCount(), new Object[] { socialNotificationSummary.getNSASeasonsCount(), socialNotificationSummary.getVideo().getTitle() });
        }
        else {
            s2 = context.getResources().getQuantityString(resourceStringId, 1, new Object[] { socialNotificationSummary.getVideo().getTitle(), socialNotificationSummary.getNSASeasonIndex() });
        }
        notificationViewHolder.getMiddleTextView().setText((CharSequence)Html.fromHtml(s2));
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
