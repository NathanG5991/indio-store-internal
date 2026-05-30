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

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.jpetstore.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = MapperTestContext.class)
@Transactional
class AccountMapperTest {

  @Autowired
  private AccountMapper mapper;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Test
  void getAccountByUsername() {
    // given
    String username = "j2ee";

    // when
    Account account = mapper.getAccountByUsername(username);

    // then
    // Correspondance exacte avec les données du compte Admin (j2ee)
    assertThat(account.getUsername()).isEqualTo("j2ee");
    assertThat(account.getEmail()).isEqualTo("admin@indio-group.com");
    assertThat(account.getFirstName()).isEqualTo("Indio");
    assertThat(account.getLastName()).isEqualTo("Admin");
    assertThat(account.getStatus()).isEqualTo("OK");
    assertThat(account.getAddress1()).isEqualTo("Siège Social");
    assertThat(account.getAddress2()).isEqualTo("Bâtiment A");
    assertThat(account.getCity()).isEqualTo("Paris");
    assertThat(account.getState()).isEqualTo("IDF");
    assertThat(account.getZip()).isEqualTo("75001");
    assertThat(account.getCountry()).isEqualTo("France");
    assertThat(account.getPhone()).isEqualTo("01-02-03-04-05");
    assertThat(account.getLanguagePreference()).isEqualTo("french");
    assertThat(account.getFavouriteCategoryId()).isEqualTo("IT");
    assertThat(account.isListOption()).isTrue();
    assertThat(account.isBannerOption()).isTrue();
  }

  @Test
  void getAccountByUsernameAndPassword() {
    // given
    String username = "ACID";
    String password = "ACID";

    // when
    Account account = mapper.getAccountByUsernameAndPassword(username, password);

    // then
    // Correspondance exacte avec les données du compte Employé (ACID)
    assertThat(account.getUsername()).isEqualTo("ACID");
    assertThat(account.getEmail()).isEqualTo("staff@indio-group.com");
    assertThat(account.getFirstName()).isEqualTo("Employé");
    assertThat(account.getLastName()).isEqualTo("Indio");
    assertThat(account.getStatus()).isEqualTo("OK");
    assertThat(account.getAddress1()).isEqualTo("Bureau Lyon");
    assertThat(account.getAddress2()).isEqualTo("Espace Cowork");
    assertThat(account.getCity()).isEqualTo("Lyon");
    assertThat(account.getState()).isEqualTo("Rhone");
    assertThat(account.getZip()).isEqualTo("69000");
    assertThat(account.getCountry()).isEqualTo("France");
    assertThat(account.getPhone()).isEqualTo("04-05-06-07-08");
    assertThat(account.getLanguagePreference()).isEqualTo("french");
    assertThat(account.getFavouriteCategoryId()).isEqualTo("OFFICE");
    assertThat(account.isListOption()).isTrue();
    assertThat(account.isBannerOption()).isTrue();
    // La bannière pour "OFFICE" dans ton script SQL
    assertThat(account.getBannerName()).isEqualTo("FOURNITURES DE BUREAU");
  }

  @Test
  void insertAccount() {
    // given
    Account account = new Account();
    account.setUsername("mybatis");
    account.setEmail("mybatis@example.com");
    account.setFirstName("My");
    account.setLastName("Batis");
    account.setStatus("NG");
    account.setAddress1("Address 1");
    account.setAddress2("Address 2");
    account.setCity("City");
    account.setState("ST");
    account.setZip("99001");
    account.setCountry("JPN");
    account.setPhone("09012345678");

    // when
    mapper.insertAccount(account);

    // then
    Map<String, Object> record = jdbcTemplate.queryForMap("SELECT * FROM account WHERE userid = ?", "mybatis");
    assertThat(record).hasSize(12).containsEntry("USERID", account.getUsername())
        .containsEntry("EMAIL", account.getEmail()).containsEntry("FIRSTNAME", account.getFirstName())
        .containsEntry("LASTNAME", account.getLastName()).containsEntry("STATUS", account.getStatus())
        .containsEntry("ADDR1", account.getAddress1()).containsEntry("ADDR2", account.getAddress2())
        .containsEntry("CITY", account.getCity()).containsEntry("STATE", account.getState())
        .containsEntry("ZIP", account.getZip()).containsEntry("COUNTRY", account.getCountry())
        .containsEntry("PHONE", account.getPhone());
  }

  @Test
  void insertProfile() {
    // given
    Account account = new Account();
    account.setUsername("mybatis");
    account.setLanguagePreference("french");
    account.setFavouriteCategoryId("IT"); // Sécurisé avec une vraie catégorie Indio Store
    account.setListOption(true);
    account.setBannerOption(false);

    // when
    mapper.insertProfile(account);

    // then
    Map<String, Object> record = jdbcTemplate.queryForMap("SELECT * FROM profile WHERE userid = ?", "mybatis");

    assertThat(record).hasSize(5).containsEntry("USERID", account.getUsername())
        .containsEntry("LANGPREF", account.getLanguagePreference())
        .containsEntry("FAVCATEGORY", account.getFavouriteCategoryId()).containsEntry("MYLISTOPT", 1)
        .containsEntry("BANNEROPT", 0);
  }

  @Test
  void insertSignon() {
    // given
    Account account = new Account();
    account.setUsername("mybatis");
    account.setPassword("password");

    // when
    mapper.insertSignon(account);

    // then
    Map<String, Object> record = jdbcTemplate.queryForMap("SELECT * FROM signon WHERE username = ?", "mybatis");

    assertThat(record).hasSize(2).containsEntry("USERNAME", account.getUsername()).containsEntry("PASSWORD",
        account.getPassword());
  }

  @Test
  void updateAccount() {
    // given
    Account account = new Account();
    account.setUsername("j2ee");
    account.setEmail("mybatis@example.com");
    account.setFirstName("My");
    account.setLastName("Batis");
    account.setStatus("NG");
    account.setAddress1("Address 1");
    account.setAddress2("Address 2");
    account.setCity("City");
    account.setState("ST");
    account.setZip("99001");
    account.setCountry("JPN");
    account.setPhone("09012345678");

    // when
    mapper.updateAccount(account);

    // then
    Map<String, Object> record = jdbcTemplate.queryForMap("SELECT * FROM account WHERE userid = ?", "j2ee");

    assertThat(record).hasSize(12).containsEntry("USERID", account.getUsername())
        .containsEntry("EMAIL", account.getEmail()).containsEntry("FIRSTNAME", account.getFirstName())
        .containsEntry("LASTNAME", account.getLastName()).containsEntry("STATUS", account.getStatus())
        .containsEntry("ADDR1", account.getAddress1()).containsEntry("ADDR2", account.getAddress2())
        .containsEntry("CITY", account.getCity()).containsEntry("STATE", account.getState())
        .containsEntry("ZIP", account.getZip()).containsEntry("COUNTRY", account.getCountry())
        .containsEntry("PHONE", account.getPhone());
  }

  @Test
  void updateProfile() {
    // given
    Account account = new Account();
    account.setUsername("j2ee");
    account.setLanguagePreference("french");
    account.setFavouriteCategoryId("IT"); // Sécurisé avec une vraie catégorie
    account.setListOption(false);
    account.setBannerOption(false);

    // when
    mapper.updateProfile(account);

    // then
    Map<String, Object> record = jdbcTemplate.queryForMap("SELECT * FROM profile WHERE userid = ?", "j2ee");

    assertThat(record).hasSize(5).containsEntry("USERID", account.getUsername())
        .containsEntry("LANGPREF", account.getLanguagePreference())
        .containsEntry("FAVCATEGORY", account.getFavouriteCategoryId()).containsEntry("MYLISTOPT", 0)
        .containsEntry("BANNEROPT", 0);
  }

  @Test
  void updateSignon() {
    // given
    Account account = new Account();
    account.setUsername("j2ee");
    account.setPassword("password");

    // when
    mapper.updateSignon(account);

    // then
    Map<String, Object> record = jdbcTemplate.queryForMap("SELECT * FROM signon WHERE username = ?", "j2ee");

    assertThat(record).hasSize(2).containsEntry("USERNAME", account.getUsername()).containsEntry("PASSWORD",
        account.getPassword());
  }
}
