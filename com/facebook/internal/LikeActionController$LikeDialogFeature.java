// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import com.facebook.widget.FacebookDialog$DialogFeature;

enum LikeActionController$LikeDialogFeature implements FacebookDialog$DialogFeature
{
    LIKE_DIALOG(20140701);
    
    private int minVersion;
    
    private LikeActionController$LikeDialogFeature(final int minVersion) {
        this.minVersion = minVersion;
    }
    
    @Override
    public String getAction() {
        return "com.facebook.platform.action.request.LIKE_DIALOG";
    }
    
    @Override
    public int getMinVersion() {
        return this.minVersion;
    }
}
