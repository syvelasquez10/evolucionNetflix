// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import java.util.List;
import android.widget.AdapterView;
import com.netflix.mediaclient.Log;
import android.view.View;
import com.netflix.mediaclient.util.api.Api16Util;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.AdapterView$OnItemSelectedListener;
import android.graphics.drawable.Drawable;
import android.widget.Spinner;

public class SeasonsSpinner extends Spinner
{
    public static final int NON_USER_SELECTED = 0;
    private static final String TAG = "SeasonsSpinner";
    public static final int USER_SELECTED = 1;
    private Drawable drawableMultipleSeasons;
    private Drawable drawableOneSeason;
    private AdapterView$OnItemSelectedListener itemSelectedListener;
    private AdapterView$OnItemSelectedListener touchListener;
    
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
        this.drawableMultipleSeasons = this.getResources().getDrawable(2130837913);
        this.drawableOneSeason = this.getResources().getDrawable(2131558585);
        Api16Util.setBackgroundDrawableCompat((View)this, this.drawableMultipleSeasons);
        this.setId(2131623951);
    }
    
    public void setBackground(final Drawable drawableMultipleSeasons, final Drawable drawableOneSeason) {
        this.drawableMultipleSeasons = drawableMultipleSeasons;
        this.drawableOneSeason = drawableOneSeason;
    }
    
    public void setNonTouchSelection(final int selection) {
        Log.v("SeasonsSpinner", "Setting selection to position: " + selection);
        super.setSelection(selection);
        if (this.itemSelectedListener != null) {
            this.itemSelectedListener.onItemSelected((AdapterView)this, (View)this, selection, 0L);
        }
    }
    
    public void setOnItemSelectedListener(final AdapterView$OnItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }
    
    public void setOnItemTouchListener(final AdapterView$OnItemSelectedListener touchListener) {
        this.touchListener = touchListener;
    }
    
    public void setSelection(final int selection) {
        Log.v("SeasonsSpinner", "Setting selection to position: " + selection);
        super.setSelection(selection);
        if (this.itemSelectedListener != null) {
            this.itemSelectedListener.onItemSelected((AdapterView)this, (View)this, selection, 1L);
        }
        if (this.touchListener != null) {
            this.touchListener.onItemSelected((AdapterView)this, (View)this, selection, this.getSelectedItemId());
        }
    }
    
    public int tryGetSeasonIndexBySeasonNumber(final int n) {
        return ((SeasonsSpinnerAdapter)this.getAdapter()).tryGetSeasonIndexBySeasonNumber(n);
    }
    
    public void updateSeasonData(final List<SeasonDetails> list) {
        ((SeasonsSpinnerAdapter)this.getAdapter()).updateSeasonData(list);
        final boolean enabled = ((SeasonsSpinnerAdapter)this.getAdapter()).getCount() > 1;
        this.setEnabled(enabled);
        if (enabled) {
            Api16Util.setBackgroundDrawableCompat((View)this, this.drawableMultipleSeasons);
            return;
        }
        Api16Util.setBackgroundDrawableCompat((View)this, this.drawableOneSeason);
    }
}
