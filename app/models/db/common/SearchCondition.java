package models.db.common;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SearchCondition {
    String value() default "=";
}


//enum SearchEnum { eq, noteq, less, lesseq, greater, greatereq, contains, startwith, endwith }