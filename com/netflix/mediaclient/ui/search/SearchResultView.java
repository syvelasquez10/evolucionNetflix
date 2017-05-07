// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.app.Activity;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.servicemgr.SearchVideo;
import com.netflix.mediaclient.servicemgr.SearchSuggestion;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.SearchPerson;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.text.style.ForegroundColorSpan;
import android.text.style.AbsoluteSizeSpan;
import android.text.SpannableString;
import com.netflix.mediaclient.util.StringUtils;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.android.widget.VideoDetailsClickListener;
import android.view.View;
import android.widget.TextView;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import android.widget.FrameLayout;

public class SearchResultView extends FrameLayout implements PlayContextProvider
{
    public static final String PEOPLE_TAG = "People";
    public static final String SUGGESTION_TAG = "Suggestion";
    public static final String VIDEO_TAG = "Video";
    private AdvancedImageView img;
    private PlayContext playContext;
    protected TextView rating;
    protected TextView title;
    private View verticalDivider;
    private VideoDetailsClickListener videoClickListener;
    
    public SearchResultView(final Context context) {
        super(context);
        this.init();
    }
    
    public SearchResultView(final Context context, final AttributeSet set) {
        super(context, set);
        this.init();
    }
    
    private CharSequence getFormattedYearSpannable(final String s, final String s2) {
        if (StringUtils.isEmpty(s2)) {
            return s;
        }
        final SpannableString spannableString = new SpannableString((CharSequence)String.format("%s  (%s)", s, s2));
        final int length = spannableString.length();
        final int n = length - s2.length() - 4;
        spannableString.setSpan((Object)new AbsoluteSizeSpan(this.getResources().getDimensionPixelSize(2131361837)), n, length, 0);
        spannableString.setSpan((Object)new ForegroundColorSpan(this.getResources().getColor(this.getYearColorResId())), n, length, 0);
        return (CharSequence)spannableString;
    }
    
    private void init() {
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        netflixActivity.getLayoutInflater().inflate(2130903159, (ViewGroup)this);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        this.setForeground(this.getResources().getDrawable(2130837855));
        (this.img = (AdvancedImageView)this.findViewById(2131165585)).setPressedStateHandlerEnabled(false);
        this.title = (TextView)this.findViewById(2131165586);
        this.verticalDivider = this.findViewById(2131165588);
        (this.rating = (TextView)this.findViewById(2131165587)).setVisibility(8);
        this.videoClickListener = new VideoDetailsClickListener(netflixActivity, this);
    }
    
    private void setViewHeights(final ImgSizeType imgSizeType) {
        final int dimensionPixelOffset = this.getResources().getDimensionPixelOffset(imgSizeType.heightDimenId);
        this.img.getLayoutParams().height = dimensionPixelOffset;
        this.verticalDivider.getLayoutParams().height = dimensionPixelOffset;
    }
    
    private void update(final SearchPerson searchPerson) {
        this.setViewHeights(ImgSizeType.SQUARE);
        final String name = searchPerson.getName();
        this.setContentDescription((CharSequence)name);
        this.setTag((Object)"People");
        this.title.setText((CharSequence)name);
        this.img.setVisibility(0);
        final String imgUrl = searchPerson.getImgUrl();
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, imgUrl, IClientLogging.AssetType.heroImage, name, false, false);
        if (StringUtils.isEmpty(imgUrl)) {
            this.img.setImageResource(2130837876);
        }
        this.videoClickListener.remove((View)this);
        this.setOnClickListener((View$OnClickListener)new PersonClickListener(searchPerson.getId(), searchPerson.getName()));
    }
    
    private void update(final SearchSuggestion searchSuggestion) {
        this.setViewHeights(ImgSizeType.SQUARE);
        final String title = searchSuggestion.getTitle();
        this.setContentDescription((CharSequence)title);
        this.setTag((Object)"Suggestion");
        this.title.setText((CharSequence)title);
        this.img.setVisibility(8);
        this.videoClickListener.remove((View)this);
        this.setOnClickListener((View$OnClickListener)new SuggestionClickListener(searchSuggestion.getTitle(), searchSuggestion.getTitle()));
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    protected int getYearColorResId() {
        return 2131296315;
    }
    
    public void update(final Object o, final PlayContext playContext) {
        this.playContext = playContext;
        if (o instanceof SearchVideo) {
            this.updateForVideo((SearchVideo)o, playContext.getVideoPos());
            return;
        }
        if (o instanceof SearchPerson) {
            this.update((SearchPerson)o);
            return;
        }
        if (o instanceof SearchSuggestion) {
            this.update((SearchSuggestion)o);
            return;
        }
        throw new IllegalStateException("Unknown search result type");
    }
    
    protected void updateForVideo(final SearchVideo searchVideo, final int n) {
        this.setViewHeights(ImgSizeType.BOXART);
        this.setContentDescription((CharSequence)searchVideo.getTitle());
        this.setTag((Object)"Video");
        this.title.setText(this.getFormattedYearSpannable(searchVideo.getTitle(), String.valueOf(searchVideo.getYear())));
        this.img.setVisibility(0);
        NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, searchVideo.getBoxshotURL(), IClientLogging.AssetType.boxArt, searchVideo.getTitle(), true, true);
        this.videoClickListener.update((View)this, searchVideo);
    }
    
    private enum ImgSizeType
    {
        BOXART(2131361862), 
        SQUARE(2131361861);
        
        private final int heightDimenId;
        
        private ImgSizeType(final int heightDimenId) {
            this.heightDimenId = heightDimenId;
        }
    }
    
    private class PersonClickListener implements View$OnClickListener
    {
        private final String id;
        private final String name;
        
        public PersonClickListener(final String id, final String name) {
            this.id = id;
            this.name = name;
        }
        
        public void onClick(final View view) {
            SearchQueryDetailsActivity.show((Activity)SearchResultView.this.getContext(), SearchQueryDetailsActivity.SearchQueryDetailsType.PERSON, this.id, this.name);
        }
    }
    
    private class SuggestionClickListener implements View$OnClickListener
    {
        private final String id;
        private final String title;
        
        public SuggestionClickListener(final String id, final String title) {
            this.id = id;
            this.title = title;
        }
        
        public void onClick(final View view) {
            SearchQueryDetailsActivity.show((Activity)SearchResultView.this.getContext(), SearchQueryDetailsActivity.SearchQueryDetailsType.SEARCH_SUGGESTION, this.id, this.title);
        }
    }
}
