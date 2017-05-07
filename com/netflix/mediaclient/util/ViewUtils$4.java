// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.graphics.Rect;
import java.util.Comparator;

final class ViewUtils$4 implements Comparator<ViewUtils$ViewComparator>
{
    @Override
    public int compare(final ViewUtils$ViewComparator viewUtils$ViewComparator, final ViewUtils$ViewComparator viewUtils$ViewComparator2) {
        final Rect rect = ViewUtils.getRect(viewUtils$ViewComparator.getView(), true);
        final Rect rect2 = ViewUtils.getRect(viewUtils$ViewComparator2.getView(), true);
        if (rect.bottom < rect2.bottom) {
            return 1;
        }
        if (rect.bottom > rect2.bottom) {
            return -1;
        }
        return 0;
    }
}
