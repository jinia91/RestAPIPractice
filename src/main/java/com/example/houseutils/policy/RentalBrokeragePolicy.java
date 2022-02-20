package com.example.houseutils.policy;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/*
* 임대일때 중개 수수료를 계산해주는 클래스
* */
@Getter
public class RentalBrokeragePolicy implements BrokeragePolicy{

    private List<BrokerageRule> rules;

    public RentalBrokeragePolicy() {
        this.rules = Arrays.asList(
                new BrokerageRule(50_000_000L,0.5,200_000L),
                new BrokerageRule(200_000_000L,0.4,300_000L),
                new BrokerageRule(900_000_000L,0.3),
                new BrokerageRule(1_200_000_000L,0.4),
                new BrokerageRule(1_500_000_000L,0.5),
                new BrokerageRule(Long.MAX_VALUE,0.6)
        );
    }
}
