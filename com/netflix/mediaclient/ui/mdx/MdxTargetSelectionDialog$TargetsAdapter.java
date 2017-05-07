// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.StringUtils;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import java.util.ArrayList;
import android.view.LayoutInflater;
import java.util.List;
import android.widget.BaseAdapter;

class MdxTargetSelectionDialog$TargetsAdapter extends BaseAdapter
{
    private List<String> data;
    private final LayoutInflater inflater;
    private int selectedIndex;
    private String subText;
    
    public MdxTargetSelectionDialog$TargetsAdapter(final LayoutInflater inflater) {
        this.inflater = inflater;
        this.data = new ArrayList<String>();
    }
    
    public int getCount() {
        return this.data.size();
    }
    
    public String getItem(final int n) {
        return this.data.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(int n, View inflate, final ViewGroup viewGroup) {
        int visibility = 0;
        MdxTargetSelectionDialog$ViewHolder tag;
        if (inflate == null) {
            inflate = this.inflater.inflate(2130903140, (ViewGroup)null);
            tag = new MdxTargetSelectionDialog$ViewHolder((TextView)inflate.findViewById(2131427647), (TextView)inflate.findViewById(2131427648));
            inflate.setTag((Object)tag);
        }
        else {
            tag = (MdxTargetSelectionDialog$ViewHolder)inflate.getTag();
        }
        tag.title.setText((CharSequence)this.getItem(n));
        if (n == this.selectedIndex) {
            n = 1;
        }
        else {
            n = 0;
        }
        final TextView subTitle = tag.subTitle;
        String subText;
        if (n != 0) {
            subText = this.subText;
        }
        else {
            subText = "";
        }
        subTitle.setText((CharSequence)subText);
        if (n != 0) {
            final TextView subTitle2 = tag.subTitle;
            if (StringUtils.isEmpty(this.subText)) {
                visibility = 8;
            }
            subTitle2.setVisibility(visibility);
        }
        else {
            tag.subTitle.setVisibility(8);
        }
        if (n != 0) {
            ViewUtils.setTextOpacityToSelected(tag.title);
            ViewUtils.setTextOpacityToSelected(tag.subTitle);
            return inflate;
        }
        ViewUtils.setTextOpacityToUnselected(tag.title);
        ViewUtils.setTextOpacityToUnselected(tag.subTitle);
        return inflate;
    }
    
    public void setData(final List<String> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }
    
    public void setSelected(final int selectedIndex, final String subText) {
        this.selectedIndex = selectedIndex;
        this.subText = subText;
        this.notifyDataSetChanged();
    }
}
