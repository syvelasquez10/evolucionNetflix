// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;
import org.json.JSONObject;

@ez
public class fq implements fo$a<bp>
{
    public bp c(final fo fo, final JSONObject jsonObject) {
        return new bp(jsonObject.getString("headline"), fo.a(jsonObject, "image", true).get(), jsonObject.getString("body"), fo.a(jsonObject, "secondary_image", false).get(), jsonObject.getString("call_to_action"), jsonObject.getString("attribution"));
    }
}
