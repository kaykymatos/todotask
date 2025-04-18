package com.kaykymatos.todotask.entities.enums;

public enum TaskStatus {
    DONE(1),
    IN_PROCESS(2),
    CANCELED(4);

    private int code;

    private TaskStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

    public static TaskStatus valueOf(int code) {
        for (TaskStatus value : TaskStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw  new IllegalArgumentException("Invalid TaskStatus code");
    }
}
