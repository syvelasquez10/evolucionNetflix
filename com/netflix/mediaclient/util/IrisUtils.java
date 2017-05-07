// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.app.Fragment;
import com.netflix.mediaclient.ui.details.DetailsFrag;
import android.view.MenuItem;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.util.log.UIViewLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.app.NotificationManager;
import android.support.v4.content.LocalBroadcastManager;
import java.io.Serializable;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import android.content.res.Resources;
import java.util.Set;
import android.os.Parcelable;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import android.content.Context;
import android.view.Menu;
import com.netflix.mediaclient.ui.iris.notifications.SlidingMenuNotificationsFrag;

public class IrisUtils
{
    public static final int NOTIFICATIONS_ID = 1000;
    public static final int PAGE_NOTIFICATIONS_SIZE;
    public static final String SHARE_URL_SUFFIX = "source=android";
    public static final String SWIPED_NOTIFICATION_ID = "swiped_notification_id";
    private static final String TAG = "SocialUtils";
    
    static {
        PAGE_NOTIFICATIONS_SIZE = SlidingMenuNotificationsFrag.MAX_NUM_NOTIFICATIONS;
    }
    
    public static void addShareIcon(final Menu menu, final Context context) {
        if (context instanceof DetailsActivity) {
            menu.add(0, 2131623953, 0, 2131165700).setIcon(2130837694).setShowAsAction(2);
        }
    }
    
    public static <T> void castArrayToSet(final Parcelable[] array, final Set<T> set) {
        for (int length = array.length, i = 0; i < length; ++i) {
            set.add((T)array[i]);
        }
    }
    
    private static String getShareText(final Resources resources, final String s, final String s2) {
        return resources.getString(2131165701, new Object[] { s, s2 });
    }
    
    private static String getShareUrl(final String s, final VideoType videoType) {
        return String.format("%s/%s/%s?%s", "www.netflix.com", "title", s, "source=android");
    }
    
    public static IrisUtils$NotificationsListStatus handleNotificationsUpdateReceiver(final Intent intent, final String s) {
        Log.i(s, "Received social notifications list updated intent");
        if (intent == null) {
            Log.w(s, "Received null intent");
            return IrisUtils$NotificationsListStatus.NO_MESSAGES;
        }
        final String action = intent.getAction();
        if (Log.isLoggable()) {
            Log.v(s, "notificationsUpdateReciever invoked with Action: " + action);
        }
        if ("com.netflix.mediaclient.intent.action.BA_IRIS_NOTIFICATION_LIST_UPDATED".equals(action)) {
            Log.i(s, "Updating menu icon");
            return (IrisUtils$NotificationsListStatus)intent.getSerializableExtra("notifications_list_status");
        }
        if (Log.isLoggable()) {
            Log.w(s, "handleNotificationsUpdateReceiver got strange action: " + action);
        }
        return IrisUtils$NotificationsListStatus.NO_MESSAGES;
    }
    
    public static void ifNotificationWasCanceledUpdateItsStatus(final Context context, final Intent intent, final String s) {
        final String stringExtra = intent.getStringExtra("swiped_notification_id");
        if (!StringUtils.isEmpty(stringExtra)) {
            Log.i(s, "saving swiped out notification id to preferences");
            PreferenceUtils.putStringPref(context, "notification_id_deleted_from_statusbar", stringExtra);
        }
    }
    
    public static void notifyOthersOfUnreadNotifications(final Context context, final boolean b, final boolean b2) {
        IrisUtils$NotificationsListStatus irisUtils$NotificationsListStatus;
        if (b) {
            irisUtils$NotificationsListStatus = IrisUtils$NotificationsListStatus.HAS_UNREAD_MESSAGES;
        }
        else if (b2) {
            irisUtils$NotificationsListStatus = IrisUtils$NotificationsListStatus.READ_ONLY;
        }
        else {
            irisUtils$NotificationsListStatus = IrisUtils$NotificationsListStatus.NO_MESSAGES;
        }
        if (Log.isLoggable()) {
            Log.i("SocialUtils", "notifyOthersOfUnreadNotifications: " + irisUtils$NotificationsListStatus);
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.BA_IRIS_NOTIFICATION_LIST_UPDATED");
        intent.putExtra("notifications_list_status", (Serializable)irisUtils$NotificationsListStatus);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        if (irisUtils$NotificationsListStatus != IrisUtils$NotificationsListStatus.HAS_UNREAD_MESSAGES) {
            removeNotificationsFromStatusBar(context);
        }
    }
    
    public static void removeNotificationsFromStatusBar(final Context context) {
        ((NotificationManager)context.getSystemService("notification")).cancel(1000);
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
        context.startActivity(Intent.createChooser(intent, (CharSequence)resources.getString(2131165702)));
        UserActionLogUtils.reportShareSheetActionEnded(context, IClientLogging$CompletionReason.success, null);
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
