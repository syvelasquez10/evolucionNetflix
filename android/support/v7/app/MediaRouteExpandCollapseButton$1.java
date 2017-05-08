// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.View$OnClickListener;

class MediaRouteExpandCollapseButton$1 implements View$OnClickListener
{
    final /* synthetic */ MediaRouteExpandCollapseButton this$0;
    
    MediaRouteExpandCollapseButton$1(final MediaRouteExpandCollapseButton this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.mIsGroupExpanded = !this.this$0.mIsGroupExpanded;
        if (this.this$0.mIsGroupExpanded) {
            this.this$0.setImageDrawable((Drawable)this.this$0.mExpandAnimationDrawable);
            this.this$0.mExpandAnimationDrawable.start();
            this.this$0.setContentDescription((CharSequence)this.this$0.mCollapseGroupDescription);
        }
        else {
            this.this$0.setImageDrawable((Drawable)this.this$0.mCollapseAnimationDrawable);
            this.this$0.mCollapseAnimationDrawable.start();
            this.this$0.setContentDescription((CharSequence)this.this$0.mExpandGroupDescription);
        }
        if (this.this$0.mListener != null) {
            this.this$0.mListener.onClick(view);
        }
    }
}
