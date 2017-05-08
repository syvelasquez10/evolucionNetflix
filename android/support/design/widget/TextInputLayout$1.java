// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.text.Editable;
import android.text.TextWatcher;

class TextInputLayout$1 implements TextWatcher
{
    final /* synthetic */ TextInputLayout this$0;
    
    TextInputLayout$1(final TextInputLayout this$0) {
        this.this$0 = this$0;
    }
    
    public void afterTextChanged(final Editable editable) {
        this.this$0.updateLabelState(true);
        if (this.this$0.mCounterEnabled) {
            this.this$0.updateCounter(editable.length());
        }
    }
    
    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
    
    public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
}
