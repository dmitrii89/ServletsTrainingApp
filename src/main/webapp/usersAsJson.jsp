<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="template/Header.jsp">
    <jsp:param name="title" value="Users" />
</jsp:include>

<script>
    $( document ).ready(function() {
        $.get( "json", function( data ) {
            var $result = $( ".result" );
            for(var i = 0; i < data.length; i++){
                $result.append("<p>Name: " + data[i].name + ", age:" + data[i].age + "</p>");
            }
        });
    });
</script>
<div class="result"></div>

<%@include file="template/Footer.jsp"%>