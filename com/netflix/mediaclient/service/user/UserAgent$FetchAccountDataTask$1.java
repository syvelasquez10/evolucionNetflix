// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.service.user.volley.FriendForRecommendation;
import java.util.Set;
import com.netflix.mediaclient.ui.profiles.RestrictedProfilesReceiver;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.util.StatusUtils;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.repository.UserLocale;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Intent;
import org.json.JSONException;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.util.ArrayList;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.service.webclient.model.leafs.User;
import com.netflix.mediaclient.javabridge.ui.Registration;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import com.netflix.mediaclient.javabridge.ui.DeviceAccount;
import com.netflix.mediaclient.javabridge.ui.EventListener;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import java.util.List;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;

class UserAgent$FetchAccountDataTask$1 extends SimpleUserAgentWebCallback
{
    final /* synthetic */ UserAgent$FetchAccountDataTask this$1;
    
    UserAgent$FetchAccountDataTask$1(final UserAgent$FetchAccountDataTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onAccountDataFetched(final AccountData accountData, final Status status) {
        if (status.isSucces()) {
            this.this$1.this$0.mListOfUserProfiles = accountData.getUserProfiles();
            this.this$1.this$0.mUser = accountData.getUser();
            this.this$1.this$0.mSubtitleDefaults = TextStyle.buildSubtitleSettings(this.this$1.this$0.mUser.getSubtitleDefaults());
            for (final UserProfile userProfile : this.this$1.this$0.mListOfUserProfiles) {
                if (Log.isLoggable()) {
                    Log.d("nf_service_useragent", String.format("fetchAccountData profileName %s profileId %s socialStatus %s", userProfile.getFirstName(), userProfile.getProfileGuid(), userProfile.isSocialConnected()));
                }
            }
            if (this.this$1.this$0.mUserAgentStateManager != null) {
                this.this$1.this$0.mUserAgentStateManager.accountDataFetched(accountData);
            }
            this.this$1.this$0.persistListOfUserProfiles(this.this$1.this$0.mListOfUserProfiles);
            this.this$1.this$0.persistUser(this.this$1.this$0.mUser);
        }
        else {
            if (Log.isLoggable()) {
                Log.e("nf_service_useragent", "fetchAccountData failed (skipping user info update) with statusCode=" + status.getStatusCode());
            }
            if (this.this$1.this$0.mUserAgentStateManager != null) {
                this.this$1.this$0.mUserAgentStateManager.accountDataFetchFailed(status, this.this$1.this$0.isAccountDataAvailable());
            }
        }
    }
}
