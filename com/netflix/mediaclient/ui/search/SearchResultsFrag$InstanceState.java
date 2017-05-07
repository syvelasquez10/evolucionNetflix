// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideo;
import android.app.Activity;
import android.util.Pair;
import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.widget.ScrollView;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.LoggingScrollView$OnScrollStopListener;
import android.view.View$OnTouchListener;
import android.widget.AdapterView$OnItemClickListener;
import java.util.Locale;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.servicemgr.model.search.SearchSuggestion;
import com.netflix.mediaclient.servicemgr.model.search.SearchPerson;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.EditText;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.ListAdapter;
import android.widget.GridView;
import com.netflix.mediaclient.Log;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.trackable.SearchTrackable;
import com.netflix.mediaclient.ui.common.SearchSimilarItemsGridViewAdapter;
import com.netflix.mediaclient.android.widget.LoggingScrollView;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import android.widget.ProgressBar;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.widget.TextView;
import java.util.Stack;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.android.widget.FlowLayout;
import android.os.Handler;
import com.netflix.mediaclient.android.widget.StaticGridView;
import android.os.Parcelable;
import java.util.Collection;
import java.util.ArrayList;
import android.os.Bundle;

class SearchResultsFrag$InstanceState
{
    final /* synthetic */ SearchResultsFrag this$0;
    
    SearchResultsFrag$InstanceState(final SearchResultsFrag this$0) {
        this.this$0 = this$0;
    }
    
    private void restoreClickHistory(final Bundle bundle) {
        if (bundle != null && bundle.containsKey("instance_state__click_history")) {
            final Parcelable[] parcelableArray = bundle.getParcelableArray("instance_state__click_history");
            if (parcelableArray != null && parcelableArray.length != 0) {
                final ArrayList list = new ArrayList<SearchItemClick>(parcelableArray.length);
                for (int length = parcelableArray.length, i = 0; i < length; ++i) {
                    list.add((SearchItemClick)parcelableArray[i]);
                }
                if (list.size() > 0 && this.this$0.clickPresseHistory != null) {
                    this.this$0.clickPresseHistory.addAll(list);
                }
            }
        }
    }
    
    private void restoreGridViewPositions(final Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.restoreGridViewPositions(bundle, this.this$0.gridViewSuggestions, "instance_state_suggestions_selected_pos");
        this.restoreGridViewPositions(bundle, this.this$0.gridViewPeople, "instance_state_people_selected_pos");
    }
    
    private void restoreGridViewPositions(final Bundle bundle, final StaticGridView staticGridView, final String s) {
        if (bundle != null && staticGridView != null && bundle.containsKey(s)) {
            final int int1 = bundle.getInt(s, -1);
            if (int1 != -1) {
                if (staticGridView == this.this$0.gridViewPeople) {
                    this.this$0.peopleSelectedPos = int1;
                }
                else if (staticGridView == this.this$0.gridViewSuggestions) {
                    this.this$0.suggestionsSelectedPos = int1;
                }
                new Handler().postDelayed((Runnable)new SearchResultsFrag$InstanceState$2(this, staticGridView, int1), 300L);
            }
        }
    }
    
    private void restoreLayoutPositions(final Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.restoreLayoutPositions(bundle, this.this$0.layoutSuggestions, "instance_state_suggestions_selected_pos");
        this.restoreLayoutPositions(bundle, this.this$0.layoutPeople, "instance_state_people_selected_pos");
    }
    
    private void restoreLayoutPositions(final Bundle bundle, final FlowLayout flowLayout, final String s) {
        if (bundle != null && flowLayout != null && bundle.containsKey(s)) {
            final int int1 = bundle.getInt(s, -1);
            if (int1 != -1) {
                if (flowLayout == this.this$0.layoutPeople) {
                    this.this$0.peopleSelectedPos = int1;
                }
                else if (flowLayout == this.this$0.layoutSuggestions) {
                    this.this$0.suggestionsSelectedPos = int1;
                }
                new Handler().postDelayed((Runnable)new SearchResultsFrag$InstanceState$1(this, flowLayout, int1), 300L);
            }
        }
    }
    
    private void restoreQuery(final Bundle bundle) {
        if (bundle != null && bundle.containsKey("instance_state_query")) {
            this.this$0.query = bundle.getString("instance_state_query");
        }
    }
    
    private void saveClickHistoryState(final Bundle bundle) {
        if (this.this$0.clickPresseHistory.size() > 0) {
            final SearchItemClick[] array = (SearchItemClick[])this.this$0.clickPresseHistory.toArray(new SearchItemClick[this.this$0.clickPresseHistory.size()]);
            if (array != null && array.length > 0) {
                bundle.putParcelableArray("instance_state__click_history", (Parcelable[])array);
            }
        }
    }
    
    private void saveGridViewState(final Bundle bundle) {
        if (this.this$0.peopleSelectedPos != -1) {
            bundle.putInt("instance_state_people_selected_pos", this.this$0.peopleSelectedPos);
        }
        if (this.this$0.suggestionsSelectedPos != -1) {
            bundle.putInt("instance_state_suggestions_selected_pos", this.this$0.suggestionsSelectedPos);
        }
    }
    
    private void saveQueryState(final Bundle bundle) {
        if (StringUtils.isNotEmpty(this.this$0.query)) {
            bundle.putString("instance_state_query", this.this$0.query);
        }
    }
    
    void restore(final Bundle bundle) {
        this.restoreGridViewPositions(bundle);
        this.restoreLayoutPositions(bundle);
        this.restoreClickHistory(bundle);
        this.restoreQuery(bundle);
    }
    
    void save(final Bundle bundle) {
        this.saveGridViewState(bundle);
        this.saveClickHistoryState(bundle);
        this.saveQueryState(bundle);
    }
}
