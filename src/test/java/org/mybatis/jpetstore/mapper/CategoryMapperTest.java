/*
 *    Copyright 2010-2026 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.jpetstore.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.jpetstore.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MapperTestContext.class)
@Transactional
class CategoryMapperTest {

  @Autowired
  private CategoryMapper mapper;

  @Test
  void getCategoryList() {
    // given

    // when
    List<Category> categories = mapper.getCategoryList();

    // then
    categories.sort(Comparator.comparing(Category::getCategoryId));
    assertThat(categories).hasSize(5);

    // Index 0: COF
    assertThat(categories.get(0).getCategoryId()).isEqualTo("COF");
    assertThat(categories.get(0).getName()).isEqualTo("Espace Café");
    assertThat(categories.get(0).getDescription()).isEqualTo("<p>Capsules, Gobelets, Thé</p>");

    // Index 1: ELEC
    assertThat(categories.get(1).getCategoryId()).isEqualTo("ELEC");
    assertThat(categories.get(1).getName()).isEqualTo("Électronique");
    assertThat(categories.get(1).getDescription()).isEqualTo("<p>Câbles, Adaptateurs, Chargeurs</p>");

    // Index 2: FURN
    assertThat(categories.get(2).getCategoryId()).isEqualTo("FURN");
    assertThat(categories.get(2).getName()).isEqualTo("Mobilier");
    assertThat(categories.get(2).getDescription()).isEqualTo("<p>Chaises ergonomiques, Bureaux</p>");

    // Index 3: IT
    assertThat(categories.get(3).getCategoryId()).isEqualTo("IT");
    assertThat(categories.get(3).getName()).isEqualTo("Informatique");
    assertThat(categories.get(3).getDescription()).isEqualTo("<p>Laptops, Écrans, Périphériques</p>");

    // Index 4: OFFICE
    assertThat(categories.get(4).getCategoryId()).isEqualTo("OFFICE");
    assertThat(categories.get(4).getName()).isEqualTo("Bureautique");
    assertThat(categories.get(4).getDescription()).isEqualTo("<p>Papeterie et fournitures de bureau</p>");
  }

  @Test
  void getCategory() {
    // given
    String categoryId = "IT";

    // when
    Category category = mapper.getCategory(categoryId);

    // then
    assertThat(category.getCategoryId()).isEqualTo("IT");
    assertThat(category.getName()).isEqualTo("Informatique");
    assertThat(category.getDescription()).isEqualTo("<p>Laptops, Écrans, Périphériques</p>");
  }

}
