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
import com.netflix.android.org.json.JSONString;

public abstract class KeyRequestData implements JSONString
{
    private static final String KEY_KEYDATA = "keydata";
    private static final String KEY_SCHEME = "scheme";
    private final KeyExchangeScheme scheme;
    
    protected KeyRequestData(final KeyExchangeScheme scheme) {
        this.scheme = scheme;
    }
    
    public static KeyRequestData create(final MslContext mslContext, final JSONObject jsonObject) {
        KeyExchangeScheme keyExchangeScheme;
        try {
            final String string = jsonObject.getString("scheme");
            keyExchangeScheme = mslContext.getKeyExchangeScheme(string);
            if (keyExchangeScheme == null) {
                throw new MslKeyExchangeException(MslError.UNIDENTIFIED_KEYX_SCHEME, string);
            }
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "keyrequestdata " + jsonObject.toString(), ex);
        }
        final JSONObject jsonObject2 = jsonObject.getJSONObject("keydata");
        final KeyExchangeFactory keyExchangeFactory = mslContext.getKeyExchangeFactory(keyExchangeScheme);
        if (keyExchangeFactory == null) {
            throw new MslKeyExchangeException(MslError.KEYX_FACTORY_NOT_FOUND, keyExchangeScheme.name());
        }
        return keyExchangeFactory.createRequestData(mslContext, jsonObject2);
    }
    
    @Override
    public boolean equals(final Object o) {
        return o == this || (o instanceof KeyRequestData && this.scheme.equals(((KeyRequestData)o).scheme));
    }
    
    public KeyExchangeScheme getKeyExchangeScheme() {
        return this.scheme;
    }
    
    protected abstract JSONObject getKeydata();
    
    @Override
    public int hashCode() {
        return this.scheme.hashCode();
    }
    
    @Override
    public final String toJSONString() {
        try {
            return new JSONStringer().object().key("scheme").value(this.scheme.name()).key("keydata").value(this.getKeydata()).endObject().toString();
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
