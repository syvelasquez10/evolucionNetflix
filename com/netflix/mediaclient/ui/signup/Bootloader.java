// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import java.net.URLEncoder;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.NrdpComponent;
import com.netflix.mediaclient.Log;
import android.net.Uri;
import com.netflix.mediaclient.servicemgr.ServiceManager;

final class Bootloader
{
    private static final String PARAMETER_DEVICE_CATEGORY = "device_cat";
    private static final String PARAMETER_DEVICE_LANGUAGE = "locale";
    private static final String PARAMETER_DEVICE_TYPE = "device_type";
    private static final String PARAMETER_FULL_ESN = "esn";
    private static final String PARAMETER_IS_PRELOADED = "isNetflixPreloaded";
    private static final String PARAMETER_OS = "os";
    private static final String PARAMETER_SDK_VERSION = "sdk_version";
    private static final String PARAMETER_SHARED_SESSION_UUID = "uuid";
    private static final String PARAMETER_SOFTWARE_VERSION = "sw_version";
    private static final String TAG = "SignupActivity";
    private String mUrl;
    
    Bootloader(final ServiceManager serviceManager, final String s, final String s2, final boolean b, final String s3) {
        if (serviceManager == null) {
            throw new IllegalArgumentException("Service Manager can not be null!");
        }
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
        sb.append("esn").append('=').append(this.urlEncode(serviceManager.getESNProvider().getEsn())).append('&');
        sb.append("device_type").append('=').append(this.urlEncode(serviceManager.getESNProvider().getESNPrefix())).append('&');
        sb.append("sdk_version").append('=').append(this.urlEncode(serviceManager.getNrdpComponentVersion(NrdpComponent.NrdLib))).append('&');
        sb.append("sw_version").append('=').append(this.urlEncode(serviceManager.getSoftwareVersion())).append('&');
        sb.append("os").append('=').append(String.valueOf(AndroidUtils.getAndroidVersion())).append('&');
        sb.append("device_cat").append('=').append(this.urlEncode(serviceManager.getDeviceCategory().getValue())).append('&');
        sb.append("locale").append('=').append(this.urlEncode(s2)).append('&');
        final StringBuilder append = sb.append("isNetflixPreloaded").append('=');
        String s4;
        if (b) {
            s4 = "true";
        }
        else {
            s4 = "false";
        }
        append.append(s4);
        if (s3 != null) {
            sb.append('&').append("uuid").append('=').append(s3);
        }
        this.mUrl = sb.toString();
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
}
