// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.Comparator;

final class GapWorker$1 implements Comparator<GapWorker$Task>
{
    @Override
    public int compare(final GapWorker$Task gapWorker$Task, final GapWorker$Task gapWorker$Task2) {
        final int n = -1;
        final boolean b = true;
        int n2;
        if (gapWorker$Task.view == null) {
            n2 = 1;
        }
        else {
            n2 = 0;
        }
        int n3;
        if (gapWorker$Task2.view == null) {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        int n4;
        if (n2 != n3) {
            if (gapWorker$Task.view != null) {
                return -1;
            }
            n4 = (b ? 1 : 0);
        }
        else {
            if (gapWorker$Task.immediate != gapWorker$Task2.immediate) {
                int n5;
                if (gapWorker$Task.immediate) {
                    n5 = n;
                }
                else {
                    n5 = 1;
                }
                return n5;
            }
            if ((n4 = gapWorker$Task2.viewVelocity - gapWorker$Task.viewVelocity) == 0 && (n4 = gapWorker$Task.distanceToItem - gapWorker$Task2.distanceToItem) == 0) {
                return 0;
            }
        }
        return n4;
    }
}
