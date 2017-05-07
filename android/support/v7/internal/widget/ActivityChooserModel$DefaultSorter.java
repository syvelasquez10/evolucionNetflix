// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.internal.widget;

import java.util.Collections;
import java.util.List;
import android.content.Intent;
import java.util.HashMap;
import java.util.Map;

final class ActivityChooserModel$DefaultSorter implements ActivityChooserModel$ActivitySorter
{
    private static final float WEIGHT_DECAY_COEFFICIENT = 0.95f;
    private final Map<String, ActivityChooserModel$ActivityResolveInfo> mPackageNameToActivityMap;
    final /* synthetic */ ActivityChooserModel this$0;
    
    private ActivityChooserModel$DefaultSorter(final ActivityChooserModel this$0) {
        this.this$0 = this$0;
        this.mPackageNameToActivityMap = new HashMap<String, ActivityChooserModel$ActivityResolveInfo>();
    }
    
    @Override
    public void sort(final Intent intent, final List<ActivityChooserModel$ActivityResolveInfo> list, final List<ActivityChooserModel$HistoricalRecord> list2) {
        final Map<String, ActivityChooserModel$ActivityResolveInfo> mPackageNameToActivityMap = this.mPackageNameToActivityMap;
        mPackageNameToActivityMap.clear();
        for (int size = list.size(), i = 0; i < size; ++i) {
            final ActivityChooserModel$ActivityResolveInfo activityChooserModel$ActivityResolveInfo = list.get(i);
            activityChooserModel$ActivityResolveInfo.weight = 0.0f;
            mPackageNameToActivityMap.put(activityChooserModel$ActivityResolveInfo.resolveInfo.activityInfo.packageName, activityChooserModel$ActivityResolveInfo);
        }
        final int size2 = list2.size();
        float n = 1.0f;
        for (int j = size2 - 1; j >= 0; --j) {
            final ActivityChooserModel$HistoricalRecord activityChooserModel$HistoricalRecord = list2.get(j);
            final ActivityChooserModel$ActivityResolveInfo activityChooserModel$ActivityResolveInfo2 = mPackageNameToActivityMap.get(activityChooserModel$HistoricalRecord.activity.getPackageName());
            if (activityChooserModel$ActivityResolveInfo2 != null) {
                activityChooserModel$ActivityResolveInfo2.weight += activityChooserModel$HistoricalRecord.weight * n;
                n *= 0.95f;
            }
        }
        Collections.sort((List<Comparable>)list);
    }
}
