// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.picker;

import android.view.View$MeasureSpec;

class ReactPicker$1 implements Runnable
{
    final /* synthetic */ ReactPicker this$0;
    
    ReactPicker$1(final ReactPicker this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        this.this$0.measure(View$MeasureSpec.makeMeasureSpec(this.this$0.getWidth(), 1073741824), View$MeasureSpec.makeMeasureSpec(this.this$0.getHeight(), 1073741824));
        this.this$0.layout(this.this$0.getLeft(), this.this$0.getTop(), this.this$0.getRight(), this.this$0.getBottom());
    }
}
