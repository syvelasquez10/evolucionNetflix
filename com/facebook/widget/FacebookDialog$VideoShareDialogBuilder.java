// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.util.EnumSet;
import android.app.Activity;

public class FacebookDialog$VideoShareDialogBuilder extends FacebookDialog$VideoDialogBuilderBase<FacebookDialog$VideoShareDialogBuilder>
{
    public FacebookDialog$VideoShareDialogBuilder(final Activity activity) {
        super(activity);
    }
    
    @Override
    protected EnumSet<? extends FacebookDialog$DialogFeature> getDialogFeatures() {
        return EnumSet.of((FacebookDialog$DialogFeature)FacebookDialog$ShareDialogFeature.SHARE_DIALOG, FacebookDialog$ShareDialogFeature.VIDEO);
    }
}
