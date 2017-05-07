// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

public enum LikeView$AuxiliaryViewPosition
{
    BOTTOM("bottom", 0);
    
    static LikeView$AuxiliaryViewPosition DEFAULT;
    
    INLINE("inline", 1), 
    TOP("top", 2);
    
    private int intValue;
    private String stringValue;
    
    static {
        LikeView$AuxiliaryViewPosition.DEFAULT = LikeView$AuxiliaryViewPosition.BOTTOM;
    }
    
    private LikeView$AuxiliaryViewPosition(final String stringValue, final int intValue) {
        this.stringValue = stringValue;
        this.intValue = intValue;
    }
    
    static LikeView$AuxiliaryViewPosition fromInt(final int n) {
        final LikeView$AuxiliaryViewPosition[] values = values();
        for (int length = values.length, i = 0; i < length; ++i) {
            final LikeView$AuxiliaryViewPosition likeView$AuxiliaryViewPosition = values[i];
            if (likeView$AuxiliaryViewPosition.getValue() == n) {
                return likeView$AuxiliaryViewPosition;
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
