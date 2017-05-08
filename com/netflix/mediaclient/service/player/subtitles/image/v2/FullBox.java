// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.player.subtitles.image.v2;

import java.io.DataInputStream;
import java.util.BitSet;

public abstract class FullBox extends Box
{
    public static final int FLAG_SIZE = 3;
    protected BitSet flags;
    protected short version;
    
    public FullBox(final BoxHeader boxHeader, final DataInputStream dataInputStream) {
        super(boxHeader);
        this.version = ParserUtils.readUint8(dataInputStream.readByte());
        this.flags = BitSet.valueOf(ParserUtils.readByteArray(dataInputStream, 3));
    }
    
    public FullBox(final BoxHeader boxHeader, final short version, final BitSet flags) {
        super(boxHeader);
        this.version = version;
        if (flags != null) {
            this.flags = flags;
            return;
        }
        (this.flags = new BitSet(24)).clear();
    }
    
    public BitSet getFlags() {
        return this.flags;
    }
    
    public short getVersion() {
        return this.version;
    }
    
    @Override
    public String toString() {
        return "FullBox{version=" + this.version + ", flags=" + this.flags + "} " + super.toString();
    }
}
