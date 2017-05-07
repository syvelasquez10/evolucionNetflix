// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.social.notifications;

import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.os.Bundle;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.app.Fragment;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import android.content.Intent;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;

public class SocialNotificationsActivity extends FragmentHostActivity
{
    public static final String NOTIFICATION_SOCIAL = "com.netflix.mediaclient.intent.action.NOTIFICATION_SOCIAL";
    private static final String TAG;
    private boolean mManagerIsReady;
    
    static {
        TAG = SocialNotificationsActivity.class.getSimpleName();
    }
    
    public static Intent getIntent(final MessageData messageData) {
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.NOTIFICATION_SOCIAL");
        MessageData.addMessageDataToIntent(intent, messageData);
        return intent;
    }
    
    @Override
    protected ManagerStatusListener createManagerStatusListener() {
        return new ManagerStatusListener() {
            @Override
            public void onManagerReady(final ServiceManager serviceManager, final Status status) {
                Log.d(SocialNotificationsActivity.TAG, "Manager is here!");
                ((ManagerStatusListener)SocialNotificationsActivity.this.getPrimaryFrag()).onManagerReady(serviceManager, status);
                SocialNotificationsActivity.this.mManagerIsReady = true;
                NflxProtocolUtils.reportUserOpenedNotification(serviceManager, SocialNotificationsActivity.this.getIntent());
            }
            
            @Override
            public void onManagerUnavailable(final ServiceManager serviceManager, final Status status) {
                Log.d(SocialNotificationsActivity.TAG, "Manager isn't available!");
                ((ManagerStatusListener)SocialNotificationsActivity.this.getPrimaryFrag()).onManagerUnavailable(serviceManager, status);
            }
        };
    }
    
    @Override
    protected Fragment createPrimaryFrag() {
        return new SocialNotificationsFrag();
    }
    
    @Override
    protected int getContentLayoutId() {
        return 2130903094;
    }
    
    @Override
    public IClientLogging.ModalView getUiScreen() {
        return IClientLogging.ModalView.socialNotifications;
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
            netflixActionBar.setTitle(this.getResources().getString(2131493405));
            netflixActionBar.setLogoType(NetflixActionBar.LogoType.GONE);
        }
    }
    
    protected void onNewIntent(final Intent intent) {
        Log.d(SocialNotificationsActivity.TAG, "onNewIntent: ", intent);
        super.onNewIntent(intent);
        this.setIntent(intent);
    }
    
    @Override
    protected boolean showAboutInMenu() {
        return false;
    }
    
    @Override
    protected boolean showSettingsInMenu() {
        return false;
    }
    
    @Override
    protected boolean showSignOutInMenu() {
        return false;
    }
}
