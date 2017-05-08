// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.View;
import java.util.Comparator;

final class ViewGroupManager$1 implements Comparator<View>
{
    @Override
    public int compare(final View view, final View view2) {
        Integer value = ViewGroupManager.mZIndexHash.get(view);
        if (value == null) {
            value = 0;
        }
        Integer value2;
        if ((value2 = ViewGroupManager.mZIndexHash.get(view2)) == null) {
            value2 = 0;
        }
        return value - value2;
    }
}
