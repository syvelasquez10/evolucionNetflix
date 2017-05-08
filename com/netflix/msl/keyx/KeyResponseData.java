// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.msl.MslInternalException;
import com.netflix.android.org.json.JSONStringer;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslKeyExchangeException;
import com.netflix.msl.MslError;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.android.org.json.JSONString;

public abstract class KeyResponseData implements JSONString
{
    private static final String KEY_KEYDATA = "keydata";
    private static final String KEY_MASTER_TOKEN = "mastertoken";
    private static final String KEY_SCHEME = "scheme";
    private final MasterToken masterToken;
    private final KeyExchangeScheme scheme;
    
    protected KeyResponseData(final MasterToken masterToken, final KeyExchangeScheme scheme) {
        this.masterToken = masterToken;
        this.scheme = scheme;
    }
    
    public static KeyResponseData create(final MslContext mslContext, final JSONObject jsonObject) {
        MasterToken masterToken;
        KeyExchangeScheme keyExchangeScheme;
        try {
            masterToken = new MasterToken(mslContext, jsonObject.getJSONObject("mastertoken"));
            final String string = jsonObject.getString("scheme");
            keyExchangeScheme = mslContext.getKeyExchangeScheme(string);
            if (keyExchangeScheme == null) {
                throw new MslKeyExchangeException(MslError.UNIDENTIFIED_KEYX_SCHEME, string);
            }
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "keyresponsedata " + jsonObject.toString(), ex);
        }
        final JSONObject jsonObject2 = jsonObject.getJSONObject("keydata");
        final KeyExchangeFactory keyExchangeFactory = mslContext.getKeyExchangeFactory(keyExchangeScheme);
        if (keyExchangeFactory == null) {
            throw new MslKeyExchangeException(MslError.KEYX_FACTORY_NOT_FOUND, keyExchangeScheme.name());
        }
        return keyExchangeFactory.createResponseData(mslContext, masterToken, jsonObject2);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof KeyResponseData)) {
                return false;
            }
            final KeyResponseData keyResponseData = (KeyResponseData)o;
            if (!this.masterToken.equals(keyResponseData.masterToken) || !this.scheme.equals(keyResponseData.scheme)) {
                return false;
            }
        }
        return true;
    }
    
    public KeyExchangeScheme getKeyExchangeScheme() {
        return this.scheme;
    }
    
    protected abstract JSONObject getKeydata();
    
    public MasterToken getMasterToken() {
        return this.masterToken;
    }
    
    @Override
    public int hashCode() {
        return this.masterToken.hashCode() ^ this.scheme.hashCode();
    }
    
    @Override
    public final String toJSONString() {
        try {
            return new JSONStringer().object().key("mastertoken").value(this.masterToken).key("scheme").value(this.scheme.name()).key("keydata").value(this.getKeydata()).endObject().toString();
        }
        catch (JSONException ex) {
            throw new MslInternalException("Error encoding " + this.getClass().getName() + " JSON.", ex);
        }
    }
    
    @Override
    public String toString() {
        return this.toJSONString();
    }
}
