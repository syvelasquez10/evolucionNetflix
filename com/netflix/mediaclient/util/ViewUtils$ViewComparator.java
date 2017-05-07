// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.graphics.Rect;
import android.view.View;
import java.util.Comparator;

public class ViewUtils$ViewComparator implements Comparable<ViewUtils$ViewComparator>
{
    public static final Comparator<ViewUtils$ViewComparator> REVERSE_SORT_BY_TOP;
    public static final Comparator<ViewUtils$ViewComparator> SORT_BY_BOTTOM;
    public static final Comparator<ViewUtils$ViewComparator> SORT_BY_TOP;
    private final View mView;
    
    static {
        REVERSE_SORT_BY_TOP = new ViewUtils$ViewComparator$1();
        SORT_BY_TOP = new ViewUtils$ViewComparator$2();
        SORT_BY_BOTTOM = new ViewUtils$ViewComparator$3();
    }
    
    public ViewUtils$ViewComparator(final View mView) {
        if (mView == null) {
            throw new IllegalArgumentException("View can not be null");
        }
        this.mView = mView;
    }
    
    @Override
    public int compareTo(final ViewUtils$ViewComparator viewUtils$ViewComparator) {
        int n = 1;
        final Rect rect = ViewUtils.getRect(this.mView, true);
        final Rect rect2 = ViewUtils.getRect(viewUtils$ViewComparator.getView(), true);
        if (rect.bottom < rect2.bottom) {
            n = -1;
        }
        else if (rect.bottom <= rect2.bottom) {
            return 0;
        }
        return n;
    }
    
    public View getView() {
        return this.mView;
    }
}
