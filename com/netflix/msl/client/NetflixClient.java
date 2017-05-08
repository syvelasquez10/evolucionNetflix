// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.client;

import com.netflix.msl.util.Base64;
import java.util.Map;
import java.util.HashMap;
import com.netflix.msl.util.JsonUtil;
import com.netflix.android.org.json.JSONArray;
import com.netflix.msl.msg.ErrorHeader;
import com.netflix.msl.msg.MessageInputStream;
import java.io.InputStream;
import com.netflix.msl.util.IoUtil;
import java.util.concurrent.ExecutionException;
import com.netflix.msl.msg.MslControl$MslChannel;
import java.util.concurrent.Future;
import java.net.MalformedURLException;
import com.netflix.msl.MslInternalException;
import java.nio.charset.Charset;
import java.net.URL;
import com.netflix.msl.userauth.UserAuthenticationData;
import java.util.Iterator;
import com.netflix.msl.MslException;
import com.netflix.msl.NetflixMslError;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.util.SystemDebugContext;
import com.netflix.msl.util.DummyMessageRegistry;
import com.netflix.msl.msg.ErrorMessageRegistry;
import com.netflix.msl.msg.MessageStreamFactory;
import com.netflix.msl.msg.MslControl;
import com.netflix.msl.msg.MessageDebugContext;

public abstract class NetflixClient
{
    protected final NetflixEnvironment environment;
    protected final String esn;
    protected final String esnPrefix;
    protected final MessageDebugContext messageDebugContext;
    protected final MslControl mslControl;
    protected final ModifiableRsaStore rsaStore;
    protected final NetflixUrlProvider urlProvider;
    
    protected NetflixClient(MessageStreamFactory messageStreamFactory, ErrorMessageRegistry errorMessageRegistry, final String esnPrefix, final String esn, final NetflixEnvironment environment, final ModifiableRsaStore rsaStore, MessageDebugContext messageDebugContext) {
        if (messageStreamFactory == null) {
            messageStreamFactory = new MessageStreamFactory();
        }
        if (errorMessageRegistry == null) {
            errorMessageRegistry = new DummyMessageRegistry();
        }
        this.mslControl = new MslControl(0, messageStreamFactory, errorMessageRegistry);
        this.esnPrefix = esnPrefix;
        this.esn = esn;
        this.environment = environment;
        this.urlProvider = NetflixUrlProviderFactory.of(environment);
        this.rsaStore = rsaStore;
        if (messageDebugContext == null) {
            messageDebugContext = new SystemDebugContext();
        }
        this.messageDebugContext = messageDebugContext;
    }
    
    private void extractMslTrustStore(JSONObject jsonObject, final ModifiableRsaStore modifiableRsaStore) {
        if (modifiableRsaStore != null) {
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
            for (final String s : jsonObject.keySet()) {
                modifiableRsaStore.addPublicKey(s, jsonObject.getString(s));
            }
        }
    }
    
    public byte[] apiRequest(final byte[] array, final String s, final UserAuthenticationData userAuthenticationData) {
        return this.processRequest(this.createApiRequest(array, s, userAuthenticationData));
    }
    
    public JSONObject appbootRequest(JSONObject jsonObject) {
        final String appbootUri = this.urlProvider.getAppbootUri(this.esnPrefix);
        try {
            jsonObject = new JSONObject(new String(this.processRequest(this.createAppbootRequest(new URL(appbootUri), jsonObject.toString().getBytes(Charset.forName("UTF-8")))), Charset.forName("UTF-8")));
            this.extractMslTrustStore(jsonObject, this.rsaStore);
            return jsonObject;
        }
        catch (MalformedURLException ex) {
            throw new MslInternalException("Unable to parse our own url for " + appbootUri, ex);
        }
    }
    
    protected abstract Future<MslControl$MslChannel> createApiRequest(final byte[] p0, final String p1, final UserAuthenticationData p2);
    
    protected abstract Future<MslControl$MslChannel> createAppbootRequest(final URL p0, final byte[] p1);
    
    protected Future<MslControl$MslChannel> createNccpRequest(final String s, final byte[] array, final String s2, final UserAuthenticationData userAuthenticationData) {
        return null;
    }
    
    public JSONObject defaultAppbootRequest(final Long n, final Long n2) {
        final JSONObject jsonObject = new JSONObject();
        final JSONObject jsonObject2 = new JSONObject();
        if (n != null) {
            jsonObject2.put("hash", (long)n);
        }
        jsonObject.put("msltruststore", jsonObject2);
        final JSONObject jsonObject3 = new JSONObject();
        if (n2 != null) {
            jsonObject3.put("hash", (long)n2);
        }
        jsonObject.put("ssltruststore", jsonObject3);
        return jsonObject;
    }
    
    protected URL getApiUrl() {
        final String apiUri = this.urlProvider.getApiUri("/msl");
        try {
            return new URL(apiUri);
        }
        catch (MalformedURLException ex) {
            throw new MslInternalException("Unable to parse our own url for " + apiUri, ex);
        }
    }
    
    protected URL getNccpUrl(String nccpUri) {
        nccpUri = this.urlProvider.getNccpUri(nccpUri);
        try {
            return new URL(nccpUri);
        }
        catch (MalformedURLException ex) {
            throw new MslInternalException("Unable to parse our own url for " + nccpUri, ex);
        }
    }
    
    @Deprecated
    public byte[] nccpRequest(final String s, final byte[] array, final String s2, final UserAuthenticationData userAuthenticationData) {
        return this.processRequest(this.createNccpRequest(s, array, s2, userAuthenticationData));
    }
    
    public byte[] processRequest(final Future<MslControl$MslChannel> future) {
        MessageInputStream input = null;
        Label_0042: {
            try {
                final MslControl$MslChannel mslControl$MslChannel = future.get();
                if (mslControl$MslChannel == null) {
                    throw new IllegalStateException("No MslChannel");
                }
                break Label_0042;
            }
            catch (ExecutionException ex) {
                final Throwable cause = ex.getCause();
                if (cause instanceof MslException) {
                    throw (MslException)cause;
                }
                if (cause instanceof RuntimeException) {
                    throw (RuntimeException)cause;
                }
                throw new RuntimeException(cause);
                final MslControl$MslChannel mslControl$MslChannel;
                input = mslControl$MslChannel.input;
                final ErrorHeader errorHeader = input.getErrorHeader();
                // iftrue(Label_0099:, errorHeader == null)
                throw new RuntimeException(errorHeader.getErrorMessage());
            }
            catch (InterruptedException ex2) {
                throw new RuntimeException(ex2);
            }
        }
        Label_0099: {
            input.getMessageHeader();
        }
        return IoUtil.readBytes(input, 2048);
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
        return new ApiHttpWrapper(string, hashMap, Integer.valueOf(jsonObject.getString("status")), Base64.decode(jsonObject.getJSONObject("payload").getString("data")));
    }
    
    public String wrapApiRequest(final String s, final Map<String, String> map, final String s2, String s3) {
        final JSONObject jsonObject = new JSONObject();
        final JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("path", s);
        final JSONObject jsonObject3 = new JSONObject();
        if (map != null) {
            for (final Map.Entry<String, String> entry : map.entrySet()) {
                jsonObject3.put(entry.getKey(), entry.getValue());
            }
        }
        jsonObject2.put("query", s2);
        if (s3 == null) {
            s3 = (String)new JSONObject();
        }
        jsonObject2.put("payload", s3);
        final JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject);
        jsonArray.put(jsonObject2);
        return jsonArray.toString();
    }
}
