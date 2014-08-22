<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>

<div>
    <h2>${p.name}</h2>
    ${p.description}
    <h4>
        <fmt:formatNumber value="${p.price}" type="Currency" />
    </h4>

    <form:form method="post" action="/cart/addToCart">
        <input type="hidden" name="productId" value="${p.productId}" />

        <input type="hidden" name="returnUrl" value="<s:url value="/" />" />

        <input type="submit" value="+ Add to cart" class="btn btn-default"/>
    </form:form>

</div>