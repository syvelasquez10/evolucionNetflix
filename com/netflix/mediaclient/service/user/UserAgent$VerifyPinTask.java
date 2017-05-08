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
import com.netflix.mediaclient.servicemgr.IMSLClient$MSLUserCredentialRegistry;
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
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.NetflixService;
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
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.util.PreferenceUtils;

class UserAgent$VerifyPinTask extends UserAgent$FetchTask<Void>
{
    final String enteredPin;
    final /* synthetic */ UserAgent this$0;
    private final UserAgentWebCallback webClientCallback;
    
    public UserAgent$VerifyPinTask(final UserAgent this$0, final String enteredPin, final UserAgent$UserAgentCallback userAgent$UserAgentCallback) {
        this.this$0 = this$0;
        super(userAgent$UserAgentCallback);
        this.webClientCallback = new UserAgent$VerifyPinTask$1(this);
        this.enteredPin = enteredPin;
    }
    
    private boolean userEnteredPinMatchesStoredPin() {
        final String stringPref = PreferenceUtils.getStringPref(this.this$0.getContext(), "prefs_last_successful_user_pin", "");
        return !stringPref.isEmpty() && this.enteredPin.equals(stringPref);
    }
    
    @Override
    public void run() {
        if (ConnectivityUtils.isConnected(this.this$0.getContext())) {
            this.this$0.mUserWebClient.verifyPin(this.enteredPin, this.webClientCallback);
            return;
        }
        if (this.userEnteredPinMatchesStoredPin()) {
            this.webClientCallback.onVerified(true, CommonStatus.OK);
            return;
        }
        this.webClientCallback.onVerified(false, CommonStatus.OK);
    }
}
