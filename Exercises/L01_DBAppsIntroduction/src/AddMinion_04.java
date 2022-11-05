import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class AddMinion_04 {

    private static final String GET_TOWN_BY_NAME = "select id from towns where name = ?";
    private static final String INSERT_TOWN = "insert into towns (name) values (?)";

    private static final String INSERT_VILLAINS = "insert into villains (name, evilness_factor) values (?, ?)";
    private static final String GET_VILLAIN_BY_NAME = "select id from villains where name = ?";
    private static final String EVILNESS_FACTOR = "evil";


    private static final String INSERT_MINION = "insert into minions (name, age, town_id) values (?, ?, ?)";
    private static final String GET_LAST_ADDED_MINION = "select id from minions order by id desc limit 1";

    private static final String INSERT_INTO_MINIONS_VILLAINS =
            "insert into minions_villains (minion_id, villain_id) values (?, ?)";



    private static final String TOWN_ADDED_FORMAT = "Town %s was added to the database.%n";
    private static final String VILLAIN_ADDED_FORMAT = "Villain %s was added to the database.%n";
    private static final String FINAL_RESULT_FORMAT = "Successfully added %s to be minion of %s.%n";

    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);

        final String minionInput = scanner.nextLine();
        final String villainInput = scanner.nextLine();

        final String minionName = minionInput.split("\\s+")[1];
        final int minionAge = Integer.parseInt(minionInput.split("\\s+")[2]);
        final String minionTownName = minionInput.split("\\s+")[3];

        final String villainName = villainInput.split("\\s+")[1];

        final Connection connection = Utils.getSQLConnection();

        final int townId = getId(connection,
                List.of(minionTownName),
                GET_TOWN_BY_NAME,
                INSERT_TOWN,
                TOWN_ADDED_FORMAT );

        final int villainId = getId(connection,
                List.of(villainName, EVILNESS_FACTOR),
                GET_VILLAIN_BY_NAME,
                INSERT_VILLAINS,
                VILLAIN_ADDED_FORMAT );

        final PreparedStatement minionInsertStatement = connection.prepareStatement(INSERT_MINION);
        minionInsertStatement.setString(1, minionName);
        minionInsertStatement.setInt(2, minionAge);
        minionInsertStatement.setInt(3, townId);

        minionInsertStatement.executeUpdate();

        final PreparedStatement lastAddedMinionStatement = connection.prepareStatement(GET_LAST_ADDED_MINION);
        final ResultSet lastAddedMinionResultSet = lastAddedMinionStatement.executeQuery();
        lastAddedMinionResultSet.next();

        final int lastAddedMinionId = lastAddedMinionResultSet.getInt(Constant.COLUMN_LABEL_ID);

        final PreparedStatement insertIntoMinionsVillainsStatement = connection.prepareStatement(INSERT_INTO_MINIONS_VILLAINS);

        insertIntoMinionsVillainsStatement.setInt(1, lastAddedMinionId);
        insertIntoMinionsVillainsStatement.setInt(2, villainId);
        insertIntoMinionsVillainsStatement.executeUpdate();

        System.out.printf(FINAL_RESULT_FORMAT, minionName, villainName);

        connection.close();

    }

    private static int getId (Connection connection,
                              List<String> arguments,
                              String selectQuery,
                              String insertQuery,
                              String printFormat) throws SQLException {

        final String name = arguments.get(0);

        final PreparedStatement selectStatement = connection.prepareStatement(selectQuery);

        selectStatement.setString(1, name);

        final ResultSet resultSet = selectStatement.executeQuery();

        if (!resultSet.next()) {
            final PreparedStatement insertStatement = connection.prepareStatement(insertQuery);

            for (int i = 1; i <= arguments.size() ; i++) {
                insertStatement.setString(i, arguments.get(i - 1));
            }

            insertStatement.executeUpdate();

            final ResultSet newResultSet = selectStatement.executeQuery();
            newResultSet.next();

            System.out.printf(printFormat, name);

            return newResultSet.getInt(Constant.COLUMN_LABEL_ID);

        }


        return resultSet.getInt(Constant.COLUMN_LABEL_ID);

    }

}

