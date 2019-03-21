package mock.seller;

import java.util.List;

import com.google.common.collect.Lists;
import com.lff.model.seller.Seller;

public class MockSeller {
	
	public static String FRUIT_HOME = "Winfred Fruit Home";
	public static String TOY_HOME = "Winfred Toy Home";
	
	public static Seller FRUIT_HOME_SELLER = new Seller(FRUIT_HOME);
	public static Seller TOY_HOME_SELLER = new Seller(TOY_HOME);
	
	public static List<Seller> getMockSellers(){
		List<Seller> sellers = Lists.newArrayList();
		sellers.add(FRUIT_HOME_SELLER);
		sellers.add(TOY_HOME_SELLER);
		return sellers;
	}
	
	
}
