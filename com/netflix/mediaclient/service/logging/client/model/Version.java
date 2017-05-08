// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.client.model;

import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import android.os.Build$VERSION;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.util.AndroidManifestUtils;
import android.content.Context;
import com.google.gson.annotations.Since;

public class Version
{
    public static final String APP = "app";
    public static final String MDXJS = "mdxjs";
    public static final String MDXLIB = "mdxlib";
    public static final String NRDAPP = "nrdapp";
    public static final String NRDLIB = "nrdlib";
    public static final String NRDSDK = "nrdsdk";
    public static final String OS = "os";
    public static final String UI = "ui";
    @Since(1.0)
    private String app;
    @Since(1.1)
    private String mdxjs;
    @Since(1.0)
    private String mdxlib;
    @Since(1.0)
    private String nrdapp;
    @Since(1.0)
    private String nrdlib;
    @Since(1.1)
    private String nrdsdk;
    @Since(1.0)
    private String os;
    @Since(1.0)
    private String ui;
    
    public Version() {
    }
    
    Version(final Context context) {
        this.app = AndroidManifestUtils.getVersion(context);
        this.ui = this.app;
        this.nrdapp = SecurityRepository.getNrdAppVersion();
        this.nrdlib = SecurityRepository.getNrdLibVersion();
        this.mdxlib = SecurityRepository.getMdxLibVersion();
        this.mdxjs = SecurityRepository.getMdxJsVersion();
        this.nrdsdk = SecurityRepository.getNrdSdkVersion();
        this.os = Build$VERSION.RELEASE;
    }
    
    public static Version createInstance(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        final Version version = new Version();
        version.app = JsonUtils.getString(jsonObject, "app", (String)null);
        version.ui = JsonUtils.getString(jsonObject, "ui", (String)null);
        version.nrdapp = JsonUtils.getString(jsonObject, "nrdapp", (String)null);
        version.nrdlib = JsonUtils.getString(jsonObject, "nrdlib", (String)null);
        version.mdxlib = JsonUtils.getString(jsonObject, "mdxlib", (String)null);
        version.mdxjs = JsonUtils.getString(jsonObject, "mdxjs", (String)null);
        version.nrdsdk = JsonUtils.getString(jsonObject, "nrdsdk", (String)null);
        version.os = JsonUtils.getString(jsonObject, "os", (String)null);
        return version;
    }
    
    public String getApp() {
        return this.app;
    }
    
    public String getMdxjs() {
        return this.mdxjs;
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
    
    public String getNrdsdk() {
        return this.nrdsdk;
    }
    
    public String getOs() {
        return this.os;
    }
    
    public String getUi() {
        return this.ui;
    }
    
    public JSONObject toJSONObject() {
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
        if (this.mdxjs != null) {
            jsonObject.put("mdxjs", (Object)this.mdxjs);
        }
        if (this.nrdsdk != null) {
            jsonObject.put("nrdsdk", (Object)this.nrdsdk);
        }
        if (this.os != null) {
            jsonObject.put("os", (Object)this.os);
        }
        return jsonObject;
    }
    
    @Override
    public String toString() {
        return "Version [app=" + this.app + ", ui=" + this.ui + ", nrdapp=" + this.nrdapp + ", nrdlib=" + this.nrdlib + ", mdxlib=" + this.mdxlib + ", mdxjs=" + this.mdxjs + ", nrdsdk=" + this.nrdsdk + "os=" + this.os + "]";
    }
}
