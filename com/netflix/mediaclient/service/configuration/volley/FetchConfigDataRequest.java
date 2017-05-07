// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.volley;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountConfigData;
import com.netflix.mediaclient.service.webclient.model.leafs.DeviceConfigData;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.ConfigData;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchConfigDataRequest extends FalkorVolleyWebClientRequest<ConfigData>
{
    private static final String ACCOUNT_CONFIG = "accountConfig";
    private static final String DEVICE_CONFIG = "deviceConfig";
    private static final String STREAMING_CONFIG = "streamingqoe";
    private static final String STREAMING_CONFIG_DEFAULT = "streamingqoeDefault";
    private static final String TAG = "nf_config_data";
    private static final String accountConfigPql;
    public static final String deviceConfigPql;
    private static final String streamingQoePql;
    public static final String streamingQoePqlDefault;
    private final ConfigurationAgentWebCallback responseCallback;
    
    static {
        deviceConfigPql = String.format("['%s']", "deviceConfig");
        accountConfigPql = String.format("['%s']", "accountConfig");
        streamingQoePql = String.format("['%s']", "streamingqoe");
        streamingQoePqlDefault = String.format("['%s']", "streamingqoeDefault");
    }
    
    public FetchConfigDataRequest(final Context context, final ConfigurationAgentWebCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        if (Log.isLoggable()) {
            Log.v("nf_config_data", "deviceConfigPql = " + FetchConfigDataRequest.deviceConfigPql);
            Log.v("nf_config_data", "accountConfigPql = " + FetchConfigDataRequest.accountConfigPql);
            Log.v("nf_config_data", "steamingqoePql = " + FetchConfigDataRequest.streamingQoePql);
        }
    }
    
    public static ConfigData parseConfigString(String jsonString) {
        final ConfigData configData = new ConfigData();
        if (Log.isLoggable()) {
            Log.v("nf_config_data", "String response to parse = " + jsonString);
        }
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_config_data", jsonString);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            Log.d("nf_config_data", "No config overrides for device");
            configData.deviceConfig = new DeviceConfigData();
            return configData;
        }
        if (dataObj.has("deviceConfig")) {
            configData.deviceConfig = FalkorParseUtils.getPropertyObject(dataObj, "deviceConfig", DeviceConfigData.class);
        }
        if (dataObj.has("accountConfig")) {
            if (Log.isLoggable()) {
                Log.v("nf_config_data", "Accnt config json: " + dataObj.get("accountConfig"));
            }
            configData.accountConfig = FalkorParseUtils.getPropertyObject(dataObj, "accountConfig", AccountConfigData.class);
            if (Log.isLoggable()) {
                final StringBuilder append = new StringBuilder().append("Parsed accnt config: ");
                if (configData.accountConfig == null) {
                    jsonString = "null";
                }
                else {
                    jsonString = configData.accountConfig.toJsonString();
                }
                Log.v("nf_config_data", append.append(jsonString).toString());
            }
        }
        if (dataObj.has("streamingqoe")) {
            final JsonElement value = dataObj.get("streamingqoe");
            if (value != null) {
                configData.streamingqoeJson = value.toString();
            }
        }
        if (dataObj.has("streamingqoeDefault")) {
            final JsonElement value2 = dataObj.get("streamingqoeDefault");
            if (value2 != null) {
                configData.streamingqoeJson = value2.toString();
            }
        }
        return configData;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(FetchConfigDataRequest.deviceConfigPql, FetchConfigDataRequest.accountConfigPql, FetchConfigDataRequest.streamingQoePql);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onConfigDataFetched(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final ConfigData configData) {
        if (this.responseCallback != null) {
            this.responseCallback.onConfigDataFetched(configData, CommonStatus.OK);
        }
    }
    
    @Override
    protected ConfigData parseFalkorResponse(final String s) {
        return parseConfigString(s);
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
