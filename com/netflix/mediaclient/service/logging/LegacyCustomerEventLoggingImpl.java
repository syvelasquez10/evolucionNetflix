// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.service.logging.customerevents.legacy.NotificationOptIn;
import com.netflix.mediaclient.service.logging.customerevents.legacy.MdpFromDeepLink;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.service.logging.customerevents.legacy.LaunchedFromDeepLink;
import com.netflix.mediaclient.Log;
import android.os.Handler;
import com.netflix.mediaclient.service.logging.customerevents.legacy.MdxLoggingManager;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.CustomerEventLogging;

public final class LegacyCustomerEventLoggingImpl implements CustomerEventLogging
{
    private static final String TAG = "nf_log";
    private Context mContext;
    private MdxLoggingManager mMdxLoggingManager;
    private LoggingAgent mOwner;
    private Handler mWorkerHandler;
    
    LegacyCustomerEventLoggingImpl(final LoggingAgent mOwner, final Context mContext, final Handler mWorkerHandler) {
        this.mOwner = mOwner;
        this.mContext = mContext;
        this.mWorkerHandler = mWorkerHandler;
        this.mMdxLoggingManager = new MdxLoggingManager(this.mWorkerHandler, this.mOwner);
    }
    
    private UserData getUserData() {
        final UserData userData = new UserData();
        userData.esn = this.mOwner.getConfigurationAgent().getEsnProvider().getEsn();
        userData.deviceCategory = this.mOwner.getConfigurationAgent().getDeviceCategory().getValue();
        userData.netflixId = this.mOwner.getUser().getUserCredentialRegistry().getNetflixID();
        userData.secureNetflixId = this.mOwner.getUser().getUserCredentialRegistry().getSecureNetflixID();
        userData.userId = this.mOwner.getUserId();
        if (this.mOwner.getUser().getCurrentProfile() != null) {
            userData.currentProfileUserId = this.mOwner.getUser().getCurrentProfile().getUserId();
        }
        userData.accountCountry = this.mOwner.getUser().getReqCountry();
        userData.accountCountry = this.mOwner.getUser().getGeoCountry();
        userData.languages = this.mOwner.getUser().getLanguagesInCsv();
        if (Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "GetUserData: " + userData);
        }
        return userData;
    }
    
    @Override
    public void logMdxPlaybackStart(final String s, final String s2, final String s3, final int n) {
        this.mMdxLoggingManager.logPlaybackStart(s, s2, s3, n, this.mOwner.getUser());
    }
    
    @Override
    public void logMdxTarget(final String s, final String s2, final String s3, final String s4) {
        this.mMdxLoggingManager.logTarget(s, s2, s3, s4, this.mOwner.getUser());
    }
    
    @Override
    public void logMdxTargetSelection(final String s) {
        this.mMdxLoggingManager.logTargetSelection(s, this.mOwner.getUser());
    }
    
    @Override
    public void reportApplicationLaunchedFromDeepLinking(final String s, final String s2) {
        final LaunchedFromDeepLink launchedFromDeepLink = new LaunchedFromDeepLink(this.mContext, this.getUserData(), s, s2);
        Log.d("nf_log", "Execute reportApplicationLaunchFromDeepLinking beacon...");
        new BackgroundTask().execute(launchedFromDeepLink);
        Log.d("nf_log", "Beacon send in background");
    }
    
    @Override
    public void reportMdpFromDeepLinking() {
        final MdpFromDeepLink mdpFromDeepLink = new MdpFromDeepLink(this.mContext, this.getUserData());
        Log.d("nf_log", "Execute reportMdpFromDeepLinking beacon...");
        new BackgroundTask().execute(mdpFromDeepLink);
        Log.d("nf_log", "Beacon send in background");
    }
    
    @Override
    public void reportNotificationOptIn(final boolean b, final String s) {
        final NotificationOptIn notificationOptIn = new NotificationOptIn(this.mContext, b, s, this.getUserData());
        Log.d("nf_log", "Execute opt in beacon...");
        new BackgroundTask().execute(notificationOptIn);
        Log.d("nf_log", "Beacon send in background");
    }
}
