// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.client;

import java.util.ArrayList;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Collection;
import com.netflix.msl.MslException;
import com.netflix.msl.MslError;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.android.org.json.JSONArray;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.MslInternalException;
import java.util.List;
import java.util.Iterator;
import com.netflix.mediaclient.Log;
import java.util.HashSet;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import com.netflix.msl.tokens.UserIdToken;
import com.netflix.msl.tokens.ServiceToken;
import java.util.Set;
import com.netflix.msl.util.MslContext;
import android.content.Context;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.tokens.MasterToken;
import java.util.Map;
import com.netflix.msl.util.MslStore;

public class AndroidMslStore implements MslStore
{
    private static final String KEY_CRYPTO_CONTEXT = "cryptoContext";
    private static final String KEY_CRYPTO_CONTEXTS = "cryptoContexts";
    private static final String KEY_MASTER_TOKEN = "masterToken";
    private static final String KEY_MASTER_TOKEN_SERIAL_NUMBER = "mtSerialNumber";
    private static final String KEY_MASTER_TOKEN_SERVICE_TOKENS = "mtServiceTokens";
    private static final String KEY_NON_REPLAYABLE_ID = "nonReplayableId";
    private static final String KEY_NON_REPLAYABLE_IDS = "nonReplayableIds";
    private static final String KEY_SERVICE_TOKEN = "serviceToken";
    private static final String KEY_SERVICE_TOKEN_SET = "serviceTokenSet";
    private static final String KEY_UNBOUND_SERVICE_TOKENS = "unboundServiceTokens";
    private static final String KEY_USERID = "userId";
    private static final String KEY_USERID_SERVICE_TOKENS = "uitServiceTokens";
    private static final String KEY_USERID_TOKEN = "userIdToken";
    private static final String KEY_USERID_TOKENS = "userIdTokens";
    private static final String KEY_USERID_TOKEN_SERIAL_NUMBER = "uitSerialNumber";
    private static final String TAG = "nf_msl_store";
    private final Map<MasterToken, ICryptoContext> cryptoContexts;
    private Context mContext;
    private MslContext mMslContext;
    private final Map<Long, Set<ServiceToken>> mtServiceTokens;
    private final Map<Long, Long> nonReplayableIds;
    private final Map<Long, Set<ServiceToken>> uitServiceTokens;
    private final Set<ServiceToken> unboundServiceTokens;
    private final Map<String, UserIdToken> userIdTokens;
    
    public AndroidMslStore(final Context mContext) {
        this.cryptoContexts = new ConcurrentHashMap<MasterToken, ICryptoContext>();
        this.userIdTokens = new ConcurrentHashMap<String, UserIdToken>();
        this.nonReplayableIds = new HashMap<Long, Long>();
        this.unboundServiceTokens = new HashSet<ServiceToken>();
        this.mtServiceTokens = new HashMap<Long, Set<ServiceToken>>();
        this.uitServiceTokens = new HashMap<Long, Set<ServiceToken>>();
        if (mContext == null) {
            throw new IllegalArgumentException("Context can not be null!");
        }
        this.mContext = mContext;
    }
    
    private MasterToken getMasterToken(final long n) {
        for (final MasterToken masterToken : this.cryptoContexts.keySet()) {
            if (masterToken != null && masterToken.getSerialNumber() == n) {
                if (Log.isLoggable()) {
                    Log.d("nf_msl_store", "Master token found for serial number: " + n + " and it is: " + masterToken);
                }
                return masterToken;
            }
        }
        if (Log.isLoggable()) {
            Log.w("nf_msl_store", "Master token not found for serial number: " + n);
        }
        return null;
    }
    
    private static ServiceToken getServiceToken(final List<ServiceToken> list, final long n, final long n2) {
        for (final ServiceToken serviceToken : list) {
            if (serviceToken.getMasterTokenSerialNumber() == n && serviceToken.getUserIdTokenSerialNumber() == n2) {
                if (Log.isLoggable()) {
                    Log.d("nf_msl_store", "ServiceToken found for user id token serial number: " + n2 + " and master token serial number " + n + " and it is: " + serviceToken);
                }
                return serviceToken;
            }
        }
        if (Log.isLoggable()) {
            Log.w("nf_msl_store", "ServiceToken not found for user id token serial number: " + n2 + " and master token serial number " + n);
        }
        return null;
    }
    
    private UserIdToken getUserIdToken(final long n) {
        for (final UserIdToken userIdToken : this.userIdTokens.values()) {
            if (userIdToken != null && userIdToken.getSerialNumber() == n) {
                if (Log.isLoggable()) {
                    Log.d("nf_msl_store", "UserIdToken found for serial number: " + n + " and it is: " + userIdToken);
                }
                return userIdToken;
            }
        }
        if (Log.isLoggable()) {
            Log.w("nf_msl_store", "UserIdToken not found for serial number: " + n);
        }
        return null;
    }
    
    private static long incrementNonReplayableId(final long n) {
        if (n < 0L || n > 9007199254740992L) {
            throw new MslInternalException("Non-replayable ID " + n + " is outside the valid range.");
        }
        if (n == 9007199254740992L) {
            return 0L;
        }
        return 1L + n;
    }
    
    private void reset() {
        this.cryptoContexts.clear();
        this.nonReplayableIds.clear();
        this.userIdTokens.clear();
        this.uitServiceTokens.clear();
        this.mtServiceTokens.clear();
        this.unboundServiceTokens.clear();
        this.save();
    }
    
    private void save() {
        while (true) {
            while (true) {
                synchronized (this) {
                    Log.d("nf_msl_store", "save:: started.");
                    JSONObject jsonObject = null;
                    Label_0160: {
                        try {
                            jsonObject = new JSONObject();
                            final JSONArray jsonArray = new JSONArray();
                            jsonObject.put("cryptoContexts", jsonArray);
                            for (final MasterToken masterToken : this.cryptoContexts.keySet()) {
                                final JSONObject jsonObject2 = new JSONObject();
                                jsonObject2.put("masterToken", new JSONObject(masterToken.toJSONString()));
                                jsonObject2.put("cryptoContext", ((WidevineCryptoContext)this.cryptoContexts.get(masterToken)).toJSONObject());
                                jsonArray.put(jsonObject2);
                            }
                            break Label_0160;
                        }
                        catch (Throwable t) {
                            Log.e("nf_msl_store", t, "Failed to save MSL store: ", new Object[0]);
                        }
                        Log.d("nf_msl_store", "save:: done.");
                        return;
                    }
                    final JSONArray jsonArray2 = new JSONArray();
                    jsonObject.put("userIdTokens", jsonArray2);
                    for (final String s : this.userIdTokens.keySet()) {
                        final JSONObject jsonObject3 = new JSONObject();
                        final UserIdToken userIdToken = this.userIdTokens.get(s);
                        jsonObject3.put("userId", s);
                        jsonObject3.put("userIdToken", new JSONObject(userIdToken.toJSONString()));
                        jsonObject3.put("mtSerialNumber", userIdToken.getMasterTokenSerialNumber());
                        jsonArray2.put(jsonObject3);
                    }
                }
                final JSONArray jsonArray3 = new JSONArray();
                final JSONObject jsonObject4;
                jsonObject4.put("nonReplayableIds", jsonArray3);
                for (final Long n : this.nonReplayableIds.keySet()) {
                    final JSONObject jsonObject5 = new JSONObject();
                    jsonObject5.put("mtSerialNumber", n);
                    jsonObject5.put("nonReplayableId", this.nonReplayableIds.get(n));
                    jsonArray3.put(jsonObject5);
                }
                final JSONArray jsonArray4 = new JSONArray();
                jsonObject4.put("unboundServiceTokens", jsonArray4);
                final Iterator<ServiceToken> iterator4 = this.unboundServiceTokens.iterator();
                while (iterator4.hasNext()) {
                    jsonArray4.put(new JSONObject(iterator4.next().toJSONString()));
                }
                final JSONArray jsonArray5 = new JSONArray();
                jsonObject4.put("mtServiceTokens", jsonArray5);
                for (final Long n2 : this.mtServiceTokens.keySet()) {
                    final JSONObject jsonObject6 = new JSONObject();
                    jsonArray5.put(jsonObject6);
                    jsonObject6.put("mtSerialNumber", n2);
                    final JSONArray jsonArray6 = new JSONArray();
                    jsonObject6.put("serviceTokenSet", jsonArray6);
                    final Set<ServiceToken> set = this.mtServiceTokens.get(n2);
                    if (set != null) {
                        for (final ServiceToken serviceToken : set) {
                            final JSONObject jsonObject7 = new JSONObject();
                            jsonArray6.put(jsonObject7);
                            if (serviceToken.isUserIdTokenBound()) {
                                jsonObject7.put("uitSerialNumber", serviceToken.getUserIdTokenSerialNumber());
                            }
                            jsonObject7.put("serviceToken", new JSONObject(serviceToken.toJSONString()));
                        }
                    }
                }
                final JSONArray jsonArray7 = new JSONArray();
                jsonObject4.put("uitServiceTokens", jsonArray7);
                for (final Long n3 : this.uitServiceTokens.keySet()) {
                    final JSONObject jsonObject8 = new JSONObject();
                    jsonArray7.put(jsonObject8);
                    jsonObject8.put("uitSerialNumber", n3);
                    final JSONArray jsonArray8 = new JSONArray();
                    jsonObject8.put("serviceTokenSet", jsonArray8);
                    final Set<ServiceToken> set2 = this.uitServiceTokens.get(n3);
                    if (set2 != null) {
                        for (final ServiceToken serviceToken2 : set2) {
                            final JSONObject jsonObject9 = new JSONObject();
                            jsonArray8.put(jsonObject9);
                            if (serviceToken2.isMasterTokenBound()) {
                                jsonObject9.put("mtSerialNumber", serviceToken2.getMasterTokenSerialNumber());
                            }
                            jsonObject9.put("serviceToken", new JSONObject(serviceToken2.toJSONString()));
                        }
                    }
                }
                if (Log.isLoggable()) {
                    Log.d("nf_msl_store", "save:: " + jsonObject4);
                }
                PreferenceUtils.putStringPref(this.mContext, "nf_msl_store_json", jsonObject4.toString());
                Log.d("nf_msl_store", "save:: done.");
                continue;
            }
        }
    }
    
    public void addServiceTokens(final Set<ServiceToken> set) {
    Label_0018_Outer:
        while (true) {
            Label_0229: {
                while (true) {
                Label_0018:
                    while (true) {
                        final ServiceToken serviceToken;
                        synchronized (this) {
                            Log.d("nf_msl_store", "addServiceTokens::");
                            final Iterator<ServiceToken> iterator = set.iterator();
                            if (!iterator.hasNext()) {
                                break Label_0229;
                            }
                            serviceToken = iterator.next();
                            if (serviceToken.isMasterTokenBound()) {
                                final Iterator<MasterToken> iterator2 = this.cryptoContexts.keySet().iterator();
                                Block_16: {
                                    while (iterator2.hasNext()) {
                                        if (serviceToken.isBoundTo((MasterToken)iterator2.next())) {
                                            break Block_16;
                                        }
                                    }
                                    break Label_0018;
                                }
                                final int n = 1;
                                if (n == 0) {
                                    throw new MslException(MslError.SERVICETOKEN_MASTERTOKEN_NOT_FOUND, "st mtserialnumber " + serviceToken.getMasterTokenSerialNumber());
                                }
                            }
                        }
                        if (serviceToken.isUserIdTokenBound()) {
                            final Iterator<UserIdToken> iterator3 = this.userIdTokens.values().iterator();
                            while (true) {
                                while (iterator3.hasNext()) {
                                    if (serviceToken.isBoundTo((UserIdToken)iterator3.next())) {
                                        final int n2 = 1;
                                        if (n2 == 0) {
                                            throw new MslException(MslError.SERVICETOKEN_USERIDTOKEN_NOT_FOUND, "st uitserialnumber " + serviceToken.getUserIdTokenSerialNumber());
                                        }
                                        continue Label_0018;
                                    }
                                }
                                final int n2 = 0;
                                continue;
                            }
                        }
                        continue Label_0018;
                    }
                    final int n = 0;
                    continue;
                }
            }
            final Set<ServiceToken> set2;
            for (final ServiceToken serviceToken2 : set2) {
                if (serviceToken2.isUnbound()) {
                    this.unboundServiceTokens.add(serviceToken2);
                }
                else {
                    if (serviceToken2.isMasterTokenBound()) {
                        Set<ServiceToken> set3;
                        if ((set3 = this.mtServiceTokens.get(serviceToken2.getMasterTokenSerialNumber())) == null) {
                            set3 = new HashSet<ServiceToken>();
                            this.mtServiceTokens.put(serviceToken2.getMasterTokenSerialNumber(), set3);
                        }
                        set3.add(serviceToken2);
                    }
                    if (!serviceToken2.isUserIdTokenBound()) {
                        continue Label_0018_Outer;
                    }
                    Set<ServiceToken> set4;
                    if ((set4 = this.uitServiceTokens.get(serviceToken2.getUserIdTokenSerialNumber())) == null) {
                        set4 = new HashSet<ServiceToken>();
                        this.uitServiceTokens.put(serviceToken2.getUserIdTokenSerialNumber(), set4);
                    }
                    set4.add(serviceToken2);
                }
            }
            this.save();
        }
        // monitorexit(this)
    }
    
    public void addUserIdToken(final String s, final UserIdToken userIdToken) {
        while (true) {
            while (true) {
                Label_0141: {
                    synchronized (this) {
                        if (Log.isLoggable()) {
                            Log.d("nf_msl_store", "addUserIdToken:: userId: " + s);
                        }
                        final Iterator<MasterToken> iterator = this.cryptoContexts.keySet().iterator();
                        Block_5: {
                            while (iterator.hasNext()) {
                                if (userIdToken.isBoundTo((MasterToken)iterator.next())) {
                                    break Block_5;
                                }
                            }
                            break Label_0141;
                        }
                        final int n = 1;
                        if (n == 0) {
                            throw new MslException(MslError.USERIDTOKEN_MASTERTOKEN_NOT_FOUND, "uit mtserialnumber " + userIdToken.getMasterTokenSerialNumber());
                        }
                    }
                    break;
                }
                final int n = 0;
                continue;
            }
        }
        final String s2;
        this.userIdTokens.put(s2, userIdToken);
        this.save();
    }
    // monitorexit(this)
    
    public void clearCryptoContexts() {
        synchronized (this) {
            Log.d("nf_msl_store", "clearCryptoContexts::");
            this.cryptoContexts.clear();
            this.nonReplayableIds.clear();
            this.userIdTokens.clear();
            this.uitServiceTokens.clear();
            this.mtServiceTokens.clear();
            this.save();
        }
    }
    
    public void clearServiceTokens() {
        synchronized (this) {
            this.unboundServiceTokens.clear();
            this.mtServiceTokens.clear();
            this.uitServiceTokens.clear();
            this.save();
        }
    }
    
    public void clearUserIdTokens() {
        synchronized (this) {
            Log.d("nf_msl_store", "clearUserIdTokens::");
            for (final UserIdToken userIdToken : this.userIdTokens.values()) {
                try {
                    this.removeServiceTokens(null, null, userIdToken);
                }
                catch (MslException ex) {
                    throw new MslInternalException("Unexpected exception while removing user ID token bound service tokens.", (Throwable)ex);
                }
            }
        }
        this.userIdTokens.clear();
        this.save();
    }
    // monitorexit(this)
    
    public ICryptoContext getCryptoContext(final MasterToken masterToken) {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d("nf_msl_store", "getCryptoContext:: for master token " + masterToken);
            }
            return this.cryptoContexts.get(masterToken);
        }
    }
    
    public MasterToken getMasterToken() {
        while (true) {
            while (true) {
                Label_0139: {
                    synchronized (this) {
                        Log.d("nf_msl_store", "getMasterToken:: starts...");
                        final MasterToken masterToken = null;
                        final Iterator<MasterToken> iterator = this.cryptoContexts.keySet().iterator();
                        if (!iterator.hasNext()) {
                            if (Log.isLoggable()) {
                                Log.d("nf_msl_store", "getMasterToken:: Return master token " + masterToken);
                            }
                            return masterToken;
                        }
                        final MasterToken masterToken2 = iterator.next();
                        if ((masterToken == null || masterToken2.isNewerThan(masterToken)) && Log.isLoggable()) {
                            Log.d("nf_msl_store", "getMasterToken:: Found master token " + masterToken2);
                            break Label_0139;
                        }
                        break Label_0139;
                    }
                }
                continue;
            }
        }
    }
    
    public long getNonReplayableId(final MasterToken masterToken) {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d("nf_msl_store", "getNonReplayableId:: for master token " + masterToken);
            }
            final long serialNumber = masterToken.getSerialNumber();
            long longValue;
            if (this.nonReplayableIds.containsKey(serialNumber)) {
                longValue = this.nonReplayableIds.get(serialNumber);
            }
            else {
                longValue = 0L;
            }
            final long incrementNonReplayableId = incrementNonReplayableId(longValue);
            this.nonReplayableIds.put(serialNumber, incrementNonReplayableId);
            this.save();
            return incrementNonReplayableId;
        }
    }
    
    public Set<ServiceToken> getServiceTokens(final MasterToken masterToken, final UserIdToken userIdToken) {
        final MasterToken masterToken2;
        Label_0090: {
            synchronized (this) {
                Log.d("nf_msl_store", "getServiceTokens::");
                if (userIdToken == null) {
                    break Label_0090;
                }
                if (masterToken == null) {
                    throw new MslException(MslError.USERIDTOKEN_MASTERTOKEN_NULL);
                }
            }
            if (!userIdToken.isBoundTo(masterToken2)) {
                throw new MslException(MslError.USERIDTOKEN_MASTERTOKEN_MISMATCH, "uit mtserialnumber " + userIdToken.getMasterTokenSerialNumber() + "; mt " + masterToken2.getSerialNumber());
            }
        }
        final HashSet<ServiceToken> set = new HashSet<ServiceToken>();
        set.addAll((Collection<?>)this.unboundServiceTokens);
        if (masterToken2 != null) {
            final Set<ServiceToken> set2 = this.mtServiceTokens.get(masterToken2.getSerialNumber());
            if (set2 != null) {
                for (final ServiceToken serviceToken : set2) {
                    if (!serviceToken.isUserIdTokenBound()) {
                        set.add(serviceToken);
                    }
                }
            }
        }
        if (userIdToken != null) {
            final Set<ServiceToken> set3 = this.uitServiceTokens.get(userIdToken.getSerialNumber());
            if (set3 != null) {
                for (final ServiceToken serviceToken2 : set3) {
                    if (serviceToken2.isBoundTo(masterToken2)) {
                        set.add(serviceToken2);
                    }
                }
            }
        }
        // monitorexit(this)
        return set;
    }
    
    public UserIdToken getUserIdToken(final String s) {
        synchronized (this) {
            Log.d("nf_msl_store", "getUserIdToken:: userId: %s", s);
            return this.userIdTokens.get(s);
        }
    }
    
    public void init(final MslContext mMslContext) {
        // monitorenter(this)
        if (mMslContext == null) {
            try {
                throw new IllegalArgumentException("MSL Context can not be null!");
            }
            finally {
            }
            // monitorexit(this)
        }
        this.mMslContext = mMslContext;
        Log.d("nf_msl_store", "load:: started.");
        final String stringPref = PreferenceUtils.getStringPref(this.mContext, "nf_msl_store_json", null);
        Label_0064: {
            if (!StringUtils.isEmpty(stringPref)) {
                while (true) {
                    Label_1450: {
                        JSONObject jsonObject;
                        JSONArray optJSONArray;
                        JSONArray optJSONArray2 = null;
                        JSONArray optJSONArray3;
                        JSONArray optJSONArray4;
                        JSONArray optJSONArray5;
                        ArrayList<ServiceToken> list;
                        JSONArray optJSONArray6;
                        int n;
                        JSONObject jsonObject2;
                        Long value;
                        Long value2;
                        int n2 = 0;
                        MasterToken masterToken;
                        WidevineCryptoContext restoreWidevineCryptoContext;
                        String optString;
                        UserIdToken userIdToken;
                        int n3;
                        int n4;
                        int n5 = 0;
                        ServiceToken serviceToken;
                        HashSet<ServiceToken> set;
                        int n6 = 0;
                        int n7 = 0;
                        JSONArray optJSONArray7;
                        int n8 = 0;
                        JSONObject jsonObject3;
                        Long value3;
                        ServiceToken serviceToken2;
                        HashSet set2;
                        JSONObject jsonObject4;
                        Long value4;
                        JSONArray optJSONArray8;
                        long optLong;
                        ServiceToken serviceToken3;
                        JSONObject jsonObject5;
                        JSONObject jsonObject6;
                        JSONObject jsonObject7;
                        Block_9_Outer:Label_0330_Outer:Label_0712_Outer:Label_0878_Outer:Label_1163_Outer:Label_1229_Outer:
                        while (true) {
                        Label_1229:
                            while (true) {
                            Label_1163:
                                while (true) {
                                Label_0946_Outer:
                                    while (true) {
                                    Label_0946:
                                        while (true) {
                                            Label_1501: {
                                            Label_0535_Outer:
                                                while (true) {
                                                    Label_1489: {
                                                    Label_0535:
                                                        while (true) {
                                                            Label_0330:Block_13_Outer:
                                                            while (true) {
                                                                Label_1474: {
                                                                    while (true) {
                                                                        Label_1467: {
                                                                            Label_1462: {
                                                                                try {
                                                                                    Log.d("nf_msl_store", "load:: MSL store found: %s", stringPref);
                                                                                    jsonObject = new JSONObject(stringPref);
                                                                                    optJSONArray = jsonObject.optJSONArray("cryptoContexts");
                                                                                    if (optJSONArray != null) {
                                                                                        if (Log.isLoggable()) {
                                                                                            Log.d("nf_msl_store", "load::Crypto contexts map found, size: " + optJSONArray.length());
                                                                                        }
                                                                                        break Label_1462;
                                                                                    }
                                                                                    else {
                                                                                        Log.d("nf_msl_store", "load::Crypto contexts map not found!");
                                                                                        Label_0278: {
                                                                                            optJSONArray2 = jsonObject.optJSONArray("userIdTokens");
                                                                                        }
                                                                                        if (optJSONArray2 != null) {
                                                                                            if (Log.isLoggable()) {
                                                                                                Log.d("nf_msl_store", "load:: UserId tokens map found, size: " + optJSONArray2.length());
                                                                                            }
                                                                                            break Label_1474;
                                                                                        }
                                                                                        else {
                                                                                            Log.d("nf_msl_store", "load:: UserId tokens map not found!");
                                                                                            Label_0483:
                                                                                            optJSONArray3 = jsonObject.optJSONArray("nonReplayableIds");
                                                                                            if (optJSONArray3 != null) {
                                                                                                if (Log.isLoggable()) {
                                                                                                    Log.d("nf_msl_store", "load:: NonReplayableId map found, size: " + optJSONArray3.length());
                                                                                                }
                                                                                                break Label_0330;
                                                                                            }
                                                                                            else {
                                                                                                Log.d("nf_msl_store", "load:: NonReplayableId map not found!");
                                                                                                Label_0660:
                                                                                                optJSONArray4 = jsonObject.optJSONArray("unboundServiceTokens");
                                                                                                if (optJSONArray4 != null) {
                                                                                                    if (Log.isLoggable()) {
                                                                                                        Log.d("nf_msl_store", "load:: UnboundServiceTokens set found, size: " + optJSONArray4.length());
                                                                                                    }
                                                                                                    break Label_0535;
                                                                                                }
                                                                                                else {
                                                                                                    Log.d("nf_msl_store", "load:: UnboundServiceTokens set not found!");
                                                                                                    Label_0817:
                                                                                                    optJSONArray5 = jsonObject.optJSONArray("mtServiceTokens");
                                                                                                    list = new ArrayList<ServiceToken>();
                                                                                                    if (optJSONArray5 != null) {
                                                                                                        if (Log.isLoggable()) {
                                                                                                            Log.d("nf_msl_store", "load:: MasterTokenServiceToken map found, size: " + optJSONArray5.length());
                                                                                                        }
                                                                                                        break Label_0535_Outer;
                                                                                                    }
                                                                                                    else {
                                                                                                        Log.d("nf_msl_store", "load:: MasterTokenServiceToken map not found!");
                                                                                                        Label_1114:
                                                                                                        optJSONArray6 = jsonObject.optJSONArray("uitServiceTokens");
                                                                                                        if (optJSONArray6 == null) {
                                                                                                            break Label_1450;
                                                                                                        }
                                                                                                        if (Log.isLoggable()) {
                                                                                                            Log.d("nf_msl_store", "load:: UserIdsServiceToken map found, size: " + optJSONArray6.length());
                                                                                                        }
                                                                                                        break Label_0946_Outer;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    // iftrue(Label_0630:, !Log.isLoggable())
                                                                                    // iftrue(Label_1520:, !Log.isLoggable())
                                                                                    // iftrue(Label_0817:, n6 >= optJSONArray4.length())
                                                                                    // iftrue(Label_1501:, !Log.isLoggable())
                                                                                    // iftrue(Label_0660:, n >= optJSONArray3.length())
                                                                                    // iftrue(Label_1114:, n7 >= optJSONArray5.length())
                                                                                    // iftrue(Label_1054:, !serviceToken2.isMasterTokenBound() || !serviceToken2.isUserIdTokenBound())
                                                                                    // iftrue(Label_1431:, n4 >= optJSONArray6.length())
                                                                                    // iftrue(Label_0278:, n2 >= optJSONArray.length())
                                                                                    // iftrue(Label_1443:, n5 >= optJSONArray8.length())
                                                                                    // iftrue(Label_1392:, serviceToken == null)
                                                                                    // iftrue(Label_1508:, n8 >= optJSONArray7.length())
                                                                                    // iftrue(Label_1489:, !Log.isLoggable())
                                                                                    // iftrue(Label_0483:, n3 >= optJSONArray2.length())
                                                                                    // iftrue(Label_1298:, !Log.isLoggable())
                                                                                    // iftrue(Label_1467:, !Log.isLoggable())
                                                                                    // iftrue(Label_0453:, !Log.isLoggable())
                                                                                    while (true) {
                                                                                        Block_12: {
                                                                                        Block_24:
                                                                                            while (true) {
                                                                                            Block_27:
                                                                                                while (true) {
                                                                                                    Block_8: {
                                                                                                        while (true) {
                                                                                                            Block_32: {
                                                                                                                while (true) {
                                                                                                                    Block_17: {
                                                                                                                        while (true) {
                                                                                                                            Block_20: {
                                                                                                                                while (true) {
                                                                                                                                    while (true) {
                                                                                                                                        Block_28: {
                                                                                                                                            while (true) {
                                                                                                                                                jsonObject2 = optJSONArray3.getJSONObject(n);
                                                                                                                                                value = jsonObject2.getLong("mtSerialNumber");
                                                                                                                                                value2 = jsonObject2.getLong("nonReplayableId");
                                                                                                                                                break Block_17;
                                                                                                                                                Log.d("nf_msl_store", "load:: add to cryptoContexts at " + n2 + ": masterToken: " + masterToken + " and cryptoContext: " + restoreWidevineCryptoContext);
                                                                                                                                                break Label_1467;
                                                                                                                                                while (true) {
                                                                                                                                                    while (true) {
                                                                                                                                                        this.userIdTokens.put(optString, userIdToken);
                                                                                                                                                        ++n3;
                                                                                                                                                        break Label_0330;
                                                                                                                                                        Log.d("nf_msl_store", "load:: add to UserIdsServiceToken map at i" + n4 + " and j " + n5 + ": serviceToken: " + serviceToken);
                                                                                                                                                        break Label_1163;
                                                                                                                                                        Log.d("nf_msl_store", "load:: add to userIdTokens at " + n3 + ": userId: " + optString + " and token: " + userIdToken.toString());
                                                                                                                                                        continue Label_0330_Outer;
                                                                                                                                                    }
                                                                                                                                                    Log.d("nf_msl_store", "Double bound service token found, use it");
                                                                                                                                                    set.add(serviceToken);
                                                                                                                                                    continue Block_13_Outer;
                                                                                                                                                }
                                                                                                                                                break Block_20;
                                                                                                                                                break Block_28;
                                                                                                                                                continue Block_9_Outer;
                                                                                                                                            }
                                                                                                                                            break Block_24;
                                                                                                                                            jsonObject3 = optJSONArray7.getJSONObject(n8);
                                                                                                                                            serviceToken2 = new ServiceToken(this.mMslContext, jsonObject3.getJSONObject("serviceToken"), this.getMasterToken(value3), this.getUserIdToken(jsonObject3.optLong("uitSerialNumber", -1L)), (ICryptoContext)null);
                                                                                                                                            set2.add(serviceToken2);
                                                                                                                                            break Block_27;
                                                                                                                                            Block_31: {
                                                                                                                                                break Block_31;
                                                                                                                                                break Block_8;
                                                                                                                                            }
                                                                                                                                            jsonObject4 = optJSONArray6.getJSONObject(n4);
                                                                                                                                            value4 = jsonObject4.getLong("uitSerialNumber");
                                                                                                                                            optJSONArray8 = jsonObject4.optJSONArray("serviceTokenSet");
                                                                                                                                            set = new HashSet<ServiceToken>(optJSONArray8.length());
                                                                                                                                            this.uitServiceTokens.put(value4, set);
                                                                                                                                            n5 = 0;
                                                                                                                                            break Label_1229;
                                                                                                                                        }
                                                                                                                                        Log.d("nf_msl_store", "load:: add to MasterTokenServiceToken map at i" + n7 + " and j " + n8 + ": serviceToken ");
                                                                                                                                        break Label_1501;
                                                                                                                                        break Block_32;
                                                                                                                                        serviceToken = getServiceToken(list, optLong, value4);
                                                                                                                                        continue Label_0712_Outer;
                                                                                                                                    }
                                                                                                                                    Log.d("nf_msl_store", "load:: add to UnboundServiceTokens Set at " + n6 + ": serviceToken: " + serviceToken3);
                                                                                                                                    break Label_1489;
                                                                                                                                    continue Label_1163_Outer;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            serviceToken3 = new ServiceToken(this.mMslContext, optJSONArray4.getJSONObject(n6), (MasterToken)null, (UserIdToken)null, (ICryptoContext)null);
                                                                                                                            this.unboundServiceTokens.add(serviceToken3);
                                                                                                                            continue Label_0946_Outer;
                                                                                                                        }
                                                                                                                        break Block_12;
                                                                                                                    }
                                                                                                                    Log.d("nf_msl_store", "load:: add to NonReplayableIds at " + n + ": master token serial number: " + value + " and nonReplayableId: " + value2);
                                                                                                                    Label_0630: {
                                                                                                                        this.nonReplayableIds.put(value, value2);
                                                                                                                    }
                                                                                                                    ++n;
                                                                                                                    continue Label_0535;
                                                                                                                    Log.d("nf_msl_store", "Check if service token exist for given userIdToken serial number: " + value4 + " and master token serial number: " + optLong);
                                                                                                                    continue Label_0946_Outer;
                                                                                                                }
                                                                                                            }
                                                                                                            optLong = optJSONArray8.getJSONObject(n5).optLong("mtSerialNumber", -1L);
                                                                                                            continue;
                                                                                                        }
                                                                                                    }
                                                                                                    jsonObject5 = optJSONArray.getJSONObject(n2);
                                                                                                    masterToken = new MasterToken(this.mMslContext, jsonObject5.getJSONObject("masterToken"));
                                                                                                    restoreWidevineCryptoContext = WidevineCryptoContext.restoreWidevineCryptoContext(this.mMslContext, jsonObject5.getJSONObject("cryptoContext"));
                                                                                                    this.cryptoContexts.put(masterToken, (ICryptoContext)restoreWidevineCryptoContext);
                                                                                                    continue Label_0330_Outer;
                                                                                                }
                                                                                                list.add(serviceToken2);
                                                                                                continue Label_0535_Outer;
                                                                                            }
                                                                                            jsonObject6 = optJSONArray5.getJSONObject(n7);
                                                                                            value3 = jsonObject6.getLong("mtSerialNumber");
                                                                                            optJSONArray7 = jsonObject6.optJSONArray("serviceTokenSet");
                                                                                            set2 = new HashSet<ServiceToken>(optJSONArray7.length());
                                                                                            this.mtServiceTokens.put(value3, (HashSet<ServiceToken>)set2);
                                                                                            n8 = 0;
                                                                                            continue Label_0946;
                                                                                        }
                                                                                        jsonObject7 = optJSONArray2.getJSONObject(n3);
                                                                                        optString = jsonObject7.optString("userId", null);
                                                                                        userIdToken = new UserIdToken(this.mMslContext, jsonObject7.getJSONObject("userIdToken"), this.getMasterToken(jsonObject7.getLong("mtSerialNumber")));
                                                                                        continue Label_0712_Outer;
                                                                                    }
                                                                                    Label_1392: {
                                                                                        Log.e("nf_msl_store", "Double bound service token not found, this should not happen!");
                                                                                    }
                                                                                    throw new IllegalStateException("Double bound service token not found, this should not happen!");
                                                                                }
                                                                                catch (Throwable t) {
                                                                                    Log.e("nf_msl_store", t, "Failed to load MSL store: try restore...", new Object[0]);
                                                                                    this.reset();
                                                                                }
                                                                                break;
                                                                                Label_1443: {
                                                                                    ++n4;
                                                                                }
                                                                                continue Label_1163;
                                                                            }
                                                                            n2 = 0;
                                                                            continue Label_1229_Outer;
                                                                        }
                                                                        ++n2;
                                                                        continue Label_1229_Outer;
                                                                    }
                                                                }
                                                                n3 = 0;
                                                                continue Label_0330;
                                                            }
                                                            n = 0;
                                                            continue Label_0535;
                                                        }
                                                        n6 = 0;
                                                        continue Label_0878_Outer;
                                                    }
                                                    ++n6;
                                                    continue Label_0878_Outer;
                                                }
                                                n7 = 0;
                                                continue Label_1163_Outer;
                                            }
                                            ++n8;
                                            continue Label_0946;
                                        }
                                        Label_1508: {
                                            ++n7;
                                        }
                                        continue Label_1163_Outer;
                                    }
                                    n4 = 0;
                                    continue Label_1163;
                                }
                                ++n5;
                                continue Label_1229;
                            }
                        }
                        Log.d("nf_msl_store", "load:: done.");
                        break Label_0064;
                    }
                    Log.d("nf_msl_store", "load:: UserIdsServiceToken map not found!");
                    continue;
                }
            }
            Log.d("nf_msl_store", "load:: MSL store not found...");
        }
    }
    // monitorexit(this)
    
    public boolean isEmpty() {
        return this.cryptoContexts.isEmpty();
    }
    
    public void removeCryptoContext(final MasterToken masterToken) {
        Label_0188: {
            Object iterator;
            synchronized (this) {
                if (Log.isLoggable()) {
                    Log.d("nf_msl_store", "removeCryptoContext:: for master token " + masterToken);
                }
                iterator = this.cryptoContexts.remove(masterToken);
                if (iterator == null) {
                    break Label_0188;
                }
                if (iterator instanceof WidevineCryptoContext) {
                    ((WidevineCryptoContext)iterator).release();
                    this.nonReplayableIds.remove(masterToken.getSerialNumber());
                    iterator = this.userIdTokens.values().iterator();
                    while (((Iterator)iterator).hasNext()) {
                        final UserIdToken userIdToken = ((Iterator<UserIdToken>)iterator).next();
                        if (userIdToken.isBoundTo(masterToken)) {
                            this.removeUserIdToken(userIdToken);
                        }
                    }
                    break Label_0188;
                }
            }
            Log.e("nf_msl_store", "NOT Widevine crypto session! This should NOT happen!");
            throw new IllegalStateException("Not Widevine crypto context: " + iterator);
            try {
                final MasterToken masterToken2;
                this.removeServiceTokens(null, masterToken2, null);
                this.save();
            }
            // monitorexit(this)
            catch (MslException ex) {
                throw new MslInternalException("Unexpected exception while removing master token bound service tokens.", (Throwable)ex);
            }
        }
    }
    
    public void removeServiceTokens(final String s, final MasterToken masterToken, final UserIdToken userIdToken) {
        synchronized (this) {
            if (Log.isLoggable()) {
                Log.d("nf_msl_store", "removeServiceTokens:: name: " + s);
            }
            if (userIdToken != null && masterToken != null && !userIdToken.isBoundTo(masterToken)) {
                throw new MslException(MslError.USERIDTOKEN_MASTERTOKEN_MISMATCH, "uit mtserialnumber " + userIdToken.getMasterTokenSerialNumber() + "; mt " + masterToken.getSerialNumber());
            }
        }
        final Throwable t;
        if (t != null && masterToken == null && userIdToken == null) {
            final Iterator<ServiceToken> iterator = this.unboundServiceTokens.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().getName().equals(t)) {
                    iterator.remove();
                }
            }
            for (final Map.Entry<Long, Set<ServiceToken>> entry : this.mtServiceTokens.entrySet()) {
                final Long n = entry.getKey();
                final Set<ServiceToken> set = entry.getValue();
                final Iterator<ServiceToken> iterator3 = set.iterator();
                while (iterator3.hasNext()) {
                    if (iterator3.next().getName().equals(t)) {
                        iterator3.remove();
                    }
                }
                this.mtServiceTokens.put(n, set);
            }
            for (final Map.Entry<Long, Set<ServiceToken>> entry2 : this.uitServiceTokens.entrySet()) {
                final Long n2 = entry2.getKey();
                final Set<ServiceToken> set2 = entry2.getValue();
                final Iterator<ServiceToken> iterator5 = set2.iterator();
                while (iterator5.hasNext()) {
                    if (iterator5.next().getName().equals(t)) {
                        iterator5.remove();
                    }
                }
                this.uitServiceTokens.put(n2, set2);
            }
        }
        if (masterToken != null && userIdToken == null) {
            final Set<ServiceToken> set3 = this.mtServiceTokens.get(masterToken.getSerialNumber());
            if (set3 != null) {
                final Iterator<ServiceToken> iterator6 = set3.iterator();
                while (iterator6.hasNext()) {
                    final ServiceToken serviceToken = iterator6.next();
                    if (t == null || serviceToken.getName().equals(t)) {
                        iterator6.remove();
                    }
                }
            }
            for (final Map.Entry<Long, Set<ServiceToken>> entry3 : this.uitServiceTokens.entrySet()) {
                final Long n3 = entry3.getKey();
                final Set<ServiceToken> set4 = entry3.getValue();
                final Iterator<ServiceToken> iterator8 = set4.iterator();
                while (iterator8.hasNext()) {
                    final ServiceToken serviceToken2 = iterator8.next();
                    if ((t == null || serviceToken2.getName().equals(t)) && serviceToken2.isBoundTo(masterToken)) {
                        iterator8.remove();
                    }
                }
                this.uitServiceTokens.put(n3, set4);
            }
        }
        if (userIdToken != null) {
            final Set<ServiceToken> set5 = this.uitServiceTokens.get(userIdToken.getSerialNumber());
            if (set5 != null) {
                final Iterator<ServiceToken> iterator9 = set5.iterator();
                while (iterator9.hasNext()) {
                    final ServiceToken serviceToken3 = iterator9.next();
                    if ((t == null || serviceToken3.getName().equals(t)) && (masterToken == null || serviceToken3.isBoundTo(masterToken))) {
                        iterator9.remove();
                    }
                }
            }
        }
        this.save();
    }
    // monitorexit(this)
    
    public void removeUserIdToken(final UserIdToken userIdToken) {
        while (true) {
        Label_0061:
            while (true) {
                Label_0167: {
                    synchronized (this) {
                        Log.d("nf_msl_store", "removeUserIdToken:: userIdToken: %s", userIdToken);
                        for (final MasterToken masterToken : this.cryptoContexts.keySet()) {
                            if (userIdToken.isBoundTo(masterToken)) {
                                break Label_0061;
                            }
                        }
                        break Label_0167;
                        Label_0142: {
                            Block_5: {
                                for (final Map.Entry<String, UserIdToken> entry : this.userIdTokens.entrySet()) {
                                    if (entry.getValue().equals((Object)userIdToken)) {
                                        break Block_5;
                                    }
                                }
                                break Label_0142;
                            }
                            final Map.Entry<String, UserIdToken> entry;
                            this.userIdTokens.remove(entry.getKey());
                            try {
                                final MasterToken masterToken;
                                this.removeServiceTokens(null, masterToken, userIdToken);
                                this.save();
                                return;
                            }
                            catch (MslException ex) {
                                throw new MslInternalException("Unexpected exception while removing user ID token bound service tokens.", (Throwable)ex);
                            }
                        }
                    }
                }
                MasterToken masterToken = null;
                continue Label_0061;
            }
        }
    }
    
    public void setCryptoContext(final MasterToken masterToken, final ICryptoContext cryptoContext) {
        synchronized (this) {
            Log.d("nf_msl_store", "setCryptoContex:: starts...");
            if (cryptoContext == null) {
                if (Log.isLoggable()) {
                    Log.d("nf_msl_store", "setCryptoContex:: Remove crypto context for master token " + masterToken);
                }
                this.removeCryptoContext(masterToken);
            }
            else {
                if (Log.isLoggable()) {
                    Log.d("nf_msl_store", "setCryptoContex:: Add crypto context " + cryptoContext + " for master token " + masterToken);
                }
                this.cryptoContexts.put(masterToken, cryptoContext);
            }
            this.save();
            Log.d("nf_msl_store", "setCryptoContex:: done.");
        }
    }
    
    public void updateUserId(final String s, final String s2) {
        // monitorenter(this)
        if (s == null) {
            try {
                throw new MslException(MslError.USERIDTOKEN_IDENTITY_INVALID, "Old userId can not be null");
            }
            finally {
            }
            // monitorexit(this)
        }
        if (s2 == null) {
            throw new MslException(MslError.USERIDTOKEN_IDENTITY_INVALID, "New userId can not be null");
        }
        final UserIdToken userIdToken = this.userIdTokens.remove(s);
        if (userIdToken == null) {
            throw new MslException(MslError.USERIDTOKEN_IDENTITY_NOT_ASSOCIATED_WITH_ENTITY, "UserIdToken not found for given old user ID: " + s);
        }
        this.userIdTokens.put(s2, userIdToken);
        this.save();
    }
    // monitorexit(this)
}
