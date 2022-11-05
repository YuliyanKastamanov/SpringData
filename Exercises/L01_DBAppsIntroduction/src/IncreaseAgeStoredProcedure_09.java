import java.sql.*;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure_09 {

    private static final String GET_MINION_NAME_AND_AGE = "select name, age from minions where id = ?";
    private static final String CALL_STORED_PROCEDURE = "CALL usp_get_older(?)";

    private static final String PRINT_FORMAT = "Minion name: %s %nMinion age: %d";


    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        final int minionId = Integer.parseInt(scanner.nextLine());

        final Connection connection = Utils.getSQLConnection();
        final PreparedStatement callProcedureStatement = connection.prepareStatement(CALL_STORED_PROCEDURE);
        callProcedureStatement.setInt(1, minionId);
        callProcedureStatement.execute();


        final PreparedStatement getChangedMinion = connection.prepareStatement(GET_MINION_NAME_AND_AGE);
        getChangedMinion.setInt(1, minionId);
        final ResultSet minionResultSet = getChangedMinion.executeQuery();


        while (minionResultSet.next()) {
            System.out.printf(PRINT_FORMAT, minionResultSet.getString(Constant.COLUMN_NAME),
                    minionResultSet.getInt(Constant.COLUMN_AGE));
        }

        connection.close();


    }

}
