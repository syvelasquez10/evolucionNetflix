// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

import android.os.Parcel;

public class voOSTrackInfoImpl implements voOSTrackInfo
{
    private voOSAudioInfo AudioInfo;
    private int Bitrate;
    private int ChunkCounts;
    private int Codec;
    private long Duration;
    private char[] FourCC;
    private char[] HeadData;
    private int HeadSize;
    private int SelectInfo;
    private int TrackID;
    private voOSType.VOOSMP_SOURCE_STREAMTYPE TrackType;
    private voOSVideoInfo VideoInfo;
    
    public voOSTrackInfoImpl() {
    }
    
    public voOSTrackInfoImpl(final int trackID, final int selectInfo, final char[] fourCC, final voOSType.VOOSMP_SOURCE_STREAMTYPE trackType, final int codec, final long duration, final voOSAudioInfo audioInfo, final voOSVideoInfo videoInfo, final int chunkCounts, final int bitrate, final int headSize, final char[] headData) {
        this.TrackID = trackID;
        this.SelectInfo = selectInfo;
        this.FourCC = fourCC;
        this.TrackType = trackType;
        this.Codec = codec;
        this.Duration = duration;
        this.AudioInfo = audioInfo;
        this.VideoInfo = videoInfo;
        this.ChunkCounts = chunkCounts;
        this.Bitrate = bitrate;
        this.HeadSize = headSize;
        this.HeadData = headData;
    }
    
    @Override
    public voOSAudioInfo getAudioInfo() {
        return this.AudioInfo;
    }
    
    @Override
    public int getBitrate() {
        return this.Bitrate;
    }
    
    @Override
    public int getChunkCounts() {
        return this.ChunkCounts;
    }
    
    @Override
    public int getCodec() {
        return this.Codec;
    }
    
    @Override
    public long getDuration() {
        return this.Duration;
    }
    
    @Override
    public char[] getFourCC() {
        return this.FourCC;
    }
    
    @Override
    public char[] getHeadData() {
        return this.HeadData;
    }
    
    @Override
    public int getHeadSize() {
        return this.HeadSize;
    }
    
    @Override
    public int getSelectInfo() {
        return this.SelectInfo;
    }
    
    @Override
    public int getTrackID() {
        return this.TrackID;
    }
    
    @Override
    public voOSType.VOOSMP_SOURCE_STREAMTYPE getTrackType() {
        return this.TrackType;
    }
    
    @Override
    public voOSVideoInfo getVideoInfo() {
        return this.VideoInfo;
    }
    
    @Override
    public boolean parse(final Parcel p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: bipush          8
        //     2: newarray        B
        //     4: astore_3       
        //     5: aload_0        
        //     6: aload_1        
        //     7: invokevirtual   android/os/Parcel.readInt:()I
        //    10: putfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.TrackID:I
        //    13: aload_0        
        //    14: aload_1        
        //    15: invokevirtual   android/os/Parcel.readInt:()I
        //    18: putfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.SelectInfo:I
        //    21: aload_1        
        //    22: aload_3        
        //    23: invokevirtual   android/os/Parcel.readByteArray:([B)V
        //    26: aload_0        
        //    27: new             Ljava/lang/String;
        //    30: dup            
        //    31: aload_3        
        //    32: iconst_0       
        //    33: bipush          8
        //    35: ldc             "utf-8"
        //    37: invokespecial   java/lang/String.<init>:([BIILjava/lang/String;)V
        //    40: invokevirtual   java/lang/String.toCharArray:()[C
        //    43: putfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.FourCC:[C
        //    46: aload_1        
        //    47: invokevirtual   android/os/Parcel.readInt:()I
        //    50: istore_2       
        //    51: aload_0        
        //    52: invokestatic    com/visualon/OSMPUtils/voOSType$VOOSMP_SOURCE_STREAMTYPE.values:()[Lcom/visualon/OSMPUtils/voOSType$VOOSMP_SOURCE_STREAMTYPE;
        //    55: iload_2        
        //    56: aaload         
        //    57: putfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.TrackType:Lcom/visualon/OSMPUtils/voOSType$VOOSMP_SOURCE_STREAMTYPE;
        //    60: aload_0        
        //    61: aload_1        
        //    62: invokevirtual   android/os/Parcel.readInt:()I
        //    65: putfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.Codec:I
        //    68: aload_0        
        //    69: aload_1        
        //    70: invokevirtual   android/os/Parcel.readInt:()I
        //    73: i2l            
        //    74: putfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.Duration:J
        //    77: aload_0        
        //    78: aload_1        
        //    79: invokevirtual   android/os/Parcel.readInt:()I
        //    82: putfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.ChunkCounts:I
        //    85: aload_0        
        //    86: aload_1        
        //    87: invokevirtual   android/os/Parcel.readInt:()I
        //    90: putfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.Bitrate:I
        //    93: aload_0        
        //    94: getfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.TrackType:Lcom/visualon/OSMPUtils/voOSType$VOOSMP_SOURCE_STREAMTYPE;
        //    97: getstatic       com/visualon/OSMPUtils/voOSType$VOOSMP_SOURCE_STREAMTYPE.VOOSMP_SS_AUDIO:Lcom/visualon/OSMPUtils/voOSType$VOOSMP_SOURCE_STREAMTYPE;
        //   100: invokevirtual   com/visualon/OSMPUtils/voOSType$VOOSMP_SOURCE_STREAMTYPE.compareTo:(Ljava/lang/Enum;)I
        //   103: ifne            128
        //   106: aload_0        
        //   107: new             Lcom/visualon/OSMPUtils/voOSAudioInfoImpl;
        //   110: dup            
        //   111: invokespecial   com/visualon/OSMPUtils/voOSAudioInfoImpl.<init>:()V
        //   114: putfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.AudioInfo:Lcom/visualon/OSMPUtils/voOSAudioInfo;
        //   117: aload_0        
        //   118: getfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.AudioInfo:Lcom/visualon/OSMPUtils/voOSAudioInfo;
        //   121: aload_1        
        //   122: invokeinterface com/visualon/OSMPUtils/voOSAudioInfo.parse:(Landroid/os/Parcel;)Z
        //   127: pop            
        //   128: aload_0        
        //   129: getfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.TrackType:Lcom/visualon/OSMPUtils/voOSType$VOOSMP_SOURCE_STREAMTYPE;
        //   132: getstatic       com/visualon/OSMPUtils/voOSType$VOOSMP_SOURCE_STREAMTYPE.VOOSMP_SS_VIDEO:Lcom/visualon/OSMPUtils/voOSType$VOOSMP_SOURCE_STREAMTYPE;
        //   135: invokevirtual   com/visualon/OSMPUtils/voOSType$VOOSMP_SOURCE_STREAMTYPE.compareTo:(Ljava/lang/Enum;)I
        //   138: ifne            163
        //   141: aload_0        
        //   142: new             Lcom/visualon/OSMPUtils/voOSVideoInfoImpl;
        //   145: dup            
        //   146: invokespecial   com/visualon/OSMPUtils/voOSVideoInfoImpl.<init>:()V
        //   149: putfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.VideoInfo:Lcom/visualon/OSMPUtils/voOSVideoInfo;
        //   152: aload_0        
        //   153: getfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.VideoInfo:Lcom/visualon/OSMPUtils/voOSVideoInfo;
        //   156: aload_1        
        //   157: invokeinterface com/visualon/OSMPUtils/voOSVideoInfo.parse:(Landroid/os/Parcel;)Z
        //   162: pop            
        //   163: aload_0        
        //   164: aload_1        
        //   165: invokevirtual   android/os/Parcel.readInt:()I
        //   168: putfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.HeadSize:I
        //   171: aload_0        
        //   172: getfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.HeadSize:I
        //   175: ifle            212
        //   178: aload_0        
        //   179: getfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.HeadSize:I
        //   182: newarray        B
        //   184: astore_3       
        //   185: aload_1        
        //   186: aload_3        
        //   187: invokevirtual   android/os/Parcel.readByteArray:([B)V
        //   190: aload_0        
        //   191: new             Ljava/lang/String;
        //   194: dup            
        //   195: aload_3        
        //   196: iconst_0       
        //   197: aload_0        
        //   198: getfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.HeadSize:I
        //   201: ldc             "utf-8"
        //   203: invokespecial   java/lang/String.<init>:([BIILjava/lang/String;)V
        //   206: invokevirtual   java/lang/String.toCharArray:()[C
        //   209: putfield        com/visualon/OSMPUtils/voOSTrackInfoImpl.HeadData:[C
        //   212: iconst_1       
        //   213: ireturn        
        //   214: astore          4
        //   216: aload           4
        //   218: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   221: goto            26
        //   224: astore_3       
        //   225: aload_3        
        //   226: invokevirtual   java/io/UnsupportedEncodingException.printStackTrace:()V
        //   229: goto            46
        //   232: astore_1       
        //   233: aload_1        
        //   234: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   237: goto            190
        //   240: astore_1       
        //   241: aload_1        
        //   242: invokevirtual   java/io/UnsupportedEncodingException.printStackTrace:()V
        //   245: goto            212
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                  
        //  -----  -----  -----  -----  --------------------------------------
        //  21     26     214    224    Ljava/lang/Exception;
        //  26     46     224    232    Ljava/io/UnsupportedEncodingException;
        //  185    190    232    240    Ljava/lang/Exception;
        //  190    212    240    248    Ljava/io/UnsupportedEncodingException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 122, Size: 122
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
}
