// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iris.notifications;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.android.widget.NetflixActionBar$LogoType;
import android.os.Bundle;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.app.Fragment;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.Intent;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class NotificationsActivity extends FragmentHostActivity
{
    private static final String NOTIFICATION_BEACON_SENT = "notification_beacon_sent";
    public static final String NOTIFICATION_IRIS = "com.netflix.mediaclient.intent.action.NOTIFICATION_IRIS";
    private static final String TAG;
    private boolean mManagerIsReady;
    private boolean mNotificationOpenedReportAlreadySent;
    
    static {
        TAG = NotificationsActivity.class.getSimpleName();
    }
    
    public static Intent getIntent(final MessageData messageData) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.NOTIFICATION_IRIS");
        MessageData.addMessageDataToIntent(intent, messageData);
        return intent;
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new NotificationsActivity$1(this);
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        return new NotificationsFrag();
    }
    
    @Override
    protected int getContentLayoutId() {
        return 2130903128;
    }
    
    @Override
    public IClientLogging$ModalView getUiScreen() {
        return IClientLogging$ModalView.socialNotifications;
    }
    
    @Override
    public boolean isLoadingData() {
        return this.mManagerIsReady && this.getPrimaryFrag() != null && ((NetflixFrag)this.getPrimaryFrag()).isLoadingData();
    }
    
    @Override
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        final NetflixActionBar netflixActionBar = this.getNetflixActionBar();
        if (netflixActionBar != null) {
            netflixActionBar.setLogoType(NetflixActionBar$LogoType.GONE);
            netflixActionBar.setTitle(this.getResources().getString(2131231238));
        }
        if (bundle != null) {
            this.mNotificationOpenedReportAlreadySent = bundle.getBoolean("notification_beacon_sent");
        }
    }
    
    @Override
    protected void onNewIntent(final Intent intent) {
        Log.d(NotificationsActivity.TAG, "onNewIntent: ", intent);
        super.onNewIntent(intent);
        this.setIntent(intent);
    }
    
    @Override
    protected void onSaveInstanceState(final Bundle bundle) {
        bundle.putBoolean("notification_beacon_sent", this.mNotificationOpenedReportAlreadySent);
        super.onSaveInstanceState(bundle);
    }
    
    @Override
    public boolean showAboutInMenu() {
        return false;
    }
    
    @Override
    public boolean showSettingsInMenu() {
        return false;
    }
    
    @Override
    public boolean showSignOutInMenu() {
        return false;
    }
}
