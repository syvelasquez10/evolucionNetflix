// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.react.uimanager.ReactShadowNode;
import android.text.SpannableStringBuilder;

abstract class FlatTextShadowNode extends FlatShadowNode
{
    private int mTextBegin;
    private int mTextEnd;
    
    final void applySpans(final SpannableStringBuilder spannableStringBuilder, final boolean b) {
        if (this.mTextBegin != this.mTextEnd || this.shouldAllowEmptySpans()) {
            this.performApplySpans(spannableStringBuilder, this.mTextBegin, this.mTextEnd, b);
        }
    }
    
    final void collectText(final SpannableStringBuilder spannableStringBuilder) {
        this.mTextBegin = spannableStringBuilder.length();
        this.performCollectText(spannableStringBuilder);
        this.mTextEnd = spannableStringBuilder.length();
    }
    
    boolean isEditable() {
        return false;
    }
    
    @Override
    public boolean isVirtual() {
        return true;
    }
    
    protected void notifyChanged(final boolean b) {
        final ReactShadowNode parent = this.getParent();
        if (parent instanceof FlatTextShadowNode) {
            ((FlatTextShadowNode)parent).notifyChanged(b);
        }
    }
    
    protected abstract void performApplySpans(final SpannableStringBuilder p0, final int p1, final int p2, final boolean p3);
    
    protected abstract void performCollectText(final SpannableStringBuilder p0);
    
    boolean shouldAllowEmptySpans() {
        return false;
    }
}
