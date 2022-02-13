package com.example.houseutils;

import com.example.houseutils.policy.BrokerageRule;
import com.example.houseutils.policy.PurchaseBrokeragePolicy;
import com.example.houseutils.policy.RentalBrokeragePolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class BrokeragePolicyUnitTest {
    PurchaseBrokeragePolicy purchaseBrokeragePolicy;
    RentalBrokeragePolicy rentalBrokeragePolicy;

    @BeforeEach
    public void setup() {
        purchaseBrokeragePolicy = new PurchaseBrokeragePolicy();
        rentalBrokeragePolicy = new RentalBrokeragePolicy();
    }

    @Test
    public void 매매정책_테스트() throws Exception {
        // given
        Long price = 30_000_000L;
        // when
        long result = purchaseBrokeragePolicy.calculate(price);
        // then
        assertThat(result).isEqualTo(250_000L);
    }

    @Test
    public void 보다실무적인_매매정책_테스트() throws Exception {
        assertThat(purchaseBrokeragePolicy.calculate(30_000_000L))
                .isEqualTo(250_000L);
        assertThat(purchaseBrokeragePolicy.calculate(50_000_000L))
                .isEqualTo(800_000L);
        assertThat(purchaseBrokeragePolicy.calculate(100_000_000L))
                .isEqualTo(800_000L);
        assertThat(purchaseBrokeragePolicy.calculate(500_000_000L))
                .isEqualTo(2_000_000L);
        assertThat(purchaseBrokeragePolicy.calculate(1_000_000_000L))
                .isEqualTo(5_000_000L);
        assertThat(purchaseBrokeragePolicy.calculate(1_500_000_000L))
                .isEqualTo(10499999L);
    }
}
