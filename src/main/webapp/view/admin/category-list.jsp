<%--
  Created by IntelliJ IDEA.
  User: binh
  Date: 9/30/2024
  Time: 8:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/conmon/taglib.jsp"%>
<a href="<c:url value="/admin/category/add"/>">Add <category></category></a>
<table>
    <tr>
        <th>STT</th>
        <th>Image</th>
        <th>Category name</th>
        <th>Status</th>
        <th>Action</th>
    </tr>
    <c:forEach items="${listcate}" var="cate" varStatus="STT" >
        <tr class="odd gradeX">
            <td>${STT.index+1 }</td>

            <c:if test="${cate.images.substring(0,5) == 'https'}">
                <c:url value="${cate.images}" var="imgUrl"></c:url>
            </c:if>
            <c:if test="${cate.images.substring(0,5) != 'https'}">
                <c:url value="/image?fname=${cate.images}" var="imgUrl"></c:url>
            </c:if>
            <td><img height="150" width="200" src="${imgUrl}" /></td>
            <td>${cate.categoryname}</td>
            <td>${cate.status}</td>
            <td><a href="<c:url value='/admin/category/edit?id=${cate.categoryID}'/>" class="center">Sửa</a>
                <a href="<c:url value='/admin/category/delete?id=${cate.categoryID}'/>" class="center">Xóa</a>
                <a href="<c:url value='/admin/videos?id=${cate.categoryID}'/>" class="center">Xem danh sách video</a>
            </td>
        </tr>
    </c:forEach>
</table>
