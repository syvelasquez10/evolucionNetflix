// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

class AdapterHelper$UpdateOp
{
    int cmd;
    int itemCount;
    int positionStart;
    
    AdapterHelper$UpdateOp(final int cmd, final int positionStart, final int itemCount) {
        this.cmd = cmd;
        this.positionStart = positionStart;
        this.itemCount = itemCount;
    }
    
    String cmdToString() {
        switch (this.cmd) {
            default: {
                return "??";
            }
            case 0: {
                return "add";
            }
            case 1: {
                return "rm";
            }
            case 2: {
                return "up";
            }
            case 3: {
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
            if (this.cmd != 3 || Math.abs(this.itemCount - this.positionStart) != 1 || this.itemCount != adapterHelper$UpdateOp.positionStart || this.positionStart != adapterHelper$UpdateOp.itemCount) {
                if (this.itemCount != adapterHelper$UpdateOp.itemCount) {
                    return false;
                }
                if (this.positionStart != adapterHelper$UpdateOp.positionStart) {
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
        return "[" + this.cmdToString() + ",s:" + this.positionStart + "c:" + this.itemCount + "]";
    }
}
