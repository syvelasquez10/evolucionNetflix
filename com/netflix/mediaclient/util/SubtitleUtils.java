// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.graphics.Typeface;
import com.netflix.mediaclient.service.player.subtitles.VerticalAlignment;
import com.netflix.mediaclient.service.player.subtitles.HorizontalAlignment;
import java.util.regex.Matcher;
import java.util.Locale;
import com.netflix.mediaclient.service.player.subtitles.Outline;
import com.netflix.mediaclient.service.player.subtitles.ColorMapping;
import com.netflix.mediaclient.service.player.subtitles.TextStyle;
import com.netflix.mediaclient.service.player.subtitles.DoubleLength;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.widget.LinearLayout;
import java.util.regex.Pattern;

public final class SubtitleUtils
{
    private static final Pattern CELL_PATTERN;
    private static final int MILLISECONDS_PER_SECOND = 1000;
    private static final Pattern PERCENT_PATTERN;
    private static final String TAG = "nf_subtitles_render";
    private static final Pattern TICK_MS_PATTERN;
    private static final Pattern TICK_SEC_PATTERN;
    private static final Pattern TICK_TIME_PATTERN;
    private static final Pattern TICK_T_PATTERN;
    
    static {
        PERCENT_PATTERN = Pattern.compile("^[0-9]*[.]?[0-9]*%$");
        CELL_PATTERN = Pattern.compile("^[0-9]{1,2}c$");
        TICK_T_PATTERN = Pattern.compile("^([0-9.]+)t$");
        TICK_MS_PATTERN = Pattern.compile("^([0-9.]+)ms$");
        TICK_SEC_PATTERN = Pattern.compile("^([0-9.]+)s$");
        TICK_TIME_PATTERN = Pattern.compile("^([0-9]+):([0-9]+):([0-9.]+)$");
    }
    
    public static int adjustIfIntersectByMovingFirstUp(final LinearLayout linearLayout, final LinearLayout linearLayout2) {
        final Rect rect = ViewUtils.getRect((View)linearLayout, true);
        final Rect rect2 = ViewUtils.getRect((View)linearLayout2, true);
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "Block 1  left: " + rect.left + ", top: " + rect.top + ", right: " + rect.right + ", bottom: " + rect.bottom);
            Log.d("nf_subtitles_render", "Block 2  left: " + rect2.left + ", top: " + rect2.top + ", right: " + rect2.right + ", bottom: " + rect2.bottom);
        }
        final Point overlap = getOverlap(rect, rect2);
        if (overlap == null) {
            Log.d("nf_subtitles_render", "===> No intersection found");
            return 0;
        }
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "===> intersection found, move by: " + overlap);
        }
        int n = 1;
        final int y = overlap.y;
        final int n2 = rect.top - y;
        int n3 = y;
        if (n2 < 0) {
            Log.w("nf_subtitles_render", "We hit top. Set top to 0");
            n3 = y + n2;
            n = -1;
        }
        rect.top -= n3;
        rect.bottom -= n3;
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "After move: Block 1 left: " + rect.left + ", top: " + rect.top + ", right: " + rect.right + ", bottom: " + rect.bottom);
        }
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)linearLayout.getLayoutParams();
        layoutParams.setMargins(rect.left, rect.top, 0, 0);
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        return n;
    }
    
    public static boolean adjustIfIntersectByMovingSecondDown(final LinearLayout linearLayout, final LinearLayout linearLayout2, final int n) {
        final Rect rect = ViewUtils.getRect((View)linearLayout, true);
        final Rect rect2 = ViewUtils.getRect((View)linearLayout2, true);
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "Block 1  left: " + rect.left + ", top: " + rect.top + ", right: " + rect.right + ", bottom: " + rect.bottom);
            Log.d("nf_subtitles_render", "Block 2  left: " + rect2.left + ", top: " + rect2.top + ", right: " + rect2.right + ", bottom: " + rect2.bottom);
        }
        final Point overlap = getOverlap(rect, rect2);
        if (overlap == null) {
            Log.d("nf_subtitles_render", "===> No intersection found");
            return false;
        }
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "===> intersection found, move by: " + overlap);
        }
        final int y = overlap.y;
        final int n2 = rect2.bottom + y;
        int n3 = y;
        if (n2 > n) {
            Log.w("nf_subtitles_render", "We hit bottom. Set bottom to " + n);
            n3 = y - n2 + n;
        }
        rect2.top += n3;
        rect2.bottom += n3;
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "After move: Block 2 left: " + rect2.left + ", top: " + rect2.top + ", right: " + rect2.right + ", bottom: " + rect2.bottom);
        }
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)linearLayout2.getLayoutParams();
        layoutParams.setMargins(rect2.left, rect2.top, 0, 0);
        linearLayout2.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        return true;
    }
    
    public static Rect createRegionForRectangle(final View view, final DoubleLength doubleLength, final DoubleLength doubleLength2) {
        if (view == null) {
            throw new IllegalArgumentException("Display area can not be null");
        }
        int n = 0;
        int n2 = 0;
        final Rect rect = new Rect();
        if (doubleLength != null) {
            n = (int)(view.getWidth() * doubleLength.getFirstLength());
            n2 = (int)(view.getHeight() * doubleLength.getSecondLength());
        }
        else {
            Log.w("nf_subtitles_render", "Extent is null!");
        }
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "Display area: w " + view.getWidth() + ", h " + view.getHeight());
            Log.d("nf_subtitles_render", "Region w/h " + n + "/" + n2);
            Log.d("nf_subtitles_render", "Extent " + doubleLength);
            Log.d("nf_subtitles_render", "Origin " + doubleLength2);
        }
        if (doubleLength2 != null) {
            rect.left = (int)(view.getWidth() * doubleLength2.getFirstLength());
            rect.top = (int)(view.getHeight() * doubleLength2.getSecondLength());
            rect.right = rect.left + n;
            rect.bottom = rect.top + n2;
            if (rect.bottom > view.getHeight()) {
                Log.w("nf_subtitles_render", "Extent h is too big!");
            }
            if (rect.right > view.getWidth()) {
                Log.w("nf_subtitles_render", "Extent w is too big!");
            }
        }
        else {
            Log.w("nf_subtitles_render", "Origin is null!");
        }
        if (Log.isLoggable("nf_subtitles_render", 2)) {
            Log.d("nf_subtitles_render", "Rectangle, left: " + rect.left + ", top: " + rect.top + ", right: " + rect.right + ", bottom: " + rect.bottom);
        }
        return rect;
    }
    
    public static Integer getBackgroundColor(final TextStyle textStyle, final TextStyle textStyle2) {
        if (textStyle2 == null) {
            return ColorMapping.resolveColor(textStyle.getBackgroundOpacity(), textStyle.getBackgroundColor());
        }
        return ColorMapping.resolveColor(textStyle2.getBackgroundOpacity(), textStyle2.getBackgroundColor());
    }
    
    public static Integer getEdgeColor(final Outline outline, final TextStyle textStyle) {
        final Integer n = null;
        if (textStyle == null || textStyle.getOutline() == null) {
            Integer resolveColor = n;
            if (outline != null) {
                resolveColor = ColorMapping.resolveColor(null, outline.getEdgeColor());
            }
            return resolveColor;
        }
        return ColorMapping.resolveColor(null, textStyle.getOutline().getEdgeColor());
    }
    
    public static float getFontSizeMultiplier(final TextStyle textStyle, final TextStyle textStyle2) {
        float n;
        if (textStyle2 == null) {
            if (textStyle.getFontSize() != null) {
                n = textStyle.getFontSize() / 100.0f;
            }
            else {
                n = 1.0f;
            }
        }
        else if (textStyle2.getFontSize() != null) {
            n = textStyle2.getFontSize() / 100.0f;
        }
        else {
            n = 1.0f;
        }
        float n2 = n;
        if (n <= 0.0f) {
            n2 = 1.0f;
        }
        return n2;
    }
    
    public static Margins getMarginsForRectangle(final View view, final DoubleLength doubleLength, final DoubleLength doubleLength2) {
        if (view == null) {
            throw new IllegalArgumentException("Display area can not be null");
        }
        int n = 0;
        int n2 = 0;
        final Margins margins = new Margins();
        if (doubleLength != null) {
            n = (int)(view.getWidth() * doubleLength.getFirstLength());
            n2 = (int)(view.getHeight() * doubleLength.getSecondLength());
        }
        else {
            Log.w("nf_subtitles_render", "Extent is null!");
        }
        if (Log.isLoggable("nf_subtitles_render", 3)) {
            Log.d("nf_subtitles_render", "Display area: w " + view.getWidth() + ", h " + view.getHeight());
            Log.d("nf_subtitles_render", "Region w/h " + n + "/" + n2);
            Log.d("nf_subtitles_render", "Extent " + doubleLength);
            Log.d("nf_subtitles_render", "Origin " + doubleLength2);
        }
        if (doubleLength2 != null) {
            margins.left = (int)(view.getWidth() * doubleLength2.getFirstLength());
            margins.top = (int)(view.getHeight() * doubleLength2.getSecondLength());
            margins.right = view.getWidth() - margins.left - n;
            margins.bottom = view.getHeight() - margins.top - n2;
        }
        else {
            Log.w("nf_subtitles_render", "Origin is null!");
        }
        if (Log.isLoggable("nf_subtitles_render", 2)) {
            Log.d("nf_subtitles_render", "Margins, left: " + margins.left + ", top: " + margins.top + ", right: " + margins.right + ", bottom: " + margins.bottom);
        }
        return margins;
    }
    
    public static Point getOverlap(Rect intersection, final Rect rect) {
        if (!Rect.intersects(intersection, rect)) {
            return null;
        }
        intersection = intersection(intersection, rect);
        final Point point = new Point();
        point.x = 0;
        point.y = intersection.height();
        return point;
    }
    
    public static Integer getTextColor(final TextStyle textStyle, final TextStyle textStyle2) {
        if (textStyle2 != null) {
            final Integer resolveColor = ColorMapping.resolveColor(textStyle2.getOpacity(), textStyle2.getColor());
            if (resolveColor != null) {
                return resolveColor;
            }
            Log.e("nf_subtitles_render", "User override exist, but color is null! Use node text color.");
        }
        return ColorMapping.resolveColor(textStyle.getOpacity(), textStyle.getColor());
    }
    
    public static Integer getWindowColor(final TextStyle textStyle, final TextStyle textStyle2) {
        if (textStyle2 == null) {
            return ColorMapping.resolveColor(textStyle.getWindowOpacity(), textStyle.getWindowColor());
        }
        return ColorMapping.resolveColor(textStyle2.getWindowOpacity(), textStyle2.getWindowColor());
    }
    
    public static Rect intersection(final Rect rect, final Rect rect2) {
        return new Rect(Math.max(rect.left, rect2.left), Math.max(rect.top, rect2.top), Math.min(rect.right, rect2.right), Math.min(rect.bottom, rect2.bottom));
    }
    
    public static boolean isStrokeTextViewRequired(final TextStyle textStyle, final TextStyle textStyle2) {
        if (textStyle2 == null) {
            if (textStyle == null || textStyle.getOutline() == null || !textStyle.getOutline().isStrokeTextRequired()) {
                return false;
            }
        }
        else if (textStyle2.getOutline() == null || !textStyle2.getOutline().isStrokeTextRequired()) {
            return false;
        }
        return true;
    }
    
    public static Float parseMargin(final String s, final int n) {
        if (!StringUtils.isEmpty(s)) {
            final Matcher matcher = SubtitleUtils.PERCENT_PATTERN.matcher(s);
            if (matcher.find()) {
                return Float.valueOf(matcher.group().replaceAll("%", "")) / 100.0f;
            }
            final Matcher matcher2 = SubtitleUtils.CELL_PATTERN.matcher(s.toLowerCase(Locale.US));
            if (matcher2.find()) {
                return Float.valueOf(matcher2.group().replaceAll("c", "")) / n;
            }
        }
        return null;
    }
    
    public static long parseTime(final String s, final double n) {
        long n2;
        if (StringUtils.isEmpty(s)) {
            Log.e("nf_subtitles_render", "dfxp-badtime: Time string us empty! Invalid time");
            n2 = -1L;
        }
        else {
            final Matcher matcher = SubtitleUtils.TICK_T_PATTERN.matcher(s);
            if (matcher.find()) {
                final long n3 = n2 = safeParseLong(matcher.group().replaceAll("t", ""));
                if (n3 != -1L) {
                    return (long)(n3 * n);
                }
            }
            else {
                final Matcher matcher2 = SubtitleUtils.TICK_MS_PATTERN.matcher(s);
                if (matcher2.find()) {
                    return safeParseLong(matcher2.group().replaceAll("ms", ""));
                }
                final Matcher matcher3 = SubtitleUtils.TICK_SEC_PATTERN.matcher(s);
                if (matcher3.find()) {
                    final long n4 = n2 = safeParseLong(matcher3.group().replaceAll("s", ""));
                    if (n4 != -1L) {
                        return (long)(n4 * n);
                    }
                }
                else {
                    final Matcher matcher4 = SubtitleUtils.TICK_TIME_PATTERN.matcher(s);
                    if (!matcher4.find()) {
                        if (Log.isLoggable("nf_subtitles_render", 6)) {
                            Log.e("nf_subtitles_render", "dfxp-badtime: Unknown format " + s);
                        }
                        return -1L;
                    }
                    final String[] safeSplit = StringUtils.safeSplit(matcher4.group(), ":");
                    if (safeSplit.length < 3) {
                        if (Log.isLoggable("nf_subtitles_render", 6)) {
                            Log.e("nf_subtitles_render", "dfxp-badtime: Tick time pattern matched, but we do not have 3 groups! This should NOT happen! " + s);
                        }
                        return -1L;
                    }
                    return (3600L * safeParseLong(safeSplit[0]) + 60L * safeParseLong(safeSplit[1]) + safeParseLong(safeSplit[2])) * 1000L;
                }
            }
        }
        return n2;
    }
    
    private static long safeParseLong(final String s) {
        try {
            return Long.parseLong(s);
        }
        catch (Throwable t) {
            Log.e("nf_subtitles_render", "Parsing failed", t);
            return -1L;
        }
    }
    
    public static int toGravity(final HorizontalAlignment horizontalAlignment, final VerticalAlignment verticalAlignment) {
        int n;
        if (horizontalAlignment == null && verticalAlignment == null) {
            Log.d("nf_subtitles_render", "toGravity default CENTER");
            n = 17;
        }
        else {
            if (horizontalAlignment == null) {
                if (Log.isLoggable("nf_subtitles_render", 3)) {
                    Log.d("nf_subtitles_render", "toGravity vert " + verticalAlignment.getValue());
                }
                return verticalAlignment.getGravity();
            }
            if (verticalAlignment == null) {
                if (Log.isLoggable("nf_subtitles_render", 3)) {
                    Log.d("nf_subtitles_render", "toGravity hor " + horizontalAlignment.getValue());
                }
                return horizontalAlignment.getGravity();
            }
            final int gravity = horizontalAlignment.getGravity();
            final int gravity2 = verticalAlignment.getGravity();
            final int n2 = n = (horizontalAlignment.getGravity() | verticalAlignment.getGravity());
            if (Log.isLoggable("nf_subtitles_render", 3)) {
                Log.d("nf_subtitles_render", "toGravity " + n2 + ", hor " + horizontalAlignment.getValue() + ", vert " + verticalAlignment.getValue() + ", plus " + (gravity + gravity2));
                return n2;
            }
        }
        return n;
    }
    
    public static Typeface toTypeFace(final TextStyle textStyle, final TextStyle textStyle2) {
        if (textStyle == null && textStyle2 == null) {
            return Typeface.SANS_SERIF;
        }
        Typeface typeface = Typeface.SANS_SERIF;
        if (textStyle2 == null) {
            if (textStyle.getFontFamily() != null) {
                typeface = textStyle.getFontFamily().getTypeface();
            }
        }
        else if (textStyle2.getFontFamily() != null) {
            typeface = textStyle2.getFontFamily().getTypeface();
        }
        return Typeface.create(typeface, toTypeFaceStyle(textStyle));
    }
    
    public static int toTypeFaceStyle(final TextStyle textStyle) {
        if (textStyle != null) {
            if (Boolean.TRUE.equals(textStyle.getItalic())) {
                if (Boolean.TRUE.equals(textStyle.getBold())) {
                    return 3;
                }
                return 2;
            }
            else if (Boolean.TRUE.equals(textStyle.getBold())) {
                return 1;
            }
        }
        return 0;
    }
    
    public static class Margins
    {
        public int bottom;
        public int left;
        public int right;
        public int top;
    }
}
