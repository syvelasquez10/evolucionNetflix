// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kids.search;

import android.widget.TextView;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.search.SearchVideo;
import com.netflix.mediaclient.util.ViewUtils;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.ui.search.SearchResultView;

public class KidsSearchResultView extends SearchResultView
{
    public KidsSearchResultView(final Context context, final int n) {
        super(context, null);
        this.init();
    }
    
    public KidsSearchResultView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    private void init() {
        if (this.title != null) {
            this.title.setTextColor(this.getResources().getColor(2131296306));
            ViewUtils.clearShadow(this.title);
        }
    }
    
    @Override
    protected int getYearColorResId() {
        return 2131296307;
    }
    
    @Override
    protected void updateForVideo(final SearchVideo searchVideo, int visibility, final String s) {
        super.updateForVideo(searchVideo, visibility, s);
        final String certification = searchVideo.getCertification();
        if (this.rating != null) {
            final TextView rating = this.rating;
            if (StringUtils.isEmpty(certification)) {
                visibility = 8;
            }
            else {
                visibility = 0;
            }
            rating.setVisibility(visibility);
        }
        if (this.rating != null) {
            this.rating.setText((CharSequence)certification);
        }
    }
}
