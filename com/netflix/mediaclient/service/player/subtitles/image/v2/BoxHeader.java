// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import java.io.IOException;
import java.io.DataInputStream;

public class BoxHeader
{
    public static final int LARGE_SIZE_SIZE = 8;
    public static final int SIZE_SIZE = 4;
    protected static final String TAG = "nf_subtitles_imv2";
    public static final int TYPE_SIZE = 4;
    public static final int USER_TYPE_SIZE = 16;
    public static final String UUID_TYPE = "uuid";
    protected long contentSizeInBytes;
    protected long headerSizeInBytes;
    private long sizeInBytes;
    protected String type;
    protected String userType;
    
    public BoxHeader(final DataInputStream dataInputStream) {
        if (dataInputStream == null) {
            throw new IllegalArgumentException("DIS is null!");
        }
        this.sizeInBytes = ParserUtils.readUint32(ParserUtils.readByteArray(dataInputStream, 4));
        this.type = ParserUtils.decode(ParserUtils.readByteArray(dataInputStream, 4));
        this.headerSizeInBytes = 8L;
        if (this.sizeInBytes == 0L) {
            throw new IOException("mp4-badsize: size can not be 0!");
        }
        if (this.sizeInBytes == 1L) {
            this.sizeInBytes = ParserUtils.readUint64(ParserUtils.readByteArray(dataInputStream, 8));
            this.headerSizeInBytes += 8L;
        }
        if (this.sizeInBytes < 8L) {
            throw new IOException("mp4-badsize: size can not be less than 8 bytes!");
        }
        if (isUUIDType(this.type)) {
            this.userType = ParserUtils.decode(ParserUtils.readByteArray(dataInputStream, 16));
            this.headerSizeInBytes += 16L;
        }
        this.contentSizeInBytes = this.sizeInBytes - this.headerSizeInBytes;
    }
    
    public static boolean isUUIDType(final String s) {
        return "uuid".equals(s);
    }
    
    public long getContentSizeInBytes() {
        return this.contentSizeInBytes;
    }
    
    public long getHeaderSizeInBytes() {
        return this.headerSizeInBytes;
    }
    
    public long getSizeInBytes() {
        return this.sizeInBytes;
    }
    
    public String getType() {
        return this.type;
    }
    
    public String getUserType() {
        return this.userType;
    }
    
    public boolean isExtendedType() {
        return this.userType != null;
    }
    
    public boolean isUserType(final String s) {
        return this.userType != null && this.userType.equals(s);
    }
    
    @Override
    public String toString() {
        return "BoxHeader{sizeInBytes=" + this.sizeInBytes + ", type='" + this.type + '\'' + ", userType='" + this.userType + '\'' + ", headerSizeInBytes=" + this.headerSizeInBytes + ", contentSizeInBytes=" + this.contentSizeInBytes + '}';
    }
}
