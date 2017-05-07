// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.util;

public class CircularArray<E>
{
    private int mCapacityBitmask;
    private E[] mElements;
    private int mHead;
    private int mTail;
    
    public CircularArray() {
        this(8);
    }
    
    public CircularArray(final int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("capacity must be positive");
        }
        int n2 = n;
        if (Integer.bitCount(n) != 1) {
            n2 = 1 << Integer.highestOneBit(n) + 1;
        }
        this.mCapacityBitmask = n2 - 1;
        this.mElements = new Object[n2];
    }
    
    private void doubleCapacity() {
        final int length = this.mElements.length;
        final int n = length - this.mHead;
        final int n2 = length << 1;
        if (n2 < 0) {
            throw new RuntimeException("Too big");
        }
        final Object[] array = new Object[n2];
        System.arraycopy(this.mElements, this.mHead, array, 0, n);
        System.arraycopy(this.mElements, 0, array, n, this.mHead);
        this.mElements = array;
        this.mHead = 0;
        this.mTail = length;
        this.mCapacityBitmask = n2 - 1;
    }
    
    public final void addFirst(final E e) {
        this.mHead = (this.mHead - 1 & this.mCapacityBitmask);
        this.mElements[this.mHead] = e;
        if (this.mHead == this.mTail) {
            this.doubleCapacity();
        }
    }
    
    public final void addLast(final E e) {
        this.mElements[this.mTail] = e;
        this.mTail = (this.mTail + 1 & this.mCapacityBitmask);
        if (this.mTail == this.mHead) {
            this.doubleCapacity();
        }
    }
    
    public final E get(final int n) {
        if (n < 0 || n >= this.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E)this.mElements[this.mHead + n & this.mCapacityBitmask];
    }
    
    public final E getFirst() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E)this.mElements[this.mHead];
    }
    
    public final E getLast() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (E)this.mElements[this.mTail - 1 & this.mCapacityBitmask];
    }
    
    public final boolean isEmpty() {
        return this.mHead == this.mTail;
    }
    
    public final E popFirst() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        final Object o = this.mElements[this.mHead];
        this.mElements[this.mHead] = null;
        this.mHead = (this.mHead + 1 & this.mCapacityBitmask);
        return (E)o;
    }
    
    public final E popLast() {
        if (this.mHead == this.mTail) {
            throw new ArrayIndexOutOfBoundsException();
        }
        final int mTail = this.mTail - 1 & this.mCapacityBitmask;
        final Object o = this.mElements[mTail];
        this.mElements[mTail] = null;
        this.mTail = mTail;
        return (E)o;
    }
    
    public final int size() {
        return this.mTail - this.mHead & this.mCapacityBitmask;
    }
}
