// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.image;

import android.graphics.PorterDuff$Mode;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.yoga.YogaConstants;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.common.MapBuilder;
import java.util.Map;
import com.facebook.drawee.backends.pipeline.Fresco;
import android.content.Context;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.uimanager.SimpleViewManager;

public class ReactImageManager extends SimpleViewManager<ReactImageView>
{
    protected static final String REACT_CLASS = "RCTImageView";
    private final Object mCallerContext;
    private AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    
    public ReactImageManager() {
        this.mDraweeControllerBuilder = null;
        this.mCallerContext = null;
    }
    
    public ReactImageManager(final AbstractDraweeControllerBuilder mDraweeControllerBuilder, final Object mCallerContext) {
        this.mDraweeControllerBuilder = mDraweeControllerBuilder;
        this.mCallerContext = mCallerContext;
    }
    
    public ReactImageView createViewInstance(final ThemedReactContext themedReactContext) {
        return new ReactImageView((Context)themedReactContext, this.getDraweeControllerBuilder(), this.getCallerContext());
    }
    
    public Object getCallerContext() {
        return this.mCallerContext;
    }
    
    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
        if (this.mDraweeControllerBuilder == null) {
            this.mDraweeControllerBuilder = Fresco.newDraweeControllerBuilder();
        }
        return this.mDraweeControllerBuilder;
    }
    
    @Override
    public Map getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.of(ImageLoadEvent.eventNameForType(4), MapBuilder.of("registrationName", "onLoadStart"), ImageLoadEvent.eventNameForType(2), MapBuilder.of("registrationName", "onLoad"), ImageLoadEvent.eventNameForType(1), MapBuilder.of("registrationName", "onError"), ImageLoadEvent.eventNameForType(3), MapBuilder.of("registrationName", "onLoadEnd"));
    }
    
    @Override
    public String getName() {
        return "RCTImageView";
    }
    
    protected void onAfterUpdateTransaction(final ReactImageView reactImageView) {
        super.onAfterUpdateTransaction((T)reactImageView);
        reactImageView.maybeUpdateView();
    }
    
    @ReactProp(customType = "Color", name = "borderColor")
    public void setBorderColor(final ReactImageView reactImageView, final Integer n) {
        if (n == null) {
            reactImageView.setBorderColor(0);
            return;
        }
        reactImageView.setBorderColor(n);
    }
    
    @ReactPropGroup(defaultFloat = Float.NaN, names = { "borderRadius", "borderTopLeftRadius", "borderTopRightRadius", "borderBottomRightRadius", "borderBottomLeftRadius" })
    public void setBorderRadius(final ReactImageView reactImageView, final int n, final float n2) {
        float pixelFromDIP = n2;
        if (!YogaConstants.isUndefined(n2)) {
            pixelFromDIP = PixelUtil.toPixelFromDIP(n2);
        }
        if (n == 0) {
            reactImageView.setBorderRadius(pixelFromDIP);
            return;
        }
        reactImageView.setBorderRadius(pixelFromDIP, n - 1);
    }
    
    @ReactProp(name = "borderWidth")
    public void setBorderWidth(final ReactImageView reactImageView, final float borderWidth) {
        reactImageView.setBorderWidth(borderWidth);
    }
    
    @ReactProp(name = "fadeDuration")
    public void setFadeDuration(final ReactImageView reactImageView, final int fadeDuration) {
        reactImageView.setFadeDuration(fadeDuration);
    }
    
    @ReactProp(name = "shouldNotifyLoadEvents")
    public void setLoadHandlersRegistered(final ReactImageView reactImageView, final boolean shouldNotifyLoadEvents) {
        reactImageView.setShouldNotifyLoadEvents(shouldNotifyLoadEvents);
    }
    
    @ReactProp(name = "loadingIndicatorSrc")
    public void setLoadingIndicatorSource(final ReactImageView reactImageView, final String loadingIndicatorSource) {
        reactImageView.setLoadingIndicatorSource(loadingIndicatorSource);
    }
    
    @ReactProp(name = "overlayColor")
    public void setOverlayColor(final ReactImageView reactImageView, final Integer n) {
        if (n == null) {
            reactImageView.setOverlayColor(0);
            return;
        }
        reactImageView.setOverlayColor(n);
    }
    
    @ReactProp(name = "progressiveRenderingEnabled")
    public void setProgressiveRenderingEnabled(final ReactImageView reactImageView, final boolean progressiveRenderingEnabled) {
        reactImageView.setProgressiveRenderingEnabled(progressiveRenderingEnabled);
    }
    
    @ReactProp(name = "resizeMethod")
    public void setResizeMethod(final ReactImageView reactImageView, final String s) {
        if (s == null || "auto".equals(s)) {
            reactImageView.setResizeMethod(ImageResizeMethod.AUTO);
            return;
        }
        if ("resize".equals(s)) {
            reactImageView.setResizeMethod(ImageResizeMethod.RESIZE);
            return;
        }
        if ("scale".equals(s)) {
            reactImageView.setResizeMethod(ImageResizeMethod.SCALE);
            return;
        }
        throw new JSApplicationIllegalArgumentException("Invalid resize method: '" + s + "'");
    }
    
    @ReactProp(name = "resizeMode")
    public void setResizeMode(final ReactImageView reactImageView, final String s) {
        reactImageView.setScaleType(ImageResizeMode.toScaleType(s));
    }
    
    @ReactProp(name = "src")
    public void setSource(final ReactImageView reactImageView, final ReadableArray source) {
        reactImageView.setSource(source);
    }
    
    @ReactProp(customType = "Color", name = "tintColor")
    public void setTintColor(final ReactImageView reactImageView, final Integer n) {
        if (n == null) {
            reactImageView.clearColorFilter();
            return;
        }
        reactImageView.setColorFilter((int)n, PorterDuff$Mode.SRC_IN);
    }
}
