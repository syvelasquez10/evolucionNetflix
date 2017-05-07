// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

public enum FacebookDialog$OpenGraphActionDialogFeature implements FacebookDialog$DialogFeature
{
    OG_ACTION_DIALOG(20130618);
    
    private int minVersion;
    
    private FacebookDialog$OpenGraphActionDialogFeature(final int minVersion) {
        this.minVersion = minVersion;
    }
    
    @Override
    public String getAction() {
        return "com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG";
    }
    
    @Override
    public int getMinVersion() {
        return this.minVersion;
    }
}
