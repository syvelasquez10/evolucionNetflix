// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.app;

import android.graphics.drawable.BitmapDrawable;
import java.util.Iterator;
import android.graphics.Canvas;
import android.util.AttributeSet;
import java.util.ArrayList;
import android.content.Context;
import java.util.List;
import android.widget.ListView;

final class OverlayListView extends ListView
{
    private final List<OverlayListView$OverlayObject> mOverlayObjects;
    
    public OverlayListView(final Context context) {
        super(context);
        this.mOverlayObjects = new ArrayList<OverlayListView$OverlayObject>();
    }
    
    public OverlayListView(final Context context, final AttributeSet set) {
        super(context, set);
        this.mOverlayObjects = new ArrayList<OverlayListView$OverlayObject>();
    }
    
    public OverlayListView(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.mOverlayObjects = new ArrayList<OverlayListView$OverlayObject>();
    }
    
    public void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        if (this.mOverlayObjects.size() > 0) {
            final Iterator<OverlayListView$OverlayObject> iterator = this.mOverlayObjects.iterator();
            while (iterator.hasNext()) {
                final OverlayListView$OverlayObject overlayListView$OverlayObject = iterator.next();
                final BitmapDrawable bitmapDrawable = overlayListView$OverlayObject.getBitmapDrawable();
                if (bitmapDrawable != null) {
                    bitmapDrawable.draw(canvas);
                }
                if (!overlayListView$OverlayObject.update(this.getDrawingTime())) {
                    iterator.remove();
                }
            }
        }
    }
}
