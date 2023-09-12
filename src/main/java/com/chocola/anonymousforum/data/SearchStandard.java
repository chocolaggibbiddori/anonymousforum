package com.chocola.anonymousforum.data;

import lombok.Getter;

@Getter
public enum SearchStandard {

    DEFAULT(""), TITLE("title"), CREATED_DATE("created_date");

    private final String value;

    SearchStandard(String value) {
        this.value = value;
    }
}
