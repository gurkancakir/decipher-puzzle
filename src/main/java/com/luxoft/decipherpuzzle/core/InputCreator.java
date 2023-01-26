package com.luxoft.decipherpuzzle.core;


import java.util.*;

public class InputCreator {

    private List<List<Integer>> result;
    private final Map<Character, Boolean> chars;

    public InputCreator(Map<Character, Boolean> chars) {
        this.chars = chars;
        result = generate(10, chars.size()).parallelStream()
                .flatMap(array -> permute(array).stream())
                .filter(intList -> filterFirstCharIsNotZero(chars, intList))
                .toList();
    }

    private static boolean filterFirstCharIsNotZero(Map<Character, Boolean> chars, List<Integer> intList) {
        int index = 0;
        for (Boolean isFirstChar : chars.values()) {
            if (isFirstChar && intList.get(index) == 0)
                return false;
            index++;
        }
        return true;
    }

    private void uniqueCombination(List<int[]> combinations, int data[], int start, int end, int index) {
        if (index == data.length) {
            int[] combination = data.clone();
            combinations.add(combination);
        } else if (start <= end) {
            data[index] = start;
            uniqueCombination(combinations, data, start + 1, end, index + 1);
            uniqueCombination(combinations, data, start + 1, end, index);
        }
    }

    public List<int[]> generate(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        uniqueCombination(combinations, new int[r], 0, n - 1, 0);
        return combinations;
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> results = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return results;
        }
        List<Integer> result = new ArrayList<>();
        depthFirstSearch(nums, results, result);
        return results;
    }

    public void depthFirstSearch(int[] nums, List<List<Integer>> results, List<Integer> result) {
        if (nums.length == result.size()) {
            List<Integer> temp = new ArrayList<>(result);
            results.add(temp);
        }
        for (int i = 0; i < nums.length; i++) {
            if (!result.contains(nums[i])) {
                result.add(nums[i]);
                depthFirstSearch(nums, results, result);
                result.remove(result.size() - 1);
            }
        }
    }

    public Iterator<Map<Character, Integer>> iterator() {
        return new InputIterator(this.result, this.chars);
    }

    static class InputIterator implements Iterator<Map<Character, Integer>> {

        private List<List<Integer>> result;
        private final Map<Character, Boolean> chars;
        private int index = 0;

        public InputIterator(List<List<Integer>> result, Map<Character, Boolean> chars) {
            this.chars = chars;
            this.result = result;
        }

        @Override
        public boolean hasNext() {
            return index < result.size();
        }

        @Override
        public Map<Character, Integer> next() {
            List<Integer> values = result.get(index++);
            Map<Character, Integer> resultMap = new HashMap<>();
            int i = 0;
            for (Map.Entry<Character, Boolean> item : this.chars.entrySet()) {
                resultMap.put(item.getKey(), values.get(i++));
            }
            return resultMap;
        }
    }
}
