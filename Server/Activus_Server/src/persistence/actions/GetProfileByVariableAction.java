package persistence.actions;

import java.sql.SQLException;
import java.util.ArrayList;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Result;
import org.jooq.TableField;
import persistence.IDatabaseAction;
import static persistence.database.generated.Tables.PROFILE;

/**
 * Returns profileID's that have the chosen variable.
 *
 * @author Victor
 */
public class GetProfileByVariableAction extends IDatabaseAction<ArrayList<Integer>> {

    private TableField TF;
    private Object value;

    private ArrayList<Integer> returnList;

    public GetProfileByVariableAction(TableField TF, Object value) {
        this.TF = TF;
        this.value = value;
    }

    //SELECT all where tablfield = T value
    @Override
    protected void execute(DSLContext database) throws SQLException {
        //Fetching the chosen information.
        //ResultType might cause problems?
        Result<Record1<Integer>> result = database.select(PROFILE.ID).from(PROFILE).where(TF.eq(value)).fetch();

        returnList = new ArrayList();

        for (Record r : result) {
            returnList.add(r.getValue(PROFILE.ID));
        }
    }

    @Override
    public ArrayList<Integer> getResult() {
        return returnList;
    }

    @Override
    public boolean hasResult() {
        return returnList != null;
    }

}