// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.text;

import android.text.SpannableStringBuilder;

class ReactTextShadowNode$SetSpanOperation
{
    protected int end;
    protected int start;
    protected Object what;
    
    ReactTextShadowNode$SetSpanOperation(final int start, final int end, final Object what) {
        this.start = start;
        this.end = end;
        this.what = what;
    }
    
    public void execute(final SpannableStringBuilder spannableStringBuilder) {
        int n = 34;
        if (this.start == 0) {
            n = 18;
        }
        spannableStringBuilder.setSpan(this.what, this.start, this.end, n);
    }
}
