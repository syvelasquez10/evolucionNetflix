// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.fbui.textlayoutbuilder;

import com.facebook.fbui.textlayoutbuilder.proxy.StaticLayoutProxy;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.text.TextUtils$TruncateAt;
import android.text.Layout$Alignment;
import android.text.TextPaint;
import java.lang.reflect.Field;
import android.text.StaticLayout;

class StaticLayoutHelper
{
    public static boolean fixLayout(final StaticLayout staticLayout) {
        int lineStart = staticLayout.getLineStart(0);
        final int lineCount = staticLayout.getLineCount();
        int i = 0;
    Label_0115_Outer:
        while (i < lineCount) {
            final int lineEnd = staticLayout.getLineEnd(i);
            while (true) {
                if (lineEnd < lineStart) {
                    try {
                        final Field declaredField = StaticLayout.class.getDeclaredField("mLines");
                        declaredField.setAccessible(true);
                        final Field declaredField2 = StaticLayout.class.getDeclaredField("mColumns");
                        declaredField2.setAccessible(true);
                        final int[] array = (int[])declaredField.get(staticLayout);
                        for (int int1 = declaredField2.getInt(staticLayout), j = 0; j < int1; ++j) {
                            swap(array, int1 * i + j, int1 * i + j + int1);
                        }
                        return false;
                        ++i;
                        lineStart = lineEnd;
                        continue Label_0115_Outer;
                    }
                    catch (Exception ex) {}
                    break;
                }
                continue;
            }
        }
        return true;
    }
    
    private static StaticLayout getStaticLayoutMaybeMaxLines(final CharSequence charSequence, final int n, final int n2, final TextPaint textPaint, final int n3, final Layout$Alignment layout$Alignment, final float n4, final float n5, final boolean b, final TextUtils$TruncateAt textUtils$TruncateAt, final int n6, final int n7, final TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        try {
            return StaticLayoutProxy.create(charSequence, n, n2, textPaint, n3, layout$Alignment, n4, n5, b, textUtils$TruncateAt, n6, n7, textDirectionHeuristicCompat);
        }
        catch (LinkageError linkageError) {
            return getStaticLayoutNoMaxLines(charSequence, n, n2, textPaint, n3, layout$Alignment, n4, n5, b, textUtils$TruncateAt, n6);
        }
    }
    
    private static StaticLayout getStaticLayoutNoMaxLines(final CharSequence charSequence, final int n, final int n2, final TextPaint textPaint, final int n3, final Layout$Alignment layout$Alignment, final float n4, final float n5, final boolean b, final TextUtils$TruncateAt textUtils$TruncateAt, final int n6) {
        return new StaticLayout(charSequence, n, n2, textPaint, n3, layout$Alignment, n4, n5, b, textUtils$TruncateAt, n6);
    }
    
    public static StaticLayout make(final CharSequence charSequence, final int n, int lineStart, final TextPaint textPaint, final int n2, final Layout$Alignment layout$Alignment, final float n3, final float n4, final boolean b, final TextUtils$TruncateAt textUtils$TruncateAt, final int n5, final int n6, final TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        StaticLayout staticLayoutMaybeMaxLines;
        StaticLayout staticLayout = staticLayoutMaybeMaxLines = getStaticLayoutMaybeMaxLines(charSequence, n, lineStart, textPaint, n2, layout$Alignment, n3, n4, b, textUtils$TruncateAt, n5, n6, textDirectionHeuristicCompat);
        if (n6 > 0) {
            int n7 = lineStart;
            while (true) {
                staticLayoutMaybeMaxLines = staticLayout;
                if (staticLayout.getLineCount() <= n6) {
                    break;
                }
                if ((lineStart = staticLayout.getLineStart(n6)) >= n7) {
                    staticLayoutMaybeMaxLines = staticLayout;
                    break;
                }
                while (lineStart > n && Character.isSpace(charSequence.charAt(lineStart - 1))) {
                    --lineStart;
                }
                final StaticLayout staticLayout2 = staticLayout = getStaticLayoutMaybeMaxLines(charSequence, n, lineStart, textPaint, n2, layout$Alignment, n3, n4, b, textUtils$TruncateAt, n5, n6, textDirectionHeuristicCompat);
                if (staticLayout2.getLineCount() >= n6) {
                    staticLayout = staticLayout2;
                    if (staticLayout2.getEllipsisCount(n6 - 1) == 0) {
                        final String string = (Object)charSequence.subSequence(n, lineStart) + " \u2026";
                        staticLayout = getStaticLayoutMaybeMaxLines(string, 0, string.length(), textPaint, n2, layout$Alignment, n3, n4, b, textUtils$TruncateAt, n5, n6, textDirectionHeuristicCompat);
                    }
                }
                n7 = lineStart;
            }
        }
        while (!fixLayout(staticLayoutMaybeMaxLines)) {}
        return staticLayoutMaybeMaxLines;
    }
    
    private static void swap(final int[] array, final int n, final int n2) {
        final int n3 = array[n];
        array[n] = array[n2];
        array[n2] = n3;
    }
}
