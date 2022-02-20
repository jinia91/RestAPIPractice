package com.example.houseutils.policy;

public class BrokeragePolicyFactory {

    private static final RentalBrokeragePolicy rentalBrokeragePolicy = new RentalBrokeragePolicy();
    private static final PurchaseBrokeragePolicy purchaseBrokeragePolicy = new PurchaseBrokeragePolicy();

    static public BrokeragePolicy of(ActionType actionType){
        switch (actionType){
            case PURCHASE:
                return purchaseBrokeragePolicy;
            case RENT:
                return rentalBrokeragePolicy;
            default:
                throw new IllegalArgumentException("No Valid Policy");
        }
    }
}
