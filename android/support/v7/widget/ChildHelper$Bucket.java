// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

class ChildHelper$Bucket
{
    long mData;
    ChildHelper$Bucket next;
    
    ChildHelper$Bucket() {
        this.mData = 0L;
    }
    
    private void ensureNext() {
        if (this.next == null) {
            this.next = new ChildHelper$Bucket();
        }
    }
    
    void clear(final int n) {
        if (n >= 64) {
            if (this.next != null) {
                this.next.clear(n - 64);
            }
            return;
        }
        this.mData &= ~(1L << n);
    }
    
    int countOnesBefore(final int n) {
        if (this.next == null) {
            if (n >= 64) {
                return Long.bitCount(this.mData);
            }
            return Long.bitCount(this.mData & (1L << n) - 1L);
        }
        else {
            if (n < 64) {
                return Long.bitCount(this.mData & (1L << n) - 1L);
            }
            return this.next.countOnesBefore(n - 64) + Long.bitCount(this.mData);
        }
    }
    
    boolean get(final int n) {
        if (n >= 64) {
            this.ensureNext();
            return this.next.get(n - 64);
        }
        return (this.mData & 1L << n) != 0x0L;
    }
    
    void insert(final int n, final boolean b) {
        if (n >= 64) {
            this.ensureNext();
            this.next.insert(n - 64, b);
        }
        else {
            final boolean b2 = (this.mData & Long.MIN_VALUE) != 0x0L;
            final long n2 = (1L << n) - 1L;
            this.mData = ((~n2 & this.mData) << 1 | (this.mData & n2));
            if (b) {
                this.set(n);
            }
            else {
                this.clear(n);
            }
            if (b2 || this.next != null) {
                this.ensureNext();
                this.next.insert(0, b2);
            }
        }
    }
    
    boolean remove(final int n) {
        boolean remove;
        if (n >= 64) {
            this.ensureNext();
            remove = this.next.remove(n - 64);
        }
        else {
            final long n2 = 1L << n;
            final boolean b = (this.mData & n2) != 0x0L;
            this.mData &= ~n2;
            final long n3 = n2 - 1L;
            this.mData = (Long.rotateRight(~n3 & this.mData, 1) | (this.mData & n3));
            remove = b;
            if (this.next != null) {
                if (this.next.get(0)) {
                    this.set(63);
                }
                this.next.remove(0);
                return b;
            }
        }
        return remove;
    }
    
    void reset() {
        this.mData = 0L;
        if (this.next != null) {
            this.next.reset();
        }
    }
    
    void set(final int n) {
        if (n >= 64) {
            this.ensureNext();
            this.next.set(n - 64);
            return;
        }
        this.mData |= 1L << n;
    }
    
    @Override
    public String toString() {
        if (this.next == null) {
            return Long.toBinaryString(this.mData);
        }
        return this.next.toString() + "xx" + Long.toBinaryString(this.mData);
    }
}
