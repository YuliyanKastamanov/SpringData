import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChangeTownNamesCasing_05 {

    private static final String UPDATE_TOWN_NAMES_WITH_UPPER_CASE = "update towns set name = upper(name) where country = ?";
    private static final String GET_ALL_TOWN_NAMES = "select name from towns where country = ?";


    private static final String NO_TOWNS_AFFECTED_FORMAT = "No town names were affected.";
    private static final String AFFECTED_TOWNS_FORMAT = "%d town names were affected.%n";

    public static void main(String[] args) throws SQLException {

    Scanner scanner = new Scanner(System.in);

        final String countryName = scanner.nextLine();

        final Connection connection = Utils.getSQLConnection();

        final PreparedStatement townNamesUpperCaseStatement = connection.prepareStatement(UPDATE_TOWN_NAMES_WITH_UPPER_CASE);

        townNamesUpperCaseStatement.setString(1, countryName);

        final int countUpdatedTowns = townNamesUpperCaseStatement.executeUpdate();

        if (countUpdatedTowns == 0){
            System.out.println(NO_TOWNS_AFFECTED_FORMAT);
            return;
        }

        System.out.printf(AFFECTED_TOWNS_FORMAT, countUpdatedTowns);
        PreparedStatement selectAllTowns = connection.prepareStatement(GET_ALL_TOWN_NAMES);
        selectAllTowns.setString(1, countryName);

        ResultSet allTownsResultSet = selectAllTowns.executeQuery();

        List<String> townNames = new ArrayList<>();

        while (allTownsResultSet.next()){
            townNames.add(allTownsResultSet.getString(Constant.COLUMN_NAME));
        }
        System.out.println(townNames);

        connection.close();

    }

}


