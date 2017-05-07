// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text;

import java.util.Locale;

public final class BidiFormatter
{
    private static final int DEFAULT_FLAGS = 2;
    private static final BidiFormatter DEFAULT_LTR_INSTANCE;
    private static final BidiFormatter DEFAULT_RTL_INSTANCE;
    private static TextDirectionHeuristicCompat DEFAULT_TEXT_DIRECTION_HEURISTIC;
    private static final int DIR_LTR = -1;
    private static final int DIR_RTL = 1;
    private static final int DIR_UNKNOWN = 0;
    private static final String EMPTY_STRING = "";
    private static final int FLAG_STEREO_RESET = 2;
    private static final char LRE = '\u202a';
    private static final char LRM = '\u200e';
    private static final String LRM_STRING;
    private static final char PDF = '\u202c';
    private static final char RLE = '\u202b';
    private static final char RLM = '\u200f';
    private static final String RLM_STRING;
    private final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat;
    private final int mFlags;
    private final boolean mIsRtlContext;
    
    static {
        BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC = TextDirectionHeuristicsCompat.FIRSTSTRONG_LTR;
        LRM_STRING = Character.toString('\u200e');
        RLM_STRING = Character.toString('\u200f');
        DEFAULT_LTR_INSTANCE = new BidiFormatter(false, 2, BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC);
        DEFAULT_RTL_INSTANCE = new BidiFormatter(true, 2, BidiFormatter.DEFAULT_TEXT_DIRECTION_HEURISTIC);
    }
    
    private BidiFormatter(final boolean mIsRtlContext, final int mFlags, final TextDirectionHeuristicCompat mDefaultTextDirectionHeuristicCompat) {
        this.mIsRtlContext = mIsRtlContext;
        this.mFlags = mFlags;
        this.mDefaultTextDirectionHeuristicCompat = mDefaultTextDirectionHeuristicCompat;
    }
    
    private static int getEntryDir(final String s) {
        return new BidiFormatter$DirectionalityEstimator(s, false).getEntryDir();
    }
    
    private static int getExitDir(final String s) {
        return new BidiFormatter$DirectionalityEstimator(s, false).getExitDir();
    }
    
    public static BidiFormatter getInstance() {
        return new BidiFormatter$Builder().build();
    }
    
    public static BidiFormatter getInstance(final Locale locale) {
        return new BidiFormatter$Builder(locale).build();
    }
    
    public static BidiFormatter getInstance(final boolean b) {
        return new BidiFormatter$Builder(b).build();
    }
    
    private static boolean isRtlLocale(final Locale locale) {
        return TextUtilsCompat.getLayoutDirectionFromLocale(locale) == 1;
    }
    
    private String markAfter(final String s, final TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        final boolean rtl = textDirectionHeuristicCompat.isRtl(s, 0, s.length());
        if (!this.mIsRtlContext && (rtl || getExitDir(s) == 1)) {
            return BidiFormatter.LRM_STRING;
        }
        if (this.mIsRtlContext && (!rtl || getExitDir(s) == -1)) {
            return BidiFormatter.RLM_STRING;
        }
        return "";
    }
    
    private String markBefore(final String s, final TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        final boolean rtl = textDirectionHeuristicCompat.isRtl(s, 0, s.length());
        if (!this.mIsRtlContext && (rtl || getEntryDir(s) == 1)) {
            return BidiFormatter.LRM_STRING;
        }
        if (this.mIsRtlContext && (!rtl || getEntryDir(s) == -1)) {
            return BidiFormatter.RLM_STRING;
        }
        return "";
    }
    
    public boolean getStereoReset() {
        return (this.mFlags & 0x2) != 0x0;
    }
    
    public boolean isRtl(final String s) {
        return this.mDefaultTextDirectionHeuristicCompat.isRtl(s, 0, s.length());
    }
    
    public boolean isRtlContext() {
        return this.mIsRtlContext;
    }
    
    public String unicodeWrap(final String s) {
        return this.unicodeWrap(s, this.mDefaultTextDirectionHeuristicCompat, true);
    }
    
    public String unicodeWrap(final String s, final TextDirectionHeuristicCompat textDirectionHeuristicCompat) {
        return this.unicodeWrap(s, textDirectionHeuristicCompat, true);
    }
    
    public String unicodeWrap(final String s, TextDirectionHeuristicCompat textDirectionHeuristicCompat, final boolean b) {
        final boolean rtl = textDirectionHeuristicCompat.isRtl(s, 0, s.length());
        final StringBuilder sb = new StringBuilder();
        if (this.getStereoReset() && b) {
            if (rtl) {
                textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.RTL;
            }
            else {
                textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.LTR;
            }
            sb.append(this.markBefore(s, textDirectionHeuristicCompat));
        }
        if (rtl != this.mIsRtlContext) {
            char c;
            if (rtl) {
                c = '\u202b';
            }
            else {
                c = '\u202a';
            }
            sb.append(c);
            sb.append(s);
            sb.append('\u202c');
        }
        else {
            sb.append(s);
        }
        if (b) {
            if (rtl) {
                textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.RTL;
            }
            else {
                textDirectionHeuristicCompat = TextDirectionHeuristicsCompat.LTR;
            }
            sb.append(this.markAfter(s, textDirectionHeuristicCompat));
        }
        return sb.toString();
    }
    
    public String unicodeWrap(final String s, final boolean b) {
        return this.unicodeWrap(s, this.mDefaultTextDirectionHeuristicCompat, b);
    }
}
