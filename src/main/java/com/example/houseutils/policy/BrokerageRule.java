package com.example.houseutils.policy;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.lang.Nullable;

/*
    가격이 특정 범위일때 상한 효율과 상한 금액을 가지는 클래스
*/
@AllArgsConstructor
@Getter
public class BrokerageRule {
    private Long lessThan;
    private Double brokeragePercentage;
    @Nullable
    private Long limitAmount;

    public Long calculateMaxBrokerage(Long price){
        if(limitAmount == null){
          return multiplyPercent(price);
        }
        return Math.max(multiplyPercent(price),limitAmount);
    }

    public BrokerageRule(Long lessThan, Double brokeragePercentage) {
        this.lessThan = lessThan;
        this.brokeragePercentage = brokeragePercentage;
        this.limitAmount = null;
    }

    public Long multiplyPercent(Long price){
        return Double.valueOf(Math.floor(brokeragePercentage / 100 * price)).longValue();
    }

}
