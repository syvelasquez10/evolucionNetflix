// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.util.EnumSet;
import android.app.Activity;

public class FacebookDialog$ShareDialogBuilder extends FacebookDialog$ShareDialogBuilderBase<FacebookDialog$ShareDialogBuilder>
{
    public FacebookDialog$ShareDialogBuilder(final Activity activity) {
        super(activity);
    }
    
    @Override
    protected EnumSet<? extends FacebookDialog$DialogFeature> getDialogFeatures() {
        return EnumSet.of((FacebookDialog$DialogFeature)FacebookDialog$ShareDialogFeature.SHARE_DIALOG);
    }
}
