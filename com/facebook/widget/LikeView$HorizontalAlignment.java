// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

public enum LikeView$HorizontalAlignment
{
    CENTER("center", 0);
    
    static LikeView$HorizontalAlignment DEFAULT;
    
    LEFT("left", 1), 
    RIGHT("right", 2);
    
    private int intValue;
    private String stringValue;
    
    static {
        LikeView$HorizontalAlignment.DEFAULT = LikeView$HorizontalAlignment.CENTER;
    }
    
    private LikeView$HorizontalAlignment(final String stringValue, final int intValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }
    
    static LikeView$HorizontalAlignment fromInt(final int n) {
        final LikeView$HorizontalAlignment[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final LikeView$HorizontalAlignment likeView$HorizontalAlignment = values[i];
            if (likeView$HorizontalAlignment.getValue() == n) {
                return likeView$HorizontalAlignment;
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
