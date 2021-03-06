package org.llyfrgell.legacy.generated;

// Generated Oct 29, 2013 6:49:27 PM by Hibernate Tools 4.0.0

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Temp generated by hbm2java
 */
@Entity
@Table(name = "temp", catalog = "collections2007")
public class Temp implements
        java.io.Serializable
{

    /**
     *
     */
    private static final long serialVersionUID = -5480740281863941723L;
    private TempId id;

    public Temp()
    {
    }

    public Temp(TempId id)
    {
        this.id = id;
    }

    @EmbeddedId
    @AttributeOverrides(
    {
            @AttributeOverride(name = "recordingNumber", column = @Column(name = "RecordingNumber")),
            @AttributeOverride(name = "unitId", column = @Column(name = "UnitID")),
            @AttributeOverride(name = "offset", column = @Column(name = "Offset")),
            @AttributeOverride(name = "programNumber", column = @Column(name = "ProgramNumber")),
            @AttributeOverride(name = "length", column = @Column(name = "Length", precision = 15, scale = 5)),
            @AttributeOverride(name = "cut", column = @Column(name = "Cut")),
            @AttributeOverride(name = "translated", column = @Column(name = "Translated")),
            @AttributeOverride(name = "subtitled", column = @Column(name = "Subtitled", nullable = false)),
            @AttributeOverride(name = "aspect", column = @Column(name = "AspectRatio")),
            @AttributeOverride(name = "ratio", column = @Column(name = "Ratio", precision = 15, scale = 5)),
            @AttributeOverride(name = "formatId", column = @Column(name = "FormatID")),
            @AttributeOverride(name = "speed", column = @Column(name = "Speed")),
            @AttributeOverride(name = "colour", column = @Column(name = "Colour")),
            @AttributeOverride(name = "cc", column = @Column(name = "CC", nullable = false)),
            @AttributeOverride(name = "cx", column = @Column(name = "CX", nullable = false)),
            @AttributeOverride(name = "sound", column = @Column(name = "Sound")),
            @AttributeOverride(name = "digital", column = @Column(name = "Digital", nullable = false)),
            @AttributeOverride(name = "thx", column = @Column(name = "THX", nullable = false)),
            @AttributeOverride(name = "problemsExist", column = @Column(name = "ProblemsExist", nullable = false)),
            @AttributeOverride(name = "problems", column = @Column(name = "Problems")),
            @AttributeOverride(name = "deleteMe", column = @Column(name = "DeleteMe", nullable = false)),
            @AttributeOverride(name = "replaced", column = @Column(name = "Replaced")),
            @AttributeOverride(name = "videoCodec", column = @Column(name = "VideoCodec", length = 10)),
            @AttributeOverride(name = "videoBitRate", column = @Column(name = "VideoBitRate")),
            @AttributeOverride(name = "videoFrameRate", column = @Column(name = "VideoFrameRate", precision = 7)),
            @AttributeOverride(name = "videoWidth", column = @Column(name = "VideoWidth")),
            @AttributeOverride(name = "videoHeight", column = @Column(name = "VideoHeight")),
            @AttributeOverride(name = "audioCodec", column = @Column(name = "AudioCodec", length = 10)),
            @AttributeOverride(name = "audioBitRate", column = @Column(name = "AudioBitRate")),
            @AttributeOverride(name = "audioSampleRate", column = @Column(name = "AudioSampleRate")),
            @AttributeOverride(name = "audioChannels", column = @Column(name = "AudioChannels")),
            @AttributeOverride(name = "audioBitRateType", column = @Column(name = "AudioBitRateType", length = 3)) })
    public TempId getId()
    {
        return this.id;
    }

    public void setId(TempId id)
    {
        this.id = id;
    }

}
