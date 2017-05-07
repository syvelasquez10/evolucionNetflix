// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.util.EnumSet;
import com.facebook.model.OpenGraphAction;
import android.app.Activity;

public class FacebookDialog$OpenGraphMessageDialogBuilder extends FacebookDialog$OpenGraphDialogBuilderBase<FacebookDialog$OpenGraphMessageDialogBuilder>
{
    public FacebookDialog$OpenGraphMessageDialogBuilder(final Activity activity, final OpenGraphAction openGraphAction, final String s) {
        super(activity, openGraphAction, s);
    }
    
    @Override
    protected EnumSet<? extends FacebookDialog$DialogFeature> getDialogFeatures() {
        return EnumSet.of((FacebookDialog$DialogFeature)FacebookDialog$OpenGraphMessageDialogFeature.OG_MESSAGE_DIALOG);
    }
}
