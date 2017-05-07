// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import android.os.Bundle;
import com.facebook.widget.FacebookDialog$DialogFeature;
import java.util.EnumSet;
import com.facebook.widget.FacebookDialog$PendingCall;
import android.app.Activity;
import com.facebook.widget.FacebookDialog$Builder;

class LikeActionController$LikeDialogBuilder extends FacebookDialog$Builder<LikeActionController$LikeDialogBuilder>
{
    private String objectId;
    
    public LikeActionController$LikeDialogBuilder(final Activity activity, final String objectId) {
        super(activity);
        this.objectId = objectId;
    }
    
    public FacebookDialog$PendingCall getAppCall() {
        return this.appCall;
    }
    
    public String getApplicationId() {
        return this.applicationId;
    }
    
    @Override
    protected EnumSet<? extends FacebookDialog$DialogFeature> getDialogFeatures() {
        return EnumSet.of((FacebookDialog$DialogFeature)LikeActionController$LikeDialogFeature.LIKE_DIALOG);
    }
    
    @Override
    protected Bundle getMethodArguments() {
        final Bundle bundle = new Bundle();
        bundle.putString("object_id", this.objectId);
        return bundle;
    }
    
    public String getWebFallbackUrl() {
        return this.getWebFallbackUrlInternal();
    }
}
