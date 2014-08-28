package com.sportsstore.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class Helper {

    private Helper(){} // prevent initialization

    private static final Gson gson;

    static {
        gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public static final <T> String toJson(T src){
        return gson.toJson(src);
    }
}
