// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.keyx;

import java.util.Arrays;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslEncodingException;
import com.netflix.msl.MslError;
import com.netflix.msl.util.Base64;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.tokens.MasterToken;
import java.math.BigInteger;

public class AuthenticatedDiffieHellmanResponseData extends KeyResponseData
{
    private static final String KEY_PARAMETERS_ID = "parametersid";
    private static final String KEY_PUBLIC_KEY = "publickey";
    private static final String KEY_WRAPDATA = "wrapdata";
    private final String parametersId;
    private final BigInteger publicKey;
    private final byte[] wrapdata;
    
    public AuthenticatedDiffieHellmanResponseData(final MasterToken ex, final JSONObject jsonObject) {
        super((MasterToken)ex, NetflixKeyExchangeScheme.AUTHENTICATED_DH);
        try {
            this.wrapdata = Base64.decode(jsonObject.getString("wrapdata"));
            this.parametersId = jsonObject.getString("parametersid");
            this.publicKey = new BigInteger(AbstractAuthenticatedDiffieHellmanExchange.correctNullBytes(Base64.decode(jsonObject.getString("publickey"))));
        }
        catch (JSONException ex2) {
            throw new MslEncodingException(MslError.JSON_PARSE_ERROR, "keydata " + jsonObject.toString(), ex2);
        }
        catch (NullPointerException ex3) {}
        catch (IllegalArgumentException ex) {
            goto Label_0091;
        }
    }
    
    public AuthenticatedDiffieHellmanResponseData(final MasterToken masterToken, final byte[] wrapdata, final String parametersId, final BigInteger publicKey) {
        super(masterToken, NetflixKeyExchangeScheme.AUTHENTICATED_DH);
        this.wrapdata = wrapdata;
        this.parametersId = parametersId;
        this.publicKey = publicKey;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof AuthenticatedDiffieHellmanResponseData)) {
            return false;
        }
        final AuthenticatedDiffieHellmanResponseData authenticatedDiffieHellmanResponseData = (AuthenticatedDiffieHellmanResponseData)o;
        return super.equals(o) && Arrays.equals(this.wrapdata, authenticatedDiffieHellmanResponseData.wrapdata) && this.parametersId.equalsIgnoreCase(authenticatedDiffieHellmanResponseData.parametersId) && this.publicKey.equals(authenticatedDiffieHellmanResponseData.publicKey);
    }
    
    @Override
    protected JSONObject getKeydata() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("wrapdata", Base64.encode(this.wrapdata));
        jsonObject.put("parametersid", this.parametersId);
        jsonObject.put("publickey", Base64.encode(AbstractAuthenticatedDiffieHellmanExchange.correctNullBytes(this.publicKey.toByteArray())));
        return jsonObject;
    }
    
    public String getParametersId() {
        return this.parametersId;
    }
    
    public BigInteger getPublicKey() {
        return this.publicKey;
    }
    
    public byte[] getWrapdata() {
        return this.wrapdata;
    }
    
    @Override
    public int hashCode() {
        return super.hashCode() ^ Arrays.hashCode(this.wrapdata) ^ this.parametersId.hashCode() ^ this.publicKey.hashCode();
    }
}
