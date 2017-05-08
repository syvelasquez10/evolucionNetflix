// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.fbui.textlayoutbuilder.util;

import android.text.StaticLayout;
import android.os.Build$VERSION;
import android.text.Layout;

public class LayoutMeasureUtil
{
    public static int getHeight(final Layout layout) {
        final boolean b = false;
        if (layout == null) {
            return 0;
        }
        int n = b ? 1 : 0;
        if (Build$VERSION.SDK_INT < 20) {
            n = (b ? 1 : 0);
            if (layout instanceof StaticLayout) {
                final int lineAscent = layout.getLineAscent(layout.getLineCount() - 1);
                final int lineDescent = layout.getLineDescent(layout.getLineCount() - 1);
                final float n2 = lineDescent - lineAscent - (lineDescent - lineAscent - layout.getSpacingAdd()) / layout.getSpacingMultiplier();
                if (n2 >= 0.0f) {
                    n = (int)(n2 + 0.5);
                }
                else {
                    n = -(int)(-n2 + 0.5);
                }
            }
        }
        return layout.getHeight() - n;
    }
    
    public static int getWidth(final Layout layout) {
        int max = 0;
        int n = 0;
        if (layout != null) {
            final int lineCount = layout.getLineCount();
            int n2 = 0;
            while (true) {
                n = max;
                if (n2 >= lineCount) {
                    break;
                }
                max = Math.max(max, (int)layout.getLineRight(n2));
                ++n2;
            }
        }
        return n;
    }
}
