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
import android.graphics.drawable.Animatable;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.drawee.controller.BaseControllerListener;

class ReactImageView$1 extends BaseControllerListener<ImageInfo>
{
    final /* synthetic */ ReactImageView this$0;
    final /* synthetic */ EventDispatcher val$mEventDispatcher;
    
    ReactImageView$1(final ReactImageView this$0, final EventDispatcher val$mEventDispatcher) {
        this.this$0 = this$0;
        this.val$mEventDispatcher = val$mEventDispatcher;
    }
    
    @Override
    public void onFailure(final String s, final Throwable t) {
        this.val$mEventDispatcher.dispatchEvent(new ImageLoadEvent(this.this$0.getId(), 1));
        this.val$mEventDispatcher.dispatchEvent(new ImageLoadEvent(this.this$0.getId(), 3));
    }
    
    @Override
    public void onFinalImageSet(final String s, final ImageInfo imageInfo, final Animatable animatable) {
        if (imageInfo != null) {
            this.val$mEventDispatcher.dispatchEvent(new ImageLoadEvent(this.this$0.getId(), 2, this.this$0.mImageSource.getSource(), imageInfo.getWidth(), imageInfo.getHeight()));
            this.val$mEventDispatcher.dispatchEvent(new ImageLoadEvent(this.this$0.getId(), 3));
        }
    }
    
    @Override
    public void onSubmit(final String s, final Object o) {
        this.val$mEventDispatcher.dispatchEvent(new ImageLoadEvent(this.this$0.getId(), 4));
    }
}
