// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import com.facebook.model.GraphMultiResult;
import android.text.TextUtils;
import android.util.Pair;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import com.facebook.model.GraphObjectList;
import java.util.Map;
import java.text.SimpleDateFormat;
import android.os.Parcelable;
import java.util.Locale;
import java.util.Date;
import java.io.File;
import android.location.Location;
import android.os.Handler;
import java.util.HashSet;
import java.net.URLConnection;
import com.facebook.internal.Utility;
import java.util.Arrays;
import java.util.Collection;
import com.facebook.internal.Validate;
import java.net.HttpURLConnection;
import java.util.Iterator;
import android.net.Uri$Builder;
import java.util.List;
import java.net.URL;
import android.os.Bundle;
import com.facebook.model.GraphObject;
import android.os.ParcelFileDescriptor;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap;
import java.io.BufferedOutputStream;
import com.facebook.internal.Logger;

class Request$Serializer implements Request$KeyValueSerializer
{
    private boolean firstWrite;
    private final Logger logger;
    private final BufferedOutputStream outputStream;
    
    public Request$Serializer(final BufferedOutputStream outputStream, final Logger logger) {
        this.firstWrite = true;
        this.outputStream = outputStream;
        this.logger = logger;
    }
    
    public void write(final String s, final Object... array) {
        if (this.firstWrite) {
            this.outputStream.write("--".getBytes());
            this.outputStream.write("3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f".getBytes());
            this.outputStream.write("\r\n".getBytes());
            this.firstWrite = false;
        }
        this.outputStream.write(String.format(s, array).getBytes());
    }
    
    public void writeBitmap(final String s, final Bitmap bitmap) {
        this.writeContentDisposition(s, s, "image/png");
        bitmap.compress(Bitmap$CompressFormat.PNG, 100, (OutputStream)this.outputStream);
        this.writeLine("", new Object[0]);
        this.writeRecordBoundary();
        this.logger.appendKeyValue("    " + s, "<Image>");
    }
    
    public void writeBytes(final String s, final byte[] array) {
        this.writeContentDisposition(s, s, "content/unknown");
        this.outputStream.write(array);
        this.writeLine("", new Object[0]);
        this.writeRecordBoundary();
        this.logger.appendKeyValue("    " + s, String.format("<Data: %d>", array.length));
    }
    
    public void writeContentDisposition(final String s, final String s2, final String s3) {
        this.write("Content-Disposition: form-data; name=\"%s\"", s);
        if (s2 != null) {
            this.write("; filename=\"%s\"", s2);
        }
        this.writeLine("", new Object[0]);
        if (s3 != null) {
            this.writeLine("%s: %s", "Content-Type", s3);
        }
        this.writeLine("", new Object[0]);
    }
    
    public void writeFile(final String p0, final ParcelFileDescriptor p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          5
        //     3: aload_0        
        //     4: aload_1        
        //     5: aload_1        
        //     6: ldc             "content/unknown"
        //     8: invokevirtual   com/facebook/Request$Serializer.writeContentDisposition:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    11: new             Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
        //    14: dup            
        //    15: aload_2        
        //    16: invokespecial   android/os/ParcelFileDescriptor$AutoCloseInputStream.<init>:(Landroid/os/ParcelFileDescriptor;)V
        //    19: astore_2       
        //    20: new             Ljava/io/BufferedInputStream;
        //    23: dup            
        //    24: aload_2        
        //    25: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    28: astore          6
        //    30: sipush          8192
        //    33: newarray        B
        //    35: astore          5
        //    37: iconst_0       
        //    38: istore_3       
        //    39: aload           6
        //    41: aload           5
        //    43: invokevirtual   java/io/BufferedInputStream.read:([B)I
        //    46: istore          4
        //    48: iload           4
        //    50: iconst_m1      
        //    51: if_icmpeq       74
        //    54: aload_0        
        //    55: getfield        com/facebook/Request$Serializer.outputStream:Ljava/io/BufferedOutputStream;
        //    58: aload           5
        //    60: iconst_0       
        //    61: iload           4
        //    63: invokevirtual   java/io/BufferedOutputStream.write:([BII)V
        //    66: iload_3        
        //    67: iload           4
        //    69: iadd           
        //    70: istore_3       
        //    71: goto            39
        //    74: aload           6
        //    76: ifnull          84
        //    79: aload           6
        //    81: invokevirtual   java/io/BufferedInputStream.close:()V
        //    84: aload_2        
        //    85: ifnull          92
        //    88: aload_2        
        //    89: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseInputStream.close:()V
        //    92: aload_0        
        //    93: ldc             ""
        //    95: iconst_0       
        //    96: anewarray       Ljava/lang/Object;
        //    99: invokevirtual   com/facebook/Request$Serializer.writeLine:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   102: aload_0        
        //   103: invokevirtual   com/facebook/Request$Serializer.writeRecordBoundary:()V
        //   106: aload_0        
        //   107: getfield        com/facebook/Request$Serializer.logger:Lcom/facebook/internal/Logger;
        //   110: new             Ljava/lang/StringBuilder;
        //   113: dup            
        //   114: invokespecial   java/lang/StringBuilder.<init>:()V
        //   117: ldc             "    "
        //   119: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   122: aload_1        
        //   123: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   126: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   129: ldc             "<Data: %d>"
        //   131: iconst_1       
        //   132: anewarray       Ljava/lang/Object;
        //   135: dup            
        //   136: iconst_0       
        //   137: iload_3        
        //   138: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   141: aastore        
        //   142: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   145: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //   148: return         
        //   149: astore_1       
        //   150: aconst_null    
        //   151: astore_2       
        //   152: aload_2        
        //   153: ifnull          160
        //   156: aload_2        
        //   157: invokevirtual   java/io/BufferedInputStream.close:()V
        //   160: aload           5
        //   162: ifnull          170
        //   165: aload           5
        //   167: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseInputStream.close:()V
        //   170: aload_1        
        //   171: athrow         
        //   172: astore_1       
        //   173: aconst_null    
        //   174: astore          6
        //   176: aload_2        
        //   177: astore          5
        //   179: aload           6
        //   181: astore_2       
        //   182: goto            152
        //   185: astore_1       
        //   186: aload_2        
        //   187: astore          5
        //   189: aload           6
        //   191: astore_2       
        //   192: goto            152
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  11     20     149    152    Any
        //  20     30     172    185    Any
        //  30     37     185    195    Any
        //  39     48     185    195    Any
        //  54     66     185    195    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 103, Size: 103
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
    
    public void writeLine(final String s, final Object... array) {
        this.write(s, array);
        this.write("\r\n", new Object[0]);
    }
    
    public void writeObject(final String s, final Object o) {
        if (isSupportedParameterType(o)) {
            this.writeString(s, parameterToString(o));
            return;
        }
        if (o instanceof Bitmap) {
            this.writeBitmap(s, (Bitmap)o);
            return;
        }
        if (o instanceof byte[]) {
            this.writeBytes(s, (byte[])o);
            return;
        }
        if (o instanceof ParcelFileDescriptor) {
            this.writeFile(s, (ParcelFileDescriptor)o);
            return;
        }
        throw new IllegalArgumentException("value is not a supported type: String, Bitmap, byte[]");
    }
    
    public void writeRecordBoundary() {
        this.writeLine("--%s", "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
    }
    
    @Override
    public void writeString(final String s, final String s2) {
        this.writeContentDisposition(s, null, null);
        this.writeLine("%s", s2);
        this.writeRecordBoundary();
        if (this.logger != null) {
            this.logger.appendKeyValue("    " + s, s2);
        }
    }
}
