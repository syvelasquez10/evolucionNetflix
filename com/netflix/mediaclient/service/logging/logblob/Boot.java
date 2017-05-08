// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.logblob;

import org.json.JSONException;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import org.json.JSONObject;
import android.content.Context;

public class Boot extends BaseLogblob
{
    public Boot(final Context context, final String s) {
    }
    
    @Override
    public String getType() {
        return "boot";
    }
    
    @Override
    public void init(final Context context, final String s, final String s2) {
        super.init(context, s, s2);
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("appid", (Object)s);
            jsonObject.put("buildDate", (Object)"20170502");
            jsonObject.put("buildTime", (Object)"181400");
            jsonObject.put("build_id", AndroidManifestUtils.getVersionCode(context));
            jsonObject.put("crashReportClient", (Object)"on");
            jsonObject.put("debug", false);
            jsonObject.put("production", true);
            jsonObject.put("version", (Object)AndroidManifestUtils.getVersion(context));
            jsonObject.put("versionString", (Object)StringUtils.notEmpty(AndroidManifestUtils.getVersionName(context), ""));
            final WindowManager windowManager = (WindowManager)context.getSystemService("window");
            if (windowManager != null) {
                final JSONObject jsonObject2 = new JSONObject();
                final DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                jsonObject2.put("width", displayMetrics.widthPixels);
                jsonObject2.put("height", displayMetrics.heightPixels);
                jsonObject.put("screen", (Object)jsonObject2);
                jsonObject.put("ui", (Object)jsonObject2);
            }
            final JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("mdxlib", (Object)"2013.3");
            jsonObject3.put("nrdlib", (Object)"2013.2");
            jsonObject3.put("nrdp", (Object)"4.0.4");
            final JSONObject jsonObject4 = new JSONObject();
            jsonObject3.put("platform", (Object)jsonObject4);
            jsonObject4.put("platformVersion", (Object)AndroidManifestUtils.getVersion(context));
            jsonObject.put("components", (Object)jsonObject3);
            this.mJson.put("msg", (Object)jsonObject);
        }
        catch (JSONException ex) {}
    }
}
