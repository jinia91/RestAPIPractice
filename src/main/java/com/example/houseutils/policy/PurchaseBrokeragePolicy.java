package com.example.houseutils.policy;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

/*
 * 매매일때 중개 수수료를 계산해주는 클래스
 * */
@Getter
public class PurchaseBrokeragePolicy implements BrokeragePolicy {

    private List<BrokerageRule> rules;

    public PurchaseBrokeragePolicy() {
        this.rules = Arrays.asList(
                new BrokerageRule(50_000_000L,0.6,250_000L),
                new BrokerageRule(200_000_000L,0.5,800_000L),
                new BrokerageRule(900_000_000L,0.4),
                new BrokerageRule(1_200_000_000L,0.5),
                new BrokerageRule(1_500_000_000L,0.6),
                new BrokerageRule(Long.MAX_VALUE,0.7)
        );
    }
}
