package mock.sku;

import java.util.Arrays;
import java.util.List;

import com.lff.model.product.Sku;
import com.lff.model.product.SkuInfo;

import mock.product.MockApples;

public class MockGenevaEarlySku {
	public static List<Sku> getMockGenevaEarlySku(){
		Sku sku1 = new Sku(MockApples.GENEVA_EARLY_APPLE,Arrays.asList(new SkuInfo[]{new SkuInfo("size", "big"),new SkuInfo("quality", "great")}),15.00);
		Sku sku2 = new Sku(MockApples.GENEVA_EARLY_APPLE,Arrays.asList(new SkuInfo[]{new SkuInfo("size", "medium"),new SkuInfo("quality", "not bad")}),8.00);
		return Arrays.asList(new Sku[]{sku1,sku2});
	}
}
