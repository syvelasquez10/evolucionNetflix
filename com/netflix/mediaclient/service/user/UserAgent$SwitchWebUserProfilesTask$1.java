// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.Set;
import com.netflix.mediaclient.ui.profiles.RestrictedProfilesReceiver;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.util.StatusUtils;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.repository.UserLocale;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import java.util.Iterator;
import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.ui.profiles.ProfileSelectionActivity;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Intent;
import org.json.JSONException;
import org.json.JSONTokener;
import org.json.JSONArray;
import java.util.ArrayList;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.User;
import com.netflix.mediaclient.service.player.subtitles.TextStyle;
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
import com.netflix.mediaclient.util.SocialNotificationsUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.UserBoundCookies;

class UserAgent$SwitchWebUserProfilesTask$1 extends SimpleUserAgentWebCallback
{
    final /* synthetic */ UserAgent$SwitchWebUserProfilesTask this$1;
    
    UserAgent$SwitchWebUserProfilesTask$1(final UserAgent$SwitchWebUserProfilesTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onUserProfileSwitched(final UserBoundCookies userBoundCookies, final Status status) {
        if (status.isSucces()) {
            if (Log.isLoggable("nf_service_useragent", 3)) {
                Log.d("nf_service_useragent", String.format("switchWebUserProfile  netflixId %s secureNetflixId %s", userBoundCookies.getUserBoundNetflixId(), userBoundCookies.getUserBoundSecureNetflixId()));
            }
            if (this.this$1.this$0.mUserAgentStateManager != null) {
                this.this$1.this$0.mUserAgentStateManager.profileSwitched(userBoundCookies);
            }
            SocialNotificationsUtils.removeSocialNotificationsFromStatusBar(this.this$1.this$0.getContext());
        }
        else {
            if (Log.isLoggable("nf_service_useragent", 6)) {
                Log.e("nf_service_useragent", "switchWebUserProfile failed  with statusCode=" + status.getStatusCode());
            }
            if (this.this$1.this$0.mUserAgentStateManager != null) {
                this.this$1.this$0.mUserAgentStateManager.profileSwitchedFailed(status);
            }
        }
    }
}
