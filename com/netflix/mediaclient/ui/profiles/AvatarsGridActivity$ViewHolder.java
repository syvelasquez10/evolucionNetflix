// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.profiles;

import android.widget.ImageView;
import com.netflix.mediaclient.android.widget.AdvancedImageView;

class AvatarsGridActivity$ViewHolder
{
    private final AdvancedImageView img;
    private final ImageView topEditImg;
    
    public AvatarsGridActivity$ViewHolder(final AdvancedImageView img, final ImageView topEditImg) {
        this.img = img;
        this.topEditImg = topEditImg;
        img.setPressedStateHandlerEnabled(false);
    }
}
