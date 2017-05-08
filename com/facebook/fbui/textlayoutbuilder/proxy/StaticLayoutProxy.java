// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.fbui.textlayoutbuilder.proxy;

import android.text.TextDirectionHeuristics;
import android.support.v4.text.TextDirectionHeuristicsCompat;
import android.text.TextDirectionHeuristic;
import android.text.StaticLayout;
import android.support.v4.text.TextDirectionHeuristicCompat;
import android.text.TextUtils$TruncateAt;
import android.text.Layout$Alignment;
import android.text.TextPaint;

public class StaticLayoutProxy
{
    public static StaticLayout create(final CharSequence charSequence, final int n, final int n2, final TextPaint textPaint, final int n3, final Layout$Alignment layout$Alignment, final float n4, final float n5, final boolean b, final TextUtils$TruncateAt textUtils$TruncateAt, final int n6, final int n7, final TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        return new StaticLayout(charSequence, n, n2, textPaint, n3, layout$Alignment, fromTextDirectionHeuristicCompat(textDirectionHeuristicCompat), n4, n5, b, textUtils$TruncateAt, n6, n7);
    }
    
    private static TextDirectionHeuristic fromTextDirectionHeuristicCompat(final TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        if (textDirectionHeuristicCompat == TextDirectionHeuristicsCompat.LTR) {
            return TextDirectionHeuristics.LTR;
        }
        if (textDirectionHeuristicCompat == TextDirectionHeuristicsCompat.RTL) {
            return TextDirectionHeuristics.RTL;
        }
        if (textDirectionHeuristicCompat == TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR) {
            return TextDirectionHeuristics.FIRSTSTRONG_LTR;
        }
        if (textDirectionHeuristicCompat == TextDirectionHeuristicsCompat.FIRSTSTRONG_RTL) {
            return TextDirectionHeuristics.FIRSTSTRONG_RTL;
        }
        if (textDirectionHeuristicCompat == TextDirectionHeuristicsCompat.ANYRTL_LTR) {
            return TextDirectionHeuristics.ANYRTL_LTR;
        }
        if (textDirectionHeuristicCompat == TextDirectionHeuristicsCompat.LOCALE) {
            return TextDirectionHeuristics.LOCALE;
        }
        return TextDirectionHeuristics.FIRSTSTRONG_LTR;
    }
}
