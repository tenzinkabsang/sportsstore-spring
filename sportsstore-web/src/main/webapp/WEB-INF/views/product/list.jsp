<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>Products</title>

<div>
    <c:forEach var="p" items="${productList}">
        <div>
            <h2>${p.name}</h2>
            ${p.description}
            <h4>
                <fmt:formatNumber value="${p.price}" type="Currency" />
            </h4>
        </div>
    </c:forEach>
</div>