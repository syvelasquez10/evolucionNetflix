// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.List;

class OpReorderer
{
    final OpReorderer$Callback mCallback;
    
    public OpReorderer(final OpReorderer$Callback mCallback) {
        this.mCallback = mCallback;
    }
    
    private int getLastMoveOutOfOrder(final List<AdapterHelper$UpdateOp> list) {
        int n = 0;
        for (int i = list.size() - 1; i >= 0; --i) {
            if (list.get(i).cmd == 3) {
                if (n != 0) {
                    return i;
                }
            }
            else {
                n = 1;
            }
        }
        return -1;
    }
    
    private void swapMoveAdd(final List<AdapterHelper$UpdateOp> list, final int n, final AdapterHelper$UpdateOp adapterHelper$UpdateOp, final int n2, final AdapterHelper$UpdateOp adapterHelper$UpdateOp2) {
        int n3 = 0;
        if (adapterHelper$UpdateOp.itemCount < adapterHelper$UpdateOp2.positionStart) {
            n3 = -1;
        }
        int n4 = n3;
        if (adapterHelper$UpdateOp.positionStart < adapterHelper$UpdateOp2.positionStart) {
            n4 = n3 + 1;
        }
        if (adapterHelper$UpdateOp2.positionStart <= adapterHelper$UpdateOp.positionStart) {
            adapterHelper$UpdateOp.positionStart += adapterHelper$UpdateOp2.itemCount;
        }
        if (adapterHelper$UpdateOp2.positionStart <= adapterHelper$UpdateOp.itemCount) {
            adapterHelper$UpdateOp.itemCount += adapterHelper$UpdateOp2.itemCount;
        }
        adapterHelper$UpdateOp2.positionStart += n4;
        list.set(n, adapterHelper$UpdateOp2);
        list.set(n2, adapterHelper$UpdateOp);
    }
    
    private void swapMoveOp(final List<AdapterHelper$UpdateOp> list, final int n, final int n2) {
        final AdapterHelper$UpdateOp adapterHelper$UpdateOp = list.get(n);
        final AdapterHelper$UpdateOp adapterHelper$UpdateOp2 = list.get(n2);
        switch (adapterHelper$UpdateOp2.cmd) {
            default: {}
            case 1: {
                this.swapMoveRemove(list, n, adapterHelper$UpdateOp, n2, adapterHelper$UpdateOp2);
            }
            case 0: {
                this.swapMoveAdd(list, n, adapterHelper$UpdateOp, n2, adapterHelper$UpdateOp2);
            }
            case 2: {
                this.swapMoveUpdate(list, n, adapterHelper$UpdateOp, n2, adapterHelper$UpdateOp2);
            }
        }
    }
    
    void reorderOps(final List<AdapterHelper$UpdateOp> list) {
        while (true) {
            final int lastMoveOutOfOrder = this.getLastMoveOutOfOrder(list);
            if (lastMoveOutOfOrder == -1) {
                break;
            }
            this.swapMoveOp(list, lastMoveOutOfOrder, lastMoveOutOfOrder + 1);
        }
    }
    
    void swapMoveRemove(final List<AdapterHelper$UpdateOp> list, final int n, final AdapterHelper$UpdateOp adapterHelper$UpdateOp, final int n2, final AdapterHelper$UpdateOp adapterHelper$UpdateOp2) {
        int n3 = 0;
        int n4;
        if (adapterHelper$UpdateOp.positionStart < adapterHelper$UpdateOp.itemCount) {
            if (adapterHelper$UpdateOp2.positionStart == adapterHelper$UpdateOp.positionStart && adapterHelper$UpdateOp2.itemCount == adapterHelper$UpdateOp.itemCount - adapterHelper$UpdateOp.positionStart) {
                n4 = 1;
            }
            else {
                n4 = 0;
            }
        }
        else if (adapterHelper$UpdateOp2.positionStart == adapterHelper$UpdateOp.itemCount + 1 && adapterHelper$UpdateOp2.itemCount == adapterHelper$UpdateOp.positionStart - adapterHelper$UpdateOp.itemCount) {
            n3 = 1;
            n4 = 1;
        }
        else {
            n4 = 0;
            n3 = 1;
        }
        if (adapterHelper$UpdateOp.itemCount < adapterHelper$UpdateOp2.positionStart) {
            --adapterHelper$UpdateOp2.positionStart;
        }
        else if (adapterHelper$UpdateOp.itemCount < adapterHelper$UpdateOp2.positionStart + adapterHelper$UpdateOp2.itemCount) {
            --adapterHelper$UpdateOp2.itemCount;
            adapterHelper$UpdateOp.cmd = 1;
            adapterHelper$UpdateOp.itemCount = 1;
            if (adapterHelper$UpdateOp2.itemCount == 0) {
                list.remove(n2);
                this.mCallback.recycleUpdateOp(adapterHelper$UpdateOp2);
            }
            return;
        }
        AdapterHelper$UpdateOp obtainUpdateOp;
        if (adapterHelper$UpdateOp.positionStart <= adapterHelper$UpdateOp2.positionStart) {
            ++adapterHelper$UpdateOp2.positionStart;
            obtainUpdateOp = null;
        }
        else if (adapterHelper$UpdateOp.positionStart < adapterHelper$UpdateOp2.positionStart + adapterHelper$UpdateOp2.itemCount) {
            obtainUpdateOp = this.mCallback.obtainUpdateOp(1, adapterHelper$UpdateOp.positionStart + 1, adapterHelper$UpdateOp2.positionStart + adapterHelper$UpdateOp2.itemCount - adapterHelper$UpdateOp.positionStart, null);
            adapterHelper$UpdateOp2.itemCount = adapterHelper$UpdateOp.positionStart - adapterHelper$UpdateOp2.positionStart;
        }
        else {
            obtainUpdateOp = null;
        }
        if (n4 != 0) {
            list.set(n, adapterHelper$UpdateOp2);
            list.remove(n2);
            this.mCallback.recycleUpdateOp(adapterHelper$UpdateOp);
        }
        else {
            if (n3 != 0) {
                if (obtainUpdateOp != null) {
                    if (adapterHelper$UpdateOp.positionStart > obtainUpdateOp.positionStart) {
                        adapterHelper$UpdateOp.positionStart -= obtainUpdateOp.itemCount;
                    }
                    if (adapterHelper$UpdateOp.itemCount > obtainUpdateOp.positionStart) {
                        adapterHelper$UpdateOp.itemCount -= obtainUpdateOp.itemCount;
                    }
                }
                if (adapterHelper$UpdateOp.positionStart > adapterHelper$UpdateOp2.positionStart) {
                    adapterHelper$UpdateOp.positionStart -= adapterHelper$UpdateOp2.itemCount;
                }
                if (adapterHelper$UpdateOp.itemCount > adapterHelper$UpdateOp2.positionStart) {
                    adapterHelper$UpdateOp.itemCount -= adapterHelper$UpdateOp2.itemCount;
                }
            }
            else {
                if (obtainUpdateOp != null) {
                    if (adapterHelper$UpdateOp.positionStart >= obtainUpdateOp.positionStart) {
                        adapterHelper$UpdateOp.positionStart -= obtainUpdateOp.itemCount;
                    }
                    if (adapterHelper$UpdateOp.itemCount >= obtainUpdateOp.positionStart) {
                        adapterHelper$UpdateOp.itemCount -= obtainUpdateOp.itemCount;
                    }
                }
                if (adapterHelper$UpdateOp.positionStart >= adapterHelper$UpdateOp2.positionStart) {
                    adapterHelper$UpdateOp.positionStart -= adapterHelper$UpdateOp2.itemCount;
                }
                if (adapterHelper$UpdateOp.itemCount >= adapterHelper$UpdateOp2.positionStart) {
                    adapterHelper$UpdateOp.itemCount -= adapterHelper$UpdateOp2.itemCount;
                }
            }
            list.set(n, adapterHelper$UpdateOp2);
            if (adapterHelper$UpdateOp.positionStart != adapterHelper$UpdateOp.itemCount) {
                list.set(n2, adapterHelper$UpdateOp);
            }
            else {
                list.remove(n2);
            }
            if (obtainUpdateOp != null) {
                list.add(n, obtainUpdateOp);
            }
        }
    }
    
    void swapMoveUpdate(final List<AdapterHelper$UpdateOp> list, final int n, final AdapterHelper$UpdateOp adapterHelper$UpdateOp, final int n2, final AdapterHelper$UpdateOp adapterHelper$UpdateOp2) {
        AdapterHelper$UpdateOp obtainUpdateOp = null;
        AdapterHelper$UpdateOp obtainUpdateOp2;
        if (adapterHelper$UpdateOp.itemCount < adapterHelper$UpdateOp2.positionStart) {
            --adapterHelper$UpdateOp2.positionStart;
            obtainUpdateOp2 = null;
        }
        else if (adapterHelper$UpdateOp.itemCount < adapterHelper$UpdateOp2.positionStart + adapterHelper$UpdateOp2.itemCount) {
            --adapterHelper$UpdateOp2.itemCount;
            obtainUpdateOp2 = this.mCallback.obtainUpdateOp(2, adapterHelper$UpdateOp.positionStart, 1, adapterHelper$UpdateOp2.payload);
        }
        else {
            obtainUpdateOp2 = null;
        }
        if (adapterHelper$UpdateOp.positionStart <= adapterHelper$UpdateOp2.positionStart) {
            ++adapterHelper$UpdateOp2.positionStart;
        }
        else if (adapterHelper$UpdateOp.positionStart < adapterHelper$UpdateOp2.positionStart + adapterHelper$UpdateOp2.itemCount) {
            final int n3 = adapterHelper$UpdateOp2.positionStart + adapterHelper$UpdateOp2.itemCount - adapterHelper$UpdateOp.positionStart;
            obtainUpdateOp = this.mCallback.obtainUpdateOp(2, adapterHelper$UpdateOp.positionStart + 1, n3, adapterHelper$UpdateOp2.payload);
            adapterHelper$UpdateOp2.itemCount -= n3;
        }
        list.set(n2, adapterHelper$UpdateOp);
        if (adapterHelper$UpdateOp2.itemCount > 0) {
            list.set(n, adapterHelper$UpdateOp2);
        }
        else {
            list.remove(n);
            this.mCallback.recycleUpdateOp(adapterHelper$UpdateOp2);
        }
        if (obtainUpdateOp2 != null) {
            list.add(n, obtainUpdateOp2);
        }
        if (obtainUpdateOp != null) {
            list.add(n, obtainUpdateOp);
        }
    }
}
