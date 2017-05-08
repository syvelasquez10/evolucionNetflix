// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.ui.lolomo.PrefetchLolomoABTestUtils;
import com.netflix.mediaclient.ui.profiles.RestrictedProfilesReceiver;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.service.voip.VoipAuthorizationTokensUpdater;
import com.netflix.mediaclient.util.StatusUtils;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.util.PrivacyUtils;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.webapi.WebApiCommand;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.model.leafs.OnRampEligibility$Action;
import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.util.l10n.UserLocale;
import com.netflix.mediaclient.media.BookmarkStore;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import java.util.Iterator;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.NetflixPreference;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;
import com.netflix.mediaclient.util.log.SignInLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Intent;
import com.netflix.mediaclient.service.webclient.model.leafs.NrmConfigData;
import org.json.JSONException;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.util.ArrayList;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.webclient.model.leafs.User;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.javabridge.ui.Registration;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import com.netflix.mediaclient.javabridge.ui.DeviceAccount;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.msl.userauth.NetflixIdAuthenticationData;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.mediaclient.servicemgr.IMSLClient$MSLUserCredentialRegistry;

class UserAgent$9 implements IMSLClient$MSLUserCredentialRegistry
{
    final /* synthetic */ UserAgent this$0;
    
    UserAgent$9(final UserAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public UserAuthenticationData getUserAuthenticationData() {
        final String userId = this.getUserId();
        if (StringUtils.isEmpty(userId)) {
            Log.w("nf_service_useragent", "getMSLUserCredentialRegistry:: User profile is not known, not need for UserAuthenticationData");
            return null;
        }
        if (this.this$0.getMSLClient().isUserKnown(userId)) {
            Log.d("nf_service_useragent", "getMSLUserCredentialRegistry:: User profile is known to MSL, no need for UserAuthenticationData.");
            return null;
        }
        Log.d("nf_service_useragent", "getMSLUserCredentialRegistry:: User profile is NOT known to MSL, get cookies for UserAuthenticationData.");
        final String netflixID = this.this$0.getNetflixID();
        final String secureNetflixID = this.this$0.getSecureNetflixID();
        int n;
        if (StringUtils.isEmpty(netflixID)) {
            Log.e("nf_service_useragent", "getMSLUserCredentialRegistry:: Netflix ID is NOT known for profile %s even if it should be!", userId);
            n = 1;
        }
        else {
            n = 0;
        }
        if (StringUtils.isEmpty(secureNetflixID)) {
            Log.e("nf_service_useragent", "getMSLUserCredentialRegistry:: Secure Netflix ID is NOT known for profile %s even if it should be!", userId);
            n = 1;
        }
        if (n != 0) {
            return null;
        }
        Log.d("nf_service_useragent", "getMSLUserCredentialRegistry:: Authorization cookies known for profile %s", userId);
        return (UserAuthenticationData)new NetflixIdAuthenticationData(netflixID, secureNetflixID);
    }
    
    @Override
    public String getUserId() {
        if (this.this$0.mCurrentUserProfile != null && StringUtils.isNotEmpty(this.this$0.mCurrentUserProfile.getProfileGuid())) {
            Log.d("nf_service_useragent", "getMSLUserCredentialRegistry:: User profile is set %s", this.this$0.mCurrentUserProfile.getProfileGuid());
            return this.this$0.mCurrentUserProfile.getProfileGuid();
        }
        Log.w("nf_service_useragent", "getMSLUserCredentialRegistry:: User profile is not set.");
        return null;
    }
    
    @Override
    public void updateApiEndpointHost(final String s) {
    }
}
