// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.util.EnumSet;
import com.facebook.model.OpenGraphAction;
import android.app.Activity;

public class FacebookDialog$OpenGraphActionDialogBuilder extends FacebookDialog$OpenGraphDialogBuilderBase<FacebookDialog$OpenGraphActionDialogBuilder>
{
    public FacebookDialog$OpenGraphActionDialogBuilder(final Activity activity, final OpenGraphAction openGraphAction, final String s) {
        super(activity, openGraphAction, s);
    }
    
    public FacebookDialog$OpenGraphActionDialogBuilder(final Activity activity, final OpenGraphAction openGraphAction, final String s, final String s2) {
        super(activity, openGraphAction, s, s2);
    }
    
    @Override
    protected EnumSet<? extends FacebookDialog$DialogFeature> getDialogFeatures() {
        return EnumSet.of((FacebookDialog$DialogFeature)FacebookDialog$OpenGraphActionDialogFeature.OG_ACTION_DIALOG);
    }
}
