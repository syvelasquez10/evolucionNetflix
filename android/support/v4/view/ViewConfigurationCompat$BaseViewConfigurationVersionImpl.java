// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ViewConfiguration;

class ViewConfigurationCompat$BaseViewConfigurationVersionImpl implements ViewConfigurationCompat$ViewConfigurationVersionImpl
{
    @Override
    public int getScaledPagingTouchSlop(final ViewConfiguration viewConfiguration) {
        return viewConfiguration.getScaledTouchSlop();
    }
    
    @Override
    public boolean hasPermanentMenuKey(final ViewConfiguration viewConfiguration) {
        return true;
    }
}
