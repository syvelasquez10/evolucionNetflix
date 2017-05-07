// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.regex.Matcher;
import java.util.Locale;
import java.io.IOException;
import android.net.Uri;
import com.netflix.mediaclient.Log;
import java.io.File;
import com.netflix.mediaclient.servicemgr.VideoDetails;
import android.content.res.Resources;
import com.netflix.mediaclient.servicemgr.ShowDetails;
import java.util.StringTokenizer;
import android.util.Pair;
import android.text.Html;
import android.text.style.StyleSpan;
import android.text.SpannableStringBuilder;
import android.content.Context;
import java.util.regex.Pattern;

public final class StringUtils
{
    private static final String DETAILS_TEXT_SPACER = "   ";
    public static final String EMPTY_STRING = "";
    private static final char[] HEX_CHAR;
    private static final Pattern PERCENTAGE_PATTERN;
    private static final Pattern PIXEL_PATTERN;
    public static final String SPACE_SPLIT_REG_EXP = " ";
    private static final Pattern STARTS_WITH_DIGIT_PATTERN;
    private static final String TAG = "StringUtils";
    public static final String UTF_8 = "utf-8";
    public static final String WHITE_SPACE_SPLIT_REG_EXP = "\\s+";
    
    static {
        HEX_CHAR = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        PERCENTAGE_PATTERN = Pattern.compile("^[0-9]+%$");
        PIXEL_PATTERN = Pattern.compile("^[0-9]+px$", 2);
        STARTS_WITH_DIGIT_PATTERN = Pattern.compile("^[0-9]");
    }
    
    public static String buildUnencodedUrlParam(final String s, final String s2) {
        final StringBuilder sb = new StringBuilder("&");
        sb.append(s);
        sb.append("=");
        sb.append(s2);
        return sb.toString();
    }
    
    public static String capitalizeFirstLetter(final String s) {
        if (isEmpty(s)) {
            return s;
        }
        final char[] charArray = s.toCharArray();
        final StringBuilder append = new StringBuilder().append(Character.toUpperCase(charArray[0]));
        for (int i = 1; i < charArray.length; ++i) {
            append.append(charArray[i]);
        }
        return append.toString();
    }
    
    public static CharSequence createBoldLabeledText(final Context context, final int n, final String s) {
        if (context == null) {
            return "";
        }
        final String string = context.getString(n);
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder((CharSequence)string);
        spannableStringBuilder.setSpan((Object)new StyleSpan(1), 0, string.length(), 0);
        String string2 = s;
        if (isEmpty(s)) {
            string2 = context.getString(2131493167);
        }
        spannableStringBuilder.append((CharSequence)" ");
        spannableStringBuilder.append((CharSequence)string2);
        return (CharSequence)spannableStringBuilder;
    }
    
    public static String decodeHtmlEntities(final String s) {
        if (isEmpty(s)) {
            return s;
        }
        return Html.fromHtml(s).toString();
    }
    
    public static String escapeJsonChars(final String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        final int length = s.length();
        final StringBuilder sb = new StringBuilder(length + 4);
        for (int i = 0; i < length; ++i) {
            final char char1 = s.charAt(i);
            switch (char1) {
                default: {
                    sb.append(char1);
                    break;
                }
                case 34:
                case 39:
                case 92: {
                    sb.append('\\');
                    sb.append(char1);
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    public static Pair<Integer, Integer> extractNumberPair(final String s) {
        final String[] safeSplit = safeSplit(s, "\\s+");
        if (safeSplit.length >= 2) {
            return (Pair<Integer, Integer>)new Pair((Object)Integer.parseInt(safeSplit[0]), (Object)Integer.parseInt(safeSplit[1]));
        }
        return null;
    }
    
    public static String[] extractTokens(final String s, final String s2) {
        String[] array;
        if (s == null || "".equals(s.trim())) {
            array = new String[0];
        }
        else {
            if (s2 == null) {
                return new String[] { s };
            }
            final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
            final String[] array2 = new String[stringTokenizer.countTokens()];
            int n = 0;
            while (true) {
                array = array2;
                if (!stringTokenizer.hasMoreTokens()) {
                    break;
                }
                array2[n] = stringTokenizer.nextToken();
                ++n;
            }
        }
        return array;
    }
    
    public static CharSequence getBasicInfoString(final Context context, final ShowDetails showDetails) {
        if (context == null) {
            return "";
        }
        final Resources resources = context.getResources();
        if (resources == null) {
            return "";
        }
        return showDetails.getYear() + "   " + showDetails.getCertification() + "   " + resources.getQuantityString(2131623936, showDetails.getNumOfSeasons(), new Object[] { showDetails.getNumOfSeasons() });
    }
    
    public static CharSequence getBasicInfoString(final Context context, final VideoDetails videoDetails) {
        final StringBuilder sb = new StringBuilder();
        if (videoDetails.getYear() > 0) {
            sb.append(videoDetails.getYear()).append("   ");
        }
        if (videoDetails.getCertification() != null) {
            sb.append(videoDetails.getCertification()).append("   ");
        }
        sb.append(String.format(context.getResources().getString(2131493172), TimeUtils.convertSecondsToMinutes(videoDetails.getRuntime())));
        return sb.toString();
    }
    
    public static String getFileAsString(final File p0) throws Exception {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_2       
        //     2: aconst_null    
        //     3: astore_3       
        //     4: new             Ljava/io/ByteArrayOutputStream;
        //     7: dup            
        //     8: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    11: astore          4
        //    13: new             Ljava/io/FileInputStream;
        //    16: dup            
        //    17: aload_0        
        //    18: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    21: astore_0       
        //    22: sipush          1024
        //    25: newarray        B
        //    27: astore_2       
        //    28: aload_0        
        //    29: aload_2        
        //    30: invokevirtual   java/io/InputStream.read:([B)I
        //    33: istore_1       
        //    34: iload_1        
        //    35: iconst_m1      
        //    36: if_icmpeq       79
        //    39: aload           4
        //    41: aload_2        
        //    42: iconst_0       
        //    43: iload_1        
        //    44: invokevirtual   java/io/ByteArrayOutputStream.write:([BII)V
        //    47: goto            28
        //    50: astore_3       
        //    51: aload_0        
        //    52: astore_2       
        //    53: aload_3        
        //    54: astore_0       
        //    55: new             Ljava/lang/Exception;
        //    58: dup            
        //    59: ldc_w           "Problem while trying to read file"
        //    62: aload_0        
        //    63: invokespecial   java/lang/Exception.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    66: athrow         
        //    67: astore_0       
        //    68: aload           4
        //    70: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //    73: aload_2        
        //    74: invokevirtual   java/io/InputStream.close:()V
        //    77: aload_0        
        //    78: athrow         
        //    79: aload           4
        //    81: invokevirtual   java/io/ByteArrayOutputStream.flush:()V
        //    84: new             Ljava/lang/String;
        //    87: dup            
        //    88: aload           4
        //    90: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //    93: invokespecial   java/lang/String.<init>:([B)V
        //    96: astore_2       
        //    97: aload           4
        //    99: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   102: aload_0        
        //   103: invokevirtual   java/io/InputStream.close:()V
        //   106: aload_2        
        //   107: areturn        
        //   108: astore_2       
        //   109: goto            77
        //   112: astore_3       
        //   113: aload_0        
        //   114: astore_2       
        //   115: aload_3        
        //   116: astore_0       
        //   117: goto            68
        //   120: astore_0       
        //   121: aload_3        
        //   122: astore_2       
        //   123: goto            55
        //   126: astore_0       
        //   127: aload_2        
        //   128: areturn        
        //    Exceptions:
        //  throws java.lang.Exception
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  13     22     120    126    Ljava/lang/Exception;
        //  13     22     67     68     Any
        //  22     28     50     55     Ljava/lang/Exception;
        //  22     28     112    120    Any
        //  28     34     50     55     Ljava/lang/Exception;
        //  28     34     112    120    Any
        //  39     47     50     55     Ljava/lang/Exception;
        //  39     47     112    120    Any
        //  55     67     67     68     Any
        //  68     77     108    112    Ljava/lang/Exception;
        //  79     97     50     55     Ljava/lang/Exception;
        //  79     97     112    120    Any
        //  97     106    126    129    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068:
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
    
    public static String getFilenameFromUri(final String s) {
        if (isEmpty(s)) {
            throw new IllegalArgumentException("Empty uri string");
        }
        final int lastIndex = s.lastIndexOf("/");
        String s2;
        if (lastIndex > 0 && lastIndex < s.length() - 1) {
            s2 = s.substring(lastIndex + 1);
        }
        else {
            Log.w("StringUtils", "No filename found in URI - using hashcode: " + s);
            s2 = String.valueOf(s.hashCode());
        }
        if (Log.isLoggable("StringUtils", 2)) {
            Log.v("StringUtils", "Generating uri from: " + s + ", file name: " + s2);
        }
        return s2;
    }
    
    public static String getPathFromUri(final String s) {
        if (isEmpty(s)) {
            throw new IllegalArgumentException("Empty uri string");
        }
        final Uri parse = Uri.parse(s);
        if (parse == null) {
            final String value = String.valueOf(s.hashCode());
            Log.w("StringUtils", "Could not parse uri: " + s + ", returning hash: " + value);
            return value;
        }
        return parse.getPath();
    }
    
    public static String getRawString(final Resources p0, final int p1) throws Exception {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aconst_null    
        //     3: astore_2       
        //     4: aload_0        
        //     5: iload_1        
        //     6: invokevirtual   android/content/res/Resources.openRawResource:(I)Ljava/io/InputStream;
        //     9: astore_0       
        //    10: aload_0        
        //    11: astore_2       
        //    12: aload_0        
        //    13: astore_3       
        //    14: aload_0        
        //    15: invokevirtual   java/io/InputStream.available:()I
        //    18: newarray        B
        //    20: astore          4
        //    22: aload_0        
        //    23: astore_2       
        //    24: aload_0        
        //    25: astore_3       
        //    26: aload_0        
        //    27: aload           4
        //    29: invokevirtual   java/io/InputStream.read:([B)I
        //    32: pop            
        //    33: aload_0        
        //    34: astore_2       
        //    35: aload_0        
        //    36: astore_3       
        //    37: new             Ljava/lang/String;
        //    40: dup            
        //    41: aload           4
        //    43: invokespecial   java/lang/String.<init>:([B)V
        //    46: astore          4
        //    48: aload_0        
        //    49: invokevirtual   java/io/InputStream.close:()V
        //    52: aload           4
        //    54: areturn        
        //    55: astore_0       
        //    56: aload_2        
        //    57: astore_3       
        //    58: new             Ljava/lang/Exception;
        //    61: dup            
        //    62: ldc_w           "Problem while trying to read raw"
        //    65: aload_0        
        //    66: invokespecial   java/lang/Exception.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    69: athrow         
        //    70: astore_0       
        //    71: aload_3        
        //    72: invokevirtual   java/io/InputStream.close:()V
        //    75: aload_0        
        //    76: athrow         
        //    77: astore_0       
        //    78: aload           4
        //    80: areturn        
        //    81: astore_2       
        //    82: goto            75
        //    Exceptions:
        //  throws java.lang.Exception
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      10     55     70     Ljava/lang/Exception;
        //  4      10     70     77     Any
        //  14     22     55     70     Ljava/lang/Exception;
        //  14     22     70     77     Any
        //  26     33     55     70     Ljava/lang/Exception;
        //  26     33     70     77     Any
        //  37     48     55     70     Ljava/lang/Exception;
        //  37     48     70     77     Any
        //  48     52     77     81     Ljava/lang/Exception;
        //  58     70     70     77     Any
        //  71     75     81     85     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 56, Size: 56
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3303)
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
    
    public static String getRemoteDataAsString(final String p0) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          4
        //     3: new             Ljava/io/ByteArrayOutputStream;
        //     6: dup            
        //     7: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    10: astore          5
        //    12: aconst_null    
        //    13: astore_2       
        //    14: aload           4
        //    16: astore_3       
        //    17: new             Ljava/net/URL;
        //    20: dup            
        //    21: aload_0        
        //    22: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    25: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    28: astore_0       
        //    29: aload_0        
        //    30: astore_2       
        //    31: aload           4
        //    33: astore_3       
        //    34: aload_0        
        //    35: sipush          5000
        //    38: invokevirtual   java/net/URLConnection.setConnectTimeout:(I)V
        //    41: aload_0        
        //    42: astore_2       
        //    43: aload           4
        //    45: astore_3       
        //    46: aload_0        
        //    47: sipush          5000
        //    50: invokevirtual   java/net/URLConnection.setReadTimeout:(I)V
        //    53: aload_0        
        //    54: astore_2       
        //    55: aload           4
        //    57: astore_3       
        //    58: aload_0        
        //    59: invokevirtual   java/net/URLConnection.connect:()V
        //    62: aload_0        
        //    63: astore_2       
        //    64: aload           4
        //    66: astore_3       
        //    67: aload_0        
        //    68: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
        //    71: astore          4
        //    73: aload_0        
        //    74: astore_2       
        //    75: aload           4
        //    77: astore_3       
        //    78: sipush          1024
        //    81: newarray        B
        //    83: astore          6
        //    85: aload_0        
        //    86: astore_2       
        //    87: aload           4
        //    89: astore_3       
        //    90: aload           4
        //    92: aload           6
        //    94: invokevirtual   java/io/InputStream.read:([B)I
        //    97: istore_1       
        //    98: iload_1        
        //    99: iconst_m1      
        //   100: if_icmpeq       159
        //   103: aload_0        
        //   104: astore_2       
        //   105: aload           4
        //   107: astore_3       
        //   108: aload           5
        //   110: aload           6
        //   112: iconst_0       
        //   113: iload_1        
        //   114: invokevirtual   java/io/ByteArrayOutputStream.write:([BII)V
        //   117: goto            85
        //   120: astore_0       
        //   121: aload           5
        //   123: ifnull          131
        //   126: aload           5
        //   128: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   131: aload_3        
        //   132: ifnull          139
        //   135: aload_3        
        //   136: invokevirtual   java/io/InputStream.close:()V
        //   139: aload_2        
        //   140: ifnull          157
        //   143: aload_2        
        //   144: instanceof      Ljava/net/HttpURLConnection;
        //   147: ifeq            157
        //   150: aload_2        
        //   151: checkcast       Ljava/net/HttpURLConnection;
        //   154: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   157: aload_0        
        //   158: athrow         
        //   159: aload_0        
        //   160: astore_2       
        //   161: aload           4
        //   163: astore_3       
        //   164: aload           5
        //   166: invokevirtual   java/io/ByteArrayOutputStream.flush:()V
        //   169: aload_0        
        //   170: astore_2       
        //   171: aload           4
        //   173: astore_3       
        //   174: new             Ljava/lang/String;
        //   177: dup            
        //   178: aload           5
        //   180: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   183: invokespecial   java/lang/String.<init>:([B)V
        //   186: astore          6
        //   188: aload           5
        //   190: ifnull          198
        //   193: aload           5
        //   195: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   198: aload           4
        //   200: ifnull          208
        //   203: aload           4
        //   205: invokevirtual   java/io/InputStream.close:()V
        //   208: aload_0        
        //   209: ifnull          226
        //   212: aload_0        
        //   213: instanceof      Ljava/net/HttpURLConnection;
        //   216: ifeq            226
        //   219: aload_0        
        //   220: checkcast       Ljava/net/HttpURLConnection;
        //   223: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   226: aload           6
        //   228: areturn        
        //   229: astore_2       
        //   230: goto            157
        //   233: astore_0       
        //   234: aload           6
        //   236: areturn        
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  17     29     120    159    Any
        //  34     41     120    159    Any
        //  46     53     120    159    Any
        //  58     62     120    159    Any
        //  67     73     120    159    Any
        //  78     85     120    159    Any
        //  90     98     120    159    Any
        //  108    117    120    159    Any
        //  126    131    229    233    Ljava/lang/Exception;
        //  135    139    229    233    Ljava/lang/Exception;
        //  143    157    229    233    Ljava/lang/Exception;
        //  164    169    120    159    Any
        //  174    188    120    159    Any
        //  193    198    233    237    Ljava/lang/Exception;
        //  203    208    233    237    Ljava/lang/Exception;
        //  212    226    233    237    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0131:
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
    
    public static boolean isEmpty(final CharSequence charSequence) {
        return charSequence == null || "".equals(charSequence);
    }
    
    public static boolean isEmpty(final String s) {
        return s == null || "".equals(s.trim());
    }
    
    public static boolean isNotEmpty(final CharSequence charSequence) {
        return !isEmpty(charSequence);
    }
    
    public static boolean isNotEmpty(final String s) {
        return !isEmpty(s);
    }
    
    public static String joinArray(final String[] array) {
        return joinArray(array, null);
    }
    
    public static String joinArray(final String[] array, final String s) {
        if (array == null || array.length < 1) {
            return "";
        }
        String s2;
        if ((s2 = s) == null) {
            s2 = ",";
        }
        int n = 1;
        final StringBuilder sb = new StringBuilder();
        for (int length = array.length, i = 0; i < length; ++i) {
            final String s3 = array[i];
            if (s3 != null) {
                if (n != 0) {
                    n = 0;
                }
                else {
                    sb.append(s2);
                }
                sb.append(s3);
            }
        }
        return sb.toString();
    }
    
    public static String modifyByEsnRules(String upperCase) {
        if (upperCase == null || "".equals(upperCase.trim())) {
            return "";
        }
        upperCase = upperCase.toUpperCase(Locale.ENGLISH);
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < upperCase.length(); ++i) {
            final char char1 = upperCase.charAt(i);
            if ((char1 >= 'A' && char1 <= 'Z') || (char1 >= '0' && char1 <= '9') || char1 == '-' || char1 == '=') {
                sb.append(char1);
            }
            else {
                sb.append('=');
            }
        }
        return sb.toString();
    }
    
    public static String notEmpty(final String s, final String s2) {
        if (isEmpty(s)) {
            return s2;
        }
        return s;
    }
    
    public static String notNull(final String s, final String s2) throws IllegalArgumentException {
        if (s2 == null) {
            throw new IllegalArgumentException(s + " can not be null!");
        }
        return s2;
    }
    
    public static String replaceWhiteSpace(final String s, final String s2) {
        return replaceWhiteSpace(s, s2, true);
    }
    
    public static String replaceWhiteSpace(final String s, final String s2, final boolean b) {
        if (s == null) {
            return "";
        }
        if (b) {
            return s.trim().replaceAll("\\s", s2);
        }
        return s.replaceAll("\\s", s2);
    }
    
    public static boolean safeEquals(final String s, final String s2) {
        if (s == null) {
            return s2 == null;
        }
        return s.equals(s2);
    }
    
    public static Integer safeParsePercentage(final String s) {
        if (isEmpty(s)) {
            return null;
        }
        try {
            final Matcher matcher = StringUtils.PERCENTAGE_PATTERN.matcher(s);
            if (!matcher.find()) {
                return null;
            }
            return Integer.valueOf(matcher.group().replaceAll("%", ""));
        }
        catch (NumberFormatException ex) {
            Log.e("StringUtils", "Failed to parse percentage ", ex);
            return null;
        }
    }
    
    public static Integer safeParsePercentage(final String s, final int n, final int n2, final boolean b) {
        final Integer safeParsePercentage = safeParsePercentage(s);
        if (safeParsePercentage != null) {
            if (safeParsePercentage < n) {
                if (b) {
                    return null;
                }
                return n;
            }
            else if (safeParsePercentage > n2) {
                if (b) {
                    return null;
                }
                return n2;
            }
        }
        return safeParsePercentage;
    }
    
    public static Integer safeParsePixelSize(final String s) {
        if (isEmpty(s)) {
            return null;
        }
        try {
            final Matcher matcher = StringUtils.PIXEL_PATTERN.matcher(s.toLowerCase(Locale.US));
            if (!matcher.find()) {
                return null;
            }
            return Integer.valueOf(matcher.group().replaceAll("px", ""));
        }
        catch (NumberFormatException ex) {
            Log.e("StringUtils", "Failed to parse pixel size ", ex);
            return null;
        }
    }
    
    public static Integer safeParsePixelSize(final String s, final int n, final int n2, final boolean b) {
        final Integer safeParsePixelSize = safeParsePixelSize(s);
        if (safeParsePixelSize != null) {
            if (safeParsePixelSize < n) {
                if (b) {
                    return null;
                }
                return n;
            }
            else if (safeParsePixelSize > n2) {
                if (b) {
                    return null;
                }
                return n2;
            }
        }
        return safeParsePixelSize;
    }
    
    public static String[] safeSplit(final String s, final String s2) {
        if (isEmpty(s)) {
            return new String[0];
        }
        return s.split(s2);
    }
    
    public static boolean startsWithDigit(final String s) {
        return !isEmpty(s) && StringUtils.STARTS_WITH_DIGIT_PATTERN.matcher(s).find();
    }
    
    public static String toHex(final byte[] array) {
        final char[] array2 = new char[array.length * 2];
        for (int i = 0; i < 20; ++i) {
            final int n = array[i] & 0xFF;
            array2[i * 2] = StringUtils.HEX_CHAR[n >> 4];
            array2[i * 2 + 1] = StringUtils.HEX_CHAR[n & 0xF];
        }
        return new String(array2);
    }
    
    public static String toUnicode(final char c) {
        return String.format("\\u%04x", (int)c);
    }
    
    public static String trimWhiteSpace(final String s) {
        if (s == null) {
            return "";
        }
        return s.trim().replaceAll("\\s+", " ");
    }
}
