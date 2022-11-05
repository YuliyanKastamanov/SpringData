import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PrintAllMinionNames_07 {


    private static final String GET_MINIONS_NAME = "select name from minions order by id";


    public static void main(String[] args) throws SQLException {

        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement getMinionStatement = connection.prepareStatement(GET_MINIONS_NAME);

        final ResultSet minionsResultSet = getMinionStatement.executeQuery();


        final List<String> minionNames = new ArrayList<>();

        for (int i = 0; minionsResultSet.next(); i++) {

            minionNames.add(minionsResultSet.getString(Constant.COLUMN_NAME));

        }
        while (minionNames.size() > 0) {
            System.out.println(minionNames.remove(0));

            if (minionNames.size() > 0){
                System.out.println(minionNames.remove(minionNames.size() - 1));
            }
        }

        connection.close();

    }
}

