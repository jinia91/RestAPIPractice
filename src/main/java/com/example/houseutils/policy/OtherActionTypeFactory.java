package com.example.houseutils.policy;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OtherActionTypeFactory {
    static public List<ActionType> getOtherActionTypesFrom(ActionType actionType) {
        return Arrays.stream(ActionType.values()).filter(type ->
                        !type.equals(actionType))
                .collect(Collectors.toList());
    }
}
