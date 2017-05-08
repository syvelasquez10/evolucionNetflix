// 
// Decompiled by Procyon v0.5.30
// 

package com.crittercism.internal;

public class cp extends Throwable
{
    private static final long serialVersionUID = -1947260712494608235L;
    public String b;
    
    public cp(final String s, String b, final String s2) {
        super(b);
        this.b = null;
        b = s;
        if (s == null) {
            b = "";
        }
        if (b.length() > 0) {
            this.b = b;
        }
        else {
            this.b = "JavaScript Exception";
        }
        String[] split;
        if (s2 == null) {
            split = new String[0];
        }
        else {
            split = s2.split("\\r?\\n");
        }
        this.setStackTrace(a(split));
    }
    
    private static StackTraceElement[] a(final String[] array) {
        final int n = 0;
        StackTraceElement[] array2;
        boolean b;
        if (array.length >= 2 && array[0] != null && array[1] != null && array[0].equals(array[1])) {
            array2 = new StackTraceElement[array.length - 1];
            b = true;
        }
        else {
            b = false;
            array2 = null;
        }
        int i = n;
        if (!b) {
            array2 = new StackTraceElement[array.length];
            i = n;
        }
        while (i < array.length) {
            if (i != 0 || !b) {
                int n2;
                if (b) {
                    n2 = i - 1;
                }
                else {
                    n2 = i;
                }
                array2[n2] = new StackTraceElement("", array[i], "", -1);
            }
            ++i;
        }
        return array2;
    }
    
    @Override
    public String toString() {
        final String localizedMessage = this.getLocalizedMessage();
        final String b = this.b;
        if (localizedMessage == null) {
            return b;
        }
        return b + ": " + localizedMessage;
    }
}
