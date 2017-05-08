// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.util;

public class HashCodeUtil
{
    public static int hashCode(final int n, final int n2) {
        return (n + 31) * 31 + n2;
    }
    
    public static int hashCode(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return (((((n + 31) * 31 + n2) * 31 + n3) * 31 + n4) * 31 + n5) * 31 + n6;
    }
    
    public static int hashCode(final Object o, final Object o2, final Object o3, final Object o4, final Object o5, final Object o6) {
        int hashCode = 0;
        int hashCode2;
        if (o == null) {
            hashCode2 = 0;
        }
        else {
            hashCode2 = o.hashCode();
        }
        int hashCode3;
        if (o2 == null) {
            hashCode3 = 0;
        }
        else {
            hashCode3 = o2.hashCode();
        }
        int hashCode4;
        if (o3 == null) {
            hashCode4 = 0;
        }
        else {
            hashCode4 = o3.hashCode();
        }
        int hashCode5;
        if (o4 == null) {
            hashCode5 = 0;
        }
        else {
            hashCode5 = o4.hashCode();
        }
        int hashCode6;
        if (o5 == null) {
            hashCode6 = 0;
        }
        else {
            hashCode6 = o5.hashCode();
        }
        if (o6 != null) {
            hashCode = o6.hashCode();
        }
        return hashCode(hashCode2, hashCode3, hashCode4, hashCode5, hashCode6, hashCode);
    }
}
