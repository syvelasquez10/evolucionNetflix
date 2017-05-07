// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.net.URLEncoder;
import java.util.ArrayList;

class Request$5 implements Request$KeyValueSerializer
{
    final /* synthetic */ Request this$0;
    final /* synthetic */ ArrayList val$keysAndValues;
    
    Request$5(final Request this$0, final ArrayList val$keysAndValues) {
        this.this$0 = this$0;
        this.val$keysAndValues = val$keysAndValues;
    }
    
    @Override
    public void writeString(final String s, final String s2) {
        this.val$keysAndValues.add(String.format("%s=%s", s, URLEncoder.encode(s2, "UTF-8")));
    }
}
