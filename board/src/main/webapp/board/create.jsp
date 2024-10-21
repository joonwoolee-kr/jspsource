<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/header.jsp"%>
<div class="container-fluid">
<h1 class="h3 mb-4 text-gray-800">Read</h1>
<form>
  <div class="form-group">
    <label for="bno">글번호</label>
    <input type="text" class="form-control" id="bno" name="bno">
  </div>
  <div class="form-group">
    <label for="title">제목</label>
    <input type="text" class="form-control" id="title" name="title">
  </div>
  <div class="form-group">
    <label for="content">내용</label>
    <input type="text" class="form-control" id="content" name="content">
  </div>
  <div class="form-group">
    <label for="attach">첨부파일</label>
    <input type="file" class="form-control" id="attach" name="attach">
  </div>
  <div class="form-group">
    <label for="name">작성자</label>
    <input type="text" class="form-control" id="name" name="name">
  </div>

  <button type="submit" class="btn btn-primary">Submit</button>
</form>
</div>
<%@ include file="../include/footer.jsp"%>