// 
// Decompiled by Procyon v0.5.30
// 

package com.vailsys.whistleengine;

import android.os.Binder;

public class WhistleEngine$WhistleEngineBinder extends Binder
{
    final /* synthetic */ WhistleEngine this$0;
    
    public WhistleEngine$WhistleEngineBinder(final WhistleEngine this$0) {
        this.this$0 = this$0;
    }
    
    public WhistleEngine getService() {
        return this.this$0;
    }
}
