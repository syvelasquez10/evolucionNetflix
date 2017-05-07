// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text;

import android.os.Build$VERSION;
import java.util.Locale;

public class TextUtilsCompat
{
    private static String ARAB_SCRIPT_SUBTAG;
    private static String HEBR_SCRIPT_SUBTAG;
    private static final TextUtilsCompat$TextUtilsCompatImpl IMPL;
    public static final Locale ROOT;
    
    static {
        if (Build$VERSION.SDK_INT >= 17) {
            IMPL = new TextUtilsCompat$TextUtilsCompatJellybeanMr1Impl(null);
        }
        else {
            IMPL = new TextUtilsCompat$TextUtilsCompatImpl(null);
        }
        ROOT = new Locale("", "");
        TextUtilsCompat.ARAB_SCRIPT_SUBTAG = "Arab";
        TextUtilsCompat.HEBR_SCRIPT_SUBTAG = "Hebr";
    }
    
    public static int getLayoutDirectionFromLocale(final Locale locale) {
        return TextUtilsCompat.IMPL.getLayoutDirectionFromLocale(locale);
    }
}
