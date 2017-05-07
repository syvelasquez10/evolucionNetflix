// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountConfigData;
import com.netflix.mediaclient.service.webclient.model.leafs.DeviceConfigData;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.ConfigData;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchConfigDataRequest extends FalcorVolleyWebClientRequest<ConfigData>
{
    private static final String FIELD_ACCOUNT_CONFIG = "accountConfig";
    private static final String FIELD_DEVICE_CONFIG = "deviceConfig";
    private static final String FIELD_STREAMING_CONFIG = "streamingqoe";
    private static final String TAG = "nf_config_data";
    private final String pqlQuery1;
    private final String pqlQuery2;
    private final String pqlQuery3;
    private final ConfigurationAgentWebCallback responseCallback;
    
    public FetchConfigDataRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final ConfigurationAgentWebCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.pqlQuery1 = new StringBuilder("['deviceConfig']").toString();
        this.pqlQuery2 = new StringBuilder("['accountConfig']").toString();
        this.pqlQuery3 = new StringBuilder("['streamingqoe']").toString();
        if (Log.isLoggable("nf_config_data", 2)) {
            Log.v("nf_config_data", "PQL1 = " + this.pqlQuery1);
            Log.v("nf_config_data", "PQL2 = " + this.pqlQuery2);
            Log.v("nf_config_data", "PQL3 = " + this.pqlQuery3);
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
                configData.accountConfig = FalcorParseUtils.getPropertyObject(dataObj, "accountConfig", AccountConfigData.class);
            }
            if (dataObj.has("streamingqoe")) {
                final JsonElement value = dataObj.get("streamingqoe");
                if (value != null) {
                    configData.streamingqoeJson = value.toString();
                    return configData;
                }
            }
        }
        return configData;
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery1, this.pqlQuery2, this.pqlQuery3 };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onConfigDataFetched(null, n);
        }
    }
    
    @Override
    protected void onSuccess(final ConfigData configData) {
        if (this.responseCallback != null) {
            this.responseCallback.onConfigDataFetched(configData, 0);
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
