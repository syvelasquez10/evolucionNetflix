// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.volley;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.util.StringUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.VoipAuthorizationData;
import com.netflix.mediaclient.service.webclient.model.leafs.SignInConfigData;
import com.netflix.mediaclient.service.webclient.model.leafs.NrmLanguagesData;
import com.netflix.mediaclient.service.webclient.model.leafs.NrmConfigData;
import com.netflix.mediaclient.service.webclient.model.leafs.CastKeyData;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountConfigData;
import com.netflix.mediaclient.service.webclient.model.leafs.DeviceConfigData;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.service.webclient.volley.FalkorException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.webclient.model.leafs.ConfigData;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchConfigDataRequest extends FalkorVolleyWebClientRequest<ConfigData>
{
    private static final String AB_TEST_CONFIG = "abTestConfig";
    private static final String ACCOUNT_CONFIG = "accountConfig";
    private static final String CAST_KEY_CONFIG = "castKey";
    private static final String CUSTOMER_SUPPORT_VOIP_AUTHORIZATIONS = "customerSupportVoipAuthorizations";
    private static final String DEVICE_CONFIG = "deviceConfig";
    private static final String NRM_INFO = "nrmInfo";
    private static final String NRM_LANG = "nrmLanguages";
    private static final String SIGNIN_CONFIG = "signInConfig";
    private static final String STREAMING_CONFIG = "streamingqoe";
    private static final String STREAMING_CONFIG_DEFAULT = "streamingqoeDefault";
    private static final String TAG = "nf_config_data";
    private static final String accountConfigPql;
    public static final String castKeyPql;
    public static final String customerSupportVoipPql;
    public static final String deviceConfigPql;
    public static final String nrmInfoPql;
    public static final String nrmLangPql;
    public static final String signInConfigPql;
    private static final String streamingQoePql;
    public static final String streamingQoePqlDefault;
    private String abTestConfigPql;
    private ServiceAgent$ConfigurationAgentInterface mConfigAgent;
    private final ConfigurationAgentWebCallback responseCallback;
    
    static {
        nrmInfoPql = String.format("['%s']", "nrmInfo");
        nrmLangPql = String.format("['%s']", "nrmLanguages");
        signInConfigPql = String.format("['%s']", "signInConfig");
        deviceConfigPql = String.format("['%s']", "deviceConfig");
        accountConfigPql = String.format("['%s']", "accountConfig");
        streamingQoePql = String.format("['%s']", "streamingqoe");
        streamingQoePqlDefault = String.format("['%s']", "streamingqoeDefault");
        customerSupportVoipPql = String.format("['%s']", "customerSupportVoipAuthorizations");
        castKeyPql = String.format("['%s']", "castKey");
    }
    
    public FetchConfigDataRequest(final Context context, final ServiceAgent$ConfigurationAgentInterface mConfigAgent, final ConfigurationAgentWebCallback responseCallback) {
        super(context);
        this.mConfigAgent = mConfigAgent;
        this.responseCallback = responseCallback;
        this.abTestConfigPql = String.format("['%s', '%s']", "abTestConfig", ABTestConfigData.getABTestIds(context));
        if (Log.isLoggable()) {
            Log.d("nf_config_data", "deviceConfigPql = " + FetchConfigDataRequest.deviceConfigPql);
            Log.d("nf_config_data", "accountConfigPql = " + FetchConfigDataRequest.accountConfigPql);
            Log.d("nf_config_data", "abTestConfigPql = " + this.abTestConfigPql);
            Log.d("nf_config_data", "steamingqoePql = " + FetchConfigDataRequest.streamingQoePql);
            Log.d("nf_config_data", "customerSupportVoipPql = " + FetchConfigDataRequest.customerSupportVoipPql);
            Log.d("nf_config_data", "castKey = " + FetchConfigDataRequest.castKeyPql);
        }
    }
    
    public static ConfigData parseConfigString(String s) {
        final ConfigData configData = new ConfigData();
        if (Log.isLoggable()) {
            Log.v("nf_config_data", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_config_data", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            Log.e("nf_config_data", "Empty response for configuration request!");
            final FalkorException ex = new FalkorException("Empty response for configuration request: " + s);
            ErrorLoggingManager.logHandledException(ex);
            throw ex;
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
                    s = "null";
                }
                else {
                    s = configData.accountConfig.toJsonString();
                }
                Log.v("nf_config_data", append.append(s).toString());
            }
        }
        if (dataObj.has("abTestConfig")) {
            if (Log.isLoggable()) {
                Log.v("nf_config_data", "AB Test config json: " + dataObj.get("abTestConfig"));
            }
            (configData.abTestConfigData = new ABTestConfigData(dataObj.get("abTestConfig").toString())).setRawABConfig(configData.abTestConfigData);
            if (Log.isLoggable()) {
                final StringBuilder append2 = new StringBuilder().append("Parsed AB Test config: ");
                if (configData.abTestConfigData == null) {
                    s = "null";
                }
                else {
                    s = configData.abTestConfigData.toJsonString();
                }
                Log.v("nf_config_data", append2.append(s).toString());
            }
        }
        if (dataObj.has("castKey")) {
            if (Log.isLoggable()) {
                Log.v("nf_config_data", "cast config json: " + dataObj.get("castKey"));
            }
            configData.castKeyData = FalkorParseUtils.getPropertyObject(dataObj, "castKey", CastKeyData.class);
            if (Log.isLoggable()) {
                final StringBuilder append3 = new StringBuilder().append("Parsed cast key config: ");
                if (configData.castKeyData == null) {
                    s = "null";
                }
                else {
                    s = configData.castKeyData.toJsonString();
                }
                Log.v("nf_config_data", append3.append(s).toString());
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
        if (dataObj.has("nrmInfo")) {
            if (Log.isLoggable()) {
                Log.v("nf_config_data", "NRM config json: " + dataObj.get("nrmInfo"));
            }
            configData.nrmInfo = FalkorParseUtils.getPropertyObject(dataObj, "nrmInfo", NrmConfigData.class);
            if (Log.isLoggable()) {
                final StringBuilder append4 = new StringBuilder().append("Parsed NRM config: ");
                if (configData.nrmInfo == null) {
                    s = "null";
                }
                else {
                    s = configData.nrmInfo.toJsonString();
                }
                Log.v("nf_config_data", append4.append(s).toString());
            }
        }
        if (dataObj.has("nrmLanguages")) {
            if (Log.isLoggable()) {
                Log.v("nf_config_data", "NRM config json: " + dataObj.get("nrmLanguages"));
            }
            configData.nrmLang = FalkorParseUtils.getPropertyObject(dataObj, "nrmLanguages", NrmLanguagesData.class);
            if (Log.isLoggable()) {
                final StringBuilder append5 = new StringBuilder().append("Parsed NRM config: ");
                if (configData.nrmLang == null) {
                    s = "null";
                }
                else {
                    s = configData.nrmLang.toJsonString();
                }
                Log.v("nf_config_data", append5.append(s).toString());
            }
        }
        if (dataObj.has("signInConfig")) {
            if (Log.isLoggable()) {
                Log.v("nf_config_data", "SignIn config json: " + dataObj.get("signInConfig"));
            }
            configData.signInConfigData = FalkorParseUtils.getPropertyObject(dataObj, "signInConfig", SignInConfigData.class);
            if (Log.isLoggable()) {
                final StringBuilder append6 = new StringBuilder().append("Parsed SingIn config: ");
                if (configData.signInConfigData == null) {
                    s = "null";
                }
                else {
                    s = configData.signInConfigData.toJsonString();
                }
                Log.v("nf_config_data", append6.append(s).toString());
            }
        }
        if (dataObj.has("customerSupportVoipAuthorizations")) {
            if (Log.isLoggable()) {
                Log.v("nf_config_data", "Customer Support VOIP authorizations json: " + dataObj.get("customerSupportVoipAuthorizations"));
            }
            configData.customerSupportVoipAuthorizations = FalkorParseUtils.getPropertyObject(dataObj, "customerSupportVoipAuthorizations", VoipAuthorizationData.class);
            if (Log.isLoggable()) {
                Log.v("nf_config_data", "Parsed VOIP authorizations: " + configData.customerSupportVoipAuthorizations);
            }
        }
        return configData;
    }
    
    @Override
    protected String getOptionalParams() {
        if (StringUtils.isEmpty(this.mConfigAgent.getChannelId())) {
            return null;
        }
        return new StringBuilder(FalkorVolleyWebClientRequest.urlEncodPQLParam("channelId", this.mConfigAgent.getChannelId())).toString();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(FetchConfigDataRequest.deviceConfigPql, FetchConfigDataRequest.accountConfigPql, this.abTestConfigPql, FetchConfigDataRequest.streamingQoePql, FetchConfigDataRequest.customerSupportVoipPql, FetchConfigDataRequest.castKeyPql);
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
