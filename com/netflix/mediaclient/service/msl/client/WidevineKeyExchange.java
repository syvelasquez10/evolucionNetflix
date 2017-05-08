// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.client;

import com.netflix.msl.MslKeyExchangeException;
import com.netflix.msl.MslError;
import com.netflix.msl.util.MslContext$ReauthCode;
import com.netflix.msl.crypto.ICryptoContext;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.keyx.KeyExchangeFactory$KeyExchangeData;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.keyx.WidevineKeyResponseData;
import com.netflix.msl.keyx.KeyResponseData;
import com.netflix.msl.tokens.MasterToken;
import com.netflix.msl.keyx.WidevineKeyRequestData;
import com.netflix.mediaclient.Log;
import com.netflix.msl.keyx.KeyRequestData;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.keyx.NetflixKeyExchangeScheme;
import com.netflix.msl.util.AuthenticationUtils;
import com.netflix.msl.keyx.KeyExchangeFactory;

public class WidevineKeyExchange extends KeyExchangeFactory
{
    private static final String TAG = "nf_msl";
    private final AuthenticationUtils authUtils;
    
    public WidevineKeyExchange(final AuthenticationUtils authUtils) {
        super(NetflixKeyExchangeScheme.WIDEVINE);
        this.authUtils = authUtils;
    }
    
    @Override
    protected KeyRequestData createRequestData(final MslContext mslContext, final JSONObject jsonObject) {
        if (Log.isLoggable()) {
            Log.d("nf_msl", "createRequestData:: " + jsonObject);
        }
        return new WidevineKeyRequestData(jsonObject);
    }
    
    @Override
    protected KeyResponseData createResponseData(final MslContext mslContext, final MasterToken masterToken, final JSONObject jsonObject) {
        if (Log.isLoggable()) {
            Log.d("nf_msl", "createResponseData:: " + jsonObject);
        }
        return new WidevineKeyResponseData(masterToken, jsonObject);
    }
    
    @Override
    public KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext mslContext, final KeyRequestData keyRequestData, final EntityAuthenticationData entityAuthenticationData) {
        throw new MslInternalException("Client will not generate a keyx  response");
    }
    
    @Override
    public KeyExchangeFactory$KeyExchangeData generateResponse(final MslContext mslContext, final KeyRequestData keyRequestData, final MasterToken masterToken) {
        throw new MslInternalException("Client will not generate a keyx  response");
    }
    
    @Override
    public ICryptoContext getCryptoContext(final MslContext mslContext, final KeyRequestData keyRequestData, final KeyResponseData keyResponseData, final MasterToken masterToken) {
        if (!(keyRequestData instanceof WidevineKeyRequestData)) {
            throw new MslInternalException("Key request data " + keyRequestData.getClass().getName() + " was not created by this factory");
        }
        if (!(keyResponseData instanceof WidevineKeyResponseData)) {
            throw new MslInternalException("Key response data " + keyResponseData.getClass().getName() + " was not created by this factory");
        }
        final WidevineKeyRequestData widevineKeyRequestData = (WidevineKeyRequestData)keyRequestData;
        final WidevineKeyResponseData widevineKeyResponseData = (WidevineKeyResponseData)keyResponseData;
        final String identity = mslContext.getEntityAuthenticationData(null).getIdentity();
        if (!this.authUtils.isSchemePermitted(identity, this.getScheme())) {
            throw new MslKeyExchangeException(MslError.KEYX_INCORRECT_DATA, "Authentication scheme for device type not supported " + identity + ":" + this.getScheme());
        }
        return new WidevineCryptoContext(mslContext, identity, widevineKeyRequestData, widevineKeyResponseData, masterToken);
    }
}
