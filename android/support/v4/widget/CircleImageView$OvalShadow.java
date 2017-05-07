// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.graphics.Canvas;
import android.graphics.Shader;
import android.graphics.Shader$TileMode;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.drawable.shapes.OvalShape;

class CircleImageView$OvalShadow extends OvalShape
{
    private int mCircleDiameter;
    private RadialGradient mRadialGradient;
    private Paint mShadowPaint;
    private int mShadowRadius;
    final /* synthetic */ CircleImageView this$0;
    
    public CircleImageView$OvalShadow(final CircleImageView this$0, final int mShadowRadius, final int mCircleDiameter) {
        this.this$0 = this$0;
        this.mShadowPaint = new Paint();
        this.mShadowRadius = mShadowRadius;
        this.mCircleDiameter = mCircleDiameter;
        this.mRadialGradient = new RadialGradient((float)(this.mCircleDiameter / 2), (float)(this.mCircleDiameter / 2), (float)this.mShadowRadius, new int[] { 1023410176, 0 }, (float[])null, Shader$TileMode.CLAMP);
        this.mShadowPaint.setShader((Shader)this.mRadialGradient);
    }
    
    public void draw(final Canvas canvas, final Paint paint) {
        final int width = this.this$0.getWidth();
        final int height = this.this$0.getHeight();
        canvas.drawCircle((float)(width / 2), (float)(height / 2), (float)(this.mCircleDiameter / 2 + this.mShadowRadius), this.mShadowPaint);
        canvas.drawCircle((float)(width / 2), (float)(height / 2), (float)(this.mCircleDiameter / 2), paint);
    }
}
