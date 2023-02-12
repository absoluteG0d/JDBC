package entity;

import java.time.LocalDate;


public class Order {
    private int id;
    private User user;
    private Product product;
    private LocalDate date;
    private double price;
    private int itemCount;

    private Order(int id, User user, Product product, LocalDate date, double price, int itemCount) {
        this.id = id;
        this.user = user;
        this.product = product;
        this.date = date;
        this.price = price;
        this.itemCount = itemCount;
    }

    public static class OrderBuilder {
        private int id;
        private User user;
        private Product product;
        private LocalDate date;
        private double price;
        private int itemCount;

        public OrderBuilder setId(int id) {
            this.id = id;
            return this;
        }
        public OrderBuilder setUser(User user) {
            this.user = user;
            return this;
        }
        public OrderBuilder setProduct(Product product) {
            this.product = product;
            return this;
        }
        public OrderBuilder setDate(LocalDate date) {
            this.date = date;
            return this;
        }
        public OrderBuilder setPrice(double price) {
            this.price = price;
            return this;
        }
        public OrderBuilder setItemCount(int itemCount) {
            this.itemCount = itemCount;
            return this;
        }
        public Order build() {
            if (user == null || product == null || date == null || price <= 0 || itemCount <= 0) {
                throw new RuntimeException("Trying to create an empty order");
            }
            return new Order(id, user, product, date, price, itemCount);
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", user=" + user +
                ", product=" + product +
                ", date=" + date +
                ", price=" + price +
                ", itemCount=" + itemCount +
                '}';
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getPrice() {
        return price;
    }

    public int getItemCount() {
        return itemCount;
    }
}
