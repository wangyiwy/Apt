package com.example;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Name;

/**
 * Created on 2016/12/13.
 *
 * @author WangYi
 * @since 1.0.0
 */

public class OnClickMethod {
    private ExecutableElement mMethodElement;
    private Name mMethodName;
    public int[] ids;

    public OnClickMethod(Element element) throws IllegalArgumentException {
        this.mMethodElement = (ExecutableElement) element;
        this.ids = mMethodElement.getAnnotation(OnClick.class).value();
        this.mMethodName = mMethodElement.getSimpleName();
    }

    public Name getMethodName() {
        return mMethodName;
    }
}
