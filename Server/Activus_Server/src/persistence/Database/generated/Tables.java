/*
 * This file is generated by jOOQ.
 */
package persistence.database.generated;


import javax.annotation.Generated;

import persistence.database.generated.tables.Buddys;
import persistence.database.generated.tables.Exercise;
import persistence.database.generated.tables.Goal;
import persistence.database.generated.tables.Login;
import persistence.database.generated.tables.Message;
import persistence.database.generated.tables.Profile;
import persistence.database.generated.tables.QuestionForBuddys;
import persistence.database.generated.tables.Schedule;
import persistence.database.generated.tables.ScheduleProfile;
import persistence.database.generated.tables.ScheduleTrainingProgram;
import persistence.database.generated.tables.Stats;
import persistence.database.generated.tables.Token;
import persistence.database.generated.tables.TrainingProgram;
import persistence.database.generated.tables.TrainingProgramExercise;
import persistence.database.generated.tables.TraningsHours;


/**
 * Convenience access to all tables in public
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>public.buddys</code>.
     */
    public static final Buddys BUDDYS = Buddys.BUDDYS;

    /**
     * The table <code>public.exercise</code>.
     */
    public static final Exercise EXERCISE = Exercise.EXERCISE;

    /**
     * The table <code>public.goal</code>.
     */
    public static final Goal GOAL = Goal.GOAL;

    /**
     * The table <code>public.login</code>.
     */
    public static final Login LOGIN = Login.LOGIN;

    /**
     * The table <code>public.message</code>.
     */
    public static final Message MESSAGE = Message.MESSAGE;

    /**
     * The table <code>public.profile</code>.
     */
    public static final Profile PROFILE = Profile.PROFILE;

    /**
     * The table <code>public.question_for_buddys</code>.
     */
    public static final QuestionForBuddys QUESTION_FOR_BUDDYS = QuestionForBuddys.QUESTION_FOR_BUDDYS;

    /**
     * The table <code>public.schedule</code>.
     */
    public static final Schedule SCHEDULE = Schedule.SCHEDULE;

    /**
     * The table <code>public.schedule_profile</code>.
     */
    public static final ScheduleProfile SCHEDULE_PROFILE = ScheduleProfile.SCHEDULE_PROFILE;

    /**
     * The table <code>public.schedule_training_program</code>.
     */
    public static final ScheduleTrainingProgram SCHEDULE_TRAINING_PROGRAM = ScheduleTrainingProgram.SCHEDULE_TRAINING_PROGRAM;

    /**
     * The table <code>public.stats</code>.
     */
    public static final Stats STATS = Stats.STATS;

    /**
     * The table <code>public.token</code>.
     */
    public static final Token TOKEN = Token.TOKEN;

    /**
     * The table <code>public.training_program</code>.
     */
    public static final TrainingProgram TRAINING_PROGRAM = TrainingProgram.TRAINING_PROGRAM;

    /**
     * The table <code>public.training_program_exercise</code>.
     */
    public static final TrainingProgramExercise TRAINING_PROGRAM_EXERCISE = TrainingProgramExercise.TRAINING_PROGRAM_EXERCISE;

    /**
     * The table <code>public.tranings_hours</code>.
     */
    public static final TraningsHours TRANINGS_HOURS = TraningsHours.TRANINGS_HOURS;
}