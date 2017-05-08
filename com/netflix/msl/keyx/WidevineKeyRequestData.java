// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import com.netflix.android.org.json.JSONObject;

public class WidevineKeyRequestData extends KeyRequestData
{
    private static final String KEY_REQUEST_DATA = "keyrequest";
    private final String keyRequestData;
    
    public WidevineKeyRequestData(final JSONObject jsonObject) {
        super(NetflixKeyExchangeScheme.WIDEVINE);
        try {
            this.keyRequestData = jsonObject.getString("keyrequest");
        }
        catch (JSONException ex) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "keydata " + jsonObject.toString(), ex);
        }
    }
    
    public WidevineKeyRequestData(final String keyRequestData) {
        super(NetflixKeyExchangeScheme.WIDEVINE);
        if (keyRequestData == null || keyRequestData.trim().isEmpty()) {
            throw new IllegalArgumentException("keyRequestData cannot be null or empty");
        }
        this.keyRequestData = keyRequestData;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof WidevineKeyRequestData)) {
                return false;
            }
            final WidevineKeyRequestData widevineKeyRequestData = (WidevineKeyRequestData)o;
            boolean b;
            if (this.keyRequestData == widevineKeyRequestData.keyRequestData || (this.keyRequestData != null && widevineKeyRequestData.keyRequestData != null && this.keyRequestData.equals(widevineKeyRequestData.keyRequestData))) {
                b = true;
            }
            else {
                b = false;
            }
            if (!super.equals(o) || !b) {
                return false;
            }
        }
        return true;
    }
    
    public String getKeyRequestData() {
        return this.keyRequestData;
    }
    
    @Override
    protected JSONObject getKeydata() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("keyrequest", this.keyRequestData);
        return jsonObject;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ this.keyRequestData.hashCode();
    }
}
