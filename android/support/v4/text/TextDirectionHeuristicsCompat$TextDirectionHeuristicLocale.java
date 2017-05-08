// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.text;

import java.util.Locale;

class TextDirectionHeuristicsCompat$TextDirectionHeuristicLocale extends TextDirectionHeuristicsCompat$TextDirectionHeuristicImpl
{
    public static final TextDirectionHeuristicsCompat$TextDirectionHeuristicLocale INSTANCE;
    
    static {
        INSTANCE = new TextDirectionHeuristicsCompat$TextDirectionHeuristicLocale();
    }
    
    public TextDirectionHeuristicsCompat$TextDirectionHeuristicLocale() {
        super(null);
    }
    
    @Override
    protected boolean defaultIsRtl() {
        return TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1;
    }
}
