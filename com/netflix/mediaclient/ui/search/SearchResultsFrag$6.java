// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.os.Bundle;
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
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.content.Context;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.EditText;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.ListAdapter;
import android.widget.GridView;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.model.trackable.SearchTrackable;
import com.netflix.mediaclient.ui.common.SearchSimilarItemsGridViewAdapter;
import com.netflix.mediaclient.android.widget.LoggingScrollView;
import com.netflix.mediaclient.servicemgr.model.search.ISearchResults;
import android.widget.ProgressBar;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.android.widget.FlowLayout;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.StaticGridView;
import java.util.Stack;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.view.View;
import android.view.View$OnClickListener;

class SearchResultsFrag$6 implements View$OnClickListener
{
    final /* synthetic */ SearchResultsFrag this$0;
    final /* synthetic */ int val$position;
    
    SearchResultsFrag$6(final SearchResultsFrag this$0, final int val$position) {
        this.this$0 = this$0;
        this.val$position = val$position;
    }
    
    public void onClick(final View view) {
        if (view != null && view instanceof SearchResultView) {
            this.this$0.onSuggestionClick((SearchResultView)view, this.val$position, 0L);
        }
    }
}
