// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.picker;

import android.content.Context;
import android.widget.Spinner;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemSelectedListener;

class ReactPicker$2 implements AdapterView$OnItemSelectedListener
{
    final /* synthetic */ ReactPicker this$0;
    
    ReactPicker$2(final ReactPicker this$0) {
        this.this$0 = this$0;
    }
    
    public void onItemSelected(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (!this.this$0.mSuppressNextEvent && this.this$0.mOnSelectListener != null) {
            this.this$0.mOnSelectListener.onItemSelected(n);
        }
        this.this$0.mSuppressNextEvent = false;
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
        if (!this.this$0.mSuppressNextEvent && this.this$0.mOnSelectListener != null) {
            this.this$0.mOnSelectListener.onItemSelected(-1);
        }
        this.this$0.mSuppressNextEvent = false;
    }
}
