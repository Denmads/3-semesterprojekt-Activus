/*
 * This file is generated by jOOQ.
 */
package persistence.database.generated.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record2;
import org.jooq.Record3;
import org.jooq.Row3;
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
public class TrainingProgramExerciseRecord extends UpdatableRecordImpl<TrainingProgramExerciseRecord> implements Record3<Integer, Integer, Integer> {

    private static final long serialVersionUID = -1401979889;

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

    /**
     * Setter for <code>public.training_program_exercise.setid</code>.
     */
    public void setSetid(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.training_program_exercise.setid</code>.
     */
    public Integer getSetid() {
        return (Integer) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record2<Integer, Integer> key() {
        return (Record2) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Integer, Integer, Integer> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Integer, Integer, Integer> valuesRow() {
        return (Row3) super.valuesRow();
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
    public Field<Integer> field3() {
        return TrainingProgramExercise.TRAINING_PROGRAM_EXERCISE.SETID;
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
    public Integer component3() {
        return getSetid();
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
    public Integer value3() {
        return getSetid();
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
    public TrainingProgramExerciseRecord value3(Integer value) {
        setSetid(value);
        return this;
    }

    @Override
    public TrainingProgramExerciseRecord values(Integer value1, Integer value2, Integer value3) {
        value1(value1);
        value2(value2);
        value3(value3);
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
    public TrainingProgramExerciseRecord(Integer exerciseid, Integer trainingProgramid, Integer setid) {
        super(TrainingProgramExercise.TRAINING_PROGRAM_EXERCISE);

        set(0, exerciseid);
        set(1, trainingProgramid);
        set(2, setid);
    }
}
