// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import org.json.JSONArray;
import org.json.JSONObject;
import android.os.Bundle;

final class BundleJSONConverter$6 implements BundleJSONConverter$Setter
{
    @Override
    public void setOnBundle(final Bundle bundle, final String s, final Object o) {
        throw new IllegalArgumentException("Unexpected type from JSON");
    }
    
    @Override
    public void setOnJSON(final JSONObject jsonObject, final String s, final Object o) {
        final JSONArray jsonArray = new JSONArray();
        final String[] array = (String[])o;
        for (int length = array.length, i = 0; i < length; ++i) {
            jsonArray.put((Object)array[i]);
        }
        jsonObject.put(s, (Object)jsonArray);
    }
}
