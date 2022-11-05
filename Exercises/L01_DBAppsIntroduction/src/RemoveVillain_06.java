import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class RemoveVillain_06 {

    private static final String GET_VILLAIN_BY_ID = "select name from villains where id = ?";
    private static final String COUNT_RELEASED_MINIONS = "select count(minion_id) as count from minions_villains where villain_id = ?";
    private static final String DELETE_MINION_ID_IN_MINION_VILLAINS = "delete from minions_villains where villain_id = ?";
    private static final String DELETE_VILLAIN = "delete from villains where id = ?";


    private static final String COLUMN_MINION_COUNT = "count";
    private static final String NO_VILLAIN_FOUND_MESSAGE = "No such villain was found";
    private static final String DELETED_VILLAIN_FORMAT = "%s was deleted%n";
    private static final String COUNT_RELEASED_MINIONS_FORMAT = "%d minions released";

    public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        final int villainId = Integer.parseInt(scanner.nextLine());


        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement getVillainStatement = connection.prepareStatement(GET_VILLAIN_BY_ID);

        getVillainStatement.setInt(1, villainId);

        final ResultSet villainResultSet = getVillainStatement.executeQuery();

        if (!villainResultSet.next()) {
            System.out.println(NO_VILLAIN_FOUND_MESSAGE);
            return;
        }
        final String villainName = villainResultSet.getString(Constant.COLUMN_NAME);
        connection.setAutoCommit(false);

        final PreparedStatement countReleasedMinionsStatement = connection.prepareStatement(COUNT_RELEASED_MINIONS);

        countReleasedMinionsStatement.setInt(1, villainId);
        final ResultSet countReleasedMinionsResultSet = countReleasedMinionsStatement.executeQuery();
        countReleasedMinionsResultSet.next();
        final int countReleasedMinions = countReleasedMinionsResultSet.getInt(COLUMN_MINION_COUNT);

        try {
            final PreparedStatement deleteMinions = connection.prepareStatement(DELETE_MINION_ID_IN_MINION_VILLAINS);
            deleteMinions.setInt(1, villainId);
            deleteMinions.executeUpdate();


            final PreparedStatement deleteVillain = connection.prepareStatement(DELETE_VILLAIN);
            deleteVillain.setInt(1, villainId);
            deleteVillain.executeUpdate();


            connection.commit();
            System.out.printf(DELETED_VILLAIN_FORMAT, villainName);
            System.out.printf(COUNT_RELEASED_MINIONS_FORMAT, countReleasedMinions);


        } catch (SQLException e) {

            connection.rollback();
        }

        connection.close();


    }

}
