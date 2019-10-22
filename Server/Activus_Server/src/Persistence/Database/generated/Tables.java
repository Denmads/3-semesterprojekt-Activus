/*
 * This file is generated by jOOQ.
 */
package Persistence.Database.generated;

import Persistence.Database.generated.tables.Buddys;
import Persistence.Database.generated.tables.Exercise;
import Persistence.Database.generated.tables.Goal;
import Persistence.Database.generated.tables.Login;
import Persistence.Database.generated.tables.Message;
import Persistence.Database.generated.tables.Profile;
import Persistence.Database.generated.tables.QuestionForBuddys;
import Persistence.Database.generated.tables.Scheme;
import Persistence.Database.generated.tables.SchemeProfile;
import Persistence.Database.generated.tables.SchemeTrainingProgram;
import Persistence.Database.generated.tables.Stats;
import Persistence.Database.generated.tables.Token;
import Persistence.Database.generated.tables.TrainingProgram;
import Persistence.Database.generated.tables.TrainingProgramExercise;
import Persistence.Database.generated.tables.TraningsHours;

import javax.annotation.Generated;

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
@SuppressWarnings({"all", "unchecked", "rawtypes"})
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
     * The table <code>public.scheme</code>.
     */
    public static final Scheme SCHEME = Scheme.SCHEME;

    /**
     * The table <code>public.scheme_profile</code>.
     */
    public static final SchemeProfile SCHEME_PROFILE = SchemeProfile.SCHEME_PROFILE;

    /**
     * The table <code>public.scheme_training_program</code>.
     */
    public static final SchemeTrainingProgram SCHEME_TRAINING_PROGRAM = SchemeTrainingProgram.SCHEME_TRAINING_PROGRAM;

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