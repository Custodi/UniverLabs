package Database;

public class DB_Const
{
    // Константы работника
    public static final String TABLE_STAFF = "staff";

    public static final String STAFF_ID = "ID_Staff";
    public static final String STAFF_NICKNAME = "Nickname";
    public static final String STAFF_NAME = "Name";
    public static final String STAFF_SURNAME = "Surname";
    public static final String STAFF_POSITION = "Position";
    public static final String STAFF_PASSWORD = "Password";
    public static final String STAFF_ACCESS_LEVEL = "Access_lvl";

    // Константы заказа
    public static final String TABLE_ORDER = "main_db.`order`";

    public static final String ORDER_ID = "idOrder";
    public static final String ORDER_ID_STAFF = "ID_Staff";
    public static final String ORDER_ID_CLIENT = "ID_Client";

    // Константы элемента заказа
    public static final String TABLE_ORDER_ELEM = "order_elem";

    public static final String ORDER_ELEM_ID_ORDER = "ID_Order";
    public static final String ORDER_ELEM_ID_PRODUCT = "ID_Product";
    public static final String ORDER_ELEM_COUNT = "Count";

    // Константы продутка
    public static final String TABLE_PRODUCT = "product";

    public static final String PRODUCT_ID = "ID_Product";
    public static final String PRODUCT_NAME = "Name";
    public static final String PRODUCT_PRICE = "Price";

    // Константы клиенты
    public static final String TABLE_CLIENT = "client";

    public static final String CLIENT_ID = "ID_Client";
    public static final String CLIENT_NAME = "Name";
    public static final String CLIENT_SURNAME = "Surname";
    public static final String CLIENT_EMAIL = "Email";
    public static final String CLIENT_PHONE = "Phone";
}
