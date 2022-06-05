package com.kzhou.mq.sequence;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/6/5 0:09
 **/
public class OrderInfoDto {
    private Long orderId;
    private String step;
    private String date;

    public OrderInfoDto(Long orderId, String step, String date) {
        this.orderId = orderId;
        this.step = step;
        this.date = date;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OrderInfoDto{" +
                "orderId=" + orderId +
                ", step='" + step + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
