// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.service.configuration.volley.FetchConfigDataRequest;
import com.netflix.mediaclient.util.AppStoreHelper;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.util.UriUtil;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.ApiEndpointRegistry;

public class EndpointRegistryProvider implements ApiEndpointRegistry
{
    private static final String ANDROID_CONFIG_ENDPOINT_FULL = "/android/samurai/config";
    private static final String ANDROID_ENDPOINT_FULL = "/android/pathEvaluator/3.3.0";
    private static final String BROWSE_RESP_FORMAT_FULL = "responseFormat=json&pathFormat=hierarchical&progressive=false&routing=reject";
    private static final String CLIENT_LOGGING_ENDPOINT = "ichnaea.netflix.com";
    private static final String CLIENT_LOGGING_PATH = "/log";
    protected static final String HTTP = "http://";
    protected static final String HTTPS = "https://";
    public static final String IMG_PREFERENCE_JPG = "jpg";
    public static final String IMG_PREFERENCE_WEBP = "webp";
    private static final String PARAM_API_LEVEL = "api";
    private static final String PARAM_APK_VERSION = "appVer";
    private static final String PARAM_APP_INSTALL_STORE = "store";
    private static final String PARAM_APP_TYPE = "appType";
    private static final String PARAM_BUILD_BOARD = "osBoard";
    private static final String PARAM_BUILD_DEVICE = "osDevice";
    private static final String PARAM_BUILD_DISPLAY = "osDisplay";
    public static final String PARAM_CUR_NETFLIX_ID_HASH = "ckh";
    private static final String PARAM_DEBUG_BUILD = "dbg";
    private static final String PARAM_FORM_FACTOR = "ffbc";
    private static final String PARAM_IMG_PREFERENCE = "imgpref";
    private static final String PARAM_LANGUAGES = "languages";
    private static final String PARAM_MANUFACTURER = "mnf";
    private static final String PARAM_MODEL_ID = "mId";
    private static final String PARAM_PQL_PATH = "path";
    private static final String PARAM_RESOLUTION = "res";
    private static final String PARAM_VIDEO_CAPABILITY = "qlty";
    private static final String PRESENTATION_TRACKING_ENDPOINT = "presentationtracking.netflix.com";
    private static final String PRESENTATION_TRACKING_PATH = "/users/presentationtracking";
    private static final String WEBCLIENT_ENDPOINT = "api-global.netflix.com";
    private String mApiEndpointUrl;
    private String mClientLogEndpointUrl;
    private String mConfigEndpointUrl;
    private final Context mContext;
    private final Boolean mDeviceHd;
    private final DeviceModel mDeviceModel;
    private String mEndpointHost;
    private final ImagePrefRepository mImagePrefRepository;
    private String mPresentationTrackingUrl;
    private final String mUiResolutionType;
    private ServiceAgent.UserAgentInterface mUserAgentInterface;
    
    public EndpointRegistryProvider(final Context mContext, final DeviceModel mDeviceModel, final Boolean mDeviceHd, final ImagePrefRepository mImagePrefRepository, final String mUiResolutionType) {
        this.mContext = mContext;
        this.mDeviceModel = mDeviceModel;
        this.mDeviceHd = mDeviceHd;
        this.mImagePrefRepository = mImagePrefRepository;
        this.mUiResolutionType = mUiResolutionType;
        this.mEndpointHost = "api-global.netflix.com";
    }
    
    private StringBuilder addLanguages(final StringBuilder sb) {
        if (this.mUserAgentInterface != null && StringUtils.isNotEmpty(this.mUserAgentInterface.getLanguagesInCsv())) {
            sb.append(this.buildUrlParam("languages", UriUtil.urlEncodeParam(this.mUserAgentInterface.getLanguagesInCsv())));
        }
        return sb;
    }
    
    private String buildUrlParam(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder("&");
        sb.append(s);
        sb.append("=");
        sb.append(s2);
        return sb.toString();
    }
    
    private String getImagePreference() {
        final String imgPreference = this.mImagePrefRepository.getImgPreference();
        if (StringUtils.isNotEmpty(imgPreference)) {
            return imgPreference;
        }
        String s;
        if (AndroidUtils.getAndroidVersion() >= 14) {
            s = "webp";
        }
        else {
            s = "jpg";
        }
        return s;
    }
    
    @Override
    public String getApiUrlFull() {
        if (StringUtils.isNotEmpty(this.mApiEndpointUrl)) {
            return this.addLanguages(new StringBuilder(this.mApiEndpointUrl)).toString();
        }
        final StringBuilder sb = new StringBuilder();
        if (this.isSecure()) {
            sb.append("https://");
        }
        else {
            sb.append("http://");
        }
        sb.append(this.mEndpointHost);
        sb.append("/android/pathEvaluator/3.3.0");
        sb.append("?");
        sb.append("responseFormat=json&pathFormat=hierarchical&progressive=false&routing=reject");
        sb.append(this.buildUrlParam("res", this.mUiResolutionType));
        sb.append(this.buildUrlParam("imgpref", this.getImagePreference()));
        this.mApiEndpointUrl = sb.toString();
        return this.addLanguages(sb).toString();
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
        sb.append("responseFormat=json&pathFormat=hierarchical&progressive=false&routing=reject");
        sb.append(this.buildUrlParam("appType", this.mDeviceModel.getAppType()));
        String s;
        if (this.mDeviceHd) {
            s = "hd";
        }
        else {
            s = "sd";
        }
        sb.append(this.buildUrlParam("qlty", s));
        sb.append(this.buildUrlParam("ffbc", this.mDeviceModel.getFormFactor()));
        sb.append(this.buildUrlParam("osBoard", UriUtil.urlEncodeParam(this.mDeviceModel.getBuildPropBoard())));
        sb.append(this.buildUrlParam("osDevice", UriUtil.urlEncodeParam(this.mDeviceModel.getBuildPropDevice())));
        sb.append(this.buildUrlParam("osDisplay", UriUtil.urlEncodeParam(this.mDeviceModel.getBuildPropDisplay())));
        sb.append(this.buildUrlParam("appVer", Integer.toString(this.mDeviceModel.getApkVer())));
        sb.append(this.buildUrlParam("mId", UriUtil.urlEncodeParam(this.mDeviceModel.getEsnModelId())));
        sb.append(this.buildUrlParam("api", Integer.toString(this.mDeviceModel.getApiLevel())));
        sb.append(this.buildUrlParam("mnf", UriUtil.urlEncodeParam(this.mDeviceModel.getManufacturer())));
        sb.append(this.buildUrlParam("store", AppStoreHelper.getInstallationSource(this.mContext)));
        return this.mConfigEndpointUrl = sb.toString();
    }
    
    public String getDeviceConfigUrl() {
        return this.getConfigUrlFull() + this.buildUrlParam("path", UriUtil.urlEncodeParam(FetchConfigDataRequest.deviceConfigPql));
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
    
    public void setUserAgentInterface(final ServiceAgent.UserAgentInterface mUserAgentInterface) {
        this.mUserAgentInterface = mUserAgentInterface;
    }
    
    @Override
    public void updateApiEndpointHost(final String mEndpointHost) {
        this.mEndpointHost = mEndpointHost;
        this.mApiEndpointUrl = null;
        this.mConfigEndpointUrl = null;
    }
}
