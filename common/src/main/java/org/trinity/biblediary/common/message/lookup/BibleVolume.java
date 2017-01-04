package org.trinity.biblediary.common.message.lookup;

import org.trinity.message.ILookupMessage;

public enum BibleVolume implements ILookupMessage<LookupType> {
    // 创世记
    GENESIS("GEN"),

    // 出埃及记
    EXODUS("EXO"),

    // 利未记
    LEVITIUS("LEV"),

    // 民数记
    NUMBERS("NUM"),

    // 申命记
    DEUTERONOMY("DEU"),

    // 约书亚记
    JOSHUA("JOS"),

    // 士师记
    JUDGES("JUG"),

    // 路得记
    RUTH("RUT"),

    // 撒母耳记上
    SAMUEL1("SA1"),

    // 撒母耳记下
    SAMUEL2("SA2"),

    // 列王纪上
    KINGS1("KI1"),

    // 列王纪下
    KINGS2("KI2"),

    // 历代志上
    CHRONICLES1("CH1"),

    // 历代志下
    CHRONICLES2("CH2"),

    // 以斯拉记
    EZRA("EZR"),

    // 尼希米记
    NEHEMIAH("NEH"),

    // 以斯帖记
    ESTHER("EST"),

    // 约伯记
    JOB("JOB"),

    // 诗篇
    PSALMS("PSM"),

    // 箴言
    PROVERBS("PRO"),

    // 传道书
    ECCLESIASTES("ECC"),

    // 雅歌
    SONG_OF_SONGS("SON"),

    // 以赛亚书
    ISAIAH("ISA"),

    // 耶利米书
    JEREMIAH("JER"),

    // 耶利米哀歌
    LAMENTATIONS("LAM"),

    // 以西结书
    EZEKIEL("EZE"),

    // 但以理书
    DANIEL("DAN"),

    // 何西阿书
    HOSEA("HOS"),

    // 约珥书
    JOEL("JOE"),

    // 阿摩司书
    AMOS("AMO"),

    // 俄巴底亚书
    OBADIAH("OBA"),

    // 约拿书
    JONAH("JON"),

    // 弥迦书
    MICAH("MIC"),

    // 那鸿书
    NAHUM("NAH"),

    // 哈巴谷书
    HABAKKUK("HAB"),

    // 西番雅书
    ZEPHANIAH("ZEP"),

    // 哈该书
    HAGGAI("HAG"),

    // 撒迦利亚书
    ZECHARIAH("ZEC"),

    // 玛拉基书
    MALACHI("MAL"),

    // 马太福音
    MATTHEW("MAT"),

    // 马可福音
    MARK("MAK"),

    // 路加福音
    LUKE("LUK"),

    // 约翰福音
    JOHN("JHN"),

    // 使徒行传
    ACTS("ACT"),

    // 罗马书
    ROMANS("ROM"),

    // 哥林多前书
    CORINTHIANS1("CO1"),

    // 哥林多后书
    CORINTHIANS2("CO2"),

    // 加拉太书
    GALATIANS("GAL"),

    // 以弗所书
    EPHESIANS("EPH"),

    // 腓利比书
    PHILIPPIANS("PHL"),

    // 歌罗西书
    COLOSSIANS("COL"),

    // 帖撒罗尼迦前书
    THESSALINIANS1("TS1"),

    // 帖撒罗尼迦后书
    THESSALINIANS2("TS2"),

    // 提摩太前书
    TIMOTHY1("TI1"),

    // 提摩太后书
    TIMOTHY2("TI2"),

    // 提多书
    TITUS("TIT"),

    // 腓利门书
    PHILEMON("PHM"),

    // 希伯来书
    HEBREWS("HEB"),

    // 雅各书
    JAMES("JAS"),

    // 彼得前书
    PETER1("PE1"),

    // 彼得后书
    PETER2("PE2"),

    // 约翰壹书
    JOHN1("JN1"),

    // 约翰贰书
    JOHN2("JN2"),

    // 约翰参书
    JOHN3("JN3"),

    // 犹大书
    JUDE("JUD"),

    // 启示录
    REVELATION("REV");

    private final String messageCode;

    private BibleVolume(final String messageCode) {
        this.messageCode = messageCode;
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.BIBLE_VOLUME;
    }

}
