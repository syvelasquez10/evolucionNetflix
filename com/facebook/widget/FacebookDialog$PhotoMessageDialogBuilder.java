// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.util.List;
import java.util.EnumSet;
import android.app.Activity;

public class FacebookDialog$PhotoMessageDialogBuilder extends FacebookDialog$PhotoDialogBuilderBase<FacebookDialog$PhotoMessageDialogBuilder>
{
    public FacebookDialog$PhotoMessageDialogBuilder(final Activity activity) {
        super(activity);
    }
    
    @Override
    protected EnumSet<? extends FacebookDialog$DialogFeature> getDialogFeatures() {
        return EnumSet.of((FacebookDialog$DialogFeature)FacebookDialog$MessageDialogFeature.MESSAGE_DIALOG, FacebookDialog$MessageDialogFeature.PHOTOS);
    }
    
    @Override
    int getMaximumNumberOfPhotos() {
        return FacebookDialog$PhotoMessageDialogBuilder.MAXIMUM_PHOTO_COUNT;
    }
    
    @Override
    public FacebookDialog$PhotoMessageDialogBuilder setFriends(final List<String> list) {
        return this;
    }
    
    @Override
    public FacebookDialog$PhotoMessageDialogBuilder setPlace(final String s) {
        return this;
    }
}
