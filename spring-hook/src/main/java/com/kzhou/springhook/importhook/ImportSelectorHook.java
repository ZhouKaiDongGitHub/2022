package com.kzhou.springhook.importhook;

import com.kzhou.springhook.test.TestClass4;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Description ImportSelectorHook
 * @Author zhoukaidong
 * @Date 2022/2/9 10:36
 **/
public class ImportSelectorHook implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        String[] importClass = new String[1];
        importClass[0] = TestClass4.class.getName();
        return importClass;
    }
}
