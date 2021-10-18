<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: thanh
  Date: 09/10/2021
  Time: 8:56 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="flex-w flex-sb-m p-b-52">
    <div class="flex-w flex-l-m filter-tope-group m-tb-10">
        <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5 how-active1"
                data-filter="*">
            All Products
        </button>

        <c:forEach items="${listLoaiSP}" var="o">
            <button class="stext-106 cl6 hov1 bor3 trans-04 m-r-32 m-tb-5"
                    data-filter=".${o.maLoaiSP}">
                    ${o.tenLoaiSP}
            </button>
        </c:forEach>
    </div>
<!-- Seach and filter
    <div class="flex-w flex-c-m m-tb-10">
        <div class="flex-c-m stext-106 cl6 size-104 bor4 pointer hov-btn3 trans-04 m-r-8 m-tb-4 js-show-filter">
            <i class="icon-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-filter-list"></i>
            <i class="icon-close-filter cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
            Filter
        </div>

        <div class="flex-c-m stext-106 cl6 size-105 bor4 pointer hov-btn3 trans-04 m-tb-4 js-show-search">
            <i class="icon-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-search"></i>
            <i class="icon-close-search cl2 m-r-6 fs-15 trans-04 zmdi zmdi-close dis-none"></i>
            Search
        </div>
    </div>
-->
    <!-- Search product (Coming soon)
    <div class="dis-none panel-search w-full p-t-10 p-b-15">
        <div class="bor8 dis-flex p-l-15">
            <button class="size-113 flex-c-m fs-16 cl2 hov-cl1 trans-04" onclick="doSearch()">
                <i class="zmdi zmdi-search"></i>
            </button>

            <input class="mtext-107 cl2 size-114 plh2 p-r-15" type="text" id="searchInput"
                   placeholder="Search for names" title="Type in a name">
        </div>
    </div>
    -->
    <!-- Filter (Coming soon)
    <div class="dis-none panel-filter w-full p-t-10">
        <div class="wrap-filter flex-w bg6 w-full p-lr-40 p-t-27 p-lr-15-sm">
            <div class="filter-col1 p-r-15 p-b-27">
                <div class="mtext-102 cl2 p-b-15">
                    Sort By
                </div>

                <ul>
                    <li class="p-b-6">
                        <a href="#" class="filter-link stext-106 trans-04">
                            Default
                        </a>
                    </li>

                    <li class="p-b-6">
                        <a href="#" class="filter-link stext-106 trans-04">
                            Popularity
                        </a>
                    </li>

                    <li class="p-b-6">
                        <a href="#" class="filter-link stext-106 trans-04">
                            Average rating
                        </a>
                    </li>

                    <li class="p-b-6">
                        <a href="#" class="filter-link stext-106 trans-04 filter-link-active">
                            Newness
                        </a>
                    </li>

                    <li class="p-b-6">
                        <a href="#" class="filter-link stext-106 trans-04">
                            Price: Low to High
                        </a>
                    </li>

                    <li class="p-b-6">
                        <a href="#" class="filter-link stext-106 trans-04">
                            Price: High to Low
                        </a>
                    </li>
                </ul>
            </div>

            <div class="filter-col2 p-r-15 p-b-27 filter-price-group">
                <div class="mtext-102 cl2 p-b-15">
                    Price
                </div>

                <ul>
                    <li class="p-b-6">
                        <button class="filter-link stext-106 trans-04 filter-link-active" data-filter="*">
                            All
                        </button>
                    </li>

                    <li class="p-b-6">
                        <button class="filter-link stext-106 trans-04" data-filter="priceLessThan1M">
                            Dưới 1.000.000 vnđ
                        </button>
                    </li>

                    <li class="p-b-6">
                        <button class="filter-link stext-106 trans-04">
                            $50.00 - $100.00
                        </button>
                    </li>

                    <li class="p-b-6">
                        <button class="filter-link stext-106 trans-04">
                            $100.00 - $150.00
                        </button>
                    </li>

                    <li class="p-b-6">
                        <button class="filter-link stext-106 trans-04">
                            $150.00 - $200.00
                        </button>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    -->
</div>

