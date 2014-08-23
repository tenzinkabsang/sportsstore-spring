<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<a href="<s:url value="/cart/index?returnUrl=${requestScope['javax.servlet.forward.servlet_path']}" />">
    <span style="text-decoration: underline">
        <span class="glyphicon glyphicon-shopping-cart"></span>
        <em>
            <c:out value="${not empty cart ? cart.totalItems : 0}" />  item(s)
            <fmt:formatNumber value="${not empty cart ? cart.computeTotalValue() : 0}" type="Currency" />
        </em>
        <strong>Checkout</strong>
    </span>
</a>