// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.List;
import android.support.v4.util.Pools$SimplePool;
import android.support.v4.util.Pools$Pool;
import java.util.ArrayList;

class AdapterHelper implements OpReorderer$Callback
{
    final AdapterHelper$Callback mCallback;
    final boolean mDisableRecycler;
    Runnable mOnItemProcessedCallback;
    final OpReorderer mOpReorderer;
    final ArrayList<AdapterHelper$UpdateOp> mPendingUpdates;
    final ArrayList<AdapterHelper$UpdateOp> mPostponedList;
    private Pools$Pool<AdapterHelper$UpdateOp> mUpdateOpPool;
    
    AdapterHelper(final AdapterHelper$Callback adapterHelper$Callback) {
        this(adapterHelper$Callback, false);
    }
    
    AdapterHelper(final AdapterHelper$Callback mCallback, final boolean mDisableRecycler) {
        this.mUpdateOpPool = new Pools$SimplePool<AdapterHelper$UpdateOp>(30);
        this.mPendingUpdates = new ArrayList<AdapterHelper$UpdateOp>();
        this.mPostponedList = new ArrayList<AdapterHelper$UpdateOp>();
        this.mCallback = mCallback;
        this.mDisableRecycler = mDisableRecycler;
        this.mOpReorderer = new OpReorderer(this);
    }
    
    private void applyAdd(final AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
        this.postponeAndUpdateViewHolders(adapterHelper$UpdateOp);
    }
    
    private void applyMove(final AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
        this.postponeAndUpdateViewHolders(adapterHelper$UpdateOp);
    }
    
    private void applyRemove(final AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
        final int positionStart = adapterHelper$UpdateOp.positionStart;
        int n = adapterHelper$UpdateOp.positionStart + adapterHelper$UpdateOp.itemCount;
        int n2 = -1;
        int i = adapterHelper$UpdateOp.positionStart;
        int n3 = 0;
        while (i < n) {
            int n4;
            if (this.mCallback.findViewHolder(i) != null || this.canFindInPreLayout(i)) {
                if (n2 == 0) {
                    this.dispatchAndUpdateViewHolders(this.obtainUpdateOp(1, positionStart, n3, null));
                    n4 = 1;
                }
                else {
                    n4 = 0;
                }
                n2 = 1;
            }
            else {
                int n5;
                if (n2 == 1) {
                    this.postponeAndUpdateViewHolders(this.obtainUpdateOp(1, positionStart, n3, null));
                    n5 = 1;
                }
                else {
                    n5 = 0;
                }
                final boolean b = false;
                n4 = n5;
                n2 = (b ? 1 : 0);
            }
            int n6;
            int n7;
            int n8;
            if (n4 != 0) {
                n6 = i - n3;
                n7 = n - n3;
                n8 = 1;
            }
            else {
                final int n9 = n3 + 1;
                n6 = i;
                n7 = n;
                n8 = n9;
            }
            n3 = n8;
            n = n7;
            i = n6 + 1;
        }
        AdapterHelper$UpdateOp obtainUpdateOp = adapterHelper$UpdateOp;
        if (n3 != adapterHelper$UpdateOp.itemCount) {
            this.recycleUpdateOp(adapterHelper$UpdateOp);
            obtainUpdateOp = this.obtainUpdateOp(1, positionStart, n3, null);
        }
        if (n2 == 0) {
            this.dispatchAndUpdateViewHolders(obtainUpdateOp);
            return;
        }
        this.postponeAndUpdateViewHolders(obtainUpdateOp);
    }
    
    private void applyUpdate(final AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
        int positionStart = adapterHelper$UpdateOp.positionStart;
        final int positionStart2 = adapterHelper$UpdateOp.positionStart;
        final int itemCount = adapterHelper$UpdateOp.itemCount;
        int i = adapterHelper$UpdateOp.positionStart;
        int n = -1;
        int n2 = 0;
        while (i < positionStart2 + itemCount) {
            int n3;
            int n5;
            if (this.mCallback.findViewHolder(i) != null || this.canFindInPreLayout(i)) {
                n3 = n2;
                int n4 = positionStart;
                if (n == 0) {
                    this.dispatchAndUpdateViewHolders(this.obtainUpdateOp(2, positionStart, n2, adapterHelper$UpdateOp.payload));
                    n3 = 0;
                    n4 = i;
                }
                positionStart = n4;
                n5 = 1;
            }
            else {
                n3 = n2;
                int n6 = positionStart;
                if (n == 1) {
                    this.postponeAndUpdateViewHolders(this.obtainUpdateOp(2, positionStart, n2, adapterHelper$UpdateOp.payload));
                    n3 = 0;
                    n6 = i;
                }
                positionStart = n6;
                n5 = 0;
            }
            ++i;
            final int n7 = n3 + 1;
            n = n5;
            n2 = n7;
        }
        AdapterHelper$UpdateOp obtainUpdateOp = adapterHelper$UpdateOp;
        if (n2 != adapterHelper$UpdateOp.itemCount) {
            final Object payload = adapterHelper$UpdateOp.payload;
            this.recycleUpdateOp(adapterHelper$UpdateOp);
            obtainUpdateOp = this.obtainUpdateOp(2, positionStart, n2, payload);
        }
        if (n == 0) {
            this.dispatchAndUpdateViewHolders(obtainUpdateOp);
            return;
        }
        this.postponeAndUpdateViewHolders(obtainUpdateOp);
    }
    
    private boolean canFindInPreLayout(final int n) {
        for (int size = this.mPostponedList.size(), i = 0; i < size; ++i) {
            final AdapterHelper$UpdateOp adapterHelper$UpdateOp = this.mPostponedList.get(i);
            if (adapterHelper$UpdateOp.cmd == 3) {
                if (this.findPositionOffset(adapterHelper$UpdateOp.itemCount, i + 1) == n) {
                    return true;
                }
            }
            else if (adapterHelper$UpdateOp.cmd == 0) {
                for (int positionStart = adapterHelper$UpdateOp.positionStart, itemCount = adapterHelper$UpdateOp.itemCount, j = adapterHelper$UpdateOp.positionStart; j < positionStart + itemCount; ++j) {
                    if (this.findPositionOffset(j, i + 1) == n) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    private void dispatchAndUpdateViewHolders(AdapterHelper$UpdateOp obtainUpdateOp) {
        if (obtainUpdateOp.cmd == 0 || obtainUpdateOp.cmd == 3) {
            throw new IllegalArgumentException("should not dispatch add or move for pre layout");
        }
        int updatePositionWithPostponed = this.updatePositionWithPostponed(obtainUpdateOp.positionStart, obtainUpdateOp.cmd);
        int positionStart = obtainUpdateOp.positionStart;
        int n = 0;
        switch (obtainUpdateOp.cmd) {
            default: {
                throw new IllegalArgumentException("op should be remove or update." + obtainUpdateOp);
            }
            case 2: {
                n = 1;
                break;
            }
            case 1: {
                n = 0;
                break;
            }
        }
        int n2 = 1;
        int n4;
        for (int i = 1; i < obtainUpdateOp.itemCount; ++i, n2 = n4) {
            final int updatePositionWithPostponed2 = this.updatePositionWithPostponed(obtainUpdateOp.positionStart + n * i, obtainUpdateOp.cmd);
            int n3 = 0;
            switch (obtainUpdateOp.cmd) {
                default: {
                    n3 = 0;
                    break;
                }
                case 2: {
                    if (updatePositionWithPostponed2 == updatePositionWithPostponed + 1) {
                        n3 = 1;
                        break;
                    }
                    n3 = 0;
                    break;
                }
                case 1: {
                    if (updatePositionWithPostponed2 == updatePositionWithPostponed) {
                        n3 = 1;
                        break;
                    }
                    n3 = 0;
                    break;
                }
            }
            if (n3 != 0) {
                n4 = n2 + 1;
            }
            else {
                final AdapterHelper$UpdateOp obtainUpdateOp2 = this.obtainUpdateOp(obtainUpdateOp.cmd, updatePositionWithPostponed, n2, obtainUpdateOp.payload);
                this.dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp2, positionStart);
                this.recycleUpdateOp(obtainUpdateOp2);
                int n5 = positionStart;
                if (obtainUpdateOp.cmd == 2) {
                    n5 = positionStart + n2;
                }
                final int n6 = 1;
                updatePositionWithPostponed = updatePositionWithPostponed2;
                positionStart = n5;
                n4 = n6;
            }
        }
        final Object payload = obtainUpdateOp.payload;
        this.recycleUpdateOp(obtainUpdateOp);
        if (n2 > 0) {
            obtainUpdateOp = this.obtainUpdateOp(obtainUpdateOp.cmd, updatePositionWithPostponed, n2, payload);
            this.dispatchFirstPassAndUpdateViewHolders(obtainUpdateOp, positionStart);
            this.recycleUpdateOp(obtainUpdateOp);
        }
    }
    
    private void postponeAndUpdateViewHolders(final AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
        this.mPostponedList.add(adapterHelper$UpdateOp);
        switch (adapterHelper$UpdateOp.cmd) {
            default: {
                throw new IllegalArgumentException("Unknown update op type for " + adapterHelper$UpdateOp);
            }
            case 0: {
                this.mCallback.offsetPositionsForAdd(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
            }
            case 3: {
                this.mCallback.offsetPositionsForMove(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
            }
            case 1: {
                this.mCallback.offsetPositionsForRemovingLaidOutOrNewView(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
            }
            case 2: {
                this.mCallback.markViewHoldersUpdated(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount, adapterHelper$UpdateOp.payload);
            }
        }
    }
    
    private int updatePositionWithPostponed(int n, int i) {
        int n4;
        for (int j = this.mPostponedList.size() - 1; j >= 0; --j, n = n4) {
            final AdapterHelper$UpdateOp adapterHelper$UpdateOp = this.mPostponedList.get(j);
            if (adapterHelper$UpdateOp.cmd == 3) {
                int n2;
                int n3;
                if (adapterHelper$UpdateOp.positionStart < adapterHelper$UpdateOp.itemCount) {
                    n2 = adapterHelper$UpdateOp.positionStart;
                    n3 = adapterHelper$UpdateOp.itemCount;
                }
                else {
                    n2 = adapterHelper$UpdateOp.itemCount;
                    n3 = adapterHelper$UpdateOp.positionStart;
                }
                if (n >= n2 && n <= n3) {
                    if (n2 == adapterHelper$UpdateOp.positionStart) {
                        if (i == 0) {
                            ++adapterHelper$UpdateOp.itemCount;
                        }
                        else if (i == 1) {
                            --adapterHelper$UpdateOp.itemCount;
                        }
                        ++n;
                    }
                    else {
                        if (i == 0) {
                            ++adapterHelper$UpdateOp.positionStart;
                        }
                        else if (i == 1) {
                            --adapterHelper$UpdateOp.positionStart;
                        }
                        --n;
                    }
                }
                else if (n < adapterHelper$UpdateOp.positionStart) {
                    if (i == 0) {
                        ++adapterHelper$UpdateOp.positionStart;
                        ++adapterHelper$UpdateOp.itemCount;
                    }
                    else if (i == 1) {
                        --adapterHelper$UpdateOp.positionStart;
                        --adapterHelper$UpdateOp.itemCount;
                    }
                }
                n4 = n;
            }
            else if (adapterHelper$UpdateOp.positionStart <= n) {
                if (adapterHelper$UpdateOp.cmd == 0) {
                    n4 = n - adapterHelper$UpdateOp.itemCount;
                }
                else {
                    n4 = n;
                    if (adapterHelper$UpdateOp.cmd == 1) {
                        n4 = n + adapterHelper$UpdateOp.itemCount;
                    }
                }
            }
            else if (i == 0) {
                ++adapterHelper$UpdateOp.positionStart;
                n4 = n;
            }
            else {
                n4 = n;
                if (i == 1) {
                    --adapterHelper$UpdateOp.positionStart;
                    n4 = n;
                }
            }
        }
        AdapterHelper$UpdateOp adapterHelper$UpdateOp2;
        for (i = this.mPostponedList.size() - 1; i >= 0; --i) {
            adapterHelper$UpdateOp2 = this.mPostponedList.get(i);
            if (adapterHelper$UpdateOp2.cmd == 3) {
                if (adapterHelper$UpdateOp2.itemCount == adapterHelper$UpdateOp2.positionStart || adapterHelper$UpdateOp2.itemCount < 0) {
                    this.mPostponedList.remove(i);
                    this.recycleUpdateOp(adapterHelper$UpdateOp2);
                }
            }
            else if (adapterHelper$UpdateOp2.itemCount <= 0) {
                this.mPostponedList.remove(i);
                this.recycleUpdateOp(adapterHelper$UpdateOp2);
            }
        }
        return n;
    }
    
    public int applyPendingUpdatesToPosition(int itemCount) {
        final int size = this.mPendingUpdates.size();
        int n = 0;
        int n2 = itemCount;
    Label_0078_Outer:
        while (true) {
            itemCount = n2;
            if (n >= size) {
                break;
            }
            final AdapterHelper$UpdateOp adapterHelper$UpdateOp = this.mPendingUpdates.get(n);
            itemCount = n2;
            while (true) {
                switch (adapterHelper$UpdateOp.cmd) {
                    default: {
                        itemCount = n2;
                    }
                    case 2: {
                        ++n;
                        n2 = itemCount;
                        continue Label_0078_Outer;
                    }
                    case 0: {
                        itemCount = n2;
                        if (adapterHelper$UpdateOp.positionStart <= n2) {
                            itemCount = n2 + adapterHelper$UpdateOp.itemCount;
                        }
                        continue;
                    }
                    case 1: {
                        itemCount = n2;
                        if (adapterHelper$UpdateOp.positionStart > n2) {
                            continue;
                        }
                        if (adapterHelper$UpdateOp.positionStart + adapterHelper$UpdateOp.itemCount > n2) {
                            itemCount = -1;
                            break Label_0078_Outer;
                        }
                        itemCount = n2 - adapterHelper$UpdateOp.itemCount;
                        continue;
                    }
                    case 3: {
                        if (adapterHelper$UpdateOp.positionStart == n2) {
                            itemCount = adapterHelper$UpdateOp.itemCount;
                            continue;
                        }
                        int n3;
                        if (adapterHelper$UpdateOp.positionStart < (n3 = n2)) {
                            n3 = n2 - 1;
                        }
                        if (adapterHelper$UpdateOp.itemCount <= (itemCount = n3)) {
                            itemCount = n3 + 1;
                        }
                        continue;
                    }
                }
                break;
            }
        }
        return itemCount;
    }
    
    void consumePostponedUpdates() {
        for (int size = this.mPostponedList.size(), i = 0; i < size; ++i) {
            this.mCallback.onDispatchSecondPass(this.mPostponedList.get(i));
        }
        this.recycleUpdateOpsAndClearList(this.mPostponedList);
    }
    
    void consumeUpdatesInOnePass() {
        this.consumePostponedUpdates();
        for (int size = this.mPendingUpdates.size(), i = 0; i < size; ++i) {
            final AdapterHelper$UpdateOp adapterHelper$UpdateOp = this.mPendingUpdates.get(i);
            switch (adapterHelper$UpdateOp.cmd) {
                case 0: {
                    this.mCallback.onDispatchSecondPass(adapterHelper$UpdateOp);
                    this.mCallback.offsetPositionsForAdd(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
                    break;
                }
                case 1: {
                    this.mCallback.onDispatchSecondPass(adapterHelper$UpdateOp);
                    this.mCallback.offsetPositionsForRemovingInvisible(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
                    break;
                }
                case 2: {
                    this.mCallback.onDispatchSecondPass(adapterHelper$UpdateOp);
                    this.mCallback.markViewHoldersUpdated(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount, adapterHelper$UpdateOp.payload);
                    break;
                }
                case 3: {
                    this.mCallback.onDispatchSecondPass(adapterHelper$UpdateOp);
                    this.mCallback.offsetPositionsForMove(adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
                    break;
                }
            }
            if (this.mOnItemProcessedCallback != null) {
                this.mOnItemProcessedCallback.run();
            }
        }
        this.recycleUpdateOpsAndClearList(this.mPendingUpdates);
    }
    
    void dispatchFirstPassAndUpdateViewHolders(final AdapterHelper$UpdateOp adapterHelper$UpdateOp, final int n) {
        this.mCallback.onDispatchFirstPass(adapterHelper$UpdateOp);
        switch (adapterHelper$UpdateOp.cmd) {
            default: {
                throw new IllegalArgumentException("only remove and update ops can be dispatched in first pass");
            }
            case 1: {
                this.mCallback.offsetPositionsForRemovingInvisible(n, adapterHelper$UpdateOp.itemCount);
            }
            case 2: {
                this.mCallback.markViewHoldersUpdated(n, adapterHelper$UpdateOp.itemCount, adapterHelper$UpdateOp.payload);
            }
        }
    }
    
    int findPositionOffset(final int n) {
        return this.findPositionOffset(n, 0);
    }
    
    int findPositionOffset(int itemCount, int n) {
        final int size = this.mPostponedList.size();
        int n2 = n;
        n = itemCount;
        while (true) {
            itemCount = n;
            if (n2 >= size) {
                break;
            }
            final AdapterHelper$UpdateOp adapterHelper$UpdateOp = this.mPostponedList.get(n2);
            if (adapterHelper$UpdateOp.cmd == 3) {
                if (adapterHelper$UpdateOp.positionStart == n) {
                    itemCount = adapterHelper$UpdateOp.itemCount;
                }
                else {
                    int n3;
                    if (adapterHelper$UpdateOp.positionStart < (n3 = n)) {
                        n3 = n - 1;
                    }
                    if (adapterHelper$UpdateOp.itemCount <= (itemCount = n3)) {
                        itemCount = n3 + 1;
                    }
                }
            }
            else if (adapterHelper$UpdateOp.positionStart <= (itemCount = n)) {
                if (adapterHelper$UpdateOp.cmd == 1) {
                    if (n < adapterHelper$UpdateOp.positionStart + adapterHelper$UpdateOp.itemCount) {
                        itemCount = -1;
                        break;
                    }
                    itemCount = n - adapterHelper$UpdateOp.itemCount;
                }
                else {
                    itemCount = n;
                    if (adapterHelper$UpdateOp.cmd == 0) {
                        itemCount = n + adapterHelper$UpdateOp.itemCount;
                    }
                }
            }
            ++n2;
            n = itemCount;
        }
        return itemCount;
    }
    
    boolean hasPendingUpdates() {
        return this.mPendingUpdates.size() > 0;
    }
    
    @Override
    public AdapterHelper$UpdateOp obtainUpdateOp(final int cmd, final int positionStart, final int itemCount, final Object payload) {
        final AdapterHelper$UpdateOp adapterHelper$UpdateOp = this.mUpdateOpPool.acquire();
        if (adapterHelper$UpdateOp == null) {
            return new AdapterHelper$UpdateOp(cmd, positionStart, itemCount, payload);
        }
        adapterHelper$UpdateOp.cmd = cmd;
        adapterHelper$UpdateOp.positionStart = positionStart;
        adapterHelper$UpdateOp.itemCount = itemCount;
        adapterHelper$UpdateOp.payload = payload;
        return adapterHelper$UpdateOp;
    }
    
    boolean onItemRangeChanged(final int n, final int n2, final Object o) {
        this.mPendingUpdates.add(this.obtainUpdateOp(2, n, n2, o));
        return this.mPendingUpdates.size() == 1;
    }
    
    boolean onItemRangeInserted(final int n, final int n2) {
        this.mPendingUpdates.add(this.obtainUpdateOp(0, n, n2, null));
        return this.mPendingUpdates.size() == 1;
    }
    
    boolean onItemRangeMoved(final int n, final int n2, final int n3) {
        boolean b = true;
        if (n == n2) {
            return false;
        }
        if (n3 != 1) {
            throw new IllegalArgumentException("Moving more than 1 item is not supported yet");
        }
        this.mPendingUpdates.add(this.obtainUpdateOp(3, n, n2, null));
        if (this.mPendingUpdates.size() != 1) {
            b = false;
        }
        return b;
    }
    
    boolean onItemRangeRemoved(final int n, final int n2) {
        this.mPendingUpdates.add(this.obtainUpdateOp(1, n, n2, null));
        return this.mPendingUpdates.size() == 1;
    }
    
    void preProcess() {
        this.mOpReorderer.reorderOps(this.mPendingUpdates);
        for (int size = this.mPendingUpdates.size(), i = 0; i < size; ++i) {
            final AdapterHelper$UpdateOp adapterHelper$UpdateOp = this.mPendingUpdates.get(i);
            switch (adapterHelper$UpdateOp.cmd) {
                case 0: {
                    this.applyAdd(adapterHelper$UpdateOp);
                    break;
                }
                case 1: {
                    this.applyRemove(adapterHelper$UpdateOp);
                    break;
                }
                case 2: {
                    this.applyUpdate(adapterHelper$UpdateOp);
                    break;
                }
                case 3: {
                    this.applyMove(adapterHelper$UpdateOp);
                    break;
                }
            }
            if (this.mOnItemProcessedCallback != null) {
                this.mOnItemProcessedCallback.run();
            }
        }
        this.mPendingUpdates.clear();
    }
    
    @Override
    public void recycleUpdateOp(final AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
        if (!this.mDisableRecycler) {
            adapterHelper$UpdateOp.payload = null;
            this.mUpdateOpPool.release(adapterHelper$UpdateOp);
        }
    }
    
    void recycleUpdateOpsAndClearList(final List<AdapterHelper$UpdateOp> list) {
        for (int size = list.size(), i = 0; i < size; ++i) {
            this.recycleUpdateOp(list.get(i));
        }
        list.clear();
    }
    
    void reset() {
        this.recycleUpdateOpsAndClearList(this.mPendingUpdates);
        this.recycleUpdateOpsAndClearList(this.mPostponedList);
    }
}
