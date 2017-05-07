// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import org.json.JSONObject;
import android.os.Bundle;

final class BundleJSONConverter$1 implements BundleJSONConverter$Setter
{
    @Override
    public void setOnBundle(final Bundle bundle, final String s, final Object o) {
        bundle.putBoolean(s, (boolean)o);
    }
    
    @Override
    public void setOnJSON(final JSONObject jsonObject, final String s, final Object o) {
        jsonObject.put(s, o);
    }
}
