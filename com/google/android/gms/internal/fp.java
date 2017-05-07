// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public class fp implements a<bo>
{
    public bo b(final fo fo, final JSONObject jsonObject) throws JSONException, InterruptedException, ExecutionException {
        return new bo(jsonObject.getString("headline"), fo.a(jsonObject, "image", true).get(), jsonObject.getString("body"), fo.a(jsonObject, "app_icon", true).get(), jsonObject.getString("call_to_action"), jsonObject.optDouble("rating", -1.0), jsonObject.optString("store"), jsonObject.optString("price"));
    }
}
