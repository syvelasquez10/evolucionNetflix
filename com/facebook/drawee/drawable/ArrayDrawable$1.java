// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.drawee.drawable;

import android.graphics.drawable.Drawable;

class ArrayDrawable$1 implements DrawableParent
{
    final /* synthetic */ ArrayDrawable this$0;
    final /* synthetic */ int val$index;
    
    ArrayDrawable$1(final ArrayDrawable this$0, final int val$index) {
        this.this$0 = this$0;
        this.val$index = val$index;
    }
    
    @Override
    public Drawable getDrawable() {
        return this.this$0.getDrawable(this.val$index);
    }
    
    @Override
    public Drawable setDrawable(final Drawable drawable) {
        return this.this$0.setDrawable(this.val$index, drawable);
    }
}
