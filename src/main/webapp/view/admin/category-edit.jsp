<%--
  Created by IntelliJ IDEA.
  User: binh
  Date: 9/30/2024
  Time: 9:08 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/conmon/taglib.jsp"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="<c:url value="/admin/category/update"/>" method="post" enctype="multipart/form-data">
    <input type="text" id = "categoryid" name="categoryid" value="${cate.categoryID}" hidden="hidden">
    <label for="categoryname">Category name:</label><br>
    <input type="text" id="categoryname" name="categoryname" value="${cate.categoryname}"><br>

    <label for="img">Link image</label><br>
    <input type="text" id="img" name="img"><br>

    <c:if test="${cate.images.substring(0,5) == 'https'}">
        <c:url value="${cate.images}" var="imgUrl"></c:url>
    </c:if>
    <c:if test="${cate.images.substring(0,5) != 'https'}">
        <c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
    </c:if>
    <img height="150" width="200" src="${imgUrl}" />
    <label for="img1">Upload image</label><br>
    <input type="file" id="img1" name="img1" ><br>


    <label for="status">Status</label><br>
    <input type="radio" id="statuson" name="status" value="1" ${cate.status==1?'checked' : ''}>
    <label for="statuson">Hoat dong</label>
    <input type="radio" id="statusoff" name="status" value="0" ${cate.status==0?'checked' : ''}>
    <label for="statuson">Khong hoat dong</label>
    <br>
    <input type="submit" value="Update">
</form>

</body>
</html>
