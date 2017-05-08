// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.util.Log;
import android.view.MotionEvent;
import android.annotation.TargetApi;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.view.View$MeasureSpec;
import android.content.res.Resources;
import android.support.v4.content.res.ConfigurationHelper;
import android.os.Build$VERSION;
import android.content.res.TypedArray;
import android.support.design.R$dimen;
import android.widget.ImageView;
import android.support.design.R$style;
import android.support.design.R$styleable;
import android.util.AttributeSet;
import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatImageHelper;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;

class FloatingActionButton$ShadowDelegateImpl implements ShadowViewDelegate
{
    final /* synthetic */ FloatingActionButton this$0;
    
    FloatingActionButton$ShadowDelegateImpl(final FloatingActionButton this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public float getRadius() {
        return this.this$0.getSizeDimension() / 2.0f;
    }
    
    @Override
    public boolean isCompatPaddingEnabled() {
        return this.this$0.mCompatPadding;
    }
    
    @Override
    public void setBackgroundDrawable(final Drawable drawable) {
        FloatingActionButton.access$001(this.this$0, drawable);
    }
    
    @Override
    public void setShadowPadding(final int n, final int n2, final int n3, final int n4) {
        this.this$0.mShadowPadding.set(n, n2, n3, n4);
        this.this$0.setPadding(this.this$0.mImagePadding + n, this.this$0.mImagePadding + n2, this.this$0.mImagePadding + n3, this.this$0.mImagePadding + n4);
    }
}
