// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.widget;

import com.facebook.internal.LikeBoxCountView$LikeBoxCountViewCaretPosition;
import android.app.Activity;
import com.facebook.internal.LikeActionController$CreationCallback;
import android.content.res.TypedArray;
import com.facebook.android.R$styleable;
import android.widget.LinearLayout$LayoutParams;
import android.view.View$OnClickListener;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import com.facebook.android.R$color;
import com.facebook.android.R$dimen;
import android.content.Intent;
import com.facebook.internal.Utility;
import android.os.Bundle;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.content.Context;
import android.widget.TextView;
import com.facebook.internal.LikeButton;
import com.facebook.internal.LikeBoxCountView;
import com.facebook.internal.LikeActionController;
import android.widget.LinearLayout;
import android.content.BroadcastReceiver;
import android.widget.FrameLayout;

public class LikeView extends FrameLayout
{
    private static final int NO_FOREGROUND_COLOR = -1;
    private LikeView$AuxiliaryViewPosition auxiliaryViewPosition;
    private BroadcastReceiver broadcastReceiver;
    private LinearLayout containerView;
    private LikeView$LikeActionControllerCreationCallback creationCallback;
    private int edgePadding;
    private int foregroundColor;
    private LikeView$HorizontalAlignment horizontalAlignment;
    private int internalPadding;
    private LikeActionController likeActionController;
    private LikeBoxCountView likeBoxCountView;
    private LikeButton likeButton;
    private LikeView$Style likeViewStyle;
    private String objectId;
    private LikeView$OnErrorListener onErrorListener;
    private TextView socialSentenceView;
    
    public LikeView(final Context context) {
        super(context);
        this.likeViewStyle = LikeView$Style.DEFAULT;
        this.horizontalAlignment = LikeView$HorizontalAlignment.DEFAULT;
        this.auxiliaryViewPosition = LikeView$AuxiliaryViewPosition.DEFAULT;
        this.foregroundColor = -1;
        this.initialize(context);
    }
    
    public LikeView(final Context context, final AttributeSet set) {
        super(context, set);
        this.likeViewStyle = LikeView$Style.DEFAULT;
        this.horizontalAlignment = LikeView$HorizontalAlignment.DEFAULT;
        this.auxiliaryViewPosition = LikeView$AuxiliaryViewPosition.DEFAULT;
        this.foregroundColor = -1;
        this.parseAttributes(set);
        this.initialize(context);
    }
    
    private void associateWithLikeActionController(final LikeActionController likeActionController) {
        this.likeActionController = likeActionController;
        this.broadcastReceiver = new LikeView$LikeControllerBroadcastReceiver(this, null);
        final LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this.getContext());
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.facebook.sdk.LikeActionController.UPDATED");
        intentFilter.addAction("com.facebook.sdk.LikeActionController.DID_ERROR");
        intentFilter.addAction("com.facebook.sdk.LikeActionController.DID_RESET");
        instance.registerReceiver(this.broadcastReceiver, intentFilter);
    }
    
    private Bundle getAnalyticsParameters() {
        final Bundle bundle = new Bundle();
        bundle.putString("style", this.likeViewStyle.toString());
        bundle.putString("auxiliary_position", this.auxiliaryViewPosition.toString());
        bundle.putString("horizontal_alignment", this.horizontalAlignment.toString());
        bundle.putString("object_id", Utility.coerceValueIfNullOrEmpty(this.objectId, ""));
        return bundle;
    }
    
    public static boolean handleOnActivityResult(final Context context, final int n, final int n2, final Intent intent) {
        return LikeActionController.handleOnActivityResult(context, n, n2, intent);
    }
    
    private void initialize(final Context context) {
        this.edgePadding = this.getResources().getDimensionPixelSize(R$dimen.com_facebook_likeview_edge_padding);
        this.internalPadding = this.getResources().getDimensionPixelSize(R$dimen.com_facebook_likeview_internal_padding);
        if (this.foregroundColor == -1) {
            this.foregroundColor = this.getResources().getColor(R$color.com_facebook_likeview_text_color);
        }
        this.setBackgroundColor(0);
        (this.containerView = new LinearLayout(context)).setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        this.initializeLikeButton(context);
        this.initializeSocialSentenceView(context);
        this.initializeLikeCountView(context);
        this.containerView.addView((View)this.likeButton);
        this.containerView.addView((View)this.socialSentenceView);
        this.containerView.addView((View)this.likeBoxCountView);
        this.addView((View)this.containerView);
        this.setObjectIdForced(this.objectId);
        this.updateLikeStateAndLayout();
    }
    
    private void initializeLikeButton(final Context context) {
        (this.likeButton = new LikeButton(context, this.likeActionController != null && this.likeActionController.isObjectLiked())).setOnClickListener((View$OnClickListener)new LikeView$1(this));
        this.likeButton.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -2));
    }
    
    private void initializeLikeCountView(final Context context) {
        (this.likeBoxCountView = new LikeBoxCountView(context)).setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, -1));
    }
    
    private void initializeSocialSentenceView(final Context context) {
        (this.socialSentenceView = new TextView(context)).setTextSize(0, this.getResources().getDimension(R$dimen.com_facebook_likeview_text_size));
        this.socialSentenceView.setMaxLines(2);
        this.socialSentenceView.setTextColor(this.foregroundColor);
        this.socialSentenceView.setGravity(17);
        this.socialSentenceView.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-2, -1));
    }
    
    private void parseAttributes(final AttributeSet set) {
        if (set != null && this.getContext() != null) {
            final TypedArray obtainStyledAttributes = this.getContext().obtainStyledAttributes(set, R$styleable.com_facebook_like_view);
            if (obtainStyledAttributes != null) {
                this.objectId = Utility.coerceValueIfNullOrEmpty(obtainStyledAttributes.getString(1), null);
                this.likeViewStyle = LikeView$Style.fromInt(obtainStyledAttributes.getInt(2, LikeView$Style.DEFAULT.getValue()));
                if (this.likeViewStyle == null) {
                    throw new IllegalArgumentException("Unsupported value for LikeView 'style'");
                }
                this.auxiliaryViewPosition = LikeView$AuxiliaryViewPosition.fromInt(obtainStyledAttributes.getInt(3, LikeView$AuxiliaryViewPosition.DEFAULT.getValue()));
                if (this.auxiliaryViewPosition == null) {
                    throw new IllegalArgumentException("Unsupported value for LikeView 'auxiliary_view_position'");
                }
                this.horizontalAlignment = LikeView$HorizontalAlignment.fromInt(obtainStyledAttributes.getInt(4, LikeView$HorizontalAlignment.DEFAULT.getValue()));
                if (this.horizontalAlignment == null) {
                    throw new IllegalArgumentException("Unsupported value for LikeView 'horizontal_alignment'");
                }
                this.foregroundColor = obtainStyledAttributes.getColor(0, -1);
                obtainStyledAttributes.recycle();
            }
        }
    }
    
    private void setObjectIdForced(final String objectId) {
        this.tearDownObjectAssociations();
        this.objectId = objectId;
        if (Utility.isNullOrEmpty(objectId)) {
            return;
        }
        this.creationCallback = new LikeView$LikeActionControllerCreationCallback(this, null);
        LikeActionController.getControllerForObjectId(this.getContext(), objectId, this.creationCallback);
    }
    
    private void tearDownObjectAssociations() {
        if (this.broadcastReceiver != null) {
            LocalBroadcastManager.getInstance(this.getContext()).unregisterReceiver(this.broadcastReceiver);
            this.broadcastReceiver = null;
        }
        if (this.creationCallback != null) {
            this.creationCallback.cancel();
            this.creationCallback = null;
        }
        this.likeActionController = null;
    }
    
    private void toggleLike() {
        if (this.likeActionController != null) {
            this.likeActionController.toggleLike((Activity)this.getContext(), this.getAnalyticsParameters());
        }
    }
    
    private void updateBoxCountCaretPosition() {
        switch (LikeView$2.$SwitchMap$com$facebook$widget$LikeView$AuxiliaryViewPosition[this.auxiliaryViewPosition.ordinal()]) {
            default: {}
            case 1: {
                this.likeBoxCountView.setCaretPosition(LikeBoxCountView$LikeBoxCountViewCaretPosition.BOTTOM);
            }
            case 2: {
                this.likeBoxCountView.setCaretPosition(LikeBoxCountView$LikeBoxCountViewCaretPosition.TOP);
            }
            case 3: {
                final LikeBoxCountView likeBoxCountView = this.likeBoxCountView;
                LikeBoxCountView$LikeBoxCountViewCaretPosition caretPosition;
                if (this.horizontalAlignment == LikeView$HorizontalAlignment.RIGHT) {
                    caretPosition = LikeBoxCountView$LikeBoxCountViewCaretPosition.RIGHT;
                }
                else {
                    caretPosition = LikeBoxCountView$LikeBoxCountViewCaretPosition.LEFT;
                }
                likeBoxCountView.setCaretPosition(caretPosition);
            }
        }
    }
    
    private void updateLayout() {
        final boolean b = true;
        final FrameLayout$LayoutParams frameLayout$LayoutParams = (FrameLayout$LayoutParams)this.containerView.getLayoutParams();
        final LinearLayout$LayoutParams linearLayout$LayoutParams = (LinearLayout$LayoutParams)this.likeButton.getLayoutParams();
        int n;
        if (this.horizontalAlignment == LikeView$HorizontalAlignment.LEFT) {
            n = 3;
        }
        else if (this.horizontalAlignment == LikeView$HorizontalAlignment.CENTER) {
            n = 1;
        }
        else {
            n = 5;
        }
        frameLayout$LayoutParams.gravity = (n | 0x30);
        linearLayout$LayoutParams.gravity = n;
        this.socialSentenceView.setVisibility(8);
        this.likeBoxCountView.setVisibility(8);
        Object o;
        if (this.likeViewStyle == LikeView$Style.STANDARD && this.likeActionController != null && !Utility.isNullOrEmpty(this.likeActionController.getSocialSentence())) {
            o = this.socialSentenceView;
        }
        else {
            if (this.likeViewStyle != LikeView$Style.BOX_COUNT || this.likeActionController == null || Utility.isNullOrEmpty(this.likeActionController.getLikeCountString())) {
                return;
            }
            this.updateBoxCountCaretPosition();
            o = this.likeBoxCountView;
        }
        ((View)o).setVisibility(0);
        ((LinearLayout$LayoutParams)((View)o).getLayoutParams()).gravity = n;
        final LinearLayout containerView = this.containerView;
        int orientation = b ? 1 : 0;
        if (this.auxiliaryViewPosition == LikeView$AuxiliaryViewPosition.INLINE) {
            orientation = 0;
        }
        containerView.setOrientation(orientation);
        if (this.auxiliaryViewPosition == LikeView$AuxiliaryViewPosition.TOP || (this.auxiliaryViewPosition == LikeView$AuxiliaryViewPosition.INLINE && this.horizontalAlignment == LikeView$HorizontalAlignment.RIGHT)) {
            this.containerView.removeView((View)this.likeButton);
            this.containerView.addView((View)this.likeButton);
        }
        else {
            this.containerView.removeView((View)o);
            this.containerView.addView((View)o);
        }
        switch (LikeView$2.$SwitchMap$com$facebook$widget$LikeView$AuxiliaryViewPosition[this.auxiliaryViewPosition.ordinal()]) {
            default: {}
            case 1: {
                ((View)o).setPadding(this.edgePadding, this.edgePadding, this.edgePadding, this.internalPadding);
            }
            case 2: {
                ((View)o).setPadding(this.edgePadding, this.internalPadding, this.edgePadding, this.edgePadding);
            }
            case 3: {
                if (this.horizontalAlignment == LikeView$HorizontalAlignment.RIGHT) {
                    ((View)o).setPadding(this.edgePadding, this.edgePadding, this.internalPadding, this.edgePadding);
                    return;
                }
                ((View)o).setPadding(this.internalPadding, this.edgePadding, this.edgePadding, this.edgePadding);
            }
        }
    }
    
    private void updateLikeStateAndLayout() {
        if (this.likeActionController == null) {
            this.likeButton.setLikeState(false);
            this.socialSentenceView.setText((CharSequence)null);
            this.likeBoxCountView.setText(null);
        }
        else {
            this.likeButton.setLikeState(this.likeActionController.isObjectLiked());
            this.socialSentenceView.setText((CharSequence)this.likeActionController.getSocialSentence());
            this.likeBoxCountView.setText(this.likeActionController.getLikeCountString());
        }
        this.updateLayout();
    }
    
    public LikeView$OnErrorListener getOnErrorListener() {
        return this.onErrorListener;
    }
    
    protected void onDetachedFromWindow() {
        this.setObjectId(null);
        super.onDetachedFromWindow();
    }
    
    public void setAuxiliaryViewPosition(LikeView$AuxiliaryViewPosition default1) {
        if (default1 == null) {
            default1 = LikeView$AuxiliaryViewPosition.DEFAULT;
        }
        if (this.auxiliaryViewPosition != default1) {
            this.auxiliaryViewPosition = default1;
            this.updateLayout();
        }
    }
    
    public void setForegroundColor(final int textColor) {
        if (this.foregroundColor != textColor) {
            this.socialSentenceView.setTextColor(textColor);
        }
    }
    
    public void setHorizontalAlignment(LikeView$HorizontalAlignment default1) {
        if (default1 == null) {
            default1 = LikeView$HorizontalAlignment.DEFAULT;
        }
        if (this.horizontalAlignment != default1) {
            this.horizontalAlignment = default1;
            this.updateLayout();
        }
    }
    
    public void setLikeViewStyle(LikeView$Style default1) {
        if (default1 == null) {
            default1 = LikeView$Style.DEFAULT;
        }
        if (this.likeViewStyle != default1) {
            this.likeViewStyle = default1;
            this.updateLayout();
        }
    }
    
    public void setObjectId(String coerceValueIfNullOrEmpty) {
        coerceValueIfNullOrEmpty = Utility.coerceValueIfNullOrEmpty(coerceValueIfNullOrEmpty, null);
        if (!Utility.areObjectsEqual(coerceValueIfNullOrEmpty, this.objectId)) {
            this.setObjectIdForced(coerceValueIfNullOrEmpty);
            this.updateLikeStateAndLayout();
        }
    }
    
    public void setOnErrorListener(final LikeView$OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }
}
