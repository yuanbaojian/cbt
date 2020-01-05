package com.ybj.cbt.Learn.DesignPattern.Factory.AvoidIf;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Author OperatorFactory
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class OperatorFactory {

    static Map<String,Operation> operationMap=new HashMap<>();

    static{
        operationMap.put("add", new Addition());
        operationMap.put("minus", new Minus());
    }

    public static Optional<Operation> getOperation(String operator){
        return Optional.ofNullable(operationMap.get(operator));
    }



}
