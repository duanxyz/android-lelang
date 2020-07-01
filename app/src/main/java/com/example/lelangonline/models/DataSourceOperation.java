package com.example.lelangonline.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.lelangonline.utils.DataStatus;

public class DataSourceOperation<T> {

    @NonNull
    public final DataStatus dataStatus;

    @Nullable
    public final T data;
    @Nullable
    public final String[] fullMessages;

    private DataSourceOperation(@NonNull DataStatus dataStatus, @Nullable T data, @Nullable String[] fullMessages) {
        this.dataStatus = dataStatus;
        this.data = data;
        this.fullMessages = fullMessages;
    }

    public static <T> DataSourceOperation<T> success(@NonNull T data) {
        return new DataSourceOperation<>(DataStatus.SUCCESS, data, null);
    }

    public static <T> DataSourceOperation<T> error(String[] fullMessages, @Nullable T data) {
        return new DataSourceOperation<>(DataStatus.ERROR, data, fullMessages);
    }

    public static <T> DataSourceOperation<T> loading(@Nullable T data) {
        return new DataSourceOperation<>(DataStatus.LOADING, data, null);
    }

    public boolean isSuccess() {
        return dataStatus == DataStatus.SUCCESS;
    }

    public boolean isLoading() {
        return dataStatus == DataStatus.LOADING;
    }

    public boolean isLoaded() {
        return dataStatus != DataStatus.LOADING;
    }
}
