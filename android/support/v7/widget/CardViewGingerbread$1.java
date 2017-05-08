// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Canvas;

class CardViewGingerbread$1 implements RoundRectDrawableWithShadow$RoundRectHelper
{
    final /* synthetic */ CardViewGingerbread this$0;
    
    CardViewGingerbread$1(final CardViewGingerbread this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void drawRoundRect(final Canvas canvas, final RectF rectF, final float n, final Paint paint) {
        final float n2 = 2.0f * n;
        final float n3 = rectF.width() - n2 - 1.0f;
        final float height = rectF.height();
        if (n >= 1.0f) {
            final float n4 = n + 0.5f;
            this.this$0.sCornerRect.set(-n4, -n4, n4, n4);
            final int save = canvas.save();
            canvas.translate(rectF.left + n4, rectF.top + n4);
            canvas.drawArc(this.this$0.sCornerRect, 180.0f, 90.0f, true, paint);
            canvas.translate(n3, 0.0f);
            canvas.rotate(90.0f);
            canvas.drawArc(this.this$0.sCornerRect, 180.0f, 90.0f, true, paint);
            canvas.translate(height - n2 - 1.0f, 0.0f);
            canvas.rotate(90.0f);
            canvas.drawArc(this.this$0.sCornerRect, 180.0f, 90.0f, true, paint);
            canvas.translate(n3, 0.0f);
            canvas.rotate(90.0f);
            canvas.drawArc(this.this$0.sCornerRect, 180.0f, 90.0f, true, paint);
            canvas.restoreToCount(save);
            canvas.drawRect(rectF.left + n4 - 1.0f, rectF.top, 1.0f + (rectF.right - n4), rectF.top + n4, paint);
            canvas.drawRect(rectF.left + n4 - 1.0f, rectF.bottom - n4, 1.0f + (rectF.right - n4), rectF.bottom, paint);
        }
        canvas.drawRect(rectF.left, rectF.top + n, rectF.right, rectF.bottom - n, paint);
    }
}
