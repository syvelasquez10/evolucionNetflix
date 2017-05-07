// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.view.View;
import com.netflix.mediaclient.servicemgr.Trackable;
import java.util.List;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import android.util.SparseIntArray;
import android.util.SparseArray;
import com.netflix.mediaclient.servicemgr.CWVideo;

public class PaginatedCwAdapter extends BasePaginatedAdapter<CWVideo>
{
    private static final String TAG = "PaginatedCwAdapter";
    private static final SparseArray<SparseIntArray> numVideosPerPageTable;
    
    static {
        numVideosPerPageTable = new SparseArray(2);
        final SparseIntArray sparseIntArray = new SparseIntArray();
        sparseIntArray.put(1, 1);
        sparseIntArray.put(2, 1);
        sparseIntArray.put(3, 2);
        sparseIntArray.put(4, 2);
        PaginatedCwAdapter.numVideosPerPageTable.put(1, (Object)sparseIntArray);
        final SparseIntArray sparseIntArray2 = new SparseIntArray();
        sparseIntArray2.put(1, 2);
        sparseIntArray2.put(2, 2);
        sparseIntArray2.put(3, 3);
        sparseIntArray2.put(4, 3);
        PaginatedCwAdapter.numVideosPerPageTable.put(2, (Object)sparseIntArray2);
    }
    
    public PaginatedCwAdapter(final Context context) {
        super(context);
    }
    
    public static int computeNumVideosToFetchPerBatch(final int n) {
        return ((SparseIntArray)PaginatedCwAdapter.numVideosPerPageTable.get(1)).get(n) * ((SparseIntArray)PaginatedCwAdapter.numVideosPerPageTable.get(2)).get(n) * 2;
    }
    
    public static int getViewHeightInPixels(final Context context) {
        final int n = (int)(BasePaginatedAdapter.computeViewPagerWidth(context, true) / ((SparseIntArray)PaginatedCwAdapter.numVideosPerPageTable.get(DeviceUtils.getBasicScreenOrientation(context))).get(DeviceUtils.getScreenSizeCategory(context)) * 0.562f + 0.5f) + context.getResources().getDimensionPixelOffset(2131492948);
        Log.v("PaginatedCwAdapter", "Computed view height: " + n);
        return n;
    }
    
    @Override
    protected int computeNumItemsPerPage(final int n, final int n2) {
        return ((SparseIntArray)PaginatedCwAdapter.numVideosPerPageTable.get(n)).get(n2);
    }
    
    @Override
    protected int computeNumVideosToFetchPerBatch(final Context context) {
        return computeNumVideosToFetchPerBatch(DeviceUtils.getScreenSizeCategory(context));
    }
    
    @Override
    protected View getView(final ViewRecycler viewRecycler, final List<CWVideo> list, final int n, final int n2, final Trackable trackable) {
        CwViewGroup cwViewGroup;
        if ((cwViewGroup = (CwViewGroup)viewRecycler.pop(CwViewGroup.class)) == null) {
            cwViewGroup = new CwViewGroup((Context)this.getActivity());
            cwViewGroup.init(n);
        }
        ((VideoViewGroup<CWVideo, V>)cwViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), trackable);
        return (View)cwViewGroup;
    }
}
