// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import java.util.List;
import android.view.View;
import android.widget.AdapterView;
import com.netflix.mediaclient.Log;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.AdapterView$OnItemSelectedListener;
import android.widget.Spinner;

public class SeasonsSpinner extends Spinner
{
    private static final int STANDARD_BG_RES_ID = 2130837885;
    private static final String TAG = "SeasonsSpinner";
    private AdapterView$OnItemSelectedListener itemSelectedListener;
    
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
        this.init();
    }
    
    private void init() {
        this.setBackgroundResource(2130837885);
    }
    
    public int getSeasonNumberForPosition(final int n) {
        return ((SeasonsSpinnerAdapter)this.getAdapter()).getSeasonNumberForPosition(n);
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
    
    public int tryGetSeasonIndexBySeasonNumber(final int n) {
        return ((SeasonsSpinnerAdapter)this.getAdapter()).tryGetSeasonIndexBySeasonNumber(n);
    }
    
    public void updateSeasonData(final List<SeasonDetails> list) {
        ((SeasonsSpinnerAdapter)this.getAdapter()).updateSeasonData(list);
        final boolean enabled = ((SeasonsSpinnerAdapter)this.getAdapter()).getCount() > 1;
        this.setEnabled(enabled);
        int backgroundResource;
        if (enabled) {
            backgroundResource = 2130837885;
        }
        else {
            backgroundResource = 2131296356;
        }
        this.setBackgroundResource(backgroundResource);
    }
}
