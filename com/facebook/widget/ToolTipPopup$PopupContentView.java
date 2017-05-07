// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.android.R$id;
import android.view.ViewGroup;
import com.facebook.android.R$layout;
import android.view.LayoutInflater;
import android.content.Context;
import android.widget.ImageView;
import android.view.View;
import android.widget.FrameLayout;

class ToolTipPopup$PopupContentView extends FrameLayout
{
    private View bodyFrame;
    private ImageView bottomArrow;
    final /* synthetic */ ToolTipPopup this$0;
    private ImageView topArrow;
    private ImageView xOut;
    
    public ToolTipPopup$PopupContentView(final ToolTipPopup this$0, final Context context) {
        this.this$0 = this$0;
        super(context);
        this.init();
    }
    
    private void init() {
        LayoutInflater.from(this.getContext()).inflate(R$layout.com_facebook_tooltip_bubble, (ViewGroup)this);
        this.topArrow = (ImageView)this.findViewById(R$id.com_facebook_tooltip_bubble_view_top_pointer);
        this.bottomArrow = (ImageView)this.findViewById(R$id.com_facebook_tooltip_bubble_view_bottom_pointer);
        this.bodyFrame = this.findViewById(R$id.com_facebook_body_frame);
        this.xOut = (ImageView)this.findViewById(R$id.com_facebook_button_xout);
    }
    
    public void onMeasure(final int n, final int n2) {
        super.onMeasure(n, n2);
    }
    
    public void showBottomArrow() {
        this.topArrow.setVisibility(4);
        this.bottomArrow.setVisibility(0);
    }
    
    public void showTopArrow() {
        this.topArrow.setVisibility(0);
        this.bottomArrow.setVisibility(4);
    }
}
