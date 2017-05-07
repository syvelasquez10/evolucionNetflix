// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.view.ViewGroup;
import android.content.Context;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import android.app.Activity;
import android.annotation.SuppressLint;
import android.widget.RelativeLayout;
import com.netflix.mediaclient.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemSelectedListener;

class KidsSeasonSpinner$1 implements AdapterView$OnItemSelectedListener
{
    final /* synthetic */ KidsSeasonSpinner this$0;
    
    KidsSeasonSpinner$1(final KidsSeasonSpinner this$0) {
        this.this$0 = this$0;
    }
    
    public void onItemSelected(final AdapterView<?> adapterView, final View view, int seasonNumberForPosition, final long n) {
        seasonNumberForPosition = this.this$0.spinner.getSeasonNumberForPosition(seasonNumberForPosition);
        Log.v("KidsSeasonSpinner", "Spinner item selected, season: " + seasonNumberForPosition);
        this.this$0.showAdapter.selectSeasonByNumber(seasonNumberForPosition);
    }
    
    public void onNothingSelected(final AdapterView<?> adapterView) {
        Log.v("KidsSeasonSpinner", "Nothing selected");
    }
}
