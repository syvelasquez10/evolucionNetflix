// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.io.IOException;

public final class Base64
{
    public static final int DECODE = 0;
    public static final int DONT_GUNZIP = 4;
    public static final int DO_BREAK_LINES = 8;
    public static final int ENCODE = 1;
    private static final byte EQUALS_SIGN = 61;
    private static final byte EQUALS_SIGN_ENC = -1;
    public static final int GZIP = 2;
    private static final int MAX_LINE_LENGTH = 76;
    private static final byte NEW_LINE = 10;
    public static final int NO_OPTIONS = 0;
    public static final int ORDERED = 32;
    private static final String PREFERRED_ENCODING = "US-ASCII";
    public static final int URL_SAFE = 16;
    private static final byte WHITE_SPACE_ENC = -5;
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
    
    public static byte[] decode(final String s) {
        return decode(s, 0);
    }
    
    public static byte[] decode(final String p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aload_0        
        //     3: ifnonnull       16
        //     6: new             Ljava/lang/IllegalArgumentException;
        //     9: dup            
        //    10: ldc             "Input string was null."
        //    12: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    15: athrow         
        //    16: aload_0        
        //    17: ldc             "US-ASCII"
        //    19: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //    22: astore_2       
        //    23: aload_2        
        //    24: astore_0       
        //    25: aload_0        
        //    26: iconst_0       
        //    27: aload_0        
        //    28: arraylength    
        //    29: iload_1        
        //    30: invokestatic    com/netflix/mediaclient/util/Base64.decode:([BIII)[B
        //    33: astore_0       
        //    34: iload_1        
        //    35: iconst_4       
        //    36: iand           
        //    37: ifeq            176
        //    40: iconst_1       
        //    41: istore_1       
        //    42: aload_0        
        //    43: astore_2       
        //    44: aload_0        
        //    45: ifnull          201
        //    48: aload_0        
        //    49: astore_2       
        //    50: aload_0        
        //    51: arraylength    
        //    52: iconst_4       
        //    53: if_icmplt       201
        //    56: aload_0        
        //    57: astore_2       
        //    58: iload_1        
        //    59: ifne            201
        //    62: aload_0        
        //    63: astore_2       
        //    64: ldc             35615
        //    66: aload_0        
        //    67: iconst_0       
        //    68: baload         
        //    69: sipush          255
        //    72: iand           
        //    73: aload_0        
        //    74: iconst_1       
        //    75: baload         
        //    76: bipush          8
        //    78: ishl           
        //    79: ldc             65280
        //    81: iand           
        //    82: ior            
        //    83: if_icmpne       201
        //    86: sipush          2048
        //    89: newarray        B
        //    91: astore_2       
        //    92: new             Ljava/io/ByteArrayOutputStream;
        //    95: dup            
        //    96: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    99: astore          4
        //   101: new             Ljava/io/ByteArrayInputStream;
        //   104: dup            
        //   105: aload_0        
        //   106: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //   109: astore_0       
        //   110: new             Ljava/util/zip/GZIPInputStream;
        //   113: dup            
        //   114: aload_0        
        //   115: invokespecial   java/util/zip/GZIPInputStream.<init>:(Ljava/io/InputStream;)V
        //   118: astore          5
        //   120: aload           5
        //   122: aload_2        
        //   123: invokevirtual   java/util/zip/GZIPInputStream.read:([B)I
        //   126: istore_1       
        //   127: iload_1        
        //   128: iflt            181
        //   131: aload           4
        //   133: aload_2        
        //   134: iconst_0       
        //   135: iload_1        
        //   136: invokevirtual   java/io/ByteArrayOutputStream.write:([BII)V
        //   139: goto            120
        //   142: astore          6
        //   144: aload           5
        //   146: astore_3       
        //   147: aload_0        
        //   148: astore_2       
        //   149: aload           6
        //   151: astore_0       
        //   152: aload           4
        //   154: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   157: aload_3        
        //   158: invokevirtual   java/util/zip/GZIPInputStream.close:()V
        //   161: aload_2        
        //   162: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   165: aload_0        
        //   166: athrow         
        //   167: astore_2       
        //   168: aload_0        
        //   169: invokevirtual   java/lang/String.getBytes:()[B
        //   172: astore_0       
        //   173: goto            25
        //   176: iconst_0       
        //   177: istore_1       
        //   178: goto            42
        //   181: aload           4
        //   183: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   186: astore_2       
        //   187: aload           4
        //   189: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   192: aload           5
        //   194: invokevirtual   java/util/zip/GZIPInputStream.close:()V
        //   197: aload_0        
        //   198: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   201: aload_2        
        //   202: areturn        
        //   203: astore_3       
        //   204: goto            192
        //   207: astore_3       
        //   208: goto            197
        //   211: astore_0       
        //   212: aload_2        
        //   213: areturn        
        //   214: astore          4
        //   216: goto            157
        //   219: astore_3       
        //   220: goto            161
        //   223: astore_2       
        //   224: goto            165
        //   227: astore_0       
        //   228: aconst_null    
        //   229: astore          4
        //   231: aconst_null    
        //   232: astore_2       
        //   233: goto            152
        //   236: astore_0       
        //   237: aconst_null    
        //   238: astore_2       
        //   239: goto            152
        //   242: astore          5
        //   244: aload_0        
        //   245: astore_2       
        //   246: aload           5
        //   248: astore_0       
        //   249: goto            152
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  16     23     167    176    Ljava/io/UnsupportedEncodingException;
        //  92     101    227    236    Any
        //  101    110    236    242    Any
        //  110    120    242    252    Any
        //  120    127    142    152    Any
        //  131    139    142    152    Any
        //  152    157    214    219    Ljava/lang/Exception;
        //  157    161    219    223    Ljava/lang/Exception;
        //  161    165    223    227    Ljava/lang/Exception;
        //  181    187    142    152    Any
        //  187    192    203    207    Ljava/lang/Exception;
        //  192    197    207    211    Ljava/lang/Exception;
        //  197    201    211    214    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 151, Size: 151
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3417)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3417)
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
    
    public static byte[] decode(final byte[] array) {
        return decode(array, 0, array.length, 0);
    }
    
    public static byte[] decode(byte[] array, final int n, final int n2, final int n3) {
        if (array == null) {
            throw new IllegalArgumentException("Cannot decode null source array.");
        }
        if (n < 0 || n + n2 > array.length) {
            throw new IllegalArgumentException(String.format("Source array with length %d cannot have offset of %d and process %d bytes.", array.length, n, n2));
        }
        if (n2 == 0) {
            return new byte[0];
        }
        if (n2 < 4) {
            throw new IllegalArgumentException("Base64-encoded string must have at least four characters, but length specified was " + n2);
        }
        final byte[] decodabet = getDecodabet(n3);
        final byte[] array2 = new byte[n2 * 3 / 4];
        final byte[] array3 = new byte[4];
        int i = n;
        int n4 = 0;
        int n5 = 0;
        while (true) {
            while (i < n + n2) {
                final byte b = decodabet[array[i] & 0xFF];
                if (b < -5) {
                    throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", array[i] & 0xFF, i));
                }
                int n7;
                int n8;
                if (b >= -1) {
                    final int n6 = n4 + 1;
                    array3[n4] = array[i];
                    if (n6 > 3) {
                        n7 = decode4to3(array3, 0, array2, n5, n3) + n5;
                        if (array[i] == 61) {
                            array = new byte[n7];
                            System.arraycopy(array2, 0, array, 0, n7);
                            return array;
                        }
                        n8 = 0;
                    }
                    else {
                        n7 = n5;
                        n8 = n6;
                    }
                }
                else {
                    final int n9 = n5;
                    n8 = n4;
                    n7 = n9;
                }
                final int n10 = i + 1;
                final int n11 = n7;
                n4 = n8;
                n5 = n11;
                i = n10;
            }
            int n7 = n5;
            continue;
        }
    }
    
    private static int decode4to3(final byte[] array, int n, final byte[] array2, final int n2, final int n3) {
        if (array == null) {
            throw new IllegalArgumentException("Source array was null.");
        }
        if (array2 == null) {
            throw new IllegalArgumentException("Destination array was null.");
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
    
    public static void decodeFileToFile(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/netflix/mediaclient/util/Base64.decodeFromFile:(Ljava/lang/String;)[B
        //     4: astore_0       
        //     5: new             Ljava/io/BufferedOutputStream;
        //     8: dup            
        //     9: new             Ljava/io/FileOutputStream;
        //    12: dup            
        //    13: aload_1        
        //    14: invokespecial   java/io/FileOutputStream.<init>:(Ljava/lang/String;)V
        //    17: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    20: astore_1       
        //    21: aload_1        
        //    22: aload_0        
        //    23: invokevirtual   java/io/OutputStream.write:([B)V
        //    26: aload_1        
        //    27: invokevirtual   java/io/OutputStream.close:()V
        //    30: return         
        //    31: astore_0       
        //    32: aconst_null    
        //    33: astore_1       
        //    34: aload_1        
        //    35: invokevirtual   java/io/OutputStream.close:()V
        //    38: aload_0        
        //    39: athrow         
        //    40: astore_0       
        //    41: return         
        //    42: astore_1       
        //    43: goto            38
        //    46: astore_0       
        //    47: goto            34
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      21     31     34     Any
        //  21     26     46     50     Any
        //  26     30     40     42     Ljava/lang/Exception;
        //  34     38     42     46     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0034:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    public static byte[] decodeFromFile(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_1       
        //     2: aconst_null    
        //     3: astore_3       
        //     4: new             Ljava/io/File;
        //     7: dup            
        //     8: aload_0        
        //     9: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    12: astore_0       
        //    13: aload_0        
        //    14: invokevirtual   java/io/File.length:()J
        //    17: ldc2_w          2147483647
        //    20: lcmp           
        //    21: ifle            68
        //    24: new             Ljava/io/IOException;
        //    27: dup            
        //    28: new             Ljava/lang/StringBuilder;
        //    31: dup            
        //    32: invokespecial   java/lang/StringBuilder.<init>:()V
        //    35: ldc_w           "File is too big for this convenience method ("
        //    38: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    41: aload_0        
        //    42: invokevirtual   java/io/File.length:()J
        //    45: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //    48: ldc_w           " bytes)."
        //    51: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    54: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    57: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //    60: athrow         
        //    61: astore_0       
        //    62: aload_3        
        //    63: invokevirtual   com/netflix/mediaclient/util/Base64$Base64InputStream.close:()V
        //    66: aload_0        
        //    67: athrow         
        //    68: aload_0        
        //    69: invokevirtual   java/io/File.length:()J
        //    72: l2i            
        //    73: newarray        B
        //    75: astore          4
        //    77: new             Lcom/netflix/mediaclient/util/Base64$Base64InputStream;
        //    80: dup            
        //    81: new             Ljava/io/BufferedInputStream;
        //    84: dup            
        //    85: new             Ljava/io/FileInputStream;
        //    88: dup            
        //    89: aload_0        
        //    90: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    93: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    96: iconst_0       
        //    97: invokespecial   com/netflix/mediaclient/util/Base64$Base64InputStream.<init>:(Ljava/io/InputStream;I)V
        //   100: astore_0       
        //   101: aload_0        
        //   102: aload           4
        //   104: iload_1        
        //   105: sipush          4096
        //   108: invokevirtual   com/netflix/mediaclient/util/Base64$Base64InputStream.read:([BII)I
        //   111: istore_2       
        //   112: iload_2        
        //   113: iflt            123
        //   116: iload_1        
        //   117: iload_2        
        //   118: iadd           
        //   119: istore_1       
        //   120: goto            101
        //   123: iload_1        
        //   124: newarray        B
        //   126: astore_3       
        //   127: aload           4
        //   129: iconst_0       
        //   130: aload_3        
        //   131: iconst_0       
        //   132: iload_1        
        //   133: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //   136: aload_0        
        //   137: invokevirtual   com/netflix/mediaclient/util/Base64$Base64InputStream.close:()V
        //   140: aload_3        
        //   141: areturn        
        //   142: astore_0       
        //   143: aload_3        
        //   144: areturn        
        //   145: astore_3       
        //   146: goto            66
        //   149: astore          4
        //   151: aload_0        
        //   152: astore_3       
        //   153: aload           4
        //   155: astore_0       
        //   156: goto            62
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      61     61     62     Any
        //  62     66     145    149    Ljava/lang/Exception;
        //  68     101    61     62     Any
        //  101    112    149    159    Any
        //  123    136    149    159    Any
        //  136    140    142    145    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0101:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    public static void decodeToFile(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: new             Lcom/netflix/mediaclient/util/Base64$Base64OutputStream;
        //     3: dup            
        //     4: new             Ljava/io/FileOutputStream;
        //     7: dup            
        //     8: aload_1        
        //     9: invokespecial   java/io/FileOutputStream.<init>:(Ljava/lang/String;)V
        //    12: iconst_0       
        //    13: invokespecial   com/netflix/mediaclient/util/Base64$Base64OutputStream.<init>:(Ljava/io/OutputStream;I)V
        //    16: astore_1       
        //    17: aload_1        
        //    18: aload_0        
        //    19: ldc             "US-ASCII"
        //    21: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //    24: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.write:([B)V
        //    27: aload_1        
        //    28: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.close:()V
        //    31: return         
        //    32: astore_0       
        //    33: aconst_null    
        //    34: astore_1       
        //    35: aload_1        
        //    36: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.close:()V
        //    39: aload_0        
        //    40: athrow         
        //    41: astore_0       
        //    42: return         
        //    43: astore_1       
        //    44: goto            39
        //    47: astore_0       
        //    48: goto            35
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      17     32     35     Any
        //  17     27     47     51     Any
        //  27     31     41     43     Ljava/lang/Exception;
        //  35     39     43     47     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0035:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
        return encodeBytes(array, 0, array.length, 0);
    }
    
    public static String encodeBytes(final byte[] array, final int n) {
        return encodeBytes(array, 0, array.length, n);
    }
    
    public static String encodeBytes(final byte[] array, final int n, final int n2) {
        return encodeBytes(array, n, n2, 0);
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
    
    public static byte[] encodeBytesToBytes(byte[] encodeBytesToBytes) {
        final byte[] array = null;
        try {
            encodeBytesToBytes = encodeBytesToBytes(encodeBytesToBytes, 0, encodeBytesToBytes.length, 0);
            return encodeBytesToBytes;
        }
        catch (IOException ex) {
            encodeBytesToBytes = array;
            assert false : "IOExceptions only come from GZipping, which is turned off: " + ex.getMessage();
            return encodeBytesToBytes;
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
        //     7: ifnonnull       21
        //    10: new             Ljava/lang/NullPointerException;
        //    13: dup            
        //    14: ldc_w           "Cannot serialize a null array."
        //    17: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
        //    20: athrow         
        //    21: iload_1        
        //    22: ifge            53
        //    25: new             Ljava/lang/IllegalArgumentException;
        //    28: dup            
        //    29: new             Ljava/lang/StringBuilder;
        //    32: dup            
        //    33: invokespecial   java/lang/StringBuilder.<init>:()V
        //    36: ldc_w           "Cannot have negative offset: "
        //    39: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    42: iload_1        
        //    43: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    46: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    49: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    52: athrow         
        //    53: iload_2        
        //    54: ifge            85
        //    57: new             Ljava/lang/IllegalArgumentException;
        //    60: dup            
        //    61: new             Ljava/lang/StringBuilder;
        //    64: dup            
        //    65: invokespecial   java/lang/StringBuilder.<init>:()V
        //    68: ldc_w           "Cannot have length offset: "
        //    71: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    74: iload_2        
        //    75: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    78: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    81: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: iload_1        
        //    86: iload_2        
        //    87: iadd           
        //    88: aload_0        
        //    89: arraylength    
        //    90: if_icmple       133
        //    93: new             Ljava/lang/IllegalArgumentException;
        //    96: dup            
        //    97: ldc_w           "Cannot have offset of %d and length of %d with array of length %d"
        //   100: iconst_3       
        //   101: anewarray       Ljava/lang/Object;
        //   104: dup            
        //   105: iconst_0       
        //   106: iload_1        
        //   107: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   110: aastore        
        //   111: dup            
        //   112: iconst_1       
        //   113: iload_2        
        //   114: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   117: aastore        
        //   118: dup            
        //   119: iconst_2       
        //   120: aload_0        
        //   121: arraylength    
        //   122: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   125: aastore        
        //   126: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   129: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   132: athrow         
        //   133: iload_3        
        //   134: iconst_2       
        //   135: iand           
        //   136: ifeq            252
        //   139: new             Ljava/io/ByteArrayOutputStream;
        //   142: dup            
        //   143: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //   146: astore          10
        //   148: new             Lcom/netflix/mediaclient/util/Base64$Base64OutputStream;
        //   151: dup            
        //   152: aload           10
        //   154: iload_3        
        //   155: iconst_1       
        //   156: ior            
        //   157: invokespecial   com/netflix/mediaclient/util/Base64$Base64OutputStream.<init>:(Ljava/io/OutputStream;I)V
        //   160: astore          12
        //   162: new             Ljava/util/zip/GZIPOutputStream;
        //   165: dup            
        //   166: aload           12
        //   168: invokespecial   java/util/zip/GZIPOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   171: astore          11
        //   173: aload           11
        //   175: aload_0        
        //   176: iload_1        
        //   177: iload_2        
        //   178: invokevirtual   java/util/zip/GZIPOutputStream.write:([BII)V
        //   181: aload           11
        //   183: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   186: aload           11
        //   188: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   191: aload           12
        //   193: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.close:()V
        //   196: aload           10
        //   198: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   201: aload           10
        //   203: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   206: astore_0       
        //   207: aload_0        
        //   208: areturn        
        //   209: astore          11
        //   211: aconst_null    
        //   212: astore          10
        //   214: aconst_null    
        //   215: astore_0       
        //   216: aload           13
        //   218: astore          12
        //   220: aload           11
        //   222: athrow         
        //   223: astore          14
        //   225: aload_0        
        //   226: astore          13
        //   228: aload           10
        //   230: astore          11
        //   232: aload           14
        //   234: astore_0       
        //   235: aload           12
        //   237: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   240: aload           11
        //   242: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.close:()V
        //   245: aload           13
        //   247: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   250: aload_0        
        //   251: athrow         
        //   252: iload_3        
        //   253: bipush          8
        //   255: iand           
        //   256: ifeq            409
        //   259: iconst_1       
        //   260: istore          6
        //   262: iload_2        
        //   263: iconst_3       
        //   264: idiv           
        //   265: istore          5
        //   267: iload_2        
        //   268: iconst_3       
        //   269: irem           
        //   270: ifle            415
        //   273: iconst_4       
        //   274: istore          4
        //   276: iload           4
        //   278: iload           5
        //   280: iconst_4       
        //   281: imul           
        //   282: iadd           
        //   283: istore          5
        //   285: iload           5
        //   287: istore          4
        //   289: iload           6
        //   291: ifeq            304
        //   294: iload           5
        //   296: iload           5
        //   298: bipush          76
        //   300: idiv           
        //   301: iadd           
        //   302: istore          4
        //   304: iload           4
        //   306: newarray        B
        //   308: astore          10
        //   310: iconst_0       
        //   311: istore          5
        //   313: iconst_0       
        //   314: istore          4
        //   316: iconst_0       
        //   317: istore          7
        //   319: iload           7
        //   321: iload_2        
        //   322: iconst_2       
        //   323: isub           
        //   324: if_icmpge       421
        //   327: aload_0        
        //   328: iload           7
        //   330: iload_1        
        //   331: iadd           
        //   332: iconst_3       
        //   333: aload           10
        //   335: iload           4
        //   337: iload_3        
        //   338: invokestatic    com/netflix/mediaclient/util/Base64.encode3to4:([BII[BII)[B
        //   341: pop            
        //   342: iload           5
        //   344: iconst_4       
        //   345: iadd           
        //   346: istore          9
        //   348: iload           9
        //   350: istore          5
        //   352: iload           4
        //   354: istore          8
        //   356: iload           6
        //   358: ifeq            394
        //   361: iload           9
        //   363: istore          5
        //   365: iload           4
        //   367: istore          8
        //   369: iload           9
        //   371: bipush          76
        //   373: if_icmplt       394
        //   376: aload           10
        //   378: iload           4
        //   380: iconst_4       
        //   381: iadd           
        //   382: bipush          10
        //   384: bastore        
        //   385: iload           4
        //   387: iconst_1       
        //   388: iadd           
        //   389: istore          8
        //   391: iconst_0       
        //   392: istore          5
        //   394: iload           8
        //   396: iconst_4       
        //   397: iadd           
        //   398: istore          4
        //   400: iload           7
        //   402: iconst_3       
        //   403: iadd           
        //   404: istore          7
        //   406: goto            319
        //   409: iconst_0       
        //   410: istore          6
        //   412: goto            262
        //   415: iconst_0       
        //   416: istore          4
        //   418: goto            276
        //   421: iload           4
        //   423: istore          5
        //   425: iload           7
        //   427: iload_2        
        //   428: if_icmpge       455
        //   431: aload_0        
        //   432: iload           7
        //   434: iload_1        
        //   435: iadd           
        //   436: iload_2        
        //   437: iload           7
        //   439: isub           
        //   440: aload           10
        //   442: iload           4
        //   444: iload_3        
        //   445: invokestatic    com/netflix/mediaclient/util/Base64.encode3to4:([BII[BII)[B
        //   448: pop            
        //   449: iload           4
        //   451: iconst_4       
        //   452: iadd           
        //   453: istore          5
        //   455: aload           10
        //   457: astore_0       
        //   458: iload           5
        //   460: aload           10
        //   462: arraylength    
        //   463: iconst_1       
        //   464: isub           
        //   465: if_icmpgt       207
        //   468: iload           5
        //   470: newarray        B
        //   472: astore_0       
        //   473: aload           10
        //   475: iconst_0       
        //   476: aload_0        
        //   477: iconst_0       
        //   478: iload           5
        //   480: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //   483: aload_0        
        //   484: areturn        
        //   485: astore_0       
        //   486: goto            191
        //   489: astore_0       
        //   490: goto            196
        //   493: astore_0       
        //   494: goto            201
        //   497: astore          10
        //   499: goto            240
        //   502: astore          10
        //   504: goto            245
        //   507: astore          10
        //   509: goto            250
        //   512: astore_0       
        //   513: aconst_null    
        //   514: astore          11
        //   516: aconst_null    
        //   517: astore          13
        //   519: aload           14
        //   521: astore          12
        //   523: goto            235
        //   526: astore_0       
        //   527: aconst_null    
        //   528: astore          11
        //   530: aload           14
        //   532: astore          12
        //   534: aload           10
        //   536: astore          13
        //   538: goto            235
        //   541: astore_0       
        //   542: aload           12
        //   544: astore          11
        //   546: aload           14
        //   548: astore          12
        //   550: aload           10
        //   552: astore          13
        //   554: goto            235
        //   557: astore_0       
        //   558: aload           11
        //   560: astore          13
        //   562: aload           12
        //   564: astore          11
        //   566: aload           13
        //   568: astore          12
        //   570: aload           10
        //   572: astore          13
        //   574: goto            235
        //   577: astore          11
        //   579: aconst_null    
        //   580: astore          12
        //   582: aload           10
        //   584: astore_0       
        //   585: aload           12
        //   587: astore          10
        //   589: aload           13
        //   591: astore          12
        //   593: goto            220
        //   596: astore          11
        //   598: aload           10
        //   600: astore_0       
        //   601: aload           12
        //   603: astore          10
        //   605: aload           13
        //   607: astore          12
        //   609: goto            220
        //   612: astore          14
        //   614: aload           11
        //   616: astore          13
        //   618: aload           10
        //   620: astore_0       
        //   621: aload           14
        //   623: astore          11
        //   625: aload           12
        //   627: astore          10
        //   629: aload           13
        //   631: astore          12
        //   633: goto            220
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  139    148    209    220    Ljava/io/IOException;
        //  139    148    512    526    Any
        //  148    162    577    596    Ljava/io/IOException;
        //  148    162    526    541    Any
        //  162    173    596    612    Ljava/io/IOException;
        //  162    173    541    557    Any
        //  173    186    612    636    Ljava/io/IOException;
        //  173    186    557    577    Any
        //  186    191    485    489    Ljava/lang/Exception;
        //  191    196    489    493    Ljava/lang/Exception;
        //  196    201    493    497    Ljava/lang/Exception;
        //  220    223    223    235    Any
        //  235    240    497    502    Ljava/lang/Exception;
        //  240    245    502    507    Ljava/lang/Exception;
        //  245    250    507    512    Ljava/lang/Exception;
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
    
    public static void encodeFileToFile(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/netflix/mediaclient/util/Base64.encodeFromFile:(Ljava/lang/String;)Ljava/lang/String;
        //     4: astore_0       
        //     5: new             Ljava/io/BufferedOutputStream;
        //     8: dup            
        //     9: new             Ljava/io/FileOutputStream;
        //    12: dup            
        //    13: aload_1        
        //    14: invokespecial   java/io/FileOutputStream.<init>:(Ljava/lang/String;)V
        //    17: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    20: astore_1       
        //    21: aload_1        
        //    22: aload_0        
        //    23: ldc             "US-ASCII"
        //    25: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //    28: invokevirtual   java/io/OutputStream.write:([B)V
        //    31: aload_1        
        //    32: invokevirtual   java/io/OutputStream.close:()V
        //    35: return         
        //    36: astore_0       
        //    37: aconst_null    
        //    38: astore_1       
        //    39: aload_1        
        //    40: invokevirtual   java/io/OutputStream.close:()V
        //    43: aload_0        
        //    44: athrow         
        //    45: astore_0       
        //    46: return         
        //    47: astore_1       
        //    48: goto            43
        //    51: astore_0       
        //    52: goto            39
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      21     36     39     Any
        //  21     31     51     55     Any
        //  31     35     45     47     Ljava/lang/Exception;
        //  39     43     47     51     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0039:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    public static String encodeFromFile(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_1       
        //     2: new             Ljava/io/File;
        //     5: dup            
        //     6: aload_0        
        //     7: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    10: astore_3       
        //    11: aload_3        
        //    12: invokevirtual   java/io/File.length:()J
        //    15: l2d            
        //    16: ldc2_w          1.4
        //    19: dmul           
        //    20: dconst_1       
        //    21: dadd           
        //    22: d2i            
        //    23: bipush          40
        //    25: invokestatic    java/lang/Math.max:(II)I
        //    28: newarray        B
        //    30: astore_0       
        //    31: new             Lcom/netflix/mediaclient/util/Base64$Base64InputStream;
        //    34: dup            
        //    35: new             Ljava/io/BufferedInputStream;
        //    38: dup            
        //    39: new             Ljava/io/FileInputStream;
        //    42: dup            
        //    43: aload_3        
        //    44: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    47: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    50: iconst_1       
        //    51: invokespecial   com/netflix/mediaclient/util/Base64$Base64InputStream.<init>:(Ljava/io/InputStream;I)V
        //    54: astore_3       
        //    55: aload_3        
        //    56: aload_0        
        //    57: iload_1        
        //    58: sipush          4096
        //    61: invokevirtual   com/netflix/mediaclient/util/Base64$Base64InputStream.read:([BII)I
        //    64: istore_2       
        //    65: iload_2        
        //    66: iflt            76
        //    69: iload_1        
        //    70: iload_2        
        //    71: iadd           
        //    72: istore_1       
        //    73: goto            55
        //    76: new             Ljava/lang/String;
        //    79: dup            
        //    80: aload_0        
        //    81: iconst_0       
        //    82: iload_1        
        //    83: ldc             "US-ASCII"
        //    85: invokespecial   java/lang/String.<init>:([BIILjava/lang/String;)V
        //    88: astore_0       
        //    89: aload_3        
        //    90: invokevirtual   com/netflix/mediaclient/util/Base64$Base64InputStream.close:()V
        //    93: aload_0        
        //    94: areturn        
        //    95: astore_0       
        //    96: aconst_null    
        //    97: astore_3       
        //    98: aload_3        
        //    99: invokevirtual   com/netflix/mediaclient/util/Base64$Base64InputStream.close:()V
        //   102: aload_0        
        //   103: athrow         
        //   104: astore_3       
        //   105: aload_0        
        //   106: areturn        
        //   107: astore_3       
        //   108: goto            102
        //   111: astore_0       
        //   112: goto            98
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      55     95     98     Any
        //  55     65     111    115    Any
        //  76     89     111    115    Any
        //  89     93     104    107    Ljava/lang/Exception;
        //  98     102    107    111    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0055:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
    
    public static String encodeObject(final Serializable s) {
        return encodeObject(s, 0);
    }
    
    public static String encodeObject(final Serializable p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aconst_null    
        //     3: astore          8
        //     5: aload_0        
        //     6: ifnonnull       20
        //     9: new             Ljava/lang/IllegalArgumentException;
        //    12: dup            
        //    13: ldc_w           "Cannot serialize a null object."
        //    16: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    19: athrow         
        //    20: new             Ljava/io/ByteArrayOutputStream;
        //    23: dup            
        //    24: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    27: astore          6
        //    29: new             Lcom/netflix/mediaclient/util/Base64$Base64OutputStream;
        //    32: dup            
        //    33: aload           6
        //    35: iload_1        
        //    36: iconst_1       
        //    37: ior            
        //    38: invokespecial   com/netflix/mediaclient/util/Base64$Base64OutputStream.<init>:(Ljava/io/OutputStream;I)V
        //    41: astore          7
        //    43: iload_1        
        //    44: iconst_2       
        //    45: iand           
        //    46: ifeq            123
        //    49: new             Ljava/util/zip/GZIPOutputStream;
        //    52: dup            
        //    53: aload           7
        //    55: invokespecial   java/util/zip/GZIPOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    58: astore          4
        //    60: aload           4
        //    62: astore_2       
        //    63: new             Ljava/io/ObjectOutputStream;
        //    66: dup            
        //    67: aload           4
        //    69: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    72: astore          5
        //    74: aload           5
        //    76: astore_3       
        //    77: aload           4
        //    79: astore_2       
        //    80: aload           5
        //    82: aload_0        
        //    83: invokevirtual   java/io/ObjectOutputStream.writeObject:(Ljava/lang/Object;)V
        //    86: aload           5
        //    88: invokevirtual   java/io/ObjectOutputStream.close:()V
        //    91: aload           4
        //    93: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //    96: aload           7
        //    98: invokevirtual   java/io/OutputStream.close:()V
        //   101: aload           6
        //   103: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   106: new             Ljava/lang/String;
        //   109: dup            
        //   110: aload           6
        //   112: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   115: ldc             "US-ASCII"
        //   117: invokespecial   java/lang/String.<init>:([BLjava/lang/String;)V
        //   120: astore_0       
        //   121: aload_0        
        //   122: areturn        
        //   123: new             Ljava/io/ObjectOutputStream;
        //   126: dup            
        //   127: aload           7
        //   129: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   132: astore          5
        //   134: aconst_null    
        //   135: astore          4
        //   137: goto            74
        //   140: astore_0       
        //   141: aconst_null    
        //   142: astore_2       
        //   143: aconst_null    
        //   144: astore_3       
        //   145: aconst_null    
        //   146: astore          5
        //   148: aload           8
        //   150: astore          4
        //   152: aload           4
        //   154: invokevirtual   java/io/ObjectOutputStream.close:()V
        //   157: aload_2        
        //   158: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   161: aload_3        
        //   162: invokevirtual   java/io/OutputStream.close:()V
        //   165: aload           5
        //   167: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   170: aload_0        
        //   171: athrow         
        //   172: astore_0       
        //   173: new             Ljava/lang/String;
        //   176: dup            
        //   177: aload           6
        //   179: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   182: invokespecial   java/lang/String.<init>:([B)V
        //   185: areturn        
        //   186: astore_0       
        //   187: goto            91
        //   190: astore_0       
        //   191: goto            96
        //   194: astore_0       
        //   195: goto            101
        //   198: astore_0       
        //   199: goto            106
        //   202: astore          4
        //   204: goto            157
        //   207: astore_2       
        //   208: goto            161
        //   211: astore_2       
        //   212: goto            165
        //   215: astore_2       
        //   216: goto            170
        //   219: astore_0       
        //   220: aconst_null    
        //   221: astore_2       
        //   222: aconst_null    
        //   223: astore_3       
        //   224: aload           8
        //   226: astore          4
        //   228: aload           6
        //   230: astore          5
        //   232: goto            152
        //   235: astore_0       
        //   236: aconst_null    
        //   237: astore_2       
        //   238: aload           8
        //   240: astore          4
        //   242: aload           7
        //   244: astore_3       
        //   245: aload           6
        //   247: astore          5
        //   249: goto            152
        //   252: astore_0       
        //   253: aload_3        
        //   254: astore          4
        //   256: aload           7
        //   258: astore_3       
        //   259: aload           6
        //   261: astore          5
        //   263: goto            152
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  20     29     140    152    Any
        //  29     43     219    235    Any
        //  49     60     235    252    Any
        //  63     74     252    266    Any
        //  80     86     252    266    Any
        //  86     91     186    190    Ljava/lang/Exception;
        //  91     96     190    194    Ljava/lang/Exception;
        //  96     101    194    198    Ljava/lang/Exception;
        //  101    106    198    202    Ljava/lang/Exception;
        //  106    121    172    186    Ljava/io/UnsupportedEncodingException;
        //  123    134    235    252    Any
        //  152    157    202    207    Ljava/lang/Exception;
        //  157    161    207    211    Ljava/lang/Exception;
        //  161    165    211    215    Ljava/lang/Exception;
        //  165    170    215    219    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 141, Size: 141
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3417)
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
    
    public static void encodeToFile(final byte[] p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       15
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc_w           "Data to encode was null."
        //    11: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    14: athrow         
        //    15: new             Lcom/netflix/mediaclient/util/Base64$Base64OutputStream;
        //    18: dup            
        //    19: new             Ljava/io/FileOutputStream;
        //    22: dup            
        //    23: aload_1        
        //    24: invokespecial   java/io/FileOutputStream.<init>:(Ljava/lang/String;)V
        //    27: iconst_1       
        //    28: invokespecial   com/netflix/mediaclient/util/Base64$Base64OutputStream.<init>:(Ljava/io/OutputStream;I)V
        //    31: astore_1       
        //    32: aload_1        
        //    33: aload_0        
        //    34: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.write:([B)V
        //    37: aload_1        
        //    38: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.close:()V
        //    41: return         
        //    42: astore_0       
        //    43: aconst_null    
        //    44: astore_1       
        //    45: aload_1        
        //    46: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.close:()V
        //    49: aload_0        
        //    50: athrow         
        //    51: astore_0       
        //    52: return         
        //    53: astore_1       
        //    54: goto            49
        //    57: astore_0       
        //    58: goto            45
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  15     32     42     45     Any
        //  32     37     57     61     Any
        //  37     41     51     53     Ljava/lang/Exception;
        //  45     49     53     57     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0045:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2592)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
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
