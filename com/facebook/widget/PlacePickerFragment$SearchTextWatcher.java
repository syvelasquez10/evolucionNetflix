// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import android.text.Editable;
import android.text.TextWatcher;

class PlacePickerFragment$SearchTextWatcher implements TextWatcher
{
    final /* synthetic */ PlacePickerFragment this$0;
    
    private PlacePickerFragment$SearchTextWatcher(final PlacePickerFragment this$0) {
        this.this$0 = this$0;
    }
    
    public void afterTextChanged(final Editable editable) {
    }
    
    public void beforeTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
    }
    
    public void onTextChanged(final CharSequence charSequence, final int n, final int n2, final int n3) {
        this.this$0.onSearchBoxTextChanged(charSequence.toString(), false);
    }
}
