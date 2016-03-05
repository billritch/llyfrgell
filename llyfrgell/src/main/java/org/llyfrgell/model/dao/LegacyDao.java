package org.llyfrgell.model.dao;

import java.util.List;

import org.llyfrgell.legacy.generated.Artists;
import org.llyfrgell.legacy.generated.AspectRatio;
import org.llyfrgell.legacy.generated.Category;
import org.llyfrgell.legacy.generated.Colour;
import org.llyfrgell.legacy.generated.Cut;
import org.llyfrgell.legacy.generated.Duty;
import org.llyfrgell.legacy.generated.Genre;
import org.llyfrgell.legacy.generated.Label;
import org.llyfrgell.legacy.generated.Language;
import org.llyfrgell.legacy.generated.Media;
import org.llyfrgell.legacy.generated.RecordingFormat;
import org.llyfrgell.legacy.generated.RecordingSpeed;
import org.llyfrgell.legacy.generated.Sound;

public interface LegacyDao {

    List<AspectRatio> listAspect();
    List<Category> listCategory();
    List<Colour> listColor();
    List<Cut> listCut();
    List<Duty> listDuty();
    List<Genre> listGenre();
    List<Label> listLabel();
    List<Language> listLanguage();
    List<Media> listMedia();
    List<RecordingFormat> listRecordingFormat();
    List<RecordingSpeed> listRecordingSpeed();
    List<Sound> listSound();

    List<Artists> listArtists();

}  // interface LegacyDao
