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

import persistence.database.generated.tables.Exercise;


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
public class ExerciseRecord extends UpdatableRecordImpl<ExerciseRecord> implements Record4<Integer, String, String, String> {

    private static final long serialVersionUID = 410243606;

    /**
     * Setter for <code>public.exercise.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.exercise.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.exercise.name</code>.
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.exercise.name</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.exercise.description</code>.
     */
    public void setDescription(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.exercise.description</code>.
     */
    public String getDescription() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.exercise.type</code>.
     */
    public void setType(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.exercise.type</code>.
     */
    public String getType() {
        return (String) get(3);
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
    public Row4<Integer, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, String, String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Exercise.EXERCISE.ID;
    }

    @Override
    public Field<String> field2() {
        return Exercise.EXERCISE.NAME;
    }

    @Override
    public Field<String> field3() {
        return Exercise.EXERCISE.DESCRIPTION;
    }

    @Override
    public Field<String> field4() {
        return Exercise.EXERCISE.TYPE;
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
    public String component4() {
        return getType();
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
    public String value4() {
        return getType();
    }

    @Override
    public ExerciseRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public ExerciseRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public ExerciseRecord value3(String value) {
        setDescription(value);
        return this;
    }

    @Override
    public ExerciseRecord value4(String value) {
        setType(value);
        return this;
    }

    @Override
    public ExerciseRecord values(Integer value1, String value2, String value3, String value4) {
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
     * Create a detached ExerciseRecord
     */
    public ExerciseRecord() {
        super(Exercise.EXERCISE);
    }

    /**
     * Create a detached, initialised ExerciseRecord
     */
    public ExerciseRecord(Integer id, String name, String description, String type) {
        super(Exercise.EXERCISE);

        set(0, id);
        set(1, name);
        set(2, description);
        set(3, type);
    }
}
