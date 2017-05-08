// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.View;
import android.view.View$OnClickListener;

class TextInputLayout$4 implements View$OnClickListener
{
    final /* synthetic */ TextInputLayout this$0;
    
    TextInputLayout$4(final TextInputLayout this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        this.this$0.passwordVisibilityToggleRequested();
    }
}
