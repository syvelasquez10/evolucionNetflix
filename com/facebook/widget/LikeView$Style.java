// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

public enum LikeView$Style
{
    BOX_COUNT("box_count", 2), 
    BUTTON("button", 1);
    
    static LikeView$Style DEFAULT;
    
    STANDARD("standard", 0);
    
    private int intValue;
    private String stringValue;
    
    static {
        LikeView$Style.DEFAULT = LikeView$Style.STANDARD;
    }
    
    private LikeView$Style(final String stringValue, final int intValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }
    
    static LikeView$Style fromInt(final int n) {
        final LikeView$Style[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final LikeView$Style likeView$Style = values[i];
            if (likeView$Style.getValue() == n) {
                return likeView$Style;
            }
        }
        return null;
    }
    
    private int getValue() {
        return this.intValue;
    }
    
    @Override
    public String toString() {
        return this.stringValue;
    }
}
