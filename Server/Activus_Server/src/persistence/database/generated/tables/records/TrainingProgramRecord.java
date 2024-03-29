/*
 * This file is generated by jOOQ.
 */
package persistence.database.generated.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;

import persistence.database.generated.tables.TrainingProgram;


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
public class TrainingProgramRecord extends UpdatableRecordImpl<TrainingProgramRecord> implements Record4<Integer, String, String, Integer> {

    private static final long serialVersionUID = 2021340925;

    /**
     * Setter for <code>public.training_program.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.training_program.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.training_program.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.training_program.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.training_program.description</code>.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.training_program.description</code>.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.training_program.profile_id</code>.
     */
    public void setProfileId(Integer value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.training_program.profile_id</code>.
     */
    public Integer getProfileId() {
        return (Integer) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, String, Integer> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, String, String, Integer> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return TrainingProgram.TRAINING_PROGRAM.ID;
    }

    @Override
    public Field<String> field2() {
        return TrainingProgram.TRAINING_PROGRAM.NAME;
    }

    @Override
    public Field<String> field3() {
        return TrainingProgram.TRAINING_PROGRAM.DESCRIPTION;
    }

    @Override
    public Field<Integer> field4() {
        return TrainingProgram.TRAINING_PROGRAM.PROFILE_ID;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getDescription();
    }

    @Override
    public Integer component4() {
        return getProfileId();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getDescription();
    }

    @Override
    public Integer value4() {
        return getProfileId();
    }

    @Override
    public TrainingProgramRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public TrainingProgramRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public TrainingProgramRecord value3(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public TrainingProgramRecord value4(Integer value) {
        setProfileId(value);
        return this;
    }

    @Override
    public TrainingProgramRecord values(Integer value1, String value2, String value3, Integer value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached TrainingProgramRecord
     */
    public TrainingProgramRecord() {
        super(TrainingProgram.TRAINING_PROGRAM);
    }

    /**
     * Create a detached, initialised TrainingProgramRecord
     */
    public TrainingProgramRecord(Integer id, String name, String description, Integer profileId) {
        super(TrainingProgram.TRAINING_PROGRAM);

        set(0, id);
        set(1, name);
        set(2, description);
        set(3, profileId);
    }
}
