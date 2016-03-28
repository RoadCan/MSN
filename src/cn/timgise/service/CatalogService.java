/**
 *    Copyright 2010-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package cn.timgise.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cn.timgise.domain.Category;
import cn.timgise.domain.Item;
import cn.timgise.domain.Product;
import cn.timgise.persistence.CategoryMapper;
import cn.timgise.persistence.ItemMapper;
import cn.timgise.persistence.ProductMapper;

/**
 * @author Eduardo Macarron
 * 
 */
@Service
public class CatalogService {

	@Autowired
	private CategoryMapper categoryMapper;
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ProductMapper productMapper;

	public List<Category> getCategoryList() {
		return categoryMapper.getCategoryList();
	}

	public Category getCategory(String categoryId) {
		return categoryMapper.getCategory(categoryId);
	}

	public Product getProduct(String productId) {
		return productMapper.getProduct(productId);
	}

	public List<Product> getProductListByCategory(String categoryId) {
		return productMapper.getProductListByCategory(categoryId);
	}

	public List<Product> searchProductList(String keywords) {
		List<Product> products = new ArrayList<Product>();
		for (String keyword : keywords.split("\\s+")) {
			products.addAll(productMapper.searchProductList("%"
					+ keyword.toLowerCase() + "%"));
		}
		return products;
	}

	public List<Item> getItemListByProduct(String productId) {
		return itemMapper.getItemListByProduct(productId);
	}

	public Item getItem(String itemId) {
		return itemMapper.getItem(itemId);
	}

	public boolean isItemInStock(String itemId) {
		return itemMapper.getInventoryQuantity(itemId) > 0;
	}
}