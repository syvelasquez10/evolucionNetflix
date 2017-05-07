// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import java.util.List;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.ListAdapter;
import android.view.ViewGroup;
import android.content.Context;
import android.widget.TextView;
import android.widget.ListView;
import android.app.Activity;
import android.app.AlertDialog$Builder;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView$OnItemClickListener;

class MdxTargetSelectionDialog$Builder$1 implements AdapterView$OnItemClickListener
{
    final /* synthetic */ MdxTargetSelectionDialog$Builder this$0;
    final /* synthetic */ AdapterView$OnItemClickListener val$listener;
    
    MdxTargetSelectionDialog$Builder$1(final MdxTargetSelectionDialog$Builder this$0, final AdapterView$OnItemClickListener val$listener) {
        this.this$0 = this$0;
        this.val$listener = val$listener;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        this.this$0.adapterWrapper.notifyDataSetChanged();
        this.val$listener.onItemClick((AdapterView)adapterView, view, n, n2);
    }
}
