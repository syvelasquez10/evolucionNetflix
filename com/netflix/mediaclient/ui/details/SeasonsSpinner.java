// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.SeasonDetails;
import java.util.List;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SpinnerAdapter;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.Spinner;

public class SeasonsSpinner extends Spinner
{
    private static final int STANDARD_BG_ID = 2130837839;
    private AdapterView$OnItemSelectedListener itemSelectedListener;
    private SeasonsSpinnerAdapter spinnerAdapter;
    
    public SeasonsSpinner(final Context context) {
        super(context, (AttributeSet)null);
        this.init();
    }
    
    public SeasonsSpinner(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    public SeasonsSpinner(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.init();
    }
    
    private void init() {
        this.setBackgroundResource(2130837839);
        this.setAdapter((SpinnerAdapter)(this.spinnerAdapter = new SeasonsSpinnerAdapter(this.getContext())));
    }
    
    public int getSeasonIndexBySeasonNumber(final int n) {
        return this.spinnerAdapter.getSeasonIndexBySeasonNumber(n);
    }
    
    public void setOnItemSelectedListener(final AdapterView$OnItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }
    
    public void setSelection(final int selection) {
        super.setSelection(selection);
        if (this.itemSelectedListener != null) {
            this.itemSelectedListener.onItemSelected((AdapterView)this, (View)this, selection, this.getSelectedItemId());
        }
    }
    
    public void updateSeasonData(final List<SeasonDetails> list) {
        boolean enabled = true;
        this.spinnerAdapter.updateSeasonData(list);
        if (this.spinnerAdapter.getCount() <= 1) {
            enabled = false;
        }
        this.setEnabled(enabled);
        int backgroundResource;
        if (enabled) {
            backgroundResource = 2130837839;
        }
        else {
            backgroundResource = 2131165219;
        }
        this.setBackgroundResource(backgroundResource);
    }
}
