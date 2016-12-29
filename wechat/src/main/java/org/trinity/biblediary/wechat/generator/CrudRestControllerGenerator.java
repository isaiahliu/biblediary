package org.trinity.biblediary.wechat.generator;

import java.io.File;
import java.io.IOException;

import org.trinity.generator.CrudGeneratorUtil;

public final class CrudRestControllerGenerator {
    public static void main(final String[] args) throws IOException {
        CrudGeneratorUtil.generate(new File("../"), "business");
    }
}
