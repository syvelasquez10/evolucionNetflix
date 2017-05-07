// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

public enum FacebookDialog$OpenGraphMessageDialogFeature implements FacebookDialog$DialogFeature
{
    OG_MESSAGE_DIALOG(20140204);
    
    private int minVersion;
    
    private FacebookDialog$OpenGraphMessageDialogFeature(final int minVersion) {
        this.minVersion = minVersion;
    }
    
    @Override
    public String getAction() {
        return "com.facebook.platform.action.request.OGMESSAGEPUBLISH_DIALOG";
    }
    
    @Override
    public int getMinVersion() {
        return this.minVersion;
    }
}
