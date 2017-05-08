// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.graphics.Paint;
import android.os.Build$VERSION;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.bridge.ReadableArray;
import android.view.View;

public abstract class BaseViewManager<T extends View, C extends LayoutShadowNode> extends ViewManager<T, C>
{
    private static final String PROP_ACCESSIBILITY_COMPONENT_TYPE = "accessibilityComponentType";
    private static final String PROP_ACCESSIBILITY_LABEL = "accessibilityLabel";
    private static final String PROP_ACCESSIBILITY_LIVE_REGION = "accessibilityLiveRegion";
    private static final String PROP_BACKGROUND_COLOR = "backgroundColor";
    private static final String PROP_ELEVATION = "elevation";
    private static final String PROP_IMPORTANT_FOR_ACCESSIBILITY = "importantForAccessibility";
    private static final String PROP_OPACITY = "opacity";
    private static final String PROP_RENDER_TO_HARDWARE_TEXTURE = "renderToHardwareTextureAndroid";
    private static final String PROP_ROTATION = "rotation";
    private static final String PROP_SCALE_X = "scaleX";
    private static final String PROP_SCALE_Y = "scaleY";
    public static final String PROP_TEST_ID = "testID";
    private static final String PROP_TRANSFORM = "transform";
    private static final String PROP_TRANSLATE_X = "translateX";
    private static final String PROP_TRANSLATE_Y = "translateY";
    private static final String PROP_Z_INDEX = "zIndex";
    private static MatrixMathHelper$MatrixDecompositionContext sMatrixDecompositionContext;
    private static double[] sTransformDecompositionArray;
    
    static {
        BaseViewManager.sMatrixDecompositionContext = new MatrixMathHelper$MatrixDecompositionContext();
        BaseViewManager.sTransformDecompositionArray = new double[16];
    }
    
    private static void resetTransformProperty(final View view) {
        view.setTranslationX(PixelUtil.toPixelFromDIP(0.0f));
        view.setTranslationY(PixelUtil.toPixelFromDIP(0.0f));
        view.setRotation(0.0f);
        view.setRotationX(0.0f);
        view.setRotationY(0.0f);
        view.setScaleX(1.0f);
        view.setScaleY(1.0f);
    }
    
    private static void setTransformProperty(final View view, final ReadableArray readableArray) {
        TransformHelper.processTransform(readableArray, BaseViewManager.sTransformDecompositionArray);
        MatrixMathHelper.decomposeMatrix(BaseViewManager.sTransformDecompositionArray, BaseViewManager.sMatrixDecompositionContext);
        view.setTranslationX(PixelUtil.toPixelFromDIP((float)BaseViewManager.sMatrixDecompositionContext.translation[0]));
        view.setTranslationY(PixelUtil.toPixelFromDIP((float)BaseViewManager.sMatrixDecompositionContext.translation[1]));
        view.setRotation((float)BaseViewManager.sMatrixDecompositionContext.rotationDegrees[2]);
        view.setRotationX((float)BaseViewManager.sMatrixDecompositionContext.rotationDegrees[0]);
        view.setRotationY((float)BaseViewManager.sMatrixDecompositionContext.rotationDegrees[1]);
        view.setScaleX((float)BaseViewManager.sMatrixDecompositionContext.scale[0]);
        view.setScaleY((float)BaseViewManager.sMatrixDecompositionContext.scale[1]);
    }
    
    @ReactProp(name = "accessibilityComponentType")
    public void setAccessibilityComponentType(final T t, final String s) {
        AccessibilityHelper.updateAccessibilityComponentType(t, s);
    }
    
    @ReactProp(name = "accessibilityLabel")
    public void setAccessibilityLabel(final T t, final String contentDescription) {
        t.setContentDescription((CharSequence)contentDescription);
    }
    
    @ReactProp(name = "accessibilityLiveRegion")
    public void setAccessibilityLiveRegion(final T t, final String s) {
        if (Build$VERSION.SDK_INT >= 19) {
            if (s == null || s.equals("none")) {
                t.setAccessibilityLiveRegion(0);
            }
            else {
                if (s.equals("polite")) {
                    t.setAccessibilityLiveRegion(1);
                    return;
                }
                if (s.equals("assertive")) {
                    t.setAccessibilityLiveRegion(2);
                }
            }
        }
    }
    
    @ReactProp(customType = "Color", defaultInt = 0, name = "backgroundColor")
    public void setBackgroundColor(final T t, final int backgroundColor) {
        t.setBackgroundColor(backgroundColor);
    }
    
    @ReactProp(name = "elevation")
    public void setElevation(final T t, final float n) {
        if (Build$VERSION.SDK_INT >= 21) {
            t.setElevation(PixelUtil.toPixelFromDIP(n));
        }
    }
    
    @ReactProp(name = "importantForAccessibility")
    public void setImportantForAccessibility(final T t, final String s) {
        if (s == null || s.equals("auto")) {
            t.setImportantForAccessibility(0);
        }
        else {
            if (s.equals("yes")) {
                t.setImportantForAccessibility(1);
                return;
            }
            if (s.equals("no")) {
                t.setImportantForAccessibility(2);
                return;
            }
            if (s.equals("no-hide-descendants")) {
                t.setImportantForAccessibility(4);
            }
        }
    }
    
    @ReactProp(defaultFloat = 1.0f, name = "opacity")
    public void setOpacity(final T t, final float alpha) {
        t.setAlpha(alpha);
    }
    
    @ReactProp(name = "renderToHardwareTextureAndroid")
    public void setRenderToHardwareTexture(final T t, final boolean b) {
        int n;
        if (b) {
            n = 2;
        }
        else {
            n = 0;
        }
        t.setLayerType(n, (Paint)null);
    }
    
    @ReactProp(name = "rotation")
    @Deprecated
    public void setRotation(final T t, final float rotation) {
        t.setRotation(rotation);
    }
    
    @ReactProp(defaultFloat = 1.0f, name = "scaleX")
    @Deprecated
    public void setScaleX(final T t, final float scaleX) {
        t.setScaleX(scaleX);
    }
    
    @ReactProp(defaultFloat = 1.0f, name = "scaleY")
    @Deprecated
    public void setScaleY(final T t, final float scaleY) {
        t.setScaleY(scaleY);
    }
    
    @ReactProp(name = "testID")
    public void setTestId(final T t, final String tag) {
        t.setTag((Object)tag);
    }
    
    @ReactProp(name = "transform")
    public void setTransform(final T t, final ReadableArray readableArray) {
        if (readableArray == null) {
            resetTransformProperty(t);
            return;
        }
        setTransformProperty(t, readableArray);
    }
    
    @ReactProp(defaultFloat = 0.0f, name = "translateX")
    @Deprecated
    public void setTranslateX(final T t, final float n) {
        t.setTranslationX(PixelUtil.toPixelFromDIP(n));
    }
    
    @ReactProp(defaultFloat = 0.0f, name = "translateY")
    @Deprecated
    public void setTranslateY(final T t, final float n) {
        t.setTranslationY(PixelUtil.toPixelFromDIP(n));
    }
    
    @ReactProp(name = "zIndex")
    public void setZIndex(final T t, final float n) {
        ViewGroupManager.setViewZIndex(t, Math.round(n));
    }
}
