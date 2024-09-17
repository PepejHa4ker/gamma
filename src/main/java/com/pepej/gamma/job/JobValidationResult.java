package com.pepej.gamma.job;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.Value;

@Value
public class JobValidationResult {

    boolean success;
    @Nullable String errorMessage;

    public static JobValidationResult ok() {
        return new JobValidationResult(true, null);
    }

    public static JobValidationResult error(@Nonnull String errorMessage) {
        return new JobValidationResult(false, errorMessage);
    }
}