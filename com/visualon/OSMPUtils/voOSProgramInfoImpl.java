// 
// Decompiled by Procyon v0.5.30
// 

package com.visualon.OSMPUtils;

import java.io.UnsupportedEncodingException;
import android.os.Parcel;

public class voOSProgramInfoImpl implements voOSProgramInfo
{
    int ProgramID;
    String ProgramName;
    voOSType.VOOSMP_SRC_PROGRAM_TYPE ProgramType;
    int SelInfo;
    int StreamCount;
    voOSStreamInfo[] StreamInfo;
    
    public voOSProgramInfoImpl() {
    }
    
    public voOSProgramInfoImpl(final int programID, final int selInfo, final String programName, final voOSType.VOOSMP_SRC_PROGRAM_TYPE programType, final int streamCount, final voOSStreamInfo[] streamInfo) {
        this.ProgramID = programID;
        this.SelInfo = selInfo;
        this.ProgramName = programName;
        this.ProgramType = programType;
        this.StreamCount = streamCount;
        this.StreamInfo = streamInfo;
    }
    
    @Override
    public int getProgramID() {
        return this.ProgramID;
    }
    
    @Override
    public String getProgramName() {
        return this.ProgramName;
    }
    
    @Override
    public voOSType.VOOSMP_SRC_PROGRAM_TYPE getProgramType() {
        return this.ProgramType;
    }
    
    @Override
    public int getSelInfo() {
        return this.SelInfo;
    }
    
    @Override
    public int getStreamCount() {
        return this.StreamCount;
    }
    
    @Override
    public voOSStreamInfo[] getStreamInfo() {
        return this.StreamInfo;
    }
    
    @Override
    public boolean parse(final Parcel parcel) {
        final boolean b = false;
        this.ProgramID = parcel.readInt();
        this.SelInfo = parcel.readInt();
        final byte[] array = new byte[256];
        parcel.readByteArray(array);
        while (true) {
            try {
                this.ProgramName = new String(array, 0, 256, "utf-8");
                int int1;
                if ((int1 = parcel.readInt()) < 0) {
                    int1 = 0;
                }
                int n;
                if ((n = int1) > 1) {
                    n = 0;
                }
                this.ProgramType = voOSType.VOOSMP_SRC_PROGRAM_TYPE.values()[n];
                this.StreamCount = parcel.readInt();
                int i = b ? 1 : 0;
                if (this.StreamCount > 0) {
                    this.StreamInfo = new voOSStreamInfo[this.StreamCount];
                    i = (b ? 1 : 0);
                }
                while (i < this.StreamCount) {
                    (this.StreamInfo[i] = new voOSStreamInfoImpl()).parse(parcel);
                    ++i;
                }
            }
            catch (UnsupportedEncodingException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
        return true;
    }
}
