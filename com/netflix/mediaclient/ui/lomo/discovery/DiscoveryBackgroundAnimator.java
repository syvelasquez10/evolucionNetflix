// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo.discovery;

import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.gfx.ImageLoader$ImageLoaderListener;
import com.netflix.mediaclient.servicemgr.IClientLogging$AssetType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.service.logging.error.ErrorLoggingManager;
import com.netflix.mediaclient.util.StringUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.view.ViewGroup;
import android.widget.ImageView$ScaleType;
import com.netflix.mediaclient.Log;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import android.os.Handler;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.servicemgr.interface_.Discovery;
import java.util.List;
import android.content.Context;
import android.view.View;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.widget.ImageView;

public class DiscoveryBackgroundAnimator implements PaginatedDiscoveryAdapter$BlurredStoryArtProvider
{
    private static final String TAG = "DiscoveryBackgroundAnimator";
    private final int MAX_BG_ALPHA;
    private final int MAX_BLUR_RADIUS;
    private ImageView blurredBottomImageView;
    private SparseArray<Bitmap> blurredImages;
    private ImageView blurredTopImageView;
    private ImageView bottomGradient;
    private View bottomView;
    private Context context;
    float currentOffset;
    private int currentPage;
    private List<Discovery> data;
    protected final ErrorWrapper$Callback errorCallback;
    LoadingAndErrorWrapper leWrapper;
    private Handler mainHandler;
    private int nextPage;
    private ExecutorService poolExecutor;
    private View topView;
    
    public DiscoveryBackgroundAnimator(final Context context, final View topView, final View bottomView) {
        this.MAX_BG_ALPHA = 155;
        this.MAX_BLUR_RADIUS = 25;
        this.nextPage = 1;
        this.mainHandler = new Handler();
        this.errorCallback = new DiscoveryBackgroundAnimator$1(this);
        this.context = context;
        this.topView = topView;
        this.bottomView = bottomView;
        this.blurredImages = (SparseArray<Bitmap>)new SparseArray();
        this.poolExecutor = Executors.newSingleThreadExecutor();
        this.addBottomGradientIfNeeded();
    }
    
    private void addBottomGradientIfNeeded() {
        Log.i("DiscoveryBackgroundAnimator", "addBottomGradientIfNeeded()");
        this.bottomGradient = (ImageView)this.bottomView.findViewById(2131689482);
        if (this.bottomGradient == null) {
            (this.bottomGradient = new ImageView(this.context)).setImageResource(2130837941);
            this.bottomGradient.setId(2131689482);
            (this.blurredTopImageView = new ImageView(this.context)).setScaleType(ImageView$ScaleType.CENTER_CROP);
            this.blurredTopImageView.setId(2131689481);
            (this.blurredBottomImageView = new ImageView(this.context)).setScaleType(ImageView$ScaleType.CENTER_CROP);
            this.blurredBottomImageView.setId(2131689480);
            final ViewGroup viewGroup = (ViewGroup)this.bottomView;
            viewGroup.addView((View)this.bottomGradient, 0, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, (int)this.context.getResources().getDimension(2131362067), 80));
            viewGroup.addView((View)this.blurredTopImageView, 0, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1, 80));
            viewGroup.addView((View)this.blurredBottomImageView, 0, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1, 80));
            LayoutInflater.from(this.context).inflate(2130903173, viewGroup);
        }
        else {
            Log.i("DiscoveryBackgroundAnimator", "bottomGradient view was found!");
            this.blurredTopImageView = (ImageView)this.bottomView.findViewById(2131689481);
            this.blurredBottomImageView = (ImageView)this.bottomView.findViewById(2131689480);
        }
        this.leWrapper = new LoadingAndErrorWrapper(this.bottomView, this.errorCallback);
    }
    
    private String getBackgroundUrl(final Discovery discovery) {
        if (Log.isLoggable()) {
            if (StringUtils.isNotEmpty(discovery.getVertStoryArtUrl())) {
                Log.i("DiscoveryBackgroundAnimator", "Got vertical story art for: " + discovery.getTitle() + "; url: " + discovery.getVertStoryArtUrl());
            }
            else {
                Log.w("DiscoveryBackgroundAnimator", "NO vertical story art for: " + discovery.getTitle());
            }
        }
        return discovery.getVertStoryArtUrl();
    }
    
    private boolean hasDataForPage(final int n) {
        return this.data != null && n < this.data.size() && this.data.get(n) != null;
    }
    
    private void setBlurredImage(final int n, final Bitmap bitmap, final ImageView imageView) {
        final Bitmap imageBitmap = (Bitmap)this.blurredImages.get(n);
        if (imageBitmap != null) {
            imageView.setImageBitmap(imageBitmap);
            this.updateBackgrounds(this.currentPage);
            Log.i("DiscoveryBackgroundAnimator", "Setting an updated blur image: " + imageBitmap + " to view: " + imageView);
            return;
        }
        if (this.poolExecutor == null) {
            Log.w("DiscoveryBackgroundAnimator", "Fragment was destroyed - skipping setBlurredImage() call");
            return;
        }
        this.poolExecutor.submit(new DiscoveryBackgroundAnimator$4(this, bitmap, n, imageView));
        this.blurredImages.put(n, (Object)bitmap);
        Log.i("DiscoveryBackgroundAnimator", "Skipping bitmap set as it was not blurred yet");
    }
    
    private void updateBackgrounds(int imageAlpha) {
        if (this.blurredTopImageView == null || this.blurredBottomImageView == null) {
            Log.w("DiscoveryBackgroundAnimator", "updateBackgrounds() was called but views are not ready yet - skipping");
            return;
        }
        if (Log.isLoggable()) {
            Log.i("DiscoveryBackgroundAnimator", "updateBackgrounds() pos: " + imageAlpha + "; offset: " + this.currentOffset + "; flag: " + (this.currentPage == imageAlpha));
        }
        imageAlpha = (int)((1.0f - this.currentOffset) * 155.0f);
        this.blurredTopImageView.setImageAlpha((int)(this.currentOffset * 155.0f * this.currentOffset * this.currentOffset));
        this.blurredBottomImageView.setImageAlpha(imageAlpha);
    }
    
    private void updateDrawables() {
        Log.i("DiscoveryBackgroundAnimator", "updateDrawables()");
        if (!this.hasDataForPage(this.currentPage)) {
            final String string = "SPY-8068 - NO data for currentPage: " + this.currentPage;
            Log.e("DiscoveryBackgroundAnimator", string);
            ErrorLoggingManager.logHandledException(string);
            return;
        }
        NetflixActivity.getImageLoader(this.context).getImg(this.getBackgroundUrl(this.data.get(this.currentPage)), IClientLogging$AssetType.boxArt, this.topView.getWidth(), this.topView.getHeight(), new DiscoveryBackgroundAnimator$2(this));
        if (this.hasDataForPage(this.nextPage)) {
            NetflixActivity.getImageLoader(this.context).getImg(this.getBackgroundUrl(this.data.get(this.nextPage)), IClientLogging$AssetType.boxArt, this.blurredTopImageView.getWidth(), this.blurredTopImageView.getHeight(), new DiscoveryBackgroundAnimator$3(this));
            return;
        }
        final String string2 = "SPY-8068 - NO data for nextPage: " + this.nextPage;
        Log.e("DiscoveryBackgroundAnimator", string2);
        ErrorLoggingManager.logHandledException(string2);
    }
    
    public void destroy() {
        Log.v("DiscoveryBackgroundAnimator", "Destroying background thread(s)");
        this.poolExecutor.shutdown();
        this.poolExecutor = null;
    }
    
    @Override
    public Drawable getBlurredStoryArt() {
        return this.blurredBottomImageView.getDrawable();
    }
    
    public void onPageScrolled(final int currentPage, final float currentOffset) {
        Log.i("DiscoveryBackgroundAnimator", "onPageScrolled: " + currentPage + "; " + currentOffset);
        if (this.currentPage != currentPage || this.currentPage == 0) {
            this.currentPage = currentPage;
            this.nextPage = this.currentPage + 1;
            this.updateDrawables();
        }
        this.currentOffset = currentOffset;
        this.updateBackgrounds(currentPage);
    }
    
    public void setData(final List<Discovery> data) {
        this.data = data;
        this.leWrapper.hide(true);
    }
    
    public void updateData(final List<Discovery> data) {
        this.setData(data);
        this.updateDrawables();
    }
}
