// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslKeyExchangeException;
import com.netflix.msl.MslError;
import com.netflix.android.org.json.JSONObject;

public class SymmetricWrappedExchange$RequestData extends KeyRequestData
{
    private static final String KEY_KEY_ID = "keyid";
    private final SymmetricWrappedExchange$KeyId keyId;
    
    public SymmetricWrappedExchange$RequestData(final JSONObject jsonObject) {
        super(KeyExchangeScheme.SYMMETRIC_WRAPPED);
        try {
            final String string = jsonObject.getString("keyid");
            try {
                this.keyId = SymmetricWrappedExchange$KeyId.valueOf(string);
            }
            catch (IllegalArgumentException ex) {
                throw new MslKeyExchangeException(MslError.UNIDENTIFIED_KEYX_KEY_ID, string, ex);
            }
        }
        catch (JSONException ex2) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "keydata " + jsonObject.toString(), ex2);
        }
    }
    
    public SymmetricWrappedExchange$RequestData(final SymmetricWrappedExchange$KeyId keyId) {
        super(KeyExchangeScheme.SYMMETRIC_WRAPPED);
        this.keyId = keyId;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof SymmetricWrappedExchange$RequestData)) {
            return false;
        }
        final SymmetricWrappedExchange$RequestData symmetricWrappedExchange$RequestData = (SymmetricWrappedExchange$RequestData)o;
        return super.equals(o) && this.keyId.equals(symmetricWrappedExchange$RequestData.keyId);
    }
    
    public SymmetricWrappedExchange$KeyId getKeyId() {
        return this.keyId;
    }
    
    @Override
    protected JSONObject getKeydata() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("keyid", this.keyId.name());
        return jsonObject;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.keyId.hashCode();
    }
}
