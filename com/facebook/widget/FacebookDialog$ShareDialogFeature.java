// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

public enum FacebookDialog$ShareDialogFeature implements FacebookDialog$DialogFeature
{
    PHOTOS(20140204), 
    SHARE_DIALOG(20130618), 
    VIDEO(20141028);
    
    private int minVersion;
    
    private FacebookDialog$ShareDialogFeature(final int minVersion) {
        this.minVersion = minVersion;
    }
    
    @Override
    public String getAction() {
        return "com.facebook.platform.action.request.FEED_DIALOG";
    }
    
    @Override
    public int getMinVersion() {
        return this.minVersion;
    }
}
