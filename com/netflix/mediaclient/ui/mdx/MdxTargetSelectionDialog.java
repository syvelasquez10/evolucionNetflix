// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.widget.CheckBox;
import java.util.ArrayList;
import android.widget.BaseAdapter;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;
import java.util.List;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.ListAdapter;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.widget.ListView;
import android.view.View;
import android.app.Activity;
import android.app.AlertDialog$Builder;

public class MdxTargetSelectionDialog
{
    private static int mRowColor;
    private static int mSelectedRowColor;
    
    public static class Builder extends AlertDialog$Builder
    {
        private final Activity activity;
        private final TargetsAdapter adapterWrapper;
        private final View content;
        private final ListView listView;
        private final TextView title;
        
        public Builder(final Activity activity) {
            super((Context)activity);
            this.activity = activity;
            MdxTargetSelectionDialog.mSelectedRowColor = activity.getResources().getColor(2131296326);
            MdxTargetSelectionDialog.mRowColor = activity.getResources().getColor(2131296325);
            final LayoutInflater layoutInflater = activity.getLayoutInflater();
            this.content = layoutInflater.inflate(2130903129, (ViewGroup)null);
            this.title = (TextView)this.content.findViewById(2131165471);
            this.listView = (ListView)this.content.findViewById(2131165472);
            this.adapterWrapper = new TargetsAdapter(layoutInflater);
            this.listView.setAdapter((ListAdapter)this.adapterWrapper);
            this.setCancelable(true);
        }
        
        public AlertDialog create() {
            final AlertDialog create = new AlertDialog$Builder((Context)this.activity).setView(this.content).create();
            create.setCanceledOnTouchOutside(true);
            return create;
        }
        
        public void setAdapterData(final List<String> data) {
            this.adapterWrapper.setData(data);
        }
        
        public void setOnItemClickListener(final AdapterView$OnItemClickListener adapterView$OnItemClickListener) {
            this.listView.setOnItemClickListener((AdapterView$OnItemClickListener)new AdapterView$OnItemClickListener() {
                public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
                    Builder.this.adapterWrapper.notifyDataSetChanged();
                    adapterView$OnItemClickListener.onItemClick((AdapterView)adapterView, view, n, n2);
                }
            });
        }
        
        public void setSelection(final int n, final String s) {
            this.adapterWrapper.setSelected(n, s);
        }
        
        public Builder setTitle(final int text) {
            this.title.setText(text);
            return this;
        }
    }
    
    private static class TargetsAdapter extends BaseAdapter
    {
        private List<String> data;
        private final LayoutInflater inflater;
        private int selectedIndex;
        private String subText;
        
        public TargetsAdapter(final LayoutInflater inflater) {
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
            final int n = 0;
            ViewHolder tag;
            if (inflate == null) {
                inflate = this.inflater.inflate(2130903112, (ViewGroup)null);
                tag = new ViewHolder((TextView)inflate.findViewById(2131165424), (TextView)inflate.findViewById(2131165425), (CheckBox)inflate.findViewById(2131165423));
                inflate.setTag((Object)tag);
            }
            else {
                tag = (ViewHolder)inflate.getTag();
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
                    visibility = 1;
                }
                else {
                    visibility = 0;
                }
                final TextView subTitle2 = tag.subTitle;
                if (visibility != 0) {
                    visibility = n;
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
    
    private static class ViewHolder
    {
        CheckBox checkBox;
        TextView subTitle;
        TextView title;
        
        ViewHolder(final TextView title, final TextView subTitle, final CheckBox checkBox) {
            this.title = title;
            this.subTitle = subTitle;
            this.checkBox = checkBox;
        }
    }
}
