// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.ui.lolomo.PrefetchLolomoABTestUtils;
import com.netflix.mediaclient.ui.profiles.RestrictedProfilesReceiver;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.service.voip.VoipAuthorizationTokensUpdater;
import com.netflix.mediaclient.util.PrivacyUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.util.l10n.UserLocale;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import java.util.Iterator;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.NetflixPreference;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;
import com.netflix.mediaclient.util.log.SignInLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.content.Context;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Intent;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.util.ArrayList;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.util.StringUtils;
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
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import org.json.JSONException;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.util.StatusUtils;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.SignInData;
import com.netflix.mediaclient.service.configuration.SimpleConfigurationAgentWebCallback;

class UserAgent$1 extends SimpleConfigurationAgentWebCallback
{
    final /* synthetic */ UserAgent this$0;
    final /* synthetic */ UserAgent$UserAgentCallback val$userAgentCallback;
    
    UserAgent$1(final UserAgent this$0, final UserAgent$UserAgentCallback val$userAgentCallback) {
        this.this$0 = this$0;
        this.val$userAgentCallback = val$userAgentCallback;
    }
    
    @Override
    public void onLoginVerified(final SignInData signInData, final Status status) {
        if (status.isError() || signInData == null || !signInData.isSignInSuccessful()) {
            if (Log.isLoggable() && signInData != null) {
                Log.d("nf_service_useragent", String.format("fail: signInData:%s, retry?:%b, trySignUp:%b", signInData, signInData.shouldRetrySignIn(), signInData.shouldTrySignUp()));
            }
            if (signInData != null && signInData.shouldTrySignUp()) {
                this.this$0.getApplication().clearSignedInOnce();
                this.this$0.notifyLoginComplete(StatusUtils.createStatus(StatusCode.USER_SIGNIN_FAILURE, "UserAgent: activateLoginViaDynecom fails", true, RootCause.clientFailure));
                return;
            }
            this.this$0.notifyLoginComplete(StatusUtils.createStatus(StatusCode.USER_SIGNIN_RETRY, "UserAgent: activateLoginViaDynecom fails", true, RootCause.clientFailure));
        }
        else {
            try {
                this.this$0.tokenActivate(new ActivationTokens(this.this$0.mDynecomNetflixId, this.this$0.mDynecomSecureNetflixId), this.val$userAgentCallback);
            }
            catch (JSONException ex) {
                Log.e("nf_service_useragent", "error creating activationTokesn", (Throwable)ex);
                this.this$0.notifyLoginComplete(StatusUtils.createStatus(StatusCode.NRD_ERROR, "UserAgent: activateLoginViaDynecom fails", true, RootCause.clientFailure));
            }
        }
    }
}
