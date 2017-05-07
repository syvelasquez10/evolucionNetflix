// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.Map;
import java.util.List;
import android.widget.ImageView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import android.os.Parcelable;
import android.graphics.RectF;
import android.view.View;
import android.graphics.Matrix;

public abstract class SharedElementCallback
{
    private Matrix mTempMatrix;
    
    public Parcelable onCaptureSharedElementSnapshot(final View view, final Matrix matrix, final RectF rectF) {
        final int round = Math.round(rectF.width());
        final int round2 = Math.round(rectF.height());
        Object bitmap;
        final Object o = bitmap = null;
        if (round > 0) {
            bitmap = o;
            if (round2 > 0) {
                if (this.mTempMatrix == null) {
                    this.mTempMatrix = new Matrix();
                }
                this.mTempMatrix.set(matrix);
                this.mTempMatrix.postTranslate(-rectF.left, -rectF.top);
                bitmap = Bitmap.createBitmap(round, round2, Bitmap$Config.ARGB_8888);
                final Canvas canvas = new Canvas((Bitmap)bitmap);
                canvas.concat(this.mTempMatrix);
                view.draw(canvas);
            }
        }
        return (Parcelable)bitmap;
    }
    
    public View onCreateSnapshotView(final Context context, final Parcelable parcelable) {
        Object o = null;
        if (parcelable instanceof Bitmap) {
            final Bitmap imageBitmap = (Bitmap)parcelable;
            o = new ImageView(context);
            ((ImageView)o).setImageBitmap(imageBitmap);
        }
        return (View)o;
    }
    
    public void onMapSharedElements(final List<String> list, final Map<String, View> map) {
    }
    
    public void onRejectSharedElements(final List<View> list) {
    }
    
    public void onSharedElementEnd(final List<String> list, final List<View> list2, final List<View> list3) {
    }
    
    public void onSharedElementStart(final List<String> list, final List<View> list2, final List<View> list3) {
    }
}
