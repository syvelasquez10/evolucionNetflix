// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.media;

public enum PlayerType
{
    device10(10, "JPlayer"), 
    device11(11, "JPlayer Base"), 
    device12(12, "JPlayer2"), 
    device7(7, "XAL"), 
    device8(8, "XAL Main Profile");
    
    private String description;
    private int value;
    
    private PlayerType(final int value, final String description) {
        this.value = value;
        this.description = description;
    }
    
    public static PlayerType toPlayerType(final int n) {
        for (int i = 0; i < values().length; ++i) {
            if (values()[i].value == n) {
                return values()[i];
            }
        }
        return null;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public int getValue() {
        return this.value;
    }
}
