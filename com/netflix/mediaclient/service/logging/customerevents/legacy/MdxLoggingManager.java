// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.customerevents.legacy;

import org.apache.http.HttpException;
import java.io.IOException;
import android.os.Build$VERSION;
import com.netflix.mediaclient.service.ServiceAgent;
import org.json.JSONException;
import com.netflix.mediaclient.webapi.AuthorizationCredentials;
import com.netflix.mediaclient.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.LoggingAgent;
import android.os.Handler;

public class MdxLoggingManager
{
    private static final String EVENT485_NAME = "MDX Controller Start Playback";
    private static final String EVENT526_NAME = "MDX Target";
    private static final String EVENT537_NAME = "MDX Target Manager Action";
    private static final String EVENT_DATAFIELD_APPCONTEXT = "appContext";
    private static final String EVENT_DATAFIELD_APP_VERSION = "app_version";
    private static final String EVENT_DATAFIELD_CATALOGID = "catalogId";
    private static final String EVENT_DATAFIELD_COUNTRY = "country";
    private static final String EVENT_DATAFIELD_DEVICENAME = "deviceName";
    private static final String EVENT_DATAFIELD_DEVICE_CAT = "device_cat";
    private static final String EVENT_DATAFIELD_DEVICE_TYPE = "device_type";
    private static final String EVENT_DATAFIELD_DIALUUID = "dialUuid";
    private static final String EVENT_DATAFIELD_EPISODEID = "episodeId";
    private static final String EVENT_DATAFIELD_ESN = "esn";
    private static final String EVENT_DATAFIELD_EVENTTYPE = "eventType";
    private static final String EVENT_DATAFIELD_GEOLOCATION_COUNTRY = "geolocation_country";
    private static final String EVENT_DATAFIELD_LANGUAGES = "languages";
    private static final String EVENT_DATAFIELD_OS_VERSION = "os_version";
    private static final String EVENT_DATAFIELD_PLAYBACKFROM = "location";
    private static final String EVENT_DATAFIELD_SERVICETYPE = "serviceType";
    private static final String EVENT_DATAFIELD_TARGETUUID = "targetUuid";
    private static final String EVENT_DATAFIELD_TIMESTAMP = "timestamp";
    private static final String EVENT_DATAFIELD_UI_VERSION = "ui_version";
    private static final String EVENT_DATA_APPCONTEXT = "home";
    private static final String EVENT_DATA_DEVICENAME = "Android";
    private static final String EVENT_FIELD_DATA = "data";
    private static final String EVENT_FIELD_ESN = "Esn";
    private static final String EVENT_FIELD_EVENTNAME = "EventName";
    private static final String EVENT_FIELD_EVENTTIME = "EventTime";
    private static final String EVENT_FIELD_TRACKID = "TrackId";
    private static final String TAG = "nf_mdxMdxLoggingManager";
    private Handler mHandler;
    private LoggingAgent mOwner;
    
    public MdxLoggingManager(final Handler mHandler, final LoggingAgent mOwner) {
        this.mHandler = mHandler;
        this.mOwner = mOwner;
    }
    
    private void buildEventAndSend(final JSONObject jsonObject, final JSONObject jsonObject2, final String s, final String s2) {
        try {
            jsonObject.putOpt("Esn", (Object)this.mOwner.getConfigurationAgent().getEsnProvider().getEsn()).putOpt("EventTime", (Object)System.currentTimeMillis()).putOpt("data", (Object)jsonObject2);
            final JSONArray jsonArray = new JSONArray();
            jsonArray.put((Object)jsonObject);
            final String string = jsonArray.toString();
            Log.d("nf_mdxMdxLoggingManager", "buildEventAndSend plaintext data: " + string);
            this.sendEvent(new MdxCustomerEvent(this.mOwner.getConfigurationAgent().getEsnProvider().getEsn(), new AuthorizationCredentials(s, s2), string));
        }
        catch (JSONException ex) {
            Log.e("nf_mdxMdxLoggingManager", "buildEventAndSend fail " + ex);
        }
    }
    
    private JSONObject getCommonEventData(final ServiceAgent.UserAgentInterface userAgentInterface) {
        final JSONObject jsonObject = new JSONObject();
        Label_0047: {
            if (userAgentInterface == null) {
                break Label_0047;
            }
            try {
                jsonObject.putOpt("languages", (Object)userAgentInterface.getLanguagesInCsv()).putOpt("geolocation_country", (Object)userAgentInterface.getGeoCountry()).putOpt("country", (Object)userAgentInterface.getReqCountry());
                jsonObject.putOpt("timestamp", (Object)System.currentTimeMillis()).putOpt("ui_version", (Object)this.mOwner.getConfigurationAgent().getSoftwareVersion()).putOpt("app_version", (Object)this.mOwner.getConfigurationAgent().getSoftwareVersion()).putOpt("device_cat", (Object)this.mOwner.getConfigurationAgent().getDeviceCategory().name()).putOpt("os_version", (Object)("Android " + Build$VERSION.RELEASE)).putOpt("device_type", (Object)this.mOwner.getConfigurationAgent().getEsnProvider().getESNPrefix());
                return jsonObject;
            }
            catch (JSONException ex) {
                Log.e("nf_mdxMdxLoggingManager", "getCommonEventData failed " + ex);
                return jsonObject;
            }
        }
    }
    
    private void sendEvent(final MdxCustomerEvent mdxCustomerEvent) {
        this.mHandler.post((Runnable)new Runnable() {
            @Override
            public void run() {
                try {
                    mdxCustomerEvent.execute();
                }
                catch (IOException ex) {
                    Log.e("nf_mdxMdxLoggingManager", "sendEvent IOException " + ex);
                }
                catch (JSONException ex2) {
                    Log.e("nf_mdxMdxLoggingManager", "sendEvent JSONException " + ex2);
                }
                catch (HttpException ex3) {
                    Log.e("nf_mdxMdxLoggingManager", "sendEvent HttpException " + ex3);
                }
            }
        });
    }
    
    public void logPlaybackStart(final String s, final String s2, final String s3, final int n, final ServiceAgent.UserAgentInterface userAgentInterface) {
        if (userAgentInterface == null) {
            Log.e("nf_mdxMdxLoggingManager", "userAgent is null");
            return;
        }
        final JSONObject commonEventData = this.getCommonEventData(userAgentInterface);
        final JSONObject jsonObject = new JSONObject();
        try {
            commonEventData.putOpt("deviceName", (Object)"Android").putOpt("catalogId", (Object)s).putOpt("appContext", (Object)"home").putOpt("esn", (Object)this.mOwner.getConfigurationAgent().getEsnProvider().getEsn()).putOpt("episodeId", (Object)s2).putOpt("location", (Object)s3);
            jsonObject.putOpt("EventName", (Object)"MDX Controller Start Playback").putOpt("TrackId", (Object)n);
            this.buildEventAndSend(jsonObject, commonEventData, userAgentInterface.getUserCredentialRegistry().getNetflixID(), userAgentInterface.getUserCredentialRegistry().getSecureNetflixID());
        }
        catch (JSONException ex) {
            Log.e("nf_mdxMdxLoggingManager", "logPlaybackStart fail " + ex);
        }
    }
    
    public void logTarget(final String s, final String s2, final String s3, final String s4, final ServiceAgent.UserAgentInterface userAgentInterface) {
        if (userAgentInterface == null) {
            Log.e("nf_mdxMdxLoggingManager", "userAgent is null");
            return;
        }
        final JSONObject commonEventData = this.getCommonEventData(userAgentInterface);
        final JSONObject jsonObject = new JSONObject();
        try {
            commonEventData.putOpt("deviceName", (Object)"Android").putOpt("targetUuid", (Object)s2).putOpt("serviceType", (Object)s4).putOpt("eventType", (Object)s).putOpt("dialUuid", (Object)s3);
            jsonObject.putOpt("EventName", (Object)"MDX Target");
            this.buildEventAndSend(jsonObject, commonEventData, userAgentInterface.getUserCredentialRegistry().getNetflixID(), userAgentInterface.getUserCredentialRegistry().getSecureNetflixID());
        }
        catch (JSONException ex) {
            Log.e("nf_mdxMdxLoggingManager", "logTarget fail " + ex);
        }
    }
    
    public void logTargetSelection(final String s, final ServiceAgent.UserAgentInterface userAgentInterface) {
        if (userAgentInterface == null) {
            Log.e("nf_mdxMdxLoggingManager", "userAgent is null");
            return;
        }
        final JSONObject commonEventData = this.getCommonEventData(userAgentInterface);
        final JSONObject jsonObject = new JSONObject();
        try {
            commonEventData.putOpt("eventType", (Object)s);
            jsonObject.putOpt("EventName", (Object)"MDX Target Manager Action");
            this.buildEventAndSend(jsonObject, commonEventData, userAgentInterface.getUserCredentialRegistry().getNetflixID(), userAgentInterface.getUserCredentialRegistry().getSecureNetflixID());
        }
        catch (JSONException ex) {
            Log.e("nf_mdxMdxLoggingManager", "logTargetSelection fail " + ex);
        }
    }
    
    void setContext() {
    }
}
