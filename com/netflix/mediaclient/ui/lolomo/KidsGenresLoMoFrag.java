// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lolomo;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.view.View;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationEdgePadding;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import android.os.Parcelable;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.interface_.genre.GenreList;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;

public class KidsGenresLoMoFrag extends LoLoMoFrag
{
    private static final String TAG = "KidsGenresLoMoFrag";
    private RecyclerViewHeaderAdapter adapter;
    private int numColumns;
    protected RecyclerView recyclerView;
    
    public static KidsGenresLoMoFrag create(final String s, final GenreList list) {
        final KidsGenresLoMoFrag kidsGenresLoMoFrag = new KidsGenresLoMoFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("genre_id", s);
        arguments.putBoolean("is_genre_list", !"lolomo".equals(s));
        if (list != null) {
            arguments.putParcelable("genre_parcel", (Parcelable)list);
        }
        kidsGenresLoMoFrag.setArguments(arguments);
        return kidsGenresLoMoFrag;
    }
    
    private void setNumColums() {
        this.numColumns = LoMoUtils.getKidsLomoGenreNumColumns(this.getActivity());
    }
    
    private void setupRecyclerViewAdapter() {
        (this.adapter = new KidsGenresLoMoFrag$ProgressiveAdapter(this, false, this.numColumns, new KidsGenresLoMoFrag$2(this))).addHeaderView(ViewUtils.createActionBarDummyView(this.getNetflixActivity()));
        this.recyclerView.setAdapter(this.adapter);
    }
    
    private void setupRecyclerViewLayoutManager() {
        final GridLayoutManager layoutManager = new GridLayoutManager((Context)this.getActivity(), this.numColumns);
        layoutManager.setSpanSizeLookup(new KidsGenresLoMoFrag$1(this));
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.addItemDecoration(new ItemDecorationEdgePadding(this.getActivity().getResources().getDimensionPixelOffset(2131296535), this.numColumns, 2));
    }
    
    @Override
    protected int getLayoutId() {
        if (BrowseExperience.isKubrickKids()) {
            return 2130903128;
        }
        return 2130903214;
    }
    
    @Override
    protected View getMainView() {
        return (View)this.recyclerView;
    }
    
    @Override
    public void onManagerReady(final ServiceManager manager, final Status status) {
        Log.v("KidsGenresLoMoFrag", "onManagerReady");
        this.manager = manager;
        if (status.isError()) {
            Log.w("KidsGenresLoMoFrag", "Manager status code not okay");
            return;
        }
        this.genreId = this.getArguments().getString("genre_id");
        ((KidsGenresLoMoFrag$ProgressiveAdapter)this.adapter).fetchData();
    }
    
    @Override
    public void refresh() {
        this.showLoadingView();
        ((KidsGenresLoMoFrag$ProgressiveAdapter)this.adapter).fetchData();
    }
    
    @Override
    protected void setupFocushandler() {
    }
    
    @Override
    protected void setupMainView(final View view) {
        this.recyclerView = (RecyclerView)view.findViewById(16908298);
        this.setNumColums();
        this.setupRecyclerViewLayoutManager();
        this.setupRecyclerViewAdapter();
    }
}
