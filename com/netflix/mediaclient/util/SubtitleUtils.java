// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.annotation.SuppressLint;
import com.netflix.mediaclient.service.player.subtitles.text.Region;
import com.netflix.mediaclient.service.player.subtitles.text.VerticalAlignment;
import com.netflix.mediaclient.service.player.subtitles.text.HorizontalAlignment;
import java.util.regex.Matcher;
import java.util.Locale;
import org.json.JSONException;
import com.netflix.mediaclient.ui.offline.OfflineImageSubtitle;
import com.netflix.mediaclient.ui.offline.OfflineTextSubtitle;
import com.netflix.mediaclient.ui.player.NccpSubtitle;
import com.netflix.mediaclient.ui.mdx.MdxSubtitle;
import org.json.JSONObject;
import com.netflix.mediaclient.service.player.subtitles.text.TextSubtitleBlock;
import android.widget.LinearLayout;
import com.netflix.mediaclient.service.player.subtitles.text.SubtitleTextNode;
import java.util.List;
import android.util.DisplayMetrics;
import android.content.Context;
import com.netflix.mediaclient.service.player.subtitles.text.ColorMapping;
import com.netflix.mediaclient.media.Subtitle;
import com.netflix.mediaclient.service.player.subtitles.DoubleLength;
import com.netflix.mediaclient.android.widget.StrokeTextView;
import com.netflix.mediaclient.service.player.subtitles.text.Outline$Shadow;
import android.widget.TextView;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.netflix.mediaclient.Log;
import android.view.View;
import java.util.HashMap;
import com.netflix.mediaclient.service.player.subtitles.text.FontWeight;
import com.netflix.mediaclient.service.player.subtitles.text.FontFamilyMapping;
import com.netflix.mediaclient.service.player.subtitles.text.Outline;
import android.graphics.Typeface;
import java.util.Map;
import com.netflix.mediaclient.service.player.subtitles.text.TextStyle;
import java.util.regex.Pattern;

public final class SubtitleUtils
{
    private static final Pattern CELL_PATTERN;
    public static final TextStyle DEFAULT_DEVICE_TEXT_STYLE_FOR_MONOSPACE;
    public static final TextStyle DEFAULT_DEVICE_TEXT_STYLE_FOR_PROPORTIONAL;
    private static final float DEFAULT_SPACING_IN_EM_FOR_MONOSPACE_FONT = 0.0f;
    private static final float DEFAULT_SPACING_IN_EM_FOR_PROPORSIONAL_FONT = 0.016f;
    private static final String DEFAULT_TEXT_COLOR = "FFFFFF";
    private static final String DEFAULT_TEXT_STLE_ID = "<%NF_DEFAULT_TEXT_STYLE%>";
    private static final int MILLISECONDS_PER_SECOND = 1000;
    private static final Pattern PERCENT_PATTERN;
    public static final int SUBTITITLE_IMAGE_1080P_HEIGHT = 1080;
    public static final int SUBTITITLE_IMAGE_1080P_WIDTH = 1920;
    public static final int SUBTITITLE_IMAGE_480P_HEIGHT = 480;
    public static final int SUBTITITLE_IMAGE_480P_WIDTH = 853;
    public static final int SUBTITITLE_IMAGE_720P_HEIGHT = 720;
    public static final int SUBTITITLE_IMAGE_720P_WIDTH = 1280;
    private static final String TAG = "nf_subtitles_render";
    private static final Pattern TICK_MS_PATTERN;
    private static final Pattern TICK_SEC_PATTERN;
    private static final Pattern TICK_TIME_PATTERN;
    private static final Pattern TICK_T_PATTERN;
    private static final Map<String, Typeface> sTypeFaceWeightMapForProportional;
    
    static {
        PERCENT_PATTERN = Pattern.compile("^[0-9]*[.]?[0-9]*%$");
        CELL_PATTERN = Pattern.compile("^[0-9]{1,2}c$");
        TICK_T_PATTERN = Pattern.compile("^([0-9.]+)t$");
        TICK_MS_PATTERN = Pattern.compile("^([0-9.]+)ms$");
        TICK_SEC_PATTERN = Pattern.compile("^([0-9.]+)s$");
        TICK_TIME_PATTERN = Pattern.compile("^([0-9]+):([0-9]+):([0-9.]+)$");
        DEFAULT_DEVICE_TEXT_STYLE_FOR_MONOSPACE = new TextStyle("<%NF_DEFAULT_TEXT_STYLE%>", "FFFFFF", null, null, null, Outline.getDefaultOutline(), FontFamilyMapping.defaultType, false, false, FontWeight.Regular, null, null, null);
        DEFAULT_DEVICE_TEXT_STYLE_FOR_PROPORTIONAL = new TextStyle("<%NF_DEFAULT_TEXT_STYLE%>", "FFFFFF", null, null, null, Outline.getDefaultOutline(), FontFamilyMapping.monospace, false, false, FontWeight.Medium, null, null, null);
        sTypeFaceWeightMapForProportional = new HashMap<String, Typeface>();
        initTypeFaceMap();
    }
    
    public static int adjustIfIntersectByMovingFirstUp(final View view, final View view2) {
        int n = 1;
        final Rect rect = ViewUtils.getRect(view, true);
        final Rect rect2 = ViewUtils.getRect(view2, true);
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Block 1  left: " + rect.left + ", top: " + rect.top + ", right: " + rect.right + ", bottom: " + rect.bottom);
            Log.d("nf_subtitles_render", "Block 2  left: " + rect2.left + ", top: " + rect2.top + ", right: " + rect2.right + ", bottom: " + rect2.bottom);
        }
        final Point overlap = getOverlap(rect, rect2);
        if (overlap == null) {
            Log.d("nf_subtitles_render", "===> No intersection found");
            return 0;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "===> intersection found, move by: " + overlap);
        }
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
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "After move: Block 1 left: " + rect.left + ", top: " + rect.top + ", right: " + rect.right + ", bottom: " + rect.bottom);
        }
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)view.getLayoutParams();
        layoutParams.setMargins(rect.left, rect.top, 0, 0);
        view.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        return n;
    }
    
    public static boolean adjustIfIntersectByMovingSecondDown(final View view, final View view2, final int n) {
        final Rect rect = ViewUtils.getRect(view, true);
        final Rect rect2 = ViewUtils.getRect(view2, true);
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Block 1  left: " + rect.left + ", top: " + rect.top + ", right: " + rect.right + ", bottom: " + rect.bottom);
            Log.d("nf_subtitles_render", "Block 2  left: " + rect2.left + ", top: " + rect2.top + ", right: " + rect2.right + ", bottom: " + rect2.bottom);
        }
        final Point overlap = getOverlap(rect, rect2);
        if (overlap == null) {
            Log.d("nf_subtitles_render", "===> No intersection found");
            return false;
        }
        if (Log.isLoggable()) {
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
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "After move: Block 2 left: " + rect2.left + ", top: " + rect2.top + ", right: " + rect2.right + ", bottom: " + rect2.bottom);
        }
        final RelativeLayout$LayoutParams layoutParams = (RelativeLayout$LayoutParams)view2.getLayoutParams();
        layoutParams.setMargins(rect2.left, rect2.top, 0, 0);
        view2.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        return true;
    }
    
    private static void applyEdge(final TextView textView, final Outline outline) {
        Log.d("nf_subtitles_render", "Apply edge");
        if (outline == null || !outline.isOutlineRequired()) {
            Log.d("nf_subtitles_render", "No outline to be applied");
            return;
        }
        final Integer edgeColor = getEdgeColor(outline);
        if (edgeColor == null) {
            Log.w("nf_subtitles_render", "Edge color unresolved, not setting anything!");
            return;
        }
        final Outline$Shadow shadow = outline.getShadow();
        if (shadow == null) {
            Log.w("nf_subtitles_render", "Shadow is null, not setting anything!");
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Sets text shadow with color " + edgeColor + ", radius " + shadow.radius + ", dx " + shadow.dx + ", dy " + shadow.dy);
        }
        textView.setShadowLayer(shadow.radius, (float)shadow.dx, (float)shadow.dy, (int)edgeColor);
    }
    
    public static void applyOutline(final TextView textView, final TextStyle textStyle) {
        if (textView == null || textStyle == null) {
            Log.e("nf_subtitles_render", "apply outline parameters are null, do nothing!");
            return;
        }
        if (textStyle.getOutline() == null) {
            Log.d("nf_subtitles_render", "No outline!");
            return;
        }
        if (textView instanceof StrokeTextView) {
            applyStroke((StrokeTextView)textView, textStyle.getOutline());
            return;
        }
        applyEdge(textView, textStyle.getOutline());
    }
    
    private static void applyStroke(final StrokeTextView strokeTextView, final Outline outline) {
        final Integer edgeColor = getEdgeColor(outline);
        int intValue;
        if (outline.getOutlineWidth() != null) {
            intValue = outline.getOutlineWidth();
        }
        else {
            intValue = 1;
        }
        if (edgeColor != null) {
            strokeTextView.setStrokeWidth(intValue);
            strokeTextView.setStrokeColor(edgeColor);
        }
    }
    
    public static void applyStyle(final TextView textView, final TextStyle textStyle, float n) {
        if (textView == null || textStyle == null) {
            Log.e("nf_subtitles_render", "apply style parameters are null, do nothing!");
            return;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Apply style " + textStyle);
        }
        final float fontSizeMultiplier = getFontSizeMultiplier(textStyle);
        n *= fontSizeMultiplier;
        textView.setTextSize(0, n);
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Text size " + n + ", scale " + fontSizeMultiplier);
        }
        textView.setTypeface(toTypeFace(textStyle, false), toTypeFaceStyle(textStyle));
        setLetterSpacing(textView, textStyle);
        final Integer textColor = getTextColor(textStyle);
        if (textColor != null) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "Sets text color to " + textColor);
            }
            textView.setTextColor((int)textColor);
        }
        else {
            Log.w("nf_subtitles_render", "Text color unresolved, not setting anything!");
        }
        final Integer backgroundColor = getBackgroundColor(textStyle);
        if (backgroundColor != null) {
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "Sets text view background color to " + backgroundColor);
            }
            textView.setBackgroundColor((int)backgroundColor);
        }
        else {
            Log.w("nf_subtitles_render", "Background color unresolved, not setting anything!");
        }
        applyOutline(textView, textStyle);
    }
    
    public static Rect createRegionForRectangle(final View view, final DoubleLength doubleLength, final DoubleLength doubleLength2) {
        int n = 0;
        if (view == null) {
            throw new IllegalArgumentException("Display area can not be null");
        }
        final Rect rect = new Rect();
        int n2;
        if (doubleLength != null) {
            n2 = (int)(view.getWidth() * doubleLength.getFirstLength());
            n = (int)(view.getHeight() * doubleLength.getSecondLength());
        }
        else {
            Log.w("nf_subtitles_render", "Extent is null!");
            n2 = 0;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Display area: w " + view.getWidth() + ", h " + view.getHeight());
            Log.d("nf_subtitles_render", "Region w/h " + n2 + "/" + n);
            Log.d("nf_subtitles_render", "Extent " + doubleLength);
            Log.d("nf_subtitles_render", "Origin " + doubleLength2);
        }
        if (doubleLength2 != null) {
            rect.left = (int)(view.getWidth() * doubleLength2.getFirstLength());
            rect.top = (int)(view.getHeight() * doubleLength2.getSecondLength());
            rect.right = n2 + rect.left;
            rect.bottom = n + rect.top;
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
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Rectangle, left: " + rect.left + ", top: " + rect.top + ", right: " + rect.right + ", bottom: " + rect.bottom);
        }
        return rect;
    }
    
    public static String createText(final String s, final int n) {
        int i = 1;
        final StringBuilder sb = new StringBuilder();
        if (n > 1) {
            while (i < n) {
                sb.append('\n');
                ++i;
            }
        }
        if (!StringUtils.isEmpty(s)) {
            sb.append(s);
        }
        sb.append(" ");
        return sb.toString();
    }
    
    public static void dumpLog(final Subtitle[] array, final String s) {
        if (array != null) {
            if (Log.isLoggable()) {
                Log.d(s, "Subtitles: " + array.length);
                for (int i = 0; i < array.length; ++i) {
                    Log.d(s, i + " " + array[i]);
                }
            }
        }
        else {
            Log.e(s, "Subtitles are null!");
        }
    }
    
    public static Integer getBackgroundColor(final TextStyle textStyle) {
        return ColorMapping.resolveColor(textStyle.getBackgroundOpacity(), textStyle.getBackgroundColor());
    }
    
    public static FontWeight getDefaultFontWeight(final FontFamilyMapping fontFamilyMapping) {
        if (FontFamilyMapping.isMonospace(fontFamilyMapping)) {
            return FontWeight.Regular;
        }
        return FontWeight.Medium;
    }
    
    private static float getDefaultSpacing(final FontFamilyMapping fontFamilyMapping) {
        if (fontFamilyMapping == null || FontFamilyMapping.isProportional(fontFamilyMapping)) {
            return 0.016f;
        }
        return 0.0f;
    }
    
    private static TextStyle getDefaultTextStyle(final FontFamilyMapping fontFamilyMapping) {
        if (FontFamilyMapping.isMonospace(fontFamilyMapping)) {
            return SubtitleUtils.DEFAULT_DEVICE_TEXT_STYLE_FOR_MONOSPACE;
        }
        return SubtitleUtils.DEFAULT_DEVICE_TEXT_STYLE_FOR_PROPORTIONAL;
    }
    
    public static TextStyle getDeviceDefaultTextStyle(final TextStyle textStyle, final TextStyle textStyle2) {
        if (textStyle == null && textStyle2 == null) {
            Log.d("nf_subtitles_render", "getDeviceDefaultTextStyle:: user and region defaults are null: proportional");
            return getDefaultTextStyle(null);
        }
        if (textStyle == null || textStyle.getFontFamily() == null) {
            Log.d("nf_subtitles_render", "getDeviceDefaultTextStyle:: user font family is null, depending on region");
            return getDefaultTextStyle(textStyle2.getFontFamily());
        }
        Log.d("nf_subtitles_render", "getDeviceDefaultTextStyle:: user font family is NOT null, deciding based on it");
        return getDefaultTextStyle(textStyle.getFontFamily());
    }
    
    public static Integer getEdgeColor(final Outline outline) {
        Integer resolveColor = null;
        if (outline != null) {
            resolveColor = ColorMapping.resolveColor(null, outline.getEdgeColor());
        }
        return resolveColor;
    }
    
    public static float getFontSizeMultiplier(final TextStyle textStyle) {
        float n;
        if (textStyle.getFontSize() != null) {
            n = textStyle.getFontSize() / 100.0f;
        }
        else {
            n = 1.0f;
        }
        if (n <= 0.0f) {
            return 1.0f;
        }
        return n;
    }
    
    public static SubtitleUtils$Margins getMarginsForRectangle(final View view, final DoubleLength doubleLength, final DoubleLength doubleLength2) {
        int n = 0;
        if (view == null) {
            throw new IllegalArgumentException("Display area can not be null");
        }
        final SubtitleUtils$Margins subtitleUtils$Margins = new SubtitleUtils$Margins();
        int n2;
        if (doubleLength != null) {
            n2 = (int)(view.getWidth() * doubleLength.getFirstLength());
            n = (int)(view.getHeight() * doubleLength.getSecondLength());
        }
        else {
            Log.w("nf_subtitles_render", "Extent is null!");
            n2 = 0;
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Display area: w " + view.getWidth() + ", h " + view.getHeight());
            Log.d("nf_subtitles_render", "Region w/h " + n2 + "/" + n);
            Log.d("nf_subtitles_render", "Extent " + doubleLength);
            Log.d("nf_subtitles_render", "Origin " + doubleLength2);
        }
        if (doubleLength2 != null) {
            subtitleUtils$Margins.left = (int)(view.getWidth() * doubleLength2.getFirstLength());
            subtitleUtils$Margins.top = (int)(view.getHeight() * doubleLength2.getSecondLength());
            subtitleUtils$Margins.right = view.getWidth() - subtitleUtils$Margins.left - n2;
            subtitleUtils$Margins.bottom = view.getHeight() - subtitleUtils$Margins.top - n;
        }
        else {
            Log.w("nf_subtitles_render", "Origin is null!");
        }
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Margins, left: " + subtitleUtils$Margins.left + ", top: " + subtitleUtils$Margins.top + ", right: " + subtitleUtils$Margins.right + ", bottom: " + subtitleUtils$Margins.bottom);
        }
        return subtitleUtils$Margins;
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
    
    public static int getSubtitleImageMaxHeight(final Context context) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Display w/h: " + displayMetrics.widthPixels + "/" + displayMetrics.heightPixels);
        }
        int n;
        if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
            n = displayMetrics.heightPixels;
        }
        else {
            n = displayMetrics.widthPixels;
        }
        if (n >= 1080) {
            Log.d("nf_subtitles_render", "Using 1080P");
            return 1080;
        }
        if (n >= 720) {
            Log.d("nf_subtitles_render", "Using 720P");
            return 720;
        }
        Log.d("nf_subtitles_render", "Using 480P");
        return 480;
    }
    
    public static int getSubtitleImageMaxWidth(final Context context) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "Display w/h: " + displayMetrics.widthPixels + "/" + displayMetrics.heightPixels);
        }
        int n;
        if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
            n = displayMetrics.widthPixels;
        }
        else {
            n = displayMetrics.heightPixels;
        }
        if (n >= 1920) {
            Log.d("nf_subtitles_render", "Using 1080P");
            return 1920;
        }
        if (n >= 1280) {
            Log.d("nf_subtitles_render", "Using 720P");
            return 1280;
        }
        Log.d("nf_subtitles_render", "Using 480P");
        return 853;
    }
    
    public static Integer getTextColor(final TextStyle textStyle) {
        return ColorMapping.resolveColor(textStyle.getOpacity(), textStyle.getColor());
    }
    
    private static Typeface getTypefaceByWeightForSansSerif(final FontWeight fontWeight) {
        if (fontWeight == null) {
            Log.d("nf_subtitles_render", "No font weight, use sans serif");
            return Typeface.SANS_SERIF;
        }
        final Typeface typeface = SubtitleUtils.sTypeFaceWeightMapForProportional.get(fontWeight.name());
        if (typeface == null) {
            if (Log.isLoggable()) {
                Log.e("nf_subtitles_render", "Typeface for proportional font not found for font weight " + fontWeight.name());
            }
            return Typeface.SANS_SERIF;
        }
        Log.d("nf_subtitles_render", "Found mapping " + typeface);
        return typeface;
    }
    
    public static Integer getWindowColor(final TextStyle textStyle) {
        return ColorMapping.resolveColor(textStyle.getWindowOpacity(), textStyle.getWindowColor());
    }
    
    private static void initTypeFaceMap() {
        Log.d("nf_subtitles_render", "Init typefaces ");
        final FontWeight[] values = FontWeight.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final FontWeight fontWeight = values[i];
            Typeface typeface = null;
            if (FontWeight.Thin == fontWeight) {
                if (AndroidUtils.getAndroidVersion() >= 16) {
                    typeface = Typeface.create("sans-serif-thin", 0);
                }
                else {
                    typeface = Typeface.create(Typeface.SANS_SERIF, 0);
                }
            }
            else if (FontWeight.Light == fontWeight) {
                typeface = Typeface.create("sans-serif-light", 0);
            }
            else if (FontWeight.Book == fontWeight) {
                typeface = Typeface.create("sans-serif-light", 0);
            }
            else if (FontWeight.Regular == fontWeight) {
                typeface = Typeface.create(Typeface.SANS_SERIF, 0);
            }
            else if (FontWeight.Medium == fontWeight) {
                if (AndroidUtils.getAndroidVersion() >= 21) {
                    typeface = Typeface.create("sans-serif-medium", 0);
                }
                else {
                    typeface = Typeface.create(Typeface.SANS_SERIF, 0);
                }
            }
            else if (FontWeight.SemiBold == fontWeight) {
                typeface = Typeface.create(Typeface.SANS_SERIF, 1);
            }
            else if (FontWeight.Bold == fontWeight) {
                typeface = Typeface.create(Typeface.SANS_SERIF, 1);
            }
            else if (FontWeight.Bold == fontWeight) {
                typeface = Typeface.create(Typeface.SANS_SERIF, 1);
            }
            else if (FontWeight.ExtraBlack == fontWeight) {
                typeface = Typeface.create(Typeface.SANS_SERIF, 1);
            }
            if (typeface == null) {
                Log.w("nf_subtitles_render", "No typeface for " + fontWeight);
                typeface = Typeface.SANS_SERIF;
            }
            else {
                Log.d("nf_subtitles_render", "Typeface for " + fontWeight + " is " + typeface);
            }
            SubtitleUtils.sTypeFaceWeightMapForProportional.put(fontWeight.name(), typeface);
        }
    }
    
    public static Rect intersection(final Rect rect, final Rect rect2) {
        return new Rect(Math.max(rect.left, rect2.left), Math.max(rect.top, rect2.top), Math.min(rect.right, rect2.right), Math.min(rect.bottom, rect2.bottom));
    }
    
    public static boolean isNextNodeInSameLine(final List<SubtitleTextNode> list, int n) {
        if (list != null) {
            ++n;
            if (list.size() > n) {
                return list.get(n).getLineBreaks() < 1;
            }
        }
        return false;
    }
    
    public static boolean isPositionDefinedInBlock(final LinearLayout linearLayout, final TextSubtitleBlock textSubtitleBlock) {
        if (linearLayout == null || textSubtitleBlock == null) {
            throw new IllegalArgumentException("region or block is null!");
        }
        Log.d("nf_subtitles_render", "isPositionDefinedInBlock start");
        if (textSubtitleBlock.getRegion() == null) {
            Log.d("nf_subtitles_render", "isPositionDefinedInBlock no region, no need for wrapper");
            return false;
        }
        if (textSubtitleBlock.getTextNodes().size() < 1) {
            Log.w("nf_subtitles_render", "isPositionDefinedInBlock no text blocks!");
            return false;
        }
        final SubtitleTextNode subtitleTextNode = textSubtitleBlock.getTextNodes().get(0);
        if (subtitleTextNode == null || subtitleTextNode.getStyle() == null) {
            Log.w("nf_subtitles_render", "isPositionDefinedInBlock p missing");
            return false;
        }
        final DoubleLength extent = textSubtitleBlock.getStyle().getExtent();
        final DoubleLength origin = textSubtitleBlock.getStyle().getOrigin();
        if (Log.isLoggable()) {
            Log.d("nf_subtitles_render", "isPositionDefinedInBlock extent " + extent);
            Log.d("nf_subtitles_render", "isPositionDefinedInBlock origin " + origin);
        }
        if (DoubleLength.canUse(extent) && DoubleLength.canUse(origin)) {
            Log.d("nf_subtitles_render", "isPositionDefinedInBlock using block extent and origin overrides, return true");
            return true;
        }
        Log.d("nf_subtitles_render", "isPositionDefinedInBlock using region defaults for extent and origin, return false");
        return false;
    }
    
    public static boolean isStrokeTextViewRequired(final TextStyle textStyle) {
        return textStyle != null && textStyle.getOutline() != null && textStyle.getOutline().isStrokeTextRequired();
    }
    
    public static Subtitle newInstance(final JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        final int optInt = jsonObject.optInt("impl", -1);
        if (optInt == 2) {
            return MdxSubtitle.newInstance(jsonObject, jsonObject.getInt("order"));
        }
        if (optInt == 1) {
            return NccpSubtitle.newInstance(jsonObject);
        }
        if (optInt == 3) {
            return new OfflineTextSubtitle(jsonObject);
        }
        if (optInt == 4) {
            return new OfflineImageSubtitle(jsonObject);
        }
        throw new JSONException("Implementation does not support restore " + optInt);
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
        if (StringUtils.isEmpty(s)) {
            Log.e("nf_subtitles_render", "dfxp-badtime: Time string us empty! Invalid time");
        }
        else {
            final Matcher matcher = SubtitleUtils.TICK_T_PATTERN.matcher(s);
            if (matcher.find()) {
                final long safeParseLong = safeParseLong(matcher.group().replaceAll("t", ""));
                if (safeParseLong == -1L) {
                    return safeParseLong;
                }
                return (long)(safeParseLong * n);
            }
            else {
                final Matcher matcher2 = SubtitleUtils.TICK_MS_PATTERN.matcher(s);
                if (matcher2.find()) {
                    return safeParseLong(matcher2.group().replaceAll("ms", ""));
                }
                final Matcher matcher3 = SubtitleUtils.TICK_SEC_PATTERN.matcher(s);
                if (matcher3.find()) {
                    final long safeParseLong2 = safeParseLong(matcher3.group().replaceAll("s", ""));
                    if (safeParseLong2 == -1L) {
                        return safeParseLong2;
                    }
                    return (long)(safeParseLong2 * n);
                }
                else {
                    final Matcher matcher4 = SubtitleUtils.TICK_TIME_PATTERN.matcher(s);
                    if (matcher4.find()) {
                        final String[] safeSplit = StringUtils.safeSplit(matcher4.group(), ":");
                        if (safeSplit.length >= 3) {
                            return (safeParseLong(safeSplit[0]) * 3600L + safeParseLong(safeSplit[1]) * 60L + safeParseLong(safeSplit[2])) * 1000L;
                        }
                        if (Log.isLoggable()) {
                            Log.e("nf_subtitles_render", "dfxp-badtime: Tick time pattern matched, but we do not have 3 groups! This should NOT happen! " + s);
                            return -1L;
                        }
                    }
                    else if (Log.isLoggable()) {
                        Log.e("nf_subtitles_render", "dfxp-badtime: Unknown format " + s);
                        return -1L;
                    }
                }
            }
        }
        return -1L;
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
    
    public static void setAlignmentToRegion(final LinearLayout linearLayout, final TextSubtitleBlock textSubtitleBlock) {
        if (linearLayout == null || textSubtitleBlock == null) {
            throw new IllegalArgumentException("region or block is null!");
        }
        final Region region = textSubtitleBlock.getRegion();
        HorizontalAlignment horizontalAlignment = HorizontalAlignment.center;
        if (region != null && region.getHorizontalAlignment() != null) {
            Log.d("nf_subtitles_render", "Horizontal alignment from region");
            horizontalAlignment = region.getHorizontalAlignment();
        }
        VerticalAlignment verticalAlignment = VerticalAlignment.bottom;
        if (region != null && region.getVerticalAlignment() != null) {
            Log.d("nf_subtitles_render", "Vertical alignment from region");
            verticalAlignment = region.getVerticalAlignment();
        }
        HorizontalAlignment horizontalAlignment2 = horizontalAlignment;
        VerticalAlignment verticalAlignment2 = verticalAlignment;
        if (textSubtitleBlock.getTextNodes().size() > 1) {
            final SubtitleTextNode subtitleTextNode = textSubtitleBlock.getTextNodes().get(0);
            horizontalAlignment2 = horizontalAlignment;
            verticalAlignment2 = verticalAlignment;
            if (subtitleTextNode != null) {
                horizontalAlignment2 = horizontalAlignment;
                verticalAlignment2 = verticalAlignment;
                if (subtitleTextNode.getStyle() != null) {
                    if (subtitleTextNode.getStyle().getHorizontalAlignment() != null) {
                        Log.d("nf_subtitles_render", "Horizontal alignment overide from p!");
                        horizontalAlignment = subtitleTextNode.getStyle().getHorizontalAlignment();
                    }
                    horizontalAlignment2 = horizontalAlignment;
                    verticalAlignment2 = verticalAlignment;
                    if (subtitleTextNode.getStyle().getVerticalAlignment() != null) {
                        Log.d("nf_subtitles_render", "Vertical alignment overide from p!");
                        verticalAlignment2 = subtitleTextNode.getStyle().getVerticalAlignment();
                        horizontalAlignment2 = horizontalAlignment;
                    }
                }
            }
        }
        linearLayout.setGravity(toGravity(horizontalAlignment2, verticalAlignment2));
    }
    
    @SuppressLint({ "NewApi" })
    public static void setLetterSpacing(final TextView textView, final TextStyle textStyle) {
        if (AndroidUtils.getAndroidVersion() < 21 || textView == null) {
            return;
        }
        FontFamilyMapping fontFamily = null;
        if (textStyle != null) {
            fontFamily = textStyle.getFontFamily();
        }
        textView.setLetterSpacing(getDefaultSpacing(fontFamily));
    }
    
    public static int toGravity(final HorizontalAlignment horizontalAlignment, final VerticalAlignment verticalAlignment) {
        int n;
        if (horizontalAlignment == null && verticalAlignment == null) {
            Log.d("nf_subtitles_render", "toGravity default CENTER");
            n = 17;
        }
        else {
            if (horizontalAlignment == null) {
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_render", "toGravity vert " + verticalAlignment.getValue());
                }
                return verticalAlignment.getGravity();
            }
            if (verticalAlignment == null) {
                if (Log.isLoggable()) {
                    Log.d("nf_subtitles_render", "toGravity hor " + horizontalAlignment.getValue());
                }
                return horizontalAlignment.getGravity();
            }
            final int gravity = horizontalAlignment.getGravity();
            final int gravity2 = verticalAlignment.getGravity();
            final int n2 = n = (horizontalAlignment.getGravity() | verticalAlignment.getGravity());
            if (Log.isLoggable()) {
                Log.d("nf_subtitles_render", "toGravity " + n2 + ", hor " + horizontalAlignment.getValue() + ", vert " + verticalAlignment.getValue() + ", plus " + (gravity2 + gravity));
                return n2;
            }
        }
        return n;
    }
    
    public static Typeface toTypeFace(final TextStyle textStyle, final boolean b) {
        Typeface sans_SERIF;
        if (textStyle == null) {
            Log.d("nf_subtitles_render", "No styles, use san serif typeface");
            sans_SERIF = Typeface.SANS_SERIF;
        }
        else {
            Typeface typeface = Typeface.SANS_SERIF;
            if (textStyle.getFontFamily() != null) {
                if (FontFamilyMapping.isSansSerif(textStyle.getFontFamily())) {
                    Log.d("nf_subtitles_render", "Apply sans serif");
                    typeface = getTypefaceByWeightForSansSerif(textStyle.getFontWeight());
                }
                else {
                    Log.d("nf_subtitles_render", "Apply non sans serif");
                    typeface = textStyle.getFontFamily().getTypeface();
                }
            }
            sans_SERIF = typeface;
            if (b) {
                return Typeface.create(typeface, toTypeFaceStyle(textStyle));
            }
        }
        return sans_SERIF;
    }
    
    public static int toTypeFaceStyle(final TextStyle textStyle) {
        if (textStyle != null) {
            if (textStyle.getFontWeight() != null && FontWeight.Bold.getValue() <= textStyle.getFontWeight().getValue()) {
                if (Boolean.TRUE.equals(textStyle.getItalic())) {
                    Log.d("nf_subtitles_render", "toTypeFaceStyle:: BOLD ITALIC");
                    return 3;
                }
                Log.d("nf_subtitles_render", "toTypeFaceStyle:: BOLD");
                return 1;
            }
            else if (Boolean.TRUE.equals(textStyle.getItalic())) {
                Log.d("nf_subtitles_render", "toTypeFaceStyle:: ITALIC");
                return 2;
            }
        }
        return 0;
    }
}
