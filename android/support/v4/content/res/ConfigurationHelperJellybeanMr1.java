// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content.res;

import android.content.res.Resources;
import android.annotation.TargetApi;

@TargetApi(17)
class ConfigurationHelperJellybeanMr1
{
    static int getDensityDpi(final Resources resources) {
        return resources.getConfiguration().densityDpi;
    }
}
