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
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
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
import com.netflix.mediaclient.javabridge.ui.DeviceAccount;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import java.util.Iterator;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;

class UserAgent$5 extends SimpleUserAgentWebCallback
{
    final /* synthetic */ UserAgent this$0;
    
    UserAgent$5(final UserAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onUserProfilesUpdated(final AccountData accountData, final Status status) {
        if (Log.isLoggable()) {
            Log.v("nf_service_useragent", "onUserProfilesUpdated: " + status.getStatusCode());
        }
        if (status.isSucces() && accountData != null) {
            final List<UserProfile> userProfiles = accountData.getUserProfiles();
            if (Log.isLoggable()) {
                Log.d("nf_service_useragent", String.format("onUserProfilesUpdated got profiles: %d", userProfiles.size()));
            }
            this.this$0.mListOfUserProfiles = userProfiles;
            this.this$0.persistListOfUserProfiles(userProfiles);
            if (this.this$0.mCurrentUserProfile != null) {
                for (final UserProfile userProfile : userProfiles) {
                    if (StringUtils.safeEquals(this.this$0.mCurrentUserProfile.getProfileGuid(), userProfile.getProfileGuid())) {
                        this.this$0.checkCurrentProfileTypeWasChanged(userProfile);
                        this.this$0.mCurrentUserProfile = userProfile;
                        this.this$0.persistCurrentProfileGuid(this.this$0.mCurrentUserProfile);
                    }
                }
            }
            UserAgentBroadcastIntents.signalProfilesListUpdated(this.this$0.getContext());
            return;
        }
        Log.e("nf_service_useragent", "Updating user profiles failed with statusCode=" + status.getStatusCode());
    }
}
