package com.example.houseutils.policy;

import com.example.houseutils.exception.CustomException;
import com.example.houseutils.exception.ErrorCode;

import java.util.List;

public interface BrokeragePolicy {

    List<BrokerageRule> getRules();

    default long calculate(Long price) {
        BrokerageRule brokerageRule = getRules().stream()
                .filter(rule -> price < rule.getLessThan())
                .findFirst().orElseThrow(() -> new CustomException(ErrorCode.INVALID_ERROR));
        return brokerageRule.calculateMaxBrokerage(price);
    }
}
