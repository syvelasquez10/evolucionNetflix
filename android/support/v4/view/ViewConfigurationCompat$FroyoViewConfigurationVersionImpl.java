// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.ViewConfiguration;

class ViewConfigurationCompat$FroyoViewConfigurationVersionImpl extends ViewConfigurationCompat$BaseViewConfigurationVersionImpl
{
    @Override
    public int getScaledPagingTouchSlop(final ViewConfiguration viewConfiguration) {
        return ViewConfigurationCompatFroyo.getScaledPagingTouchSlop(viewConfiguration);
    }
}
