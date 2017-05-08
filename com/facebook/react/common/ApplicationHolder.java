// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.common;

import com.facebook.infer.annotation.Assertions;
import android.app.Application;
import com.facebook.proguard.annotations.DoNotStrip;

@Deprecated
@DoNotStrip
public class ApplicationHolder
{
    private static Application sApplication;
    
    @DoNotStrip
    public static Application getApplication() {
        return Assertions.assertNotNull(ApplicationHolder.sApplication);
    }
    
    public static void setApplication(final Application sApplication) {
        ApplicationHolder.sApplication = sApplication;
    }
}
