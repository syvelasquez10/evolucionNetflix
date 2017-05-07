// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

public class Triple<T, U, V>
{
    public final T first;
    public final U second;
    public final V third;
    
    public Triple(final T first, final U second, final V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (o == null) {
                return false;
            }
            if (!(o instanceof Triple)) {
                return false;
            }
            final Triple triple = (Triple)o;
            if (this.first == null) {
                if (triple.first != null) {
                    return false;
                }
            }
            else if (!this.first.equals(triple.first)) {
                return false;
            }
            if (this.second == null) {
                if (triple.second != null) {
                    return false;
                }
            }
            else if (!this.second.equals(triple.second)) {
                return false;
            }
            if (this.third == null) {
                if (triple.third != null) {
                    return false;
                }
            }
            else if (!this.third.equals(triple.third)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        int hashCode2;
        if (this.first == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = this.first.hashCode();
        }
        int hashCode3;
        if (this.second == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = this.second.hashCode();
        }
        if (this.third != null) {
            hashCode = this.third.hashCode();
        }
        return (hashCode3 + (hashCode2 + 31) * 31) * 31 + hashCode;
    }
}
