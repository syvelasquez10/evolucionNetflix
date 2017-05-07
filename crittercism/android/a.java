// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONArray;
import java.util.List;
import org.json.JSONObject;

public final class a
{
    JSONObject a;
    
    private a(final au au, final List list) {
        this.a = new JSONObject();
        list.size();
        final JSONArray jsonArray = new JSONArray();
        final JSONArray jsonArray2 = new JSONArray();
        jsonArray2.put((Object)au.a());
        jsonArray2.put((Object)au.b());
        jsonArray2.put((Object)au.c());
        jsonArray2.put((Object)"5.0.6");
        jsonArray2.put(au.e());
        jsonArray.put((Object)jsonArray2);
        final JSONArray jsonArray3 = new JSONArray();
        jsonArray3.put((Object)ee.a.a());
        jsonArray3.put((Object)au.f());
        jsonArray3.put((Object)au.j());
        jsonArray3.put((Object)au.i());
        jsonArray3.put((Object)au.k());
        jsonArray3.put(au.g());
        jsonArray3.put(au.h());
        jsonArray.put((Object)jsonArray3);
        final JSONArray jsonArray4 = new JSONArray();
        final Iterator<c> iterator = list.iterator();
        while (iterator.hasNext()) {
            jsonArray4.put((Object)iterator.next().d());
        }
        jsonArray.put((Object)jsonArray4);
        this.a.put("d", (Object)jsonArray);
    }
    
    public static a a(final au au, final List list) {
        try {
            return new a(au, list);
        }
        catch (JSONException ex) {
            dy.b("Unable to generate APM request's JSON: " + ex);
            return null;
        }
    }
}
