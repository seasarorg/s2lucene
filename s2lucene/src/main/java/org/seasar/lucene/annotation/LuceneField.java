package org.seasar.lucene.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface LuceneField {

	String fieldName() default "";

	boolean store() default true;

	boolean toknize() default true;

	String analyzerType() default "Japanese";

}
