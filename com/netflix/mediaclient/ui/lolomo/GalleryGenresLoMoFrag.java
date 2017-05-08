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
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
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

public class GalleryGenresLoMoFrag extends LoLoMoFrag
{
    private static final String TAG = "GalleryGenresLoMoFrag";
    private RecyclerViewHeaderAdapter adapter;
    private boolean hasMoreData;
    private int numColumns;
    protected RecyclerView recyclerView;
    private int startIndex;
    
    public GalleryGenresLoMoFrag() {
        this.startIndex = 0;
    }
    
    public static GalleryGenresLoMoFrag create(final String s, final GenreList list) {
        final GalleryGenresLoMoFrag galleryGenresLoMoFrag = new GalleryGenresLoMoFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("genre_id", s);
        arguments.putBoolean("is_genre_list", !"lolomo".equals(s));
        if (list != null) {
            arguments.putParcelable("genre_parcel", (Parcelable)list);
        }
        galleryGenresLoMoFrag.setArguments(arguments);
        return galleryGenresLoMoFrag;
    }
    
    private void setNumColums() {
        this.numColumns = LoMoUtils.getGalleryLomoGenreNumColumns(this.getNetflixActivity());
    }
    
    private void setupRecyclerViewAdapter() {
        (this.adapter = new GalleryGenresLoMoFrag$ProgressiveAdapter(this, false, this.numColumns, new GalleryGenresLoMoFrag$2(this))).addHeaderView(ViewUtils.createActionBarDummyView(this.getNetflixActivity()));
        final int dimensionPixelSize = this.getActivity().getResources().getDimensionPixelSize(2131362197);
        this.recyclerView.setPadding(dimensionPixelSize, dimensionPixelSize, dimensionPixelSize, dimensionPixelSize);
        this.recyclerView.setAdapter(this.adapter);
    }
    
    private void setupRecyclerViewLayoutManager() {
        final GridLayoutManager layoutManager = new GridLayoutManager((Context)this.getActivity(), this.numColumns);
        layoutManager.setSpanSizeLookup(new GalleryGenresLoMoFrag$1(this));
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.addItemDecoration(new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131362195), this.numColumns));
    }
    
    @Override
    protected int getLayoutId() {
        if (BrowseExperience.showKidsExperience()) {
            return 2130903152;
        }
        return 2130903258;
    }
    
    @Override
    protected View getMainView() {
        return (View)this.recyclerView;
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        Log.v("GalleryGenresLoMoFrag", "onManagerReady");
        if (status.isError()) {
            Log.w("GalleryGenresLoMoFrag", "Manager status code not okay");
            return;
        }
        this.genreId = this.getArguments().getString("genre_id");
        this.startIndex = 0;
        ((GalleryGenresLoMoFrag$ProgressiveAdapter)this.adapter).fetchData();
    }
    
    @Override
    public void refresh() {
        this.showLoadingView();
        this.startIndex = 0;
        this.adapter.clearData();
        ((GalleryGenresLoMoFrag$ProgressiveAdapter)this.adapter).fetchData();
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
