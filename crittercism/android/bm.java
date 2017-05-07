// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import java.io.OutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public final class bm implements ch
{
    private JSONObject a;
    private String b;
    
    public bm(final au au) {
        this.b = cg.a.a();
        try {
            this.a = new JSONObject().put("appID", (Object)au.a()).put("deviceID", (Object)au.c()).put("crPlatform", (Object)"android").put("crVersion", (Object)au.d()).put("deviceModel", (Object)au.j()).put("osName", (Object)"android").put("osVersion", (Object)au.k()).put("carrier", (Object)au.f()).put("mobileCountryCode", au.g()).put("mobileNetworkCode", au.h()).put("appVersion", (Object)au.b()).put("locale", (Object)new bx$k().a);
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
