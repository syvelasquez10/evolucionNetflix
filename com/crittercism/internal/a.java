// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import org.json.JSONException;
import java.util.Iterator;
import org.json.JSONArray;
import java.util.List;
import org.json.JSONObject;

public final class a
{
    JSONObject a;
    
    private a(final ar ar, final List list) {
        this.a = new JSONObject();
        list.size();
        final JSONArray jsonArray = new JSONArray();
        final JSONArray jsonArray2 = new JSONArray();
        jsonArray2.put((Object)ar.a());
        jsonArray2.put((Object)ar.b());
        jsonArray2.put((Object)ar.c());
        jsonArray2.put((Object)"5.6.4");
        jsonArray2.put(ar.e());
        jsonArray.put((Object)jsonArray2);
        final JSONArray jsonArray3 = new JSONArray();
        jsonArray3.put((Object)eb.a.a());
        jsonArray3.put((Object)ar.f());
        jsonArray3.put((Object)ar.j());
        jsonArray3.put((Object)ar.i());
        jsonArray3.put((Object)ar.k());
        jsonArray3.put(ar.g());
        jsonArray3.put(ar.h());
        jsonArray.put((Object)jsonArray3);
        final JSONArray jsonArray4 = new JSONArray();
        final Iterator<c> iterator = list.iterator();
        while (iterator.hasNext()) {
            jsonArray4.put((Object)iterator.next().d());
        }
        jsonArray.put((Object)jsonArray4);
        this.a.put("d", (Object)jsonArray);
    }
    
    public static a a(final ar ar, final List list) {
        try {
            return new a(ar, list);
        }
        catch (JSONException ex) {
            dw.a("Unable to generate APM request's JSON: " + ex);
            return null;
        }
    }
}
