// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.annotation.TargetApi;
import android.view.View$MeasureSpec;
import android.content.res.TypedArray;
import android.support.design.R$dimen;
import android.view.View;
import android.os.Build$VERSION;
import android.support.design.R$style;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.widget.ImageView;
import android.graphics.drawable.Drawable;

class FloatingActionButton$1 implements ShadowViewDelegate
{
    final /* synthetic */ FloatingActionButton this$0;
    
    FloatingActionButton$1(final FloatingActionButton this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public float getRadius() {
        return this.this$0.getSizeDimension() / 2.0f;
    }
    
    @Override
    public void setBackgroundDrawable(final Drawable drawable) {
        FloatingActionButton.access$201(this.this$0, drawable);
    }
    
    @Override
    public void setShadowPadding(final int n, final int n2, final int n3, final int n4) {
        this.this$0.mShadowPadding.set(n, n2, n3, n4);
        this.this$0.setPadding(this.this$0.mContentPadding + n, this.this$0.mContentPadding + n2, this.this$0.mContentPadding + n3, this.this$0.mContentPadding + n4);
    }
}
