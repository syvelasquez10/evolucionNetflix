// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.service.configuration.volley.FetchConfigDataRequest;
import com.netflix.mediaclient.util.MultiValuedHashMap;
import java.util.Map;
import com.netflix.mediaclient.util.Coppola2Utils;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.user.UserLocaleRepository;
import com.netflix.mediaclient.util.AppStoreHelper;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.util.UriUtil;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry$ResponsePathFormat;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.offline.agent.OfflineAgentInterface;
import android.content.Context;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;

public class EndpointRegistryProvider implements ApiEndpointRegistry
{
    private static final String ANDROID_API_ENDPOINT_PATH = "/android/4.13/api";
    private static final String ANDROID_CONFIG_ENDPOINT_FULL = "/android/samurai/config";
    private static final boolean BROWSE_AUTO_REDIRECT_TRUE = true;
    private static final String BROWSE_RESP_AUTO_REDIRECT = "&routing=redirect";
    private static final String BROWSE_RESP_FORMAT = "responseFormat=json&progressive=false";
    private static final String BROWSE_RESP_MANUAL_REDIRECT = "&routing=reject";
    private static final String CLIENT_LOGGING_ENDPOINT = "ichnaea.netflix.com";
    private static final String CLIENT_LOGGING_PATH = "/log";
    private static final String ENDPOINT_REVISION_LATEST = "&revision=latest";
    private static final String HTTP = "http://";
    private static final String HTTPS = "https://";
    private static final String IMG_PREFERENCE_JPG = "jpg";
    private static final String IMG_PREFERENCE_WEBP = "webp";
    private static final String PARAM_API_LEVEL = "api";
    private static final String PARAM_APP_INSTALL_STORE = "store";
    private static final String PARAM_APP_TYPE = "appType";
    private static final String PARAM_APP_VERSION = "appVersion";
    private static final String PARAM_APP_VERSION_CODE = "appVer";
    private static final String PARAM_BUILD_BOARD = "osBoard";
    private static final String PARAM_BUILD_DEVICE = "osDevice";
    private static final String PARAM_BUILD_DISPLAY = "osDisplay";
    private static final String PARAM_DEBUG_BUILD = "dbg";
    private static final String PARAM_DEVICE_LOCALE = "deviceLocale";
    private static final String PARAM_DEVICE_MEM_LEVEL = "memLevel";
    private static final String PARAM_DOWNLOAD_ENABLED = "dlEnabled";
    private static final String PARAM_ESN = "esn";
    private static final String PARAM_FORM_FACTOR = "ffbc";
    private static final String PARAM_IMG_TYPE_PREFERENCE = "imgpref";
    private static final String PARAM_IS_DEVICE_LOCALE_IN_APP = "lackLocale";
    private static final String PARAM_KUBRICK_KIDS_EXPERIENCE = "kk";
    private static final String PARAM_LANGUAGES = "languages";
    private static final String PARAM_LOLOMO_TEST_TYPE = "ltType";
    private static final String PARAM_MANUFACTURER = "mnf";
    private static final String PARAM_MODEL_ID = "mId";
    private static final String PARAM_PATH_FORMAT = "pathFormat";
    private static final String PARAM_PQL_PATH = "path";
    private static final String PARAM_PROFILE_TYPE = "prfType";
    private static final String PARAM_PROGRESSIVE = "progressive";
    private static final String PARAM_RESOLUTION = "res";
    private static final String PARAM_RESPONSE_FORMAT = "responseFormat";
    private static final String PARAM_REVISION = "revision";
    private static final String PARAM_ROUTING = "routing";
    private static final String PARAM_VIDEO_CAPABILITY = "qlty";
    private static final String PRESENTATION_TRACKING_ENDPOINT = "presentationtracking.netflix.com";
    private static final String PRESENTATION_TRACKING_PATH = "/users/presentationtracking";
    private static final String TAG = "EndpointRegistryProvider";
    private static final String WEBCLIENT_ENDPOINT = "api-global.netflix.com";
    private String mAppBootUrl;
    private String mCachedEndpointUrl;
    private String mClientLogEndpointUrl;
    private String mConfigEndpointUrl;
    private ServiceAgent$ConfigurationAgentInterface mConfigurationAgent;
    private final Context mContext;
    private String mEndpointHost;
    private OfflineAgentInterface mOfflineAgent;
    private String mPresentationTrackingUrl;
    private ServiceAgent$UserAgentInterface mUserAgent;
    
    public EndpointRegistryProvider(final Context mContext, final ServiceAgent$UserAgentInterface mUserAgent, final ConfigurationAgent mConfigurationAgent, final OfflineAgentInterface mOfflineAgent) {
        this.mContext = mContext;
        this.mConfigurationAgent = mConfigurationAgent;
        this.mUserAgent = mUserAgent;
        this.mOfflineAgent = mOfflineAgent;
        this.mEndpointHost = "api-global.netflix.com";
    }
    
    private String addDynamicParams(final StringBuilder sb, final ApiEndpointRegistry$ResponsePathFormat apiEndpointRegistry$ResponsePathFormat) {
        if (this.mUserAgent != null && StringUtils.isNotEmpty(this.mUserAgent.getCurrentAppLanguage())) {
            sb.append(this.buildUrlParam("languages", UriUtil.urlEncodeParam(this.mUserAgent.getCurrentAppLanguage())));
        }
        if (this.mUserAgent != null && KidsUtils.isKidsProfile(this.mUserAgent.getCurrentProfile())) {
            sb.append(this.buildUrlParam("prfType", this.mUserAgent.getCurrentProfile().getProfileType().toString()));
            if (BrowseExperience.showKidsExperience()) {
                sb.append(this.buildUrlParam("kk", Boolean.TRUE.toString()));
            }
        }
        if (this.mOfflineAgent != null && this.mOfflineAgent.isOfflineFeatureEnabled()) {
            sb.append(this.buildUrlParam("dlEnabled", Boolean.TRUE.toString()));
        }
        sb.append(apiEndpointRegistry$ResponsePathFormat.urlParams);
        return sb.toString();
    }
    
    private String buildConfigUrl(final boolean b) {
        final StringBuilder sb = new StringBuilder();
        if (this.isSecure()) {
            sb.append("https://");
        }
        else {
            sb.append("http://");
        }
        sb.append(this.mEndpointHost);
        sb.append("/android/samurai/config");
        sb.append("?");
        sb.append("responseFormat=json&progressive=false");
        sb.append(ApiEndpointRegistry$ResponsePathFormat.HIERARCHICAL.urlParams);
        String s;
        if (b) {
            s = "&routing=redirect";
        }
        else {
            s = "&routing=reject";
        }
        sb.append(s);
        sb.append(this.buildUrlParam("appType", this.mConfigurationAgent.getDeviceModel().getAppType()));
        sb.append(this.buildUrlParam("dbg", String.valueOf(false)));
        final DeviceModel deviceModel = this.mConfigurationAgent.getDeviceModel();
        String s2;
        if (this.mConfigurationAgent.isDeviceHd()) {
            s2 = "hd";
        }
        else {
            s2 = "sd";
        }
        sb.append(this.buildUrlParam("qlty", s2));
        sb.append(this.buildUrlParam("ffbc", deviceModel.getFormFactor()));
        sb.append(this.buildUrlParam("osBoard", UriUtil.urlEncodeParam(deviceModel.getBuildPropBoard())));
        sb.append(this.buildUrlParam("osDevice", UriUtil.urlEncodeParam(deviceModel.getBuildPropDevice())));
        sb.append(this.buildUrlParam("osDisplay", UriUtil.urlEncodeParam(deviceModel.getBuildPropDisplay())));
        sb.append(this.buildUrlParam("appVer", Integer.toString(deviceModel.getAppVersionCode())));
        sb.append(this.buildUrlParam("appVersion", UriUtil.urlEncodeParam(deviceModel.getAppVersion())));
        sb.append(this.buildUrlParam("mId", UriUtil.urlEncodeParam(deviceModel.getEsnModelId())));
        sb.append(this.buildUrlParam("api", Integer.toString(deviceModel.getApiLevel())));
        sb.append(this.buildUrlParam("mnf", UriUtil.urlEncodeParam(deviceModel.getManufacturer())));
        sb.append(this.buildUrlParam("store", AppStoreHelper.getInstallationSource(this.mContext)));
        sb.append(this.buildUrlParam("memLevel", ConfigurationAgent.getMemLevel()));
        sb.append(this.buildUrlParam("lackLocale", String.valueOf(UserLocaleRepository.isApkMissingDeviceLocaleSupport())));
        sb.append(this.buildUrlParam("deviceLocale", String.valueOf(UserLocaleRepository.getDeviceLocale().getLanguage())));
        final String string = sb.toString();
        if (Log.isLoggable()) {
            Log.v("EndpointRegistryProvider", "Config URL: " + string);
        }
        return string;
    }
    
    private String buildUrlParam(final String s, final String s2) {
        return UriUtil.buildUrlParam(s, s2);
    }
    
    public static String getEndpointPath(final ServiceManager serviceManager) {
        final String apiUrlFull = serviceManager.getConfiguration().getApiEndpointRegistry().getApiUrlFull(ApiEndpointRegistry$ResponsePathFormat.GRAPH);
        return apiUrlFull.substring(0, apiUrlFull.indexOf(63));
    }
    
    private String getImagePreference() {
        final String imagePreference = this.mConfigurationAgent.getImagePreference();
        if (StringUtils.isNotEmpty(imagePreference)) {
            return imagePreference;
        }
        return "webp";
    }
    
    private String getLoLoMoABTestPreference() {
        if (Coppola2Utils.isCoppolaDiscovery(this.mContext)) {
            return "c2";
        }
        return null;
    }
    
    @Override
    public Map<String, String> getApiRequestParams(final ApiEndpointRegistry$ResponsePathFormat apiEndpointRegistry$ResponsePathFormat) {
        synchronized (this) {
            final MultiValuedHashMap<String, String> multiValuedHashMap = new MultiValuedHashMap<String, String>();
            multiValuedHashMap.put("responseFormat", "json");
            multiValuedHashMap.put("progressive", "false");
            multiValuedHashMap.put("routing", "reject");
            multiValuedHashMap.put("ffbc", this.mConfigurationAgent.getDeviceModel().getFormFactor());
            if (this.mUserAgent != null && StringUtils.isNotEmpty(this.mUserAgent.getCurrentAppLanguage())) {
                multiValuedHashMap.put("languages", this.mUserAgent.getCurrentAppLanguage());
            }
            if (this.mUserAgent != null && KidsUtils.isKidsProfile(this.mUserAgent.getCurrentProfile())) {
                multiValuedHashMap.put("prfType", this.mUserAgent.getCurrentProfile().getProfileType().toString());
                if (BrowseExperience.showKidsExperience()) {
                    multiValuedHashMap.put("kk", Boolean.TRUE.toString());
                }
            }
            if (apiEndpointRegistry$ResponsePathFormat != null) {
                multiValuedHashMap.put("pathFormat", apiEndpointRegistry$ResponsePathFormat.value);
            }
            else {
                if (Log.isLoggable()) {
                    Log.w("EndpointRegistryProvider", "Response format is NOT known, go for GRAPH for request " + this.getClass().getSimpleName());
                }
                multiValuedHashMap.put("pathFormat", ApiEndpointRegistry$ResponsePathFormat.GRAPH.value);
            }
            multiValuedHashMap.put("res", this.mConfigurationAgent.getImageResolutionClass().urlParamValue);
            multiValuedHashMap.put("imgpref", this.mConfigurationAgent.getImagePreference());
            multiValuedHashMap.put("imgpref", this.getImagePreference());
            if (this.mOfflineAgent != null && this.mOfflineAgent.isOfflineFeatureEnabled()) {
                multiValuedHashMap.put("dlEnabled", Boolean.TRUE.toString());
            }
            return multiValuedHashMap;
        }
    }
    
    @Override
    public String getApiUri(final String s) {
        final StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(this.mEndpointHost);
        if (s != null) {
            sb.append(s);
        }
        sb.append("/android/4.13/api");
        return sb.toString();
    }
    
    @Override
    public String getApiUrlFull(final ApiEndpointRegistry$ResponsePathFormat apiEndpointRegistry$ResponsePathFormat) {
        if (StringUtils.isNotEmpty(this.mCachedEndpointUrl)) {
            return this.addDynamicParams(new StringBuilder(this.mCachedEndpointUrl), apiEndpointRegistry$ResponsePathFormat);
        }
        final StringBuilder sb = new StringBuilder();
        if (this.isSecure()) {
            sb.append("https://");
        }
        else {
            sb.append("http://");
        }
        sb.append(this.mEndpointHost);
        sb.append("/android/4.13/api");
        sb.append("?");
        sb.append("responseFormat=json&progressive=false");
        sb.append("&routing=reject");
        sb.append(this.buildUrlParam("res", this.mConfigurationAgent.getImageResolutionClass().urlParamValue));
        sb.append(this.buildUrlParam("imgpref", this.getImagePreference()));
        sb.append(this.buildUrlParam("ffbc", this.mConfigurationAgent.getDeviceModel().getFormFactor()));
        sb.append(this.buildUrlParam("appVersion", UriUtil.urlEncodeParam(this.mConfigurationAgent.getDeviceModel().getAppVersion())));
        final String loLoMoABTestPreference = this.getLoLoMoABTestPreference();
        if (loLoMoABTestPreference != null) {
            sb.append(this.buildUrlParam("ltType", loLoMoABTestPreference));
        }
        if (this.mUserAgent != null && this.mUserAgent.getCurrentProfile() != null) {
            this.mCachedEndpointUrl = sb.toString();
            if (Log.isLoggable()) {
                Log.v("EndpointRegistryProvider", "Set mCachedEndpointUrl to: " + this.mCachedEndpointUrl);
            }
        }
        return this.addDynamicParams(sb, apiEndpointRegistry$ResponsePathFormat);
    }
    
    public String getAppStartConfigUrl() {
        return this.buildConfigUrl(true) + this.buildUrlParam("path", UriUtil.urlEncodeParam(FetchConfigDataRequest.nrmInfoPql)) + this.buildUrlParam("path", UriUtil.urlEncodeParam(FetchConfigDataRequest.signInConfigPql)) + this.buildUrlParam("path", UriUtil.urlEncodeParam(FetchConfigDataRequest.deviceConfigPql)) + this.buildUrlParam("path", UriUtil.urlEncodeParam(FetchConfigDataRequest.streamingQoePqlDefault));
    }
    
    @Override
    public String getAppbootUri(final String s) {
        if (StringUtils.isNotEmpty(this.mAppBootUrl)) {
            return this.mAppBootUrl;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append("appboot.netflix.com");
        sb.append("/appboot/").append(s);
        return this.mAppBootUrl = sb.toString();
    }
    
    @Override
    public String getClientLoggingUrlFull() {
        synchronized (this) {
            String s;
            if (StringUtils.isNotEmpty(this.mClientLogEndpointUrl)) {
                s = this.mClientLogEndpointUrl;
            }
            else {
                final StringBuilder sb = new StringBuilder();
                if (this.isSecure()) {
                    sb.append("https://");
                }
                else {
                    sb.append("http://");
                }
                sb.append("ichnaea.netflix.com");
                sb.append("/log");
                this.mClientLogEndpointUrl = sb.toString();
                s = this.mClientLogEndpointUrl;
            }
            return s;
        }
    }
    
    @Override
    public String getConfigUrlFull() {
        if (StringUtils.isNotEmpty(this.mConfigEndpointUrl)) {
            return this.mConfigEndpointUrl;
        }
        return this.mConfigEndpointUrl = this.buildConfigUrl(false);
    }
    
    public String getCustomerSupportAuthTokensUrl(final String s) {
        return this.buildConfigUrl(true) + this.buildUrlParam("path", UriUtil.urlEncodeParam(FetchConfigDataRequest.customerSupportVoipPql)) + this.buildUrlParam("esn", UriUtil.urlEncodeParam(s));
    }
    
    @Override
    public String getNccpUri(final String s) {
        return this.getApiUri(s);
    }
    
    @Override
    public String getPresentationTrackingUrlFull() {
        synchronized (this) {
            String s;
            if (StringUtils.isNotEmpty(this.mPresentationTrackingUrl)) {
                s = this.mPresentationTrackingUrl;
            }
            else {
                final StringBuilder sb = new StringBuilder();
                if (this.isSecure()) {
                    sb.append("https://");
                }
                else {
                    sb.append("http://");
                }
                sb.append("presentationtracking.netflix.com");
                sb.append("/users/presentationtracking");
                this.mPresentationTrackingUrl = sb.toString();
                s = this.mPresentationTrackingUrl;
            }
            return s;
        }
    }
    
    public boolean isSecure() {
        return true;
    }
    
    public void setUserAgentInterface(final ServiceAgent$UserAgentInterface mUserAgent) {
        this.mUserAgent = mUserAgent;
    }
    
    @Override
    public void updateApiEndpointHost(final String mEndpointHost) {
        this.mEndpointHost = mEndpointHost;
        this.mCachedEndpointUrl = null;
        this.mConfigEndpointUrl = null;
    }
}
