<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="html" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<title>
    Manage Products
</title>

<p>
    <em class="text-muted">
        <sec:authorize var="loggedIn" access="isAuthenticated()">
            Welcome <sec:authentication property="principal.username" /> -
            <a href="<s:url value="/static/j_spring_security_logout"/>">Logout</a>
        </sec:authorize>
    </em>
</p>


<table class="table table-bordered table-condensed">
    <tr>
        <th>Name</th>
        <th>Category</th>
        <th>Price</th>
        <th>Actions</th>
    </tr>
    <c:forEach var="item" items="${productList}">
        <tr>
            <td>
                <a href="<s:url value="edit/${item.productId}"/>" class="btn btn-link">${item.name}</a>
            </td>
            <td>${item.category}</td>
            <td>
                <fmt:formatNumber value="${item.price}" type="Currency"/>
            </td>

            <td>
                <html:form method="delete" action="delete">
                    <input type="hidden" name="productId" value="${item.productId}"/>
                    <input type="submit" value="Delete" class="btn btn-warning"/>
                </html:form>
            </td>
        </tr>
    </c:forEach>
</table>

<p>
    <a href="<s:url value="create"/>" class="btn btn-default">Add a new product</a>
</p>
