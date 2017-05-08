// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.text.StaticLayout$Builder;
import com.netflix.mediaclient.util.LogUtils;
import android.util.Log;
import android.text.TextDirectionHeuristics;
import android.text.TextDirectionHeuristic;
import android.os.Build$VERSION;
import android.text.TextUtils$TruncateAt;
import android.text.Layout$Alignment;
import android.text.TextPaint;
import android.text.StaticLayout;
import java.lang.reflect.Constructor;

public class StaticLayoutWithMaxLines
{
    private static final String TAG = "StaticLayoutWithMaxL";
    private static Constructor<StaticLayout> sConstructor;
    private static boolean stopTrying;
    
    static {
        StaticLayoutWithMaxLines.stopTrying = false;
    }
    
    public static StaticLayout instantiate(final CharSequence charSequence, final int n, final int n2, final TextPaint textPaint, final int n3, final Layout$Alignment alignment, final float n4, final float n5, final boolean includePad, final TextUtils$TruncateAt ellipsize, final int ellipsizedWidth, final int maxLines) {
        while (true) {
            synchronized (StaticLayoutWithMaxLines.class) {
                StaticLayout build;
                if (StaticLayoutWithMaxLines.stopTrying || Build$VERSION.SDK_INT < 19) {
                    build = null;
                }
                else {
                    if (Build$VERSION.SDK_INT >= 19 && Build$VERSION.SDK_INT < 23) {
                        try {
                            (StaticLayoutWithMaxLines.sConstructor = StaticLayout.class.getDeclaredConstructor(CharSequence.class, Integer.TYPE, Integer.TYPE, TextPaint.class, Integer.TYPE, Layout$Alignment.class, TextDirectionHeuristic.class, Float.TYPE, Float.TYPE, Boolean.TYPE, TextUtils$TruncateAt.class, Integer.TYPE, Integer.TYPE)).setAccessible(true);
                            build = StaticLayoutWithMaxLines.sConstructor.newInstance(charSequence, n, n2, textPaint, n3, alignment, TextDirectionHeuristics.LOCALE, n4, n5, includePad, ellipsize, ellipsizedWidth, maxLines);
                            return build;
                        }
                        catch (Exception ex) {
                            StaticLayoutWithMaxLines.stopTrying = true;
                            Log.e("StaticLayoutWithMaxL", "Error instantiating StaticLayout with maxlines");
                            LogUtils.reportErrorSafely("Reflection error instantiating StaticLayout with maxlines", (Throwable)ex);
                            return null;
                        }
                    }
                    if (Build$VERSION.SDK_INT < 23) {
                        return null;
                    }
                    final StaticLayout$Builder obtain = StaticLayout$Builder.obtain(charSequence, n, n2, textPaint, n3);
                    obtain.setAlignment(alignment);
                    obtain.setTextDirection(TextDirectionHeuristics.LOCALE);
                    obtain.setLineSpacing(n5, n4);
                    obtain.setIncludePad(includePad);
                    obtain.setEllipsizedWidth(ellipsizedWidth);
                    obtain.setMaxLines(maxLines);
                    obtain.setEllipsize(ellipsize);
                    build = obtain.build();
                }
                return build;
            }
            return null;
        }
    }
}
