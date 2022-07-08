package com.company.InterfacesAndAbstraction.Exercise.P05Тelephony;

import java.util.List;

public class Smartphone implements Callable, Browsable {
    private List<String> numbers;
    private List<String> urls;

    public Smartphone(List<String> numbers, List<String> urls) {
        this.numbers = numbers;
        this.urls = urls;
    }

    @Override
    public String browse() {
//        return this.urls.stream()
//                .map(u -> u.matches("\\d+") ? String.format("Browsing: %s!", u) : "Invalid URL!")
//                .collect(Collectors.joining(System.lineSeparator()));

        StringBuilder sb = new StringBuilder();
        for (String url : this.urls) {
            if (isCharacter(url)) {
                sb.append(String.format("Browsing: %s!", url)).append(System.lineSeparator());
            } else {
                sb.append("Invalid URL!").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    @Override
    public String call() {
//        return this.numbers.stream()
//                .map(n -> n.matches("\\D*") ? String.format("Calling... %s", n) : "Invalid number!")
//                .collect(Collectors.joining(System.lineSeparator()));

        StringBuilder sb = new StringBuilder();
        for (String n : this.numbers) {
            if (isDigit(n)) {
                sb.append(String.format("Calling... %s", n)).append(System.lineSeparator());
            } else {
                sb.append("Invalid number!").append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    private boolean isDigit(String number) {
        for (int i = 0; i < number.length(); i++) {
            if (!Character.isDigit(number.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private boolean isCharacter(String url) {
        for (int i = 0; i < url.length(); i++) {
            if (Character.isDigit(url.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
