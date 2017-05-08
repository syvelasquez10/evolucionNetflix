// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

class AdapterHelper$UpdateOp
{
    int cmd;
    int itemCount;
    Object payload;
    int positionStart;
    
    AdapterHelper$UpdateOp(final int cmd, final int positionStart, final int itemCount, final Object payload) {
        this.cmd = cmd;
        this.positionStart = positionStart;
        this.itemCount = itemCount;
        this.payload = payload;
    }
    
    String cmdToString() {
        switch (this.cmd) {
            default: {
                return "??";
            }
            case 1: {
                return "add";
            }
            case 2: {
                return "rm";
            }
            case 4: {
                return "up";
            }
            case 8: {
                return "mv";
            }
        }
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final AdapterHelper$UpdateOp adapterHelper$UpdateOp = (AdapterHelper$UpdateOp)o;
            if (this.cmd != adapterHelper$UpdateOp.cmd) {
                return false;
            }
            if (this.cmd != 8 || Math.abs(this.itemCount - this.positionStart) != 1 || this.itemCount != adapterHelper$UpdateOp.positionStart || this.positionStart != adapterHelper$UpdateOp.itemCount) {
                if (this.itemCount != adapterHelper$UpdateOp.itemCount) {
                    return false;
                }
                if (this.positionStart != adapterHelper$UpdateOp.positionStart) {
                    return false;
                }
                if (this.payload != null) {
                    if (!this.payload.equals(adapterHelper$UpdateOp.payload)) {
                        return false;
                    }
                }
                else if (adapterHelper$UpdateOp.payload != null) {
                    return false;
                }
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return (this.cmd * 31 + this.positionStart) * 31 + this.itemCount;
    }
    
    @Override
    public String toString() {
        return Integer.toHexString(System.identityHashCode(this)) + "[" + this.cmdToString() + ",s:" + this.positionStart + "c:" + this.itemCount + ",p:" + this.payload + "]";
    }
}
