// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.signup;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.SessionKey;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.servicemgr.NrdpComponent;
import com.netflix.mediaclient.Log;
import android.net.Uri;
import com.netflix.mediaclient.servicemgr.ServiceManager;

final class Bootloader
{
    private static final String ACTIVE_SESSIONS = "activeSessions";
    private static final String PARAMETER_DEVICE_CATEGORY = "device_cat";
    private static final String PARAMETER_DEVICE_LANGUAGE = "locale";
    private static final String PARAMETER_DEVICE_TYPE = "device_type";
    private static final String PARAMETER_FULL_ESN = "esn";
    private static final String PARAMETER_IS_PRELOADED = "isNetflixPreloaded";
    private static final String PARAMETER_LOG_DATA = "logData";
    private static final String PARAMETER_OS = "os";
    private static final String PARAMETER_SDK_VERSION = "sdk_version";
    private static final String PARAMETER_SOFTWARE_VERSION = "sw_version";
    private static final String SEQUENCE = "sequence";
    private static final String TAG = "SignupActivity";
    private String mUrl;
    
    Bootloader(final ServiceManager serviceManager, String s, final String s2, final boolean b) {
        if (serviceManager == null) {
            throw new IllegalArgumentException("Service Manager can not be null!");
        }
        final StringBuilder sb = new StringBuilder();
        final String query = Uri.parse(s).getQuery();
        Log.d("SignupActivity", "URL queryParam: " + query);
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
        if (b) {
            s = "true";
        }
        else {
            s = "false";
        }
        append.append(s).append('&');
        sb.append("logData").append('=').append(this.getLogData(serviceManager));
        this.mUrl = sb.toString();
    }
    
    private boolean addSession(final SessionKey sessionKey) {
        return sessionKey != null && ("appSession".equals(sessionKey.getName()) || "userSession".equals(sessionKey.getName()));
    }
    
    private String getLogData(final ServiceManager serviceManager) {
        final String s = "";
        if (serviceManager == null || serviceManager.getClientLogging() == null) {
            return "";
        }
        final List<SessionKey> activeLoggingSessions = serviceManager.getClientLogging().getActiveLoggingSessions();
        if (activeLoggingSessions == null || activeLoggingSessions.size() < 1) {
            return "";
        }
        JSONObject jsonObject = null;
        Label_0161: {
            String string;
            try {
                jsonObject = new JSONObject();
                final JSONArray jsonArray = new JSONArray();
                jsonObject.put("activeSessions", (Object)jsonArray);
                jsonObject.put("sequence", (Object)String.valueOf(serviceManager.getClientLogging().getNextSequence()));
                for (final SessionKey sessionKey : activeLoggingSessions) {
                    if (this.addSession(sessionKey)) {
                        jsonArray.put((Object)sessionKey.toJSONArray());
                    }
                }
                break Label_0161;
            }
            catch (Throwable t) {
                Log.e("SignupActivity", "Failed to create JSON", t);
                string = s;
            }
            return this.urlEncode(string);
        }
        String string = jsonObject.toString();
        return this.urlEncode(string);
    }
    
    public String getUrl() {
        Log.d("SignupActivity", "URL : " + this.mUrl);
        return this.mUrl;
    }
    
    public String urlEncode(final String s) {
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
}
