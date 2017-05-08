// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import com.netflix.msl.util.Base64;
import java.util.Date;
import com.netflix.msl.entityauth.EntityAuthenticationScheme;
import com.netflix.android.org.json.JSONException;
import com.netflix.msl.MslCryptoException;
import com.netflix.msl.MslEntityAuthException;
import com.netflix.android.org.json.JSONObject;
import com.netflix.msl.MslMessageException;
import com.netflix.msl.MslError;
import com.netflix.msl.MslInternalException;
import com.netflix.msl.util.MslContext;
import com.netflix.msl.MslConstants$ResponseCode;
import com.netflix.msl.entityauth.EntityAuthenticationData;

public class ErrorHeader extends Header
{
    private static final String KEY_ERROR_CODE = "errorcode";
    private static final String KEY_ERROR_MESSAGE = "errormsg";
    private static final String KEY_INTERNAL_CODE = "internalcode";
    private static final String KEY_MESSAGE_ID = "messageid";
    private static final String KEY_RECIPIENT = "recipient";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_USER_MESSAGE = "usermsg";
    private static final long MILLISECONDS_PER_SECOND = 1000L;
    private final EntityAuthenticationData entityAuthData;
    private final MslConstants$ResponseCode errorCode;
    private final String errorMsg;
    private final byte[] errordata;
    private final int internalCode;
    private final long messageId;
    private final String recipient;
    private final byte[] signature;
    private final Long timestamp;
    private final String userMsg;
    
    public ErrorHeader(final MslContext mslContext, final EntityAuthenticationData entityAuthenticationData, final String recipient, final long messageId, final MslConstants$ResponseCode errorCode, final int internalCode, final String errorMsg, final String userMsg) {
        if (messageId < 0L || messageId > 9007199254740992L) {
            throw new MslInternalException("Message ID " + messageId + " is out of range.");
        }
        if (entityAuthenticationData == null) {
            throw new MslMessageException(MslError.MESSAGE_ENTITY_NOT_FOUND);
        }
        final EntityAuthenticationScheme scheme = entityAuthenticationData.getScheme();
        final boolean encrypts = scheme.encrypts();
        this.entityAuthData = entityAuthenticationData;
        if (!encrypts) {
            goto Label_0307;
        }
        this.recipient = recipient;
        this.timestamp = mslContext.getTime() / 1000L;
        this.messageId = messageId;
        this.errorCode = errorCode;
        if (internalCode < 0) {
            goto Label_0312;
        }
        this.internalCode = internalCode;
        this.errorMsg = errorMsg;
        this.userMsg = userMsg;
        final JSONObject jsonObject = new JSONObject();
        try {
            if (this.recipient != null) {
                jsonObject.put("recipient", this.recipient);
            }
            jsonObject.put("timestamp", this.timestamp);
            jsonObject.put("messageid", this.messageId);
            jsonObject.put("errorcode", this.errorCode.intValue());
            if (this.internalCode > 0) {
                jsonObject.put("internalcode", this.internalCode);
            }
            if (this.errorMsg != null) {
                jsonObject.put("errormsg", this.errorMsg);
            }
            if (this.userMsg != null) {
                jsonObject.put("usermsg", this.userMsg);
            }
            try {
                if (mslContext.getEntityAuthenticationFactory(scheme) == null) {
                    throw new MslEntityAuthException(MslError.ENTITYAUTH_FACTORY_NOT_FOUND, scheme.name());
                }
                goto Label_0342;
            }
            catch (MslCryptoException ex) {
                ex.setEntityAuthenticationData(entityAuthenticationData);
                ex.setMessageId(messageId);
                throw ex;
            }
            catch (MslEntityAuthException ex2) {
                ex2.setEntityAuthenticationData(entityAuthenticationData);
                ex2.setMessageId(messageId);
                throw ex2;
            }
        }
        catch (JSONException ex3) {}
    }
    
    protected ErrorHeader(final MslContext p0, final String p1, final EntityAuthenticationData p2, final byte[] p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aconst_null    
        //     1: astore          5
        //     3: aload_0        
        //     4: invokespecial   com/netflix/msl/msg/Header.<init>:()V
        //     7: aload_0        
        //     8: aload_3        
        //     9: putfield        com/netflix/msl/msg/ErrorHeader.entityAuthData:Lcom/netflix/msl/entityauth/EntityAuthenticationData;
        //    12: aload_0        
        //    13: aload           4
        //    15: putfield        com/netflix/msl/msg/ErrorHeader.signature:[B
        //    18: aload_3        
        //    19: ifnonnull       42
        //    22: new             Lcom/netflix/msl/MslMessageException;
        //    25: dup            
        //    26: getstatic       com/netflix/msl/MslError.MESSAGE_ENTITY_NOT_FOUND:Lcom/netflix/msl/MslError;
        //    29: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;)V
        //    32: athrow         
        //    33: astore_1       
        //    34: aload_1        
        //    35: aload_3        
        //    36: invokevirtual   com/netflix/msl/MslCryptoException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslCryptoException;
        //    39: pop            
        //    40: aload_1        
        //    41: athrow         
        //    42: aload_3        
        //    43: invokevirtual   com/netflix/msl/entityauth/EntityAuthenticationData.getScheme:()Lcom/netflix/msl/entityauth/EntityAuthenticationScheme;
        //    46: astore          4
        //    48: aload_1        
        //    49: aload           4
        //    51: invokevirtual   com/netflix/msl/util/MslContext.getEntityAuthenticationFactory:(Lcom/netflix/msl/entityauth/EntityAuthenticationScheme;)Lcom/netflix/msl/entityauth/EntityAuthenticationFactory;
        //    54: astore          6
        //    56: aload           6
        //    58: ifnonnull       86
        //    61: new             Lcom/netflix/msl/MslEntityAuthException;
        //    64: dup            
        //    65: getstatic       com/netflix/msl/MslError.ENTITYAUTH_FACTORY_NOT_FOUND:Lcom/netflix/msl/MslError;
        //    68: aload           4
        //    70: invokevirtual   com/netflix/msl/entityauth/EntityAuthenticationScheme.name:()Ljava/lang/String;
        //    73: invokespecial   com/netflix/msl/MslEntityAuthException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //    76: athrow         
        //    77: astore_1       
        //    78: aload_1        
        //    79: aload_3        
        //    80: invokevirtual   com/netflix/msl/MslEntityAuthException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslEntityAuthException;
        //    83: pop            
        //    84: aload_1        
        //    85: athrow         
        //    86: aload           6
        //    88: aload_1        
        //    89: aload_3        
        //    90: invokevirtual   com/netflix/msl/entityauth/EntityAuthenticationFactory.getCryptoContext:(Lcom/netflix/msl/util/MslContext;Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/crypto/ICryptoContext;
        //    93: astore_1       
        //    94: aload_0        
        //    95: aload_2        
        //    96: invokestatic    com/netflix/msl/util/Base64.decode:(Ljava/lang/String;)[B
        //    99: putfield        com/netflix/msl/msg/ErrorHeader.errordata:[B
        //   102: aload_0        
        //   103: getfield        com/netflix/msl/msg/ErrorHeader.errordata:[B
        //   106: ifnull          117
        //   109: aload_0        
        //   110: getfield        com/netflix/msl/msg/ErrorHeader.errordata:[B
        //   113: arraylength    
        //   114: ifne            151
        //   117: new             Lcom/netflix/msl/MslMessageException;
        //   120: dup            
        //   121: getstatic       com/netflix/msl/MslError.HEADER_DATA_MISSING:Lcom/netflix/msl/MslError;
        //   124: aload_2        
        //   125: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   128: aload_3        
        //   129: invokevirtual   com/netflix/msl/MslMessageException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslMessageException;
        //   132: athrow         
        //   133: astore_1       
        //   134: new             Lcom/netflix/msl/MslMessageException;
        //   137: dup            
        //   138: getstatic       com/netflix/msl/MslError.HEADER_DATA_INVALID:Lcom/netflix/msl/MslError;
        //   141: aload_2        
        //   142: aload_1        
        //   143: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   146: aload_3        
        //   147: invokevirtual   com/netflix/msl/MslMessageException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslMessageException;
        //   150: athrow         
        //   151: aload_1        
        //   152: aload_0        
        //   153: getfield        com/netflix/msl/msg/ErrorHeader.errordata:[B
        //   156: aload_0        
        //   157: getfield        com/netflix/msl/msg/ErrorHeader.signature:[B
        //   160: invokeinterface com/netflix/msl/crypto/ICryptoContext.verify:([B[B)Z
        //   165: ifne            183
        //   168: new             Lcom/netflix/msl/MslCryptoException;
        //   171: dup            
        //   172: getstatic       com/netflix/msl/MslError.MESSAGE_VERIFICATION_FAILED:Lcom/netflix/msl/MslError;
        //   175: invokespecial   com/netflix/msl/MslCryptoException.<init>:(Lcom/netflix/msl/MslError;)V
        //   178: aload_3        
        //   179: invokevirtual   com/netflix/msl/MslCryptoException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslCryptoException;
        //   182: athrow         
        //   183: aload_1        
        //   184: aload_0        
        //   185: getfield        com/netflix/msl/msg/ErrorHeader.errordata:[B
        //   188: invokeinterface com/netflix/msl/crypto/ICryptoContext.decrypt:([B)[B
        //   193: astore_1       
        //   194: new             Ljava/lang/String;
        //   197: dup            
        //   198: aload_1        
        //   199: getstatic       com/netflix/msl/MslConstants.DEFAULT_CHARSET:Ljava/nio/charset/Charset;
        //   202: invokespecial   java/lang/String.<init>:([BLjava/nio/charset/Charset;)V
        //   205: astore_1       
        //   206: new             Lcom/netflix/android/org/json/JSONObject;
        //   209: dup            
        //   210: aload_1        
        //   211: invokespecial   com/netflix/android/org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   214: astore_2       
        //   215: aload_0        
        //   216: aload_2        
        //   217: ldc             "messageid"
        //   219: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   222: putfield        com/netflix/msl/msg/ErrorHeader.messageId:J
        //   225: aload_0        
        //   226: getfield        com/netflix/msl/msg/ErrorHeader.messageId:J
        //   229: lconst_0       
        //   230: lcmp           
        //   231: iflt            245
        //   234: aload_0        
        //   235: getfield        com/netflix/msl/msg/ErrorHeader.messageId:J
        //   238: ldc2_w          9007199254740992
        //   241: lcmp           
        //   242: ifle            317
        //   245: new             Lcom/netflix/msl/MslMessageException;
        //   248: dup            
        //   249: getstatic       com/netflix/msl/MslError.MESSAGE_ID_OUT_OF_RANGE:Lcom/netflix/msl/MslError;
        //   252: new             Ljava/lang/StringBuilder;
        //   255: dup            
        //   256: invokespecial   java/lang/StringBuilder.<init>:()V
        //   259: ldc_w           "errordata "
        //   262: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   265: aload_1        
        //   266: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   269: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   272: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   275: aload_3        
        //   276: invokevirtual   com/netflix/msl/MslMessageException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslMessageException;
        //   279: athrow         
        //   280: astore_2       
        //   281: new             Lcom/netflix/msl/MslEncodingException;
        //   284: dup            
        //   285: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   288: new             Ljava/lang/StringBuilder;
        //   291: dup            
        //   292: invokespecial   java/lang/StringBuilder.<init>:()V
        //   295: ldc_w           "errordata "
        //   298: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   301: aload_1        
        //   302: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   305: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   308: aload_2        
        //   309: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   312: aload_3        
        //   313: invokevirtual   com/netflix/msl/MslEncodingException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslEncodingException;
        //   316: athrow         
        //   317: aload_2        
        //   318: ldc             "recipient"
        //   320: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   323: ifeq            502
        //   326: aload_2        
        //   327: ldc             "recipient"
        //   329: invokevirtual   com/netflix/android/org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   332: astore_1       
        //   333: aload_0        
        //   334: aload_1        
        //   335: putfield        com/netflix/msl/msg/ErrorHeader.recipient:Ljava/lang/String;
        //   338: aload           5
        //   340: astore_1       
        //   341: aload_2        
        //   342: ldc             "timestamp"
        //   344: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   347: ifeq            360
        //   350: aload_2        
        //   351: ldc             "timestamp"
        //   353: invokevirtual   com/netflix/android/org/json/JSONObject.getLong:(Ljava/lang/String;)J
        //   356: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //   359: astore_1       
        //   360: aload_0        
        //   361: aload_1        
        //   362: putfield        com/netflix/msl/msg/ErrorHeader.timestamp:Ljava/lang/Long;
        //   365: getstatic       com/netflix/msl/MslConstants$ResponseCode.FAIL:Lcom/netflix/msl/MslConstants$ResponseCode;
        //   368: astore_1       
        //   369: aload_2        
        //   370: ldc             "errorcode"
        //   372: invokevirtual   com/netflix/android/org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //   375: invokestatic    com/netflix/msl/MslConstants$ResponseCode.valueOf:(I)Lcom/netflix/msl/MslConstants$ResponseCode;
        //   378: astore_1       
        //   379: aload_0        
        //   380: aload_1        
        //   381: putfield        com/netflix/msl/msg/ErrorHeader.errorCode:Lcom/netflix/msl/MslConstants$ResponseCode;
        //   384: aload_2        
        //   385: ldc             "internalcode"
        //   387: invokevirtual   com/netflix/android/org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   390: ifeq            515
        //   393: aload_0        
        //   394: aload_2        
        //   395: ldc             "internalcode"
        //   397: invokevirtual   com/netflix/android/org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //   400: putfield        com/netflix/msl/msg/ErrorHeader.internalCode:I
        //   403: aload_0        
        //   404: getfield        com/netflix/msl/msg/ErrorHeader.internalCode:I
        //   407: ifge            520
        //   410: new             Lcom/netflix/msl/MslMessageException;
        //   413: dup            
        //   414: getstatic       com/netflix/msl/MslError.INTERNAL_CODE_NEGATIVE:Lcom/netflix/msl/MslError;
        //   417: new             Ljava/lang/StringBuilder;
        //   420: dup            
        //   421: invokespecial   java/lang/StringBuilder.<init>:()V
        //   424: ldc_w           "errordata "
        //   427: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   430: aload_2        
        //   431: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   434: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   437: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   440: invokespecial   com/netflix/msl/MslMessageException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;)V
        //   443: aload_3        
        //   444: invokevirtual   com/netflix/msl/MslMessageException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslMessageException;
        //   447: aload_0        
        //   448: getfield        com/netflix/msl/msg/ErrorHeader.messageId:J
        //   451: invokevirtual   com/netflix/msl/MslMessageException.setMessageId:(J)Lcom/netflix/msl/MslMessageException;
        //   454: athrow         
        //   455: astore_1       
        //   456: new             Lcom/netflix/msl/MslEncodingException;
        //   459: dup            
        //   460: getstatic       com/netflix/msl/MslError.JSON_PARSE_ERROR:Lcom/netflix/msl/MslError;
        //   463: new             Ljava/lang/StringBuilder;
        //   466: dup            
        //   467: invokespecial   java/lang/StringBuilder.<init>:()V
        //   470: ldc_w           "errordata "
        //   473: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   476: aload_2        
        //   477: invokevirtual   com/netflix/android/org/json/JSONObject.toString:()Ljava/lang/String;
        //   480: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   483: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   486: aload_1        
        //   487: invokespecial   com/netflix/msl/MslEncodingException.<init>:(Lcom/netflix/msl/MslError;Ljava/lang/String;Ljava/lang/Throwable;)V
        //   490: aload_3        
        //   491: invokevirtual   com/netflix/msl/MslEncodingException.setEntityAuthenticationData:(Lcom/netflix/msl/entityauth/EntityAuthenticationData;)Lcom/netflix/msl/MslEncodingException;
        //   494: aload_0        
        //   495: getfield        com/netflix/msl/msg/ErrorHeader.messageId:J
        //   498: invokevirtual   com/netflix/msl/MslEncodingException.setMessageId:(J)Lcom/netflix/msl/MslEncodingException;
        //   501: athrow         
        //   502: aconst_null    
        //   503: astore_1       
        //   504: goto            333
        //   507: astore_1       
        //   508: getstatic       com/netflix/msl/MslConstants$ResponseCode.FAIL:Lcom/netflix/msl/MslConstants$ResponseCode;
        //   511: astore_1       
        //   512: goto            379
        //   515: aload_0        
        //   516: iconst_m1      
        //   517: putfield        com/netflix/msl/msg/ErrorHeader.internalCode:I
        //   520: aload_0        
        //   521: aload_2        
        //   522: ldc             "errormsg"
        //   524: aconst_null    
        //   525: invokevirtual   com/netflix/android/org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   528: putfield        com/netflix/msl/msg/ErrorHeader.errorMsg:Ljava/lang/String;
        //   531: aload_0        
        //   532: aload_2        
        //   533: ldc             "usermsg"
        //   535: aconst_null    
        //   536: invokevirtual   com/netflix/android/org/json/JSONObject.optString:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   539: putfield        com/netflix/msl/msg/ErrorHeader.userMsg:Ljava/lang/String;
        //   542: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                        
        //  -----  -----  -----  -----  --------------------------------------------
        //  7      18     33     42     Lcom/netflix/msl/MslCryptoException;
        //  7      18     77     86     Lcom/netflix/msl/MslEntityAuthException;
        //  22     33     33     42     Lcom/netflix/msl/MslCryptoException;
        //  22     33     77     86     Lcom/netflix/msl/MslEntityAuthException;
        //  42     56     33     42     Lcom/netflix/msl/MslCryptoException;
        //  42     56     77     86     Lcom/netflix/msl/MslEntityAuthException;
        //  61     77     33     42     Lcom/netflix/msl/MslCryptoException;
        //  61     77     77     86     Lcom/netflix/msl/MslEntityAuthException;
        //  86     94     33     42     Lcom/netflix/msl/MslCryptoException;
        //  86     94     77     86     Lcom/netflix/msl/MslEntityAuthException;
        //  94     102    133    151    Ljava/lang/IllegalArgumentException;
        //  94     102    33     42     Lcom/netflix/msl/MslCryptoException;
        //  94     102    77     86     Lcom/netflix/msl/MslEntityAuthException;
        //  102    117    33     42     Lcom/netflix/msl/MslCryptoException;
        //  102    117    77     86     Lcom/netflix/msl/MslEntityAuthException;
        //  117    133    33     42     Lcom/netflix/msl/MslCryptoException;
        //  117    133    77     86     Lcom/netflix/msl/MslEntityAuthException;
        //  134    151    33     42     Lcom/netflix/msl/MslCryptoException;
        //  134    151    77     86     Lcom/netflix/msl/MslEntityAuthException;
        //  151    183    33     42     Lcom/netflix/msl/MslCryptoException;
        //  151    183    77     86     Lcom/netflix/msl/MslEntityAuthException;
        //  183    194    33     42     Lcom/netflix/msl/MslCryptoException;
        //  183    194    77     86     Lcom/netflix/msl/MslEntityAuthException;
        //  206    245    280    317    Lcom/netflix/android/org/json/JSONException;
        //  245    280    280    317    Lcom/netflix/android/org/json/JSONException;
        //  317    333    455    502    Lcom/netflix/android/org/json/JSONException;
        //  333    338    455    502    Lcom/netflix/android/org/json/JSONException;
        //  341    360    455    502    Lcom/netflix/android/org/json/JSONException;
        //  360    369    455    502    Lcom/netflix/android/org/json/JSONException;
        //  369    379    507    515    Ljava/lang/IllegalArgumentException;
        //  369    379    455    502    Lcom/netflix/android/org/json/JSONException;
        //  379    455    455    502    Lcom/netflix/android/org/json/JSONException;
        //  508    512    455    502    Lcom/netflix/android/org/json/JSONException;
        //  515    520    455    502    Lcom/netflix/android/org/json/JSONException;
        //  520    542    455    502    Lcom/netflix/android/org/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0117:
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
    
    @Override
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof ErrorHeader)) {
                return false;
            }
            final ErrorHeader errorHeader = (ErrorHeader)o;
            if (!this.entityAuthData.equals(errorHeader.entityAuthData) || (this.recipient != errorHeader.recipient && (this.recipient == null || !this.recipient.equals(errorHeader.recipient))) || ((this.timestamp == null || !this.timestamp.equals(errorHeader.timestamp)) && (this.timestamp != null || errorHeader.timestamp != null)) || this.messageId != errorHeader.messageId || this.errorCode != errorHeader.errorCode || this.internalCode != errorHeader.internalCode || (this.errorMsg != errorHeader.errorMsg && (this.errorMsg == null || !this.errorMsg.equals(errorHeader.errorMsg))) || (this.userMsg != errorHeader.userMsg && (this.userMsg == null || !this.userMsg.equals(errorHeader.userMsg)))) {
                return false;
            }
        }
        return true;
    }
    
    public EntityAuthenticationData getEntityAuthenticationData() {
        return this.entityAuthData;
    }
    
    public MslConstants$ResponseCode getErrorCode() {
        return this.errorCode;
    }
    
    public String getErrorMessage() {
        return this.errorMsg;
    }
    
    public int getInternalCode() {
        return this.internalCode;
    }
    
    public long getMessageId() {
        return this.messageId;
    }
    
    public String getRecipient() {
        return this.recipient;
    }
    
    public Date getTimestamp() {
        if (this.timestamp != null) {
            return new Date(this.timestamp * 1000L);
        }
        return null;
    }
    
    public String getUserMessage() {
        return this.userMsg;
    }
    
    @Override
    public int hashCode() {
        int hashCode = 0;
        final int hashCode2 = this.entityAuthData.hashCode();
        int hashCode3;
        if (this.recipient != null) {
            hashCode3 = this.recipient.hashCode();
        }
        else {
            hashCode3 = 0;
        }
        int hashCode4;
        if (this.timestamp != null) {
            hashCode4 = this.timestamp.hashCode();
        }
        else {
            hashCode4 = 0;
        }
        final int hashCode5 = Long.valueOf(this.messageId).hashCode();
        final int hashCode6 = this.errorCode.hashCode();
        final int hashCode7 = Integer.valueOf(this.internalCode).hashCode();
        int hashCode8;
        if (this.errorMsg != null) {
            hashCode8 = this.errorMsg.hashCode();
        }
        else {
            hashCode8 = 0;
        }
        if (this.userMsg != null) {
            hashCode = this.userMsg.hashCode();
        }
        return hashCode8 ^ (hashCode7 ^ (hashCode4 ^ (hashCode2 ^ hashCode3) ^ hashCode5 ^ hashCode6)) ^ hashCode;
    }
    
    @Override
    public String toJSONString() {
        try {
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("entityauthdata", this.entityAuthData);
            jsonObject.put("errordata", Base64.encode(this.errordata));
            jsonObject.put("signature", Base64.encode(this.signature));
            return jsonObject.toString();
        }
        catch (JSONException ex) {
            throw new MslInternalException("Error encoding " + this.getClass().getName() + " JSON.", ex);
        }
    }
}
