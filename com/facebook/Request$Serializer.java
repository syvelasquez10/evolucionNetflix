// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.text.TextUtils;
import android.os.Handler;
import android.util.Pair;
import java.util.ArrayList;
import java.util.HashMap;
import com.facebook.model.GraphObjectList;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.HashSet;
import java.net.URLConnection;
import java.util.Arrays;
import com.facebook.internal.Validate;
import java.util.List;
import java.util.Locale;
import java.net.HttpURLConnection;
import java.net.URL;
import android.net.Uri$Builder;
import android.util.Log;
import com.facebook.internal.Utility;
import com.facebook.internal.ServerProtocol;
import android.os.Bundle;
import com.facebook.model.GraphObject;
import java.util.regex.Pattern;
import org.json.JSONObject;
import java.util.Iterator;
import java.util.Collection;
import org.json.JSONArray;
import android.os.ParcelFileDescriptor;
import android.graphics.Bitmap$CompressFormat;
import android.graphics.Bitmap;
import java.io.OutputStream;
import com.facebook.internal.Logger;

class Request$Serializer implements Request$KeyValueSerializer
{
    private boolean firstWrite;
    private final Logger logger;
    private final OutputStream outputStream;
    
    public Request$Serializer(final OutputStream outputStream, final Logger logger) {
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
        bitmap.compress(Bitmap$CompressFormat.PNG, 100, this.outputStream);
        this.writeLine("", new Object[0]);
        this.writeRecordBoundary();
        if (this.logger != null) {
            this.logger.appendKeyValue("    " + s, "<Image>");
        }
    }
    
    public void writeBytes(final String s, final byte[] array) {
        this.writeContentDisposition(s, s, "content/unknown");
        this.outputStream.write(array);
        this.writeLine("", new Object[0]);
        this.writeRecordBoundary();
        if (this.logger != null) {
            this.logger.appendKeyValue("    " + s, String.format("<Data: %d>", array.length));
        }
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
    
    public void writeFile(final String p0, final ParcelFileDescriptor p1, final String p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          7
        //     3: aload_3        
        //     4: astore          6
        //     6: aload_3        
        //     7: ifnonnull       14
        //    10: ldc             "content/unknown"
        //    12: astore          6
        //    14: aload_0        
        //    15: aload_1        
        //    16: aload_1        
        //    17: aload           6
        //    19: invokevirtual   com/facebook/Request$Serializer.writeContentDisposition:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    22: aload_0        
        //    23: getfield        com/facebook/Request$Serializer.outputStream:Ljava/io/OutputStream;
        //    26: instanceof      Lcom/facebook/ProgressNoopOutputStream;
        //    29: ifeq            114
        //    32: aload_0        
        //    33: getfield        com/facebook/Request$Serializer.outputStream:Ljava/io/OutputStream;
        //    36: checkcast       Lcom/facebook/ProgressNoopOutputStream;
        //    39: aload_2        
        //    40: invokevirtual   android/os/ParcelFileDescriptor.getStatSize:()J
        //    43: invokevirtual   com/facebook/ProgressNoopOutputStream.addProgress:(J)V
        //    46: iconst_0       
        //    47: istore          5
        //    49: aload_0        
        //    50: ldc             ""
        //    52: iconst_0       
        //    53: anewarray       Ljava/lang/Object;
        //    56: invokevirtual   com/facebook/Request$Serializer.writeLine:(Ljava/lang/String;[Ljava/lang/Object;)V
        //    59: aload_0        
        //    60: invokevirtual   com/facebook/Request$Serializer.writeRecordBoundary:()V
        //    63: aload_0        
        //    64: getfield        com/facebook/Request$Serializer.logger:Lcom/facebook/internal/Logger;
        //    67: ifnull          113
        //    70: aload_0        
        //    71: getfield        com/facebook/Request$Serializer.logger:Lcom/facebook/internal/Logger;
        //    74: new             Ljava/lang/StringBuilder;
        //    77: dup            
        //    78: invokespecial   java/lang/StringBuilder.<init>:()V
        //    81: ldc             "    "
        //    83: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    86: aload_1        
        //    87: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    90: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    93: ldc             "<Data: %d>"
        //    95: iconst_1       
        //    96: anewarray       Ljava/lang/Object;
        //    99: dup            
        //   100: iconst_0       
        //   101: iload           5
        //   103: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   106: aastore        
        //   107: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   110: invokevirtual   com/facebook/internal/Logger.appendKeyValue:(Ljava/lang/String;Ljava/lang/Object;)V
        //   113: return         
        //   114: new             Landroid/os/ParcelFileDescriptor$AutoCloseInputStream;
        //   117: dup            
        //   118: aload_2        
        //   119: invokespecial   android/os/ParcelFileDescriptor$AutoCloseInputStream.<init>:(Landroid/os/ParcelFileDescriptor;)V
        //   122: astore_2       
        //   123: new             Ljava/io/BufferedInputStream;
        //   126: dup            
        //   127: aload_2        
        //   128: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //   131: astore_3       
        //   132: sipush          8192
        //   135: newarray        B
        //   137: astore          6
        //   139: iconst_0       
        //   140: istore          4
        //   142: aload_3        
        //   143: aload           6
        //   145: invokevirtual   java/io/BufferedInputStream.read:([B)I
        //   148: istore          5
        //   150: iload           5
        //   152: iconst_m1      
        //   153: if_icmpeq       178
        //   156: aload_0        
        //   157: getfield        com/facebook/Request$Serializer.outputStream:Ljava/io/OutputStream;
        //   160: aload           6
        //   162: iconst_0       
        //   163: iload           5
        //   165: invokevirtual   java/io/OutputStream.write:([BII)V
        //   168: iload           4
        //   170: iload           5
        //   172: iadd           
        //   173: istore          4
        //   175: goto            142
        //   178: aload_3        
        //   179: ifnull          186
        //   182: aload_3        
        //   183: invokevirtual   java/io/BufferedInputStream.close:()V
        //   186: iload           4
        //   188: istore          5
        //   190: aload_2        
        //   191: ifnull          49
        //   194: aload_2        
        //   195: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseInputStream.close:()V
        //   198: iload           4
        //   200: istore          5
        //   202: goto            49
        //   205: astore_1       
        //   206: aconst_null    
        //   207: astore_2       
        //   208: aload           7
        //   210: astore_3       
        //   211: aload_2        
        //   212: ifnull          219
        //   215: aload_2        
        //   216: invokevirtual   java/io/BufferedInputStream.close:()V
        //   219: aload_3        
        //   220: ifnull          227
        //   223: aload_3        
        //   224: invokevirtual   android/os/ParcelFileDescriptor$AutoCloseInputStream.close:()V
        //   227: aload_1        
        //   228: athrow         
        //   229: astore_1       
        //   230: aconst_null    
        //   231: astore          6
        //   233: aload_2        
        //   234: astore_3       
        //   235: aload           6
        //   237: astore_2       
        //   238: goto            211
        //   241: astore_1       
        //   242: aload_2        
        //   243: astore          6
        //   245: aload_3        
        //   246: astore_2       
        //   247: aload           6
        //   249: astore_3       
        //   250: goto            211
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  114    123    205    211    Any
        //  123    132    229    241    Any
        //  132    139    241    253    Any
        //  142    150    241    253    Any
        //  156    168    241    253    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 133, Size: 133
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
    
    public void writeFile(final String s, final Request$ParcelFileDescriptorWithMimeType request$ParcelFileDescriptorWithMimeType) {
        this.writeFile(s, request$ParcelFileDescriptorWithMimeType.getFileDescriptor(), request$ParcelFileDescriptorWithMimeType.getMimeType());
    }
    
    public void writeLine(final String s, final Object... array) {
        this.write(s, array);
        this.write("\r\n", new Object[0]);
    }
    
    public void writeObject(final String s, final Object o, final Request currentRequest) {
        if (this.outputStream instanceof RequestOutputStream) {
            ((RequestOutputStream)this.outputStream).setCurrentRequest(currentRequest);
        }
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
            this.writeFile(s, (ParcelFileDescriptor)o, null);
            return;
        }
        if (o instanceof Request$ParcelFileDescriptorWithMimeType) {
            this.writeFile(s, (Request$ParcelFileDescriptorWithMimeType)o);
            return;
        }
        throw new IllegalArgumentException("value is not a supported type: String, Bitmap, byte[]");
    }
    
    public void writeRecordBoundary() {
        this.writeLine("--%s", "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
    }
    
    public void writeRequestsAsJson(final String s, final JSONArray jsonArray, final Collection<Request> collection) {
        if (!(this.outputStream instanceof RequestOutputStream)) {
            this.writeString(s, jsonArray.toString());
        }
        else {
            final RequestOutputStream requestOutputStream = (RequestOutputStream)this.outputStream;
            this.writeContentDisposition(s, null, null);
            this.write("[", new Object[0]);
            final Iterator<Request> iterator = collection.iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final Request currentRequest = iterator.next();
                final JSONObject jsonObject = jsonArray.getJSONObject(n);
                requestOutputStream.setCurrentRequest(currentRequest);
                if (n > 0) {
                    this.write(",%s", jsonObject.toString());
                }
                else {
                    this.write("%s", jsonObject.toString());
                }
                ++n;
            }
            this.write("]", new Object[0]);
            if (this.logger != null) {
                this.logger.appendKeyValue("    " + s, jsonArray.toString());
            }
        }
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
