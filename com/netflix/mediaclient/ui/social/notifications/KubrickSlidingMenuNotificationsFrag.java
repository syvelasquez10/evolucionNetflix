// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.app.Activity;

public class KubrickSlidingMenuNotificationsFrag extends NotificationsFrag
{
    private static int MAX_NUM_NOTIFICATIONS = 0;
    private static final int MAX_NUM_NOTIFICATIONS_DEFAULT = 3;
    private static final int MAX_NUM_NOTIFICATIONS_X_LARGE = 5;
    
    static {
        KubrickSlidingMenuNotificationsFrag.MAX_NUM_NOTIFICATIONS = 3;
    }
    
    public static int getCurrentMaxNotificationsNum() {
        return KubrickSlidingMenuNotificationsFrag.MAX_NUM_NOTIFICATIONS;
    }
    
    @Override
    protected boolean canLoadMultiplePages() {
        return false;
    }
    
    @Override
    protected int getNumNotificationsPerPage() {
        return KubrickSlidingMenuNotificationsFrag.MAX_NUM_NOTIFICATIONS;
    }
    
    @Override
    protected int getRowLayoutResourceId() {
        return 2130903115;
    }
    
    @Override
    protected boolean isListViewStatic() {
        return true;
    }
    
    @Override
    public void onAttach(final Activity activity) {
        super.onAttach(activity);
        if (DeviceUtils.getScreenSizeCategory((Context)activity) >= 4) {
            KubrickSlidingMenuNotificationsFrag.MAX_NUM_NOTIFICATIONS = 5;
        }
    }
    
    @Override
    protected boolean shouldShowPlayButtonFromNotifications() {
        return false;
    }
}
