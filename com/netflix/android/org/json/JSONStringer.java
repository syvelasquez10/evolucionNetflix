// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.org.json;

import java.io.Writer;
import java.io.StringWriter;

public class JSONStringer extends JSONWriter
{
    public JSONStringer() {
        super(new StringWriter());
    }
    
    @Override
    public String toString() {
        if (this.mode == 'd') {
            return this.writer.toString();
        }
        return null;
    }
}
