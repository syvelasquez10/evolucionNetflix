// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import java.util.Collection;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.SeasonDetails;
import java.util.List;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.BaseAdapter;

public class SeasonsSpinnerAdapter extends BaseAdapter
{
    private final Context context;
    private final LayoutInflater inflater;
    private final List<SeasonDetails> seasons;
    
    public SeasonsSpinnerAdapter(final Context context) {
        this.seasons = new ArrayList<SeasonDetails>();
        this.context = context;
        this.inflater = (LayoutInflater)context.getSystemService("layout_inflater");
    }
    
    public int getCount() {
        return this.seasons.size();
    }
    
    public SeasonDetails getItem(final int n) {
        return this.seasons.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public int getSeasonIndexBySeasonNumber(final int n) {
        for (int i = 0; i < this.getCount(); ++i) {
            if (n == this.getItem(i).getSeasonNumber()) {
                return i;
            }
        }
        return -1;
    }
    
    public View getView(int backgroundResource, final View view, final ViewGroup viewGroup) {
        TextView textView;
        if ((textView = (TextView)view) == null) {
            textView = (TextView)this.inflater.inflate(2130903115, (ViewGroup)null, false);
        }
        textView.setText((CharSequence)this.getItem(backgroundResource).getSeasonNumberTitle(this.context));
        if (viewGroup instanceof SeasonsSpinner) {
            backgroundResource = 2131165206;
        }
        else {
            backgroundResource = 2130837690;
        }
        textView.setBackgroundResource(backgroundResource);
        return (View)textView;
    }
    
    public void updateSeasonData(final List<SeasonDetails> list) {
        this.seasons.clear();
        this.seasons.addAll(list);
        this.notifyDataSetChanged();
    }
}
