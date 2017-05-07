// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideo;
import android.app.Activity;
import android.util.Pair;
import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.widget.ScrollView;
import com.netflix.mediaclient.android.widget.LoggingScrollView$OnScrollStopListener;
import android.annotation.SuppressLint;
import android.view.View$OnTouchListener;
import android.widget.AdapterView$OnItemClickListener;
import java.util.Locale;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchSuggestion;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchPerson;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.EditText;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.ListAdapter;
import android.widget.GridView;
import com.netflix.mediaclient.servicemgr.interface_.trackable.SearchTrackable;
import com.netflix.mediaclient.ui.common.SearchSimilarItemsGridViewAdapter;
import com.netflix.mediaclient.android.widget.LoggingScrollView;
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;
import android.widget.ProgressBar;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.FlowLayout;
import android.widget.TextView;
import java.util.Stack;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.widget.StaticGridView;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;

class SearchResultsFrag$3 implements ViewTreeObserver$OnGlobalLayoutListener
{
    final /* synthetic */ SearchResultsFrag this$0;
    final /* synthetic */ StaticGridView val$gridView;
    
    SearchResultsFrag$3(final SearchResultsFrag this$0, final StaticGridView val$gridView) {
        this.this$0 = this$0;
        this.val$gridView = val$gridView;
    }
    
    public void onGlobalLayout() {
        final int n = this.val$gridView.getWidth() - this.val$gridView.getPaddingLeft() - this.val$gridView.getPaddingRight();
        Log.v("SearchResultsFrag", "View dimens: " + n + ", " + this.val$gridView.getHeight());
        final int numVideoGridCols = SearchUtils.getNumVideoGridCols((Context)this.this$0.getActivity());
        if (numVideoGridCols > 0) {
            this.this$0.imgHeightVideo = (int)(n / numVideoGridCols * SearchUtils.getVideoImageAspectRatio() + 0.5);
            Log.v("SearchResultsFrag", "imgHeight: " + this.this$0.imgHeightVideo);
        }
        final int numPeopleGridCols = SearchUtils.getNumPeopleGridCols((Context)this.this$0.getActivity());
        if (numPeopleGridCols > 0) {
            this.this$0.imgHeightPeople = (int)(n / numPeopleGridCols * SearchUtils.getPeopleImageAspectRatio() + 0.5);
            SearchResultsFrag.access$316(this.this$0, this.this$0.getActivity().getResources().getDimension(2131296385));
            Log.v("SearchResultsFrag", "imgHeightPeople: " + this.this$0.imgHeightPeople);
        }
        this.this$0.fireImpressionEvents();
        ViewUtils.removeGlobalLayoutListener((View)this.val$gridView, (ViewTreeObserver$OnGlobalLayoutListener)this);
    }
}
