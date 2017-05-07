// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.details;

import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemSelectedListener;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.view.ViewGroup;
import android.content.Context;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import android.app.Activity;
import android.annotation.SuppressLint;
import android.widget.RelativeLayout;

@SuppressLint({ "ViewConstructor" })
public class KidsSeasonSpinner extends RelativeLayout
{
    private static final String TAG = "KidsSeasonSpinner";
    private final Activity activity;
    private final KidsShowDetailsAdapter showAdapter;
    private final SeasonsSpinner spinner;
    
    public KidsSeasonSpinner(final KidsShowDetailsFrag kidsShowDetailsFrag, final KidsShowDetailsAdapter showAdapter) {
        super((Context)kidsShowDetailsFrag.getActivity());
        this.activity = kidsShowDetailsFrag.getActivity();
        this.showAdapter = showAdapter;
        this.activity.getLayoutInflater().inflate(2130903108, (ViewGroup)this);
        this.setBackgroundColor(this.activity.getResources().getColor(2131296371));
        this.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, -2));
        final List<SeasonDetails> seasons = this.showAdapter.getSeasons();
        Log.v("KidsSeasonSpinner", "Creating season spinner, num seasons: " + seasons.size());
        (this.spinner = (SeasonsSpinner)this.findViewById(2131165426)).updateSeasonData(seasons);
        this.spinner.setOnItemSelectedListener((AdapterView$OnItemSelectedListener)new AdapterView$OnItemSelectedListener() {
            public void onItemSelected(final AdapterView<?> adapterView, final View view, int seasonNumberForPosition, final long n) {
                seasonNumberForPosition = KidsSeasonSpinner.this.spinner.getSeasonNumberForPosition(seasonNumberForPosition);
                Log.v("KidsSeasonSpinner", "Spinner item selected, season: " + seasonNumberForPosition);
                KidsSeasonSpinner.this.showAdapter.selectSeasonByNumber(seasonNumberForPosition);
            }
            
            public void onNothingSelected(final AdapterView<?> adapterView) {
                Log.v("KidsSeasonSpinner", "Nothing selected");
            }
        });
    }
    
    public void setSeasonNumber(final int n) {
        Log.v("KidsSeasonSpinner", "Setting curr season number: " + n);
        this.spinner.setSelectionWithoutCallback(this.spinner.getSeasonIndexBySeasonNumber(n));
    }
}
