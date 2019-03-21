package mock.sku;

import java.util.Arrays;
import java.util.List;

import com.lff.model.product.Sku;
import com.lff.model.product.SkuInfo;

import mock.product.MockApples;

public class MockRoyalGalaSku {
	public static List<Sku> getMockRoyalGalaSku(){
		Sku sku1 = new Sku(MockApples.ROYAL_GALA_APPLE,Arrays.asList(new SkuInfo[]{new SkuInfo("size", "small"),new SkuInfo("quality", "not bad")}),50.00);
		Sku sku2 = new Sku(MockApples.ROYAL_GALA_APPLE,Arrays.asList(new SkuInfo[]{new SkuInfo("size", "big"),new SkuInfo("quality", "great")}),80.00);
		return Arrays.asList(new Sku[]{sku1,sku2});
	}
}
