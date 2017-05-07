// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.app.Fragment;
import com.netflix.mediaclient.ui.details.DetailsFrag;
import android.view.MenuItem;
import java.math.BigInteger;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import android.app.Activity;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.view.View$OnClickListener;
import android.widget.LinearLayout$LayoutParams;
import android.app.NotificationManager;
import android.support.v4.content.LocalBroadcastManager;
import java.io.Serializable;
import com.netflix.mediaclient.servicemgr.interface_.user.UserProfile;
import com.netflix.mediaclient.ui.details.RecommendToFriendsFrag;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.res.Resources;
import java.util.Set;
import android.os.Parcelable;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.servicemgr.AddToListData$StateListener;
import android.widget.TextView;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.Menu;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.content.Context;

public class SocialUtils
{
    public static final int BASE_ENCODING_MODULE = 62;
    public static final int PAGE_SOCIAL_NOTIFICATIONS_SIZE = 20;
    public static final String SHARE_URL_SUFFIX = "?s=a";
    public static final int SOCIAL_NOTIFICATIONS_ID = 1000;
    public static final String SWIPED_SOCIAL_NOTIFICATION_ID = "swiped_social_notification_id";
    public static final String TAG = "SocialUtils";
    private static final String TINY_HOST = "http://movi.es/";
    private static SocialUtils$ShareSheetType shareSheetType;
    
    static {
        SocialUtils.shareSheetType = SocialUtils$ShareSheetType.RECOMMEND_ONLY;
    }
    
    public static void addShareIconIfNeeded(final NetflixActivity netflixActivity, final Menu menu) {
        if (getShareSheetType() == SocialUtils$ShareSheetType.SHARE_IN_HEADER || getShareSheetType() == SocialUtils$ShareSheetType.RECOMMEND_PLUS_SHARE) {
            menu.add(0, 2131623953, 65536, 2131165610).setIcon(2130837735).setShowAsAction(2);
        }
    }
    
    private static AddToListData$StateListener addToMyListWrapper(final NetflixActivity netflixActivity, final ServiceManager serviceManager, final TextView textView, final TextView textView2, final String s) {
        AddToListData$StateListener addToMyListWrapper;
        final AddToListData$StateListener addToListData$StateListener = addToMyListWrapper = null;
        if (serviceManager != null) {
            addToMyListWrapper = addToListData$StateListener;
            if (netflixActivity != null) {
                addToMyListWrapper = addToListData$StateListener;
                if (textView != null) {
                    addToMyListWrapper = serviceManager.createAddToMyListWrapper((DetailsActivity)netflixActivity, textView, textView2, false);
                    serviceManager.registerAddToMyListListener(s, addToMyListWrapper);
                }
            }
        }
        return addToMyListWrapper;
    }
    
    public static <T> void castArrayToSet(final Parcelable[] array, final Set<T> set) {
        for (int length = array.length, i = 0; i < length; ++i) {
            set.add((T)array[i]);
        }
    }
    
    public static SocialUtils$ShareSheetType getShareSheetType() {
        return SocialUtils.shareSheetType;
    }
    
    private static String getShareText(final Resources resources, final String s, final String s2) {
        return resources.getString(2131165611, new Object[] { s, s2 });
    }
    
    private static String getShareUrl(final String s, final VideoType videoType) {
        SocialUtils$TinyTypes socialUtils$TinyTypes;
        if (videoType == VideoType.MOVIE) {
            socialUtils$TinyTypes = SocialUtils$TinyTypes.MOVIE_TYPE;
        }
        else {
            socialUtils$TinyTypes = SocialUtils$TinyTypes.SERIES_TYPE;
        }
        return tinyify(socialUtils$TinyTypes, s) + "?s=a";
    }
    
    public static SocialUtils$NotificationsListStatus handleNotificationsUpdateReceiver(final Intent intent, final String s) {
        Log.i(s, "Received social notifications list updated intent");
        if (intent == null) {
            Log.w(s, "Received null intent");
            return SocialUtils$NotificationsListStatus.NO_MESSAGES;
        }
        final String action = intent.getAction();
        if (Log.isLoggable()) {
            Log.v(s, "notificationsUpdateReciever invoked with Action: " + action);
        }
        if ("com.netflix.mediaclient.intent.action.BA_NOTIFICATION_LIST_UPDATED".equals(action)) {
            Log.i(s, "Updating menu icon");
            return (SocialUtils$NotificationsListStatus)intent.getSerializableExtra("notifications_list_status");
        }
        if (Log.isLoggable()) {
            Log.w(s, "handleNotificationsUpdateReceiver got strange action: " + action);
        }
        return SocialUtils$NotificationsListStatus.NO_MESSAGES;
    }
    
    public static void ifSocialNotificationWasCanceledUpdateItsStatus(final Context context, final Intent intent, final String s) {
        final String stringExtra = intent.getStringExtra("swiped_social_notification_id");
        if (!StringUtils.isEmpty(stringExtra)) {
            Log.i(s, "saving swiped out notification id to preferences");
            PreferenceUtils.putStringPref(context, "notification_id_deleted_from_statusbar", stringExtra);
        }
    }
    
    public static boolean isNotificationsFeatureSupported(final NetflixActivity netflixActivity) {
        return netflixActivity != null && isNotificationsFeatureSupported(netflixActivity.getServiceManager());
    }
    
    public static boolean isNotificationsFeatureSupported(final ServiceManager serviceManager) {
        return RecommendToFriendsFrag.isSocialRecommendationsFeatureSupported(serviceManager);
    }
    
    public static boolean isNotificationsFeatureSupported(final UserProfile userProfile, final Context context) {
        return RecommendToFriendsFrag.isSocialRecommendationsFeatureSupported(userProfile, context);
    }
    
    public static void notifyOthersOfUnreadNotifications(final Context context, final boolean b, final boolean b2) {
        SocialUtils$NotificationsListStatus socialUtils$NotificationsListStatus;
        if (b) {
            socialUtils$NotificationsListStatus = SocialUtils$NotificationsListStatus.HAS_UNREAD_MESSAGES;
        }
        else if (b2) {
            socialUtils$NotificationsListStatus = SocialUtils$NotificationsListStatus.READ_ONLY;
        }
        else {
            socialUtils$NotificationsListStatus = SocialUtils$NotificationsListStatus.NO_MESSAGES;
        }
        if (Log.isLoggable()) {
            Log.i("SocialUtils", "notifyOthersOfUnreadNotifications: " + socialUtils$NotificationsListStatus);
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.BA_NOTIFICATION_LIST_UPDATED");
        intent.putExtra("notifications_list_status", (Serializable)socialUtils$NotificationsListStatus);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        if (socialUtils$NotificationsListStatus != SocialUtils$NotificationsListStatus.HAS_UNREAD_MESSAGES) {
            removeSocialNotificationsFromStatusBar(context);
        }
    }
    
    public static void removeSocialNotificationsFromStatusBar(final Context context) {
        ((NotificationManager)context.getSystemService("notification")).cancel(1000);
    }
    
    public static void setShareSheetType(int n) {
        --n;
        if (n >= 0 && n < SocialUtils$ShareSheetType.values().length) {
            SocialUtils.shareSheetType = SocialUtils$ShareSheetType.values()[n];
        }
        else if (Log.isLoggable()) {
            Log.w("SocialUtils", "setShareSheetType() got weird cellIndex: " + n + ". Cell #1 will be used instead.");
        }
    }
    
    public static AddToListData$StateListener setupVideoDetailsButtons(final NetflixActivity netflixActivity, final ServiceManager serviceManager, final TextView textView, final TextView textView2, final TextView textView3, final TextView textView4, final String s, final String s2, final VideoType videoType) {
        final AddToListData$StateListener addToListData$StateListener = null;
        final AddToListData$StateListener addToListData$StateListener2 = null;
        AddToListData$StateListener addToListData$StateListener3;
        if (netflixActivity == null) {
            Log.w("SocialUtils", "Activity is null, bail out...");
            addToListData$StateListener3 = addToListData$StateListener2;
        }
        else {
            setShareSheetType(serviceManager.getConfiguration().getShareSheetExperience());
            AddToListData$StateListener addToListData$StateListener4 = null;
            switch (SocialUtils$3.$SwitchMap$com$netflix$mediaclient$util$SocialUtils$ShareSheetType[getShareSheetType().ordinal()]) {
                default: {
                    addToListData$StateListener4 = addToListData$StateListener;
                    break;
                }
                case 1: {
                    addToListData$StateListener4 = addToMyListWrapper(netflixActivity, serviceManager, textView3, textView4, s);
                    break;
                }
                case 2: {
                    textView.setVisibility(8);
                    ((LinearLayout$LayoutParams)textView3.getLayoutParams()).leftMargin = 0;
                    return addToMyListWrapper(netflixActivity, serviceManager, textView3, textView4, s);
                }
                case 3: {
                    textView.setText(2131165610);
                    textView.setCompoundDrawablesWithIntrinsicBounds(2130837780, 0, 0, 0);
                }
                case 4: {
                    textView.setOnClickListener((View$OnClickListener)new SocialUtils$1(netflixActivity, s, s2, videoType));
                    return addToMyListWrapper(netflixActivity, serviceManager, textView3, textView4, s);
                }
                case 5: {
                    netflixActivity.invalidateOptionsMenu();
                    addToListData$StateListener4 = addToMyListWrapper(netflixActivity, serviceManager, textView3, textView4, s);
                    break;
                }
                case 6: {
                    textView.setVisibility(8);
                    netflixActivity.invalidateOptionsMenu();
                    ((LinearLayout$LayoutParams)textView3.getLayoutParams()).leftMargin = 0;
                    return addToMyListWrapper(netflixActivity, serviceManager, textView3, textView4, s);
                }
            }
            if (textView == null) {
                Log.i("SocialUtils", "We don't have recommend button on tablets yet...");
                return addToListData$StateListener4;
            }
            if (RecommendToFriendsFrag.isSocialRecommendationsFeatureSupported(serviceManager) && serviceManager.getCurrentProfile().isSocialConnected()) {
                UIViewLogUtils.reportUIViewImpressionEvent((Context)netflixActivity, UIViewLogging$UIViewCommandName.socialRecommendButton, RecommendToFriendsFrag.getTrackIdStatic(netflixActivity));
                textView.setOnClickListener((View$OnClickListener)new SocialUtils$2(serviceManager, netflixActivity, s));
                return addToListData$StateListener4;
            }
            textView.setVisibility(8);
            addToListData$StateListener3 = addToListData$StateListener4;
            if (textView2 != null) {
                textView2.setVisibility(8);
                return addToListData$StateListener4;
            }
        }
        return addToListData$StateListener3;
    }
    
    public static AddToListData$StateListener setupVideoDetailsButtons(final VideoDetailsViewGroup videoDetailsViewGroup, final NetflixActivity netflixActivity, final ServiceManager serviceManager, final String s, final String s2, final VideoType videoType) {
        return setupVideoDetailsButtons(netflixActivity, serviceManager, videoDetailsViewGroup.getRecommendButton(), videoDetailsViewGroup.getRecommendButtonLabel(), videoDetailsViewGroup.getAddToMyListButton(), videoDetailsViewGroup.getAddToMyListButtonLabel(), s, s2, videoType);
    }
    
    private static void startShare(final Context context, String shareUrl, final String s, final VideoType videoType) {
        final Resources resources = context.getResources();
        shareUrl = getShareUrl(shareUrl, videoType);
        UIViewLogUtils.reportUIViewCommandStarted(context, UIViewLogging$UIViewCommandName.shareSheet, IClientLogging$ModalView.movieDetails, null, null);
        UserActionLogUtils.reportShareSheetActionStarted(shareUrl, context, null, IClientLogging$ModalView.movieDetails);
        UIViewLogUtils.reportUIViewCommandEnded(context);
        final Intent intent = new Intent("android.intent.action.SEND");
        intent.setFlags(268435456);
        intent.setType("text/plain");
        intent.putExtra("android.intent.extra.TEXT", getShareText(resources, s, shareUrl));
        context.startActivity(Intent.createChooser(intent, (CharSequence)resources.getString(2131165612)));
        UserActionLogUtils.reportShareSheetActionEnded(context, IClientLogging$CompletionReason.success, null);
    }
    
    public static String tinyify(final SocialUtils$TinyTypes socialUtils$TinyTypes, final String s) {
        return "http://movi.es/" + BaseConverter.convertToBase(new BigInteger(socialUtils$TinyTypes.ordinal() + s), 62);
    }
    
    public static boolean tryHandleMenuItemClick(final MenuItem menuItem, final Context context) {
        if (context instanceof DetailsActivity && menuItem.getItemId() == 2131623953) {
            final DetailsActivity detailsActivity = (DetailsActivity)context;
            final String videoId = detailsActivity.getVideoId();
            final VideoType videoType = detailsActivity.getVideoType();
            final Fragment primaryFrag = detailsActivity.getPrimaryFrag();
            String title;
            if (primaryFrag instanceof DetailsFrag) {
                title = ((DetailsFrag)primaryFrag).getTitle();
            }
            else {
                title = null;
            }
            String s = title;
            if (title == null) {
                s = "";
            }
            startShare(context, videoId, s, videoType);
            return true;
        }
        return false;
    }
}
