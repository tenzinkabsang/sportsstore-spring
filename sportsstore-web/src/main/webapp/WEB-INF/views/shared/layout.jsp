<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <title><decorator:title default="SportsStore" /></title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <%--<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">--%>

    <link rel="stylesheet" href="<s:url value="/resources/css/bootstrap.css" />">

    <style type="text/css">
        body { padding: 59px; }
        .navbar { margin-bottom: 30px; }
        .error {
            color: #ff0000;
        }

        .errorblock {
            color: #000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>
<body>
<div>
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <div class="collapse navbar-collapse">
                <form class="navbar-form navbar-right" role="form">
                    <div class="form-group">
                        <em><span class="text-muted"><decorator:head /></span> </em>
                    </div>
                </form>
            </div>

        </div>
    </div>

    <div>

        <div class="col-sm-3 col-md-2 sidebar">
            <%@include file="menu.jsp"%>
        </div>

        <div class="col-sm-9 main">
            <c:if test="${not empty message}">
                <div class="alert alert-success" role="alert">
                    ${message}
                </div>
            </c:if>

            <decorator:body />
        </div>
    </div>

</div>
</body>
</html>