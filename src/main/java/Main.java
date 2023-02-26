import dao.OrderDAO;
import dao.ProductDAO;
import entity.Product;

public class Main {
    public static void main(String[] args) {
        ProductDAO productDAO = new ProductDAO();
        productDAO.retrieve(1);
    }
}




