// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.lang.reflect.Method;
import android.util.Log;
import java.util.Map;
import java.util.Collection;
import org.json.JSONObject;
import org.json.JSONArray;
import java.util.Iterator;
import android.content.ComponentName;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.content.Context;

public class MeasurementEvent
{
    public static final String APP_LINK_NAVIGATE_IN_EVENT_NAME = "al_nav_in";
    public static final String APP_LINK_NAVIGATE_OUT_EVENT_NAME = "al_nav_out";
    public static final String MEASUREMENT_EVENT_ARGS_KEY = "event_args";
    public static final String MEASUREMENT_EVENT_NAME_KEY = "event_name";
    public static final String MEASUREMENT_EVENT_NOTIFICATION_NAME = "com.parse.bolts.measurement_event";
    private Context appContext;
    private Bundle args;
    private String name;
    
    private MeasurementEvent(final Context context, final String name, final Bundle args) {
        this.appContext = context.getApplicationContext();
        this.name = name;
        this.args = args;
    }
    
    private static Bundle getApplinkLogData(final Context context, String s, final Bundle bundle, final Intent intent) {
        final Bundle bundle2 = new Bundle();
        final ComponentName resolveActivity = intent.resolveActivity(context.getPackageManager());
        if (resolveActivity != null) {
            bundle2.putString("class", resolveActivity.getShortClassName());
        }
        if ("al_nav_out".equals(s)) {
            if (resolveActivity != null) {
                bundle2.putString("package", resolveActivity.getPackageName());
            }
            if (intent.getData() != null) {
                bundle2.putString("outputURL", intent.getData().toString());
            }
            if (intent.getScheme() != null) {
                bundle2.putString("outputURLScheme", intent.getScheme());
            }
        }
        else if ("al_nav_in".equals(s)) {
            if (intent.getData() != null) {
                bundle2.putString("inputURL", intent.getData().toString());
            }
            if (intent.getScheme() != null) {
                bundle2.putString("inputURLScheme", intent.getScheme());
            }
        }
        final Iterator<String> iterator = bundle.keySet().iterator();
        while (iterator.hasNext()) {
            s = iterator.next();
            final Object value = bundle.get(s);
            if (value instanceof Bundle) {
                for (final String s2 : ((Bundle)value).keySet()) {
                    final String objectToJSONString = objectToJSONString(((Bundle)value).get(s2));
                    if (s.equals("referer_app_link")) {
                        if (s2.equalsIgnoreCase("url")) {
                            bundle2.putString("refererURL", objectToJSONString);
                            continue;
                        }
                        if (s2.equalsIgnoreCase("app_name")) {
                            bundle2.putString("refererAppName", objectToJSONString);
                            continue;
                        }
                        if (s2.equalsIgnoreCase("package")) {
                            bundle2.putString("sourceApplication", objectToJSONString);
                            continue;
                        }
                    }
                    bundle2.putString(s + "/" + s2, objectToJSONString);
                }
            }
            else {
                final String objectToJSONString2 = objectToJSONString(value);
                if (s.equals("target_url")) {
                    final Uri parse = Uri.parse(objectToJSONString2);
                    bundle2.putString("targetURL", parse.toString());
                    bundle2.putString("targetURLHost", parse.getHost());
                }
                else {
                    bundle2.putString(s, objectToJSONString2);
                }
            }
        }
        return bundle2;
    }
    
    private static String objectToJSONString(final Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof JSONArray || o instanceof JSONObject) {
            return o.toString();
        }
        try {
            if (o instanceof Collection) {
                return new JSONArray((Collection)o).toString();
            }
            if (o instanceof Map) {
                return new JSONObject((Map)o).toString();
            }
            return o.toString();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private void sendBroadcast() {
        if (this.name == null) {
            Log.d(this.getClass().getName(), "Event name is required");
        }
        try {
            final Class<?> forName = Class.forName("android.support.v4.content.LocalBroadcastManager");
            final Method method = forName.getMethod("getInstance", Context.class);
            final Method method2 = forName.getMethod("sendBroadcast", Intent.class);
            final Object invoke = method.invoke(null, this.appContext);
            final Intent intent = new Intent("com.parse.bolts.measurement_event");
            intent.putExtra("event_name", this.name);
            intent.putExtra("event_args", this.args);
            method2.invoke(invoke, intent);
        }
        catch (Exception ex) {
            Log.d(this.getClass().getName(), "LocalBroadcastManager in android support library is required to raise bolts event.");
        }
    }
    
    static void sendBroadcastEvent(final Context context, final String s, final Intent intent, final Map<String, String> map) {
        final Bundle bundle = new Bundle();
        while (true) {
            Label_0190: {
                if (intent == null) {
                    break Label_0190;
                }
                final Bundle appLinkData = AppLinks.getAppLinkData(intent);
                final Bundle applinkLogData;
                if (appLinkData != null) {
                    applinkLogData = getApplinkLogData(context, s, appLinkData, intent);
                }
                else {
                    final Uri data = intent.getData();
                    if (data != null) {
                        bundle.putString("intentData", data.toString());
                    }
                    final Bundle extras = intent.getExtras();
                    if (extras != null) {
                        for (final String s2 : extras.keySet()) {
                            bundle.putString(s2, objectToJSONString(extras.get(s2)));
                        }
                    }
                    break Label_0190;
                }
                if (map != null) {
                    for (final String s3 : map.keySet()) {
                        applinkLogData.putString(s3, (String)map.get(s3));
                    }
                }
                new MeasurementEvent(context, s, applinkLogData).sendBroadcast();
                return;
            }
            Bundle applinkLogData = bundle;
            continue;
        }
    }
}
