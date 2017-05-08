// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Canvas;

class CardViewJellybeanMr1$1 implements RoundRectDrawableWithShadow$RoundRectHelper
{
    final /* synthetic */ CardViewJellybeanMr1 this$0;
    
    CardViewJellybeanMr1$1(final CardViewJellybeanMr1 this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void drawRoundRect(final Canvas canvas, final RectF rectF, final float n, final Paint paint) {
        canvas.drawRoundRect(rectF, n, n, paint);
    }
}
