// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging.apm.model;

public enum Display$AspectRatio
{
    _16x9("16x9"), 
    _4x3("4x3");
    
    private String desc;
    
    private Display$AspectRatio(final String desc) {
        this.desc = desc;
    }
    
    public static Display$AspectRatio find(final String s) {
        if (Display$AspectRatio._16x9.desc.equals(s)) {
            return Display$AspectRatio._16x9;
        }
        if (Display$AspectRatio._4x3.desc.equals(s)) {
            return Display$AspectRatio._4x3;
        }
        return null;
    }
    
    public String getDesc() {
        return this.desc;
    }
}
