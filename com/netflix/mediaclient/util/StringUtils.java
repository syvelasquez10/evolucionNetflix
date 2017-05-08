// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.regex.Matcher;
import com.netflix.mediaclient.android.widget.InternalURLSpan;
import android.text.style.URLSpan;
import android.text.Spannable;
import android.net.Uri;
import com.netflix.mediaclient.Log;
import java.io.FileInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import android.content.res.Resources;
import com.netflix.mediaclient.util.l10n.LocalizationUtils;
import java.util.StringTokenizer;
import android.util.Pair;
import android.text.Html;
import java.util.Iterator;
import java.util.Collection;
import android.text.style.StyleSpan;
import android.text.SpannableStringBuilder;
import android.content.Context;
import java.util.Locale;
import java.util.regex.Pattern;

public final class StringUtils
{
    private static final String DETAILS_TEXT_SPACER = "   ";
    public static final String EMPTY_STRING = "";
    private static final char[] HEX_CHAR;
    public static final String NULL_STRING_VALUE = "null";
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
        return capitalizeFirstLetter(s, null);
    }
    
    public static String capitalizeFirstLetter(String upperCase, final Locale locale) {
        if (isEmpty(upperCase)) {
            return upperCase;
        }
        Locale default1;
        if ((default1 = locale) == null) {
            default1 = Locale.getDefault();
        }
        final char[] charArray = upperCase.toCharArray();
        upperCase = upperCase.toUpperCase(default1);
        final StringBuilder append = new StringBuilder().append(upperCase.charAt(0));
        for (int i = 1; i < charArray.length; ++i) {
            append.append(charArray[i]);
        }
        return append.toString();
    }
    
    public static CharSequence createBoldLabeledText(final Context context, final int n, final String s) {
        if (context == null) {
            return "";
        }
        return createBoldLabeledText(context, context.getString(n), s);
    }
    
    public static CharSequence createBoldLabeledText(final Context context, final String s, final String s2) {
        if (context == null || isEmpty(s2)) {
            return "";
        }
        final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder((CharSequence)s);
        spannableStringBuilder.setSpan((Object)new StyleSpan(1), 0, s.length(), 0);
        spannableStringBuilder.append((CharSequence)" ");
        spannableStringBuilder.append((CharSequence)s2);
        return (CharSequence)spannableStringBuilder;
    }
    
    public static <T> String createStringFromCollection(final Collection<T> collection) {
        if (collection == null || collection.size() == 0) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        final Iterator<T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next());
            sb.append(", ");
        }
        return sb.substring(0, sb.length() - ", ".length());
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
        int n = 0;
        if (s == null || "".equals(s.trim())) {
            return new String[0];
        }
        if (s2 == null) {
            return new String[] { s };
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final String[] array = new String[stringTokenizer.countTokens()];
        while (stringTokenizer.hasMoreTokens()) {
            array[n] = stringTokenizer.nextToken();
            ++n;
        }
        return array;
    }
    
    public static CharSequence getBasicMovieInfoString(final Context context, final int n, final String s, final int n2) {
        if (context == null) {
            return "";
        }
        final Resources resources = context.getResources();
        if (resources == null) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        LocalizationUtils.addMarkerForRtLocale(sb, '\u200f');
        if (n > 0) {
            sb.append(n).append("   ");
        }
        if (isNotEmpty(s)) {
            LocalizationUtils.addMarkerForRtLocale(sb, '\u202a');
            sb.append(s);
            LocalizationUtils.addMarkerForRtLocale(sb, '\u202b');
            sb.append("   ");
        }
        if (n2 > 0) {
            sb.append(LocalizationUtils.forceLayoutDirectionIfNeeded(resources.getString(2131231123, new Object[] { TimeUtils.convertSecondsToMinutes(n2) })));
        }
        return sb.toString();
    }
    
    public static CharSequence getBasicMovieInfoString(final Context context, final MovieDetails movieDetails) {
        if (context == null) {
            return "";
        }
        return getBasicMovieInfoString(context, movieDetails.getYear(), movieDetails.getCertification(), movieDetails.getPlayable().getRuntime());
    }
    
    public static CharSequence getBasicMovieInfoString(final Context context, final VideoDetails videoDetails) {
        if (context == null) {
            return "";
        }
        return getBasicMovieInfoString(context, videoDetails.getYear(), videoDetails.getCertification(), videoDetails.getPlayable().getRuntime());
    }
    
    public static CharSequence getBasicShowInfoString(final Context context, final int n, final String s, final String s2) {
        if (context == null) {
            return "";
        }
        if (context.getResources() == null) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        LocalizationUtils.addMarkerForRtLocale(sb, '\u200f');
        if (n > 0) {
            sb.append(n).append("   ");
        }
        if (isNotEmpty(s)) {
            LocalizationUtils.addMarkerForRtLocale(sb, '\u202a');
            sb.append(s);
            LocalizationUtils.addMarkerForRtLocale(sb, '\u202b');
            sb.append("   ");
        }
        if (isNotEmpty(s2)) {
            LocalizationUtils.addMarkerForRtLocale(sb, '\u200f');
            sb.append(s2);
        }
        return sb.toString();
    }
    
    public static CharSequence getBasicShowInfoString(final Context context, final ShowDetails showDetails) {
        if (context == null) {
            return "";
        }
        return getBasicShowInfoString(context, showDetails.getYear(), showDetails.getCertification(), showDetails.getNumSeasonsLabel());
    }
    
    public static int getCsvCount(final String s) {
        if (isEmpty(s)) {
            return 0;
        }
        return s.split(",").length;
    }
    
    public static String getFileAsString(File file) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Object o2 = null;
        Object o = null;
        while (true) {
            try {
                final File file2 = file = (File)new FileInputStream(file);
                Label_0085: {
                    try {
                        final byte[] array = new byte[1024];
                        while (true) {
                            file = file2;
                            final int read = ((InputStream)file2).read(array);
                            if (read == -1) {
                                break Label_0085;
                            }
                            file = file2;
                            byteArrayOutputStream.write(array, 0, read);
                        }
                    }
                    catch (Exception o2) {
                        file = file2;
                        o = o2;
                        throw new Exception("Problem while trying to read file", (Throwable)o);
                    }
                    finally {
                        o = file;
                        file = (File)o2;
                    }
                    try {
                        byteArrayOutputStream.close();
                        ((InputStream)o).close();
                        throw file;
                        file = (File)o;
                        byteArrayOutputStream.flush();
                        file = (File)o;
                        o2 = new String(byteArrayOutputStream.toByteArray());
                        final ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                        byteArrayOutputStream2.close();
                        final Throwable t = (Throwable)o;
                        ((InputStream)t).close();
                        final Object o3 = o2;
                        return (String)o3;
                    }
                    catch (Exception ex) {}
                }
            }
            catch (Exception ex2) {}
            finally {
                final File file3;
                file = file3;
                o = null;
                continue;
            }
            break;
        }
        try {
            final ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
            byteArrayOutputStream2.close();
            final Throwable t = (Throwable)o;
            ((InputStream)t).close();
            final Object o3 = o2;
            return (String)o3;
        }
        catch (Exception ex3) {
            return (String)o2;
        }
    }
    
    public static String getFilenameFromUri(final String s) {
        if (isEmpty(s)) {
            throw new IllegalArgumentException("Empty uri string");
        }
        final int lastIndex = s.lastIndexOf("/");
        String s2;
        if (lastIndex > 0 && lastIndex < s.length() - 1) {
            final String substring = s.substring(lastIndex + 1);
            final int index = substring.indexOf(63);
            s2 = substring;
            if (index > 0) {
                s2 = substring.substring(0, index);
            }
        }
        else {
            Log.w("StringUtils", "No filename found in URI - using hashcode: " + s);
            s2 = String.valueOf(s.hashCode());
        }
        if (Log.isLoggable()) {
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
    
    public static String getRawString(final Resources p0, final int p1) {
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
    
    public static String getRemoteDataAsString(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore_3       
        //     2: aconst_null    
        //     3: astore          4
        //     5: new             Ljava/io/ByteArrayOutputStream;
        //     8: dup            
        //     9: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    12: astore          5
        //    14: new             Ljava/net/URL;
        //    17: dup            
        //    18: aload_0        
        //    19: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    22: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    25: astore_2       
        //    26: aload           4
        //    28: astore_0       
        //    29: aload_2        
        //    30: sipush          5000
        //    33: invokevirtual   java/net/URLConnection.setConnectTimeout:(I)V
        //    36: aload           4
        //    38: astore_0       
        //    39: aload_2        
        //    40: sipush          5000
        //    43: invokevirtual   java/net/URLConnection.setReadTimeout:(I)V
        //    46: aload           4
        //    48: astore_0       
        //    49: aload_2        
        //    50: invokevirtual   java/net/URLConnection.connect:()V
        //    53: aload           4
        //    55: astore_0       
        //    56: aload_2        
        //    57: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
        //    60: astore_3       
        //    61: aload_3        
        //    62: astore_0       
        //    63: sipush          1024
        //    66: newarray        B
        //    68: astore          4
        //    70: aload_3        
        //    71: astore_0       
        //    72: aload_3        
        //    73: aload           4
        //    75: invokevirtual   java/io/InputStream.read:([B)I
        //    78: istore_1       
        //    79: iload_1        
        //    80: iconst_m1      
        //    81: if_icmpeq       143
        //    84: aload_3        
        //    85: astore_0       
        //    86: aload           5
        //    88: aload           4
        //    90: iconst_0       
        //    91: iload_1        
        //    92: invokevirtual   java/io/ByteArrayOutputStream.write:([BII)V
        //    95: goto            70
        //    98: astore          4
        //   100: aload_0        
        //   101: astore_3       
        //   102: aload           4
        //   104: astore_0       
        //   105: aload           5
        //   107: ifnull          115
        //   110: aload           5
        //   112: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   115: aload_3        
        //   116: ifnull          123
        //   119: aload_3        
        //   120: invokevirtual   java/io/InputStream.close:()V
        //   123: aload_2        
        //   124: ifnull          141
        //   127: aload_2        
        //   128: instanceof      Ljava/net/HttpURLConnection;
        //   131: ifeq            141
        //   134: aload_2        
        //   135: checkcast       Ljava/net/HttpURLConnection;
        //   138: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   141: aload_0        
        //   142: athrow         
        //   143: aload_3        
        //   144: astore_0       
        //   145: aload           5
        //   147: invokevirtual   java/io/ByteArrayOutputStream.flush:()V
        //   150: aload_3        
        //   151: astore_0       
        //   152: new             Ljava/lang/String;
        //   155: dup            
        //   156: aload           5
        //   158: invokevirtual   java/io/ByteArrayOutputStream.toByteArray:()[B
        //   161: invokespecial   java/lang/String.<init>:([B)V
        //   164: astore          4
        //   166: aload           5
        //   168: ifnull          176
        //   171: aload           5
        //   173: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   176: aload_3        
        //   177: ifnull          184
        //   180: aload_3        
        //   181: invokevirtual   java/io/InputStream.close:()V
        //   184: aload_2        
        //   185: ifnull          202
        //   188: aload_2        
        //   189: instanceof      Ljava/net/HttpURLConnection;
        //   192: ifeq            202
        //   195: aload_2        
        //   196: checkcast       Ljava/net/HttpURLConnection;
        //   199: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   202: aload           4
        //   204: areturn        
        //   205: astore_2       
        //   206: goto            141
        //   209: astore_0       
        //   210: aconst_null    
        //   211: astore_2       
        //   212: goto            105
        //   215: astore_0       
        //   216: aload           4
        //   218: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  14     26     209    215    Any
        //  29     36     98     105    Any
        //  39     46     98     105    Any
        //  49     53     98     105    Any
        //  56     61     98     105    Any
        //  63     70     98     105    Any
        //  72     79     98     105    Any
        //  86     95     98     105    Any
        //  110    115    205    209    Ljava/lang/Exception;
        //  119    123    205    209    Ljava/lang/Exception;
        //  127    141    205    209    Ljava/lang/Exception;
        //  145    150    98     105    Any
        //  152    166    98     105    Any
        //  171    176    215    219    Ljava/lang/Exception;
        //  180    184    215    219    Ljava/lang/Exception;
        //  188    202    215    219    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0176:
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
    
    public static String getSubStringSafely(final String s, final int n) {
        String s2;
        if (s == null) {
            s2 = null;
        }
        else {
            s2 = s;
            if (n >= 0) {
                s2 = s;
                if (s.length() > n) {
                    return s.substring(0, n);
                }
            }
        }
        return s2;
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
    
    public static boolean isNumeric(final String s) {
        return isNumeric(s, 10);
    }
    
    public static boolean isNumeric(String trim, final int n) {
        if (!isEmpty(trim)) {
            trim = trim.trim();
            for (int i = 0; i < trim.length(); ++i) {
                if (i == 0 && trim.charAt(i) == '-') {
                    if (trim.length() == 1) {
                        return false;
                    }
                }
                else if (Character.digit(trim.charAt(i), n) < 0) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static String joinArray(final String[] array) {
        return joinArray(array, null);
    }
    
    public static String joinArray(final String[] array, final String s) {
        int n = 1;
        if (array == null || array.length < 1) {
            return "";
        }
        String s2;
        if ((s2 = s) == null) {
            s2 = ",";
        }
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
    
    public static boolean modifyUrlHandling(final Spannable spannable) {
        final URLSpan[] array = (URLSpan[])spannable.getSpans(0, spannable.length(), (Class)URLSpan.class);
        if (array.length < 1) {
            return false;
        }
        for (int length = array.length, i = 0; i < length; ++i) {
            final URLSpan urlSpan = array[i];
            final int spanStart = spannable.getSpanStart((Object)urlSpan);
            final int spanEnd = spannable.getSpanEnd((Object)urlSpan);
            spannable.removeSpan((Object)urlSpan);
            spannable.setSpan((Object)new InternalURLSpan(urlSpan.getURL()), spanStart, spanEnd, 0);
        }
        return true;
    }
    
    public static String notEmpty(final String s, final String s2) {
        if (isEmpty(s)) {
            return s2;
        }
        return s;
    }
    
    public static String notNull(final String s, final String s2) {
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
    
    public static String safeGetClassName(final Object o) {
        if (o == null) {
            return "null object";
        }
        return o.getClass().getSimpleName();
    }
    
    public static Integer safeParsePercentage(final String s) {
        if (!isEmpty(s)) {
            try {
                final Matcher matcher = StringUtils.PERCENTAGE_PATTERN.matcher(s);
                if (matcher.find()) {
                    return Integer.valueOf(matcher.group().replaceAll("%", ""));
                }
            }
            catch (NumberFormatException ex) {
                Log.e("StringUtils", "Failed to parse percentage ", ex);
                return null;
            }
        }
        return null;
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
        if (!isEmpty(s)) {
            try {
                final Matcher matcher = StringUtils.PIXEL_PATTERN.matcher(s.toLowerCase(Locale.US));
                if (matcher.find()) {
                    return Integer.valueOf(matcher.group().replaceAll("px", ""));
                }
            }
            catch (NumberFormatException ex) {
                Log.e("StringUtils", "Failed to parse pixel size ", ex);
                return null;
            }
        }
        return null;
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
