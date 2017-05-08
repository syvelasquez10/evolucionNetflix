// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.util.JsonUtils;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import com.netflix.android.org.json.JSONObject;
import java.util.List;
import com.netflix.msl.MslConstants$CompressionAlgorithm;
import java.util.Set;
import com.netflix.android.org.json.JSONString;

public class MessageCapabilities implements JSONString
{
    private static final String KEY_COMPRESSION_ALGOS = "compressionalgos";
    private static final String KEY_LANGUAGES = "languages";
    private final Set<MslConstants$CompressionAlgorithm> compressionAlgos;
    private final List<String> languages;
    
    public MessageCapabilities(final JSONObject p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: iconst_0       
        //     1: istore_3       
        //     2: aload_0        
        //     3: invokespecial   java/lang/Object.<init>:()V
        //     6: ldc             Lcom/netflix/msl/MslConstants$CompressionAlgorithm;.class
        //     8: invokestatic    java/util/EnumSet.noneOf:(Ljava/lang/Class;)Ljava/util/EnumSet;
        //    11: astore          4
        //    13: aload_1        
        //    14: ldc             "compressionalgos"
        //    16: invokevirtual   com/netflix/android/org/json/JSONObject.optJSONArray:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONArray;
        //    19: astore          5
        //    21: iconst_0       
        //    22: istore_2       
        //    23: aload           5
        //    25: ifnull          65
        //    28: iload_2        
        //    29: aload           5
        //    31: invokevirtual   com/netflix/android/org/json/JSONArray.length:()I
        //    34: if_icmpge       65
        //    37: aload           5
        //    39: iload_2        
        //    40: invokevirtual   com/netflix/android/org/json/JSONArray.getString:(I)Ljava/lang/String;
        //    43: astore          6
        //    45: aload           4
        //    47: aload           6
        //    49: invokestatic    com/netflix/msl/MslConstants$CompressionAlgorithm.valueOf:(Ljava/lang/String;)Lcom/netflix/msl/MslConstants$CompressionAlgorithm;
        //    52: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //    57: pop            
        //    58: iload_2        
        //    59: iconst_1       
        //    60: iadd           
        //    61: istore_2       
        //    62: goto            23
        //    65: aload_0        
        //    66: aload           4
        //    68: invokestatic    java/util/Collections.unmodifiableSet:(Ljava/util/Set;)Ljava/util/Set;
        //    71: putfield        com/netflix/msl/msg/MessageCapabilities.compressionAlgos:Ljava/util/Set;
        //    74: new             Ljava/util/ArrayList;
        //    77: dup            
        //    78: invokespecial   java/util/ArrayList.<init>:()V
        //    81: astore          4
        //    83: aload_1        
        //    84: ldc             "languages"
        //    86: invokevirtual   com/netflix/android/org/json/JSONObject.optJSONArray:(Ljava/lang/String;)Lcom/netflix/android/org/json/JSONArray;
        //    89: astore          5
        //    91: iload_3        
        //    92: istore_2       
        //    93: aload           5
        //    95: ifnull          128
        //    98: iload_2        
        //    99: aload           5
        //   101: invokevirtual   com/netflix/android/org/json/JSONArray.length:()I
        //   104: if_icmpge       128
        //   107: aload           4
        //   109: aload           5
        //   111: iload_2        
        //   112: invokevirtual   com/netflix/android/org/json/JSONArray.getString:(I)Ljava/lang/String;
        //   115: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   120: pop            
        //   121: iload_2        
        //   122: iconst_1       
        //   123: iadd           
        //   124: istore_2       
        //   125: goto            93
        //   128: aload_0        
        //   129: aload           4
        //   131: invokestatic    java/util/Collections.unmodifiableList:(Ljava/util/List;)Ljava/util/List;
        //   134: putfield        com/netflix/msl/msg/MessageCapabilities.languages:Ljava/util/List;
        //   137: return         
        //   138: astore          4
        //   140: new             Lcom/netflix/msl/MslEncodingException;
        //   143: dup            
        //   144: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   147: new             Ljava/lang/StringBuilder;
        //   150: dup            
        //   151: invokespecial   java/lang/StringBuilder.<init>:()V
        //   154: ldc             "capabilities "
        //   156: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   159: aload_1        
        //   160: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   163: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   166: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   169: aload           4
        //   171: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   174: athrow         
        //   175: astore          6
        //   177: goto            58
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  6      21     138    175    Lcom/netflix/android/org/json/JSONException;
        //  28     45     138    175    Lcom/netflix/android/org/json/JSONException;
        //  45     58     175    180    Ljava/lang/IllegalArgumentException;
        //  45     58     138    175    Lcom/netflix/android/org/json/JSONException;
        //  65     91     138    175    Lcom/netflix/android/org/json/JSONException;
        //  98     121    138    175    Lcom/netflix/android/org/json/JSONException;
        //  128    137    138    175    Lcom/netflix/android/org/json/JSONException;
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:692)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:529)
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
    
    public MessageCapabilities(Set<MslConstants$CompressionAlgorithm> none, List<String> list) {
        if (none == null) {
            none = EnumSet.noneOf(MslConstants$CompressionAlgorithm.class);
        }
        this.compressionAlgos = (Set<MslConstants$CompressionAlgorithm>)Collections.unmodifiableSet((Set<?>)none);
        if (list == null) {
            list = new ArrayList<String>();
        }
        this.languages = (List<String>)Collections.unmodifiableList((List<?>)list);
    }
    
    public static MessageCapabilities intersection(final MessageCapabilities messageCapabilities, final MessageCapabilities messageCapabilities2) {
        if (messageCapabilities == null || messageCapabilities2 == null) {
            return null;
        }
        final EnumSet<MslConstants$CompressionAlgorithm> none = EnumSet.noneOf(MslConstants$CompressionAlgorithm.class);
        none.addAll((Collection<?>)messageCapabilities.compressionAlgos);
        none.retainAll(messageCapabilities2.compressionAlgos);
        final ArrayList<String> list = new ArrayList<String>(messageCapabilities.languages);
        list.retainAll(messageCapabilities2.languages);
        return new MessageCapabilities(none, list);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof MessageCapabilities)) {
                return false;
            }
            final MessageCapabilities messageCapabilities = (MessageCapabilities)o;
            if (!this.compressionAlgos.equals(messageCapabilities.compressionAlgos) || !this.languages.equals(messageCapabilities.languages)) {
                return false;
            }
        }
        return true;
    }
    
    public Set<MslConstants$CompressionAlgorithm> getCompressionAlgorithms() {
        return this.compressionAlgos;
    }
    
    public List<String> getLanguages() {
        return this.languages;
    }
    
    @Override
    public int hashCode() {
        return this.compressionAlgos.hashCode() ^ this.languages.hashCode();
    }
    
    @Override
    public String toJSONString() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("compressionalgos", JsonUtils.createArray(this.compressionAlgos));
            jsonObject.put("languages", this.languages);
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            throw new MslInternalException("Error encoding " + this.getClass().getName() + " JSON.", ex);
        }
    }
    
    @Override
    public String toString() {
        return this.toJSONString();
    }
}
