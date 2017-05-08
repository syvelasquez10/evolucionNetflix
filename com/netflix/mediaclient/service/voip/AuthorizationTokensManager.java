// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import com.netflix.mediaclient.service.webclient.model.leafs.VoipAuthorization;
import com.netflix.mediaclient.service.webclient.model.leafs.VoipAuthorizationData;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.servicemgr.IVoip$UserType;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import java.util.HashMap;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.IVoip$AuthorizationTokens;
import java.util.Map;

class AuthorizationTokensManager
{
    static final String APP_ID = "samurai";
    private static final String TAG = "nf_voip";
    private Map<String, IVoip$AuthorizationTokens> mAuthorizationTokensMap;
    private Context mContext;
    private ServiceAgent$UserAgentInterface mUserAgent;
    
    AuthorizationTokensManager(final Context mContext, final ServiceAgent$UserAgentInterface mUserAgent) {
        this.mAuthorizationTokensMap = new HashMap<String, IVoip$AuthorizationTokens>();
        if (mContext == null) {
            throw new IllegalStateException("Context can not be null!");
        }
        if (mUserAgent == null) {
            throw new IllegalStateException("User agent can not be null!");
        }
        this.mUserAgent = mUserAgent;
        this.mContext = mContext;
        this.loadDefaultTokens();
        this.loadRetrievedTokens();
    }
    
    private void dumpTokens() {
        if (Log.isLoggable()) {
            Log.d("nf_voip", "--------- Tokens map ------");
            for (final String s : this.mAuthorizationTokensMap.keySet()) {
                final IVoip$AuthorizationTokens voip$AuthorizationTokens = this.mAuthorizationTokensMap.get(s);
                Log.d("nf_voip", "Key: " + s);
                Log.d("nf_voip", "Entry: " + voip$AuthorizationTokens);
            }
            Log.d("nf_voip", "---------------------------");
        }
    }
    
    private IVoip$AuthorizationTokens getDefaultTokens() {
        final IVoip$AuthorizationTokens voip$AuthorizationTokens = this.mAuthorizationTokensMap.get(IVoip$UserType.CS_DEFAULT.name());
        if (voip$AuthorizationTokens == null) {
            Log.e("nf_voip", "Default tokens not found!");
        }
        else {
            Log.d("nf_voip", "Default tokens found!");
            if (voip$AuthorizationTokens.isExpired()) {
                Log.w("nf_voip", "Default tokens expired?");
                return voip$AuthorizationTokens;
            }
        }
        return voip$AuthorizationTokens;
    }
    
    private void loadDefaultTokens() {
        Log.d("nf_voip", "Load default tokens...");
        final IVoip$AuthorizationTokens defaultTokens = SecurityRepository.getDefaultTokens(this.mContext);
        if (defaultTokens != null) {
            this.mAuthorizationTokensMap.put(defaultTokens.getUserType().name(), defaultTokens);
            return;
        }
        Log.e("nf_voip", "Unable to find default tokens!");
    }
    
    private void loadRetrievedTokens() {
        final String stringPref = PreferenceUtils.getStringPref(this.mContext, "cusotmer_support_voip_authorizations", null);
        if (StringUtils.isEmpty(stringPref)) {
            Log.d("nf_voip", "User and non member tokens NOT found...");
            return;
        }
        while (true) {
            while (true) {
                try {
                    final JSONObject jsonObject = new JSONObject(stringPref);
                    if (jsonObject.has(IVoip$UserType.CS_MEMBER.name())) {
                        Log.d("nf_voip", "Member token found!");
                        this.mAuthorizationTokensMap.put(IVoip$UserType.CS_MEMBER.name(), new IVoip$AuthorizationTokens(jsonObject.getJSONObject(IVoip$UserType.CS_MEMBER.name())));
                        if (jsonObject.has(IVoip$UserType.CS_NON_MEMBER.name())) {
                            Log.d("nf_voip", "Non-member token found!");
                            this.mAuthorizationTokensMap.put(IVoip$UserType.CS_NON_MEMBER.name(), new IVoip$AuthorizationTokens(jsonObject.getJSONObject(IVoip$UserType.CS_NON_MEMBER.name())));
                            return;
                        }
                        break;
                    }
                }
                catch (JSONException ex) {
                    Log.e("nf_voip", "Failed to load user/non member auths from preferences.", (Throwable)ex);
                    return;
                }
                Log.d("nf_voip", "Member token NOT found!");
                continue;
            }
        }
        Log.d("nf_voip", "Non-member token NOT found!");
    }
    
    private void save() {
        while (true) {
            Log.d("nf_voip", "Save changes to preferences...");
            this.dumpTokens();
            while (true) {
                while (true) {
                    try {
                        final JSONObject jsonObject = new JSONObject();
                        final IVoip$AuthorizationTokens voip$AuthorizationTokens = this.mAuthorizationTokensMap.get(IVoip$UserType.CS_MEMBER.name());
                        if (voip$AuthorizationTokens != null) {
                            Log.d("nf_voip", "Member tokens saved to preferences...");
                            jsonObject.put(IVoip$UserType.CS_MEMBER.name(), (Object)voip$AuthorizationTokens.toJSon());
                        }
                        else {
                            Log.w("nf_voip", "Member tokens NOT found, unable to save!");
                        }
                        final IVoip$AuthorizationTokens voip$AuthorizationTokens2 = this.mAuthorizationTokensMap.get(IVoip$UserType.CS_NON_MEMBER.name());
                        if (voip$AuthorizationTokens2 != null) {
                            Log.d("nf_voip", "Non member tokens saved to preferences...");
                            jsonObject.put(IVoip$UserType.CS_NON_MEMBER.name(), (Object)voip$AuthorizationTokens2.toJSon());
                            PreferenceUtils.putStringPref(this.mContext, "cusotmer_support_voip_authorizations", jsonObject.toString());
                            Log.d("nf_voip", "Saved.");
                            return;
                        }
                    }
                    catch (JSONException ex) {
                        Log.e("nf_voip", "Failed to save to preferences...", (Throwable)ex);
                        continue;
                    }
                    break;
                }
                Log.w("nf_voip", "Non member tokens NOT found, unable to save!");
                continue;
            }
        }
    }
    
    public IVoip$AuthorizationTokens getAuthorizationTokens() {
        synchronized (this) {
            IVoip$AuthorizationTokens voip$AuthorizationTokens;
            if (this.mUserAgent != null && this.mUserAgent.isUserLoggedIn()) {
                Log.d("nf_voip", "User is logged in, use member authorization tokens if exist...");
                voip$AuthorizationTokens = this.mAuthorizationTokensMap.get(IVoip$UserType.CS_MEMBER.name());
            }
            else {
                Log.d("nf_voip", "User is NOT logged in, use non member authorization tokens if exist...");
                voip$AuthorizationTokens = this.mAuthorizationTokensMap.get(IVoip$UserType.CS_NON_MEMBER.name());
            }
            IVoip$AuthorizationTokens voip$AuthorizationTokens2 = voip$AuthorizationTokens;
            if (voip$AuthorizationTokens != null) {
                voip$AuthorizationTokens2 = voip$AuthorizationTokens;
                if (voip$AuthorizationTokens.isExpired()) {
                    Log.e("nf_voip", "Found tokens expired! Reset.");
                    voip$AuthorizationTokens2 = null;
                }
            }
            IVoip$AuthorizationTokens defaultTokens;
            if ((defaultTokens = voip$AuthorizationTokens2) == null) {
                Log.d("nf_voip", "Gets default authorization tokens!");
                defaultTokens = this.getDefaultTokens();
                if (defaultTokens == null) {
                    Log.e("nf_voip", "Default user authorization tokens not found!");
                }
                else {
                    Log.d("nf_voip", "Default user authorization tokens found.");
                }
            }
            if (Log.isLoggable()) {
                Log.d("nf_voip", "Using " + defaultTokens);
            }
            return defaultTokens;
        }
    }
    
    public boolean refreshAuthorizationTokens() {
        final IVoip$AuthorizationTokens voip$AuthorizationTokens = this.mAuthorizationTokensMap.get(IVoip$UserType.CS_NON_MEMBER.name());
        if (voip$AuthorizationTokens == null) {
            Log.d("nf_voip", "Non member token is not found, it needs to fetched...");
            return true;
        }
        if (voip$AuthorizationTokens.isExpired()) {
            Log.d("nf_voip", "Non member token is not found, it needs to fetched...");
            return true;
        }
        Log.d("nf_voip", "Non member token exist and it is not expired. No need to refresh!");
        return false;
    }
    
    public void removeUserTokens() {
        Log.d("nf_voip", "Remove VOIP user authorization data!");
        this.mAuthorizationTokensMap.remove(IVoip$UserType.CS_MEMBER.name());
        this.save();
    }
    
    public void updateAuthorizationData(final VoipAuthorizationData voipAuthorizationData) {
        // monitorenter(this)
        Label_0018: {
            if (voipAuthorizationData != null) {
                break Label_0018;
            }
        Label_0090_Outer:
            while (true) {
            Label_0090:
                while (true) {
                    while (true) {
                        try {
                            Log.w("nf_voip", "VOIP authorization data not found!");
                            return;
                            this.dumpTokens();
                            // iftrue(Label_0106:, voipAuthorizationData.getNonMemberVoipAuthorization() != null)
                            // iftrue(Label_0222:, voipAuthorizationData.getUserVoipAuthorization() != null)
                            Block_7: {
                                break Block_7;
                                while (true) {
                                    Log.w("nf_voip", "VOIP user authorization data not found!");
                                    this.mAuthorizationTokensMap.remove(IVoip$UserType.CS_MEMBER.name());
                                    break Label_0090;
                                    this.dumpTokens();
                                    continue Label_0090_Outer;
                                }
                                this.dumpTokens();
                                this.save();
                                return;
                            }
                            Log.w("nf_voip", "VOIP non member authorization data not found!");
                            this.mAuthorizationTokensMap.remove(IVoip$UserType.CS_NON_MEMBER.name());
                            continue;
                        }
                        finally {
                        }
                        // monitorexit(this)
                        Label_0106: {
                            if (Log.isLoggable()) {
                                Log.d("nf_voip", "VOIP non member authorization data found: " + voipAuthorizationData.getNonMemberVoipAuthorization());
                            }
                        }
                        final VoipAuthorization nonMemberVoipAuthorization = voipAuthorizationData.getNonMemberVoipAuthorization();
                        final IVoip$AuthorizationTokens voip$AuthorizationTokens = new IVoip$AuthorizationTokens(nonMemberVoipAuthorization.getUserToken(), nonMemberVoipAuthorization.getUserEncToken(), IVoip$UserType.CS_NON_MEMBER, nonMemberVoipAuthorization.getExpirationTime());
                        if (Log.isLoggable()) {
                            Log.d("nf_voip", "VOIP non member token created: " + voip$AuthorizationTokens);
                        }
                        this.mAuthorizationTokensMap.put(voip$AuthorizationTokens.getUserType().name(), voip$AuthorizationTokens);
                        continue;
                    }
                    Label_0222: {
                        if (Log.isLoggable()) {
                            Log.d("nf_voip", "VOIP user authorization data found: " + voipAuthorizationData.getUserVoipAuthorization());
                        }
                    }
                    final VoipAuthorization userVoipAuthorization = voipAuthorizationData.getUserVoipAuthorization();
                    final IVoip$AuthorizationTokens voip$AuthorizationTokens2 = new IVoip$AuthorizationTokens(userVoipAuthorization.getUserToken(), userVoipAuthorization.getUserEncToken(), IVoip$UserType.CS_MEMBER, userVoipAuthorization.getExpirationTime());
                    if (Log.isLoggable()) {
                        Log.d("nf_voip", "VOIP member token created: " + voip$AuthorizationTokens2);
                    }
                    this.mAuthorizationTokensMap.put(voip$AuthorizationTokens2.getUserType().name(), voip$AuthorizationTokens2);
                    continue Label_0090;
                }
            }
        }
    }
}
