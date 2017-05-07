// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.FacebookException;
import android.os.Bundle;
import java.io.File;
import android.app.Activity;

abstract class FacebookDialog$VideoDialogBuilderBase<CONCRETE extends FacebookDialog$VideoDialogBuilderBase<?>> extends FacebookDialog$Builder<CONCRETE>
{
    private String place;
    private String videoAttachmentUrl;
    
    public FacebookDialog$VideoDialogBuilderBase(final Activity activity) {
        super(activity);
    }
    
    public CONCRETE addVideoFile(final File file) {
        this.videoAttachmentUrl = this.addVideoAttachmentFile(file);
        return (CONCRETE)this;
    }
    
    @Override
    protected Bundle getMethodArguments() {
        final Bundle bundle = new Bundle();
        this.putExtra(bundle, "PLACE", this.place);
        bundle.putString("VIDEO", this.videoAttachmentUrl);
        return bundle;
    }
    
    public CONCRETE setPlace(final String place) {
        this.place = place;
        return (CONCRETE)this;
    }
    
    @Override
    void validate() {
        super.validate();
        if (this.videoAttachmentUrl == null || this.videoAttachmentUrl.isEmpty()) {
            throw new FacebookException("Must specify at least one video.");
        }
    }
}
