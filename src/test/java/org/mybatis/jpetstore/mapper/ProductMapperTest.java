/*
 * Copyright 2010-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.mybatis.jpetstore.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.jpetstore.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MapperTestContext.class)
@Transactional
class ProductMapperTest {

  @Autowired
  private ProductMapper mapper;

  @Test
  void getProductListByCategory() {
    // given
    String categoryId = "IT";

    // when
    List<Product> products = mapper.getProductListByCategory(categoryId);

    // then
    products.sort(Comparator.comparing(Product::getProductId));
    assertThat(products).hasSize(3);
    
    // Index 0: IT-LP-01
    assertThat(products.get(0).getProductId()).isEqualTo("IT-LP-01");
    assertThat(products.get(0).getName()).isEqualTo("Dell XPS 15");
    assertThat(products.get(0).getCategoryId()).isEqualTo("IT");
    assertThat(products.get(0).getDescription())
        .isEqualTo("Ordinateur portable haute performance pour développeur");
        
    // Index 1: IT-LP-02
    assertThat(products.get(1).getProductId()).isEqualTo("IT-LP-02");
    assertThat(products.get(1).getName()).isEqualTo("MacBook Pro");
    assertThat(products.get(1).getCategoryId()).isEqualTo("IT");
    assertThat(products.get(1).getDescription())
        .isEqualTo("Apple Silicon M3 - 16GB RAM");
        
    // Index 2: IT-MN-01
    assertThat(products.get(2).getProductId()).isEqualTo("IT-MN-01");
    assertThat(products.get(2).getName()).isEqualTo("Écran 27 Pouces");
    assertThat(products.get(2).getCategoryId()).isEqualTo("IT");
    assertThat(products.get(2).getDescription())
        .isEqualTo("Dalle 4K pour graphistes et bureautique");
  }

  @Test
  void getProduct() {
    // given
    String productId = "IT-LP-01";

    // when
    Product product = mapper.getProduct(productId);

    // then
    assertThat(product.getProductId()).isEqualTo("IT-LP-01");
    assertThat(product.getName()).isEqualTo("Dell XPS 15");
    assertThat(product.getCategoryId()).isEqualTo("IT");
    assertThat(product.getDescription()).isEqualTo("Ordinateur portable haute performance pour développeur");
  }

  @Test
  void searchProductList() {
    // given
    String keywords = "%o%";

    // when
    List<Product> products = mapper.searchProductList(keywords);

    // then
    products.sort(Comparator.comparing(Product::getProductId));
    System.out.println(products);
    
    // Il y a 6 produits contenant la lettre "o" ou "O" dans leur nom
    assertThat(products).hasSize(6);
    
    // 0: COF-01 (Boîte Café)
    assertThat(products.get(0).getProductId()).isEqualTo("COF-01");
    assertThat(products.get(0).getName()).isEqualTo("Boîte Café");
    assertThat(products.get(0).getCategoryId()).isEqualTo("COF");
    assertThat(products.get(0).getDescription())
        .isEqualTo("Pack de 50 capsules expresso");
        
    // 1: FUR-01 (Chaise Ergonomique)
    assertThat(products.get(1).getName()).isEqualTo("Chaise Ergonomique");
    
    // 2: IT-LP-02 (MacBook Pro)
    assertThat(products.get(2).getName()).isEqualTo("MacBook Pro");
    
    // 3: IT-MN-01 (Écran 27 Pouces)
    assertThat(products.get(3).getName()).isEqualTo("Écran 27 Pouces");
    
    // 4: OFF-01 (Carnet Indio)
    assertThat(products.get(4).getName()).isEqualTo("Carnet Indio");
    
    // 5: OFF-02 (Pack Stylos)
    assertThat(products.get(5).getName()).isEqualTo("Pack Stylos");
  }

}
