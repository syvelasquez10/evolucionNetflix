// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.image;

import com.facebook.react.bridge.ReadableMap;
import android.net.Uri;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.drawee.drawable.AutoRotateDrawable;
import com.facebook.react.views.imagehelper.ResourceDrawableIdHelper;
import com.facebook.react.uimanager.PixelUtil;
import java.util.Arrays;
import com.facebook.react.uimanager.FloatUtil;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.controller.ForwardingControllerListener;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.drawee.generic.RoundingParams$RoundingMethod;
import com.facebook.common.util.UriUtil;
import com.facebook.react.views.imagehelper.MultiSourceHelper$MultiSourceResult;
import com.facebook.react.views.imagehelper.MultiSourceHelper;
import com.facebook.yoga.YogaConstants;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import java.util.LinkedList;
import android.content.Context;
import java.util.List;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.react.views.imagehelper.ImageSource;
import android.graphics.Matrix;
import com.facebook.drawee.view.GenericDraweeView;

public class ReactImageView extends GenericDraweeView
{
    private static float[] sComputedCornerRadii;
    private static final Matrix sInverse;
    private static final Matrix sMatrix;
    private int mBorderColor;
    private float[] mBorderCornerRadii;
    private float mBorderRadius;
    private float mBorderWidth;
    private ImageSource mCachedImageSource;
    private final Object mCallerContext;
    private ControllerListener mControllerForTesting;
    private ControllerListener mControllerListener;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private int mFadeDurationMs;
    private ImageSource mImageSource;
    private boolean mIsDirty;
    private Drawable mLoadingImageDrawable;
    private int mOverlayColor;
    private boolean mProgressiveRenderingEnabled;
    private ImageResizeMethod mResizeMethod;
    private final ReactImageView$RoundedCornerPostprocessor mRoundedCornerPostprocessor;
    private ScalingUtils$ScaleType mScaleType;
    private final List<ImageSource> mSources;
    
    static {
        ReactImageView.sComputedCornerRadii = new float[4];
        sMatrix = new Matrix();
        sInverse = new Matrix();
    }
    
    public ReactImageView(final Context context, final AbstractDraweeControllerBuilder mDraweeControllerBuilder, final Object mCallerContext) {
        super(context, buildHierarchy(context));
        this.mResizeMethod = ImageResizeMethod.AUTO;
        this.mBorderRadius = Float.NaN;
        this.mFadeDurationMs = -1;
        this.mScaleType = ImageResizeMode.defaultValue();
        this.mDraweeControllerBuilder = mDraweeControllerBuilder;
        this.mRoundedCornerPostprocessor = new ReactImageView$RoundedCornerPostprocessor(this, null);
        this.mCallerContext = mCallerContext;
        this.mSources = new LinkedList<ImageSource>();
    }
    
    private static GenericDraweeHierarchy buildHierarchy(final Context context) {
        return new GenericDraweeHierarchyBuilder(context.getResources()).setRoundingParams(RoundingParams.fromCornersRadius(0.0f)).build();
    }
    
    private void cornerRadii(final float[] array) {
        float mBorderRadius;
        if (!YogaConstants.isUndefined(this.mBorderRadius)) {
            mBorderRadius = this.mBorderRadius;
        }
        else {
            mBorderRadius = 0.0f;
        }
        float n;
        if (this.mBorderCornerRadii != null && !YogaConstants.isUndefined(this.mBorderCornerRadii[0])) {
            n = this.mBorderCornerRadii[0];
        }
        else {
            n = mBorderRadius;
        }
        array[0] = n;
        float n2;
        if (this.mBorderCornerRadii != null && !YogaConstants.isUndefined(this.mBorderCornerRadii[1])) {
            n2 = this.mBorderCornerRadii[1];
        }
        else {
            n2 = mBorderRadius;
        }
        array[1] = n2;
        float n3;
        if (this.mBorderCornerRadii != null && !YogaConstants.isUndefined(this.mBorderCornerRadii[2])) {
            n3 = this.mBorderCornerRadii[2];
        }
        else {
            n3 = mBorderRadius;
        }
        array[2] = n3;
        float n4 = mBorderRadius;
        if (this.mBorderCornerRadii != null) {
            n4 = mBorderRadius;
            if (!YogaConstants.isUndefined(this.mBorderCornerRadii[3])) {
                n4 = this.mBorderCornerRadii[3];
            }
        }
        array[3] = n4;
    }
    
    private boolean hasMultipleSources() {
        return this.mSources.size() > 1;
    }
    
    private void setSourceImage() {
        this.mImageSource = null;
        if (this.mSources.isEmpty()) {
            return;
        }
        if (this.hasMultipleSources()) {
            final MultiSourceHelper$MultiSourceResult bestSourceForSize = MultiSourceHelper.getBestSourceForSize(this.getWidth(), this.getHeight(), this.mSources);
            this.mImageSource = bestSourceForSize.getBestResult();
            this.mCachedImageSource = bestSourceForSize.getBestResultInCache();
            return;
        }
        this.mImageSource = this.mSources.get(0);
    }
    
    private boolean shouldResize(final ImageSource imageSource) {
        boolean b = false;
        if (this.mResizeMethod == ImageResizeMethod.AUTO) {
            if (UriUtil.isLocalContentUri(imageSource.getUri()) || UriUtil.isLocalFileUri(imageSource.getUri())) {
                b = true;
            }
        }
        else if (this.mResizeMethod == ImageResizeMethod.RESIZE) {
            return true;
        }
        return b;
    }
    
    private void warnImageSource(final String s) {
    }
    
    public boolean hasOverlappingRendering() {
        return false;
    }
    
    public void maybeUpdateView() {
        if (this.mIsDirty && (!this.hasMultipleSources() || (this.getWidth() > 0 && this.getHeight() > 0))) {
            this.setSourceImage();
            if (this.mImageSource != null) {
                final boolean shouldResize = this.shouldResize(this.mImageSource);
                if (!shouldResize || (this.getWidth() > 0 && this.getHeight() > 0)) {
                    final GenericDraweeHierarchy genericDraweeHierarchy = this.getHierarchy();
                    genericDraweeHierarchy.setActualImageScaleType(this.mScaleType);
                    if (this.mLoadingImageDrawable != null) {
                        genericDraweeHierarchy.setPlaceholderImage(this.mLoadingImageDrawable, ScalingUtils$ScaleType.CENTER);
                    }
                    boolean b;
                    if (this.mScaleType != ScalingUtils$ScaleType.CENTER_CROP && this.mScaleType != ScalingUtils$ScaleType.FOCUS_CROP) {
                        b = true;
                    }
                    else {
                        b = false;
                    }
                    final RoundingParams roundingParams = genericDraweeHierarchy.getRoundingParams();
                    if (b) {
                        roundingParams.setCornersRadius(0.0f);
                    }
                    else {
                        this.cornerRadii(ReactImageView.sComputedCornerRadii);
                        roundingParams.setCornersRadii(ReactImageView.sComputedCornerRadii[0], ReactImageView.sComputedCornerRadii[1], ReactImageView.sComputedCornerRadii[2], ReactImageView.sComputedCornerRadii[3]);
                    }
                    roundingParams.setBorder(this.mBorderColor, this.mBorderWidth);
                    if (this.mOverlayColor != 0) {
                        roundingParams.setOverlayColor(this.mOverlayColor);
                    }
                    else {
                        roundingParams.setRoundingMethod(RoundingParams$RoundingMethod.BITMAP_ONLY);
                    }
                    genericDraweeHierarchy.setRoundingParams(roundingParams);
                    int mFadeDurationMs;
                    if (this.mFadeDurationMs >= 0) {
                        mFadeDurationMs = this.mFadeDurationMs;
                    }
                    else if (this.mImageSource.isResource()) {
                        mFadeDurationMs = 0;
                    }
                    else {
                        mFadeDurationMs = 300;
                    }
                    genericDraweeHierarchy.setFadeDuration(mFadeDurationMs);
                    ReactImageView$RoundedCornerPostprocessor mRoundedCornerPostprocessor;
                    if (b) {
                        mRoundedCornerPostprocessor = this.mRoundedCornerPostprocessor;
                    }
                    else {
                        mRoundedCornerPostprocessor = null;
                    }
                    ResizeOptions resizeOptions;
                    if (shouldResize) {
                        resizeOptions = new ResizeOptions(this.getWidth(), this.getHeight());
                    }
                    else {
                        resizeOptions = null;
                    }
                    final ImageRequest build = ImageRequestBuilder.newBuilderWithSource(this.mImageSource.getUri()).setPostprocessor(mRoundedCornerPostprocessor).setResizeOptions(resizeOptions).setAutoRotateEnabled(true).setProgressiveRenderingEnabled(this.mProgressiveRenderingEnabled).build();
                    this.mDraweeControllerBuilder.reset();
                    this.mDraweeControllerBuilder.setAutoPlayAnimations(true).setCallerContext(this.mCallerContext).setOldController(this.getController()).setImageRequest(build);
                    if (this.mCachedImageSource != null) {
                        this.mDraweeControllerBuilder.setLowResImageRequest(ImageRequestBuilder.newBuilderWithSource(this.mCachedImageSource.getUri()).setPostprocessor(mRoundedCornerPostprocessor).setResizeOptions(resizeOptions).setAutoRotateEnabled(true).setProgressiveRenderingEnabled(this.mProgressiveRenderingEnabled).build());
                    }
                    if (this.mControllerListener != null && this.mControllerForTesting != null) {
                        final ForwardingControllerListener controllerListener = new ForwardingControllerListener();
                        controllerListener.addListener(this.mControllerListener);
                        controllerListener.addListener(this.mControllerForTesting);
                        this.mDraweeControllerBuilder.setControllerListener(controllerListener);
                    }
                    else if (this.mControllerForTesting != null) {
                        this.mDraweeControllerBuilder.setControllerListener(this.mControllerForTesting);
                    }
                    else if (this.mControllerListener != null) {
                        this.mDraweeControllerBuilder.setControllerListener(this.mControllerListener);
                    }
                    this.setController(this.mDraweeControllerBuilder.build());
                    this.mIsDirty = false;
                }
            }
        }
    }
    
    protected void onSizeChanged(final int n, final int n2, final int n3, final int n4) {
        super.onSizeChanged(n, n2, n3, n4);
        if (n > 0 && n2 > 0) {
            this.mIsDirty = (this.mIsDirty || this.hasMultipleSources());
            this.maybeUpdateView();
        }
    }
    
    public void setBorderColor(final int mBorderColor) {
        this.mBorderColor = mBorderColor;
        this.mIsDirty = true;
    }
    
    public void setBorderRadius(final float mBorderRadius) {
        if (!FloatUtil.floatsEqual(this.mBorderRadius, mBorderRadius)) {
            this.mBorderRadius = mBorderRadius;
            this.mIsDirty = true;
        }
    }
    
    public void setBorderRadius(final float n, final int n2) {
        if (this.mBorderCornerRadii == null) {
            Arrays.fill(this.mBorderCornerRadii = new float[4], Float.NaN);
        }
        if (!FloatUtil.floatsEqual(this.mBorderCornerRadii[n2], n)) {
            this.mBorderCornerRadii[n2] = n;
            this.mIsDirty = true;
        }
    }
    
    public void setBorderWidth(final float n) {
        this.mBorderWidth = PixelUtil.toPixelFromDIP(n);
        this.mIsDirty = true;
    }
    
    public void setControllerListener(final ControllerListener mControllerForTesting) {
        this.mControllerForTesting = mControllerForTesting;
        this.mIsDirty = true;
        this.maybeUpdateView();
    }
    
    public void setFadeDuration(final int mFadeDurationMs) {
        this.mFadeDurationMs = mFadeDurationMs;
    }
    
    public void setLoadingIndicatorSource(final String s) {
        final Drawable resourceDrawable = ResourceDrawableIdHelper.getInstance().getResourceDrawable(this.getContext(), s);
        AutoRotateDrawable mLoadingImageDrawable;
        if (resourceDrawable != null) {
            mLoadingImageDrawable = new AutoRotateDrawable(resourceDrawable, 1000);
        }
        else {
            mLoadingImageDrawable = null;
        }
        this.mLoadingImageDrawable = mLoadingImageDrawable;
        this.mIsDirty = true;
    }
    
    public void setOverlayColor(final int mOverlayColor) {
        this.mOverlayColor = mOverlayColor;
        this.mIsDirty = true;
    }
    
    public void setProgressiveRenderingEnabled(final boolean mProgressiveRenderingEnabled) {
        this.mProgressiveRenderingEnabled = mProgressiveRenderingEnabled;
    }
    
    public void setResizeMethod(final ImageResizeMethod mResizeMethod) {
        this.mResizeMethod = mResizeMethod;
        this.mIsDirty = true;
    }
    
    public void setScaleType(final ScalingUtils$ScaleType mScaleType) {
        this.mScaleType = mScaleType;
        this.mIsDirty = true;
    }
    
    public void setShouldNotifyLoadEvents(final boolean b) {
        if (!b) {
            this.mControllerListener = null;
        }
        else {
            this.mControllerListener = new ReactImageView$1(this, ((ReactContext)this.getContext()).getNativeModule(UIManagerModule.class).getEventDispatcher());
        }
        this.mIsDirty = true;
    }
    
    public void setSource(final ReadableArray readableArray) {
        int i = 0;
        this.mSources.clear();
        if (readableArray != null && readableArray.size() != 0) {
            if (readableArray.size() == 1) {
                final String string = readableArray.getMap(0).getString("uri");
                final ImageSource imageSource = new ImageSource(this.getContext(), string);
                this.mSources.add(imageSource);
                if (Uri.EMPTY.equals((Object)imageSource.getUri())) {
                    this.warnImageSource(string);
                }
            }
            else {
                while (i < readableArray.size()) {
                    final ReadableMap map = readableArray.getMap(i);
                    final String string2 = map.getString("uri");
                    final ImageSource imageSource2 = new ImageSource(this.getContext(), string2, map.getDouble("width"), map.getDouble("height"));
                    this.mSources.add(imageSource2);
                    if (Uri.EMPTY.equals((Object)imageSource2.getUri())) {
                        this.warnImageSource(string2);
                    }
                    ++i;
                }
            }
        }
        this.mIsDirty = true;
    }
}
