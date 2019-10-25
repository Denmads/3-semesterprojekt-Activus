/*
 * This file is generated by jOOQ.
 */
package persistence.database.generated.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;

import persistence.database.generated.tables.TrainingProgramExercise;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.12.1"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TrainingProgramExerciseRecord extends UpdatableRecordImpl<TrainingProgramExerciseRecord> implements Record2<Integer, Integer> {

    private static final long serialVersionUID = -1528835272;

    /**
     * Setter for <code>public.training_program_exercise.exerciseid</code>.
     */
    public void setExerciseid(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.training_program_exercise.exerciseid</code>.
     */
    public Integer getExerciseid() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.training_program_exercise.training_programid</code>.
     */
    public void setTrainingProgramid(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.training_program_exercise.training_programid</code>.
     */
    public Integer getTrainingProgramid() {
        return (Integer) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Integer, Integer> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row2<Integer, Integer> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    @Override
    public Row2<Integer, Integer> valuesRow() {
        return (Row2) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return TrainingProgramExercise.TRAINING_PROGRAM_EXERCISE.EXERCISEID;
    }

    @Override
    public Field<Integer> field2() {
        return TrainingProgramExercise.TRAINING_PROGRAM_EXERCISE.TRAINING_PROGRAMID;
    }

    @Override
    public Integer component1() {
        return getExerciseid();
    }

    @Override
    public Integer component2() {
        return getTrainingProgramid();
    }

    @Override
    public Integer value1() {
        return getExerciseid();
    }

    @Override
    public Integer value2() {
        return getTrainingProgramid();
    }

    @Override
    public TrainingProgramExerciseRecord value1(Integer value) {
        setExerciseid(value);
        return this;
    }

    @Override
    public TrainingProgramExerciseRecord value2(Integer value) {
        setTrainingProgramid(value);
        return this;
    }

    @Override
    public TrainingProgramExerciseRecord values(Integer value1, Integer value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TrainingProgramExerciseRecord
     */
    public TrainingProgramExerciseRecord() {
        super(TrainingProgramExercise.TRAINING_PROGRAM_EXERCISE);
    }

    /**
     * Create a detached, initialised TrainingProgramExerciseRecord
     */
    public TrainingProgramExerciseRecord(Integer exerciseid, Integer trainingProgramid) {
        super(TrainingProgramExercise.TRAINING_PROGRAM_EXERCISE);

        set(0, exerciseid);
        set(1, trainingProgramid);
    }
}