// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.client;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import com.netflix.msl.util.IoUtil;
import java.io.InputStream;
import java.security.interfaces.RSAPrivateKey;
import java.util.Iterator;
import com.netflix.android.org.json.JSONArray;
import com.netflix.android.org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import java.security.spec.InvalidKeySpecException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.KeySpec;
import java.security.spec.X509EncodedKeySpec;
import com.netflix.msl.util.Base64;
import java.security.NoSuchAlgorithmException;
import com.netflix.mediaclient.util.AndroidUtils;
import java.util.HashMap;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Map;
import java.security.KeyFactory;
import android.content.Context;
import com.netflix.msl.client.ModifiableRsaStore;

public class AndroidModifiableRsaStore implements ModifiableRsaStore
{
    private static final String KEY_APPBOOT = "APPBOOT";
    private static final String PREFERENCE_MSL_RSA_STORE = "nf_msl_rsa_store_json";
    private static final String PROPERTY_IDENTITY = "identity";
    private static final String PROPERTY_KEY_ENCODED = "encodedKey";
    private static final String PROPERTY_PUBLIC_KEYS = "publicKeys";
    private static final String TAG = "nf_msl_rsastore";
    private Context context;
    private final KeyFactory factory;
    private final Map<String, PublicKey> keys;
    private final Map<String, PrivateKey> privateKeys;
    private Map<String, String> publicKeysMap;
    
    public AndroidModifiableRsaStore(final Context context) {
        this.keys = new HashMap<String, PublicKey>();
        this.publicKeysMap = new HashMap<String, String>();
        this.privateKeys = new HashMap<String, PrivateKey>();
        if (context == null) {
            throw new IllegalArgumentException("Context can be null");
        }
        this.context = context;
        try {
            this.factory = KeyFactory.getInstance("RSA");
            this.loadFromPersistance();
            this.addPublicKey("APPBOOT", AndroidUtils.getMslAppBootKey());
        }
        catch (NoSuchAlgorithmException ex) {
            throw new IllegalStateException("Unable to get RSA key factory", ex);
        }
    }
    
    private void addPublicKey(final String s, final String s2, final boolean b) {
        this.addPublicKey(s, Base64.decode(s2));
        if (b) {
            this.savePublicKeyToPersistance(s, s2);
        }
    }
    
    private void addPublicKey(final String s, final byte[] array) {
        final X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(array);
        PublicKey generatePublic;
        try {
            generatePublic = this.factory.generatePublic(x509EncodedKeySpec);
            if (!(generatePublic instanceof RSAPublicKey)) {
                throw new IllegalArgumentException("Public key is not an instance of RSAPublicKey.");
            }
        }
        catch (InvalidKeySpecException ex) {
            throw new IllegalArgumentException("Public key can not be parsed", ex);
        }
        this.keys.put(s, generatePublic);
    }
    
    private void loadFromPersistance() {
        JSONArray optJSONArray;
        try {
            final String stringPref = PreferenceUtils.getStringPref(this.context, "nf_msl_rsa_store_json", null);
            if (StringUtils.isEmpty(stringPref)) {
                Log.d("nf_msl_rsastore", "RSA store not found...");
                return;
            }
            if (Log.isLoggable()) {
                Log.d("nf_msl_rsastore", "RSA store found " + stringPref);
            }
            optJSONArray = new JSONObject(stringPref).optJSONArray("publicKeys");
            if (optJSONArray == null) {
                Log.e("nf_msl_rsastore", "Public keys array NOT found!");
                return;
            }
        }
        catch (Throwable t) {
            Log.e("nf_msl_rsastore", t, "Failed to create public key JSON object: ", new Object[0]);
            return;
        }
        for (int i = 0; i < optJSONArray.length(); ++i) {
            final JSONObject jsonObject = optJSONArray.getJSONObject(i);
            this.addPublicKey(jsonObject.optString("identity"), jsonObject.optString("encodedKey"), false);
        }
    }
    
    private void save() {
        final JSONArray jsonArray;
        synchronized (this) {
            Log.d("nf_msl_rsastore", "save:: started.");
            final JSONObject jsonObject = new JSONObject();
            jsonArray = new JSONArray();
            for (final String s : this.publicKeysMap.keySet()) {
                final String s2 = this.publicKeysMap.get(s);
                final JSONObject jsonObject2 = new JSONObject();
                jsonObject2.put("identity", s);
                jsonObject2.put("encodedKey", s2);
                jsonArray.put(jsonObject2);
            }
        }
        final JSONObject jsonObject3;
        jsonObject3.put("publicKeys", jsonArray);
        final String string = jsonObject3.toString();
        if (Log.isLoggable()) {
            Log.d("nf_msl_rsastore", "save:: " + string);
        }
        PreferenceUtils.putStringPref(this.context, "nf_msl_rsa_store_json", string);
        Log.d("nf_msl_rsastore", "save:: done.");
    }
    // monitorexit(this)
    
    private void savePublicKeyToPersistance(final String s, final String s2) {
        if (StringUtils.isEmpty(s) || StringUtils.isEmpty(s2)) {
            Log.w("nf_msl_rsastore", "Empty identity and/or raw public key. It should NOT happen!");
            return;
        }
        if ("APPBOOT".equals(s)) {
            Log.d("nf_msl_rsastore", "Do not add APPBOOT to persistence...");
            return;
        }
        this.publicKeysMap.put(s, s2);
        try {
            this.save();
        }
        catch (Throwable t) {
            Log.e("nf_msl_rsastore", t, "Failed to save RSA store to persistenace: ", new Object[0]);
        }
    }
    
    private void savePublicKeyToPersistance(final String s, final byte[] array) {
        if (array == null) {
            Log.w("nf_msl_rsastore", "Empty raw public key. It should NOT happen!");
            return;
        }
        this.savePublicKeyToPersistance(s, Base64.encode(array));
    }
    
    public void addPrivateKey(final String s, final PrivateKey privateKey) {
        if (!(privateKey instanceof RSAPrivateKey)) {
            throw new IllegalArgumentException("Private key is not an instance of RSAPrivateKey.");
        }
        this.privateKeys.put(s, privateKey);
    }
    
    public void addPublicKey(final String s, final InputStream inputStream) {
        synchronized (this) {
            final byte[] bytes = IoUtil.readBytes(inputStream, 2048);
            this.savePublicKeyToPersistance(s, bytes);
            this.addPublicKey(s, bytes);
        }
    }
    
    public void addPublicKey(final String s, final String s2) {
        this.addPublicKey(s, s2, true);
    }
    
    public Set<String> getIdentities() {
        final HashSet<Object> set = (HashSet<Object>)new HashSet<String>();
        set.addAll(this.keys.keySet());
        set.addAll(this.privateKeys.keySet());
        return (Set<String>)set;
    }
    
    public PrivateKey getPrivateKey(final String s) {
        return this.privateKeys.get(s);
    }
    
    public PublicKey getPublicKey(final String s) {
        return this.keys.get(s);
    }
}
