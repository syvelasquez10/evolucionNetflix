// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.ui.profiles.RestrictedProfilesReceiver;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.service.voip.VoipAuthorizationTokensUpdater;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
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
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.SignInLogging$SignInType;
import com.netflix.mediaclient.util.log.SignInLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
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
import com.netflix.mediaclient.service.NetflixService;
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
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.util.StatusUtils;
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
                final String cookies = activateEvent.getCookies();
                final String access$900 = this.this$0.extractToken(this.this$0.getNetflixIdName() + "=", cookies);
                final String access$901 = this.this$0.extractToken(this.this$0.getSecureNetflixIdName() + "=", cookies);
                if (StringUtils.isNotEmpty(access$900) && StringUtils.isNotEmpty(access$901)) {
                    this.this$0.mUserAgentStateManager.accountOrProfileActivated(true, access$900, access$901);
                }
            }
            else {
                if (activateEvent.isActionId()) {
                    this.this$0.mUserAgentStateManager.accountOrProfileActivated(false, null, null);
                    if (Log.isLoggable()) {
                        Log.d("nf_service_useragent", "Received a activate event with ActionID error: " + activateEvent.getActionID() + " Received msg " + activateEvent.getMessage());
                    }
                    final NetflixStatus actionIdResult = StatusUtils.toActionIdResult(activateEvent);
                    this.this$0.notifyLoginComplete(actionIdResult);
                    UserAgentBroadcastIntents.signalProfileSelectionResult(this.this$0.getContext(), actionIdResult.getStatusCode().getValue(), null);
                    return;
                }
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
