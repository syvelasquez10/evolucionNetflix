// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import android.graphics.Paint;
import android.graphics.Shader$TileMode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.ui.iko.kong.model.KongInteractivePostPlayModel;
import android.view.View$OnClickListener;
import android.view.View$OnTouchListener;
import android.view.View;
import com.netflix.mediaclient.util.ViewUtils;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.graphics.BitmapFactory$Options;
import android.widget.ImageView;

public class KongBackgroundScreen extends KongBaseScreen
{
    private static final String TAG = "KongBackgroundScreen";
    private ImageView backButton;
    private String backButtonImageUrl;
    private ImageView background;
    private ImageView backgroundPattern;
    private String battleResultHeaderString;
    private String bgImageUrl;
    private String bgPatternImageUrl;
    private ImageView closeButton;
    private String closeButtonImageUrl;
    private boolean forceToggleAnimations;
    private String itemSelectionHeaderString;
    private boolean kongPostPlayDismissed;
    private BitmapFactory$Options options;
    private String postPlayTitleString;
    private ProgressBar progress;
    private String redFlareImageUrl;
    private TextView title;
    private ViewGroup titleContainer;
    private ImageView titleFlare;
    private String unLockedTitleString;
    
    KongBackgroundScreen(final KongInteractivePostPlayManager kongInteractivePostPlayManager) {
        super(kongInteractivePostPlayManager);
        this.options = new BitmapFactory$Options();
    }
    
    ImageView getBackButton() {
        return this.backButton;
    }
    
    ImageView getCloseButton() {
        return this.closeButton;
    }
    
    boolean getForceToggleAnimations() {
        return this.forceToggleAnimations;
    }
    
    @Override
    void hide() {
        ViewUtils.setVisibleOrGone((View)this.closeButton, false);
        this.postPlayManager.hide();
    }
    
    @Override
    void initViews(final View view) {
        this.background = (ImageView)view.findViewById(2131690050);
        this.backgroundPattern = (ImageView)view.findViewById(2131690051);
        this.background.setOnTouchListener((View$OnTouchListener)new KongBackgroundScreen$1(this, view));
        this.title = (TextView)view.findViewById(2131689864);
        this.titleContainer = (ViewGroup)view.findViewById(2131690070);
        this.titleFlare = (ImageView)view.findViewById(2131689865);
        this.progress = (ProgressBar)view.findViewById(2131690071);
        this.closeButton = (ImageView)view.findViewById(2131690064);
        (this.backButton = (ImageView)view.findViewById(2131689777)).setOnClickListener((View$OnClickListener)new KongBackgroundScreen$2(this));
    }
    
    @Override
    void loadPostPlayData(final KongInteractivePostPlayModel kongInteractivePostPlayModel) {
        this.bgImageUrl = kongInteractivePostPlayModel.getBackgroundImageUrl();
        this.bgPatternImageUrl = kongInteractivePostPlayModel.getHexParallaxImageUrl();
        this.closeButtonImageUrl = kongInteractivePostPlayModel.getCloseBTNImageUrl();
        this.backButtonImageUrl = kongInteractivePostPlayModel.getBackBTNImageUrl();
        this.redFlareImageUrl = kongInteractivePostPlayModel.getRedFlareImageUrl();
        this.unLockedTitleString = kongInteractivePostPlayModel.getUnlockedHeaderString();
        this.itemSelectionHeaderString = kongInteractivePostPlayModel.getItemSelectionHeaderString();
        this.battleResultHeaderString = kongInteractivePostPlayModel.getResultTitleString();
        final KongInteractivePostPlayManager$POST_PLAY_STATE postPlayState = this.postPlayManager.getPostPlayState();
        if (postPlayState == KongInteractivePostPlayManager$POST_PLAY_STATE.UNLOCK || postPlayState == KongInteractivePostPlayManager$POST_PLAY_STATE.POWERUP) {
            this.postPlayTitleString = this.unLockedTitleString;
        }
        else {
            if (postPlayState == KongInteractivePostPlayManager$POST_PLAY_STATE.BATTLE) {
                this.postPlayTitleString = this.itemSelectionHeaderString;
                return;
            }
            if (postPlayState == KongInteractivePostPlayManager$POST_PLAY_STATE.RESULT) {
                this.postPlayTitleString = this.battleResultHeaderString;
            }
        }
    }
    
    @Override
    void loadResources() {
        ThreadUtils.assertNotOnMain();
        this.options.inSampleSize = 4;
        this.postPlayManager.loadImageBitmapFromCache(this.background, this.bgImageUrl, this.options);
        if (StringUtils.isNotEmpty(this.bgPatternImageUrl)) {
            if (Build.MANUFACTURER.equals("Amazon")) {
                this.postPlayManager.loadImageBitmapFromCache(this.backgroundPattern, this.bgPatternImageUrl);
            }
            else {
                final BitmapDrawable bitmapDrawable = new BitmapDrawable(this.postPlayManager.fetchImageFromCache(this.bgPatternImageUrl));
                bitmapDrawable.setTileModeXY(Shader$TileMode.REPEAT, Shader$TileMode.REPEAT);
                this.handler.post((Runnable)new KongBackgroundScreen$3(this, bitmapDrawable));
            }
        }
        this.postPlayManager.loadImageBitmapFromCache(this.titleFlare, this.redFlareImageUrl);
        this.postPlayManager.loadImageBitmapFromCache(this.closeButton, this.closeButtonImageUrl);
        this.postPlayManager.loadImageBitmapFromCache(this.backButton, this.backButtonImageUrl);
    }
    
    @Override
    void onResourcesLoaded() {
        this.setTitleText(this.postPlayTitleString);
        this.background.setLayerType(2, (Paint)null);
        ViewUtils.setVisibleOrInvisible((View)this.titleContainer, true);
        ViewUtils.setVisibleOrGone((View)this.closeButton, false);
    }
    
    @Override
    public void releaseBitmapResources() {
        ViewUtils.resetImageDrawable(this.titleFlare);
        ViewUtils.resetImageDrawable(this.closeButton);
        ViewUtils.resetImageDrawable(this.backButton);
        ViewUtils.resetImageDrawable(this.background);
        ViewUtils.resetImageDrawable(this.backgroundPattern);
    }
    
    void setTitleText(final String text) {
        this.title.setText((CharSequence)text);
    }
    
    void showProgress(final boolean b) {
        ViewUtils.setVisibleOrGone((View)this.progress, b);
    }
    
    @Override
    void start() {
        ViewUtils.setVisibleOrGone((View)this.backButton, true);
        ViewUtils.setVisibleOrGone((View)this.closeButton, false);
    }
}
