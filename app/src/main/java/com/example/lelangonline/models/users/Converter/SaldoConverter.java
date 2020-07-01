package com.example.lelangonline.models.users.Converter;

import androidx.room.TypeConverter;

import com.example.lelangonline.models.users.Saldo;
import com.example.lelangonline.models.users.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SaldoConverter {
    @TypeConverter
    public String fromSaldo(Saldo saldo) {
        if (saldo == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Saldo>() {}.getType();
        return gson.toJson(saldo, type);
    }

    @TypeConverter
    public Saldo toSaldo(String saldoString) {
        if (saldoString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<Saldo>() {}.getType();
        return gson.fromJson(saldoString, type);
    }

    @TypeConverter
    public String fromUser(User user) {
        if (user == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<User>() {}.getType();
        return gson.toJson(user, type);
    }

    @TypeConverter
    public User toUser(String userString) {
        if (userString == null) {
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<User>() {}.getType();
        return gson.fromJson(userString, type);
    }

}
