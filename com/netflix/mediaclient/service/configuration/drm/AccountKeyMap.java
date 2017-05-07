// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.drm;

import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import org.json.JSONObject;

class AccountKeyMap
{
    private static final String CKE_KID_MAPKEY = "kceKeyId";
    private static final String CKH_KID_MAPKEY = "kchKeyId";
    private static final String CURRENT_ACCOUNT = "currentAccount";
    private static final String KSID_MAPKEY = "keySetId";
    private static final String TAG;
    private JSONObject mAccountKeyMap;
    private Context mContext;
    private KeyIds mKeyIdsFromLegacy;
    
    static {
        TAG = AccountKeyMap.class.getSimpleName();
    }
    
    AccountKeyMap(final Context mContext) {
        this.mContext = mContext;
        final String stringPref = PreferenceUtils.getStringPref(this.mContext, "nf_drm_acckeymap", null);
        if (Log.isLoggable(AccountKeyMap.TAG, 3)) {
            Log.d(AccountKeyMap.TAG, "has json " + stringPref);
        }
        if (stringPref == null) {
            this.mAccountKeyMap = new JSONObject();
            this.buildKeyIdsMapFromLegacy();
            return;
        }
        try {
            this.mAccountKeyMap = new JSONObject(stringPref);
        }
        catch (JSONException ex) {
            this.mAccountKeyMap = new JSONObject();
        }
    }
    
    private void buildKeyIdsMapFromLegacy() {
        final String stringPref = PreferenceUtils.getStringPref(this.mContext, "nf_drm_cdm_keyset_id", null);
        final String stringPref2 = PreferenceUtils.getStringPref(this.mContext, "nf_drm_kce_key_id", null);
        final String stringPref3 = PreferenceUtils.getStringPref(this.mContext, "nf_drm_kch_key_id", null);
        if (Log.isLoggable(AccountKeyMap.TAG, 3)) {
            Log.d(AccountKeyMap.TAG, "has legacy ksid [" + stringPref + "], kce_id [" + stringPref2 + "], kch_id [" + stringPref3 + "]");
        }
        if (StringUtils.isNotEmpty(stringPref) && StringUtils.isNotEmpty(stringPref2) && StringUtils.isNotEmpty(stringPref3)) {
            this.mKeyIdsFromLegacy = new KeyIds(stringPref, stringPref2, stringPref3);
            PreferenceUtils.removePref(this.mContext, "nf_drm_cdm_keyset_id");
            PreferenceUtils.removePref(this.mContext, "nf_drm_kce_key_id");
            PreferenceUtils.removePref(this.mContext, "nf_drm_kch_key_id");
        }
    }
    
    private void saveToPreference() {
        if (Log.isLoggable(AccountKeyMap.TAG, 3)) {
            Log.d(AccountKeyMap.TAG, "saveToPreference " + this.mAccountKeyMap.toString());
        }
        PreferenceUtils.putStringPref(this.mContext, "nf_drm_acckeymap", this.mAccountKeyMap.toString());
    }
    
    void addCurrentKeyIds(final String s, final String s2, final String s3) {
        while (true) {
            try {
                final String optString = this.mAccountKeyMap.optString("currentAccount");
                if (StringUtils.isNotEmpty(optString)) {
                    this.mAccountKeyMap.putOpt(optString, (Object)new KeyIds(s, s2, s3).toJSON().toString());
                }
                else {
                    Log.w(AccountKeyMap.TAG, "addCurrentKeyIds no current account");
                }
                this.saveToPreference();
            }
            catch (JSONException ex) {
                Log.w(AccountKeyMap.TAG, "can't addCurrentKeyIds " + ex);
                continue;
            }
            break;
        }
    }
    
    void clearMap() {
        this.mAccountKeyMap = new JSONObject();
        this.saveToPreference();
    }
    
    String getCurrentAccount() {
        return this.mAccountKeyMap.optString("currentAccount");
    }
    
    KeyIds getCurrentKeyIds() {
        return new KeyIds(this.mAccountKeyMap.optString(this.mAccountKeyMap.optString("currentAccount")));
    }
    
    void removeCurrentKeyIds(final String s) {
        this.mAccountKeyMap.remove(s);
        this.saveToPreference();
    }
    
    KeyIds restoreKeyIdsForAccount(final String s) {
        if (StringUtils.isEmpty(s)) {
            return this.restoreKeyIdsWithoutAccount();
        }
        final String optString = this.mAccountKeyMap.optString("currentAccount");
        Label_0056: {
            if (s.equals(optString)) {
                break Label_0056;
            }
            while (true) {
                while (true) {
                    Label_0138: {
                        try {
                            if (this.mAccountKeyMap.has(s)) {
                                this.mAccountKeyMap.putOpt("currentAccount", (Object)s);
                            }
                            else {
                                if (!StringUtils.isEmpty(optString) || this.mKeyIdsFromLegacy == null) {
                                    break Label_0138;
                                }
                                this.mAccountKeyMap.putOpt("currentAccount", (Object)s);
                                this.mAccountKeyMap.putOpt(s, (Object)this.mKeyIdsFromLegacy.toJSON().toString());
                            }
                            this.saveToPreference();
                            return this.getCurrentKeyIds();
                        }
                        catch (JSONException ex) {
                            Log.w(AccountKeyMap.TAG, "can't buildKeyIdsToKeySetIdMapFromLegacy " + ex);
                            continue;
                        }
                        continue;
                    }
                    if (Log.isLoggable(AccountKeyMap.TAG, 3)) {
                        Log.d(AccountKeyMap.TAG, "account not found when restoreKeyIdsForAccount " + s);
                    }
                    this.mAccountKeyMap.putOpt("currentAccount", (Object)s);
                    continue;
                }
            }
        }
    }
    
    KeyIds restoreKeyIdsWithoutAccount() {
        Log.d(AccountKeyMap.TAG, "restoreKeyIdsWithoutAccount not supported");
        return null;
    }
    
    class KeyIds
    {
        private String mKceKeyId;
        private String mKchKeyId;
        private String mKeySetId;
        
        KeyIds(final String s) {
            if (Log.isLoggable(AccountKeyMap.TAG, 3)) {
                Log.d(AccountKeyMap.TAG, "KeyIds from JSON " + s);
            }
            try {
                final JSONObject jsonObject = new JSONObject(s);
                this.mKeySetId = jsonObject.optString("keySetId");
                this.mKceKeyId = jsonObject.optString("kceKeyId");
                this.mKchKeyId = jsonObject.optString("kchKeyId");
            }
            catch (JSONException ex) {
                Log.w(AccountKeyMap.TAG, "can't turn JSON to KeyIds " + ex);
            }
        }
        
        KeyIds(final String mKeySetId, final String mKceKeyId, final String mKchKeyId) {
            this.mKeySetId = mKeySetId;
            this.mKceKeyId = mKceKeyId;
            this.mKchKeyId = mKchKeyId;
        }
        
        public String getKceKeyId() {
            return this.mKceKeyId;
        }
        
        public String getKchKeyId() {
            return this.mKchKeyId;
        }
        
        public String getKeySetId() {
            return this.mKeySetId;
        }
        
        JSONObject toJSON() {
            final JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.putOpt("keySetId", (Object)this.mKeySetId).putOpt("kceKeyId", (Object)this.mKceKeyId).putOpt("kchKeyId", (Object)this.mKchKeyId);
                return jsonObject;
            }
            catch (JSONException ex) {
                Log.w(AccountKeyMap.TAG, "can't turn KeyIds toJSON" + ex);
                return jsonObject;
            }
        }
    }
}
