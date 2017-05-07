// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import java.util.EnumSet;
import android.app.Activity;

public class FacebookDialog$PhotoShareDialogBuilder extends FacebookDialog$PhotoDialogBuilderBase<FacebookDialog$PhotoShareDialogBuilder>
{
    public FacebookDialog$PhotoShareDialogBuilder(final Activity activity) {
        super(activity);
    }
    
    @Override
    protected EnumSet<? extends FacebookDialog$DialogFeature> getDialogFeatures() {
        return EnumSet.of((FacebookDialog$DialogFeature)FacebookDialog$ShareDialogFeature.SHARE_DIALOG, FacebookDialog$ShareDialogFeature.PHOTOS);
    }
    
    @Override
    int getMaximumNumberOfPhotos() {
        return FacebookDialog$PhotoShareDialogBuilder.MAXIMUM_PHOTO_COUNT;
    }
}
