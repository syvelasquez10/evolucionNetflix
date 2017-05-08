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
import android.graphics.Path$Direction;
import android.graphics.RectF;
import android.graphics.Path;
import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.BitmapShader;
import android.graphics.Shader$TileMode;
import android.graphics.Paint;
import com.facebook.react.uimanager.FloatUtil;
import android.graphics.Rect;
import android.graphics.Bitmap;
import com.facebook.imagepipeline.request.BasePostprocessor;

class ReactImageView$RoundedCornerPostprocessor extends BasePostprocessor
{
    final /* synthetic */ ReactImageView this$0;
    
    private ReactImageView$RoundedCornerPostprocessor(final ReactImageView this$0) {
        this.this$0 = this$0;
    }
    
    void getRadii(final Bitmap bitmap, final float[] array, final float[] array2) {
        this.this$0.mScaleType.getTransform(ReactImageView.sMatrix, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()), bitmap.getWidth(), bitmap.getHeight(), 0.0f, 0.0f);
        ReactImageView.sMatrix.invert(ReactImageView.sInverse);
        array2[1] = (array2[0] = ReactImageView.sInverse.mapRadius(array[0]));
        array2[3] = (array2[2] = ReactImageView.sInverse.mapRadius(array[1]));
        array2[5] = (array2[4] = ReactImageView.sInverse.mapRadius(array[2]));
        array2[7] = (array2[6] = ReactImageView.sInverse.mapRadius(array[3]));
    }
    
    @Override
    public void process(final Bitmap bitmap, final Bitmap bitmap2) {
        this.this$0.cornerRadii(ReactImageView.sComputedCornerRadii);
        bitmap.setHasAlpha(true);
        if (FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[0], 0.0f) && FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[1], 0.0f) && FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[2], 0.0f) && FloatUtil.floatsEqual(ReactImageView.sComputedCornerRadii[3], 0.0f)) {
            super.process(bitmap, bitmap2);
            return;
        }
        final Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader((Shader)new BitmapShader(bitmap2, Shader$TileMode.CLAMP, Shader$TileMode.CLAMP));
        final Canvas canvas = new Canvas(bitmap);
        final float[] array = new float[8];
        this.getRadii(bitmap2, ReactImageView.sComputedCornerRadii, array);
        final Path path = new Path();
        path.addRoundRect(new RectF(0.0f, 0.0f, (float)bitmap2.getWidth(), (float)bitmap2.getHeight()), array, Path$Direction.CW);
        canvas.drawPath(path, paint);
    }
}
