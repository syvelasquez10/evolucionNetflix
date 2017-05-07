// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import java.util.Collection;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import com.netflix.mediaclient.Log;
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
    private int itemBackgroundResource;
    private final List<SeasonDetails> seasons;
    private SeasonsSpinnerAdapter$IViewCreator viewCreator;
    
    public SeasonsSpinnerAdapter(final NetflixActivity context, final SeasonsSpinnerAdapter$IViewCreator viewCreator) {
        this.seasons = new ArrayList<SeasonDetails>();
        this.context = (Context)context;
        this.viewCreator = viewCreator;
    }
    
    public int getCount() {
        return this.seasons.size();
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
    
    public int getSeasonNumberForPosition(final int n) {
        return this.getItem(n).getSeasonNumber();
    }
    
    public View getView(int itemBackgroundResource, final View view, final ViewGroup viewGroup) {
        TextView textView = (TextView)view;
        if (textView == null) {
            textView = (TextView)this.viewCreator.createItemView();
        }
        final SeasonDetails item = this.getItem(itemBackgroundResource);
        textView.setTag((Object)item.getSeasonNumber());
        textView.setText((CharSequence)item.getSeasonNumberTitle(this.context));
        if (viewGroup instanceof SeasonsSpinner) {
            itemBackgroundResource = 2131296356;
        }
        else {
            itemBackgroundResource = this.itemBackgroundResource;
        }
        textView.setBackgroundResource(itemBackgroundResource);
        return (View)textView;
    }
    
    public void setItemBackgroundColor(final int itemBackgroundResource) {
        this.itemBackgroundResource = itemBackgroundResource;
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
