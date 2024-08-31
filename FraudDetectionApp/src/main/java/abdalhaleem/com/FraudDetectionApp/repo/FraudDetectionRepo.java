package abdalhaleem.com.FraudDetectionApp.repo;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.*;
import java.util.Objects;

@Repository
public class FraudDetectionRepo {
    String url = "jdbc:postgresql://localhost:5432/transactions";
    String password = "abd123";
    String user = "postgres";

    public boolean isFrequencySuspicious(BigInteger cardNumber) {
        String countSql = "SELECT COUNT(*) AS COUNTER FROM TRANSACTION_MODEL WHERE card_number =? AND time_stamp >= NOW() - INTERVAL '10' MINUTE";

        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement st = con.prepareStatement(countSql);
            st.setLong(1, cardNumber.longValue());
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                int counter = resultSet.getInt("COUNTER");

                return counter > 5;
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public boolean isCountrySuspicious(String country, BigInteger cardNumber) {
        String countrySql = "SELECT COUNTRY FROM TRANSACTION_MODEL WHERE card_number = ? ORDER BY time_stamp desc limit 1";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement st = con.prepareStatement(countrySql);
            st.setLong(1, cardNumber.longValue());
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return !Objects.equals(resultSet.getString("COUNTRY"), country);
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isLocationSuspicious(String location, BigInteger cardNumber) {
        String locationSql = "SELECT LOCATION FROM TRANSACTION_MODEL WHERE card_number = ? ORDER BY time_stamp desc limit 1";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement st = con.prepareStatement(locationSql);
            st.setLong(1, cardNumber.longValue());
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return !Objects.equals(resultSet.getString("LOCATION"), location);
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isUserNameSuspicious(String userName, BigInteger cardNumber) {
        String nameSql = "SELECT USER_NAME FROM TRANSACTION_MODEL WHERE card_number = ? ORDER BY time_stamp desc limit 1";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement st = con.prepareStatement(nameSql);
            st.setLong(1, cardNumber.longValue());
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return !Objects.equals(resultSet.getString("USER_NAME"), userName);
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isCurrencySuspicious(String currency, BigInteger cardNumber) {
        String currencySql = "SELECT CURRENCY FROM TRANSACTION_MODEL WHERE card_number = ? ORDER BY time_stamp desc limit 1";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement st = con.prepareStatement(currencySql);
            st.setLong(1, cardNumber.longValue());
            ResultSet resultSet = st.executeQuery();

            if (resultSet.next()) {
                return !Objects.equals(resultSet.getString("CURRENCY"), currency);
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isIpAddressSuspicious(String ipAddress, BigInteger cardNumber) {
        String ipAddressSql = "SELECT IP_ADDRESS FROM TRANSACTION_MODEL WHERE card_number = ? ORDER BY time_stamp desc limit 1";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement st = con.prepareStatement(ipAddressSql);
            st.setLong(1, cardNumber.longValue());
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return !Objects.equals(resultSet.getString("IP_ADDRESS"), ipAddress);
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isDeviceTypeSuspicious(String deviceType, BigInteger cardNumber) {
        String deviceTypeSql = "SELECT DEVICE_TYPE FROM TRANSACTION_MODEL WHERE card_number = ? ORDER BY time_stamp desc limit 1";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement st = con.prepareStatement(deviceTypeSql);
            st.setLong(1, cardNumber.longValue());
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return !Objects.equals(resultSet.getString("DEVICE_TYPE"), deviceType);
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isBrowserSuspicious(String browser, BigInteger cardNumber) {
        String browserSql = "SELECT BROWSER FROM TRANSACTION_MODEL WHERE card_number = ? ORDER BY time_stamp desc limit 1";
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, password);
            PreparedStatement st = con.prepareStatement(browserSql);
            st.setLong(1, cardNumber.longValue());
            ResultSet resultSet = st.executeQuery();
            if (resultSet.next()) {
                return !Objects.equals(resultSet.getString("BROWSER"), browser);
            } else {
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
