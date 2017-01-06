package org.trinity.biblediary.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;

public class PlanProgressDetailDto extends AbstractBusinessDto {
    private int fromChapter;

    private int fromVerse;

    private LookupDto fromVolume;

    private int part;

    private int toChapter;

    private int toVerse;

    private LookupDto toVolume;

    public int getFromChapter() {
        return fromChapter;
    }

    public int getFromVerse() {
        return fromVerse;
    }

    public LookupDto getFromVolume() {
        return fromVolume;
    }

    public int getPart() {
        return part;
    }

    public int getToChapter() {
        return toChapter;
    }

    public int getToVerse() {
        return toVerse;
    }

    public LookupDto getToVolume() {
        return toVolume;
    }

    public void setFromChapter(final int fromChapter) {
        this.fromChapter = fromChapter;
    }

    public void setFromVerse(final int fromVerse) {
        this.fromVerse = fromVerse;
    }

    public void setFromVolume(final LookupDto fromVolume) {
        this.fromVolume = fromVolume;
    }

    public void setPart(final int part) {
        this.part = part;
    }

    public void setToChapter(final int toChapter) {
        this.toChapter = toChapter;
    }

    public void setToVerse(final int toVerse) {
        this.toVerse = toVerse;
    }

    public void setToVolume(final LookupDto toVolume) {
        this.toVolume = toVolume;
    }
}
