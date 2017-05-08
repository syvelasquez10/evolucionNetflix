// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.msl.client;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.msl.util.JsonUtil;
import com.netflix.android.org.json.JSONArray;
import com.netflix.msl.client.ApiHttpWrapper;
import com.netflix.msl.msg.MessageInputStream;
import com.netflix.msl.msg.ErrorHeader;
import java.io.InputStream;
import com.netflix.msl.util.IoUtil;
import java.util.concurrent.ExecutionException;
import com.netflix.msl.msg.MslControl$MslChannel;
import java.util.concurrent.Future;
import com.netflix.msl.tokens.UserIdToken;
import com.netflix.mediaclient.service.configuration.crypto.CryptoManagerRegistry;
import com.netflix.msl.keyx.KeyRequestData;
import java.net.MalformedURLException;
import com.netflix.msl.MslInternalException;
import java.nio.charset.Charset;
import java.util.Iterator;
import com.netflix.msl.MslException;
import com.netflix.msl.NetflixMslError;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.entityauth.EntityAuthenticationFactory;
import java.util.Map;
import com.netflix.msl.entityauth.EntityAuthenticationData;
import com.netflix.msl.entityauth.UnauthenticatedAuthenticationData;
import com.netflix.msl.keyx.NetflixKeyExchangeScheme;
import com.netflix.msl.keyx.KeyExchangeFactory;
import com.netflix.msl.keyx.KeyExchangeScheme;
import com.netflix.msl.entityauth.UnauthenticatedAuthenticationFactory;
import com.netflix.msl.util.AuthenticationUtils;
import com.netflix.msl.entityauth.RsaAuthenticationFactory;
import com.netflix.msl.entityauth.EntityAuthenticationScheme;
import java.util.HashMap;
import com.netflix.msl.util.NullAuthenticationUtils;
import com.netflix.msl.io.Url;
import com.netflix.msl.msg.MessageContext;
import com.netflix.msl.client.ClientRequestMessageContext;
import java.net.URL;
import com.netflix.msl.userauth.UserAuthenticationData;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.util.MslStore;
import com.netflix.msl.entityauth.RsaStore;
import com.netflix.mediaclient.Log;
import com.netflix.msl.msg.ErrorMessageRegistry;
import com.netflix.msl.msg.MessageStreamFactory;
import com.netflix.msl.util.Base64$Base64Impl;
import com.netflix.msl.util.Base64;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;
import com.netflix.msl.client.ModifiableRsaStore;
import com.netflix.msl.client.ClientMslContext;
import com.netflix.msl.msg.MslControl;
import com.netflix.msl.msg.MessageDebugContext;
import com.netflix.mediaclient.service.configuration.esn.EsnProvider;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.msl.keyx.WidevineKeyRequestData;
import com.netflix.msl.client.KeyRequestDataProvider;

public final class AndroidMslClient implements KeyRequestDataProvider<WidevineKeyRequestData>
{
    private static final String CS_UTF8 = "UTF-8";
    private static final String TAG = "nf_msl";
    private static final int TIMEOUT_MS = 5000;
    private ServiceAgent$ConfigurationAgentInterface configuration;
    private Context context;
    private EsnProvider esnProvider;
    private MessageDebugContext messageDebugContext;
    private MslControl mslControl;
    private ClientMslContext mslCtx;
    private AndroidMslStore mslStore;
    private ModifiableRsaStore rsaStore;
    private ApiEndpointRegistry urlProvider;
    
    public AndroidMslClient(final Context context, final ServiceAgent$ConfigurationAgentInterface configuration, final ServiceAgent$UserAgentInterface serviceAgent$UserAgentInterface) {
        Base64.setImpl(new AndroidBase64Impl());
        this.context = context;
        this.mslControl = new MslControl(0, new MessageStreamFactory(), new AndroidMessageRegistry(context));
        this.esnProvider = configuration.getEsnProvider();
        if (Log.isLoggable()) {
            Log.d("nf_msl", "ESN " + this.esnProvider.getEsn());
        }
        this.urlProvider = configuration.getApiEndpointRegistry();
        this.rsaStore = new AndroidModifiableRsaStore(context);
        this.messageDebugContext = new AndroidDebugContext();
        this.configuration = configuration;
        this.mslStore = new AndroidMslStore(context);
        this.mslCtx = this.createMslContext(this.esnProvider.getEsn(), this.rsaStore, this.mslStore);
        this.mslStore.init(this.mslCtx);
    }
    
    private AndroidMslClient$MslChannelWrapper createApiRequest(final byte[] array, final String s, final UserAuthenticationData userAuthenticationData) {
        return this.createApiRequest(array, s, userAuthenticationData, null);
    }
    
    private AndroidMslClient$MslChannelWrapper createApiRequest(final byte[] array, final String s, final UserAuthenticationData userAuthenticationData, final Boolean b) {
        final URL url = new URL(this.getApiUrl());
        final ClientRequestMessageContext build = ClientRequestMessageContext.builder().userAuthData(userAuthenticationData).keyRequestDataProvider(this).payload(array).userId(s).nonReplayable(b).debugContext(this.messageDebugContext).build();
        final AndroidMslClient$MslChannelWrapper androidMslClient$MslChannelWrapper = new AndroidMslClient$MslChannelWrapper(null);
        androidMslClient$MslChannelWrapper.url = new MslUrlHttpURLConnectionImpl(this.configuration, url);
        androidMslClient$MslChannelWrapper.future = this.mslControl.request(this.mslCtx, build, androidMslClient$MslChannelWrapper.url, 5000);
        return androidMslClient$MslChannelWrapper;
    }
    
    private AndroidMslClient$MslChannelWrapper createAppbootRequest(final URL url, final byte[] array) {
        final ClientRequestMessageContext build = ClientRequestMessageContext.builder().encrypted(false).keyRequestDataProvider(this).payload(array).debugContext(this.messageDebugContext).build();
        final AndroidMslClient$MslChannelWrapper androidMslClient$MslChannelWrapper = new AndroidMslClient$MslChannelWrapper(null);
        androidMslClient$MslChannelWrapper.url = new MslUrlHttpURLConnectionImpl(this.configuration, url);
        androidMslClient$MslChannelWrapper.future = this.mslControl.request(this.mslCtx, build, androidMslClient$MslChannelWrapper.url, 5000);
        return androidMslClient$MslChannelWrapper;
    }
    
    private ClientMslContext createMslContext(final String s, final RsaStore rsaStore, final MslStore mslStore) {
        final NullAuthenticationUtils nullAuthenticationUtils = new NullAuthenticationUtils();
        final HashMap<EntityAuthenticationScheme, RsaAuthenticationFactory> hashMap = new HashMap<EntityAuthenticationScheme, RsaAuthenticationFactory>();
        hashMap.put(EntityAuthenticationScheme.RSA, new RsaAuthenticationFactory(rsaStore, nullAuthenticationUtils));
        hashMap.put(EntityAuthenticationScheme.NONE, (RsaAuthenticationFactory)new UnauthenticatedAuthenticationFactory(nullAuthenticationUtils));
        final HashMap<KeyExchangeScheme, KeyExchangeFactory> hashMap2 = new HashMap<KeyExchangeScheme, KeyExchangeFactory>();
        hashMap2.put(NetflixKeyExchangeScheme.WIDEVINE, new WidevineKeyExchange(nullAuthenticationUtils));
        return ClientMslContext.builder().entityAuthData(new UnauthenticatedAuthenticationData(s)).mslStore(mslStore).entityAuthFactories((Map<EntityAuthenticationScheme, EntityAuthenticationFactory>)hashMap).keyxFactories(hashMap2).build();
    }
    
    private void extractMslTrustStore(JSONObject jsonObject, final ModifiableRsaStore modifiableRsaStore) {
        if (modifiableRsaStore == null) {
            Log.w("nf_msl", "extractMslTrustStore:: rsaStore is null!");
        }
        else {
            if (!jsonObject.has("msltruststore")) {
                throw new MslException(NetflixMslError.JSON_PARSE_ERROR, String.format("Unable to find msltruststore in appboot response (%s)", jsonObject));
            }
            final JSONObject jsonObject2 = jsonObject.getJSONObject("msltruststore");
            if (jsonObject2.has("error")) {
                throw new MslException(NetflixMslError.JSON_PARSE_ERROR, String.format("Unable to get msltruststore from appboot response because of error (%s)", jsonObject.getString("error")));
            }
            if (!jsonObject2.has("keys")) {
                throw new MslException(NetflixMslError.JSON_PARSE_ERROR, String.format("Unable to find msltruststore keys in appboot response (%s)", jsonObject));
            }
            jsonObject = jsonObject2.getJSONObject("keys");
            final Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                final String s = keys.next();
                modifiableRsaStore.addPublicKey(s, jsonObject.getString(s));
            }
        }
    }
    
    private void releaseResources(final AndroidMslClient$MslChannelWrapper androidMslClient$MslChannelWrapper) {
        if (androidMslClient$MslChannelWrapper == null) {
            throw new IllegalStateException("MSL channel and URL not known! This should NOT happen!");
        }
        if (androidMslClient$MslChannelWrapper.url == null) {
            throw new IllegalStateException("MSL URL not known! This should NOT happen!");
        }
        androidMslClient$MslChannelWrapper.url.release();
    }
    
    public byte[] apiRequest(byte[] apiRequest, final String s, final UserAuthenticationData userAuthenticationData) {
        apiRequest = (byte[])(Object)this.createApiRequest(apiRequest, s, userAuthenticationData);
        try {
            return this.processRequest(((AndroidMslClient$MslChannelWrapper)(Object)apiRequest).future);
        }
        finally {
            this.releaseResources((AndroidMslClient$MslChannelWrapper)(Object)apiRequest);
        }
    }
    
    public JSONObject appbootRequest(final JSONObject jsonObject) {
        final String appbootUri = this.urlProvider.getAppbootUri(this.esnProvider.getESNPrefix());
        if (Log.isLoggable()) {
            Log.d("nf_msl", "appBootRequest: " + jsonObject);
            Log.d("nf_msl", "Go to app boot server: " + appbootUri);
        }
        AndroidMslClient$MslChannelWrapper androidMslClient$MslChannelWrapper = null;
        AndroidMslClient$MslChannelWrapper appbootRequest = null;
        try {
            final AndroidMslClient$MslChannelWrapper androidMslClient$MslChannelWrapper2 = androidMslClient$MslChannelWrapper = (appbootRequest = this.createAppbootRequest(new URL(appbootUri), jsonObject.toString().getBytes(Charset.forName("UTF-8"))));
            final String s = new String(this.processRequest(androidMslClient$MslChannelWrapper2.future), Charset.forName("UTF-8"));
            appbootRequest = androidMslClient$MslChannelWrapper2;
            androidMslClient$MslChannelWrapper = androidMslClient$MslChannelWrapper2;
            if (Log.isLoggable()) {
                appbootRequest = androidMslClient$MslChannelWrapper2;
                androidMslClient$MslChannelWrapper = androidMslClient$MslChannelWrapper2;
                Log.d("nf_msl", "AppBoot response body: " + s);
            }
            appbootRequest = androidMslClient$MslChannelWrapper2;
            androidMslClient$MslChannelWrapper = androidMslClient$MslChannelWrapper2;
            final JSONObject jsonObject2 = new JSONObject(s);
            appbootRequest = androidMslClient$MslChannelWrapper2;
            androidMslClient$MslChannelWrapper = androidMslClient$MslChannelWrapper2;
            this.extractMslTrustStore(jsonObject2, this.rsaStore);
            return jsonObject2;
        }
        catch (MalformedURLException ex) {
            androidMslClient$MslChannelWrapper = appbootRequest;
            throw new MslInternalException("Unable to parse our own url for " + appbootUri, ex);
        }
        finally {
            this.releaseResources(androidMslClient$MslChannelWrapper);
        }
    }
    
    public JSONObject defaultAppbootRequest(final Long n, final Long n2) {
        final JSONObject jsonObject = new JSONObject();
        final JSONObject jsonObject2 = new JSONObject();
        if (n != null) {
            jsonObject2.put("hash", (long)n);
        }
        jsonObject.put("msltruststore", jsonObject2);
        final JSONObject jsonObject3 = new JSONObject();
        if (n != null) {
            jsonObject3.put("hash", (long)n);
        }
        jsonObject.put("ssltruststore", jsonObject3);
        return jsonObject;
    }
    
    @Override
    public WidevineKeyRequestData get() {
        Log.d("nf_msl", "WidevineKeyRequestDataProvider::get:");
        return CryptoManagerRegistry.getCryptoManager().getKeyRequestData();
    }
    
    public String getApiUrl() {
        return this.urlProvider.getApiUri("/msl");
    }
    
    public Context getContext() {
        return this.context;
    }
    
    public ApiEndpointRegistry getUrlProvider() {
        return this.urlProvider;
    }
    
    public UserIdToken getUserIdToken(final String s) {
        return this.mslStore.getUserIdToken(s);
    }
    
    public byte[] processRequest(final Future<MslControl$MslChannel> future) {
        MessageInputStream input = null;
        Label_0066: {
            try {
                final MslControl$MslChannel mslControl$MslChannel = future.get();
                if (mslControl$MslChannel == null) {
                    throw new IllegalStateException("No MslChannel");
                }
                break Label_0066;
            }
            catch (ExecutionException ex) {
                Log.e("nf_msl", ex, "Execution exception: ", new Object[0]);
                final Throwable cause = ex.getCause();
                if (cause instanceof MslException) {
                    Log.e("nf_msl", "MSL exception found ");
                    throw (MslException)cause;
                }
                if (cause instanceof RuntimeException) {
                    Log.e("nf_msl", "Runtime exception found ");
                    throw (RuntimeException)cause;
                }
                throw new RuntimeException(cause);
                // iftrue(Label_0187:, errorHeader == null)
                // iftrue(Label_0124:, !Log.isLoggable())
                ErrorHeader errorHeader = null;
                while (true) {
                    Log.e("nf_msl", "processRequest:: Error found: " + errorHeader.getErrorMessage());
                    throw new MslErrorException(errorHeader);
                    final MslControl$MslChannel mslControl$MslChannel;
                    input = mslControl$MslChannel.input;
                    Log.d("nf_msl", "processRequest:: check input stream for error... ");
                    errorHeader = input.getErrorHeader();
                    continue;
                }
                Label_0124: {
                    throw new MslErrorException(errorHeader);
                }
            }
            catch (InterruptedException ex2) {
                Log.e("nf_msl", ex2, "Interrupted exception found ", new Object[0]);
                throw new RuntimeException(ex2);
            }
        }
        Label_0187: {
            Log.d("nf_msl", "processRequest:: response received... ");
        }
        return IoUtil.readBytes(input, 2048);
    }
    
    public void removeUserData() {
        synchronized (this) {
            this.mslStore.clearUserIdTokens();
        }
    }
    
    public boolean shouldExecuteAppBotSynchronously() {
        return this.mslStore.isEmpty();
    }
    
    public ApiHttpWrapper unwrapApiResponse(final byte[] array) {
        final JSONArray jsonArray = new JSONArray(new String(array, Charset.forName("UTF-8")));
        final String string = jsonArray.getJSONObject(0).getString("edgemsl-version");
        final JSONObject jsonObject = jsonArray.getJSONObject(1);
        final Map<String, Object> map = JsonUtil.getMap(jsonObject, "headers");
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        for (final Map.Entry<String, V> entry : map.entrySet()) {
            hashMap.put(entry.getKey(), entry.getValue().toString());
        }
        final int intValue = Integer.valueOf(jsonObject.getString("status"));
        final byte[] decode = Base64.decode(jsonObject.getJSONObject("payload").getString("data"));
        if (Log.isLoggable()) {
            Log.d("nf_msl", "Raw unwrapped response: " + jsonArray);
        }
        return new ApiHttpWrapper(string, hashMap, intValue, decode);
    }
    
    public String wrapApiRequest(final String s, final String s2, final Map<String, String> map, String s3, final String s4) {
        final JSONObject jsonObject = new JSONObject();
        final JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("path", s);
        final JSONObject jsonObject3 = new JSONObject();
        if (map != null) {
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                jsonObject3.put(entry.getKey(), entry.getValue());
            }
        }
        if (s3 == null) {
            s3 = (String)new JSONObject();
        }
        jsonObject2.put("query", s3);
        jsonObject2.put("headers", jsonObject3);
        if (StringUtils.isNotEmpty(s4)) {
            final JSONObject jsonObject4 = new JSONObject();
            jsonObject2.put("payload", jsonObject4);
            jsonObject4.put("data", s4);
        }
        if (StringUtils.isNotEmpty(s2)) {
            jsonObject2.put("method", s2);
        }
        final JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        jsonArray.put(jsonObject2);
        return jsonArray.toString();
    }
}
