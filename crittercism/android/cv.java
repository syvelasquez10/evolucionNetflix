// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.OutputStream;
import org.json.JSONArray;
import java.util.HashMap;
import java.util.Map;

public final class cv implements cx
{
    public Map a;
    
    public cv(final au au) {
        (this.a = new HashMap()).put("app_id", au.a());
        this.a.put("hashed_device_id", au.c());
        this.a.put("library_version", "5.0.6");
    }
    
    public final cv a(final String s, final String s2) {
        this.a.put(s, s2);
        return this;
    }
    
    public final cv a(final String s, final JSONArray jsonArray) {
        this.a.put(s, jsonArray);
        return this;
    }
    
    @Override
    public final void a(final OutputStream outputStream) {
        dy.b();
        outputStream.write(new JSONObject(this.a).toString().getBytes("UTF8"));
    }
    
    @Override
    public final String toString() {
        try {
            return new JSONObject(this.a).toString(4);
        }
        catch (JSONException ex) {
            dy.a();
            return null;
        }
    }
}
