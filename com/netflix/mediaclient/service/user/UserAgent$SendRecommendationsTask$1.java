// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user;

import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.ui.profiles.RestrictedProfilesReceiver;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.util.StatusUtils;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.repository.UserLocale;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
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
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import android.widget.Toast;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.social.FriendForRecommendation;
import java.util.Set;

class UserAgent$SendRecommendationsTask$1 extends SimpleUserAgentWebCallback
{
    final /* synthetic */ UserAgent$SendRecommendationsTask this$1;
    
    UserAgent$SendRecommendationsTask$1(final UserAgent$SendRecommendationsTask this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void onRecommendationsSent(final Set<FriendForRecommendation> set, final Status status) {
        Label_0182: {
            if (!status.isSucces() || set == null || set.size() <= 0) {
                break Label_0182;
            }
            Object firstName = null;
            final Iterator<FriendForRecommendation> iterator = set.iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final FriendForRecommendation friendForRecommendation = iterator.next();
                if (friendForRecommendation.wasRecommended()) {
                    if (firstName == null) {
                        firstName = friendForRecommendation.getFriendProfile().getFirstName();
                    }
                    else {
                        ++n;
                    }
                }
            }
            if (firstName == null) {
                break Label_0182;
            }
            String s;
            if (n == 0) {
                s = this.this$1.this$0.getContext().getString(2131493334, new Object[] { firstName });
            }
            else {
                s = this.this$1.this$0.getContext().getResources().getQuantityString(2131623938, n, new Object[] { n, firstName });
            }
            Toast.makeText(this.this$1.this$0.getContext(), (CharSequence)s, 1).show();
            return;
        }
        if (Log.isLoggable("nf_service_useragent", 6)) {
            Log.e("nf_service_useragent", "Problem occured trying to send recommendations! Result: " + status + "; Friends: " + set);
        }
    }
}
