// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import android.view.View;
import android.widget.AdapterView;
import com.netflix.mediaclient.Log;
import android.widget.SpinnerAdapter;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.Spinner;

public class SeasonsSpinner extends Spinner
{
    private static final int STANDARD_BG_RES_ID = 2130837847;
    private static final String TAG = "SeasonsSpinner";
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
    
    protected SeasonsSpinner(final Context context, final AttributeSet set, final int n, final int n2, final int n3) {
        super(context, set, n, n2, n3);
    }
    
    public int getSeasonIndexBySeasonNumber(final int n) {
        return this.spinnerAdapter.getSeasonIndexBySeasonNumber(n);
    }
    
    public int getSeasonNumberForPosition(final int n) {
        return this.spinnerAdapter.getSeasonNumberForPosition(n);
    }
    
    protected void init() {
        this.setBackgroundResource(2130837847);
        this.setAdapter((SpinnerAdapter)(this.spinnerAdapter = new SeasonsSpinnerAdapter((NetflixActivity)this.getContext())));
    }
    
    public void setOnItemSelectedListener(final AdapterView$OnItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }
    
    public void setSelection(final int selection) {
        Log.v("SeasonsSpinner", "Setting selection to position: " + selection);
        super.setSelection(selection);
        if (this.itemSelectedListener != null) {
            this.itemSelectedListener.onItemSelected((AdapterView)this, (View)this, selection, this.getSelectedItemId());
        }
    }
    
    public void setSelectionWithoutCallback(final int selection) {
        Log.v("SeasonsSpinner", "Setting selection (no callback) to position: " + selection);
        super.setSelection(selection);
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
            backgroundResource = 2130837847;
        }
        else {
            backgroundResource = 2131296356;
        }
        this.setBackgroundResource(backgroundResource);
    }
}
