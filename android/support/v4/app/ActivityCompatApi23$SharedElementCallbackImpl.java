// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.app;

import android.app.SharedElementCallback$OnSharedElementsReadyListener;
import java.util.Map;
import java.util.List;
import android.content.Context;
import android.os.Parcelable;
import android.graphics.RectF;
import android.graphics.Matrix;
import android.view.View;
import android.app.SharedElementCallback;

class ActivityCompatApi23$SharedElementCallbackImpl extends SharedElementCallback
{
    private ActivityCompatApi23$SharedElementCallback23 mCallback;
    
    public ActivityCompatApi23$SharedElementCallbackImpl(final ActivityCompatApi23$SharedElementCallback23 mCallback) {
        this.mCallback = mCallback;
    }
    
    public Parcelable onCaptureSharedElementSnapshot(final View view, final Matrix matrix, final RectF rectF) {
        return this.mCallback.onCaptureSharedElementSnapshot(view, matrix, rectF);
    }
    
    public View onCreateSnapshotView(final Context context, final Parcelable parcelable) {
        return this.mCallback.onCreateSnapshotView(context, parcelable);
    }
    
    public void onMapSharedElements(final List<String> list, final Map<String, View> map) {
        this.mCallback.onMapSharedElements(list, map);
    }
    
    public void onRejectSharedElements(final List<View> list) {
        this.mCallback.onRejectSharedElements(list);
    }
    
    public void onSharedElementEnd(final List<String> list, final List<View> list2, final List<View> list3) {
        this.mCallback.onSharedElementEnd(list, list2, list3);
    }
    
    public void onSharedElementStart(final List<String> list, final List<View> list2, final List<View> list3) {
        this.mCallback.onSharedElementStart(list, list2, list3);
    }
    
    public void onSharedElementsArrived(final List<String> list, final List<View> list2, final SharedElementCallback$OnSharedElementsReadyListener sharedElementCallback$OnSharedElementsReadyListener) {
        this.mCallback.onSharedElementsArrived(list, list2, new ActivityCompatApi23$SharedElementCallbackImpl$1(this, sharedElementCallback$OnSharedElementsReadyListener));
    }
}
