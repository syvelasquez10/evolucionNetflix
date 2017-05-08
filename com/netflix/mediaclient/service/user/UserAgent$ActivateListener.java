// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.ui.verifyplay.PinVerifier$PinType;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.ui.lolomo.PrefetchLolomoABTestUtils;
import com.netflix.mediaclient.ui.profiles.RestrictedProfilesReceiver;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.configuration.PersistentConfig;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.service.voip.VoipAuthorizationTokensUpdater;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.util.PrivacyUtils;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ThumbMessaging;
import com.netflix.mediaclient.webapi.WebApiCommand;
import com.netflix.mediaclient.servicemgr.IMSLClient$MSLUserCredentialRegistry;
import com.netflix.mediaclient.service.webclient.model.leafs.EogAlert;
import com.netflix.model.leafs.OnRampEligibility$Action;
import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.util.l10n.UserLocale;
import com.netflix.mediaclient.service.webclient.model.leafs.UmaAlert;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import java.util.Iterator;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.NetflixPreference;
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
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.service.webclient.model.leafs.User;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import com.netflix.mediaclient.javabridge.ui.Registration;
import com.netflix.mediaclient.javabridge.ui.Nrdp;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import com.netflix.mediaclient.javabridge.ui.DeviceAccount;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.webclient.UserCredentialRegistry;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.StatusUtils;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.error.crypto.ErrorSource;
import com.netflix.mediaclient.service.error.crypto.CryptoErrorManager;
import com.netflix.mediaclient.service.error.crypto.NtbaCdmProvisionFailedErrorHandler;
import com.netflix.mediaclient.service.error.ErrorDescriptor;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.event.nrdp.registration.ActivateEvent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.event.UIEvent;
import com.netflix.mediaclient.javabridge.ui.EventListener;

class UserAgent$ActivateListener implements EventListener
{
    final /* synthetic */ UserAgent this$0;
    
    private UserAgent$ActivateListener(final UserAgent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void received(final UIEvent uiEvent) {
        Log.d("nf_service_useragent", "Received a activate event ");
        if (uiEvent instanceof ActivateEvent) {
            final ActivateEvent activateEvent = (ActivateEvent)uiEvent;
            if (!activateEvent.failed()) {
                Log.d("nf_service_useragent", "Received a success activate event: %s", activateEvent);
                final String cookies = activateEvent.getCookies();
                final String access$1000 = this.this$0.extractToken(this.this$0.getNetflixIdName() + "=", cookies);
                final String access$1001 = this.this$0.extractToken(this.this$0.getSecureNetflixIdName() + "=", cookies);
                if (StringUtils.isNotEmpty(access$1000) && StringUtils.isNotEmpty(access$1001)) {
                    this.this$0.mUserAgentStateManager.accountOrProfileActivated(true, access$1000, access$1001);
                }
            }
            else if (activateEvent.isActionId()) {
                Log.d("nf_service_useragent", "Received an activate event with ActionID error: %s", activateEvent);
                if (BlacklistedWidevinePluginErrorDescriptor.canHandle(activateEvent)) {
                    Log.d("nf_service_useragent", "Action ID 1 and reason code 15003: blacklisted Widevine L3 plugin, report an error");
                    this.this$0.getErrorHandler().addError(new BlacklistedWidevinePluginErrorDescriptor(this.this$0.getContext()));
                    return;
                }
                if (NtbaCdmProvisionFailedErrorHandler.canHandle(activateEvent)) {
                    Log.d("nf_service_useragent", "Action ID 1 and reason code 15001 and transaction is CDM provision: initiate crypto error workflow");
                    CryptoErrorManager.INSTANCE.mediaDrmFailure(ErrorSource.ntba, StatusCode.DRM_FAILURE_CDM, new IllegalStateException("Action ID 1 and reason code 15001 and transaction is CDM provision"));
                    return;
                }
                this.this$0.mUserAgentStateManager.accountOrProfileActivated(false, null, null);
                final NetflixStatus actionIdResult = StatusUtils.toActionIdResult(activateEvent);
                this.this$0.notifyLoginComplete(actionIdResult);
                UserAgentBroadcastIntents.signalProfileSelectionResult(this.this$0.getContext(), actionIdResult.getStatusCode().getValue(), null);
            }
            else {
                if (activateEvent.isNetworkError()) {
                    Log.d("nf_service_useragent", "Received a activate event with Network error");
                    final NetflixStatus actionIdResult2 = StatusUtils.toActionIdResult(activateEvent);
                    actionIdResult2.setDisplayMessage(true);
                    actionIdResult2.setMessage(activateEvent.getMessage());
                    this.this$0.mUserAgentStateManager.accountOrProfileActivated(false, null, null);
                    this.this$0.notifyLoginComplete(actionIdResult2);
                    UserAgentBroadcastIntents.signalProfileSelectionResult(this.this$0.getContext(), StatusCode.NETWORK_ERROR.getValue(), null);
                    return;
                }
                Log.e("nf_service_useragent", "Received a unexpected Activate event");
            }
        }
    }
}
