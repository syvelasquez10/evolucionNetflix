// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.math.BigInteger;
import android.util.SparseIntArray;

public class BaseConverter
{
    protected static final char[] baseChars;
    protected static final SparseIntArray hashCharToIntMap;
    
    static {
        baseChars = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '_', '-', '.', '*', '(', ')', ',', '@', '\'', '$', ':', ';', '!' };
        hashCharToIntMap = new SparseIntArray();
        initMap();
    }
    
    private static BigInteger convertFromBaseToBigInteger(final String s, int n) {
        int i = 0;
        final BigInteger bigInteger = new BigInteger("" + n);
        final BigInteger bigInteger2 = new BigInteger("0");
        final String string = new StringBuilder(s).reverse().toString();
        BigInteger add = bigInteger2;
        for (n = 0; i < string.length(); ++i, ++n) {
            add = add.add(bigInteger.pow(n).multiply(new BigInteger("" + getIntValue(string.charAt(i)))));
        }
        return add;
    }
    
    public static String convertFromBaseToInteger(final String s, final int n) {
        return convertFromBaseToBigInteger(s, n).toString();
    }
    
    public static String convertFromBaseToString(final String s, final int n) {
        return new String(convertFromBaseToBigInteger(s, n).toByteArray());
    }
    
    public static String convertToBase(BigInteger subtract, int n) {
        final BigInteger bigInteger = new BigInteger("" + n);
        final StringBuilder sb = new StringBuilder();
        final BigInteger[] divideAndRemainder = subtract.divideAndRemainder(bigInteger);
        BigInteger bigInteger2 = divideAndRemainder[0];
        BigInteger bigInteger3 = divideAndRemainder[1];
        sb.append(BaseConverter.baseChars[bigInteger3.intValue()]);
        n = 1;
        while (bigInteger2.intValue() != 0) {
            subtract = subtract.subtract(bigInteger3);
            ++n;
            final BigInteger[] divideAndRemainder2 = subtract.divideAndRemainder(bigInteger.pow(n));
            bigInteger2 = divideAndRemainder2[0];
            bigInteger3 = divideAndRemainder2[1];
            sb.append(BaseConverter.baseChars[bigInteger3.divide(bigInteger.pow(n - 1)).intValue()]);
        }
        return sb.reverse().toString();
    }
    
    private static int getIntValue(final char c) {
        final Integer value = BaseConverter.hashCharToIntMap.get((int)c);
        if (value != null) {
            return value;
        }
        throw new NumberFormatException("Value out of range " + c);
    }
    
    static void initMap() {
        for (int i = 0; i < BaseConverter.baseChars.length; ++i) {
            BaseConverter.hashCharToIntMap.put((int)BaseConverter.baseChars[i], (int)Integer.valueOf(i));
        }
    }
}
