public class App {
    public static void main(String[] args) {
        Product cheese = new Product("cheese", 20, 3, true, true, 0.4);
        Product biscuits = new Product("biscuits", 30, 3, true, true, 0.7);
        Product tv = new Product("tv", 20000, 2, false, true, 10.0);
        Product scratchCard = new Product("scratch Card", 100, 10, false, false, 0.0);
        Customer customer = new Customer("alice", 1000);
        customer.addToCart(cheese, 2);
        customer.addToCart(biscuits, 1);
        customer.addToCart(scratchCard, 1);
        customer.checkout();
    }
}

class Product {
    String name;
    int price;
    int quantity;
    boolean expires;
    boolean requiresShipping;
    double weight;

    public Product(String name, int price, int quantity, boolean expires, boolean requiresShipping, double weight) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.expires = expires;
        this.requiresShipping = requiresShipping;
        this.weight = weight;
    }
}

class Cart {
    Product product;
    int quantity;

    public Cart(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}

class Customer {
    String name;
    int balance;
    java.util.ArrayList<Cart> cart = new java.util.ArrayList<>();

    public Customer(String name, int balance) {
        this.name = name;
        this.balance = balance;
    }

    public void addToCart(Product prod, int qty) {
        if (qty > prod.quantity) {
            System.out.println("not enough product quantity");
            return;
        }
        cart.add(new Cart(prod, qty));
    }

    public void checkout() {
        int subtotal = 0;
        double totalWeight = 0.0;

        if (cart.isEmpty()) {
            System.out.println("cart is empty");
            return;
        }
        for (Cart item : cart) {
            
            if (item.quantity > item.product.quantity) {
                System.out.println("not enough stock for " + item.product.name);
                return;
            }
            subtotal += item.product.price * item.quantity;
            if (item.product.requiresShipping) {
                totalWeight += item.product.weight * item.quantity;
            }
        }

        int shippingFee = (int)(totalWeight * 10);
        int totalAmount = subtotal + shippingFee;

        if (balance < totalAmount) {
            System.out.println("please check your balance");
            return; 
        }

   
     
        
        for (Cart item : cart) {
            System.out.println(item.quantity + "x " + item.product.name + "   " + (item.product.price * item.quantity));
        }
        System.out.println("Subtotal      " + subtotal);
        System.out.println("Shipping      " + shippingFee);
        System.out.println("Amount        " + totalAmount);
       
    }
}
