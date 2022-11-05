import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionNames_03 {


    private static final String GET_VILLAIN_NAME_BY_ID = "select name from villains as v where id = ?";
    private static final String GET_MINIONS_AND_AGE_BY_VILLAIN_ID =
            "select m.name, m.age from minions as m join minions_villains mv on m.id = mv.minion_id" +
                    " where villain_id = ?;";

    private static final String NO_VILLAIN_FOUND_FORMAT = "No villain with ID %d exists in the database.";
    private static final String VILLAIN_FORMAT = "Villain: %s%n";

    private static final String PRINT_MINIONS_FORMAT = "%d. %s %d%n";

    public static void main(String[] args) throws SQLException {


        final int villainId = new Scanner(System.in).nextInt();

        final Connection connection = Utils.getSQLConnection();
        final PreparedStatement statementForVillains = connection.prepareStatement(GET_VILLAIN_NAME_BY_ID);

        statementForVillains.setInt(1, villainId);

        final ResultSet resultSetVillain = statementForVillains.executeQuery();


        if (!resultSetVillain.next()) {

            System.out.printf(NO_VILLAIN_FOUND_FORMAT, villainId);
            return;
        }

        final String villainName = resultSetVillain.getString(Constant.COLUMN_NAME);

        System.out.printf(VILLAIN_FORMAT, villainName);

        final PreparedStatement statementForMinions = connection.prepareStatement(GET_MINIONS_AND_AGE_BY_VILLAIN_ID);

        statementForMinions.setInt(1, villainId);

        final ResultSet resultSetMinions = statementForMinions.executeQuery();


        for (int i = 0; resultSetMinions.next(); i++) {

            final String minionName = resultSetMinions.getString(Constant.COLUMN_NAME);
            final int minionAge = resultSetMinions.getInt(Constant.COLUMN_AGE);
            System.out.printf(PRINT_MINIONS_FORMAT, i + 1, minionName, minionAge);
        }

        connection.close();

    }
}

