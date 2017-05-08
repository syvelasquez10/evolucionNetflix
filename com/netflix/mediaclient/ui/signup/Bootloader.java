// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.NrdpComponent;
import android.net.Uri;
import com.netflix.mediaclient.Log;
import java.net.URLEncoder;
import com.netflix.mediaclient.servicemgr.ServiceManager;

final class Bootloader
{
    private static final String PARAMETER_DEVICE_CATEGORY = "device_cat";
    private static final String PARAMETER_DEVICE_LANGUAGE = "locale";
    private static final String PARAMETER_FULL_ESN = "esn";
    private static final String PARAMETER_IS_INAPP_FLOW = "inapp";
    private static final String PARAMETER_IS_PRELOADED = "isNetflixPreloaded";
    private static final String PARAMETER_NRD_DEVICE_MODEL = "nrdDeviceModel";
    private static final String PARAMETER_OS = "os";
    private static final String PARAMETER_PLAYBILLING = "isPlayBillingEnabled";
    private static final String PARAMETER_SDK_VERSION = "sdk_version";
    private static final String PARAMETER_SHARED_SESSION_UUID = "uuid";
    private static final String PARAMETER_SOFTWARE_VERSION = "sw_version";
    private static final String PARAMETER_VIDEO_ID = "titleVideoId";
    private static final String TAG = "SignupActivity";
    private boolean mIsPlayBillingEnabled;
    private boolean mIsPreloaded;
    private String mLang;
    private ServiceManager mSvcManager;
    private String mUrl;
    private String mUuid;
    private String mVideoId;
    
    Bootloader(final ServiceManager mSvcManager, final String url, final String mLang, final boolean mIsPreloaded, final boolean mIsPlayBillingEnabled, final String mUuid) {
        if (mSvcManager == null) {
            throw new IllegalArgumentException("Service Manager can not be null!");
        }
        this.mSvcManager = mSvcManager;
        this.mLang = mLang;
        this.mIsPreloaded = mIsPreloaded;
        this.mIsPlayBillingEnabled = mIsPlayBillingEnabled;
        this.mUuid = mUuid;
        this.setUrl(url);
    }
    
    private String urlEncode(final String s) {
        if (s == null) {
            return "";
        }
        try {
            return URLEncoder.encode(s, "UTF-8");
        }
        catch (Exception ex) {
            return s;
        }
    }
    
    public String getUrl() {
        if (Log.isLoggable()) {
            Log.d("SignupActivity", "URL : " + this.mUrl);
        }
        return this.mUrl;
    }
    
    public void setUrl(String s) {
        final StringBuilder sb = new StringBuilder();
        final String query = Uri.parse(s).getQuery();
        if (Log.isLoggable()) {
            Log.d("SignupActivity", "URL queryParam: " + query);
        }
        if (query != null) {
            sb.append(s).append('&');
        }
        else {
            sb.append(s).append("?");
        }
        sb.append("esn").append('=').append(this.urlEncode(this.mSvcManager.getESNProvider().getEsn())).append('&');
        sb.append("sdk_version").append('=').append(this.urlEncode(this.mSvcManager.getNrdpComponentVersion(NrdpComponent.NrdLib))).append('&');
        sb.append("sw_version").append('=').append(this.urlEncode(this.mSvcManager.getSoftwareVersion())).append('&');
        sb.append("os").append('=').append(String.valueOf(AndroidUtils.getAndroidVersion())).append('&');
        sb.append("device_cat").append('=').append(this.urlEncode(this.mSvcManager.getDeviceCategory().getValue())).append('&');
        sb.append("locale").append('=').append(this.urlEncode(this.mLang)).append('&');
        sb.append("inapp").append("=true&");
        final StringBuilder append = sb.append("isNetflixPreloaded").append('=');
        if (this.mIsPreloaded) {
            s = "true";
        }
        else {
            s = "false";
        }
        append.append(s).append('&');
        final StringBuilder append2 = sb.append("isPlayBillingEnabled").append('=');
        if (this.mIsPlayBillingEnabled) {
            s = "true";
        }
        else {
            s = "false";
        }
        append2.append(s);
        if (this.mUuid != null) {
            sb.append('&').append("uuid").append('=').append(this.mUuid);
        }
        if (this.mVideoId != null) {
            sb.append('&').append("titleVideoId").append('=').append(this.mVideoId);
        }
        this.mUrl = sb.toString();
    }
    
    public void setVideoId(final String mVideoId) {
        this.mVideoId = mVideoId;
    }
}
