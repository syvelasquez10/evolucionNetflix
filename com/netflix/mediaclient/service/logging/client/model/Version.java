// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import org.json.JSONException;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import android.content.Context;
import com.google.gson.annotations.Since;

public class Version
{
    public static final String APP = "app";
    public static final String MDXLIB = "mdxlib";
    public static final String NRDAPP = "nrdapp";
    public static final String NRDLIB = "nrdlib";
    public static final String UI = "ui";
    @Since(1.0)
    private String app;
    @Since(1.0)
    private String mdxlib;
    @Since(1.0)
    private String nrdapp;
    @Since(1.0)
    private String nrdlib;
    @Since(1.0)
    private String ui;
    
    public Version() {
    }
    
    Version(final Context context) {
        this.app = AndroidManifestUtils.getVersionName(context);
        this.ui = this.app;
        this.nrdapp = SecurityRepository.getNrdAppVersion();
        this.nrdlib = SecurityRepository.getNrdLibVersion();
        this.mdxlib = SecurityRepository.getMdxLibVersion();
    }
    
    public static Version createInstance(final JSONObject jsonObject) throws JSONException {
        if (jsonObject == null) {
            return null;
        }
        final Version version = new Version();
        version.app = JsonUtils.getString(jsonObject, "app", null);
        version.ui = JsonUtils.getString(jsonObject, "ui", null);
        version.nrdapp = JsonUtils.getString(jsonObject, "nrdapp", null);
        version.nrdlib = JsonUtils.getString(jsonObject, "nrdlib", null);
        version.mdxlib = JsonUtils.getString(jsonObject, "mdxlib", null);
        return version;
    }
    
    public String getApp() {
        return this.app;
    }
    
    public String getMdxlib() {
        return this.mdxlib;
    }
    
    public String getNrdapp() {
        return this.nrdapp;
    }
    
    public String getNrdlib() {
        return this.nrdlib;
    }
    
    public String getUi() {
        return this.ui;
    }
    
    public JSONObject toJSONObject() throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        if (this.app != null) {
            jsonObject.put("app", (Object)this.app);
        }
        if (this.ui != null) {
            jsonObject.put("ui", (Object)this.ui);
        }
        if (this.nrdapp != null) {
            jsonObject.put("nrdapp", (Object)this.nrdapp);
        }
        if (this.nrdlib != null) {
            jsonObject.put("nrdlib", (Object)this.nrdlib);
        }
        if (this.mdxlib != null) {
            jsonObject.put("mdxlib", (Object)this.mdxlib);
        }
        return jsonObject;
    }
}
