/*
 * This file is generated by jOOQ.
 */
package persistence.database.generated.tables.records;


import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row10;
import org.jooq.impl.UpdatableRecordImpl;

import persistence.database.generated.tables.Profile;


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
public class ProfileRecord extends UpdatableRecordImpl<ProfileRecord> implements Record10<Integer, Integer, String, String, Integer, String, String, String, String, String> {

    private static final long serialVersionUID = -1133770287;

    /**
     * Setter for <code>public.profile.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.profile.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>public.profile.loginid</code>.
     */
    public void setLoginid(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.profile.loginid</code>.
     */
    public Integer getLoginid() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>public.profile.gym</code>.
     */
    public void setGym(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.profile.gym</code>.
     */
    public String getGym() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.profile.city</code>.
     */
    public void setCity(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.profile.city</code>.
     */
    public String getCity() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.profile.age</code>.
     */
    public void setAge(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.profile.age</code>.
     */
    public Integer getAge() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>public.profile.first_name</code>.
     */
    public void setFirstName(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.profile.first_name</code>.
     */
    public String getFirstName() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.profile.last_name</code>.
     */
    public void setLastName(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.profile.last_name</code>.
     */
    public String getLastName() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.profile.gender</code>.
     */
    public void setGender(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.profile.gender</code>.
     */
    public String getGender() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.profile.country</code>.
     */
    public void setCountry(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.profile.country</code>.
     */
    public String getCountry() {
        return (String) get(8);
    }

    /**
     * Setter for <code>public.profile.sport</code>.
     */
    public void setSport(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.profile.sport</code>.
     */
    public String getSport() {
        return (String) get(9);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record10 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row10<Integer, Integer, String, String, Integer, String, String, String, String, String> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    @Override
    public Row10<Integer, Integer, String, String, Integer, String, String, String, String, String> valuesRow() {
        return (Row10) super.valuesRow();
    }

    @Override
    public Field<Integer> field1() {
        return Profile.PROFILE.ID;
    }

    @Override
    public Field<Integer> field2() {
        return Profile.PROFILE.LOGINID;
    }

    @Override
    public Field<String> field3() {
        return Profile.PROFILE.GYM;
    }

    @Override
    public Field<String> field4() {
        return Profile.PROFILE.CITY;
    }

    @Override
    public Field<Integer> field5() {
        return Profile.PROFILE.AGE;
    }

    @Override
    public Field<String> field6() {
        return Profile.PROFILE.FIRST_NAME;
    }

    @Override
    public Field<String> field7() {
        return Profile.PROFILE.LAST_NAME;
    }

    @Override
    public Field<String> field8() {
        return Profile.PROFILE.GENDER;
    }

    @Override
    public Field<String> field9() {
        return Profile.PROFILE.COUNTRY;
    }

    @Override
    public Field<String> field10() {
        return Profile.PROFILE.SPORT;
    }

    @Override
    public Integer component1() {
        return getId();
    }

    @Override
    public Integer component2() {
        return getLoginid();
    }

    @Override
    public String component3() {
        return getGym();
    }

    @Override
    public String component4() {
        return getCity();
    }

    @Override
    public Integer component5() {
        return getAge();
    }

    @Override
    public String component6() {
        return getFirstName();
    }

    @Override
    public String component7() {
        return getLastName();
    }

    @Override
    public String component8() {
        return getGender();
    }

    @Override
    public String component9() {
        return getCountry();
    }

    @Override
    public String component10() {
        return getSport();
    }

    @Override
    public Integer value1() {
        return getId();
    }

    @Override
    public Integer value2() {
        return getLoginid();
    }

    @Override
    public String value3() {
        return getGym();
    }

    @Override
    public String value4() {
        return getCity();
    }

    @Override
    public Integer value5() {
        return getAge();
    }

    @Override
    public String value6() {
        return getFirstName();
    }

    @Override
    public String value7() {
        return getLastName();
    }

    @Override
    public String value8() {
        return getGender();
    }

    @Override
    public String value9() {
        return getCountry();
    }

    @Override
    public String value10() {
        return getSport();
    }

    @Override
    public ProfileRecord value1(Integer value) {
        setId(value);
        return this;
    }

    @Override
    public ProfileRecord value2(Integer value) {
        setLoginid(value);
        return this;
    }

    @Override
    public ProfileRecord value3(String value) {
        setGym(value);
        return this;
    }

    @Override
    public ProfileRecord value4(String value) {
        setCity(value);
        return this;
    }

    @Override
    public ProfileRecord value5(Integer value) {
        setAge(value);
        return this;
    }

    @Override
    public ProfileRecord value6(String value) {
        setFirstName(value);
        return this;
    }

    @Override
    public ProfileRecord value7(String value) {
        setLastName(value);
        return this;
    }

    @Override
    public ProfileRecord value8(String value) {
        setGender(value);
        return this;
    }

    @Override
    public ProfileRecord value9(String value) {
        setCountry(value);
        return this;
    }

    @Override
    public ProfileRecord value10(String value) {
        setSport(value);
        return this;
    }

    @Override
    public ProfileRecord values(Integer value1, Integer value2, String value3, String value4, Integer value5, String value6, String value7, String value8, String value9, String value10) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ProfileRecord
     */
    public ProfileRecord() {
        super(Profile.PROFILE);
    }

    /**
     * Create a detached, initialised ProfileRecord
     */
    public ProfileRecord(Integer id, Integer loginid, String gym, String city, Integer age, String firstName, String lastName, String gender, String country, String sport) {
        super(Profile.PROFILE);

        set(0, id);
        set(1, loginid);
        set(2, gym);
        set(3, city);
        set(4, age);
        set(5, firstName);
        set(6, lastName);
        set(7, gender);
        set(8, country);
        set(9, sport);
    }
}
