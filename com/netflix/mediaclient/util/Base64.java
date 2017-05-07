// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.io.OutputStream;
import java.io.FilterOutputStream;
import java.io.InputStream;
import java.io.FilterInputStream;
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
    
    public static byte[] decode(final String s) throws IOException {
        return decode(s, 0);
    }
    
    public static byte[] decode(final String p0, final int p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       14
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc             "Input string was null."
        //    10: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    13: athrow         
        //    14: aload_0        
        //    15: ldc             "US-ASCII"
        //    17: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //    20: astore_2       
        //    21: aload_2        
        //    22: astore_0       
        //    23: aload_0        
        //    24: iconst_0       
        //    25: aload_0        
        //    26: arraylength    
        //    27: iload_1        
        //    28: invokestatic    com/netflix/mediaclient/util/Base64.decode:([BIII)[B
        //    31: astore          5
        //    33: iload_1        
        //    34: iconst_4       
        //    35: iand           
        //    36: ifeq            201
        //    39: iconst_1       
        //    40: istore_1       
        //    41: aload           5
        //    43: astore_3       
        //    44: aload           5
        //    46: ifnull          224
        //    49: aload           5
        //    51: astore_3       
        //    52: aload           5
        //    54: arraylength    
        //    55: iconst_4       
        //    56: if_icmplt       224
        //    59: aload           5
        //    61: astore_3       
        //    62: iload_1        
        //    63: ifne            224
        //    66: aload           5
        //    68: astore_3       
        //    69: ldc             35615
        //    71: aload           5
        //    73: iconst_0       
        //    74: baload         
        //    75: sipush          255
        //    78: iand           
        //    79: aload           5
        //    81: iconst_1       
        //    82: baload         
        //    83: bipush          8
        //    85: ishl           
        //    86: ldc             65280
        //    88: iand           
        //    89: ior            
        //    90: if_icmpne       224
        //    93: aconst_null    
        //    94: astore_3       
        //    95: aconst_null    
        //    96: astore          4
        //    98: aconst_null    
        //    99: astore_2       
        //   100: sipush          2048
        //   103: newarray        B
        //   105: astore          6
        //   107: new             Ljava/io/ByteArrayOutputStream;
        //   110: dup            
        //   111: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //   114: astore_0       
        //   115: new             Ljava/io/ByteArrayInputStream;
        //   118: dup            
        //   119: aload           5
        //   121: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //   124: astore_2       
        //   125: new             Ljava/util/zip/GZIPInputStream;
        //   128: dup            
        //   129: aload_2        
        //   130: invokespecial   java/util/zip/GZIPInputStream.<init>:(Ljava/io/InputStream;)V
        //   133: astore          5
        //   135: aload           5
        //   137: aload           6
        //   139: invokevirtual   java/util/zip/GZIPInputStream.read:([B)I
        //   142: istore_1       
        //   143: iload_1        
        //   144: iflt            206
        //   147: aload_0        
        //   148: aload           6
        //   150: iconst_0       
        //   151: iload_1        
        //   152: invokevirtual   java/io/ByteArrayOutputStream.write:([BII)V
        //   155: goto            135
        //   158: astore          6
        //   160: aload_0        
        //   161: astore_3       
        //   162: aload           5
        //   164: astore          4
        //   166: aload_2        
        //   167: astore          5
        //   169: aload           6
        //   171: astore_0       
        //   172: aload_3        
        //   173: astore_2       
        //   174: aload           5
        //   176: astore_3       
        //   177: aload_2        
        //   178: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   181: aload           4
        //   183: invokevirtual   java/util/zip/GZIPInputStream.close:()V
        //   186: aload_3        
        //   187: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   190: aload_0        
        //   191: athrow         
        //   192: astore_2       
        //   193: aload_0        
        //   194: invokevirtual   java/lang/String.getBytes:()[B
        //   197: astore_0       
        //   198: goto            23
        //   201: iconst_0       
        //   202: istore_1       
        //   203: goto            41
        //   206: aload_0        
        //   207: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   210: astore_3       
        //   211: aload_0        
        //   212: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   215: aload           5
        //   217: invokevirtual   java/util/zip/GZIPInputStream.close:()V
        //   220: aload_2        
        //   221: invokevirtual   java/io/ByteArrayInputStream.close:()V
        //   224: aload_3        
        //   225: areturn        
        //   226: astore_0       
        //   227: goto            215
        //   230: astore_0       
        //   231: goto            220
        //   234: astore_0       
        //   235: aload_3        
        //   236: areturn        
        //   237: astore_2       
        //   238: goto            181
        //   241: astore_2       
        //   242: goto            186
        //   245: astore_2       
        //   246: goto            190
        //   249: astore_0       
        //   250: goto            177
        //   253: astore          5
        //   255: aload_0        
        //   256: astore_2       
        //   257: aload           5
        //   259: astore_0       
        //   260: goto            177
        //   263: astore          5
        //   265: aload_2        
        //   266: astore_3       
        //   267: aload_0        
        //   268: astore_2       
        //   269: aload           5
        //   271: astore_0       
        //   272: goto            177
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  14     21     192    201    Ljava/io/UnsupportedEncodingException;
        //  107    115    249    253    Any
        //  115    125    253    263    Any
        //  125    135    263    275    Any
        //  135    143    158    177    Any
        //  147    155    158    177    Any
        //  177    181    237    241    Ljava/lang/Exception;
        //  181    186    241    245    Ljava/lang/Exception;
        //  186    190    245    249    Ljava/lang/Exception;
        //  206    211    158    177    Any
        //  211    215    226    230    Ljava/lang/Exception;
        //  215    220    230    234    Ljava/lang/Exception;
        //  220    224    234    237    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 161, Size: 161
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
    
    public static byte[] decode(final byte[] array) throws IOException {
        return decode(array, 0, array.length, 0);
    }
    
    public static byte[] decode(byte[] array, final int n, final int n2, final int n3) throws IOException {
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
        int n4 = 0;
        final byte[] array3 = new byte[4];
        int i = n;
        int n5 = 0;
        while (true) {
            while (i < n + n2) {
                final byte b = decodabet[array[i] & 0xFF];
                if (b < -5) {
                    throw new IOException(String.format("Bad Base64 input character decimal %d in array position %d", array[i] & 0xFF, i));
                }
                int n7;
                if (b >= -1) {
                    final int n6 = n5 + 1;
                    array3[n5] = array[i];
                    n5 = n6;
                    n7 = n4;
                    if (n6 > 3) {
                        n4 += decode4to3(array3, 0, array2, n4, n3);
                        n5 = 0;
                        n7 = n4;
                        if (array[i] == 61) {
                            array = new byte[n4];
                            System.arraycopy(array2, 0, array, 0, n4);
                            return array;
                        }
                    }
                }
                else {
                    n7 = n4;
                }
                ++i;
                n4 = n7;
            }
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
    
    public static void decodeFileToFile(final String p0, final String p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/netflix/mediaclient/util/Base64.decodeFromFile:(Ljava/lang/String;)[B
        //     4: astore_0       
        //     5: aconst_null    
        //     6: astore_2       
        //     7: new             Ljava/io/BufferedOutputStream;
        //    10: dup            
        //    11: new             Ljava/io/FileOutputStream;
        //    14: dup            
        //    15: aload_1        
        //    16: invokespecial   java/io/FileOutputStream.<init>:(Ljava/lang/String;)V
        //    19: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    22: astore_1       
        //    23: aload_1        
        //    24: aload_0        
        //    25: invokevirtual   java/io/OutputStream.write:([B)V
        //    28: aload_1        
        //    29: invokevirtual   java/io/OutputStream.close:()V
        //    32: return         
        //    33: astore_0       
        //    34: aload_2        
        //    35: astore_1       
        //    36: aload_1        
        //    37: invokevirtual   java/io/OutputStream.close:()V
        //    40: aload_0        
        //    41: athrow         
        //    42: astore_0       
        //    43: return         
        //    44: astore_1       
        //    45: goto            40
        //    48: astore_0       
        //    49: goto            36
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  7      23     33     36     Any
        //  23     28     48     52     Any
        //  28     32     42     44     Ljava/lang/Exception;
        //  36     40     44     48     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0036:
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
    
    public static byte[] decodeFromFile(final String p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: new             Ljava/io/File;
        //     5: dup            
        //     6: aload_0        
        //     7: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    10: astore_0       
        //    11: iconst_0       
        //    12: istore_1       
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
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      11     61     62     Any
        //  13     61     61     62     Any
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
    
    public static void decodeToFile(final String p0, final String p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2       
        //     2: new             Lcom/netflix/mediaclient/util/Base64$Base64OutputStream;
        //     5: dup            
        //     6: new             Ljava/io/FileOutputStream;
        //     9: dup            
        //    10: aload_1        
        //    11: invokespecial   java/io/FileOutputStream.<init>:(Ljava/lang/String;)V
        //    14: iconst_0       
        //    15: invokespecial   com/netflix/mediaclient/util/Base64$Base64OutputStream.<init>:(Ljava/io/OutputStream;I)V
        //    18: astore_1       
        //    19: aload_1        
        //    20: aload_0        
        //    21: ldc             "US-ASCII"
        //    23: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //    26: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.write:([B)V
        //    29: aload_1        
        //    30: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.close:()V
        //    33: return         
        //    34: astore_0       
        //    35: aload_2        
        //    36: astore_1       
        //    37: aload_1        
        //    38: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.close:()V
        //    41: aload_0        
        //    42: athrow         
        //    43: astore_0       
        //    44: return         
        //    45: astore_1       
        //    46: goto            41
        //    49: astore_0       
        //    50: goto            37
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      19     34     37     Any
        //  19     29     49     53     Any
        //  29     33     43     45     Ljava/lang/Exception;
        //  37     41     45     49     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0037:
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
        n = (n6 | n4 | n5);
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
    
    public static String encodeBytes(final byte[] array) throws IOException {
        return encodeBytes(array, 0, array.length, 0);
    }
    
    public static String encodeBytes(final byte[] array, final int n) throws IOException {
        return encodeBytes(array, 0, array.length, n);
    }
    
    public static String encodeBytes(final byte[] array, final int n, final int n2) throws IOException {
        return encodeBytes(array, n, n2, 0);
    }
    
    public static String encodeBytes(byte[] encodeBytesToBytes, final int n, final int n2, final int n3) throws IOException {
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
    
    public static byte[] encodeBytesToBytes(final byte[] p0, final int p1, final int p2, final int p3) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       15
        //     4: new             Ljava/lang/NullPointerException;
        //     7: dup            
        //     8: ldc_w           "Cannot serialize a null array."
        //    11: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
        //    14: athrow         
        //    15: iload_1        
        //    16: ifge            47
        //    19: new             Ljava/lang/IllegalArgumentException;
        //    22: dup            
        //    23: new             Ljava/lang/StringBuilder;
        //    26: dup            
        //    27: invokespecial   java/lang/StringBuilder.<init>:()V
        //    30: ldc_w           "Cannot have negative offset: "
        //    33: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    36: iload_1        
        //    37: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    40: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    43: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    46: athrow         
        //    47: iload_2        
        //    48: ifge            79
        //    51: new             Ljava/lang/IllegalArgumentException;
        //    54: dup            
        //    55: new             Ljava/lang/StringBuilder;
        //    58: dup            
        //    59: invokespecial   java/lang/StringBuilder.<init>:()V
        //    62: ldc_w           "Cannot have length offset: "
        //    65: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    68: iload_2        
        //    69: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //    72: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    75: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    78: athrow         
        //    79: iload_1        
        //    80: iload_2        
        //    81: iadd           
        //    82: aload_0        
        //    83: arraylength    
        //    84: if_icmple       127
        //    87: new             Ljava/lang/IllegalArgumentException;
        //    90: dup            
        //    91: ldc_w           "Cannot have offset of %d and length of %d with array of length %d"
        //    94: iconst_3       
        //    95: anewarray       Ljava/lang/Object;
        //    98: dup            
        //    99: iconst_0       
        //   100: iload_1        
        //   101: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   104: aastore        
        //   105: dup            
        //   106: iconst_1       
        //   107: iload_2        
        //   108: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   111: aastore        
        //   112: dup            
        //   113: iconst_2       
        //   114: aload_0        
        //   115: arraylength    
        //   116: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   119: aastore        
        //   120: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   123: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //   126: athrow         
        //   127: iload_3        
        //   128: iconst_2       
        //   129: iand           
        //   130: ifeq            270
        //   133: aconst_null    
        //   134: astore          11
        //   136: aconst_null    
        //   137: astore          18
        //   139: aconst_null    
        //   140: astore          13
        //   142: aconst_null    
        //   143: astore          15
        //   145: aconst_null    
        //   146: astore          14
        //   148: aconst_null    
        //   149: astore          12
        //   151: aconst_null    
        //   152: astore          17
        //   154: aconst_null    
        //   155: astore          16
        //   157: new             Ljava/io/ByteArrayOutputStream;
        //   160: dup            
        //   161: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //   164: astore          10
        //   166: new             Lcom/netflix/mediaclient/util/Base64$Base64OutputStream;
        //   169: dup            
        //   170: aload           10
        //   172: iload_3        
        //   173: iconst_1       
        //   174: ior            
        //   175: invokespecial   com/netflix/mediaclient/util/Base64$Base64OutputStream.<init>:(Ljava/io/OutputStream;I)V
        //   178: astore          11
        //   180: new             Ljava/util/zip/GZIPOutputStream;
        //   183: dup            
        //   184: aload           11
        //   186: invokespecial   java/util/zip/GZIPOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   189: astore          12
        //   191: aload           12
        //   193: aload_0        
        //   194: iload_1        
        //   195: iload_2        
        //   196: invokevirtual   java/util/zip/GZIPOutputStream.write:([BII)V
        //   199: aload           12
        //   201: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   204: aload           12
        //   206: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   209: aload           11
        //   211: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.close:()V
        //   214: aload           10
        //   216: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   219: aload           10
        //   221: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   224: areturn        
        //   225: astore          10
        //   227: aload           14
        //   229: astore          13
        //   231: aload           18
        //   233: astore_0       
        //   234: aload           16
        //   236: astore          11
        //   238: aload           11
        //   240: astore          12
        //   242: aload_0        
        //   243: astore          11
        //   245: aload           10
        //   247: athrow         
        //   248: astore_0       
        //   249: aload           12
        //   251: astore          10
        //   253: aload           13
        //   255: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   258: aload           10
        //   260: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.close:()V
        //   263: aload           11
        //   265: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   268: aload_0        
        //   269: athrow         
        //   270: iload_3        
        //   271: bipush          8
        //   273: iand           
        //   274: ifeq            427
        //   277: iconst_1       
        //   278: istore          6
        //   280: iload_2        
        //   281: iconst_3       
        //   282: idiv           
        //   283: istore          5
        //   285: iload_2        
        //   286: iconst_3       
        //   287: irem           
        //   288: ifle            433
        //   291: iconst_4       
        //   292: istore          4
        //   294: iload           5
        //   296: iconst_4       
        //   297: imul           
        //   298: iload           4
        //   300: iadd           
        //   301: istore          5
        //   303: iload           5
        //   305: istore          4
        //   307: iload           6
        //   309: ifeq            322
        //   312: iload           5
        //   314: iload           5
        //   316: bipush          76
        //   318: idiv           
        //   319: iadd           
        //   320: istore          4
        //   322: iload           4
        //   324: newarray        B
        //   326: astore          10
        //   328: iconst_0       
        //   329: istore          7
        //   331: iconst_0       
        //   332: istore          4
        //   334: iconst_0       
        //   335: istore          5
        //   337: iload           7
        //   339: iload_2        
        //   340: iconst_2       
        //   341: isub           
        //   342: if_icmpge       439
        //   345: aload_0        
        //   346: iload           7
        //   348: iload_1        
        //   349: iadd           
        //   350: iconst_3       
        //   351: aload           10
        //   353: iload           4
        //   355: iload_3        
        //   356: invokestatic    com/netflix/mediaclient/util/Base64.encode3to4:([BII[BII)[B
        //   359: pop            
        //   360: iload           5
        //   362: iconst_4       
        //   363: iadd           
        //   364: istore          9
        //   366: iload           4
        //   368: istore          8
        //   370: iload           9
        //   372: istore          5
        //   374: iload           6
        //   376: ifeq            412
        //   379: iload           4
        //   381: istore          8
        //   383: iload           9
        //   385: istore          5
        //   387: iload           9
        //   389: bipush          76
        //   391: if_icmplt       412
        //   394: aload           10
        //   396: iload           4
        //   398: iconst_4       
        //   399: iadd           
        //   400: bipush          10
        //   402: bastore        
        //   403: iload           4
        //   405: iconst_1       
        //   406: iadd           
        //   407: istore          8
        //   409: iconst_0       
        //   410: istore          5
        //   412: iload           7
        //   414: iconst_3       
        //   415: iadd           
        //   416: istore          7
        //   418: iload           8
        //   420: iconst_4       
        //   421: iadd           
        //   422: istore          4
        //   424: goto            337
        //   427: iconst_0       
        //   428: istore          6
        //   430: goto            280
        //   433: iconst_0       
        //   434: istore          4
        //   436: goto            294
        //   439: iload           4
        //   441: istore          5
        //   443: iload           7
        //   445: iload_2        
        //   446: if_icmpge       473
        //   449: aload_0        
        //   450: iload           7
        //   452: iload_1        
        //   453: iadd           
        //   454: iload_2        
        //   455: iload           7
        //   457: isub           
        //   458: aload           10
        //   460: iload           4
        //   462: iload_3        
        //   463: invokestatic    com/netflix/mediaclient/util/Base64.encode3to4:([BII[BII)[B
        //   466: pop            
        //   467: iload           4
        //   469: iconst_4       
        //   470: iadd           
        //   471: istore          5
        //   473: iload           5
        //   475: aload           10
        //   477: arraylength    
        //   478: iconst_1       
        //   479: isub           
        //   480: if_icmpgt       500
        //   483: iload           5
        //   485: newarray        B
        //   487: astore_0       
        //   488: aload           10
        //   490: iconst_0       
        //   491: aload_0        
        //   492: iconst_0       
        //   493: iload           5
        //   495: invokestatic    java/lang/System.arraycopy:(Ljava/lang/Object;ILjava/lang/Object;II)V
        //   498: aload_0        
        //   499: areturn        
        //   500: aload           10
        //   502: areturn        
        //   503: astore_0       
        //   504: goto            209
        //   507: astore_0       
        //   508: goto            214
        //   511: astore_0       
        //   512: goto            219
        //   515: astore          12
        //   517: goto            258
        //   520: astore          10
        //   522: goto            263
        //   525: astore          10
        //   527: goto            268
        //   530: astore_0       
        //   531: aload           10
        //   533: astore          11
        //   535: aload           17
        //   537: astore          10
        //   539: aload           15
        //   541: astore          13
        //   543: goto            253
        //   546: astore_0       
        //   547: aload           10
        //   549: astore          12
        //   551: aload           11
        //   553: astore          10
        //   555: aload           12
        //   557: astore          11
        //   559: aload           15
        //   561: astore          13
        //   563: goto            253
        //   566: astore_0       
        //   567: aload           10
        //   569: astore          13
        //   571: aload           11
        //   573: astore          10
        //   575: aload           13
        //   577: astore          11
        //   579: aload           12
        //   581: astore          13
        //   583: goto            253
        //   586: astore          11
        //   588: aload           10
        //   590: astore_0       
        //   591: aload           11
        //   593: astore          10
        //   595: aload           16
        //   597: astore          11
        //   599: aload           14
        //   601: astore          13
        //   603: goto            238
        //   606: astore          12
        //   608: aload           10
        //   610: astore_0       
        //   611: aload           12
        //   613: astore          10
        //   615: aload           14
        //   617: astore          13
        //   619: goto            238
        //   622: astore          13
        //   624: aload           10
        //   626: astore_0       
        //   627: aload           13
        //   629: astore          10
        //   631: aload           12
        //   633: astore          13
        //   635: goto            238
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  157    166    225    238    Ljava/io/IOException;
        //  157    166    248    253    Any
        //  166    180    586    606    Ljava/io/IOException;
        //  166    180    530    546    Any
        //  180    191    606    622    Ljava/io/IOException;
        //  180    191    546    566    Any
        //  191    204    622    638    Ljava/io/IOException;
        //  191    204    566    586    Any
        //  204    209    503    507    Ljava/lang/Exception;
        //  209    214    507    511    Ljava/lang/Exception;
        //  214    219    511    515    Ljava/lang/Exception;
        //  245    248    248    253    Any
        //  253    258    515    520    Ljava/lang/Exception;
        //  258    263    520    525    Ljava/lang/Exception;
        //  263    268    525    530    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertLocalVariables(AstBuilder.java:3035)
        //     at com.strobel.decompiler.ast.AstBuilder.performStackAnalysis(AstBuilder.java:2445)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:108)
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
    
    public static void encodeFileToFile(final String p0, final String p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: invokestatic    com/netflix/mediaclient/util/Base64.encodeFromFile:(Ljava/lang/String;)Ljava/lang/String;
        //     4: astore_0       
        //     5: aconst_null    
        //     6: astore_2       
        //     7: new             Ljava/io/BufferedOutputStream;
        //    10: dup            
        //    11: new             Ljava/io/FileOutputStream;
        //    14: dup            
        //    15: aload_1        
        //    16: invokespecial   java/io/FileOutputStream.<init>:(Ljava/lang/String;)V
        //    19: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    22: astore_1       
        //    23: aload_1        
        //    24: aload_0        
        //    25: ldc             "US-ASCII"
        //    27: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //    30: invokevirtual   java/io/OutputStream.write:([B)V
        //    33: aload_1        
        //    34: invokevirtual   java/io/OutputStream.close:()V
        //    37: return         
        //    38: astore_0       
        //    39: aload_2        
        //    40: astore_1       
        //    41: aload_1        
        //    42: invokevirtual   java/io/OutputStream.close:()V
        //    45: aload_0        
        //    46: athrow         
        //    47: astore_0       
        //    48: return         
        //    49: astore_1       
        //    50: goto            45
        //    53: astore_0       
        //    54: goto            41
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  7      23     38     41     Any
        //  23     33     53     57     Any
        //  33     37     47     49     Ljava/lang/Exception;
        //  41     45     49     53     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0041:
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
    
    public static String encodeFromFile(final String p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: new             Ljava/io/File;
        //     5: dup            
        //     6: aload_0        
        //     7: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    10: astore_0       
        //    11: aload_0        
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
        //    30: astore          4
        //    32: iconst_0       
        //    33: istore_1       
        //    34: new             Lcom/netflix/mediaclient/util/Base64$Base64InputStream;
        //    37: dup            
        //    38: new             Ljava/io/BufferedInputStream;
        //    41: dup            
        //    42: new             Ljava/io/FileInputStream;
        //    45: dup            
        //    46: aload_0        
        //    47: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    50: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    53: iconst_1       
        //    54: invokespecial   com/netflix/mediaclient/util/Base64$Base64InputStream.<init>:(Ljava/io/InputStream;I)V
        //    57: astore_0       
        //    58: aload_0        
        //    59: aload           4
        //    61: iload_1        
        //    62: sipush          4096
        //    65: invokevirtual   com/netflix/mediaclient/util/Base64$Base64InputStream.read:([BII)I
        //    68: istore_2       
        //    69: iload_2        
        //    70: iflt            80
        //    73: iload_1        
        //    74: iload_2        
        //    75: iadd           
        //    76: istore_1       
        //    77: goto            58
        //    80: new             Ljava/lang/String;
        //    83: dup            
        //    84: aload           4
        //    86: iconst_0       
        //    87: iload_1        
        //    88: ldc             "US-ASCII"
        //    90: invokespecial   java/lang/String.<init>:([BIILjava/lang/String;)V
        //    93: astore_3       
        //    94: aload_0        
        //    95: invokevirtual   com/netflix/mediaclient/util/Base64$Base64InputStream.close:()V
        //    98: aload_3        
        //    99: areturn        
        //   100: astore_0       
        //   101: aload_3        
        //   102: invokevirtual   com/netflix/mediaclient/util/Base64$Base64InputStream.close:()V
        //   105: aload_0        
        //   106: athrow         
        //   107: astore_0       
        //   108: aload_3        
        //   109: areturn        
        //   110: astore_3       
        //   111: goto            105
        //   114: astore          4
        //   116: aload_0        
        //   117: astore_3       
        //   118: aload           4
        //   120: astore_0       
        //   121: goto            101
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      32     100    101    Any
        //  34     58     100    101    Any
        //  58     69     114    124    Any
        //  80     94     114    124    Any
        //  94     98     107    110    Ljava/lang/Exception;
        //  101    105    110    114    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0058:
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
    
    public static String encodeObject(final Serializable s) throws IOException {
        return encodeObject(s, 0);
    }
    
    public static String encodeObject(final Serializable p0, final int p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: ifnonnull       15
        //     4: new             Ljava/lang/IllegalArgumentException;
        //     7: dup            
        //     8: ldc_w           "Cannot serialize a null object."
        //    11: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    14: athrow         
        //    15: aconst_null    
        //    16: astore          7
        //    18: aconst_null    
        //    19: astore_3       
        //    20: aconst_null    
        //    21: astore          4
        //    23: aconst_null    
        //    24: astore          9
        //    26: aconst_null    
        //    27: astore          8
        //    29: aconst_null    
        //    30: astore          5
        //    32: aconst_null    
        //    33: astore          6
        //    35: new             Ljava/io/ByteArrayOutputStream;
        //    38: dup            
        //    39: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    42: astore_2       
        //    43: new             Lcom/netflix/mediaclient/util/Base64$Base64OutputStream;
        //    46: dup            
        //    47: aload_2        
        //    48: iload_1        
        //    49: iconst_1       
        //    50: ior            
        //    51: invokespecial   com/netflix/mediaclient/util/Base64$Base64OutputStream.<init>:(Ljava/io/OutputStream;I)V
        //    54: astore          7
        //    56: iload_1        
        //    57: iconst_2       
        //    58: iand           
        //    59: ifeq            137
        //    62: aload           9
        //    64: astore          4
        //    66: new             Ljava/util/zip/GZIPOutputStream;
        //    69: dup            
        //    70: aload           7
        //    72: invokespecial   java/util/zip/GZIPOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    75: astore_3       
        //    76: new             Ljava/io/ObjectOutputStream;
        //    79: dup            
        //    80: aload_3        
        //    81: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    84: astore          4
        //    86: aload           4
        //    88: astore          6
        //    90: aload_3        
        //    91: astore          4
        //    93: aload           6
        //    95: astore          5
        //    97: aload           6
        //    99: aload_0        
        //   100: invokevirtual   java/io/ObjectOutputStream.writeObject:(Ljava/lang/Object;)V
        //   103: aload           6
        //   105: invokevirtual   java/io/ObjectOutputStream.close:()V
        //   108: aload_3        
        //   109: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   112: aload           7
        //   114: invokevirtual   java/io/OutputStream.close:()V
        //   117: aload_2        
        //   118: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   121: new             Ljava/lang/String;
        //   124: dup            
        //   125: aload_2        
        //   126: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   129: ldc             "US-ASCII"
        //   131: invokespecial   java/lang/String.<init>:([BLjava/lang/String;)V
        //   134: astore_0       
        //   135: aload_0        
        //   136: areturn        
        //   137: aload           9
        //   139: astore          4
        //   141: new             Ljava/io/ObjectOutputStream;
        //   144: dup            
        //   145: aload           7
        //   147: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   150: astore          6
        //   152: aload           8
        //   154: astore_3       
        //   155: goto            90
        //   158: astore_2       
        //   159: aload           6
        //   161: astore          5
        //   163: aload           7
        //   165: astore_0       
        //   166: aload           5
        //   168: invokevirtual   java/io/ObjectOutputStream.close:()V
        //   171: aload           4
        //   173: invokevirtual   java/util/zip/GZIPOutputStream.close:()V
        //   176: aload_3        
        //   177: invokevirtual   java/io/OutputStream.close:()V
        //   180: aload_0        
        //   181: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   184: aload_2        
        //   185: athrow         
        //   186: astore_0       
        //   187: new             Ljava/lang/String;
        //   190: dup            
        //   191: aload_2        
        //   192: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   195: invokespecial   java/lang/String.<init>:([B)V
        //   198: areturn        
        //   199: astore_0       
        //   200: goto            108
        //   203: astore_0       
        //   204: goto            112
        //   207: astore_0       
        //   208: goto            117
        //   211: astore_0       
        //   212: goto            121
        //   215: astore          5
        //   217: goto            171
        //   220: astore          4
        //   222: goto            176
        //   225: astore_3       
        //   226: goto            180
        //   229: astore_0       
        //   230: goto            184
        //   233: astore          7
        //   235: aload_2        
        //   236: astore_0       
        //   237: aload           6
        //   239: astore          5
        //   241: aload           7
        //   243: astore_2       
        //   244: goto            166
        //   247: astore          6
        //   249: aload           7
        //   251: astore_3       
        //   252: aload_2        
        //   253: astore_0       
        //   254: aload           6
        //   256: astore_2       
        //   257: goto            166
        //   260: astore          8
        //   262: aload_3        
        //   263: astore          4
        //   265: aload           7
        //   267: astore_3       
        //   268: aload_2        
        //   269: astore_0       
        //   270: aload           6
        //   272: astore          5
        //   274: aload           8
        //   276: astore_2       
        //   277: goto            166
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  35     43     158    166    Any
        //  43     56     233    247    Any
        //  66     76     247    260    Any
        //  76     86     260    280    Any
        //  97     103    247    260    Any
        //  103    108    199    203    Ljava/lang/Exception;
        //  108    112    203    207    Ljava/lang/Exception;
        //  112    117    207    211    Ljava/lang/Exception;
        //  117    121    211    215    Ljava/lang/Exception;
        //  121    135    186    199    Ljava/io/UnsupportedEncodingException;
        //  141    152    247    260    Any
        //  166    171    215    220    Ljava/lang/Exception;
        //  171    176    220    225    Ljava/lang/Exception;
        //  176    180    225    229    Ljava/lang/Exception;
        //  180    184    229    233    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 151, Size: 151
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
    
    public static void encodeToFile(final byte[] p0, final String p1) throws IOException {
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
        //    15: aconst_null    
        //    16: astore_2       
        //    17: new             Lcom/netflix/mediaclient/util/Base64$Base64OutputStream;
        //    20: dup            
        //    21: new             Ljava/io/FileOutputStream;
        //    24: dup            
        //    25: aload_1        
        //    26: invokespecial   java/io/FileOutputStream.<init>:(Ljava/lang/String;)V
        //    29: iconst_1       
        //    30: invokespecial   com/netflix/mediaclient/util/Base64$Base64OutputStream.<init>:(Ljava/io/OutputStream;I)V
        //    33: astore_1       
        //    34: aload_1        
        //    35: aload_0        
        //    36: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.write:([B)V
        //    39: aload_1        
        //    40: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.close:()V
        //    43: return         
        //    44: astore_0       
        //    45: aload_2        
        //    46: astore_1       
        //    47: aload_1        
        //    48: invokevirtual   com/netflix/mediaclient/util/Base64$Base64OutputStream.close:()V
        //    51: aload_0        
        //    52: athrow         
        //    53: astore_0       
        //    54: return         
        //    55: astore_1       
        //    56: goto            51
        //    59: astore_0       
        //    60: goto            47
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  17     34     44     47     Any
        //  34     39     59     63     Any
        //  39     43     53     55     Ljava/lang/Exception;
        //  47     51     55     59     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0047:
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
    
    public static class Base64InputStream extends FilterInputStream
    {
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int numSigBytes;
        private int options;
        private int position;
        
        public Base64InputStream(final InputStream inputStream) {
            this(inputStream, 0);
        }
        
        public Base64InputStream(final InputStream inputStream, final int options) {
            final boolean b = true;
            super(inputStream);
            this.options = options;
            this.breakLines = ((options & 0x8) > 0);
            this.encode = ((options & 0x1) > 0 && b);
            int bufferLength;
            if (this.encode) {
                bufferLength = 4;
            }
            else {
                bufferLength = 3;
            }
            this.bufferLength = bufferLength;
            this.buffer = new byte[this.bufferLength];
            this.position = -1;
            this.lineLength = 0;
            this.decodabet = getDecodabet(options);
        }
        
        @Override
        public int read() throws IOException {
            if (this.position < 0) {
                if (this.encode) {
                    final byte[] array = new byte[3];
                    int n = 0;
                    for (int i = 0; i < 3; ++i) {
                        final int read = this.in.read();
                        if (read < 0) {
                            break;
                        }
                        array[i] = (byte)read;
                        ++n;
                    }
                    if (n <= 0) {
                        return -1;
                    }
                    encode3to4(array, 0, n, this.buffer, 0, this.options);
                    this.position = 0;
                    this.numSigBytes = 4;
                }
                else {
                    final byte[] array2 = new byte[4];
                    int j;
                    for (j = 0; j < 4; ++j) {
                        int read2;
                        do {
                            read2 = this.in.read();
                        } while (read2 >= 0 && this.decodabet[read2 & 0x7F] <= -5);
                        if (read2 < 0) {
                            break;
                        }
                        array2[j] = (byte)read2;
                    }
                    if (j == 4) {
                        this.numSigBytes = decode4to3(array2, 0, this.buffer, 0, this.options);
                        this.position = 0;
                    }
                    else {
                        if (j == 0) {
                            return -1;
                        }
                        throw new IOException("Improperly padded Base64 input.");
                    }
                }
            }
            if (this.position < 0) {
                throw new IOException("Error in Base64 code reading stream.");
            }
            if (this.position >= this.numSigBytes) {
                return -1;
            }
            if (this.encode && this.breakLines && this.lineLength >= 76) {
                this.lineLength = 0;
                return 10;
            }
            ++this.lineLength;
            final byte b = this.buffer[this.position++];
            if (this.position >= this.bufferLength) {
                this.position = -1;
            }
            return b & 0xFF;
        }
        
        @Override
        public int read(final byte[] array, final int n, final int n2) throws IOException {
            int n3 = 0;
            int n4;
            while (true) {
                n4 = n3;
                if (n3 >= n2) {
                    break;
                }
                final int read = this.read();
                if (read >= 0) {
                    array[n + n3] = (byte)read;
                    ++n3;
                }
                else {
                    if ((n4 = n3) == 0) {
                        n4 = -1;
                        break;
                    }
                    break;
                }
            }
            return n4;
        }
    }
    
    public static class Base64OutputStream extends FilterOutputStream
    {
        private byte[] b4;
        private boolean breakLines;
        private byte[] buffer;
        private int bufferLength;
        private byte[] decodabet;
        private boolean encode;
        private int lineLength;
        private int options;
        private int position;
        private boolean suspendEncoding;
        
        public Base64OutputStream(final OutputStream outputStream) {
            this(outputStream, 1);
        }
        
        public Base64OutputStream(final OutputStream outputStream, final int options) {
            final boolean b = true;
            super(outputStream);
            this.breakLines = ((options & 0x8) != 0x0);
            this.encode = ((options & 0x1) != 0x0 && b);
            int bufferLength;
            if (this.encode) {
                bufferLength = 3;
            }
            else {
                bufferLength = 4;
            }
            this.bufferLength = bufferLength;
            this.buffer = new byte[this.bufferLength];
            this.position = 0;
            this.lineLength = 0;
            this.suspendEncoding = false;
            this.b4 = new byte[4];
            this.options = options;
            this.decodabet = getDecodabet(options);
        }
        
        @Override
        public void close() throws IOException {
            this.flushBase64();
            super.close();
            this.buffer = null;
            this.out = null;
        }
        
        public void flushBase64() throws IOException {
            if (this.position > 0) {
                if (!this.encode) {
                    throw new IOException("Base64 input not properly padded.");
                }
                this.out.write(encode3to4(this.b4, this.buffer, this.position, this.options));
                this.position = 0;
            }
        }
        
        public void resumeEncoding() {
            this.suspendEncoding = false;
        }
        
        public void suspendEncoding() throws IOException {
            this.flushBase64();
            this.suspendEncoding = true;
        }
        
        @Override
        public void write(int access$200) throws IOException {
            if (this.suspendEncoding) {
                this.out.write(access$200);
            }
            else if (this.encode) {
                this.buffer[this.position++] = (byte)access$200;
                if (this.position >= this.bufferLength) {
                    this.out.write(encode3to4(this.b4, this.buffer, this.bufferLength, this.options));
                    this.lineLength += 4;
                    if (this.breakLines && this.lineLength >= 76) {
                        this.out.write(10);
                        this.lineLength = 0;
                    }
                    this.position = 0;
                }
            }
            else if (this.decodabet[access$200 & 0x7F] > -5) {
                this.buffer[this.position++] = (byte)access$200;
                if (this.position >= this.bufferLength) {
                    access$200 = decode4to3(this.buffer, 0, this.b4, 0, this.options);
                    this.out.write(this.b4, 0, access$200);
                    this.position = 0;
                }
            }
            else if (this.decodabet[access$200 & 0x7F] != -5) {
                throw new IOException("Invalid character in Base64 data.");
            }
        }
        
        @Override
        public void write(final byte[] array, final int n, final int n2) throws IOException {
            if (this.suspendEncoding) {
                this.out.write(array, n, n2);
            }
            else {
                for (int i = 0; i < n2; ++i) {
                    this.write(array[n + i]);
                }
            }
        }
    }
}
