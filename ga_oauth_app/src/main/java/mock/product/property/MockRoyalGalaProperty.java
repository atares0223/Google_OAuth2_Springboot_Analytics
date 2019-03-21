package mock.product.property;

import java.util.Arrays;
import java.util.List;

import com.lff.model.product.Property;

public class MockRoyalGalaProperty {
	public static List<Property> getMockRoyalGalaProperty(){
		Property sizeProperty = new Property("size", Arrays.asList(new String[]{"big","medium","small"}));
		Property qualityProperty = new Property("quality", Arrays.asList(new String[]{"great","good","not bad"}));
		return Arrays.asList(new Property[]{sizeProperty,qualityProperty});
	}
}
