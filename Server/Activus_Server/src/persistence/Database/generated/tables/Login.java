/*
 * This file is generated by jOOQ.
 */
package persistence.database.generated.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;

import persistence.database.generated.Indexes;
import persistence.database.generated.Keys;
import persistence.database.generated.Public;
import persistence.database.generated.tables.records.LoginRecord;


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
public class Login extends TableImpl<LoginRecord> {

    private static final long serialVersionUID = -1526735879;

    /**
     * The reference instance of <code>public.login</code>
     */
    public static final Login LOGIN = new Login();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<LoginRecord> getRecordType() {
        return LoginRecord.class;
    }

    /**
     * The column <code>public.login.id</code>.
     */
    public final TableField<LoginRecord, Integer> ID = createField(DSL.name("id"), org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.field("nextval('login_id_seq'::regclass)", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>public.login.username</code>.
     */
    public final TableField<LoginRecord, String> USERNAME = createField(DSL.name("username"), org.jooq.impl.SQLDataType.VARCHAR(255).nullable(false), this, "");

    /**
     * The column <code>public.login.hash_password</code>.
     */
    public final TableField<LoginRecord, byte[]> HASH_PASSWORD = createField(DSL.name("hash_password"), org.jooq.impl.SQLDataType.BLOB.nullable(false), this, "");

    /**
     * The column <code>public.login.password_salt</code>.
     */
    public final TableField<LoginRecord, byte[]> PASSWORD_SALT = createField(DSL.name("password_salt"), org.jooq.impl.SQLDataType.BLOB.nullable(false), this, "");

    /**
     * Create a <code>public.login</code> table reference
     */
    public Login() {
        this(DSL.name("login"), null);
    }

    /**
     * Create an aliased <code>public.login</code> table reference
     */
    public Login(String alias) {
        this(DSL.name(alias), LOGIN);
    }

    /**
     * Create an aliased <code>public.login</code> table reference
     */
    public Login(Name alias) {
        this(alias, LOGIN);
    }

    private Login(Name alias, Table<LoginRecord> aliased) {
        this(alias, aliased, null);
    }

    private Login(Name alias, Table<LoginRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Login(Table<O> child, ForeignKey<O, LoginRecord> key) {
        super(child, key, LOGIN);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.LOGIN_ID, Indexes.LOGIN_PKEY, Indexes.LOGIN_USERNAME);
    }

    @Override
    public Identity<LoginRecord, Integer> getIdentity() {
        return Keys.IDENTITY_LOGIN;
    }

    @Override
    public UniqueKey<LoginRecord> getPrimaryKey() {
        return Keys.LOGIN_PKEY;
    }

    @Override
    public List<UniqueKey<LoginRecord>> getKeys() {
        return Arrays.<UniqueKey<LoginRecord>>asList(Keys.LOGIN_PKEY);
    }

    @Override
    public Login as(String alias) {
        return new Login(DSL.name(alias), this);
    }

    @Override
    public Login as(Name alias) {
        return new Login(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Login rename(String name) {
        return new Login(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Login rename(Name name) {
        return new Login(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<Integer, String, byte[], byte[]> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
