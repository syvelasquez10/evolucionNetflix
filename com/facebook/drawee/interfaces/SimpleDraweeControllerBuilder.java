// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.interfaces;

import android.net.Uri;

public interface SimpleDraweeControllerBuilder
{
    DraweeController build();
    
    SimpleDraweeControllerBuilder setCallerContext(final Object p0);
    
    SimpleDraweeControllerBuilder setOldController(final DraweeController p0);
    
    SimpleDraweeControllerBuilder setUri(final Uri p0);
}
