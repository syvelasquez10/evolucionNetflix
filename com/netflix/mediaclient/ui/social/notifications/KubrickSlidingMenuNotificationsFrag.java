// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

public class KubrickSlidingMenuNotificationsFrag extends NotificationsFrag
{
    private static final int MAX_NUM_NOTIFICATIONS = 4;
    
    @Override
    protected boolean canLoadMultiplePages() {
        return false;
    }
    
    @Override
    protected int computeRowCount() {
        if (this.isLoadingData()) {
            return 0;
        }
        return super.computeRowCount();
    }
    
    @Override
    protected int getNumNotificationsPerPage() {
        return 4;
    }
    
    @Override
    protected int getRowLayoutResourceId() {
        return 2130903112;
    }
    
    @Override
    protected boolean isListViewStatic() {
        return true;
    }
    
    @Override
    protected boolean shouldShowPlayButtonFromNotifications() {
        return false;
    }
}
