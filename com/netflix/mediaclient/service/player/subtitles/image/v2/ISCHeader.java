// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import java.io.DataInputStream;
import java.util.UUID;

public class ISCHeader extends FullBox
{
    public static final String USER_TYPE_HINF = "com.netflix.hinf";
    private UUID assetId;
    private long creationTime;
    private String languageCode;
    private long movieID;
    private long packageID;
    private short rootContainerExtentX;
    private short rootContainerExtentY;
    private String subtitleType;
    
    public ISCHeader(final BoxHeader boxHeader, final DataInputStream dataInputStream) {
        super(boxHeader, dataInputStream);
        if (!boxHeader.isExtendedType()) {
            throw new IllegalStateException("ISCHeader is supposed to be extended type!");
        }
        if (!this.getBoxHeader().isUserType("com.netflix.hinf")) {
            throw new IllegalStateException("ISCHeader does not have expected user type value!");
        }
        this.assetId = ParserUtils.readUUID(dataInputStream);
        this.creationTime = dataInputStream.readLong();
        this.movieID = dataInputStream.readLong();
        this.packageID = dataInputStream.readLong();
        this.rootContainerExtentX = dataInputStream.readShort();
        this.rootContainerExtentY = dataInputStream.readShort();
        this.languageCode = ParserUtils.decode(ParserUtils.readByteArray(dataInputStream, 16));
        this.subtitleType = ParserUtils.decode(ParserUtils.readByteArray(dataInputStream, 16));
    }
    
    public static boolean isThisBox(final BoxHeader boxHeader) {
        if (boxHeader == null) {
            throw new IllegalStateException("Header is null!");
        }
        return "com.netflix.hinf".equals(boxHeader.getUserType());
    }
    
    public UUID getAssetId() {
        return this.assetId;
    }
    
    public long getCreationTime() {
        return this.creationTime;
    }
    
    public String getLanguageCode() {
        return this.languageCode;
    }
    
    public long getMovieID() {
        return this.movieID;
    }
    
    public long getPackageID() {
        return this.packageID;
    }
    
    public short getRootContainerExtentX() {
        return this.rootContainerExtentX;
    }
    
    public short getRootContainerExtentY() {
        return this.rootContainerExtentY;
    }
    
    public String getSubtitleType() {
        return this.subtitleType;
    }
    
    @Override
    public String toString() {
        return "ISCHeader{assetId=" + this.assetId + ", creationTime=" + this.creationTime + ", packageID=" + this.packageID + ", movieID=" + this.movieID + ", rootContainerExtentX=" + this.rootContainerExtentX + ", rootContainerExtentY=" + this.rootContainerExtentY + ", languageCode='" + this.languageCode + '\'' + ", subtitleType='" + this.subtitleType + '\'' + "} " + super.toString();
    }
}
