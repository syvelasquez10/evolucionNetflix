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
import com.netflix.mediaclient.servicemgr.SeasonDetails;
import java.util.List;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.BaseAdapter;

public class SeasonsSpinnerAdapter extends BaseAdapter
{
    private static final String TAG = "SeasonsSpinnerAdapter";
    private final Context context;
    private final LayoutInflater inflater;
    private int itemBgDrawableId;
    private final List<SeasonDetails> seasons;
    
    public SeasonsSpinnerAdapter(final NetflixActivity context) {
        this.seasons = new ArrayList<SeasonDetails>();
        this.context = (Context)context;
        this.inflater = (LayoutInflater)this.context.getSystemService("layout_inflater");
        int itemBgDrawableId;
        if (context.isForKids()) {
            itemBgDrawableId = 2130837744;
        }
        else {
            itemBgDrawableId = 2130837854;
        }
        this.itemBgDrawableId = itemBgDrawableId;
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
    
    public int getSeasonIndexBySeasonNumber(final int n) {
        for (int i = 0; i < this.getCount(); ++i) {
            if (n == this.getItem(i).getSeasonNumber()) {
                Log.v("SeasonsSpinnerAdapter", "Found season index: " + i);
                return i;
            }
        }
        return -1;
    }
    
    public int getSeasonNumberForPosition(final int n) {
        return this.getItem(n).getSeasonNumber();
    }
    
    public View getView(int itemBgDrawableId, final View view, final ViewGroup viewGroup) {
        TextView textView;
        if ((textView = (TextView)view) == null) {
            textView = (TextView)this.inflater.inflate(2130903163, (ViewGroup)null, false);
        }
        final SeasonDetails item = this.getItem(itemBgDrawableId);
        textView.setTag((Object)item.getSeasonNumber());
        textView.setText((CharSequence)item.getSeasonNumberTitle(this.context));
        if (viewGroup instanceof SeasonsSpinner) {
            itemBgDrawableId = 2131296304;
        }
        else {
            itemBgDrawableId = this.itemBgDrawableId;
        }
        textView.setBackgroundResource(itemBgDrawableId);
        return (View)textView;
    }
    
    public void updateSeasonData(final List<SeasonDetails> list) {
        this.seasons.clear();
        this.seasons.addAll(list);
        this.notifyDataSetChanged();
    }
}
