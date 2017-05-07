// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

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

public class MdxTargetSelectionDialog$Builder extends AlertDialog$Builder
{
    private final Activity activity;
    private final MdxTargetSelectionDialog$TargetsAdapter adapterWrapper;
    private final View content;
    private final ListView listView;
    private final TextView title;
    
    public MdxTargetSelectionDialog$Builder(final Activity activity) {
        super((Context)activity);
        this.activity = activity;
        MdxTargetSelectionDialog.mSelectedRowColor = activity.getResources().getColor(2131296376);
        MdxTargetSelectionDialog.mRowColor = activity.getResources().getColor(2131296375);
        final LayoutInflater layoutInflater = activity.getLayoutInflater();
        this.content = layoutInflater.inflate(2130903141, (ViewGroup)null);
        this.title = (TextView)this.content.findViewById(2131165512);
        this.listView = (ListView)this.content.findViewById(2131165513);
        this.adapterWrapper = new MdxTargetSelectionDialog$TargetsAdapter(layoutInflater);
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
        this.listView.setOnItemClickListener((AdapterView$OnItemClickListener)new MdxTargetSelectionDialog$Builder$1(this, adapterView$OnItemClickListener));
    }
    
    public void setSelection(final int n, final String s) {
        this.adapterWrapper.setSelected(n, s);
    }
    
    public MdxTargetSelectionDialog$Builder setTitle(final int text) {
        this.title.setText(text);
        return this;
    }
}
