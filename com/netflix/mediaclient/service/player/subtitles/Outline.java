// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles;

import com.netflix.mediaclient.util.StringUtils;

public final class Outline
{
    private static Integer DEFAULT_OUTLINE_BLUR;
    private static String DEFAULT_OUTLINE_COLOR;
    private static Integer DEFAULT_OUTLINE_WIDTH;
    private String mEdgeColor;
    private CharacterEdgeTypeMapping mEdgeType;
    private Integer mOutlineBlur;
    private Integer mOutlineWidth;
    
    static {
        Outline.DEFAULT_OUTLINE_WIDTH = 1;
        Outline.DEFAULT_OUTLINE_BLUR = 0;
        Outline.DEFAULT_OUTLINE_COLOR = "000000";
    }
    
    private Outline() {
        this.mEdgeType = CharacterEdgeTypeMapping.NONE;
    }
    
    public Outline(final CharacterEdgeTypeMapping mEdgeType, final String mEdgeColor, final Integer mOutlineWidth, final Integer mOutlineBlur) {
        this.mEdgeType = CharacterEdgeTypeMapping.NONE;
        this.mEdgeType = mEdgeType;
        this.mEdgeColor = mEdgeColor;
        this.mOutlineWidth = mOutlineWidth;
        this.mOutlineBlur = mOutlineBlur;
    }
    
    static Outline createInstance(final String s) {
        int n = 0;
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        final Outline outline = new Outline();
        if (CharacterEdgeTypeMapping.NONE.getLookupValue().equalsIgnoreCase(s)) {
            outline.mEdgeType = CharacterEdgeTypeMapping.NONE;
        }
        else {
            final String[] safeSplit = StringUtils.safeSplit(s, " ");
            if (StringUtils.startsWithDigit(s)) {
                if (safeSplit.length < 1) {
                    return null;
                }
            }
            else {
                if (safeSplit.length < 2) {
                    return null;
                }
                outline.mEdgeColor = ColorMapping.findColor(safeSplit[0]);
                n = 1;
            }
            outline.mOutlineWidth = StringUtils.safeParsePixelSize(safeSplit[n]);
            final int n2 = n + 1;
            if (n2 < safeSplit.length) {
                outline.mOutlineBlur = StringUtils.safeParsePixelSize(safeSplit[n2]);
            }
        }
        if (outline.mOutlineBlur != null) {
            outline.mEdgeType = CharacterEdgeTypeMapping.DROP_SHADOW;
        }
        else {
            outline.mEdgeType = CharacterEdgeTypeMapping.UNIFORM;
        }
        return outline;
    }
    
    public static Outline getDefaultOutline() {
        return new Outline(CharacterEdgeTypeMapping.UNIFORM, Outline.DEFAULT_OUTLINE_COLOR, Outline.DEFAULT_OUTLINE_WIDTH, Outline.DEFAULT_OUTLINE_BLUR);
    }
    
    public String getEdgeColor() {
        return this.mEdgeColor;
    }
    
    public CharacterEdgeTypeMapping getEdgeType() {
        return this.mEdgeType;
    }
    
    public Integer getOutlineBlur() {
        return this.mOutlineBlur;
    }
    
    public Integer getOutlineWidth() {
        return this.mOutlineWidth;
    }
    
    public Outline$Shadow getShadow() {
        if (this.mEdgeType == null) {
            return null;
        }
        return this.mEdgeType.getShadow();
    }
    
    public boolean isOutlineRequired() {
        return !CharacterEdgeTypeMapping.NONE.equals(this.mEdgeType) && this.mEdgeType != null;
    }
    
    public boolean isStrokeTextRequired() {
        return CharacterEdgeTypeMapping.UNIFORM.equals(this.mEdgeType);
    }
    
    public void setEdgeColor(final String mEdgeColor) {
        this.mEdgeColor = mEdgeColor;
    }
    
    public void setEdgeType(final CharacterEdgeTypeMapping mEdgeType) {
        this.mEdgeType = mEdgeType;
    }
    
    @Override
    public String toString() {
        return "Outline [mEdgeType=" + this.mEdgeType + ", mEdgeColor=" + this.mEdgeColor + ", mOutlineWidth=" + this.mOutlineWidth + ", mOutlineBlur=" + this.mOutlineBlur + "]";
    }
}
