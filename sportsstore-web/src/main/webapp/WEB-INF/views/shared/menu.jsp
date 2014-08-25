
<ul class="nav nav-pills nav-stacked">
    <li>
        <a href="<c:url value="/" />">Home</a>
    </li>
    <c:forEach var="category" items="${categoryModel.categories}">

        <li class="${categoryModel.isSelected(category)}">
            <%--<a href="/${category}/page1">${category}</a>--%>

            <a href="<c:url value="/${category}/page1" />">${category}</a>
        </li>
    </c:forEach>
</ul>

