// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import java.util.concurrent.ExecutionException;
import org.json.JSONException;
import org.json.JSONObject;

@ez
public class fq implements a<bp>
{
    public bp c(final fo fo, final JSONObject jsonObject) throws JSONException, InterruptedException, ExecutionException {
        return new bp(jsonObject.getString("headline"), fo.a(jsonObject, "image", true).get(), jsonObject.getString("body"), fo.a(jsonObject, "secondary_image", false).get(), jsonObject.getString("call_to_action"), jsonObject.getString("attribution"));
    }
}
