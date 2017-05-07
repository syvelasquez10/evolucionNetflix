// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.service.logging.cmpevents.legacy.NotificationFeedback;
import com.netflix.mediaclient.service.pushnotification.UserFeedbackOnReceivedPushNotification;
import com.netflix.mediaclient.service.pushnotification.MessageData;
import com.netflix.mediaclient.servicemgr.model.user.UserProfile;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.CmpEventLogging;

public final class LegacyCmpEventLoggingImpl implements CmpEventLogging
{
    private static final String TAG = "nf_log";
    private Context mContext;
    private LoggingAgent mOwner;
    
    LegacyCmpEventLoggingImpl(final LoggingAgent mOwner, final Context mContext) {
        this.mOwner = mOwner;
        this.mContext = mContext;
    }
    
    private UserData getUserData() {
        final UserData userData = new UserData();
        userData.esn = this.mOwner.getConfigurationAgent().getEsnProvider().getEsn();
        userData.deviceCategory = this.mOwner.getConfigurationAgent().getDeviceCategory().getValue();
        final ServiceAgent.UserAgentInterface user = this.mOwner.getUser();
        UserData userData2;
        if (user == null) {
            Log.w("nf_log", "User agent should not be null here!");
            userData2 = null;
        }
        else {
            final UserCredentialRegistry userCredentialRegistry = user.getUserCredentialRegistry();
            if (userCredentialRegistry != null) {
                userData.netflixId = userCredentialRegistry.getNetflixID();
                userData.secureNetflixId = userCredentialRegistry.getSecureNetflixID();
            }
            userData.userId = this.mOwner.getUserId();
            final UserProfile currentProfile = user.getCurrentProfile();
            if (currentProfile != null) {
                userData.currentProfileUserId = currentProfile.getUserId();
            }
            userData.accountCountry = user.getReqCountry();
            userData.accountCountry = user.getGeoCountry();
            userData.languages = user.getLanguagesInCsv();
            userData2 = userData;
            if (Log.isLoggable("nf_log", 3)) {
                Log.d("nf_log", "GetUserData: " + userData);
                return userData;
            }
        }
        return userData2;
    }
    
    @Override
    public void reportUserFeedbackOnReceivedPushNotification(final MessageData messageData, final UserFeedbackOnReceivedPushNotification userFeedbackOnReceivedPushNotification) {
        final NotificationFeedback notificationFeedback = new NotificationFeedback(this.mContext, messageData, userFeedbackOnReceivedPushNotification, this.getUserData());
        Log.d("nf_log", "Execute feedback beacon...");
        new BackgroundTask().execute(notificationFeedback);
        Log.d("nf_log", "reportUserFeedbackOnReceivedPushNotification - Beacon send in background");
    }
}
