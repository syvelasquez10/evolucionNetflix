// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import java.lang.ref.Reference;
import com.facebook.infer.annotation.Assertions;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable$Callback;
import com.facebook.drawee.interfaces.DraweeController;

final class DraweeRequestHelper
{
    private int mAttachCounter;
    private final DraweeController mDraweeController;
    
    void attach(final FlatViewGroup$InvalidateCallback flatViewGroup$InvalidateCallback) {
        ++this.mAttachCounter;
        if (this.mAttachCounter == 1) {
            this.getDrawable().setCallback((Drawable$Callback)((Reference<Drawable$Callback>)flatViewGroup$InvalidateCallback).get());
            this.mDraweeController.onAttach();
        }
    }
    
    void detach() {
        --this.mAttachCounter;
        if (this.mAttachCounter == 0) {
            this.mDraweeController.onDetach();
        }
    }
    
    Drawable getDrawable() {
        return this.getHierarchy().getTopLevelDrawable();
    }
    
    GenericDraweeHierarchy getHierarchy() {
        return Assertions.assumeNotNull(this.mDraweeController.getHierarchy());
    }
}
