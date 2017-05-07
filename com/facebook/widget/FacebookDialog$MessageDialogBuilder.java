// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.util.List;
import java.util.EnumSet;
import android.app.Activity;

public class FacebookDialog$MessageDialogBuilder extends FacebookDialog$ShareDialogBuilderBase<FacebookDialog$MessageDialogBuilder>
{
    public FacebookDialog$MessageDialogBuilder(final Activity activity) {
        super(activity);
    }
    
    @Override
    protected EnumSet<? extends FacebookDialog$DialogFeature> getDialogFeatures() {
        return EnumSet.of((FacebookDialog$DialogFeature)FacebookDialog$MessageDialogFeature.MESSAGE_DIALOG);
    }
    
    @Override
    public FacebookDialog$MessageDialogBuilder setFriends(final List<String> list) {
        return this;
    }
    
    @Override
    public FacebookDialog$MessageDialogBuilder setPlace(final String s) {
        return this;
    }
}
