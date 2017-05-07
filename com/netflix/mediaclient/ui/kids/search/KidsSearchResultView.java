// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.search;

import android.widget.TextView;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.SearchVideo;
import com.netflix.mediaclient.util.ViewUtils;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.ui.search.SearchResultView;

public class KidsSearchResultView extends SearchResultView
{
    public KidsSearchResultView(final Context context) {
        super(context);
        this.init();
    }
    
    public KidsSearchResultView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    private void init() {
        this.title.setTextColor(this.getResources().getColor(2131296306));
        ViewUtils.clearShadow(this.title);
    }
    
    @Override
    protected int getYearColorResId() {
        return 2131296308;
    }
    
    @Override
    protected void updateForVideo(final SearchVideo searchVideo, int visibility) {
        super.updateForVideo(searchVideo, visibility);
        final String certification = searchVideo.getCertification();
        final TextView rating = this.rating;
        if (StringUtils.isEmpty(certification)) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        rating.setVisibility(visibility);
        this.rating.setText((CharSequence)certification);
    }
}
