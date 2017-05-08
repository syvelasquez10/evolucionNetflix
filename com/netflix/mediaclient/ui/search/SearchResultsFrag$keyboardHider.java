// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.os.Bundle;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.details.DPPrefetchABTestUtils;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideo;
import android.app.Activity;
import android.util.Pair;
import com.netflix.mediaclient.service.logging.search.utils.SearchLogUtils;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.widget.ScrollView;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.android.widget.LoggingScrollView$OnScrollStopListener;
import android.widget.AdapterView$OnItemClickListener;
import java.util.Locale;
import android.view.ViewTreeObserver$OnGlobalLayoutListener;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchSuggestion;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchPerson;
import android.view.View$OnClickListener;
import android.view.ViewGroup$LayoutParams;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.ui.common.PlayContextImp;
import android.content.Context;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.EditText;
import java.util.ArrayList;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import android.widget.ListAdapter;
import android.widget.GridView;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.trackable.SearchTrackable;
import com.netflix.mediaclient.ui.common.SearchSimilarItemsGridViewAdapter;
import com.netflix.mediaclient.android.widget.LoggingScrollView;
import com.netflix.mediaclient.servicemgr.interface_.search.ISearchResults;
import android.widget.ProgressBar;
import com.netflix.mediaclient.android.widget.FlowLayout;
import android.widget.TextView;
import com.netflix.mediaclient.android.widget.StaticGridView;
import java.util.Stack;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View;
import android.view.View$OnTouchListener;

class SearchResultsFrag$keyboardHider implements View$OnTouchListener
{
    final /* synthetic */ SearchResultsFrag this$0;
    
    SearchResultsFrag$keyboardHider(final SearchResultsFrag this$0) {
        this.this$0 = this$0;
    }
    
    @SuppressLint({ "ClickableViewAccessibility" })
    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        this.this$0.hideKeyboard();
        return false;
    }
}
