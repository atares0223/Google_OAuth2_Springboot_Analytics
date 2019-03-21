package mock.sku;

import java.util.Arrays;
import java.util.List;

import com.lff.model.product.Sku;
import com.lff.model.product.SkuInfo;

import mock.product.MockApples;

public class MockJerseyMacSku {
	public static List<Sku> getMockJerseyMacSku(){
		Sku sku1 = new Sku(MockApples.JERSEYMAC_APPLE,Arrays.asList(new SkuInfo[]{new SkuInfo("size", "small"),new SkuInfo("quality", "great")}),15.00);
		Sku sku2 = new Sku(MockApples.JERSEYMAC_APPLE,Arrays.asList(new SkuInfo[]{new SkuInfo("size", "medium"),new SkuInfo("quality", "good")}),15.00);
		return Arrays.asList(new Sku[]{sku1,sku2});
	}
}
