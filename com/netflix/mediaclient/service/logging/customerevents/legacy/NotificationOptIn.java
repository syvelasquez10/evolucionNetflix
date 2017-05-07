// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerevents.legacy;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.webapi.AuthorizationCredentials;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.webapi.CommonRequestParameters;
import com.netflix.mediaclient.service.logging.UserData;
import android.content.Context;

public class NotificationOptIn implements Runnable
{
    private static final String TAG = "nf_rest";
    private Context mContext;
    private String mDeviceToken;
    private boolean mOptIn;
    private UserData mUser;
    
    public NotificationOptIn(final Context mContext, final boolean mOptIn, final String mDeviceToken, final UserData mUser) {
        this.mContext = mContext;
        this.mOptIn = mOptIn;
        this.mDeviceToken = mDeviceToken;
        this.mUser = mUser;
    }
    
    protected CommonRequestParameters getCommonRequestParameters(final Context context, final UserData userData) {
        final CommonRequestParameters instanceWithCredentials = CommonRequestParameters.getInstanceWithCredentials();
        instanceWithCredentials.userId = userData.currentProfileUserId;
        instanceWithCredentials.osVersion = String.valueOf(AndroidUtils.getAndroidVersion());
        instanceWithCredentials.deviceCategory = this.mUser.deviceCategory;
        instanceWithCredentials.appVersion = AndroidManifestUtils.getVersion(context);
        instanceWithCredentials.uiVersion = instanceWithCredentials.appVersion;
        instanceWithCredentials.country = userData.accountCountry;
        instanceWithCredentials.geolocationCountry = userData.geoLocationCountry;
        instanceWithCredentials.languages = userData.languages;
        return instanceWithCredentials;
    }
    
    @Override
    public void run() {
        try {
            final AuthorizationCredentials authorizationCredentials = new AuthorizationCredentials(this.mUser.netflixId, this.mUser.secureNetflixId);
            final CustomerEventCommand customerEventCommand = new CustomerEventCommand(this.mUser.esn, authorizationCredentials, new PushNotificationOptInStatus(this.mUser.esn, this.getCommonRequestParameters(this.mContext, this.mUser), this.mDeviceToken, this.mOptIn, true, authorizationCredentials).toString());
            Log.d("nf_rest", "Executing NotificationOptInCommand WebAPI call start");
            final String execute = customerEventCommand.execute();
            Log.d("nf_rest", "Executing NotificationOptInCommand WebAPI call done");
            Log.d("nf_rest", "NotificationOptInCommand response: " + execute);
        }
        catch (Throwable t) {
            Log.e("nf_rest", "Failed to execute both WebAPI call!", t);
        }
    }
}
