// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.presentation;

import org.json.JSONException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import java.util.Iterator;
import java.util.Collection;
import com.netflix.mediaclient.Log;
import java.util.LinkedList;
import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import com.netflix.mediaclient.service.ServiceAgent$UserAgentInterface;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;

public class PresentationRequest
{
    public static final String API = "sw_version";
    public static final String APPLICATION_NAME = "application_name";
    public static final String APPLICATION_VER = "application_v";
    public static final String APP_NAME_VAL = "andorid";
    public static final String APP_NAME_VAL_KUBRICK = "android/kubrick";
    public static final String COUNTRY = "country";
    protected static final boolean DEBUG = false;
    public static final String DEVICE_TYPE = "device_type";
    public static final String ESN = "esn";
    public static final String EVENTS = "data";
    public static final String NRDP_VERSION = "sdk_version";
    private static String TAG;
    public static final long USER_GLANCE_TIME = 300L;
    private String api;
    private String app_name;
    private String app_ver;
    private Context context;
    private String country;
    private String device_type;
    private String esn;
    private List<PresentationEvent> events;
    private String nrdp_ver;
    
    static {
        PresentationRequest.TAG = "nf_presentation";
    }
    
    public PresentationRequest() {
        this.events = new ArrayList<PresentationEvent>();
    }
    
    public PresentationRequest(final Context context, final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface, final ServiceAgent$UserAgentInterface serviceAgent$UserAgentInterface) {
        this.events = new ArrayList<PresentationEvent>();
        this.context = context;
        this.esn = serviceAgent$ConfigurationAgentInterface.getEsnProvider().getEsn();
        this.country = serviceAgent$UserAgentInterface.getGeoCountry();
        this.device_type = serviceAgent$ConfigurationAgentInterface.getEsnProvider().getDeviceModel();
        this.app_ver = AndroidManifestUtils.getVersion(context);
        this.nrdp_ver = SecurityRepository.getNrdLibVersion();
        this.api = Integer.toString(AndroidUtils.getAndroidVersion());
        this.app_name = this.getApplicationName(serviceAgent$ConfigurationAgentInterface);
    }
    
    private static void addToSlidingWindow(final LinkedList<PresentationEvent> list, final PresentationEvent presentationEvent, final int n) {
        if (list.size() >= n) {
            final PresentationEvent presentationEvent2 = list.removeFirst();
            Log.d(PresentationRequest.TAG, String.format("t %d, row %d, rank %d,  %s, %s - !Drop", presentationEvent2.getTime(), presentationEvent2.getRow(), presentationEvent2.getRank(), presentationEvent2.getVideoIds(), presentationEvent2.getVideoImageTypeIdentifierIds()));
        }
        list.add(presentationEvent);
    }
    
    private static List<PresentationEvent> filterFastScrollEvents(final Context context, final List<PresentationEvent> list) {
        final ArrayList<Object> list2 = (ArrayList<Object>)new ArrayList<PresentationEvent>();
        final LinkedList<PresentationEvent> list3 = new LinkedList<PresentationEvent>();
        final int numPagesVisibileToUser = getNumPagesVisibileToUser(context);
        for (final PresentationEvent presentationEvent : list) {
            if (shouldFlushSlidingWindow(list3, presentationEvent, numPagesVisibileToUser)) {
                list2.addAll(list3);
                list3.clear();
            }
            addToSlidingWindow(list3, presentationEvent, numPagesVisibileToUser);
        }
        list2.addAll(list3);
        return (List<PresentationEvent>)list2;
    }
    
    private String getApplicationName(final ServiceAgent$ConfigurationAgentInterface serviceAgent$ConfigurationAgentInterface) {
        if (BrowseExperience.isKubrick()) {
            return "android/kubrick";
        }
        return "andorid";
    }
    
    private static int getNumPagesVisibileToUser(final Context context) {
        int n = 2;
        if (DeviceUtils.isPortrait(context)) {
            n = 3;
            if (DeviceUtils.isTabletByContext(context)) {
                n = 4;
            }
        }
        return n;
    }
    
    private static boolean shouldFlushSlidingWindow(final LinkedList<PresentationEvent> list, final PresentationEvent presentationEvent, final int n) {
        if (list.size() == 0) {
            return false;
        }
        final PresentationEvent presentationEvent2 = list.getLast();
        return presentationEvent2.getRow() == presentationEvent.getRow() || !StringUtils.safeEquals(presentationEvent2.getLocation(), presentationEvent.getLocation()) || presentationEvent.getTime() - presentationEvent2.getTime() > 300L;
    }
    
    public void addAllEvent(final List<PresentationEvent> list) {
        this.events.addAll(filterFastScrollEvents(this.context, list));
    }
    
    public void initFromString(final String s) {
        if (StringUtils.isEmpty(s)) {
            throw new IllegalArgumentException("Cant create PT request from empty string");
        }
        final JSONObject jsonObject = new JSONObject(s);
        this.esn = JsonUtils.getString(jsonObject, "esn", null);
        this.country = JsonUtils.getString(jsonObject, "country", null);
        this.device_type = JsonUtils.getString(jsonObject, "device_type", null);
        this.app_name = JsonUtils.getString(jsonObject, "application_name", null);
        this.app_ver = JsonUtils.getString(jsonObject, "application_v", null);
        this.nrdp_ver = JsonUtils.getString(jsonObject, "sdk_version", null);
        this.api = JsonUtils.getString(jsonObject, "sw_version", null);
        final JSONArray jsonArray = new JSONArray(JsonUtils.getString(jsonObject, "data", null));
        for (int i = 0; i < jsonArray.length(); ++i) {
            final PresentationEvent instance = PresentationEvent.createInstance(jsonArray.getJSONObject(i));
            if (instance != null) {
                this.events.add(instance);
            }
        }
    }
    
    public JSONObject toJSONObject() {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("esn", (Object)this.esn);
        if (StringUtils.isNotEmpty(this.country)) {
            jsonObject.putOpt("country", (Object)this.country);
        }
        jsonObject.putOpt("device_type", (Object)this.device_type);
        jsonObject.putOpt("application_name", (Object)this.app_name);
        jsonObject.putOpt("application_v", (Object)this.app_ver);
        jsonObject.putOpt("sdk_version", (Object)this.nrdp_ver);
        jsonObject.putOpt("sw_version", (Object)this.api);
        if (this.events != null) {
            final JSONArray jsonArray = new JSONArray();
            jsonObject.put("data", (Object)jsonArray);
            final Iterator<PresentationEvent> iterator = this.events.iterator();
            while (iterator.hasNext()) {
                final JSONArray jsonArray2 = iterator.next().toJSONArray();
                for (int i = 0; i < jsonArray2.length(); ++i) {
                    jsonArray.put((Object)jsonArray2.getJSONObject(i));
                }
            }
        }
        return jsonObject;
    }
    
    public Map<String, String> toRequestParams() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("esn", this.esn);
        hashMap.put("country", this.country);
        hashMap.put("device_type", this.device_type);
        hashMap.put("application_name", this.app_name);
        hashMap.put("application_v", this.app_ver);
        hashMap.put("sdk_version", this.nrdp_ver);
        hashMap.put("sw_version", this.api);
        if (this.events != null) {
            final JSONArray jsonArray = new JSONArray();
            for (final PresentationEvent presentationEvent : this.events) {
                Log.d(PresentationRequest.TAG, String.format("t %d, row %d, rank %d, %s, %s - sending", presentationEvent.getTime(), presentationEvent.getRow(), presentationEvent.getRank(), presentationEvent.getVideoIds(), presentationEvent.getVideoImageTypeIdentifierIds()));
                try {
                    final JSONArray jsonArray2 = presentationEvent.toJSONArray();
                    for (int i = 0; i < jsonArray2.length(); ++i) {
                        jsonArray.put((Object)jsonArray2.getJSONObject(i));
                    }
                    continue;
                }
                catch (JSONException ex) {
                    Log.d(PresentationRequest.TAG, String.format("Error in event json exception %s", ex));
                    continue;
                }
                break;
            }
            hashMap.put("data", jsonArray.toString());
        }
        return hashMap;
    }
}
