// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPSubTitle;

public interface voSubTitleFormatSetting
{
    public static final int VOOSMP_FontStyle_Default = 0;
    public static final int VOOSMP_FontStyle_Default_Casual = 5;
    public static final int VOOSMP_FontStyle_Default_Cursive = 6;
    public static final int VOOSMP_FontStyle_Default_Monospaced_without_serifs = 3;
    public static final int VOOSMP_FontStyle_Default_Proportionally_spaced_with_serifs = 2;
    public static final int VOOSMP_FontStyle_Default_Proportionally_spaced_without_serifs = 4;
    public static final int VOOSMP_FontStyle_Default_Small_capitals = 7;
    public static final int VOOSMP_Monospaced_with_serifs = 1;
    
    void reset();
    
    void setBackgroundColor(final int p0);
    
    void setBackgroundOpacity(final int p0);
    
    void setEdgeColor(final int p0);
    
    void setEdgeOpacity(final int p0);
    
    void setEdgeType(final int p0);
    
    void setFontBold(final boolean p0);
    
    void setFontColor(final int p0);
    
    void setFontItalic(final boolean p0);
    
    void setFontName(final String p0);
    
    void setFontOpacity(final int p0);
    
    void setFontSizeScale(final int p0);
    
    void setFontStyle(final int p0);
    
    void setFontUnderline(final boolean p0);
    
    void setWindowColor(final int p0);
    
    void setWindowOpacity(final int p0);
}
