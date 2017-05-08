// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view.animation;

import android.graphics.PathMeasure;
import android.graphics.Path;
import android.view.animation.Interpolator;

class PathInterpolatorGingerbread implements Interpolator
{
    private static final float PRECISION = 0.002f;
    private final float[] mX;
    private final float[] mY;
    
    public PathInterpolatorGingerbread(final float n, final float n2) {
        this(createQuad(n, n2));
    }
    
    public PathInterpolatorGingerbread(final float n, final float n2, final float n3, final float n4) {
        this(createCubic(n, n2, n3, n4));
    }
    
    public PathInterpolatorGingerbread(final Path path) {
        final PathMeasure pathMeasure = new PathMeasure(path, false);
        final float length = pathMeasure.getLength();
        final int n = (int)(length / 0.002f) + 1;
        this.mX = new float[n];
        this.mY = new float[n];
        final float[] array = new float[2];
        for (int i = 0; i < n; ++i) {
            pathMeasure.getPosTan(i * length / (n - 1), array, (float[])null);
            this.mX[i] = array[0];
            this.mY[i] = array[1];
        }
    }
    
    private static Path createCubic(final float n, final float n2, final float n3, final float n4) {
        final Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(n, n2, n3, n4, 1.0f, 1.0f);
        return path;
    }
    
    private static Path createQuad(final float n, final float n2) {
        final Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(n, n2, 1.0f, 1.0f);
        return path;
    }
    
    public float getInterpolation(float n) {
        float n2 = 1.0f;
        if (n <= 0.0f) {
            n2 = 0.0f;
        }
        else if (n < 1.0f) {
            int n3 = 0;
            int n4 = this.mX.length - 1;
            while (n4 - n3 > 1) {
                final int n5 = (n3 + n4) / 2;
                if (n < this.mX[n5]) {
                    n4 = n5;
                }
                else {
                    n3 = n5;
                }
            }
            final float n6 = this.mX[n4] - this.mX[n3];
            if (n6 == 0.0f) {
                return this.mY[n3];
            }
            n = (n - this.mX[n3]) / n6;
            final float n7 = this.mY[n3];
            return n * (this.mY[n4] - n7) + n7;
        }
        return n2;
    }
}
