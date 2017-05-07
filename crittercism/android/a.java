// 
// Decompiled by Procyon v0.5.30
// 

package crittercism.android;

import org.json.JSONException;
import android.util.Log;
import java.util.Iterator;
import org.json.JSONArray;
import java.util.List;
import org.json.JSONObject;

public final class a
{
    JSONObject a;
    
    private a(final g g, final List list) {
        this.a = new JSONObject();
        list.size();
        final JSONArray jsonArray = new JSONArray();
        final JSONArray jsonArray2 = new JSONArray();
        jsonArray2.put((Object)g.a());
        jsonArray2.put((Object)g.b());
        jsonArray2.put((Object)g.c());
        jsonArray2.put((Object)"3.1.4");
        jsonArray2.put(g.d());
        jsonArray.put((Object)jsonArray2);
        final JSONArray jsonArray3 = new JSONArray();
        jsonArray3.put((Object)au.a.a());
        jsonArray3.put((Object)g.e());
        jsonArray3.put((Object)g.g());
        jsonArray3.put((Object)g.f());
        jsonArray3.put((Object)g.h());
        jsonArray.put((Object)jsonArray3);
        final JSONArray jsonArray4 = new JSONArray();
        final Iterator<b> iterator = list.iterator();
        while (iterator.hasNext()) {
            jsonArray4.put((Object)iterator.next().a());
        }
        jsonArray.put((Object)jsonArray4);
        this.a.put("d", (Object)jsonArray);
    }
    
    public static a a(final g g, final List list) {
        try {
            return new a(g, list);
        }
        catch (JSONException ex) {
            Log.e("Crittercism", "Unable to generate APM request's JSON: " + ex);
            return null;
        }
    }
}
