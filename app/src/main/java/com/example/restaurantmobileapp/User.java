package com.example.restaurantmobileapp;

public class User {
    private String name, email;
    private Cart cart; //
    private FavouriteProducts favouriteProducts;

    public User(){}
    public User(String name, String email, Cart cart, FavouriteProducts favouriteProducts) {
        this.name = name;
        this.email = email;
        this.cart = cart;
        this.favouriteProducts = favouriteProducts;
    }

    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Cart getCart() {
        return cart;
    }
    public void setCart(Cart cart) {
        this.cart = cart;
    }
    public FavouriteProducts getFavouriteProducts() {
        return this.favouriteProducts;
    }
    public void setFavouriteProducts(FavouriteProducts favouriteProducts) {
        this.favouriteProducts = favouriteProducts;
    }

    public void addOrder(Order order){
        this.cart.addOrder(order);
    }
    public void canOrder(Order order){this.cart.canOrder(order);}

    public void addFavouriteProduct(Product product){
        this.favouriteProducts.addFavouriteProduct(product);
    }
    public void removeFavouriteProduct(Product product){
        this.favouriteProducts.removeFavouriteProduct(product);
    }
}