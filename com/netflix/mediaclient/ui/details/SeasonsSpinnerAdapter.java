// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import java.util.Collection;
import android.support.v4.content.ContextCompat;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.activity.FragmentHostActivity;
import java.util.ArrayList;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import java.util.List;
import android.content.Context;
import android.widget.BaseAdapter;

public class SeasonsSpinnerAdapter extends BaseAdapter
{
    private static final String TAG = "SeasonsSpinnerAdapter";
    private final Context context;
    private int dropDownBackgroundColor;
    private int dropDownTextColor;
    private int itemBackgroundResource;
    private final List<SeasonDetails> seasons;
    private SeasonsSpinnerAdapter$IViewCreator viewCreator;
    
    public SeasonsSpinnerAdapter(final NetflixActivity context, final SeasonsSpinnerAdapter$IViewCreator viewCreator) {
        this.seasons = new ArrayList<SeasonDetails>();
        this.dropDownTextColor = -1;
        this.dropDownBackgroundColor = -1;
        this.context = (Context)context;
        this.viewCreator = viewCreator;
    }
    
    private void logException(final int n) {
        if (this.context instanceof FragmentHostActivity) {
            final Fragment primaryFrag = ((FragmentHostActivity)this.context).getPrimaryFrag();
            if (primaryFrag instanceof EpisodesFrag) {
                final String format = String.format("SPY-8698, null season found, show id = %s , position = %d", ((EpisodesFrag)primaryFrag).getShowId(), n);
                Log.e("SeasonsSpinnerAdapter", format);
                ErrorLoggingManager.logHandledException(format);
            }
        }
    }
    
    private void setIdForTest(final ViewGroup viewGroup) {
        if (viewGroup == null) {
            return;
        }
        viewGroup.setId(2131820567);
    }
    
    public int getCount() {
        return this.seasons.size();
    }
    
    public View getDropDownView(final int n, final View view, final ViewGroup idForTest) {
        TextView textView = (TextView)view;
        if (textView == null) {
            textView = (TextView)this.viewCreator.createItemView();
        }
        this.setIdForTest(idForTest);
        idForTest.setOverScrollMode(2);
        if (this.dropDownBackgroundColor != -1) {
            textView.setBackgroundResource(this.dropDownBackgroundColor);
        }
        if (this.dropDownTextColor != -1) {
            textView.setTextColor(idForTest.getResources().getColor(this.dropDownTextColor));
        }
        textView.setText((CharSequence)this.getItem(n).getTitle());
        KidsUtils.setBackgroundIfApplicable((View)textView);
        KidsUtils.setTextColorIfApplicable(textView);
        return (View)textView;
    }
    
    public SeasonDetails getItem(final int n) {
        if (n < 0 || n >= this.getCount()) {
            Log.w("SeasonsSpinnerAdapter", "Position requested (" + n + ") is outside of count: " + this.getCount());
            return null;
        }
        return this.seasons.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(int itemBackgroundResource, final View view, final ViewGroup viewGroup) {
        TextView textView = (TextView)view;
        if (textView == null) {
            textView = (TextView)this.viewCreator.createItemView();
        }
        final SeasonDetails item = this.getItem(itemBackgroundResource);
        if (item != null) {
            textView.setTag((Object)item.getSeasonNumber());
            textView.setText((CharSequence)item.getTitle());
        }
        else {
            this.logException(itemBackgroundResource);
        }
        if (viewGroup instanceof SeasonsSpinner) {
            itemBackgroundResource = 2131755265;
        }
        else {
            itemBackgroundResource = this.itemBackgroundResource;
        }
        textView.setBackgroundResource(itemBackgroundResource);
        textView.setTextColor(ContextCompat.getColor(viewGroup.getContext(), 2131755210));
        KidsUtils.manageSectionTextColor(textView, VideoDetailsViewGroup$Section.SPINNER);
        return (View)textView;
    }
    
    public void setDropDownBackgroundColor(final int dropDownBackgroundColor) {
        this.dropDownBackgroundColor = dropDownBackgroundColor;
    }
    
    public void setDropDownTextColor(final int dropDownTextColor) {
        this.dropDownTextColor = dropDownTextColor;
    }
    
    public void setItemBackgroundColor(final int itemBackgroundResource) {
        this.itemBackgroundResource = itemBackgroundResource;
    }
    
    public int tryGetEpisodeIndexBySeasonNumber(int n) {
        final int n2 = 0;
        int i = 0;
        int n3 = 0;
        while (i < this.getCount()) {
            if (i == n) {
                Log.v("SeasonsSpinnerAdapter", "Found season index: " + i);
                return n3;
            }
            n3 += this.getItem(i).getNumOfEpisodes();
            ++i;
        }
        if (this.getCount() > 0) {
            n = n2;
        }
        else {
            n = -1;
        }
        return n;
    }
    
    public int tryGetSeasonIndexBySeasonNumber(int n) {
        final int n2 = 0;
        for (int i = 0; i < this.getCount(); ++i) {
            if (n == this.getItem(i).getSeasonNumber()) {
                Log.v("SeasonsSpinnerAdapter", "Found season index: " + i);
                return i;
            }
        }
        if (this.getCount() > 0) {
            n = n2;
        }
        else {
            n = -1;
        }
        return n;
    }
    
    public void updateSeasonData(final List<SeasonDetails> list) {
        this.seasons.clear();
        this.seasons.addAll(list);
        this.notifyDataSetChanged();
    }
}
