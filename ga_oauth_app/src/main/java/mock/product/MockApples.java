package mock.product;

import java.util.Arrays;
import java.util.List;

import com.lff.model.product.Category;
import com.lff.model.product.Product;
import com.lff.model.seller.Seller;

import mock.category.MockCategory;
import mock.seller.MockSeller;

public class MockApples {
	private static Category category = MockCategory.getCategory(MockSeller.FRUIT_HOME);
	private static Seller seller = MockSeller.FRUIT_HOME_SELLER;
	public static Product GENEVA_EARLY_APPLE = new Product(category, seller, "Red Apple", 5.00, 10);
	public static Product JERSEYMAC_APPLE = new Product(category, seller, "Blue Apple", 15.00, 20);
	public static Product ROYAL_GALA_APPLE = new Product(category, seller, "Black Apple", 75.00, 60);
	public static List<Product> getMockApples(){
		return Arrays.asList(new Product[]{GENEVA_EARLY_APPLE,JERSEYMAC_APPLE,ROYAL_GALA_APPLE});
	}
}
