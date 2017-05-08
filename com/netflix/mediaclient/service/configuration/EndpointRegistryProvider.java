// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.service.configuration.volley.FetchConfigDataRequest;
import com.netflix.mediaclient.util.Coppola2Utils;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.user.UserLocaleRepository;
import com.netflix.mediaclient.util.AppStoreHelper;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.util.UriUtil;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry$ResponsePathFormat;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;

public class EndpointRegistryProvider implements ApiEndpointRegistry
{
    private static final String ANDROID_CONFIG_ENDPOINT_FULL = "/android/samurai/config";
    private static final String ANDROID_ENDPOINT_FULL = "/android/4.10/api";
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
    private static final String PARAM_ESN = "esn";
    private static final String PARAM_FORM_FACTOR = "ffbc";
    private static final String PARAM_IMG_TYPE_PREFERENCE = "imgpref";
    private static final String PARAM_IS_DEVICE_LOCALE_IN_APP = "lackLocale";
    private static final String PARAM_KUBRICK_KIDS_EXPERIENCE = "kk";
    private static final String PARAM_LANGUAGES = "languages";
    private static final String PARAM_LOLOMO_TEST_TYPE = "ltType";
    private static final String PARAM_MANUFACTURER = "mnf";
    private static final String PARAM_MODEL_ID = "mId";
    private static final String PARAM_PQL_PATH = "path";
    private static final String PARAM_PROFILE_TYPE = "prfType";
    private static final String PARAM_RESOLUTION = "res";
    private static final String PARAM_VIDEO_CAPABILITY = "qlty";
    private static final String PRESENTATION_TRACKING_ENDPOINT = "presentationtracking.netflix.com";
    private static final String PRESENTATION_TRACKING_PATH = "/users/presentationtracking";
    private static final String TAG = "EndpointRegistryProvider";
    private static final String WEBCLIENT_ENDPOINT = "api-global.netflix.com";
    private String mCachedEndpointUrl;
    private String mClientLogEndpointUrl;
    private String mConfigEndpointUrl;
    private final Context mContext;
    private final Boolean mDeviceHd;
    private final DeviceModel mDeviceModel;
    private String mEndpointHost;
    private final ImagePrefRepository mImagePrefRepository;
    private final ImageResolutionClass mImageResolutionClass;
    private String mPresentationTrackingUrl;
    private ServiceAgent$UserAgentInterface mUserAgent;
    
    public EndpointRegistryProvider(final Context mContext, final DeviceModel mDeviceModel, final Boolean mDeviceHd, final ImagePrefRepository mImagePrefRepository, final ImageResolutionClass mImageResolutionClass) {
        this.mContext = mContext;
        this.mDeviceModel = mDeviceModel;
        this.mDeviceHd = mDeviceHd;
        this.mImagePrefRepository = mImagePrefRepository;
        this.mImageResolutionClass = mImageResolutionClass;
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
        sb.append(this.buildUrlParam("appType", this.mDeviceModel.getAppType()));
        sb.append(this.buildUrlParam("dbg", String.valueOf(false)));
        String s2;
        if (this.mDeviceHd) {
            s2 = "hd";
        }
        else {
            s2 = "sd";
        }
        sb.append(this.buildUrlParam("qlty", s2));
        sb.append(this.buildUrlParam("ffbc", this.mDeviceModel.getFormFactor()));
        sb.append(this.buildUrlParam("osBoard", UriUtil.urlEncodeParam(this.mDeviceModel.getBuildPropBoard())));
        sb.append(this.buildUrlParam("osDevice", UriUtil.urlEncodeParam(this.mDeviceModel.getBuildPropDevice())));
        sb.append(this.buildUrlParam("osDisplay", UriUtil.urlEncodeParam(this.mDeviceModel.getBuildPropDisplay())));
        sb.append(this.buildUrlParam("appVer", Integer.toString(this.mDeviceModel.getAppVersionCode())));
        sb.append(this.buildUrlParam("appVersion", UriUtil.urlEncodeParam(this.mDeviceModel.getAppVersion())));
        sb.append(this.buildUrlParam("mId", UriUtil.urlEncodeParam(this.mDeviceModel.getEsnModelId())));
        sb.append(this.buildUrlParam("api", Integer.toString(this.mDeviceModel.getApiLevel())));
        sb.append(this.buildUrlParam("mnf", UriUtil.urlEncodeParam(this.mDeviceModel.getManufacturer())));
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
    
    private String getImagePreference() {
        final String imgPreference = this.mImagePrefRepository.getImgPreference();
        if (StringUtils.isNotEmpty(imgPreference)) {
            return imgPreference;
        }
        if (AndroidUtils.getAndroidVersion() >= 14) {
            return "webp";
        }
        return "jpg";
    }
    
    private String getLoLoMoABTestPreference() {
        if (Coppola2Utils.isCoppolaDiscovery(this.mContext)) {
            return "c2";
        }
        return null;
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
        sb.append("/android/4.10/api");
        sb.append("?");
        sb.append("responseFormat=json&progressive=false");
        sb.append("&routing=reject");
        sb.append(this.buildUrlParam("res", this.mImageResolutionClass.urlParamValue));
        sb.append(this.buildUrlParam("imgpref", this.getImagePreference()));
        sb.append(this.buildUrlParam("ffbc", this.mDeviceModel.getFormFactor()));
        sb.append(this.buildUrlParam("appVersion", UriUtil.urlEncodeParam(this.mDeviceModel.getAppVersion())));
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
