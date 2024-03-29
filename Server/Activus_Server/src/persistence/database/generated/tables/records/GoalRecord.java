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

import persistence.database.generated.tables.Goal;


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
public class GoalRecord extends UpdatableRecordImpl<GoalRecord> implements Record4<Integer, Integer, Integer, String> {

    private static final long serialVersionUID = -1054786303;

    /**
     * Setter for <code>public.goal.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.goal.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.goal.profileid</code>.
     */
    public void setProfileid(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.goal.profileid</code>.
     */
    public Integer getProfileid() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.goal.weight</code>.
     */
    public void setWeight(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.goal.weight</code>.
     */
    public Integer getWeight() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>public.goal.goal_text</code>.
     */
    public void setGoalText(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.goal.goal_text</code>.
     */
    public String getGoalText() {
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
    public Row4<Integer, Integer, Integer, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<Integer, Integer, Integer, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Goal.GOAL.ID;
    }

    @Override
    public Field<Integer> field2() {
        return Goal.GOAL.PROFILEID;
    }

    @Override
    public Field<Integer> field3() {
        return Goal.GOAL.WEIGHT;
    }

    @Override
    public Field<String> field4() {
        return Goal.GOAL.GOAL_TEXT;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getProfileid();
    }

    @Override
    public Integer component3() {
        return getWeight();
    }

    @Override
    public String component4() {
        return getGoalText();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getProfileid();
    }

    @Override
    public Integer value3() {
        return getWeight();
    }

    @Override
    public String value4() {
        return getGoalText();
    }

    @Override
    public GoalRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public GoalRecord value2(Integer value) {
        setProfileid(value);
        return this;
    }

    @Override
    public GoalRecord value3(Integer value) {
        setWeight(value);
        return this;
    }

    @Override
    public GoalRecord value4(String value) {
        setGoalText(value);
        return this;
    }

    @Override
    public GoalRecord values(Integer value1, Integer value2, Integer value3, String value4) {
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
     * Create a detached GoalRecord
     */
    public GoalRecord() {
        super(Goal.GOAL);
    }

    /**
     * Create a detached, initialised GoalRecord
     */
    public GoalRecord(Integer id, Integer profileid, Integer weight, String goalText) {
        super(Goal.GOAL);

        set(0, id);
        set(1, profileid);
        set(2, weight);
        set(3, goalText);
    }
}
