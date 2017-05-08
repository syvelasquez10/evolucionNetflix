// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import org.json.JSONException;
import org.json.JSONArray;
import java.io.File;

public final class bx extends bo
{
    private bx(final File file) {
        super(file);
    }
    
    @Override
    public final Object a() {
        try {
            return new JSONArray((String)super.a());
        }
        catch (JSONException ex) {
            return null;
        }
    }
}
