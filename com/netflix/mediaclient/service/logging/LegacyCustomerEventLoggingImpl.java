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
    private static final String TAG_PUSH = "nf_push";
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
            userData.currentProfileToken = this.mOwner.getUser().getCurrentProfile().getUserId();
            userData.currentProfileGuid = this.mOwner.getUser().getCurrentProfile().getProfileId();
        }
        userData.accountCountry = this.mOwner.getUser().getReqCountry();
        userData.accountCountry = this.mOwner.getUser().getGeoCountry();
        userData.languages = this.mOwner.getUser().getLanguagesInCsv();
        if (Log.isLoggable("nf_log", 3)) {}
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
    public void reportApplicationLaunchedFromDeepLinking(final String s, final String s2, final String s3) {
        final LaunchedFromDeepLink launchedFromDeepLink = new LaunchedFromDeepLink(this.mContext, this.getUserData(), s, s2, s3);
        Log.d("nf_log", "Execute reportApplicationLaunchFromDeepLinking beacon...");
        new BackgroundTask().execute(launchedFromDeepLink);
        Log.d("nf_log", "reportApplicationLaunchedFromDeepLinking - Beacon send in background");
    }
    
    @Override
    public void reportMdpFromDeepLinking(final String s) {
        final MdpFromDeepLink mdpFromDeepLink = new MdpFromDeepLink(this.mContext, this.getUserData(), s);
        Log.d("nf_log", "Execute reportMdpFromDeepLinking beacon...");
        new BackgroundTask().execute(mdpFromDeepLink);
        Log.d("nf_log", "reportMdpFromDeepLinking - Beacon send in background");
    }
    
    @Override
    public void reportNotificationOptIn(final boolean b, final boolean b2, final String s) {
        final NotificationOptIn notificationOptIn = new NotificationOptIn(this.mContext, b, b2, s, this.getUserData());
        Log.d("nf_push", String.format("Execute opt in beacon... %s", notificationOptIn));
        new BackgroundTask().execute(notificationOptIn);
        Log.d("nf_push", "reportNotificationOptIn - Beacon send in background");
    }
}
