// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import android.widget.AdapterView$OnItemClickListener;
import java.util.List;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.ListAdapter;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.content.DialogInterface$OnCancelListener;
import android.widget.ListView;
import android.view.View;
import android.app.Activity;
import android.support.v7.app.AlertDialog$Builder;

public class MdxTargetSelectionDialog$Builder extends AlertDialog$Builder
{
    private final Activity activity;
    private final MdxTargetSelectionDialog$TargetsAdapter adapterWrapper;
    private final View content;
    private final ListView listView;
    private DialogInterface$OnCancelListener onCancelListener;
    private final TextView title;
    
    public MdxTargetSelectionDialog$Builder(final Activity activity) {
        super((Context)activity);
        this.activity = activity;
        final LayoutInflater layoutInflater = activity.getLayoutInflater();
        this.content = layoutInflater.inflate(2130903209, (ViewGroup)null);
        this.title = (TextView)this.content.findViewById(2131755495);
        this.listView = (ListView)this.content.findViewById(2131755552);
        this.adapterWrapper = new MdxTargetSelectionDialog$TargetsAdapter(layoutInflater);
        this.listView.setAdapter((ListAdapter)this.adapterWrapper);
        this.setCancelable(true);
    }
    
    @Override
    public AlertDialog create() {
        final MdxTargetSelectionDialog mdxTargetSelectionDialog = new MdxTargetSelectionDialog((Context)this.activity, null);
        mdxTargetSelectionDialog.setView(this.content);
        mdxTargetSelectionDialog.setCanceledOnTouchOutside(true);
        if (this.onCancelListener != null) {
            mdxTargetSelectionDialog.setOnCancelListener(this.onCancelListener);
        }
        return mdxTargetSelectionDialog;
    }
    
    public void setAdapterData(final List<String> data) {
        this.adapterWrapper.setData(data);
    }
    
    @Override
    public MdxTargetSelectionDialog$Builder setOnCancelListener(final DialogInterface$OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        return this;
    }
    
    public void setOnItemClickListener(final AdapterView$OnItemClickListener adapterView$OnItemClickListener) {
        this.listView.setOnItemClickListener((AdapterView$OnItemClickListener)new MdxTargetSelectionDialog$Builder$1(this, adapterView$OnItemClickListener));
    }
    
    public void setSelection(final int n, final String s) {
        this.adapterWrapper.setSelected(n, s);
    }
    
    @Override
    public MdxTargetSelectionDialog$Builder setTitle(final int text) {
        this.title.setText(text);
        return this;
    }
}
