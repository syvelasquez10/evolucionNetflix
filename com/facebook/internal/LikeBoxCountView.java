// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.view.View;
import android.graphics.Paint$Style;
import com.facebook.android.R$color;
import com.facebook.android.R$dimen;
import android.graphics.RectF;
import android.graphics.Path;
import android.graphics.Canvas;
import android.content.Context;
import android.widget.TextView;
import android.graphics.Paint;
import android.widget.FrameLayout;

public class LikeBoxCountView extends FrameLayout
{
    private int additionalTextPadding;
    private Paint borderPaint;
    private float borderRadius;
    private float caretHeight;
    private LikeBoxCountView$LikeBoxCountViewCaretPosition caretPosition;
    private float caretWidth;
    private TextView likeCountLabel;
    private int textPadding;
    
    public LikeBoxCountView(final Context context) {
        super(context);
        this.caretPosition = LikeBoxCountView$LikeBoxCountViewCaretPosition.LEFT;
        this.initialize(context);
    }
    
    private void drawBorder(final Canvas canvas, final float n, final float n2, final float n3, final float n4) {
        final Path path = new Path();
        final float n5 = this.borderRadius * 2.0f;
        path.addArc(new RectF(n, n2, n + n5, n2 + n5), -180.0f, 90.0f);
        if (this.caretPosition == LikeBoxCountView$LikeBoxCountViewCaretPosition.TOP) {
            path.lineTo((n3 - n - this.caretWidth) / 2.0f + n, n2);
            path.lineTo((n3 - n) / 2.0f + n, n2 - this.caretHeight);
            path.lineTo((n3 - n + this.caretWidth) / 2.0f + n, n2);
        }
        path.lineTo(n3 - this.borderRadius, n2);
        path.addArc(new RectF(n3 - n5, n2, n3, n2 + n5), -90.0f, 90.0f);
        if (this.caretPosition == LikeBoxCountView$LikeBoxCountViewCaretPosition.RIGHT) {
            path.lineTo(n3, (n4 - n2 - this.caretWidth) / 2.0f + n2);
            path.lineTo(this.caretHeight + n3, (n4 - n2) / 2.0f + n2);
            path.lineTo(n3, (n4 - n2 + this.caretWidth) / 2.0f + n2);
        }
        path.lineTo(n3, n4 - this.borderRadius);
        path.addArc(new RectF(n3 - n5, n4 - n5, n3, n4), 0.0f, 90.0f);
        if (this.caretPosition == LikeBoxCountView$LikeBoxCountViewCaretPosition.BOTTOM) {
            path.lineTo((n3 - n + this.caretWidth) / 2.0f + n, n4);
            path.lineTo((n3 - n) / 2.0f + n, this.caretHeight + n4);
            path.lineTo((n3 - n - this.caretWidth) / 2.0f + n, n4);
        }
        path.lineTo(this.borderRadius + n, n4);
        path.addArc(new RectF(n, n4 - n5, n5 + n, n4), 90.0f, 90.0f);
        if (this.caretPosition == LikeBoxCountView$LikeBoxCountViewCaretPosition.LEFT) {
            path.lineTo(n, (n4 - n2 + this.caretWidth) / 2.0f + n2);
            path.lineTo(n - this.caretHeight, (n4 - n2) / 2.0f + n2);
            path.lineTo(n, (n4 - n2 - this.caretWidth) / 2.0f + n2);
        }
        path.lineTo(n, this.borderRadius + n2);
        canvas.drawPath(path, this.borderPaint);
    }
    
    private void initialize(final Context context) {
        this.setWillNotDraw(false);
        this.caretHeight = this.getResources().getDimension(R$dimen.com_facebook_likeboxcountview_caret_height);
        this.caretWidth = this.getResources().getDimension(R$dimen.com_facebook_likeboxcountview_caret_width);
        this.borderRadius = this.getResources().getDimension(R$dimen.com_facebook_likeboxcountview_border_radius);
        (this.borderPaint = new Paint()).setColor(this.getResources().getColor(R$color.com_facebook_likeboxcountview_border_color));
        this.borderPaint.setStrokeWidth(this.getResources().getDimension(R$dimen.com_facebook_likeboxcountview_border_width));
        this.borderPaint.setStyle(Paint$Style.STROKE);
        this.initializeLikeCountLabel(context);
        this.addView((View)this.likeCountLabel);
        this.setCaretPosition(this.caretPosition);
    }
    
    private void initializeLikeCountLabel(final Context context) {
        (this.likeCountLabel = new TextView(context)).setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        this.likeCountLabel.setGravity(17);
        this.likeCountLabel.setTextSize(0, this.getResources().getDimension(R$dimen.com_facebook_likeboxcountview_text_size));
        this.likeCountLabel.setTextColor(this.getResources().getColor(R$color.com_facebook_likeboxcountview_text_color));
        this.textPadding = this.getResources().getDimensionPixelSize(R$dimen.com_facebook_likeboxcountview_text_padding);
        this.additionalTextPadding = this.getResources().getDimensionPixelSize(R$dimen.com_facebook_likeboxcountview_caret_height);
    }
    
    private void setAdditionalTextPadding(final int n, final int n2, final int n3, final int n4) {
        this.likeCountLabel.setPadding(this.textPadding + n, this.textPadding + n2, this.textPadding + n3, this.textPadding + n4);
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        int paddingTop = this.getPaddingTop();
        int paddingLeft = this.getPaddingLeft();
        int n = this.getWidth() - this.getPaddingRight();
        int n2 = this.getHeight() - this.getPaddingBottom();
        switch (LikeBoxCountView$1.$SwitchMap$com$facebook$internal$LikeBoxCountView$LikeBoxCountViewCaretPosition[this.caretPosition.ordinal()]) {
            case 4: {
                n2 -= (int)this.caretHeight;
                break;
            }
            case 1: {
                paddingLeft += (int)this.caretHeight;
                break;
            }
            case 2: {
                paddingTop += (int)this.caretHeight;
                break;
            }
            case 3: {
                n -= (int)this.caretHeight;
                break;
            }
        }
        this.drawBorder(canvas, paddingLeft, paddingTop, n, n2);
    }
    
    public void setCaretPosition(final LikeBoxCountView$LikeBoxCountViewCaretPosition caretPosition) {
        this.caretPosition = caretPosition;
        switch (LikeBoxCountView$1.$SwitchMap$com$facebook$internal$LikeBoxCountView$LikeBoxCountViewCaretPosition[caretPosition.ordinal()]) {
            default: {}
            case 1: {
                this.setAdditionalTextPadding(this.additionalTextPadding, 0, 0, 0);
            }
            case 2: {
                this.setAdditionalTextPadding(0, this.additionalTextPadding, 0, 0);
            }
            case 3: {
                this.setAdditionalTextPadding(0, 0, this.additionalTextPadding, 0);
            }
            case 4: {
                this.setAdditionalTextPadding(0, 0, 0, this.additionalTextPadding);
            }
        }
    }
    
    public void setText(final String text) {
        this.likeCountLabel.setText((CharSequence)text);
    }
}
