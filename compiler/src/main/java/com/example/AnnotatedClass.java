package com.example;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.ArrayList;
import java.util.List;

import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;

/**
 * Created on 2016/12/13.
 *
 * @author WangYi
 * @since 1.0.0
 */

public class AnnotatedClass {
    public TypeElement mTypeElement;
    public List<BindViewField> mFields;
    public List<OnClickMethod> mMethods;
    public Elements mElements;

    public AnnotatedClass(TypeElement classElement, Elements elements) {
        this.mTypeElement = classElement;
        this.mElements = elements;
        this.mFields = new ArrayList<>();
        this.mMethods = new ArrayList<>();
    }

    public void addField(BindViewField field) {
        mFields.add(field);
    }

    public void addMethod(OnClickMethod method) {
        mMethods.add(method);
    }

    public JavaFile getJavaFile() {
        MethodSpec.Builder methodBuilder = MethodSpec.methodBuilder("bind")
                .addModifiers(Modifier.PUBLIC)
                .addAnnotation(Override.class)
                .addParameter(TypeName.get(mTypeElement.asType()), "activity", Modifier.FINAL);

        for (BindViewField field : mFields) {
            methodBuilder.addStatement("activity.$N = ($T)(activity.findViewById($L))", field.getFieldName(),
                    ClassName.get(field.getFieldType()), field.getResId());
        }

        if (mMethods.size() > 0) {
            methodBuilder.addStatement("$T listener", ClassName.get("android.view", "View", "OnClickListener"));
        }
        for (OnClickMethod method : mMethods) {
            TypeSpec listener = TypeSpec.anonymousClassBuilder("")
                    .addSuperinterface(ClassName.get("android.view", "View", "OnClickListener"))
                    .addMethod(MethodSpec.methodBuilder("onClick")
                            .addAnnotation(Override.class)
                            .addModifiers(Modifier.PUBLIC)
                            .returns(TypeName.VOID)
                            .addParameter(ClassName.get("android.view", "View"), "view")
                            .addStatement("activity.$N()", method.getMethodName())
                            .build())
                    .build();
            methodBuilder.addStatement("listener = $L ", listener);
            for (int id : method.ids) {
                methodBuilder.addStatement("activity.findViewById($L).setOnClickListener(listener)", id);
            }
        }
        TypeSpec finderClass = TypeSpec.classBuilder(mTypeElement.getSimpleName() + "$$Binder")
                .addModifiers(Modifier.PUBLIC)
                .addSuperinterface(ParameterizedTypeName.get(ClassName.get("com.wangyi.viewbinder", "Binder"), TypeName.get(mTypeElement.asType())))
                .addMethod(methodBuilder.build())
                .build();

        String packageName = mElements.getPackageOf(mTypeElement).getQualifiedName().toString();

        return JavaFile.builder(packageName, finderClass).build();
    }
}
