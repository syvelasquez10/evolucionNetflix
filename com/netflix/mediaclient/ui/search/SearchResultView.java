// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.search;

import android.text.Spannable;
import android.util.TypedValue;
import android.graphics.Typeface;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchVideo;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchSuggestion;
import android.view.View$OnClickListener;
import android.view.View;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.servicemgr.interface_.search.SearchPerson;
import android.annotation.SuppressLint;
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

public class SearchResultView extends FrameLayout implements PlayContextProvider
{
    public static final String PEOPLE_TAG = "People";
    public static final String SUGGESTION_TAG = "Suggestion";
    public static final String VIDEO_TAG = "Video";
    private String displayName;
    private boolean ignoreClicks;
    private AdvancedImageView img;
    private PlayContext playContext;
    private String playableId;
    protected TextView rating;
    private int resId;
    private String searchReferenceId;
    protected TextView title;
    private IClientLogging$ModalView trackModalview;
    private VideoDetailsClickListener videoClickListener;
    
    public SearchResultView(final Context context, final int resId) {
        super(context);
        this.resId = 2130903217;
        this.ignoreClicks = false;
        this.resId = resId;
        this.init();
    }
    
    public SearchResultView(final Context context, final AttributeSet set) {
        super(context, set);
        this.resId = 2130903217;
        this.ignoreClicks = false;
        this.init();
    }
    
    private void findViews() {
        this.img = (AdvancedImageView)this.findViewById(2131624516);
        this.title = (TextView)this.findViewById(2131624517);
    }
    
    private CharSequence getFormattedYearSpannable(final String s, final String s2) {
        if (StringUtils.isEmpty(s2)) {
            return s;
        }
        final SpannableString spannableString = new SpannableString((CharSequence)String.format("%s  (%s)", s, s2));
        final int length = spannableString.length();
        final int n = length - s2.length() - 4;
        spannableString.setSpan((Object)new AbsoluteSizeSpan(this.getResources().getDimensionPixelSize(2131296333)), n, length, 0);
        spannableString.setSpan((Object)new ForegroundColorSpan(this.getResources().getColor(2131558560)), n, length, 0);
        return (CharSequence)spannableString;
    }
    
    private void init() {
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        netflixActivity.getLayoutInflater().inflate(this.resId, (ViewGroup)this);
        this.playContext = PlayContext.EMPTY_CONTEXT;
        this.setForeground(this.getResources().getDrawable(2130837940));
        this.findViews();
        this.setupViews();
        this.videoClickListener = new VideoDetailsClickListener(netflixActivity, this);
    }
    
    @SuppressLint({ "DefaultLocale" })
    private void setTitleTextWithSubtextHighlighting(final String text, final String s) {
        if (this.title == null || text == null || s == null) {
            return;
        }
        final int index = text.toLowerCase().indexOf(s.toLowerCase());
        if (index < 0) {
            this.title.setText((CharSequence)text);
            return;
        }
        int n;
        if ((n = s.length() + index) > text.length() - 1) {
            n = text.length() - 1;
        }
        final SpannableString text2 = new SpannableString((CharSequence)text);
        ((Spannable)text2).setSpan((Object)new ForegroundColorSpan(this.getHighLightColor()), index, n, 33);
        this.title.setText((CharSequence)text2);
    }
    
    private void setupViews() {
        if (this.img != null) {
            this.img.setPressedStateHandlerEnabled(false);
        }
        if (this.rating != null) {
            this.rating.setVisibility(8);
        }
    }
    
    private void update(final SearchPerson searchPerson, final String s) {
        final String name = searchPerson.getName();
        this.setContentDescription((CharSequence)name);
        this.setTag((Object)"People");
        this.trackModalview = IClientLogging$ModalView.peopleTitleResults;
        if (name != null) {
            this.setTitleTextWithSubtextHighlighting(name, s);
        }
        if (this.img != null) {
            this.img.setVisibility(0);
            final String imgUrl = searchPerson.getImgUrl();
            if (StringUtils.isNotEmpty(imgUrl)) {
                NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, imgUrl, IClientLogging$AssetType.heroImage, name, BrowseExperience.getImageLoaderConfigNoPlaceholder(), false);
            }
            else {
                this.img.setImageResource(2130837583);
            }
        }
        if (!this.ignoreClicks) {
            this.videoClickListener.remove((View)this);
            this.setOnClickListener((View$OnClickListener)new SearchResultView$PersonClickListener(this, searchPerson.getId(), searchPerson.getName(), s));
        }
    }
    
    private void update(final SearchSuggestion searchSuggestion, final String s) {
        final String title = searchSuggestion.getTitle();
        this.setContentDescription((CharSequence)title);
        this.setTag((Object)"Suggestion");
        this.trackModalview = IClientLogging$ModalView.suggestionTitleResults;
        if (title != null) {
            this.setTitleTextWithSubtextHighlighting(title, s);
        }
        if (this.img != null) {
            this.img.setVisibility(8);
        }
        if (!this.ignoreClicks) {
            this.videoClickListener.remove((View)this);
            this.setOnClickListener((View$OnClickListener)new SearchResultView$SuggestionClickListener(this, searchSuggestion.getTitle(), searchSuggestion.getTitle(), s));
        }
    }
    
    private void updateForVideo(final SearchVideo searchVideo, final int n, String s) {
        this.setContentDescription((CharSequence)searchVideo.getTitle());
        this.setTag((Object)"Video");
        this.trackModalview = IClientLogging$ModalView.titleResults;
        if (this.title != null) {
            this.title.setVisibility(8);
        }
        if (this.img != null && searchVideo != null) {
            this.img.setVisibility(0);
            if (SearchUtils.shouldShowVerticalBoxArt()) {
                s = searchVideo.getBoxshotUrl();
            }
            else {
                s = searchVideo.getHorzDispUrl();
            }
            NetflixActivity.getImageLoader(this.getContext()).showImg(this.img, s, IClientLogging$AssetType.boxArt, searchVideo.getTitle(), BrowseExperience.getImageLoaderConfig(), true);
        }
        this.videoClickListener.update((View)this, searchVideo, this.img.getPressedStateHandler());
    }
    
    public void clearTitleTextHighlighting() {
        if (this.title != null && this.displayName != null) {
            this.title.setText((CharSequence)this.displayName);
            this.title.setTypeface(Typeface.DEFAULT);
        }
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    int getHighLightColor() {
        final TypedValue typedValue = new TypedValue();
        this.title.getContext().getTheme().resolveAttribute(2130771982, typedValue, true);
        return typedValue.data;
    }
    
    public AdvancedImageView getImage() {
        return this.img;
    }
    
    public PlayContext getPlayContext() {
        return this.playContext;
    }
    
    public String getPlayablId() {
        return this.playableId;
    }
    
    public void setIgnoreClicks() {
        this.ignoreClicks = true;
    }
    
    public void setTitleTextWithSelectdHighlighting() {
        if (this.title == null || this.title.getText() == null) {
            return;
        }
        final SpannableString text = new SpannableString(this.title.getText());
        ((Spannable)text).setSpan((Object)new ForegroundColorSpan(this.getHighLightColor()), 0, this.title.getText().length(), 33);
        this.title.setText((CharSequence)text);
        this.title.setTypeface(Typeface.DEFAULT_BOLD);
    }
    
    public void update(final Object o, final PlayContext playContext, final String s, final String searchReferenceId) {
        this.playContext = playContext;
        this.searchReferenceId = searchReferenceId;
        if (o instanceof SearchVideo) {
            this.playableId = ((SearchVideo)o).getId();
            this.displayName = this.playableId;
            this.updateForVideo((SearchVideo)o, playContext.getVideoPos(), s);
            return;
        }
        if (o instanceof SearchPerson) {
            this.playableId = ((SearchPerson)o).getId();
            this.displayName = ((SearchPerson)o).getName();
            this.update((SearchPerson)o, s);
            return;
        }
        if (o instanceof SearchSuggestion) {
            this.playableId = ((SearchSuggestion)o).getTitle();
            this.displayName = this.playableId;
            this.update((SearchSuggestion)o, s);
            return;
        }
        throw new IllegalStateException("Unknown search result type");
    }
}
