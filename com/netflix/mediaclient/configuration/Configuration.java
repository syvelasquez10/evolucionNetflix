// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.configuration;

import java.util.Iterator;
import com.netflix.mediaclient.NetflixApplication;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Configuration
{
    public static final String BOOKMARK_END_MARK = "bookmark-end-mark";
    public static final String BOOKMARK_START_MARK = "bookmark-start-mark";
    public static final String CRASH_REPORTING_SERVICE = "crash-reporting";
    private static final String[] PARAMETERS;
    private static final String TAG = "ConfigurationAPI";
    public static final String UI_LOADING_WORKFLOW_TYPE = "ui_loading_workflow_type";
    private static Map<String, String> appConfiguration;
    
    static {
        PARAMETERS = new String[] { "ui_loading_workflow_type", "bookmark-start-mark", "bookmark-end-mark", "crash-reporting" };
        Configuration.appConfiguration = new HashMap<String, String>();
    }
    
    public static String getApplicationConfigData(String s) {
        synchronized (Configuration.class) {
            s = Configuration.appConfiguration.get(s);
            return s;
        }
    }
    
    public static String[] getConfigList() {
        synchronized (Configuration.class) {
            final ArrayList<String> list = new ArrayList<String>();
            list.add("bookmark-start-mark");
            list.add("bookmark-end-mark");
            return list.toArray(new String[list.size()]);
        }
    }
    
    private static boolean isApplicationConfiguration(final String s) {
        final boolean b = false;
        int n = 0;
        boolean b2;
        while (true) {
            b2 = b;
            if (n >= Configuration.PARAMETERS.length) {
                break;
            }
            if (Configuration.PARAMETERS[n].equals(s)) {
                b2 = true;
                break;
            }
            ++n;
        }
        return b2;
    }
    
    public static void load(final Context context) {
        synchronized (Configuration.class) {
            final String stringPref = PreferenceUtils.getStringPref(context, "ui_loading_workflow_type", null);
            if (stringPref == null) {
                Log.d("ConfigurationAPI", "UI load workflow type NOT found in preferences.");
            }
            else if (Log.isLoggable("ConfigurationAPI", 3)) {
                Log.d("ConfigurationAPI", "UI load workflow type found in preferences with value: " + stringPref);
            }
        }
    }
    
    public static void setConfigData(final NetflixApplication netflixApplication, final String s, final String s2) {
        while (true) {
        Label_0061_Outer:
            while (true) {
                while (true) {
                    Label_0107: {
                        synchronized (Configuration.class) {
                            if (Log.isLoggable("ConfigurationAPI", 3)) {
                                Log.d("ConfigurationAPI", "nrdp.setConfigData: type =  " + s + ", data = " + s2);
                            }
                            break Label_0107;
                            Log.e("ConfigurationAPI", "PArameteres can not be null!");
                            return;
                            // iftrue(Label_0096:, !isApplicationConfiguration(s))
                            Log.d("ConfigurationAPI", "nrdp.setConfigData: application bookmark");
                            Configuration.appConfiguration.put(s, s2);
                            return;
                        }
                        Label_0096: {
                            Log.e("ConfigurationAPI", "Uknown property");
                        }
                        return;
                    }
                    if (s != null && s2 != null) {
                        continue;
                    }
                    break;
                }
                continue Label_0061_Outer;
            }
        }
    }
    
    public static void updateConfigData(final Context context, final Map<String, String> map) {
        synchronized (Configuration.class) {
            if (Log.isLoggable("ConfigurationAPI", 3)) {
                Log.d("ConfigurationAPI", "updateConfigData: parameters found:  " + map.size());
            }
            for (final String s : Configuration.appConfiguration.keySet()) {
                if (Log.isLoggable("ConfigurationAPI", 3)) {
                    Log.d("ConfigurationAPI", "updateConfigData: Removing: " + s);
                }
                PreferenceUtils.removePref(context, s);
            }
        }
        final String s2 = Configuration.appConfiguration.get("bookmark-start-mark");
        final String s3 = Configuration.appConfiguration.get("bookmark-end-mark");
        Configuration.appConfiguration.clear();
        if (map.size() > 0) {
            Configuration.appConfiguration.putAll(map);
            for (final String s4 : map.keySet()) {
                final String s5 = (String)map.get(s4);
                if (Log.isLoggable("ConfigurationAPI", 3)) {
                    Log.d("ConfigurationAPI", "updateConfigData: add parameter: " + s4 + " with value " + s5);
                }
                final Context context2;
                PreferenceUtils.putStringPref(context2, s4, s5);
            }
        }
        if (s2 != null) {
            if (Log.isLoggable("ConfigurationAPI", 3)) {
                Log.d("ConfigurationAPI", "updateConfigData: add parameter: bookmark-start-mark with value " + s2);
            }
            Configuration.appConfiguration.put("bookmark-start-mark", s2);
        }
        if (s3 != null) {
            if (Log.isLoggable("ConfigurationAPI", 3)) {
                Log.d("ConfigurationAPI", "updateConfigData: add parameter: bookmark-end-mark with value " + s3);
            }
            Configuration.appConfiguration.put("bookmark-end-mark", s3);
        }
    }
    // monitorexit(Configuration.class)
}
