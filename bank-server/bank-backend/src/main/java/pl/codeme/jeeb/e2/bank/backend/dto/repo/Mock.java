package pl.codeme.jeeb.e2.bank.backend.dto.repo;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.FIELD;

import javax.inject.Qualifier;

@Target({TYPE, FIELD})
@Retention(RUNTIME)
@Qualifier
public @interface Mock {
}
