// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.experience;

import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.View;
import android.os.Bundle;
import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.fragment.NetflixFrag;

public class ThemeActivity$ThemeFragment extends NetflixFrag
{
    LayoutInflater inflater;
    RecyclerView recyclerView;
    
    public boolean isLoadingData() {
        return false;
    }
    
    public View onCreateView(final LayoutInflater inflater, final ViewGroup viewGroup, final Bundle bundle) {
        this.inflater = inflater;
        final View inflate = inflater.inflate(2130903282, viewGroup, false);
        (this.recyclerView = (RecyclerView)inflate.findViewById(16908298)).setBackgroundColor(-65536);
        new LoadingAndErrorWrapper(inflate, null).hide(false);
        return inflate;
    }
    
    public void onViewCreated(final View view, final Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.recyclerView.setLayoutManager(new LinearLayoutManager((Context)this.getActivity()));
        this.recyclerView.setAdapter(new ThemeActivity$ThemeFragment$1(this));
    }
}
