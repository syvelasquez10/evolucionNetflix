// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text;

public class TextDirectionHeuristicsCompat
{
    public static final TextDirectionHeuristicCompat ANYRTL_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_LTR;
    public static final TextDirectionHeuristicCompat FIRSTSTRONG_RTL;
    public static final TextDirectionHeuristicCompat LOCALE;
    public static final TextDirectionHeuristicCompat LTR;
    public static final TextDirectionHeuristicCompat RTL;
    private static final int STATE_FALSE = 1;
    private static final int STATE_TRUE = 0;
    private static final int STATE_UNKNOWN = 2;
    
    static {
        LTR = new TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal(null, false, null);
        RTL = new TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal(null, true, null);
        FIRSTSTRONG_LTR = new TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat$FirstStrong.INSTANCE, false, null);
        FIRSTSTRONG_RTL = new TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat$FirstStrong.INSTANCE, true, null);
        ANYRTL_LTR = new TextDirectionHeuristicsCompat$TextDirectionHeuristicInternal(TextDirectionHeuristicsCompat$AnyStrong.INSTANCE_RTL, false, null);
        LOCALE = TextDirectionHeuristicsCompat$TextDirectionHeuristicLocale.INSTANCE;
    }
    
    private static int isRtlText(final int n) {
        switch (n) {
            default: {
                return 2;
            }
            case 0: {
                return 1;
            }
            case 1:
            case 2: {
                return 0;
            }
        }
    }
    
    private static int isRtlTextOrFormat(final int n) {
        switch (n) {
            default: {
                return 2;
            }
            case 0:
            case 14:
            case 15: {
                return 1;
            }
            case 1:
            case 2:
            case 16:
            case 17: {
                return 0;
            }
        }
    }
}
