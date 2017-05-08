// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

import java.io.OutputStream;
import org.json.JSONArray;

public abstract class cg extends bn
{
    public abstract JSONArray a();
    
    @Override
    public final void a(final OutputStream outputStream) {
        final String string = this.a().toString();
        dw.d("BREADCRUMB WRITING " + string);
        outputStream.write(string.getBytes());
    }
}
