// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.soloader;

import java.io.DataOutput;
import java.io.DataInput;

public final class UnpackingSoSource$DsoManifest
{
    public final UnpackingSoSource$Dso[] dsos;
    
    public UnpackingSoSource$DsoManifest(final UnpackingSoSource$Dso[] dsos) {
        this.dsos = dsos;
    }
    
    static final UnpackingSoSource$DsoManifest read(final DataInput dataInput) {
        if (dataInput.readByte() != 1) {
            throw new RuntimeException("wrong dso manifest version");
        }
        final int int1 = dataInput.readInt();
        if (int1 < 0) {
            throw new RuntimeException("illegal number of shared libraries");
        }
        final UnpackingSoSource$Dso[] array = new UnpackingSoSource$Dso[int1];
        for (int i = 0; i < int1; ++i) {
            array[i] = new UnpackingSoSource$Dso(dataInput.readUTF(), dataInput.readUTF());
        }
        return new UnpackingSoSource$DsoManifest(array);
    }
    
    public final void write(final DataOutput dataOutput) {
        dataOutput.writeByte(1);
        dataOutput.writeInt(this.dsos.length);
        for (int i = 0; i < this.dsos.length; ++i) {
            dataOutput.writeUTF(this.dsos[i].name);
            dataOutput.writeUTF(this.dsos[i].hash);
        }
    }
}
