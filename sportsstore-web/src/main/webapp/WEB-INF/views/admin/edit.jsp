<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%@ taglib prefix="html" tagdir="/WEB-INF/tags" %>

<title>Admin: Edit ${product.name}</title>

<div class="container">
    <h2>Edit ${product.name}</h2>

    <form:form modelAttribute="product" role="form" action="/admin/edit">
        <form:errors path="*" cssClass="error" />

        <form:hidden path="productId"/>

        <html:inputField name="name" label="Name" />

        <html:inputField name="description" label="Description" />

        <html:inputField name="price" label="Price" />

        <html:inputField name="category" label="Category" />

        <button type="submit" class="btn btn-default">Save</button>

        <a href="<s:url value="/admin/index"/>">Cancel and return to List</a>
    </form:form>

</div>

