// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.util;

import java.io.UnsupportedEncodingException;
import java.io.IOException;

public class Base64
{
    private static final byte[] _ORDERED_ALPHABET;
    private static final byte[] _ORDERED_DECODABET;
    private static final byte[] _STANDARD_ALPHABET;
    private static final byte[] _STANDARD_DECODABET;
    private static final byte[] _URL_SAFE_ALPHABET;
    private static final byte[] _URL_SAFE_DECODABET;
    
    static {
        _STANDARD_ALPHABET = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47 };
        _STANDARD_DECODABET = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 };
        _URL_SAFE_ALPHABET = new byte[] { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95 };
        _URL_SAFE_DECODABET = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -9, -9, -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 };
        _ORDERED_ALPHABET = new byte[] { 45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122 };
        _ORDERED_DECODABET = new byte[] { -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, -9, -9, -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, -9 };
    }
    
    private static int decode4to3(final byte[] array, int n, final byte[] array2, final int n2, final int n3) {
        if (array == null) {
            throw new NullPointerException("Source array was null.");
        }
        if (array2 == null) {
            throw new NullPointerException("Destination array was null.");
        }
        if (n < 0 || n + 3 >= array.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and still process four bytes.", array.length, n));
        }
        if (n2 < 0 || n2 + 2 >= array2.length) {
            throw new IllegalArgumentException(String.format("Destination array with length %d cannot have offset of %d and still store three bytes.", array2.length, n2));
        }
        final byte[] decodabet = getDecodabet(n3);
        if (array[n + 2] == 61) {
            array2[n2] = (byte)(((decodabet[array[n]] & 0xFF) << 18 | (decodabet[array[n + 1]] & 0xFF) << 12) >>> 16);
            return 1;
        }
        if (array[n + 3] == 61) {
            n = ((decodabet[array[n]] & 0xFF) << 18 | (decodabet[array[n + 1]] & 0xFF) << 12 | (decodabet[array[n + 2]] & 0xFF) << 6);
            array2[n2] = (byte)(n >>> 16);
            array2[n2 + 1] = (byte)(n >>> 8);
            return 2;
        }
        n = ((decodabet[array[n]] & 0xFF) << 18 | (decodabet[array[n + 1]] & 0xFF) << 12 | (decodabet[array[n + 2]] & 0xFF) << 6 | (decodabet[array[n + 3]] & 0xFF));
        array2[n2] = (byte)(n >> 16);
        array2[n2 + 1] = (byte)(n >> 8);
        array2[n2 + 2] = (byte)n;
        return 3;
    }
    
    private static byte[] encode3to4(final byte[] array, int n, final int n2, final byte[] array2, final int n3, int n4) {
        int n5 = 0;
        final byte[] alphabet = getAlphabet(n4);
        if (n2 > 0) {
            n4 = array[n] << 24 >>> 8;
        }
        else {
            n4 = 0;
        }
        int n6;
        if (n2 > 1) {
            n6 = array[n + 1] << 24 >>> 16;
        }
        else {
            n6 = 0;
        }
        if (n2 > 2) {
            n5 = array[n + 2] << 24 >>> 24;
        }
        n = (n5 | (n6 | n4));
        switch (n2) {
            default: {
                return array2;
            }
            case 3: {
                array2[n3] = alphabet[n >>> 18];
                array2[n3 + 1] = alphabet[n >>> 12 & 0x3F];
                array2[n3 + 2] = alphabet[n >>> 6 & 0x3F];
                array2[n3 + 3] = alphabet[n & 0x3F];
                return array2;
            }
            case 2: {
                array2[n3] = alphabet[n >>> 18];
                array2[n3 + 1] = alphabet[n >>> 12 & 0x3F];
                array2[n3 + 2] = alphabet[n >>> 6 & 0x3F];
                array2[n3 + 3] = 61;
                return array2;
            }
            case 1: {
                array2[n3] = alphabet[n >>> 18];
                array2[n3 + 1] = alphabet[n >>> 12 & 0x3F];
                array2[n3 + 3] = (array2[n3 + 2] = 61);
                return array2;
            }
        }
    }
    
    private static byte[] encode3to4(final byte[] array, final byte[] array2, final int n, final int n2) {
        encode3to4(array2, 0, n, array, 0, n2);
        return array;
    }
    
    public static String encodeBytes(final byte[] array) {
        final String s = null;
        String encodeBytes;
        while (true) {
            try {
                encodeBytes = encodeBytes(array, 0, array.length, 0);
                assert encodeBytes != null;
            }
            catch (IOException ex) {
                encodeBytes = s;
                assert false : ex.getMessage();
                continue;
            }
            break;
        }
        return encodeBytes;
    }
    
    public static String encodeBytes(byte[] encodeBytesToBytes, final int n, final int n2, final int n3) {
        encodeBytesToBytes = encodeBytesToBytes(encodeBytesToBytes, n, n2, n3);
        try {
            return new String(encodeBytesToBytes, "US-ASCII");
        }
        catch (UnsupportedEncodingException ex) {
            return new String(encodeBytesToBytes);
        }
    }
    
    public static byte[] encodeBytesToBytes(final byte[] p0, final int p1, final int p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          14
        //     3: aconst_null    
        //     4: astore          13
        //     6: aload_0        
        //     7: ifnonnull       20
        //    10: new             Ljava/lang/NullPointerException;
        //    13: dup            
        //    14: ldc             "Cannot serialize a null array."
        //    16: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
        //    19: athrow         
        //    20: iload_1        
        //    21: ifge            51
        //    24: new             Ljava/lang/IllegalArgumentException;
        //    27: dup            
        //    28: new             Ljava/lang/StringBuilder;
        //    31: dup            
        //    32: invokespecial   java/lang/StringBuilder.<init>:()V
        //    35: ldc             "Cannot have negative offset: "
        //    37: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    40: iload_1        
        //    41: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    44: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    47: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    50: athrow         
        //    51: iload_2        
        //    52: ifge            83
        //    55: new             Ljava/lang/IllegalArgumentException;
        //    58: dup            
        //    59: new             Ljava/lang/StringBuilder;
        //    62: dup            
        //    63: invokespecial   java/lang/StringBuilder.<init>:()V
        //    66: ldc_w           "Cannot have length offset: "
        //    69: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    72: iload_2        
        //    73: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    76: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    79: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    82: athrow         
        //    83: iload_1        
        //    84: iload_2        
        //    85: iadd           
        //    86: aload_0        
        //    87: arraylength    
        //    88: if_icmple       131
        //    91: new             Ljava/lang/IllegalArgumentException;
        //    94: dup            
        //    95: ldc_w           "Cannot have offset of %d and length of %d with array of length %d"
        //    98: iconst_3       
        //    99: anewarray       Ljava/lang/Object;
        //   102: dup            
        //   103: iconst_0       
        //   104: iload_1        
        //   105: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   108: aastore        
        //   109: dup            
        //   110: iconst_1       
        //   111: iload_2        
        //   112: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   115: aastore        
        //   116: dup            
        //   117: iconst_2       
        //   118: aload_0        
        //   119: arraylength    
        //   120: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   123: aastore        
        //   124: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   127: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   130: athrow         
        //   131: iload_3        
        //   132: iconst_2       
        //   133: iand           
        //   134: ifeq            250
        //   137: new             Ljava/io/ByteArrayOutputStream;
        //   140: dup            
        //   141: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //   144: astore          10
        //   146: new             Lorg/java_websocket/util/Base64$OutputStream;
        //   149: dup            
        //   150: aload           10
        //   152: iload_3        
        //   153: iconst_1       
        //   154: ior            
        //   155: invokespecial   org/java_websocket/util/Base64$OutputStream.<init>:(Ljava/io/OutputStream;I)V
        //   158: astore          12
        //   160: new             Ljava/util/zip/GZIPOutputStream;
        //   163: dup            
        //   164: aload           12
        //   166: invokespecial   java/util/zip/GZIPOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   169: astore          11
        //   171: aload           11
        //   173: aload_0        
        //   174: iload_1        
        //   175: iload_2        
        //   176: invokevirtual   java/util/zip/GZIPOutputStream.write:([BII)V
        //   179: aload           11
        //   181: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   184: aload           11
        //   186: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   189: aload           12
        //   191: invokevirtual   org/java_websocket/util/Base64$OutputStream.close:()V
        //   194: aload           10
        //   196: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   199: aload           10
        //   201: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   204: astore_0       
        //   205: aload_0        
        //   206: areturn        
        //   207: astore          11
        //   209: aconst_null    
        //   210: astore          10
        //   212: aconst_null    
        //   213: astore_0       
        //   214: aload           13
        //   216: astore          12
        //   218: aload           11
        //   220: athrow         
        //   221: astore          14
        //   223: aload_0        
        //   224: astore          13
        //   226: aload           10
        //   228: astore          11
        //   230: aload           14
        //   232: astore_0       
        //   233: aload           12
        //   235: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   238: aload           11
        //   240: invokevirtual   org/java_websocket/util/Base64$OutputStream.close:()V
        //   243: aload           13
        //   245: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   248: aload_0        
        //   249: athrow         
        //   250: iload_3        
        //   251: bipush          8
        //   253: iand           
        //   254: ifeq            407
        //   257: iconst_1       
        //   258: istore          6
        //   260: iload_2        
        //   261: iconst_3       
        //   262: idiv           
        //   263: istore          5
        //   265: iload_2        
        //   266: iconst_3       
        //   267: irem           
        //   268: ifle            413
        //   271: iconst_4       
        //   272: istore          4
        //   274: iload           4
        //   276: iload           5
        //   278: iconst_4       
        //   279: imul           
        //   280: iadd           
        //   281: istore          5
        //   283: iload           5
        //   285: istore          4
        //   287: iload           6
        //   289: ifeq            302
        //   292: iload           5
        //   294: iload           5
        //   296: bipush          76
        //   298: idiv           
        //   299: iadd           
        //   300: istore          4
        //   302: iload           4
        //   304: newarray        B
        //   306: astore          10
        //   308: iconst_0       
        //   309: istore          5
        //   311: iconst_0       
        //   312: istore          4
        //   314: iconst_0       
        //   315: istore          7
        //   317: iload           7
        //   319: iload_2        
        //   320: iconst_2       
        //   321: isub           
        //   322: if_icmpge       419
        //   325: aload_0        
        //   326: iload           7
        //   328: iload_1        
        //   329: iadd           
        //   330: iconst_3       
        //   331: aload           10
        //   333: iload           4
        //   335: iload_3        
        //   336: invokestatic    org/java_websocket/util/Base64.encode3to4:([BII[BII)[B
        //   339: pop            
        //   340: iload           5
        //   342: iconst_4       
        //   343: iadd           
        //   344: istore          9
        //   346: iload           9
        //   348: istore          5
        //   350: iload           4
        //   352: istore          8
        //   354: iload           6
        //   356: ifeq            392
        //   359: iload           9
        //   361: istore          5
        //   363: iload           4
        //   365: istore          8
        //   367: iload           9
        //   369: bipush          76
        //   371: if_icmplt       392
        //   374: aload           10
        //   376: iload           4
        //   378: iconst_4       
        //   379: iadd           
        //   380: bipush          10
        //   382: bastore        
        //   383: iload           4
        //   385: iconst_1       
        //   386: iadd           
        //   387: istore          8
        //   389: iconst_0       
        //   390: istore          5
        //   392: iload           8
        //   394: iconst_4       
        //   395: iadd           
        //   396: istore          4
        //   398: iload           7
        //   400: iconst_3       
        //   401: iadd           
        //   402: istore          7
        //   404: goto            317
        //   407: iconst_0       
        //   408: istore          6
        //   410: goto            260
        //   413: iconst_0       
        //   414: istore          4
        //   416: goto            274
        //   419: iload           4
        //   421: istore          5
        //   423: iload           7
        //   425: iload_2        
        //   426: if_icmpge       453
        //   429: aload_0        
        //   430: iload           7
        //   432: iload_1        
        //   433: iadd           
        //   434: iload_2        
        //   435: iload           7
        //   437: isub           
        //   438: aload           10
        //   440: iload           4
        //   442: iload_3        
        //   443: invokestatic    org/java_websocket/util/Base64.encode3to4:([BII[BII)[B
        //   446: pop            
        //   447: iload           4
        //   449: iconst_4       
        //   450: iadd           
        //   451: istore          5
        //   453: aload           10
        //   455: astore_0       
        //   456: iload           5
        //   458: aload           10
        //   460: arraylength    
        //   461: iconst_1       
        //   462: isub           
        //   463: if_icmpgt       205
        //   466: iload           5
        //   468: newarray        B
        //   470: astore_0       
        //   471: aload           10
        //   473: iconst_0       
        //   474: aload_0        
        //   475: iconst_0       
        //   476: iload           5
        //   478: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //   481: aload_0        
        //   482: areturn        
        //   483: astore_0       
        //   484: goto            189
        //   487: astore_0       
        //   488: goto            194
        //   491: astore_0       
        //   492: goto            199
        //   495: astore          10
        //   497: goto            238
        //   500: astore          10
        //   502: goto            243
        //   505: astore          10
        //   507: goto            248
        //   510: astore_0       
        //   511: aconst_null    
        //   512: astore          11
        //   514: aconst_null    
        //   515: astore          13
        //   517: aload           14
        //   519: astore          12
        //   521: goto            233
        //   524: astore_0       
        //   525: aconst_null    
        //   526: astore          11
        //   528: aload           14
        //   530: astore          12
        //   532: aload           10
        //   534: astore          13
        //   536: goto            233
        //   539: astore_0       
        //   540: aload           12
        //   542: astore          11
        //   544: aload           14
        //   546: astore          12
        //   548: aload           10
        //   550: astore          13
        //   552: goto            233
        //   555: astore_0       
        //   556: aload           11
        //   558: astore          13
        //   560: aload           12
        //   562: astore          11
        //   564: aload           13
        //   566: astore          12
        //   568: aload           10
        //   570: astore          13
        //   572: goto            233
        //   575: astore          11
        //   577: aconst_null    
        //   578: astore          12
        //   580: aload           10
        //   582: astore_0       
        //   583: aload           12
        //   585: astore          10
        //   587: aload           13
        //   589: astore          12
        //   591: goto            218
        //   594: astore          11
        //   596: aload           10
        //   598: astore_0       
        //   599: aload           12
        //   601: astore          10
        //   603: aload           13
        //   605: astore          12
        //   607: goto            218
        //   610: astore          14
        //   612: aload           11
        //   614: astore          13
        //   616: aload           10
        //   618: astore_0       
        //   619: aload           14
        //   621: astore          11
        //   623: aload           12
        //   625: astore          10
        //   627: aload           13
        //   629: astore          12
        //   631: goto            218
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  137    146    207    218    Ljava/io/IOException;
        //  137    146    510    524    Any
        //  146    160    575    594    Ljava/io/IOException;
        //  146    160    524    539    Any
        //  160    171    594    610    Ljava/io/IOException;
        //  160    171    539    555    Any
        //  171    184    610    634    Ljava/io/IOException;
        //  171    184    555    575    Any
        //  184    189    483    487    Ljava/lang/Exception;
        //  189    194    487    491    Ljava/lang/Exception;
        //  194    199    491    495    Ljava/lang/Exception;
        //  218    221    221    233    Any
        //  233    238    495    500    Ljava/lang/Exception;
        //  238    243    500    505    Ljava/lang/Exception;
        //  243    248    505    510    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 348, Size: 348
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3551)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:210)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static final byte[] getAlphabet(final int n) {
        if ((n & 0x10) == 0x10) {
            return Base64._URL_SAFE_ALPHABET;
        }
        if ((n & 0x20) == 0x20) {
            return Base64._ORDERED_ALPHABET;
        }
        return Base64._STANDARD_ALPHABET;
    }
    
    private static final byte[] getDecodabet(final int n) {
        if ((n & 0x10) == 0x10) {
            return Base64._URL_SAFE_DECODABET;
        }
        if ((n & 0x20) == 0x20) {
            return Base64._ORDERED_DECODABET;
        }
        return Base64._STANDARD_DECODABET;
    }
}
