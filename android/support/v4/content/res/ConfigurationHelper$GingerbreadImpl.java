// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content.res;

import android.content.res.Resources;

class ConfigurationHelper$GingerbreadImpl implements ConfigurationHelper$ConfigurationHelperImpl
{
    @Override
    public int getDensityDpi(final Resources resources) {
        return ConfigurationHelperGingerbread.getDensityDpi(resources);
    }
    
    @Override
    public int getScreenHeightDp(final Resources resources) {
        return ConfigurationHelperGingerbread.getScreenHeightDp(resources);
    }
    
    @Override
    public int getScreenWidthDp(final Resources resources) {
        return ConfigurationHelperGingerbread.getScreenWidthDp(resources);
    }
    
    @Override
    public int getSmallestScreenWidthDp(final Resources resources) {
        return ConfigurationHelperGingerbread.getSmallestScreenWidthDp(resources);
    }
}
