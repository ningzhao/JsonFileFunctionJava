package com.ningzhao;

public class User {
    final private String id;
    final private String name;

    public User(String id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }
}
