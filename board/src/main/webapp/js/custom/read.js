// 목록 클릭 시 
// actionform action="/list.do" 수정 후 submit

const actionForm = document.querySelector("#readForm");

document.querySelector("#readForm .btn-success").addEventListener("click", () => {
	// actionForm bno 요소 제거
	actionForm.querySelector("[name='bno']").remove();

	actionForm.action = "/list.do";
	actionForm.submit();
})

// read.jsp에서 수정 클릭 시
// actionform action="/modify.do" 수정 후 submit
document.querySelector("#readForm .btn-primary").addEventListener("click", () => {
	actionForm.action = "/modify.do";
	actionForm.submit();
})

// modify.jsp에서 수정 클릭 시 (submit) => submit 중지
// readForm password, title, content 값이 있는 지 확인하고
// 없다면 msg 띄우고, 있으면 submit
const readForm = document.querySelector("#readForm");
readForm.addEventListener("submit", (e) => {
	e.preventDefault();

	const title = readForm.querySelector("#title");
	const content = readForm.querySelector("#content");
	const password = readForm.querySelector("#password");

	if (title.value === "") {
		alert("제목을 입력하세요.");
		title.focus();
		return;
	} else if (content.value === "") {
		alert("내용을 입력하세요.");
		content.focus();
		return;
	} else if (password.value === "") {
		alert("비밀번호를 입력하세요.");
		password.focus();
		return;
	}
	
	readForm.submit();
})


// 삭제 클릭 시
// readForm action = "/delete.do" 변경 후 form submit
document.querySelector("#readForm .btn-danger").addEventListener("click", () => {
	const readForm = document.querySelector("#readForm");
	readForm.action = "/delete.do";
	readForm.submit();
})