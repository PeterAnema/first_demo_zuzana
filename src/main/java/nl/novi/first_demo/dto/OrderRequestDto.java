package nl.novi.first_demo.dto;

import java.util.Date;
import java.util.List;

public class OrderRequestDto {

    private Date date;
    private long customerId;
    private List<OrderRegelDto> orderRegelDtos;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public List<OrderRegelDto> getOrderRegelDtos() {
        return orderRegelDtos;
    }

    public void setOrderRegelDtos(List<OrderRegelDto> orderRegelDtos) {
        this.orderRegelDtos = orderRegelDtos;
    }
}

