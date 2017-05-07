// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

public enum FacebookDialog$MessageDialogFeature implements FacebookDialog$DialogFeature
{
    MESSAGE_DIALOG(20140204), 
    PHOTOS(20140324);
    
    private int minVersion;
    
    private FacebookDialog$MessageDialogFeature(final int minVersion) {
        this.minVersion = minVersion;
    }
    
    @Override
    public String getAction() {
        return "com.facebook.platform.action.request.MESSAGE_DIALOG";
    }
    
    @Override
    public int getMinVersion() {
        return this.minVersion;
    }
}
