/*
 * This file is generated by jOOQ.
 */
package persistence.database.generated.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.UpdatableRecordImpl;

import persistence.database.generated.tables.QuestionForBuddys;


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
public class QuestionForBuddysRecord extends UpdatableRecordImpl<QuestionForBuddysRecord> implements Record5<Integer, Boolean, String, String, Integer> {

    private static final long serialVersionUID = 169941012;

    /**
     * Setter for <code>public.question_for_buddys.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.question_for_buddys.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.question_for_buddys.train_with_them</code>.
     */
    public void setTrainWithThem(Boolean value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.question_for_buddys.train_with_them</code>.
     */
    public Boolean getTrainWithThem() {
        return (Boolean) get(1);
    }

    /**
     * Setter for <code>public.question_for_buddys.what_do_i_train</code>. I train some stuff
     */
    public void setWhatDoITrain(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.question_for_buddys.what_do_i_train</code>. I train some stuff
     */
    public String getWhatDoITrain() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.question_for_buddys.experience</code>. Are you new to the gym or exercise. Or do you know what you are doing?
     */
    public void setExperience(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.question_for_buddys.experience</code>. Are you new to the gym or exercise. Or do you know what you are doing?
     */
    public String getExperience() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.question_for_buddys.profileid</code>.
     */
    public void setProfileid(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.question_for_buddys.profileid</code>.
     */
    public Integer getProfileid() {
        return (Integer) get(4);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Boolean, String, String, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<Integer, Boolean, String, String, Integer> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return QuestionForBuddys.QUESTION_FOR_BUDDYS.ID;
    }

    @Override
    public Field<Boolean> field2() {
        return QuestionForBuddys.QUESTION_FOR_BUDDYS.TRAIN_WITH_THEM;
    }

    @Override
    public Field<String> field3() {
        return QuestionForBuddys.QUESTION_FOR_BUDDYS.WHAT_DO_I_TRAIN;
    }

    @Override
    public Field<String> field4() {
        return QuestionForBuddys.QUESTION_FOR_BUDDYS.EXPERIENCE;
    }

    @Override
    public Field<Integer> field5() {
        return QuestionForBuddys.QUESTION_FOR_BUDDYS.PROFILEID;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Boolean component2() {
        return getTrainWithThem();
    }

    @Override
    public String component3() {
        return getWhatDoITrain();
    }

    @Override
    public String component4() {
        return getExperience();
    }

    @Override
    public Integer component5() {
        return getProfileid();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Boolean value2() {
        return getTrainWithThem();
    }

    @Override
    public String value3() {
        return getWhatDoITrain();
    }

    @Override
    public String value4() {
        return getExperience();
    }

    @Override
    public Integer value5() {
        return getProfileid();
    }

    @Override
    public QuestionForBuddysRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public QuestionForBuddysRecord value2(Boolean value) {
        setTrainWithThem(value);
        return this;
    }

    @Override
    public QuestionForBuddysRecord value3(String value) {
        setWhatDoITrain(value);
        return this;
    }

    @Override
    public QuestionForBuddysRecord value4(String value) {
        setExperience(value);
        return this;
    }

    @Override
    public QuestionForBuddysRecord value5(Integer value) {
        setProfileid(value);
        return this;
    }

    @Override
    public QuestionForBuddysRecord values(Integer value1, Boolean value2, String value3, String value4, Integer value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached QuestionForBuddysRecord
     */
    public QuestionForBuddysRecord() {
        super(QuestionForBuddys.QUESTION_FOR_BUDDYS);
    }

    /**
     * Create a detached, initialised QuestionForBuddysRecord
     */
    public QuestionForBuddysRecord(Integer id, Boolean trainWithThem, String whatDoITrain, String experience, Integer profileid) {
        super(QuestionForBuddys.QUESTION_FOR_BUDDYS);

        set(0, id);
        set(1, trainWithThem);
        set(2, whatDoITrain);
        set(3, experience);
        set(4, profileid);
    }
}
