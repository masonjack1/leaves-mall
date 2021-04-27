package ltd.leaves.mall.common;

public enum ServiceResultEnum {
    ERROR("error"),

    SUCCESS("success"),

    DATA_NOT_EXIST("No records found！"),

    SAME_CATEGORY_EXIST("There are classifications of the same class with the same name！"),

    SAME_LOGIN_NAME_EXIST("The user name already exists！"),

    LOGIN_NAME_NULL("Please enter your login name！"),

    LOGIN_PASSWORD_NULL("Please enter your password.！"),

    LOGIN_VERIFY_CODE_NULL("Please enter the verification code！"),

    LOGIN_VERIFY_CODE_ERROR("Verification code error！"),

    GOODS_NOT_EXIST("The goods do not exist！"),

    GOODS_PUT_DOWN("The goods have been removed from the shelves！"),

    SHOPPING_CART_ITEM_LIMIT_NUMBER_ERROR("Exceeds the maximum purchase quantity of a single item！"),

    SHOPPING_CART_ITEM_TOTAL_NUMBER_ERROR("The maximum size of the shopping cart is exceeded！"),

    LOGIN_ERROR("Login failed！"),

    LOGIN_USER_LOCKED("The user has been barred from logging in！"),

    ORDER_NOT_EXIST_ERROR("The order does not exist！"),

    NULL_ADDRESS_ERROR("The address cannot be empty！"),

    ORDER_PRICE_ERROR("Abnormal order price！"),

    ORDER_GENERATE_ERROR("Generate Order Exception！"),

    SHOPPING_ITEM_ERROR("Shopping cart data exception！"),

    SHOPPING_ITEM_COUNT_ERROR("Insufficient inventory！"),

    ORDER_STATUS_ERROR("Order status abnormal！"),

    OPERATE_ERROR("The operation failure！"),

    DB_ERROR("database error");

    private String result;

    ServiceResultEnum(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
