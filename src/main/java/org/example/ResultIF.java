package org.example;

interface ResultIF {
    String prompt = null;
    Operation operation = null;
    int result = -999;

    public void printResult();
}
