// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.picker;

import android.widget.AdapterView$OnItemSelectedListener;
import android.content.Context;
import android.widget.Spinner;

public class ReactPicker extends Spinner
{
    private int mMode;
    private ReactPicker$OnSelectListener mOnSelectListener;
    private Integer mPrimaryColor;
    private Integer mStagedSelection;
    private boolean mSuppressNextEvent;
    private final Runnable measureAndLayout;
    
    public ReactPicker(final Context context, final int mMode) {
        super(context, mMode);
        this.mMode = 0;
        this.measureAndLayout = new ReactPicker$1(this);
        this.mMode = mMode;
    }
    
    private void setSelectionWithSuppressEvent(final int selection) {
        if (selection != this.getSelectedItemPosition()) {
            this.mSuppressNextEvent = true;
            this.setSelection(selection);
        }
    }
    
    public int getMode() {
        return this.mMode;
    }
    
    public ReactPicker$OnSelectListener getOnSelectListener() {
        return this.mOnSelectListener;
    }
    
    public Integer getPrimaryColor() {
        return this.mPrimaryColor;
    }
    
    public void requestLayout() {
        super.requestLayout();
        this.post(this.measureAndLayout);
    }
    
    public void setOnSelectListener(final ReactPicker$OnSelectListener mOnSelectListener) {
        if (this.getOnItemSelectedListener() == null) {
            this.mSuppressNextEvent = true;
            this.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new ReactPicker$2(this));
        }
        this.mOnSelectListener = mOnSelectListener;
    }
    
    public void setPrimaryColor(final Integer mPrimaryColor) {
        this.mPrimaryColor = mPrimaryColor;
    }
    
    public void setStagedSelection(final int n) {
        this.mStagedSelection = n;
    }
    
    public void updateStagedSelection() {
        if (this.mStagedSelection != null) {
            this.setSelectionWithSuppressEvent(this.mStagedSelection);
            this.mStagedSelection = null;
        }
    }
}
