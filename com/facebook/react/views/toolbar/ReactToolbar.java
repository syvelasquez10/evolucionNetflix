// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.toolbar;

import android.view.Menu;
import com.facebook.react.bridge.ReadableArray;
import android.view.MenuItem;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.drawee.controller.ControllerListener;
import android.net.Uri;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.react.uimanager.PixelUtil;
import com.facebook.react.bridge.ReadableMap;
import android.graphics.drawable.Drawable;
import com.facebook.drawee.drawable.ScalingUtils$ScaleType;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import android.content.Context;
import com.facebook.drawee.view.DraweeHolder;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.MultiDraweeHolder;
import android.support.v7.widget.Toolbar;

public class ReactToolbar extends Toolbar
{
    private final MultiDraweeHolder<GenericDraweeHierarchy> mActionsHolder;
    private final Runnable mLayoutRunnable;
    private ReactToolbar$IconControllerListener mLogoControllerListener;
    private final DraweeHolder mLogoHolder;
    private ReactToolbar$IconControllerListener mNavIconControllerListener;
    private final DraweeHolder mNavIconHolder;
    private ReactToolbar$IconControllerListener mOverflowIconControllerListener;
    private final DraweeHolder mOverflowIconHolder;
    
    public ReactToolbar(final Context context) {
        super(context);
        this.mActionsHolder = new MultiDraweeHolder<GenericDraweeHierarchy>();
        this.mLayoutRunnable = new ReactToolbar$4(this);
        this.mLogoHolder = DraweeHolder.create(this.createDraweeHierarchy(), context);
        this.mNavIconHolder = DraweeHolder.create(this.createDraweeHierarchy(), context);
        this.mOverflowIconHolder = DraweeHolder.create(this.createDraweeHierarchy(), context);
        this.mLogoControllerListener = new ReactToolbar$1(this, this.mLogoHolder);
        this.mNavIconControllerListener = new ReactToolbar$2(this, this.mNavIconHolder);
        this.mOverflowIconControllerListener = new ReactToolbar$3(this, this.mOverflowIconHolder);
    }
    
    private void attachDraweeHolders() {
        this.mLogoHolder.onAttach();
        this.mNavIconHolder.onAttach();
        this.mOverflowIconHolder.onAttach();
        this.mActionsHolder.onAttach();
    }
    
    private GenericDraweeHierarchy createDraweeHierarchy() {
        return new GenericDraweeHierarchyBuilder(this.getResources()).setActualImageScaleType(ScalingUtils$ScaleType.FIT_CENTER).setFadeDuration(0).build();
    }
    
    private void detachDraweeHolders() {
        this.mLogoHolder.onDetach();
        this.mNavIconHolder.onDetach();
        this.mOverflowIconHolder.onDetach();
        this.mActionsHolder.onDetach();
    }
    
    private Drawable getDrawableByName(final String s) {
        if (this.getDrawableResourceByName(s) != 0) {
            return this.getResources().getDrawable(this.getDrawableResourceByName(s));
        }
        return null;
    }
    
    private int getDrawableResourceByName(final String s) {
        return this.getResources().getIdentifier(s, "drawable", this.getContext().getPackageName());
    }
    
    private ReactToolbar$IconImageInfo getIconImageInfo(final ReadableMap readableMap) {
        if (readableMap.hasKey("width") && readableMap.hasKey("height")) {
            return new ReactToolbar$IconImageInfo(Math.round(PixelUtil.toPixelFromDIP(readableMap.getInt("width"))), Math.round(PixelUtil.toPixelFromDIP(readableMap.getInt("height"))));
        }
        return null;
    }
    
    private void setIconSource(final ReadableMap readableMap, final ReactToolbar$IconControllerListener controllerListener, final DraweeHolder draweeHolder) {
        String string;
        if (readableMap != null) {
            string = readableMap.getString("uri");
        }
        else {
            string = null;
        }
        if (string == null) {
            controllerListener.setIconImageInfo(null);
            controllerListener.setDrawable(null);
            return;
        }
        if (string.startsWith("http://") || string.startsWith("https://") || string.startsWith("file://")) {
            controllerListener.setIconImageInfo(this.getIconImageInfo(readableMap));
            draweeHolder.setController(Fresco.newDraweeControllerBuilder().setUri(Uri.parse(string)).setControllerListener(controllerListener).setOldController(draweeHolder.getController()).build());
            draweeHolder.getTopLevelDrawable().setVisible(true, true);
            return;
        }
        controllerListener.setDrawable(this.getDrawableByName(string));
    }
    
    private void setMenuItemIcon(final MenuItem menuItem, final ReadableMap readableMap) {
        final DraweeHolder<GenericDraweeHierarchy> create = DraweeHolder.create(this.createDraweeHierarchy(), this.getContext());
        final ReactToolbar$ActionIconControllerListener reactToolbar$ActionIconControllerListener = new ReactToolbar$ActionIconControllerListener(this, menuItem, create);
        reactToolbar$ActionIconControllerListener.setIconImageInfo(this.getIconImageInfo(readableMap));
        this.setIconSource(readableMap, reactToolbar$ActionIconControllerListener, create);
        this.mActionsHolder.add(create);
    }
    
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.attachDraweeHolders();
    }
    
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.detachDraweeHolders();
    }
    
    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        this.attachDraweeHolders();
    }
    
    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        this.detachDraweeHolders();
    }
    
    public void requestLayout() {
        super.requestLayout();
        this.post(this.mLayoutRunnable);
    }
    
    void setActions(final ReadableArray readableArray) {
        final Menu menu = this.getMenu();
        menu.clear();
        this.mActionsHolder.clear();
        if (readableArray != null) {
            for (int i = 0; i < readableArray.size(); ++i) {
                final ReadableMap map = readableArray.getMap(i);
                final MenuItem add = menu.add(0, 0, i, (CharSequence)map.getString("title"));
                if (map.hasKey("icon")) {
                    this.setMenuItemIcon(add, map.getMap("icon"));
                }
                int int1;
                if (map.hasKey("show")) {
                    int1 = map.getInt("show");
                }
                else {
                    int1 = 0;
                }
                int showAsAction = int1;
                if (map.hasKey("showWithText")) {
                    showAsAction = int1;
                    if (map.getBoolean("showWithText")) {
                        showAsAction = (int1 | 0x4);
                    }
                }
                add.setShowAsAction(showAsAction);
            }
        }
    }
    
    void setLogoSource(final ReadableMap readableMap) {
        this.setIconSource(readableMap, this.mLogoControllerListener, this.mLogoHolder);
    }
    
    void setNavIconSource(final ReadableMap readableMap) {
        this.setIconSource(readableMap, this.mNavIconControllerListener, this.mNavIconHolder);
    }
    
    void setOverflowIconSource(final ReadableMap readableMap) {
        this.setIconSource(readableMap, this.mOverflowIconControllerListener, this.mOverflowIconHolder);
    }
}
