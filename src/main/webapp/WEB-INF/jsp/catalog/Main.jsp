<%--

       Copyright 2010-2026 the original author or authors.

       Licensed under the Apache License, Version 2.0 (the "License");
       you may not use this file except in compliance with the License.
       You may obtain a copy of the License at

          https://www.apache.org/licenses/LICENSE-2.0

       Unless required by applicable law or agreed to in writing, software
       distributed under the License is distributed on an "AS IS" BASIS,
       WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
       See the License for the specific language governing permissions and
       limitations under the License.

--%>
<%@ include file="../common/IncludeTop.jsp"%>

<div id="Welcome">
<div id="WelcomeContent">
    <c:if test="${sessionScope.accountBean != null }">
        <c:if test="${sessionScope.accountBean.authenticated}">
            Bienvenue collaborateur, ${sessionScope.accountBean.account.firstName}!
        </c:if>
    </c:if>
</div>
</div>

<div id="Main">
<div id="Sidebar">
<div id="SidebarContent">
    <b>CATEGORIES DES CONSOMMABLES</b><br /><br />

    <stripes:link beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean" event="viewCategory">
        <stripes:param name="categoryId" value="IT" />
        <strong>INFORMATIQUE</strong>
    </stripes:link> <br />
    Laptops, Ecrans, Peripheriques <br /><br />

    <stripes:link beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean" event="viewCategory">
        <stripes:param name="categoryId" value="OFFICE" />
        <strong>BUREAUTIQUE</strong>
    </stripes:link> <br />
    Papeterie et fournitures <br /><br />

    <stripes:link beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean" event="viewCategory">
        <stripes:param name="categoryId" value="FURN" />
        <strong>MOBILIER</strong>
    </stripes:link> <br />
    Chaises et Bureaux <br /><br />

    <stripes:link beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean" event="viewCategory">
        <stripes:param name="categoryId" value="ELEC" />
        <strong>ELECTRONIQUE</strong>
    </stripes:link> <br />
    Cables et Adaptateurs <br /><br />

    <stripes:link beanclass="org.mybatis.jpetstore.web.actions.CatalogActionBean" event="viewCategory">
        <stripes:param name="categoryId" value="COF" />
        <strong>ESPACE CAFE</strong>
    </stripes:link> <br />
    Consommables detente
</div>
</div>

<div id="MainImage">
    <div id="MainImageContent" style="padding: 40px; text-align: left; border: 1px solid #eee; border-radius: 10px; background-color: #f9f9f9;">
        <h1 style="color: #e74c3c; margin-top: 0;">INDIO STORE</h1>
        <h2 style="color: #2c3e50;">Portail d'approvisionnement interne</h2>
        <p>Bienvenue sur votre nouvel outil de gestion du materiel de bureau.</p>
        <p>Utilisez le menu a gauche ou les liens rapides en haut pour commander vos fournitures.</p>
        <br />
        <div style="padding: 15px; background-color: #e74c3c; color: white; display: inline-block; border-radius: 5px;">
            <strong>Information :</strong> Toutes les commandes sont soumises a validation RH.
        </div>
    </div>
</div>

<div id="Separator">&nbsp;</div>
</div>

<%@ include file="../common/IncludeBottom.jsp"%>
