// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.FacebookException;
import java.util.List;
import com.facebook.internal.Utility;
import android.os.Bundle;
import android.graphics.Bitmap;
import java.io.File;
import java.util.Collection;
import android.app.Activity;
import java.util.ArrayList;

abstract class FacebookDialog$PhotoDialogBuilderBase<CONCRETE extends FacebookDialog$PhotoDialogBuilderBase<?>> extends FacebookDialog$Builder<CONCRETE>
{
    static int MAXIMUM_PHOTO_COUNT;
    private ArrayList<String> friends;
    private ArrayList<String> imageAttachmentUrls;
    private String place;
    
    static {
        FacebookDialog$PhotoDialogBuilderBase.MAXIMUM_PHOTO_COUNT = 6;
    }
    
    public FacebookDialog$PhotoDialogBuilderBase(final Activity activity) {
        super(activity);
        this.imageAttachmentUrls = new ArrayList<String>();
    }
    
    public CONCRETE addPhotoFiles(final Collection<File> collection) {
        this.imageAttachmentUrls.addAll(this.addImageAttachmentFiles(collection));
        return (CONCRETE)this;
    }
    
    public CONCRETE addPhotos(final Collection<Bitmap> collection) {
        this.imageAttachmentUrls.addAll(this.addImageAttachments(collection));
        return (CONCRETE)this;
    }
    
    abstract int getMaximumNumberOfPhotos();
    
    @Override
    protected Bundle getMethodArguments() {
        final Bundle bundle = new Bundle();
        this.putExtra(bundle, "PLACE", this.place);
        bundle.putStringArrayList("PHOTOS", (ArrayList)this.imageAttachmentUrls);
        if (!Utility.isNullOrEmpty(this.friends)) {
            bundle.putStringArrayList("FRIENDS", (ArrayList)this.friends);
        }
        return bundle;
    }
    
    @Override
    protected Bundle setBundleExtras(final Bundle bundle) {
        this.putExtra(bundle, "com.facebook.platform.extra.APPLICATION_ID", this.applicationId);
        this.putExtra(bundle, "com.facebook.platform.extra.APPLICATION_NAME", this.applicationName);
        this.putExtra(bundle, "com.facebook.platform.extra.PLACE", this.place);
        bundle.putStringArrayList("com.facebook.platform.extra.PHOTOS", (ArrayList)this.imageAttachmentUrls);
        if (!Utility.isNullOrEmpty(this.friends)) {
            bundle.putStringArrayList("com.facebook.platform.extra.FRIENDS", (ArrayList)this.friends);
        }
        return bundle;
    }
    
    public CONCRETE setFriends(final List<String> list) {
        ArrayList<String> friends;
        if (list == null) {
            friends = null;
        }
        else {
            friends = new ArrayList<String>(list);
        }
        this.friends = friends;
        return (CONCRETE)this;
    }
    
    public CONCRETE setPlace(final String place) {
        this.place = place;
        return (CONCRETE)this;
    }
    
    @Override
    void validate() {
        super.validate();
        if (this.imageAttachmentUrls.isEmpty()) {
            throw new FacebookException("Must specify at least one photo.");
        }
        if (this.imageAttachmentUrls.size() > this.getMaximumNumberOfPhotos()) {
            throw new FacebookException(String.format("Cannot add more than %d photos.", this.getMaximumNumberOfPhotos()));
        }
    }
}
