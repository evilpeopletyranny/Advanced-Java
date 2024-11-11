package annotation1_intro.code.repeatable;

import java.lang.annotation.Repeatable;

@Repeatable(Schedules.class)
public @interface Schedule {
    String day();
}
