package com.example.lelangonline.models.converters;

import androidx.room.TypeConverter;

import com.example.lelangonline.models.Source;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class SourceConverter {

        @TypeConverter
        public String fromSource(Source source) {
            if (source == null) {
                return (null);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<Source>() {}.getType();
            return gson.toJson(source, type);
        }

        @TypeConverter
        public Source toSource(String sourceString) {
            if (sourceString == null) {
                return (null);
            }
            Gson gson = new Gson();
            Type type = new TypeToken<Source>() {}.getType();
            return gson.fromJson(sourceString, type);
        }

}
