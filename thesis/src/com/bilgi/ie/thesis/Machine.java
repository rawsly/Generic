package com.bilgi.ie.thesis;

public class Machine {
    public Integer id;
    private String name;
    private String type;
    private Stage stage;

    public Machine() {}

    public Machine(Integer id, String name, String type, Stage stage) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.stage = stage;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Machine{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", type='" + type + '\'' +
            ", stage=" + stage +
            '}';
    }
}
