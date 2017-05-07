// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.widget.CheckBox;
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
    
    public View getView(int visibility, View inflate, final ViewGroup viewGroup) {
        final int n = 1;
        final int n2 = 0;
        MdxTargetSelectionDialog$ViewHolder tag;
        if (inflate == null) {
            inflate = this.inflater.inflate(2130903124, (ViewGroup)null);
            tag = new MdxTargetSelectionDialog$ViewHolder((TextView)inflate.findViewById(2131165464), (TextView)inflate.findViewById(2131165465), (CheckBox)inflate.findViewById(2131165463));
            inflate.setTag((Object)tag);
        }
        else {
            tag = (MdxTargetSelectionDialog$ViewHolder)inflate.getTag();
        }
        tag.title.setText((CharSequence)this.getItem(visibility));
        final boolean checked = visibility == this.selectedIndex;
        final TextView subTitle = tag.subTitle;
        String subText;
        if (checked) {
            subText = this.subText;
        }
        else {
            subText = "";
        }
        subTitle.setText((CharSequence)subText);
        if (checked) {
            if (this.subText != null && !"".equals(this.subText.trim())) {
                visibility = n;
            }
            else {
                visibility = 0;
            }
            final TextView subTitle2 = tag.subTitle;
            if (visibility != 0) {
                visibility = n2;
            }
            else {
                visibility = 8;
            }
            subTitle2.setVisibility(visibility);
        }
        else {
            tag.subTitle.setVisibility(8);
        }
        tag.checkBox.setChecked(checked);
        if (checked) {
            inflate.setBackgroundColor(MdxTargetSelectionDialog.mSelectedRowColor);
            return inflate;
        }
        inflate.setBackgroundColor(MdxTargetSelectionDialog.mRowColor);
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
