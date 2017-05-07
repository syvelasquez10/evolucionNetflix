// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import android.view.View;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;

class ProfileSelectionActivity$Holder
{
    private final AdvancedImageView img;
    private final TextView title;
    private final View topEditImg;
    
    public ProfileSelectionActivity$Holder(final AdvancedImageView img, final TextView title, final View topEditImg) {
        this.img = img;
        this.title = title;
        this.topEditImg = topEditImg;
        img.setPressedStateHandlerEnabled(false);
    }
}
