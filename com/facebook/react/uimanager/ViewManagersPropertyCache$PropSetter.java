// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.uimanager;

import android.view.View;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.common.logging.FLog;
import java.util.Arrays;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.lang.reflect.Method;

abstract class ViewManagersPropertyCache$PropSetter
{
    private static final Object[] SHADOW_ARGS;
    private static final Object[] SHADOW_GROUP_ARGS;
    private static final Object[] VIEW_MGR_ARGS;
    private static final Object[] VIEW_MGR_GROUP_ARGS;
    protected final Integer mIndex;
    protected final String mPropName;
    protected final String mPropType;
    protected final Method mSetter;
    
    static {
        VIEW_MGR_ARGS = new Object[2];
        VIEW_MGR_GROUP_ARGS = new Object[3];
        SHADOW_ARGS = new Object[1];
        SHADOW_GROUP_ARGS = new Object[2];
    }
    
    private ViewManagersPropertyCache$PropSetter(final ReactProp reactProp, String customType, final Method mSetter) {
        this.mPropName = reactProp.name();
        if (!"__default_type__".equals(reactProp.customType())) {
            customType = reactProp.customType();
        }
        this.mPropType = customType;
        this.mSetter = mSetter;
        this.mIndex = null;
    }
    
    private ViewManagersPropertyCache$PropSetter(final ReactPropGroup reactPropGroup, String customType, final Method mSetter, final int n) {
        this.mPropName = reactPropGroup.names()[n];
        if (!"__default_type__".equals(reactPropGroup.customType())) {
            customType = reactPropGroup.customType();
        }
        this.mPropType = customType;
        this.mSetter = mSetter;
        this.mIndex = n;
    }
    
    protected abstract Object extractProperty(final ReactStylesDiffMap p0);
    
    public String getPropName() {
        return this.mPropName;
    }
    
    public String getPropType() {
        return this.mPropType;
    }
    
    public void updateShadowNodeProp(final ReactShadowNode reactShadowNode, final ReactStylesDiffMap reactStylesDiffMap) {
        try {
            if (this.mIndex == null) {
                ViewManagersPropertyCache$PropSetter.SHADOW_ARGS[0] = this.extractProperty(reactStylesDiffMap);
                this.mSetter.invoke(reactShadowNode, ViewManagersPropertyCache$PropSetter.SHADOW_ARGS);
                Arrays.fill(ViewManagersPropertyCache$PropSetter.SHADOW_ARGS, null);
                return;
            }
            ViewManagersPropertyCache$PropSetter.SHADOW_GROUP_ARGS[0] = this.mIndex;
            ViewManagersPropertyCache$PropSetter.SHADOW_GROUP_ARGS[1] = this.extractProperty(reactStylesDiffMap);
            this.mSetter.invoke(reactShadowNode, ViewManagersPropertyCache$PropSetter.SHADOW_GROUP_ARGS);
            Arrays.fill(ViewManagersPropertyCache$PropSetter.SHADOW_GROUP_ARGS, null);
        }
        catch (Throwable t) {
            FLog.e(ViewManager.class, "Error while updating prop " + this.mPropName, t);
            throw new JSApplicationIllegalArgumentException("Error while updating property '" + this.mPropName + "' in shadow node of type: " + reactShadowNode.getViewClass(), t);
        }
    }
    
    public void updateViewProp(final ViewManager viewManager, final View view, final ReactStylesDiffMap reactStylesDiffMap) {
        try {
            if (this.mIndex == null) {
                ViewManagersPropertyCache$PropSetter.VIEW_MGR_ARGS[0] = view;
                ViewManagersPropertyCache$PropSetter.VIEW_MGR_ARGS[1] = this.extractProperty(reactStylesDiffMap);
                this.mSetter.invoke(viewManager, ViewManagersPropertyCache$PropSetter.VIEW_MGR_ARGS);
                Arrays.fill(ViewManagersPropertyCache$PropSetter.VIEW_MGR_ARGS, null);
                return;
            }
            ViewManagersPropertyCache$PropSetter.VIEW_MGR_GROUP_ARGS[0] = view;
            ViewManagersPropertyCache$PropSetter.VIEW_MGR_GROUP_ARGS[1] = this.mIndex;
            ViewManagersPropertyCache$PropSetter.VIEW_MGR_GROUP_ARGS[2] = this.extractProperty(reactStylesDiffMap);
            this.mSetter.invoke(viewManager, ViewManagersPropertyCache$PropSetter.VIEW_MGR_GROUP_ARGS);
            Arrays.fill(ViewManagersPropertyCache$PropSetter.VIEW_MGR_GROUP_ARGS, null);
        }
        catch (Throwable t) {
            FLog.e(ViewManager.class, "Error while updating prop " + this.mPropName, t);
            throw new JSApplicationIllegalArgumentException("Error while updating property '" + this.mPropName + "' of a view managed by: " + viewManager.getName(), t);
        }
    }
}
