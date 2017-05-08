// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.OutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public final class bk implements cf
{
    private JSONObject a;
    private String b;
    
    public bk(final ar ar) {
        this.b = ce.a.a();
        try {
            this.a = new JSONObject().put("appID", (Object)ar.a()).put("deviceID", (Object)ar.c()).put("crPlatform", (Object)"android").put("crVersion", (Object)ar.d()).put("deviceModel", (Object)ar.j()).put("osName", (Object)"android").put("osVersion", (Object)ar.k()).put("carrier", (Object)ar.f()).put("mobileCountryCode", ar.g()).put("mobileNetworkCode", ar.h()).put("appVersion", (Object)ar.b()).put("locale", (Object)new bv$k().a);
        }
        catch (JSONException ex) {}
    }
    
    @Override
    public final void a(final OutputStream outputStream) {
        outputStream.write(this.a.toString().getBytes());
    }
    
    @Override
    public final String e() {
        return this.b;
    }
}
