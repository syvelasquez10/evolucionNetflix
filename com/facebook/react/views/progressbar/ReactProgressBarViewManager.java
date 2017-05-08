// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.progressbar;

import com.facebook.react.uimanager.annotations.ReactProp;
import android.view.View;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import android.content.Context;
import com.facebook.react.uimanager.BaseViewManager;

public class ReactProgressBarViewManager extends BaseViewManager<ProgressBarContainerView, ProgressBarShadowNode>
{
    static final String DEFAULT_STYLE = "Normal";
    static final String PROP_ANIMATING = "animating";
    static final String PROP_INDETERMINATE = "indeterminate";
    static final String PROP_PROGRESS = "progress";
    static final String PROP_STYLE = "styleAttr";
    protected static final String REACT_CLASS = "AndroidProgressBar";
    private static Object sProgressBarCtorLock;
    
    static {
        ReactProgressBarViewManager.sProgressBarCtorLock = new Object();
    }
    
    public static ProgressBar createProgressBar(final Context context, final int n) {
        synchronized (ReactProgressBarViewManager.sProgressBarCtorLock) {
            return new ProgressBar(context, (AttributeSet)null, n);
        }
    }
    
    static int getStyleFromString(final String s) {
        if (s == null) {
            throw new JSApplicationIllegalArgumentException("ProgressBar needs to have a style, null received");
        }
        if (s.equals("Horizontal")) {
            return 16842872;
        }
        if (s.equals("Small")) {
            return 16842873;
        }
        if (s.equals("Large")) {
            return 16842874;
        }
        if (s.equals("Inverse")) {
            return 16843399;
        }
        if (s.equals("SmallInverse")) {
            return 16843400;
        }
        if (s.equals("LargeInverse")) {
            return 16843401;
        }
        if (s.equals("Normal")) {
            return 16842871;
        }
        throw new JSApplicationIllegalArgumentException("Unknown ProgressBar style: " + s);
    }
    
    @Override
    public ProgressBarShadowNode createShadowNodeInstance() {
        return new ProgressBarShadowNode();
    }
    
    @Override
    protected ProgressBarContainerView createViewInstance(final ThemedReactContext themedReactContext) {
        return new ProgressBarContainerView((Context)themedReactContext);
    }
    
    @Override
    public String getName() {
        return "AndroidProgressBar";
    }
    
    @Override
    public Class<ProgressBarShadowNode> getShadowNodeClass() {
        return ProgressBarShadowNode.class;
    }
    
    @Override
    protected void onAfterUpdateTransaction(final ProgressBarContainerView progressBarContainerView) {
        progressBarContainerView.apply();
    }
    
    @ReactProp(name = "animating")
    public void setAnimating(final ProgressBarContainerView progressBarContainerView, final boolean animating) {
        progressBarContainerView.setAnimating(animating);
    }
    
    @ReactProp(customType = "Color", name = "color")
    public void setColor(final ProgressBarContainerView progressBarContainerView, final Integer color) {
        progressBarContainerView.setColor(color);
    }
    
    @ReactProp(name = "indeterminate")
    public void setIndeterminate(final ProgressBarContainerView progressBarContainerView, final boolean indeterminate) {
        progressBarContainerView.setIndeterminate(indeterminate);
    }
    
    @ReactProp(name = "progress")
    public void setProgress(final ProgressBarContainerView progressBarContainerView, final double progress) {
        progressBarContainerView.setProgress(progress);
    }
    
    @ReactProp(name = "styleAttr")
    public void setStyle(final ProgressBarContainerView progressBarContainerView, final String style) {
        progressBarContainerView.setStyle(style);
    }
    
    @Override
    public void updateExtraData(final ProgressBarContainerView progressBarContainerView, final Object o) {
    }
}
