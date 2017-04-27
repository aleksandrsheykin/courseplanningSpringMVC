package main.models.pojo;

/**
 * Created by admin on 19.04.2017.
 */
public class Product {
    private Integer id_product;
    private String name;
    private String description;
    private int user_id;

    public Product(Integer id_product, String name, String description, int user_id) {
        this.id_product = id_product;
        this.name = name;
        this.description = description;
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}