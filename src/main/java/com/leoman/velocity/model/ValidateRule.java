package com.leoman.velocity.model;

public enum ValidateRule {
    Email("邮箱","email"),
    Mobile("手机号码","mobile"),
    Date("日期","date");

    private final String ruleName;
    private final String regex;

    private ValidateRule(String ruleName,String regex) {
        this.ruleName = ruleName;
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    public String getRuleName() {
        return this.ruleName;
    }

    public static ValidateRule string2Enum(String ruleName) {
        if ((ruleName == null) || ("".equals(ruleName))) {
            return null;
        }
        ValidateRule[] rules = values();
        for (ValidateRule validateRule : rules) {
            if (validateRule.getRuleName().equals(ruleName)) {
                return validateRule;
            }
        }
        return null;
    }
}
