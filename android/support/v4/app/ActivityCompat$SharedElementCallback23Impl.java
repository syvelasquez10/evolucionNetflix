// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import java.util.Map;
import java.util.List;
import android.content.Context;
import android.os.Parcelable;
import android.graphics.RectF;
import android.graphics.Matrix;
import android.view.View;

class ActivityCompat$SharedElementCallback23Impl extends ActivityCompatApi23$SharedElementCallback23
{
    private SharedElementCallback mCallback;
    
    public ActivityCompat$SharedElementCallback23Impl(final SharedElementCallback mCallback) {
        this.mCallback = mCallback;
    }
    
    @Override
    public Parcelable onCaptureSharedElementSnapshot(final View view, final Matrix matrix, final RectF rectF) {
        return this.mCallback.onCaptureSharedElementSnapshot(view, matrix, rectF);
    }
    
    @Override
    public View onCreateSnapshotView(final Context context, final Parcelable parcelable) {
        return this.mCallback.onCreateSnapshotView(context, parcelable);
    }
    
    @Override
    public void onMapSharedElements(final List<String> list, final Map<String, View> map) {
        this.mCallback.onMapSharedElements(list, map);
    }
    
    @Override
    public void onRejectSharedElements(final List<View> list) {
        this.mCallback.onRejectSharedElements(list);
    }
    
    @Override
    public void onSharedElementEnd(final List<String> list, final List<View> list2, final List<View> list3) {
        this.mCallback.onSharedElementEnd(list, list2, list3);
    }
    
    @Override
    public void onSharedElementStart(final List<String> list, final List<View> list2, final List<View> list3) {
        this.mCallback.onSharedElementStart(list, list2, list3);
    }
    
    @Override
    public void onSharedElementsArrived(final List<String> list, final List<View> list2, final ActivityCompatApi23$OnSharedElementsReadyListenerBridge activityCompatApi23$OnSharedElementsReadyListenerBridge) {
        this.mCallback.onSharedElementsArrived(list, list2, new ActivityCompat$SharedElementCallback23Impl$1(this, activityCompatApi23$OnSharedElementsReadyListenerBridge));
    }
}
