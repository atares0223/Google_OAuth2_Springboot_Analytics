package mock.category;

import com.lff.model.product.Category;

import mock.seller.MockSeller;

public class MockCategory {
	
	public static String FRUIT = "fruit";
	public static String APPLE = "apple";
	public static Category FRUIT_CATEGORY = new Category(null, FRUIT);
	public static Category APPLE_CATEGORY = new Category(FRUIT_CATEGORY, APPLE);
	
	public static String TOY = "toy";
	public static String TRANSFORMERS = "transformers";
	public static Category TOY_CATEGORY = new Category(null, TOY);
	public static Category TRANSFORMERS_CATEGORY = new Category(TOY_CATEGORY, TRANSFORMERS);
	public static Category getCategory(String sellerName){
		if(sellerName.equals(MockSeller.FRUIT_HOME)){
			return APPLE_CATEGORY;
		}else if(sellerName.equals(MockSeller.TOY_HOME)){
			return TRANSFORMERS_CATEGORY;
		}
		return null;
	}
	
	
}
