package com.luxoft.decipherpuzzle.core;

import com.luxoft.decipherpuzzle.core.expressions.Expression;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Component
@AllArgsConstructor
public class Decipher {

    private final ExpressionFactory expressionFactory;

    public List<String> decode(String formula) {
        List<String> possibleResult = new ArrayList<>();

        StopWatch time = new StopWatch("Performance");
        time.start("Non-Thread ");
        Expression expression = expressionFactory.create(formula);
        InputCreator inputCreator = new InputCreator(expression.getChars());
        Iterator<Map<Character, Integer>> it = inputCreator.iterator();
        while (it.hasNext()) {
            Map<Character, Integer> value = it.next();
            boolean isSuccess = expression.check(value);
            if (isSuccess) {
                possibleResult.add(expression.show(value));
            }
        }
        time.stop();
        System.out.println("Result Time : " + time.prettyPrint());
        return possibleResult;
    }

    public List<String> decodeThread(String formula) {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<String> possibleResult = new ArrayList<>();
        List<Future<String>> tasks = new ArrayList<>();

        StopWatch time = new StopWatch("Performance");
        time.start("Thread");
        Expression expression = expressionFactory.create(formula);
        InputCreator inputCreator = new InputCreator(expression.getChars());
        Iterator<Map<Character, Integer>> it = inputCreator.iterator();
        while (it.hasNext()) {
            Map<Character, Integer> value = it.next();
            tasks.add(executorService.submit(() -> {
                boolean isSuccess = expression.check(value);
                if (isSuccess) {
                    return expression.show(value);
                }
                return null;
            }));
        }


        for (Future<String> task: tasks){
            try {
                String result = task.get();
                if (null != result)
                    possibleResult.add(task.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        time.stop();
        System.out.println("Result time : " +time.prettyPrint());


        return possibleResult;
    }

}
