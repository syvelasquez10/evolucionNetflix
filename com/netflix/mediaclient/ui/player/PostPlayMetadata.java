// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import com.netflix.mediaclient.util.gfx.ImageLoader$StaticImgConfig;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import android.view.ViewGroup;
import android.widget.Button;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.model.leafs.PostPlayAction;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.model.leafs.PostPlayItem;
import java.util.ArrayList;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.ui.details.NetflixRatingBar;
import com.netflix.mediaclient.android.widget.AdvancedImageView;
import android.widget.TextView;
import java.util.List;
import android.widget.LinearLayout;

public class PostPlayMetadata extends LinearLayout
{
    private List<PostPlayCallToAction> actions;
    private LinearLayout buttonsContainer;
    private TextView contentLength;
    private PostPlayCountDown countdown;
    private TextView episodeBadge;
    private AdvancedImageView logo;
    private TextView maturityRating;
    private LinearLayout metadataBar;
    private PlayerFragment playerFragment;
    private TextView postPlayTitle;
    private NetflixRatingBar ratingBar;
    private TextView synopsis;
    private LinearLayout titleBar;
    private TextView year;
    
    public PostPlayMetadata(final Context context) {
        this(context, null);
    }
    
    public PostPlayMetadata(final Context context, final AttributeSet set) {
        this(context, set, 0);
    }
    
    public PostPlayMetadata(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.actions = new ArrayList<PostPlayCallToAction>(3);
    }
    
    private void findViews() {
        this.countdown = (PostPlayCountDown)this.findViewById(2131755729);
        this.logo = (AdvancedImageView)this.findViewById(2131755760);
        this.titleBar = (LinearLayout)this.findViewById(2131755761);
        this.episodeBadge = (TextView)this.findViewById(2131755762);
        this.postPlayTitle = (TextView)this.findViewById(2131755440);
        this.metadataBar = (LinearLayout)this.findViewById(2131755755);
        this.ratingBar = (NetflixRatingBar)this.findViewById(2131755167);
        this.year = (TextView)this.findViewById(2131755756);
        this.maturityRating = (TextView)this.findViewById(2131755757);
        this.contentLength = (TextView)this.findViewById(2131755758);
        this.synopsis = (TextView)this.findViewById(2131755738);
        this.buttonsContainer = (LinearLayout)this.findViewById(2131755759);
    }
    
    private String getContentLength(final PostPlayItem postPlayItem) {
        final String type = postPlayItem.getType();
        switch (type) {
            default: {
                return postPlayItem.getRuntime() / 60 + "h " + postPlayItem.getRuntime() % 60 + " m";
            }
            case "show": {
                return postPlayItem.getSeasonsLabel();
            }
            case "season":
            case "series": {
                return postPlayItem.getEpisodes() + " episodes";
            }
        }
    }
    
    private String getTitle(final PostPlayItem postPlayItem) {
        String title;
        if (postPlayItem.isNSRE()) {
            title = postPlayItem.getTitle();
        }
        else {
            String s;
            if (postPlayItem.getType().equals("episode") && postPlayItem.getPlayAction() != null) {
                final PostPlayAction playAction = postPlayItem.getPlayAction();
                s = this.getContext().getResources().getString(2131296743, new Object[] { postPlayItem.getAncestorTitle(), playAction.getSeasonSequenceAbbr(), playAction.getEpisode(), postPlayItem.getTitle() });
            }
            else {
                s = postPlayItem.getTitle();
            }
            title = s;
            if (!StringUtils.isEmpty(postPlayItem.getSupplementalMessage())) {
                title = s;
                if (!postPlayItem.getExperienceType().contains("nextEpisode")) {
                    title = s;
                    if (!postPlayItem.getExperienceType().contains("recommendation")) {
                        return postPlayItem.getSupplementalMessage();
                    }
                }
            }
        }
        return title;
    }
    
    private boolean supportsButtons(final PostPlayItem postPlayItem) {
        boolean b = false;
        final String experienceType = postPlayItem.getExperienceType();
        switch (experienceType) {
            default: {
                b = true;
                return b;
            }
            case "nextEpisode":
            case "episodicTeaser": {
                return b;
            }
        }
    }
    
    private void updateMetadataBar(final PostPlayItem postPlayItem) {
        if (postPlayItem.getRating().size() > 0) {
            this.ratingBar.update(ViewUtils.getRatingBarDataProviderSafely((View)this), postPlayItem);
            this.ratingBar.setVisibility(0);
        }
        else {
            this.ratingBar.setVisibility(8);
        }
        if (postPlayItem.getYear() != null) {
            this.year.setText((CharSequence)String.valueOf(postPlayItem.getYear()));
            this.year.setVisibility(0);
        }
        else {
            this.year.setVisibility(8);
        }
        if (postPlayItem.getMaturityRating() != null) {
            this.maturityRating.setText((CharSequence)postPlayItem.getMaturityRating());
            this.maturityRating.setVisibility(0);
        }
        else {
            this.maturityRating.setVisibility(8);
        }
        final String contentLength = this.getContentLength(postPlayItem);
        if (contentLength != null) {
            this.contentLength.setText((CharSequence)contentLength);
            this.contentLength.setVisibility(0);
            return;
        }
        this.contentLength.setVisibility(8);
    }
    
    private void updateTitleBar(final PostPlayItem postPlayItem) {
        if (postPlayItem.getTitle() != null) {
            this.postPlayTitle.setText((CharSequence)this.getTitle(postPlayItem));
            this.postPlayTitle.setVisibility(0);
        }
        else {
            this.postPlayTitle.setVisibility(8);
        }
        if (postPlayItem.hasNewBadge()) {
            LoMoUtils.toggleEpisodeBadge(postPlayItem.getBadgeKeys(), this.episodeBadge);
            this.episodeBadge.setVisibility(0);
            return;
        }
        this.episodeBadge.setVisibility(8);
    }
    
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.findViews();
    }
    
    public void startTimer() {
        if (this.countdown != null) {
            this.countdown.startTimer();
        }
    }
    
    public void stopTimer() {
        if (this.countdown != null) {
            this.countdown.stopTimer();
        }
    }
    
    public void updateViews(final PostPlayItem postPlayItem, final NetflixActivity netflixActivity, final PlayerFragment playerFragment, final PostPlayRequestContext postPlayRequestContext) {
        this.playerFragment = playerFragment;
        final boolean b = postPlayItem.getExperienceType().equals("episodicTeaser") && postPlayItem.getLogoAsset() != null;
        this.buttonsContainer.removeAllViews();
        if (this.supportsButtons(postPlayItem) && !postPlayRequestContext.equals(PostPlayRequestContext.MDX)) {
            for (int i = 1; i < postPlayItem.getActions().size(); ++i) {
                final PostPlayAction postPlayAction = postPlayItem.getActions().get(i);
                final Button button = (Button)netflixActivity.getLayoutInflater().inflate(2130903264, (ViewGroup)this.buttonsContainer, false);
                this.buttonsContainer.addView((View)button);
                this.actions.add(new PostPlayCallToAction(netflixActivity, playerFragment, postPlayAction, postPlayRequestContext, (View)button));
            }
        }
        if (postPlayItem.getSynopsis() != null && !b) {
            this.synopsis.setText((CharSequence)postPlayItem.getSynopsis());
            this.synopsis.setVisibility(0);
        }
        else {
            this.synopsis.setVisibility(8);
        }
        if (this.countdown != null) {
            if (postPlayItem.isAutoPlay()) {
                this.countdown.updateViews(postPlayItem, netflixActivity);
                this.countdown.setVisibility(0);
            }
            else {
                this.countdown.setVisibility(8);
            }
        }
        if (this.titleBar != null) {
            this.updateTitleBar(postPlayItem);
        }
        if (this.metadataBar != null) {
            this.updateMetadataBar(postPlayItem);
        }
        if (this.logo != null) {
            if (!b) {
                this.logo.setVisibility(8);
                return;
            }
            NetflixActivity.getImageLoader((Context)netflixActivity).showImg(this.logo, postPlayItem.getLogoAsset().getUrl(), IClientLogging$AssetType.merchStill, postPlayItem.getAncestorTitle(), ImageLoader$StaticImgConfig.LIGHT_NO_PLACEHOLDER, true, 1);
            this.logo.setVisibility(0);
        }
    }
}
