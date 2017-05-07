// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.Locale;

public class TextUtilsCompat
{
    private static String ARAB_SCRIPT_SUBTAG;
    private static String HEBR_SCRIPT_SUBTAG;
    public static final Locale ROOT;
    
    static {
        ROOT = new Locale("", "");
        TextUtilsCompat.ARAB_SCRIPT_SUBTAG = "Arab";
        TextUtilsCompat.HEBR_SCRIPT_SUBTAG = "Hebr";
    }
    
    private static int getLayoutDirectionFromFirstChar(final Locale locale) {
        switch (Character.getDirectionality(locale.getDisplayName(locale).charAt(0))) {
            default: {
                return 0;
            }
            case 1:
            case 2: {
                return 1;
            }
        }
    }
    
    public static int getLayoutDirectionFromLocale(@Nullable final Locale locale) {
        if (locale != null && !locale.equals(TextUtilsCompat.ROOT)) {
            final String script = ICUCompat.getScript(ICUCompat.addLikelySubtags(locale.toString()));
            if (script == null) {
                return getLayoutDirectionFromFirstChar(locale);
            }
            if (script.equalsIgnoreCase(TextUtilsCompat.ARAB_SCRIPT_SUBTAG) || script.equalsIgnoreCase(TextUtilsCompat.HEBR_SCRIPT_SUBTAG)) {
                return 1;
            }
        }
        return 0;
    }
    
    @NonNull
    public static String htmlEncode(@NonNull final String s) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                default: {
                    sb.append(char1);
                    break;
                }
                case 60: {
                    sb.append("&lt;");
                    break;
                }
                case 62: {
                    sb.append("&gt;");
                    break;
                }
                case 38: {
                    sb.append("&amp;");
                    break;
                }
                case 39: {
                    sb.append("&#39;");
                    break;
                }
                case 34: {
                    sb.append("&quot;");
                    break;
                }
            }
        }
        return sb.toString();
    }
}
