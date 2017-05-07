// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import com.netflix.mediaclient.servicemgr.model.Video;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideo;
import android.graphics.Typeface;
import com.netflix.mediaclient.servicemgr.model.search.SearchSuggestion;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.model.search.SearchPerson;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.text.style.ForegroundColorSpan;
import android.text.style.AbsoluteSizeSpan;
import android.text.SpannableString;
import com.netflix.mediaclient.util.StringUtils;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.widget.TextView;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.widget.FrameLayout;
import android.app.Activity;
import android.view.View;
import android.view.View$OnClickListener;

class SearchResultView$PersonClickListener implements View$OnClickListener
{
    private final String id;
    private final String name;
    private final String query;
    final /* synthetic */ SearchResultView this$0;
    
    public SearchResultView$PersonClickListener(final SearchResultView this$0, final String id, final String name, final String query) {
        this.this$0 = this$0;
        this.id = id;
        this.name = name;
        this.query = query;
    }
    
    public void onClick(final View view) {
        SearchQueryDetailsActivity.show((Activity)this.this$0.getContext(), SearchQueryDetailsActivity$SearchQueryDetailsType.PERSON, this.id, this.name, this.query, this.this$0.searchReferenceId, this.this$0.trackModalview);
    }
}
