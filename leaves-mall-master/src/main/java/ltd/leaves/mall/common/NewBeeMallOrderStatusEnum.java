package ltd.leaves.mall.common;

public enum NewBeeMallOrderStatusEnum {

    DEFAULT(-9, "ERROR"),
    ORDER_PRE_PAY(0, "To be paid"),
    OREDER_PAID(1, "Have paid"),
    OREDER_PACKAGED(2, "Distribution  complete"),
    OREDER_EXPRESS(3, "Outbound success"),
    ORDER_SUCCESS(4, "Deal successful"),
    ORDER_CLOSED_BY_MALLUSER(-1, "Manual override"),
    ORDER_CLOSED_BY_EXPIRED(-2, "Timeout closure"),
    ORDER_CLOSED_BY_JUDGE(-3, "Businesses closed");

    private int orderStatus;

    private String name;

    NewBeeMallOrderStatusEnum(int orderStatus, String name) {
        this.orderStatus = orderStatus;
        this.name = name;
    }

    public static NewBeeMallOrderStatusEnum getNewBeeMallOrderStatusEnumByStatus(int orderStatus) {
        for (NewBeeMallOrderStatusEnum newBeeMallOrderStatusEnum : NewBeeMallOrderStatusEnum.values()) {
            if (newBeeMallOrderStatusEnum.getOrderStatus() == orderStatus) {
                return newBeeMallOrderStatusEnum;
            }
        }
        return DEFAULT;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
