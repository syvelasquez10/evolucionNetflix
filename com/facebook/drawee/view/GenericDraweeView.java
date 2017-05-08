// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.view;

import android.content.Context;
import com.facebook.drawee.generic.GenericDraweeHierarchy;

public class GenericDraweeView extends DraweeView<GenericDraweeHierarchy>
{
    public GenericDraweeView(final Context context, final GenericDraweeHierarchy hierarchy) {
        super(context);
        this.setHierarchy(hierarchy);
    }
}
