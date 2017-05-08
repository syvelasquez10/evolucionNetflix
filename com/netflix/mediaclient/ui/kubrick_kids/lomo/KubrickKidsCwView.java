// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.lomo;

import com.netflix.mediaclient.util.ViewUtils;
import android.widget.TextView;
import android.view.View;
import com.netflix.mediaclient.android.widget.PressedStateHandler;
import android.util.AttributeSet;
import android.content.Context;
import com.netflix.mediaclient.android.widget.PressAnimationFrameLayout;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickHighDensityCwView;

public class KubrickKidsCwView extends KubrickHighDensityCwView
{
    private PressAnimationFrameLayout pressableViewGroup;
    
    public KubrickKidsCwView(final Context context) {
        super(context);
    }
    
    public KubrickKidsCwView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    public KubrickKidsCwView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
    }
    
    @Override
    protected int getLayoutId() {
        return 2130903134;
    }
    
    @Override
    protected PressedStateHandler getPressableStateHandler() {
        return this.pressableViewGroup.getPressedStateHandler();
    }
    
    public PressAnimationFrameLayout getPressableView() {
        return this.pressableViewGroup;
    }
    
    @Override
    protected void init() {
        super.init();
        this.img.setPressedStateHandlerEnabled(false);
        this.pressableViewGroup = (PressAnimationFrameLayout)this.findViewById(2131624298);
        if (this.infoIcon instanceof TextView) {
            ViewUtils.removeShadow((TextView)this.infoIcon);
        }
    }
}
