// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.volley;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountConfigData;
import com.netflix.mediaclient.service.webclient.model.leafs.DeviceConfigData;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.ConfigData;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchConfigDataRequest extends FalcorVolleyWebClientRequest<ConfigData>
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
        if (Log.isLoggable("nf_config_data", 2)) {
            Log.v("nf_config_data", "deviceConfigPql = " + FetchConfigDataRequest.deviceConfigPql);
            Log.v("nf_config_data", "accountConfigPql = " + FetchConfigDataRequest.accountConfigPql);
            Log.v("nf_config_data", "steamingqoePql = " + FetchConfigDataRequest.streamingQoePql);
        }
    }
    
    public static ConfigData parseConfigString(final String s) throws FalcorParseException, FalcorServerException {
        final ConfigData configData = new ConfigData();
        if (Log.isLoggable("nf_config_data", 2)) {
            Log.v("nf_config_data", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_config_data", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            Log.d("nf_config_data", "No config overrides for device");
            configData.deviceConfig = new DeviceConfigData();
        }
        else {
            if (dataObj.has("deviceConfig")) {
                configData.deviceConfig = FalcorParseUtils.getPropertyObject(dataObj, "deviceConfig", DeviceConfigData.class);
            }
            if (dataObj.has("accountConfig")) {
                if (Log.isLoggable("nf_config_data", 2)) {
                    Log.v("nf_config_data", "Accnt config: " + dataObj.get("accountConfig"));
                }
                configData.accountConfig = FalcorParseUtils.getPropertyObject(dataObj, "accountConfig", AccountConfigData.class);
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
                    return configData;
                }
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
    protected ConfigData parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        return parseConfigString(s);
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
