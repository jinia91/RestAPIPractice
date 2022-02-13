package com.example.houseutils;

import com.example.houseutils.policy.ActionType;
import com.example.houseutils.policy.BrokeragePolicy;
import com.example.houseutils.policy.BrokeragePolicyFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.example.houseutils.policy.OtherActionTypeFactory.getOtherActionTypesFrom;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * 중개 수수료가 얼마인지 조회하는 컨트롤러
 */
@RestController
public class BrokerageQueryController {

    @GetMapping("/v1/api/calc/brokerage")
    public Long calcBrokerageV1(@RequestParam ActionType actionType,
                                @RequestParam Long price){

        BrokeragePolicy policy = BrokeragePolicyFactory.of(actionType);
        return policy.calculate(price);
    }

    @GetMapping("/v2/api/calc/brokerage")
    public ResponseEntity<EntityModel<ResultDto>> calcBrokerageV2(@RequestParam ActionType actionType,
                                                       @RequestParam Long price){

        BrokeragePolicy policy = BrokeragePolicyFactory.of(actionType);
        ResultDto resultDto = new ResultDto(policy.calculate(price));
        EntityModel<ResultDto> entityModel = EntityModel.of(resultDto);
        List<ActionType> otherActionTypes = getOtherActionTypesFrom(actionType);

        for(ActionType otherActionType :otherActionTypes){
            WebMvcLinkBuilder linkTo =
                    linkTo(methodOn(this.getClass()).calcBrokerageV2(otherActionType,price));
            entityModel.add(linkTo.withRel(String.format("%s-policy-calculate",otherActionType)));
        }
        return  ResponseEntity.ok(entityModel);
    }
}
