package DB;

import DB.entity.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private static DBManager instance;


    public static synchronized DBManager getInstance() {
        if (instance == null) {
            try {
                instance = new DBManager();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private DBManager() throws SQLException {
    }

    public static final String GET_USER_BY_ROLE="SELECT * FROM users where role=?";
    public static final String UPDATE_ORDER_STATUS="UPDATE orders SET order_status=? where id=?";
    public static final String GET_ALL_ROUTES="SELECT * from routes";
    public static final String GET_USER_BY_LOGIN="SELECT * FROM users WHERE login=?";
    public static final String GET_USER_BY_EMAIL="SELECT * FROM users WHERE email=?";
    public static final String GET_USER_BY_PHONE_NUMBER="SELECT * FROM users WHERE phone_number=?";
    public static final String INSERT_USER="INSERT INTO users VALUES (DEFAULT,?,?,?,?,?,md5(?),'client')";
    public static final String INSERT_ORDER="INSERT INTO orders VALUES (DEFAULT,?,?,?,?,?,?,?,?,?,?)";
    public static final String GET_ALL_TARIFF="SELECT * from tariffs";
    public static final String GET_ORDER_BY_ID="SELECT * from orders where id = ?";
    public static final String GET_ROUTE_BY_ID="SELECT * from routes where id = ?";
    public static final String GET_ALL_ORDERS="SELECT * FROM orders";
    public static final String GET_ALL_USERS="SELECT * FROM users";
    public static final String GET_USERS_ORDERS="SELECT * from orders where USER_ID = ?";


    public List<User> getAllClients() {
        List<User> clientList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
           PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ROLE);
           preparedStatement.setString(1,"client");
           ResultSet resultSet = preparedStatement.executeQuery();
           while (resultSet.next()){
               clientList.add(extractUser(resultSet));
           }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientList;
    }

    public void modifyOrderStatus(Long orderId, String newOrderStatus) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_ORDER_STATUS);
            preparedStatement.setString(1, newOrderStatus);
            preparedStatement.setLong(2, orderId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Route> getRouteList() {

        List<Route> routeList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ROUTES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String start = resultSet.getString("start");
                String destination = resultSet.getString("destination");
                Long id = resultSet.getLong("id");
                int length = resultSet.getInt("length");


                routeList.add(new Route(id, start, destination, length));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return routeList;
    }

    public User findUserByLogin(String login) {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return user;
    }

    public User findUserByEMail(String email) {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return user;
    }

    public User findUserByPhoneNumber(String phoneNumber) {
        User user = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_PHONE_NUMBER);
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = extractUser(resultSet);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return user;
    }

    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("id"));
        user.setEmail(rs.getString("email"));
        user.setPhoneNumber(rs.getString("phone_number"));
        user.setName(rs.getString("name"));
        user.setSurname(rs.getString("surname"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setRole(rs.getString("role"));
        return user;
    }

    private Order extractOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getLong("id"));
        order.setUserId(resultSet.getLong("user_id"));
        order.setCargoName(resultSet.getString("cargo_name"));
        order.setCargoMass(resultSet.getInt("cargo_mass"));
        order.setTariffId(resultSet.getLong("tariff_id"));
        order.setRouteId(resultSet.getLong("route_id"));
        order.setDeliveryAddress(resultSet.getString("delivery_address"));
        order.setPickUpDate(resultSet.getDate("pick_up_date"));
        order.setDateOfArrival(resultSet.getDate("date_of_arrival"));
        order.setOrderPlacementTime(resultSet.getTimestamp("order_placement_time"));
        order.setOrderStatus(resultSet.getString("order_status"));
        return order;
    }

    public User insertUser(User user) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPhoneNumber());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setString(5, user.getLogin());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                user.setId(resultSet.getLong(1));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public Order insertOrder(Order order) {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setLong(1, order.getUserId());
            preparedStatement.setString(2, order.getCargoName());
            preparedStatement.setDouble(3, order.getCargoMass());
            preparedStatement.setLong(4, order.getTariffId());
            preparedStatement.setLong(5, order.getRouteId());
            preparedStatement.setString(6, order.getDeliveryAddress());
            preparedStatement.setTimestamp(7, order.getOrderPlacementTime());
            preparedStatement.setDate(8, order.getPickUpDate());
            preparedStatement.setDate(9, order.getDateOfArrival());
            preparedStatement.setString(10, order.getOrderStatus());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                order.setId(resultSet.getLong(1));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public List<Tariff> getTariffList() {

        List<Tariff> tariffList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_TARIFF);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                double cargoHoldHeight = resultSet.getDouble("cargo_hold_height");
                double cargoHoldLength = resultSet.getDouble("cargo_hold_length");
                double cargoHoldWidth = resultSet.getDouble("cargo_hold_width");
                int cargoMassCap = resultSet.getInt("cargo_mass_cap");
                int pricePerKm = resultSet.getInt("price_per_km");
                String deliveryRange = resultSet.getString("delivery_range");


                tariffList.add(new Tariff(id, name, cargoHoldHeight, cargoHoldLength, cargoHoldWidth, cargoMassCap, deliveryRange, pricePerKm));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return tariffList;
    }

    public Order getOrderById(Long orderId) {
        Order order = new Order();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ORDER_BY_ID);
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = extractOrder(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return order;
    }

    public Route getRouteById(Long routeId) {
        Route route = new Route();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ROUTE_BY_ID);
            preparedStatement.setLong(1, routeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                route.setId(resultSet.getLong("id"));
                route.setStart(resultSet.getString("start"));
                route.setDestination(resultSet.getString("destination"));
                route.setLength(resultSet.getInt("length"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return route;
    }

    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                orderList.add(extractOrder(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderList;
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userList.add(extractUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public List<Order> getUsersOrders(Long userId) {
        List<Order> orderList = new ArrayList<>();

        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USERS_ORDERS);
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                orderList.add(extractOrder(resultSet));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return orderList;
    }
}
